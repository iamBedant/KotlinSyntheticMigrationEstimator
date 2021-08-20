package com.iamBedant.app

import kotlinx.ast.common.ast.Ast
import kotlinx.ast.common.ast.DefaultAstNode

fun Iterable<Ast>.importList(): DefaultAstNode {
    return find { it is DefaultAstNode && it.description == "importList" } as DefaultAstNode
}

fun DefaultAstNode.doesContainKotlinSyntheticImports(): Boolean {
    return this.children.any { it.description.contains("kotlinx.android.synthetic") }
}

fun DefaultAstNode.findNumberOfSyntheticImportsOnTheNode(): Int {
    return this.children.filter { it.description.contains("kotlinx.android.synthetic") }.count()
}

fun DefaultAstNode.isWildImportFound(): Boolean {
    return this.children.any {
        it.description.contains("kotlinx.android.synthetic") && it.description.endsWith(".*)")
    }
}