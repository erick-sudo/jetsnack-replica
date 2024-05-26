package com.compose.samples.replicas.jetsnack.ui.home.cart

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.compose.samples.replicas.jetsnack.R
import com.compose.samples.replicas.jetsnack.model.OrderLine
import com.compose.samples.replicas.jetsnack.model.ShippingDestination
import com.compose.samples.replicas.jetsnack.model.SnackCollection
import com.compose.samples.replicas.jetsnack.model.SnackRepo
import com.compose.samples.replicas.jetsnack.ui.components.JetsnackButton
import com.compose.samples.replicas.jetsnack.ui.components.JetsnackDivider
import com.compose.samples.replicas.jetsnack.ui.components.JetsnackScaffold
import com.compose.samples.replicas.jetsnack.ui.components.JetsnackSnackbar
import com.compose.samples.replicas.jetsnack.ui.components.JetsnackSurface
import com.compose.samples.replicas.jetsnack.ui.components.QuantitySelector
import com.compose.samples.replicas.jetsnack.ui.components.SnackCollection
import com.compose.samples.replicas.jetsnack.ui.components.SnackImage
import com.compose.samples.replicas.jetsnack.ui.components.rememberJetsnackScaffoldState
import com.compose.samples.replicas.jetsnack.ui.home.DestinationBar
import com.compose.samples.replicas.jetsnack.ui.home.HomeSections
import com.compose.samples.replicas.jetsnack.ui.home.JetsnackBottomBar
import com.compose.samples.replicas.jetsnack.ui.home.ShippingDestinationItem
import com.compose.samples.replicas.jetsnack.ui.home.ShippingDestinationScreen
import com.compose.samples.replicas.jetsnack.ui.theme.AlphaNearOpaque
import com.compose.samples.replicas.jetsnack.ui.theme.JetsnackTheme
import com.compose.samples.replicas.jetsnack.ui.utils.OnNavigateToRoute
import com.compose.samples.replicas.jetsnack.ui.utils.OnSnackClick
import com.compose.samples.replicas.jetsnack.ui.utils.formatPrice

@Composable
fun Cart(
    orderLines: List<OrderLine>,
    shippingDestination: ShippingDestination?,
    onShippingAddress: (ShippingDestination) -> Unit,
    onSnackClick: OnSnackClick,
    onNavigateToRoute: OnNavigateToRoute,
    increaseItemCount: (Long) -> Unit,
    decreaseItemCount: (Long) -> Unit,
    removeSnack: (Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val inspiredByCart by remember {
        mutableStateOf(SnackRepo.getInspiredByCart())
    }
    val jetsnackScaffoldState = rememberJetsnackScaffoldState()
    var shippingScreenVisible by rememberSaveable {
        mutableStateOf(false)
    }

    Box {
        JetsnackScaffold(
            bottomBar = {
                JetsnackBottomBar(
                    tabs = HomeSections.entries.toTypedArray(),
                    currentRoute = HomeSections.CART.route,
                    navigateToRoute = onNavigateToRoute
                )
            },
            snackbarHost = { snackbarHostState ->
                SnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier
                        .systemBarsPadding(),
                    snackbar = { snackbarData ->
                        JetsnackSnackbar(snackbarData)
                    }
                )
            },
            scaffoldState = jetsnackScaffoldState.scaffoldState,
            modifier = modifier
        ) { paddingValues ->
            Cart(
                orderLines = orderLines,
                shippingDestination = shippingDestination,
                removeSnack = removeSnack,
                increaseItemCount = increaseItemCount,
                decreaseItemCount = decreaseItemCount,
                inspiredByCart = inspiredByCart,
                onSnackClick = onSnackClick,
                onShowShippingAddressScreen = { shippingScreenVisible = true },
                modifier = Modifier.padding(paddingValues),
            )
        }

        AnimatedVisibility(
            visible = shippingScreenVisible,
            enter = slideInHorizontally() + expandHorizontally(
                expandFrom = Alignment.End
            ) + fadeIn(initialAlpha = 0f),
            exit = slideOutHorizontally(
                animationSpec = spring(stiffness = Spring.StiffnessLow, dampingRatio = 0.4f)
            ) + shrinkHorizontally()
                    + fadeOut()
        ) {
            ShippingDestinationScreen(
                onDismiss = { shippingScreenVisible = false },
                onShippingDestination = {
                    onShippingAddress(it)
                    shippingScreenVisible = false
                }
            )
        }
    }
}

@Composable
private fun Cart(
    orderLines: List<OrderLine>,
    shippingDestination: ShippingDestination?,
    removeSnack: (Long) -> Unit,
    increaseItemCount: (Long) -> Unit,
    decreaseItemCount: (Long) -> Unit,
    inspiredByCart: SnackCollection,
    onSnackClick: OnSnackClick,
    onShowShippingAddressScreen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    JetsnackSurface(modifier = modifier.fillMaxSize()) {
        Box {
            CartContent(
                orderLines = orderLines,
                removeSnack = removeSnack,
                increaseItemCount = increaseItemCount,
                decreaseItemCount = decreaseItemCount,
                inspiredByCart = inspiredByCart,
                onSnackClick = onSnackClick,
                shippingDestination = shippingDestination,
                onShowShippingAddressScreen = onShowShippingAddressScreen,
                modifier = Modifier.align(Alignment.TopCenter)
            )

            DestinationBar(modifier = modifier.align(Alignment.TopCenter))

            CheckoutBar(modifier = Modifier.align(Alignment.BottomCenter))
        }
    }
}

