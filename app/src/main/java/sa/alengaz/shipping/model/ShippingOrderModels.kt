package sa.alengaz.shipping.model

import kotlinx.serialization.Serializable

@Serializable
data class ShippingItem(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class ShippingOrder(
    val requestId: String,
    val items: List<ShippingItem>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: ShippingOrderStatus = ShippingOrderStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class ShippingOrderStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
