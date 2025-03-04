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
import io.nativeblocks.core.util.evaluateMixConditionOperator

/**
 * A native action that changes a variable's value based on the provided parameters.
 * This class handles the process of evaluating and updating a variable's value,
 * ensuring that the variable is updated based on the current context and conditions.
 *
 * Evaluates a variableValue that may contain mixed conditions and operators based on a variable type
 * specified by variableKey. If the string contains conditions, it evaluates them and converts the result
 * to a boolean string if the variable type is "BOOLEAN". If the string contains operators, it evaluates
 * the expression and converts the result to the specified numeric type.
 *
 * Supported types: "BOOLEAN", "INT", "DOUBLE", "LONG", "FLOAT".
 *
 * The evaluated variableValue is returned as a string, or the original value if no conditions or operators are found.
 *
 * A class responsible for changing the variables of a block within the Nativeblocks system.
 * This action allows modification of a variable.
 *
 * Example 1 (for BOOLEAN type):
 * - variableValue: "(4 / 2 != 0) && (true == true)"
 * - Evaluated value: "true" (evaluates the condition and returns boolean as string)
 *
 * Example 2 (for INT type):
 * - variableValue: "(3+1)/2"
 * - Evaluated value: "2" (evaluates the arithmetic expression and returns the result as an integer)
 *
 * Example 3 (for STRING type):
 * - variableValue: "\"test\" == \"test\""
 * - Evaluated value: "true" (evaluates string equality and returns the result as string)
 *
 * Example 4 (for FLOAT type):
 * - variableValue: "2 * 2.5"
 * - Evaluated value: "5.0" (evaluates multiplication and returns the result as float)
 */
@NativeAction(
    keyType = "NATIVE_CHANGE_VARIABLE",
    name = "Native Change Variable",
    description = "Native Change Variable",
    version = 1
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
            valuePicker = NativeActionValuePicker.TEXT_AREA_INPUT
        )
        val variableValue: String,
        @NativeActionEvent(then = Then.NEXT, dataBinding = ["variableKey"])
        val onNext: (String) -> Unit
    )

    @NativeActionFunction
    fun invoke(param: Parameter) {
        val data = param.actionProps.trigger?.data.orEmpty()
        val variable = param.actionProps.variables?.get(data["variableKey"]?.value.orEmpty())
        if (variable == null) return

        var value = actionHandleVariableValue(param.actionProps, param.variableValue) ?: ""
        value = value.evaluateMixConditionOperator(type = variable.type)
        param.onNext(value)
    }
}