package com.compose.samples.replicas.jetsnack.ui.home.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.compose.samples.replicas.jetsnack.R
import com.compose.samples.replicas.jetsnack.model.OrderLine
import com.compose.samples.replicas.jetsnack.model.SnackRepo
import com.compose.samples.replicas.jetsnack.model.SnackbarManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CartViewModel(
    private val snackManager: SnackbarManager,
    snackRepository: SnackRepo
) : ViewModel() {
    private val _orderLines: MutableStateFlow<List<OrderLine>> = MutableStateFlow(snackRepository.getCart())
    val orderLines
        get() = _orderLines.asStateFlow()

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

    private fun updateSnackCount(snackId: Long, count: Int) {
        _orderLines.value = orderLines.value.map {
            if(it.snack.id == snackId) {
                it.copy(count = count)
            } else {
                it
            }
        }
    }

    companion object {
        fun provideFactory(
            snackbarManager: SnackbarManager = SnackbarManager,
            snackRepository: SnackRepo = SnackRepo
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CartViewModel(
                    snackManager = snackbarManager,
                    snackRepository = snackRepository
                ) as T
            }
        }
    }
}