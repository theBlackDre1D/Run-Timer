package co.devine_solutions.runtimer.android.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier.height(50.dp),
        shape = CircleShape,
        onClick = onClick
    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}