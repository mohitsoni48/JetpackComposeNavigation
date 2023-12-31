package com.mohitsoni.jetpackcomposenavigation

//import org.jetbrains.kotlin.codegen.ImplementationBodyCodegen
//import org.jetbrains.kotlin.codegen.extensions.ExpressionCodegenExtension
//
//class NavHostWrapperPlugin : ExpressionCodegenExtension {
//
//    override fun generateClassSyntheticParts(codegen: ImplementationBodyCodegen) {
//        super.generateClassSyntheticParts(codegen)
//    }

//    override fun generateClassSyntheticParts(codegen: ExpressionCodegenExtension.CodegenContext, declaration: IrFunction) {
//        if (declaration.fqNameOrNull()?.asString() == "com.example.NavHostWrapperKt.setupNavHost") {
//            declaration.accept(NavHostWrapperVisitor(codegen))
//        }
//    }
//
//    private class NavHostWrapperVisitor(private val codegen: ImplementationBodyCodegen) :
//        IrElementVisitorVoid {
//
//        private val visitedComposables = mutableSetOf<String>()
//
//        override fun visitCall(expression: IrCall) {
//            super.visitCall(expression)
//
//            val functionDescriptor = expression.symbol.owner as? FunctionDescriptor
//            val functionName = functionDescriptor?.name?.asString()
//
//            if (functionName == "composable") {
//                val composableName = expression.valueArguments[0].getArgumentValue()?.toString()
//                composableName?.let { visitedComposables.add(it) }
//            }
//        }
//
//        override fun visitElement(element: IrElement) {
//            element.acceptChildrenVoid(this)
//        }
//
//        override fun visitModuleFragment(fragment: IrModuleFragment, data: Nothing?) {
//            super.visitModuleFragment(fragment, data)
//
//            val expectedComposables = setOf("Composable1", "Composable2", "Composable3") // Update with your composables
//
//            val missingComposables = expectedComposables - visitedComposables
//            if (missingComposables.isNotEmpty()) {
//                val message = "Missing composables in NavHostWrapper: $missingComposables"
//                codegen.reportMessage(
//                    CompilerMessageSeverity.ERROR,
//                    message,
//                    CompilerMessageSourceLocation.NO_LOCATION
//                )
//            }
//        }
//    }
//}