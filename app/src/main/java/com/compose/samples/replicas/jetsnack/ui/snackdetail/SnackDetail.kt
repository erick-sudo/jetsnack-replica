package com.compose.samples.replicas.jetsnack.ui.snackdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.compose.samples.replicas.jetsnack.ui.theme.JetsnackTheme

@Composable
fun SnackDetail(
    snackId: Long,
    upPress: () -> Unit,
) {
    Box(Modifier.fillMaxSize()) {
        val scroll = rememberScrollState(0)
        Header()
        Body()
        Title()
        Image()
        Up()
        CartBottomBar()
    }
}

@Composable
private fun Header(

) {
    Spacer(
        modifier = Modifier
            .height(280.dp)
            .fillMaxWidth()
            .background(Brush.horizontalGradient(JetsnackTheme.colors.tornado1))
    )
}

@Composable
private fun Up(

) {

}

@Composable
private fun Body(

) {
    Text(text = "Body")
}

@Composable
private fun Title(

) {

}

@Composable
private fun Image(

) {

}

@Composable
private fun CollapsingImageLayout(

) {

}

@Composable
private fun CartBottomBar(

) {

}