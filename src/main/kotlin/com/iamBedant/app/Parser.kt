package com.iamBedant.app

import kotlinx.ast.common.AstSource
import kotlinx.ast.common.ast.Ast
import kotlinx.ast.grammar.kotlin.common.summary
import kotlinx.ast.grammar.kotlin.target.antlr.kotlin.KotlinGrammarAntlrKotlinParser
import java.io.File

fun parseModule(path: String, outputCsvFileName: String) {

    val parsingFailedPaths = mutableListOf<String>()
    val sourceFolder = File(path)
    val records = sourceFolder
        .walkTopDown()
        .filter {
            it.isFile && it.name.endsWith(".kt")
        }
        .map {
            it.nameWithoutExtension to AstSource.String(it.readText())
        }
        .map { (name, source) ->
            name to KotlinGrammarAntlrKotlinParser.parseKotlinFile(source)
        }
        .map { (name, source) ->

            try {
                name to source.summary().get()
            } catch (ex: Exception) {
                println("Bad News Parsing of $name has failed")
                parsingFailedPaths.add(name)
                name to emptyList<Ast>()
            }

        }
        .filter { (name, astList) ->
            astList.isNotEmpty() && astList.importList().doesContainKotlinSyntheticImports()
        }
        .map { (name, astList) ->
            ModifiedRecord.from(name, astList)
        }.toList()

    val csvRecord = records
        .sortedByDescending(ModifiedRecord::numberOfKotlinSyntheticImport)
        .joinToString(
            separator = "\n",
            prefix = "Name, Synthetic Imports, WildCardImports\n",
            transform = ModifiedRecord::toCsvRow
        )

    val file = File("$outputCsvFileName.csv").apply { writeText(csvRecord) }
    println("Wrote results to ${file.absolutePath}")
}

private data class ModifiedRecord(
    val name: String,
    val numberOfKotlinSyntheticImport: Int,
    val isWildCardImports: Boolean
) {
    companion object {
        fun from(name: String, astList: List<Ast>): ModifiedRecord {
            val klass = astList.importList()
            val numberOfKotlinSyntheticImport = klass.findNumberOfSyntheticImportsOnTheNode()
            val isWildImportsFound = klass.isWildImportFound()
            return ModifiedRecord(
                name = name,
                numberOfKotlinSyntheticImport = numberOfKotlinSyntheticImport,
                isWildCardImports = isWildImportsFound
            )
        }
    }

    fun toCsvRow(): String = "$name,$numberOfKotlinSyntheticImport,$isWildCardImports,"
}
