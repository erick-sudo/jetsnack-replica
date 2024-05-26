package com.compose.samples.replicas.jetsnack.ui.home.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.compose.samples.replicas.jetsnack.R
import com.compose.samples.replicas.jetsnack.model.OrderLine
import com.compose.samples.replicas.jetsnack.model.ShippingDestination
import com.compose.samples.replicas.jetsnack.model.ShippingRepo
import com.compose.samples.replicas.jetsnack.model.SnackRepo
import com.compose.samples.replicas.jetsnack.model.SnackbarManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CartViewModel(
    private val snackManager: SnackbarManager,
    snackRepository: SnackRepo,
    shippingRepository: ShippingRepo
) : ViewModel() {
    private val _orderLines: MutableStateFlow<List<OrderLine>> = MutableStateFlow(snackRepository.getCart())
    private val _shippingDestination: MutableStateFlow<ShippingDestination?> = MutableStateFlow(null) // shippingRepository.defaultShippingDestination()
    val orderLines
        get() = _orderLines.asStateFlow()

    val shippingDestination
        get() = _shippingDestination.asStateFlow()

    private var requestCount = 0
    private fun shouldRandomlyFail(): Boolean = ++requestCount % 5 == 0

    fun increaseSnackCount(snackId: Long) {
        if(!shouldRandomlyFail()) {
            val currentCount = orderLines.value.first { it.snack.id == snackId }.count
            updateSnackCount(snackId, currentCount + 1)
        } else {
            snackManager.showMessage(R.string.cart_increase_error)
        }
    }

    fun decreaseSnackCount(snackId: Long) {
        if(!shouldRandomlyFail()) {
            val currentCount = _orderLines.value.first { it.snack.id == snackId }.count
            if(currentCount == 1) {
                removeSnack(snackId)
            } else {
                updateSnackCount(snackId, currentCount - 1)
            }
        } else {
            snackManager.showMessage(R.string.cart_decrease_error)
        }
    }

    fun removeSnack(snackId: Long) {
        _orderLines.value = orderLines.value.filter { it.snack.id != snackId }
    }

    fun addToCart(snackId: Long, qty: Int) {
        _orderLines.value.find { it.snack.id == snackId }?.let {
            updateSnackCount(snackId, it.count + 1)
            snackManager.showMessage(R.string.cart_item_qty_incremented)
            return
        }
        val snack = SnackRepo.getSnack(snackId)
        _orderLines.value = orderLines.value.toMutableList().apply { add(OrderLine(snack, qty)) }
        snackManager.showMessage(R.string.added_to_cart)
    }

    private fun updateSnackCount(snackId: Long, count: Int) {
        _orderLines.value = orderLines.value.map {
            if(it.snack.id == snackId) {
                it.copy(count = count)
            } else {
                it
            }
        }
    }

    suspend fun computeShippingFee(): Float {
        delay(1500)
        return shippingDestination.value?.let { ShippingRepo.computeShippingFee(_orderLines.value, it) } ?: 0f
    }

    suspend fun onShippingAddress(shippingDestination: ShippingDestination) {
        _shippingDestination.value = shippingDestination
    }

    companion object {
        fun provideFactory(
            snackbarManager: SnackbarManager = SnackbarManager,
            snackRepository: SnackRepo = SnackRepo,
            shippingRepository: ShippingRepo = ShippingRepo
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CartViewModel(
                    snackManager = snackbarManager,
                    snackRepository = snackRepository,
                    shippingRepository = shippingRepository
                ) as T
            }
        }
    }
}