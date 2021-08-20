package com.iamBedant.app

import com.github.ajalt.clikt.completion.completionOption
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.versionOption

class KotlinSyntheticModuleInputs : CliktCommand(
    name = "ktsy",
    printHelpOnEmptyArgs = true
) {
    private val path: String by option(
        "-p", "--path",
        help = "Relative path to your module. eg: java -jar ktsy.jar -p \"GoHost/auth/authui/src/main/java\" "
    ).default("/")

    private val output: String by option(
        "-o", "--output", help = "Name of your csv output file"
    ).default("result")

    private val debug by option(
        "-d", "--debug",
        help = "Enable debug logging"
    ).flag()

    init {
        completionOption()
        versionOption(
            version = "1.0.0",
            names = setOf("-v", "--version")
        )
    }

    override fun run() {
        if (path == "/") {
            println("Provide a valid path")
        } else {
            parseModule(path, output)
        }
    }
}