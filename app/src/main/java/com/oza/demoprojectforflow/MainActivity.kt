package com.oza.demoprojectforflow

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.oza.demoprojectforflow.ui.theme.DemoProjectForFlowTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull

class MainActivity : ComponentActivity() {
    val userList = listOf("user1", "user2", "user3", "user4", "user5")

    val flowOfUserList = flowOf("user6", "user7", "user8", "user9", "user10")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val currentProducedInt = remember {
                mutableStateOf(0)
            }
            DemoProjectForFlowTheme {
                LaunchedEffect(key1 = currentProducedInt) {
                    GlobalScope.launch {
                        val data = producer()
                        data.collect {
                            Log.d("TAG", "onCreate: $it")
                            currentProducedInt.value = it
                        }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text("Current value is ${currentProducedInt.value}")
                }
            }
        }
    }

    fun producer() = flow<Int> {
        val list = listOf(1,2,3,4,5,6,7,8,9,10)
        list.forEach {
            delay(1000L)
            emit(it)
        }
    }
}
