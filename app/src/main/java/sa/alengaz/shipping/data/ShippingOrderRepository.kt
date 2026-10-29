package sa.alengaz.shipping.data

import sa.alengaz.shipping.model.ShippingOrder
import sa.alengaz.shipping.model.ShippingItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface ShippingOrderRepository {
    fun getAvailableCatalog(): Flow<List<ShippingItem>>
    fun getActiveRequest(): Flow<ShippingOrder?>
    suspend fun submitRequest(request: ShippingOrder): Result<String>
}

class InMemoryShippingOrderRepository : ShippingOrderRepository {
    private val _catalog = MutableStateFlow<List<ShippingItem>>(listOf(
        ShippingItem("1", "خدمة الشحن القياسية", "توصيل قطع الغيار في غضون 3-5 أيام عمل، مع تتبع الطلبات", 5000L, "verified"),
        ShippingItem("2", "خدمة الشحن السريع", "توصيل قطع الغيار في غضون يومين عمل، لضمان وصولها بسرعة", 9500L, "bolt")
    ))
    private val _active = MutableStateFlow<ShippingOrder?>(null)

    override fun getAvailableCatalog(): Flow<List<ShippingItem>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<ShippingOrder?> = _active.asStateFlow()

    override suspend fun submitRequest(request: ShippingOrder): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
