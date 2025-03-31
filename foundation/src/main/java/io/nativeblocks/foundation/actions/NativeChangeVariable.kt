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
import io.nativeblocks.core.util.actionHandleVariableValue
import io.nativeblocks.core.util.replacingTypeValue

/**
 * An native action responsible for changing the variable within the Nativeblocks system.
 *
 * The `NativeChangeVariable` class enables the modification of a value dynamically.
 * It supports variable substitution, conditional evaluation, and arithmetic operations for property values.
 *
 * Variable Value Supported Formats:
 *   - `{var:variable-key}`: Replaces with the value of the variable.
 *   - `{index}`: Replaces with the list item index.
 *   - `#SCRIPT 2 + 2 #ENDSCRIPT`: The string with evaluated JavaScript code replacing the script tags.
 */
@NativeAction(
    keyType = "NATIVE_CHANGE_VARIABLE",
    name = "Native Change Variable",
    description = "Native Change Variable",
    version = 2
)
class NativeChangeVariable {
    /**
     * @param actionProps The properties of the action, including data and variables associated with the action.
     * @param variableKey The key that specifies which variable to change.
     * @param variableValue The value to assign to the variable. This value can contain expressions or conditions
     *                      that need to be evaluated based on the variable type.
     *                      The evaluated result will be assigned to the variable.
     * @param onNext A callback function that is invoked with the updated value of the variable after the action completes.
     */
    @NativeActionParameter
    data class Parameter(
        val actionProps: ActionProps,
        @NativeActionData(description = "key of the variable")
        val variableKey: String,
        @NativeActionProp(
            description = "value of the variable",
            valuePicker = NativeActionValuePicker.SCRIPT_AREA_INPUT
        )
        val variableValue: String,
        @NativeActionEvent(then = Then.NEXT, dataBinding = ["variableKey"])
        val onNext: (String) -> Unit
    )

    @NativeActionFunction
    fun invoke(param: Parameter) {
        val data = param.actionProps.trigger?.data.orEmpty()
        val variable = param.actionProps.variables?.get(data["variableKey"]?.value.orEmpty()) ?: return

        var value = actionHandleVariableValue(param.actionProps, param.variableValue) ?: ""
        value = value.replacingTypeValue(type = variable.type)
        param.onNext(value)
    }
}