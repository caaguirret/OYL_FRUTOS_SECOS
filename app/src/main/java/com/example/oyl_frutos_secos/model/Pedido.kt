package com.example.oyl_frutos_secos.model

/**
 * Estados posibles de una solicitud de compra (según el caso).
 */
enum class EstadoPedido(val etiqueta: String) {
    PENDIENTE_VALIDACION("Pendiente de validación"),
    CONFIRMADO("Confirmado"),
    PREPARADO("Preparado"),
    ENTREGADO("Entregado")
}

/**
 * Línea de detalle de un pedido.
 */
data class ItemPedido(
    val productoId: Int,
    val cantidad: Int,
    val precioUnitario: Int
) {
    val subtotal: Int get() = cantidad * precioUnitario
}

/**
 * Entidad Pedido del caso: identificador, fecha y hora (timestamp),
 * detalle de artículos, monto total y estado.
 */
data class Pedido(
    val id: Int,
    val fechaHora: Long,
    val items: List<ItemPedido>,
    val estado: EstadoPedido = EstadoPedido.PENDIENTE_VALIDACION
) {
    val total: Int get() = items.sumOf { it.subtotal }
}
