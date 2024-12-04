package com.oza.demoprojectforflow

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.oza.demoprojectforflow.ui.theme.DemoProjectForFlowTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
            DemoProjectForFlowTheme {
                LaunchedEffect(key1 = Unit) {
                    var demo = CoroutineScope(Dispatchers.IO).async {
                        ""
                    }
//                    simpleFlowCaller()
//                    flowOfUserList.collect { user ->
//                        Log.d("User Flow", user)
//                    }
//                    CoroutineScope(Dispatchers.IO).launch {
//                        launch {
//                            flowOfUserList.collect { user ->
//                                Log.d("User Flow", user)
//                            }
//                        }
//                        launch {
//                            userList.asFlow().collect { user ->
//                                Log.d("User Flow", user)
//                            }
//                        }
//                        withTimeoutOrNull(2000) {
//                            flowOfUserList.collect { user ->
//                                Log.d("User Cancelled Flow", user)
//                            }
//                        }
//                    }
                    lifecycleScope.launch(Dispatchers.IO) {
                        simpleFlow()
                            .filter { user ->
                                user == "user3"
                            }
                            .map { user ->
                                "Mr. ${user.uppercase()}"
                            }.collect { user ->
                            Log.d("user", user)
                        }
                    }
                }
            }
        }
    }

    fun simpleFlowCaller() {
        CoroutineScope(Dispatchers.IO).launch {
            simpleFlow().collect { user ->
                Log.d("user", user)
            }
            launch {
                simpleFlow().collect { user ->
                    Log.d("user", "flow1 $user")
                }
            }
            launch {
                simpleFlow().collect { user ->
                    Log.d("user", "flow2 $user")
                }
            }
        }
    }

    private fun simpleFlow(): Flow<String> = flow {
        userList.forEach { user ->
            delay(1000)
            emit(user)
        }
    }
}