package co.devine_solutions.runtimer.android.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

enum class TimerFieldType {
    SECONDS,
    MINUTES,
    HOURS
}

data class TimerField(
    val value: Int,
    val fieldType: TimerFieldType,
    val onFieldChange: (Int?) -> Unit
)

data class TimerComponentConfiguration(
    val seconds: TimerField,
    val minutes: TimerField,
    val hours: TimerField? = null
) {
    companion object {
        fun defaultTimerConfiguration(): TimerComponentConfiguration {
            return TimerComponentConfiguration(
                seconds = TimerField(30, TimerFieldType.SECONDS, {}),
                minutes = TimerField(0, TimerFieldType.MINUTES, {})
            )
        }
    }

    fun getTimeInSeconds(): Int {
        val minutesInSeconds = this.minutes.value * 60
        return minutesInSeconds + this.seconds.value
    }
}

private val timerKeyboardOptions = KeyboardOptions(
    capitalization = KeyboardCapitalization.None,
    autoCorrect = false,
    keyboardType = KeyboardType.Number,
    imeAction = ImeAction.Next
)

@Composable
fun TimerComponent(
    minutes: TimerField,
    seconds: TimerField,
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Cyan),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TextField(
                modifier = Modifier.weight(1f),
                value = minutes.value.toString(),
                onValueChange = { minutesValue ->
                    val handledValue = if (minutesValue.isNotEmpty()) minutesValue.toInt() else 0
                    minutes.onFieldChange(handledValue)
                },
                keyboardOptions = timerKeyboardOptions,
                placeholder = { Text(text = getLabelForFieldType(minutes.fieldType)) }
            )

            TextField(
                modifier = Modifier.weight(1f),
                value = seconds.value.toString(),
                onValueChange = { secondsValue ->
                    val handledValue = if (secondsValue.isNotEmpty()) secondsValue.toInt() else 0
                    seconds.onFieldChange(handledValue)
                },
                keyboardOptions = timerKeyboardOptions,
                placeholder = { Text(text = getLabelForFieldType(seconds.fieldType)) }
            )
        }
    }
}

private fun getLabelForFieldType(fieldType: TimerFieldType): String {
    return when (fieldType) {
        TimerFieldType.SECONDS -> "Seconds"
        TimerFieldType.MINUTES -> "Minutes"
        TimerFieldType.HOURS -> "Hours"
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun PreviewTimerCompose() {
    val minutes = TimerField(
        value = 2,
        fieldType = TimerFieldType.MINUTES,
        onFieldChange = { }
    )
    val seconds = TimerField(
        value = 30,
        fieldType = TimerFieldType.SECONDS,
        onFieldChange = { }
    )
    TimerComponent(minutes = minutes, seconds = seconds)
}