@Composable
private fun CartContent(
    orderLines: List<OrderLine>,
    removeSnack: (Long) -> Unit,
    increaseItemCount: (Long) -> Unit,
    decreaseItemCount: (Long) -> Unit,
    inspiredByCart: SnackCollection,
    onSnackClick: (Long) -> Unit,
    shippingDestination: ShippingDestination?,
    onShowShippingAddressScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    val resources = LocalContext.current.resources
    val snackCountFormattedString = remember(orderLines.size, resources) {
        resources.getQuantityString(
            R.plurals.cart_order_count,
            orderLines.size, orderLines.size
        )
    }

    LazyColumn(modifier) {
        item {
            Spacer(
                modifier = Modifier
                    .windowInsetsTopHeight(
                        WindowInsets.statusBars.add(WindowInsets(top = 56.dp))
                    )
            )

            Text(
                text = stringResource(R.string.cart_order_header, snackCountFormattedString),
                style = MaterialTheme.typography.h6,
                color = JetsnackTheme.colors.brand,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .heightIn(min = 56.dp)
                    .padding(horizontal = 24.dp, vertical = 4.dp)
                    .wrapContentHeight()
            )
        }

        items(orderLines) { orderLine ->
            SwipeDismissItem(
                background = { offsetX ->
                    /**
                     * Background color changes from light gray to red when the
                     * swipe to delete with exceeds 160.dp
                     */
                    val backgroundColor = if(offsetX < (-160).dp) {
                        JetsnackTheme.colors.error
                    } else {
                        JetsnackTheme.colors.uiFloated
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(backgroundColor),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Center
                    ) {
                        // Set 4.dp padding only if offset is bigger than 160.dp
                        val padding: Dp by animateDpAsState(
                            if (offsetX > (-160).dp) 4.dp else 0.dp, label = ""
                        )

                        Box(
                            modifier = Modifier
                                .width(offsetX * -1)
                                .padding(padding)
                        ) {
                            // Height equals to width removing padding
                            val height = (offsetX + 8.dp) * -1
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(height)
                                    .align(Alignment.Center),
                                shape = CircleShape,
                                color = JetsnackTheme.colors.error
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    // Icon must be visible while in this width range
                                    if (offsetX < (-40).dp && offsetX > (-152).dp) {
                                        // Icon alpha decreases as it is about to disappear
                                        val iconAlpha: Float by animateFloatAsState(
                                            if (offsetX < (-120).dp) 0.5f else 1f, label = ""
                                        )

                                        Icon(
                                            imageVector = Icons.Filled.Delete,
                                            modifier = Modifier
                                                .size(16.dp)
                                                .graphicsLayer(alpha = iconAlpha),
                                            tint = JetsnackTheme.colors.uiBackground,
                                            contentDescription = null,
                                        )
                                    }

                                    /*Text opacity increases as the text is supposed to appear in
                                    the screen*/
                                    val textAlpha by animateFloatAsState(
                                        if (offsetX > (-144).dp) 0.5f else 1f, label = ""
                                    )
                                    if (offsetX < (-120).dp) {
                                        Text(
                                            text = stringResource(id = R.string.remove_item),
                                            style = MaterialTheme.typography.subtitle1,
                                            color = JetsnackTheme.colors.uiBackground,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .graphicsLayer(
                                                    alpha = textAlpha
                                                )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            ) {
                CartItem(
                    orderLine = orderLine,
                    removeSnack = removeSnack,
                    increaseItemCount = increaseItemCount,
                    decreaseItemCount = decreaseItemCount,
                    onSnackClick = onSnackClick
                )
            }
        }

        item {
            SelectedShippingDestination(
                shippingDestination = shippingDestination,
                onShowShippingAddressScreen = onShowShippingAddressScreen,
                modifier = Modifier
            )
        }

        item {
            SummaryItem(
                subtotal = orderLines.sumOf { it.snack.price * it.count },
                shippingCosts = 369
            )
        }

        item {
            SnackCollection(
                snackCollection = inspiredByCart,
                onSnackClick = onSnackClick,
                highlight = false
            )
            Spacer(Modifier.height(56.dp))
        }
    }
}

@Composable
fun CartItem(
    orderLine: OrderLine,
    removeSnack: (Long) -> Unit,
    increaseItemCount: (Long) -> Unit,
    decreaseItemCount: (Long) -> Unit,
    onSnackClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val snack = orderLine.snack
    ConstraintLayout(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onSnackClick(snack.id) }
            .background(JetsnackTheme.colors.uiBackground)
            .padding(horizontal = 24.dp)
    ) {
        val (divider, image, name, tag, priceSpacer, price, remove, quantity) = createRefs()
        createVerticalChain(name, tag, priceSpacer, price, chainStyle = ChainStyle.Packed)
        SnackImage(
            imageUrl = snack.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top, margin = 16.dp)
                    bottom.linkTo(parent.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                }
        )

        Text(
            text = snack.name,
            style = MaterialTheme.typography.subtitle1,
            color = JetsnackTheme.colors.textSecondary,
            modifier = Modifier.constrainAs(name) {
                linkTo(
                    start = image.end,
                    startMargin = 16.dp,
                    end = remove.start,
                    endMargin = 16.dp,
                    bias = 0f
                )
            }
        )

        IconButton(
            onClick = { removeSnack(snack.id) },
            modifier = Modifier
                .constrainAs(remove) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(top = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                tint = JetsnackTheme.colors.iconSecondary,
                contentDescription = stringResource(R.string.label_remove)
            )
        }

        Text(
            text = snack.tagline,
            style = MaterialTheme.typography.body1,
            color = JetsnackTheme.colors.textHelp,
            modifier = Modifier.constrainAs(tag) {
                linkTo(
                    start = image.end,
                    startMargin = 16.dp,
                    end = parent.end,
                    endMargin = 16.dp,
                    bias = 0f
                )
            }
        )

        Spacer(
            Modifier
                .height(8.dp)
                .constrainAs(priceSpacer) {
                    linkTo(top = tag.bottom, bottom = price.top)
                }
        )

        Text(
            text = formatPrice(snack.price),
            style = MaterialTheme.typography.subtitle1,
            color = JetsnackTheme.colors.textPrimary,
            modifier = Modifier.constrainAs(price) {
                linkTo(
                    start = image.end,
                    end = quantity.start,
                    startMargin = 16.dp,
                    endMargin = 16.dp,
                    bias = 0f
                )
            }
        )

        QuantitySelector(
            count = orderLine.count,
            decreaseItemCount = { decreaseItemCount(snack.id) },
            increaseItemCount = { increaseItemCount(snack.id) },
            modifier = Modifier.constrainAs(quantity) {
                baseline.linkTo(price.baseline)
                end.linkTo(parent.end)
            }
        )

        JetsnackDivider(
            Modifier.constrainAs(divider) {
                linkTo(start = parent.start, end = parent.end)
                top.linkTo(parent.bottom)
            }
        )
    }
}

@Composable
fun SelectedShippingDestination(
    shippingDestination: ShippingDestination?,
    onShowShippingAddressScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.cart_destination_header),
            style = MaterialTheme.typography.h6,
            color = JetsnackTheme.colors.brand,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .heightIn(min = 56.dp)
                .wrapContentHeight()
        )

        if(shippingDestination != null) {
            ShippingDestinationItem(
                shippingDestination = shippingDestination,
                onShippingDestination = {}
            )
        }

        JetsnackButton(
            modifier = Modifier
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 4.dp,
                    bottom = 24.dp
                ),
            onClick = onShowShippingAddressScreen
        ) {
            Text(text = if(shippingDestination == null) "Select Shipping Address" else "Change Shipping Address")
        }

        JetsnackDivider()
    }
}

