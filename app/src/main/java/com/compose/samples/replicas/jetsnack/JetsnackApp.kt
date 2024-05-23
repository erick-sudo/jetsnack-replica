package com.compose.samples.replicas.jetsnack

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.compose.samples.replicas.jetsnack.navigation.JetsnackNavHost
import com.compose.samples.replicas.jetsnack.ui.theme.JetsnackTheme

@Composable
fun JetsnackApp() {
    JetsnackTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = JetsnackTheme.colors.uiBackground
        ) {
            JetsnackNavHost()
        }
    }
}