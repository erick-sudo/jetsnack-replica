package com.compose.samples.replicas.jetsnack.ui.components

import android.content.res.Resources
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.DrawerDefaults
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import com.compose.samples.replicas.jetsnack.model.SnackbarManager
import com.compose.samples.replicas.jetsnack.ui.theme.JetsnackTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun JetsnackScaffold(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(hostState = it)},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    isFloatingActionButtonDockerd: Boolean = false,
    drawerContent: @Composable (ColumnScope.() -> Unit)? = null,
    drawerShape: Shape = MaterialTheme.shapes.large,
    drawerElevation: Dp = DrawerDefaults.Elevation,
    drawerBackgroundColor: Color = JetsnackTheme.colors.uiBackground,
    drawerContentColor: Color = JetsnackTheme.colors.textSecondary,
    drawerScrimColor: Color = JetsnackTheme.colors.uiBorder,
    backgroundColor: Color = JetsnackTheme.colors.uiBackground,
    contentColor: Color = JetsnackTheme.colors.textSecondary,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        scaffoldState = scaffoldState,
        topBar = topBar, bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        isFloatingActionButtonDocked = isFloatingActionButtonDockerd,
        drawerContent = drawerContent,
        drawerShape = drawerShape,
        drawerElevation = drawerElevation,
        drawerBackgroundColor = drawerBackgroundColor,
        drawerContentColor = drawerContentColor,
        drawerScrimColor = drawerScrimColor,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        content = content
    )
}

/**
 * Remember and create an instance of [JetsnackScaffoldState]
 */
@Composable
fun rememberJetsnackScaffoldState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
):  JetsnackScaffoldState = remember(scaffoldState, snackbarManager, resources, coroutineScope) {
        JetsnackScaffoldState(scaffoldState, snackbarManager, resources, coroutineScope)
}

/**
 * Responsible for holding [ScaffoldState], handles the logic of showing snackbar messages
 */
@Stable
class JetsnackScaffoldState(
    val scaffoldState: ScaffoldState,
    private val snackbarManager: SnackbarManager,
    private val resources: Resources,
    coroutineScope: CoroutineScope
) {

    // Process snackbars coming from SnackbarManager
    init {
        coroutineScope.launch {
            snackbarManager.messages.collect { currentMessages ->
                if(currentMessages.isNotEmpty()) {
                    val message = currentMessages[0]
                    val text = resources.getText(message.messageId)

                    snackbarManager.setMessageShow(message.id)

                    scaffoldState.snackbarHostState.showSnackbar(text.toString())
                }
            }
        }
    }
}

/**
 * A composable function that return the [Resources]. It get recomposed when configuration
 * get updated
 */
@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}