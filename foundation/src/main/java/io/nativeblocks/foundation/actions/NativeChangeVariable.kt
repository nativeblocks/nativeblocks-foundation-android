package io.nativeblocks.foundation.actions

import io.nativeblocks.compiler.type.NativeAction
import io.nativeblocks.compiler.type.NativeActionData
import io.nativeblocks.compiler.type.NativeActionEvent
import io.nativeblocks.compiler.type.NativeActionFunction
import io.nativeblocks.compiler.type.NativeActionParameter
import io.nativeblocks.compiler.type.NativeActionProp
import io.nativeblocks.compiler.type.NativeActionValuePicker
import io.nativeblocks.compiler.type.Then
import io.nativeblocks.core.api.provider.action.ActionProps
import io.nativeblocks.core.util.evaluateMixConditionOperator
import io.nativeblocks.core.util.getVariableValue
import io.nativeblocks.core.util.parseWithJsonPath

@NativeAction(
    keyType = "NATIVE_CHANGE_VARIABLE",
    name = "Native Change Variable",
    description = "Native Change Variable",
    version = 1
)
class NativeChangeVariable {
    @NativeActionParameter
    data class Parameter(
        val actionProps: ActionProps,
        @NativeActionData(description = "key of the variable")
        val variableKey: String,
        @NativeActionProp(
            description = "value of the variable",
            valuePicker = NativeActionValuePicker.TEXT_AREA_INPUT
        )
        val variableValue: String,
        @NativeActionEvent(then = Then.NEXT, dataBinding = ["variableKey"])
        val onNext: (String) -> Unit
    )

    @NativeActionFunction
    fun invoke(param: Parameter) {
        val data = param.actionProps.trigger?.data.orEmpty()

        val variableKeyData = param.actionProps.variables?.get(data["variableKey"]?.value.orEmpty())

        var value = param.variableValue
        val variable = variableKeyData ?: return

        param.actionProps.variables?.forEach { variableItem ->
            value = value.getVariableValue(variableItem.key, variableItem.value.value)
        }

        value =
            value.parseWithJsonPath(param.actionProps.variables, param.actionProps.listItemIndex)
        value = value.evaluateMixConditionOperator(type = variable.type)
        param.onNext(value)
    }
}