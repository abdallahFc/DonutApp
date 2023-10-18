package com.example.donutapp.composable

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment.Companion as Alignment1

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SideEffectDemo() {
    var state by remember { mutableIntStateOf(0) }
    var isClicked by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    Column {
        Button(onClick = { state++ }) {
            Text(text = "Click me")
        }
        val color=if (isClicked) Color.Green else Color.Gray
        Button(
            onClick = { isClicked = !isClicked },
            colors = ButtonDefaults.buttonColors(color)
        ) {
            Text(text = "Click me")
        }
        LaunchedEffect(key1 = state){
           Log.d("SideEffectDemo", "LaunchedEffect: $state")
        }
        SideEffect{
            Log.d("SideEffectDemo", "SideEffect: $state")
        }
        scope.launch {
            Log.d("SideEffectDemo", "Coroutine: $state")
        }
        }

    }

@Composable
fun WithoutSideEffectExample() {

    var counter by remember { mutableStateOf(0) }
    val context = LocalContext.current

    // on every recomposition , this toast will show
   Toast.makeText(context, "Counter: $counter", Toast.LENGTH_SHORT).show()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { counter++ }) {
            Text(text = "Click here")
        }
        Text(text = "$counter")
    }

}
@Composable
fun WithLaunchEffect() {

    var counter by remember { mutableStateOf(0) }
    val context = LocalContext.current


    LaunchedEffect(key1 = true) {
        Toast.makeText(context, "Counter: $counter", Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { counter++ }) {
            Text(text = "Click here")
        }
        Text(text = "$counter")
    }

}

sealed class ApiState<out T> {
    data class Success<T>(val data: String) : ApiState<T>()
    object Loading : ApiState<Nothing>()
    object Empty : ApiState<Nothing>()
}

class MainViewModel : ViewModel() {
    private val _apiState: MutableState<ApiState<String>> = mutableStateOf(ApiState.Empty)
    var apiState: State<ApiState<String>> = _apiState
        private set

    fun getApiData() = viewModelScope.launch {
        _apiState.value = ApiState.Loading
        delay(2000)
        _apiState.value = ApiState.Success("Data loaded successfully..")
    }

}

@Composable
fun LaunchEffectExample() {
    val viewModel: MainViewModel = viewModel()
    var call by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = call) {
        viewModel.getApiData()
    }
// never call this function here as whenever recomposition occurs this function will call again
//    viewModel.getApiData()

    when (val res = viewModel.apiState.value) {
        is ApiState.Success -> {
            Log.d("main", "Success")
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = res.data, fontSize = 25.sp)
                Button(onClick = {
                    call = !call
                }) {
                    Text(text = "Call Api again !")
                }
            }
        }
        ApiState.Loading -> {
            Log.d("main", "Loading")
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        ApiState.Empty -> {}
    }

}

@Composable
fun CallStateScreen() {

    var callState: String by remember { mutableStateOf("No Call State") }
    val context = LocalContext.current

    val callReceiver = remember {
        object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val state = intent?.getStringExtra(TelephonyManager.EXTRA_STATE)
                Log.d("CallStateReceiver", "Received call state: $state")
                if (state == TelephonyManager.EXTRA_STATE_OFFHOOK){
                    callState = "Call State started"
                }
                if (state == TelephonyManager.EXTRA_STATE_RINGING){
                    callState = "Call State Ringing"
                }
                if (state != TelephonyManager.EXTRA_STATE_IDLE){
                    callState = "Call State End"
                }
            }
        }
    }

    DisposableEffect(key1 = Unit) {
        val intentFilter = IntentFilter()
        intentFilter.addAction(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
        context.registerReceiver(callReceiver, intentFilter)
        onDispose {
            context.unregisterReceiver(callReceiver)
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = callState)
    }
}

@Composable
fun AirplaneModeScreen() {
    var data by remember{ mutableStateOf("No State") }
    val context = LocalContext.current
    val broadcastReceiver = remember {
        object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                val bundle = intent?.getBooleanExtra("state",false) ?: return
                data = if(bundle)
                    "Airplane mode enabled"
                else
                    "Airplane mode disabled"
            }

        }
    }

    DisposableEffect(key1 = Unit){
        val intentFilter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        context.applicationContext.registerReceiver(broadcastReceiver,intentFilter)
        Log.d("fdfdf","tag")
        onDispose {
            Log.d("fdfdf","tags")
            context.applicationContext.unregisterReceiver(broadcastReceiver)
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = data)
    }

}

@SuppressLint("UnrememberedMutableState")
@Composable
fun DerivedStateExample() {

    var counter =  mutableStateOf(0)
    var c = remember { 0 }
    val e=5
    LaunchedEffect(key1 = counter){
        Log.d("DerivedStateExample", "LaunchedEffect: ${counter.value}")
    }
    Log.d("DerivedStateExample", "c: $c")
    val evenOdd by remember {
        derivedStateOf {
            if (counter.value % 2 == 0) "even"
            else if (counter.value == 0) "zero"
            else "odd"
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "$e", fontSize = 30.sp)

        Text(text = "count is $evenOdd", fontSize = 30.sp)

        Button(onClick = {
            counter.value++
        }) {
            Text(text = "Counter")
        }

    }

}




@SuppressLint("ProduceStateDoesNotAssignValue")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DerivedStateOfExample() {
    val viewModel: MainViewModel = viewModel()

    var numberOne by remember { mutableStateOf(0) }
    var numberTwo by remember { mutableStateOf(0) }
    val b= produceState(initialValue = "Loading...", key1 = viewModel.apiState.value){
        delay(5000)
        value=viewModel.apiState.value.toString()
    }
    val result by remember { derivedStateOf { numberOne + numberTwo } }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TextField(value = "$numberOne", onValueChange = { numberOne = it.toIntOrNull() ?: 0 })
        TextField(value = "$numberTwo", onValueChange = { numberTwo = it.toIntOrNull() ?: 0 })
        Text(text = "Result is : ${b.value}", fontSize = 30.sp)
    }

}

@Preview(showSystemUi = true)
@Composable
fun SideEffectDemoPreview() {
    DerivedStateExample()
}


@SuppressLint("UnrememberedMutableState")
@Composable
fun CounterScreen() {
    var counterState = remember { mutableStateOf(0)}

    Column {
        Text("Counter: ${counterState.value}")
        Button(onClick = { counterState.value++ }) {
            Text("Increment")
        }
    }
    SideEffect {
        Log.d("CounterScreen", "SideEffect: ${counterState.value}")
    }
    LaunchedEffect(key1 = counterState) {
        Log.d("CounterScreen", "LaunchedEffect: ${counterState.value}")
    }
}

@Preview
@Composable
fun CounterScreenPreview() {
    CounterScreen()
}