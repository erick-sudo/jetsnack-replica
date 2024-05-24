package com.compose.samples.replicas.jetsnack.ui.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides.Companion.Horizontal
import androidx.compose.foundation.layout.WindowInsetsSides.Companion.Top
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.compose.samples.replicas.jetsnack.ui.theme.JetsnackTheme
import com.compose.samples.replicas.jetsnack.R
import com.compose.samples.replicas.jetsnack.ui.components.JetsnackDivider
import com.compose.samples.replicas.jetsnack.ui.theme.AlphaNearOpaque

@Composable
fun DestinationBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        backgroundColor = JetsnackTheme.colors.uiBackground.copy(alpha = AlphaNearOpaque),
        contentColor = JetsnackTheme.colors.textSecondary,
        contentPadding = WindowInsets.systemBars.only(Horizontal + Top).asPaddingValues(),
        elevation = 0.dp,
        modifier = modifier
    ) {
        Text(
            text = "Deliver to 1600 Amphitheater Way",
            style = MaterialTheme.typography.subtitle1,
            color = JetsnackTheme.colors.textSecondary,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )

        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ) {
            Icon(
                imageVector = Icons.Outlined.MoreVert,
                tint = JetsnackTheme.colors.brand,
                contentDescription = stringResource(R.string.label_select_delivery)
            )
        }
    }

    JetsnackDivider()
}

@Preview(showSystemUi = true)
@Composable
fun DestinationBarPreview() {
    JetsnackTheme {
        DestinationBar()
    }
}