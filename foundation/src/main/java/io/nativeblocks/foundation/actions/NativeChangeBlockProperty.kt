package io.nativeblocks.foundation.actions

import io.nativeblocks.compiler.type.NativeAction
import io.nativeblocks.compiler.type.NativeActionEvent
import io.nativeblocks.compiler.type.NativeActionFunction
import io.nativeblocks.compiler.type.NativeActionParameter
import io.nativeblocks.compiler.type.NativeActionProp
import io.nativeblocks.compiler.type.Then
import io.nativeblocks.core.api.provider.action.ActionProps
import io.nativeblocks.core.util.evaluateMixConditionOperator
import io.nativeblocks.core.util.getVariableValue

@NativeAction(
    keyType = "NATIVE_CHANGE_BLOCK_PROPERTY",
    name = "Native Change Block Property",
    description = "Native Change Block Property",
    version = 1
)
class NativeChangeBlockProperty {
    @NativeActionParameter
    data class Parameter(
        val actionProps: ActionProps,
        @NativeActionProp(description = "key of the block")
        val blockKey: String,
        @NativeActionProp(description = "key of the block's property")
        val propertyKey: String,
        @NativeActionProp(description = "new value for the block's Mobile property")
        val propertyValueMobile: String,
        @NativeActionProp(description = "new value for the block's Tablet property")
        val propertyValueTablet: String,
        @NativeActionProp(description = "new value for the block's Desktop property")
        val propertyValueDesktop: String,
        @NativeActionEvent(then = Then.NEXT)
        val onNext: () -> Unit
    )

    @NativeActionFunction
    fun invoke(param: Parameter) {
        var valueMobile = param.propertyValueMobile
        var valueTablet = param.propertyValueTablet
        var valueDesktop = param.propertyValueDesktop

        val block = param.actionProps.blocks.orEmpty()[param.blockKey]
        val blockProperties = block?.properties.orEmpty().toMutableMap()
        var currentProperty = blockProperties[param.propertyKey]
        if (currentProperty != null) {
            if (param.propertyValueMobile.isNotEmpty()) {
                param.actionProps.variables?.forEach { variableItem ->
                    valueMobile =
                        valueMobile.getVariableValue(variableItem.key, variableItem.value.value)
                }
                valueMobile = valueMobile.evaluateMixConditionOperator(type = currentProperty.type)
                currentProperty = currentProperty.copy(valueMobile = valueMobile)
            }
            if (param.propertyValueTablet.isNotEmpty()) {
                param.actionProps.variables?.forEach { variableItem ->
                    valueTablet =
                        valueTablet.getVariableValue(variableItem.key, variableItem.value.value)
                }
                valueTablet = valueTablet.evaluateMixConditionOperator(type = currentProperty.type)
                currentProperty = currentProperty.copy(valueTablet = valueTablet)
            }
            if (param.propertyValueDesktop.isNotEmpty()) {
                param.actionProps.variables?.forEach { variableItem ->
                    valueDesktop =
                        valueDesktop.getVariableValue(variableItem.key, variableItem.value.value)
                }
                valueDesktop =
                    valueDesktop.evaluateMixConditionOperator(type = currentProperty.type)
                currentProperty = currentProperty.copy(valueDesktop = valueDesktop)
            }
            blockProperties[currentProperty.key] = currentProperty
        }
        if (block != null) {
            param.actionProps.onChangeBlock?.invoke(block.copy(properties = blockProperties))
        }
        param.onNext()
    }
}
