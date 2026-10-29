package sa.alengaz.shipping.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.alengaz.shipping.data.ShippingOrderRepository
import sa.alengaz.shipping.model.ShippingOrder
import sa.alengaz.shipping.model.ShippingItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ShippingOrderViewModel(
    private val repository: ShippingOrderRepository
) : ViewModel() {
    val catalog: StateFlow<List<ShippingItem>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<ShippingOrder?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: ShippingOrder) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
