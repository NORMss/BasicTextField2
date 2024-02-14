@file:OptIn(ExperimentalFoundationApi::class)

package com.norm.mybasictextfield2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text2.BasicSecureTextField
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.InputTransformation
import androidx.compose.foundation.text2.input.TextFieldBuffer
import androidx.compose.foundation.text2.input.TextFieldCharSequence
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.norm.mybasictextfield2.ui.theme.MyBasicTextField2Theme
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyBasicTextField2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var text1 by remember {
                        mutableStateOf("")
                    }
                    var text2 by remember {
                        mutableStateOf("")
                    }

                    val modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(16.dp)

                    LaunchedEffect(key1 = true) {
                        delay(5000L)
                        text1 = "Hello, World!"
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        BasicTextField(
                            value = text1,
                            onValueChange = {
                                text1 = it
                            },
                            modifier = modifier
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        val state1 = rememberTextFieldState()
                        val state2 = rememberTextFieldState()
//                        state.edit {
//
//                        }

//                        state.textAsFlow()
//                            .map{ }

//                        snapshotFlow { state }

                        BasicTextField2(
                            state = state1,
                            modifier = modifier,
                            inputTransformation = AndroidInputTransformation1,
                            scrollState = rememberScrollState()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        BasicSecureTextField(
                            state = state2,
                            modifier = modifier,
                            inputTransformation = AndroidInputTransformation2,
                            scrollState = rememberScrollState()
                        )
                    }
                }
            }
        }
    }
}

object AndroidInputTransformation1 : InputTransformation {
    override fun transformInput(
        originalValue: TextFieldCharSequence,
        valueWithChanges: TextFieldBuffer
    ) {
        if (!"Android".contains(valueWithChanges.asCharSequence())) {
            valueWithChanges.revertAllChanges()
        }
    }
}

object AndroidInputTransformation2 : InputTransformation {
    override fun transformInput(
        originalValue: TextFieldCharSequence,
        valueWithChanges: TextFieldBuffer
    ) {
        if (!"password".contains(valueWithChanges.asCharSequence())) {
            valueWithChanges.revertAllChanges()
        }
    }
}