@Composable
fun SummaryItem(
    subtotal: Long,
    shippingCosts: Long,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        Text(
            text = stringResource(R.string.cart_summary_header),
            style = MaterialTheme.typography.h6,
            color = JetsnackTheme.colors.brand,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .heightIn(min = 56.dp)
                .wrapContentHeight()
        )
        Row(modifier = Modifier.padding(horizontal = 24.dp)) {
            Text(
                text = stringResource(R.string.cart_subtotal_label),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
                    .alignBy(LastBaseline)
            )
            Text(
                text = formatPrice(subtotal),
                style = MaterialTheme.typography.body1,
                modifier = Modifier.alignBy(LastBaseline)
            )
        }
        Row(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
            Text(
                text = stringResource(R.string.cart_shipping_label),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start)
                    .alignBy(LastBaseline)
            )
            Text(
                text = formatPrice(shippingCosts),
                style = MaterialTheme.typography.body1,
                modifier = Modifier.alignBy(LastBaseline)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        JetsnackDivider()
        Row(modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)) {
            Text(
                text = stringResource(R.string.cart_total_label),
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 16.dp)
                    .wrapContentWidth(Alignment.End)
                    .alignBy(LastBaseline)
            )
            Text(
                text = formatPrice(subtotal + shippingCosts),
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.alignBy(LastBaseline)
            )
        }
        JetsnackDivider()
    }
}

@Composable
private fun CheckoutBar(
    modifier: Modifier = Modifier
) {
    Column(
        modifier.background(
            JetsnackTheme.colors.uiBackground.copy(alpha = AlphaNearOpaque)
        )
    ) {

        JetsnackDivider()
        Row {
            Spacer(Modifier.weight(1f))
            JetsnackButton(
                onClick = { /* todo */ },
                shape = RectangleShape,
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.cart_checkout),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Left,
                    maxLines = 1
                )
            }
        }
    }
}