package co.devine_solutions.runtimer.android.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.devine_solutions.runtimer.android.RunTimerApplicationTheme
import co.devine_solutions.runtimer.android.ui.components.PrimaryButton
import co.devine_solutions.runtimer.android.ui.components.TimerComponent
import co.devine_solutions.runtimer.android.ui.components.TimerComponentConfiguration
import co.devine_solutions.runtimer.android.ui.components.TimerField
import co.devine_solutions.runtimer.android.ui.components.TimerFieldType
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RunTimerDashboard()
        }
    }
}

@Composable
fun RunTimerDashboard(viewModel: MainActivityVM = getViewModel()) {
    val timerState = viewModel.timers.collectAsState(initial = listOf())

    RunTimerApplicationTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.DarkGray,
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {

                    LazyColumn {
                        var totalSeconds = 0

                        timerState.value.forEach { timerConfiguration ->
                            item {
                                TimerComponent(
                                    minutes = timerConfiguration.minutes,
                                    seconds = timerConfiguration.seconds
                                )

                                totalSeconds += timerConfiguration.getTimeInSeconds()

                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }

                        item {
                            Text(
                                text = "Total time in seconds: $totalSeconds",
                                color = Color.White
                            )
                        }
                    }

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        PrimaryButton("Add timer") {
                            viewModel.addTimer(TimerComponentConfiguration.defaultTimerConfiguration())
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        PrimaryButton("Start") {
                            // TODO
                        }
                    }
                }
            }
        }
    }
}

@Preview(device = "id:pixel_5")
@Composable
fun DefaultPreview() {
    RunTimerApplicationTheme {
        RunTimerDashboard()
    }
}
