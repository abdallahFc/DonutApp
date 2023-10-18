import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@Composable
fun LifecycleDemo() {
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)
    val lifecycle = lifecycleOwner.value.lifecycle

    val onCreateEventObserver = rememberUpdatedState(LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_CREATE) {
            Log.d("LifecycleDemo", "onCreate")
        }
    })

    val onStartEventObserver = rememberUpdatedState(LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_START) {
            Log.d("LifecycleDemo", "onStart")
        }
    })

    val onResumeEventObserver = rememberUpdatedState(LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            Log.d("LifecycleDemo", "onResume")
        }
    })

    val onPauseEventObserver = rememberUpdatedState(LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_PAUSE) {
            Log.d("LifecycleDemo", "onPause")
        }
    })

    val onStopEventObserver = rememberUpdatedState(LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_STOP) {
            Log.d("LifecycleDemo", "onStop")
        }
    })

    val onDestroyEventObserver = rememberUpdatedState(LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_DESTROY) {
            Log.d("LifecycleDemo", "onDestroy")
        }
    })

    val onAnyEventObserver = rememberUpdatedState(LifecycleEventObserver { _, event ->
        Log.d("LifecycleDemo", "onAny: $event")
    })

    DisposableEffect(Unit) {
        lifecycle.addObserver(onCreateEventObserver.value)
        lifecycle.addObserver(onStartEventObserver.value)
        lifecycle.addObserver(onResumeEventObserver.value)
        lifecycle.addObserver(onPauseEventObserver.value)
        lifecycle.addObserver(onStopEventObserver.value)
        lifecycle.addObserver(onDestroyEventObserver.value)
        lifecycle.addObserver(onAnyEventObserver.value)

        onDispose {
            lifecycle.removeObserver(onCreateEventObserver.value)
            lifecycle.removeObserver(onStartEventObserver.value)
            lifecycle.removeObserver(onResumeEventObserver.value)
            lifecycle.removeObserver(onPauseEventObserver.value)
            lifecycle.removeObserver(onStopEventObserver.value)
            lifecycle.removeObserver(onDestroyEventObserver.value)
            lifecycle.removeObserver(onAnyEventObserver.value)

            Log.d("LifecycleDemo", "onDispose")
        }
    }
}

private val LocalInt = staticCompositionLocalOf { 0 }
private val tag = "CompLocal"

@Composable
fun CompositionLocalDemo() {
    LocalContext

    var counter by remember {
        mutableStateOf(-1)
    }

    Button(onClick = { counter++ }) {
        Text(text = "Increment")
    }

    if (counter < 0) return

    Log.d(tag, "************** Using CompositionLocal **************")
    CompositionLocalProvider(
        LocalInt provides counter,
    ) {
        Parent()
    }
}

@Composable
private fun Parent() {
    Log.d(tag, "Start Parent - LocalInt: ${LocalInt.current} ")
    Log.d(tag, "St")
    Child()
    Log.d(tag, "End Parent - LocalInt: ${LocalInt.current}")
}

@Composable
private fun Child() {
    Log.d(tag, "Start Child - LocalInt: ${LocalInt.current} ")
    GrandChild()
    Log.d(tag, "Emd Child - LocalInt: ${LocalInt.current} ")
}

@Composable
private fun GrandChild() {
    Log.d(tag, "Start GrandChild")
    RememberDemo()
    Log.d(tag, "End GrandChild")
}

@Composable
fun RememberDemo() {
    Log.d(tag, "************** Using Remember **************")
    Log.d(tag, "Demo: ${LocalInt.current} ")
}
