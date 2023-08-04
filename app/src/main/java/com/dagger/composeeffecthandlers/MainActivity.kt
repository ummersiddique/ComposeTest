package com.dagger.composeeffecthandlers

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dagger.composeeffecthandlers.ui.theme.ComposeEffectHandlersTheme
import com.dagger.composeeffecthandlers.viewmodels.LaunchedEffectViewModel
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {

    private val viewModel by lazy {
        LaunchedEffectViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeEffectHandlersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") {
                            DemoScreen(text = "Home Screen") {
                                navController.navigate("details")
                            }
                        }
                        composable("details") {
                            DemoScreen(text = "Details Screen") {
                                navController.navigate("settings")
                            }
                        }
                        composable("settings") {
                            DemoScreen(text = "Settings Screen") {
                                navController.navigate("home") {
                                    launchSingleTop = true
                                    popUpTo(0)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LaunchedEffectAnimation(
    counter: Int
) {
    val animatable = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = counter) {
        animatable.animateTo(counter.toFloat())
    }
}

@Composable
fun LaunchedEffectSample(
    viewModel: LaunchedEffectViewModel
) {
    var text by remember { mutableStateOf("") }
    val counter = remember { mutableStateOf(1) }
    val context = LocalContext.current

    LaunchedEffectAnimation(counter = 12)

    // this is nothing
    LaunchedEffect(key1 = true) {
        viewModel.sharedFlow.collect { event ->
            when (event) {
                is LaunchedEffectViewModel.ScreenEvents.Navigate -> {

                }

                is LaunchedEffectViewModel.ScreenEvents.ShowSnackBar -> {
                    Toast.makeText(context, event.message + text, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = {
                counter.value = counter.value + 1
                text += counter.value
                viewModel.toast()
            },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "Hello! : ${counter.value}",
                modifier = Modifier
            )
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val counter = remember {
        mutableStateOf(1)
    }
    var text by remember { mutableStateOf("") }

    LaunchedEffect(key1 = text) {
        delay(1000)
        println("Counter value is now : $text")
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = {
                counter.value = counter.value + 1
                text += counter.value
            },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "Hello $name! : ${counter.value}",
                modifier = modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeEffectHandlersTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun Screen1Preview() {
    DemoScreen(text = "First Screen") {

    }
}

@Preview(showBackground = true)
@Composable
fun Screen2Preview() {
    ComposeEffectHandlersTheme {
        DemoScreen(text = "Second Screen") {

        }
    }
}

@Composable
fun DemoScreen(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = onClick,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = text,
                modifier = modifier
            )
        }
    }
}