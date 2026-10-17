package com.example.oyl_frutos_secos.model

/**
 * Categorías comerciales del catálogo (según el caso OYL Frutos Secos).
 */
enum class CategoriaProducto(val etiqueta: String) {
    FRUTOS_SECOS("Frutos secos"),
    SEMILLAS("Semillas"),
    DESHIDRATADOS("Deshidratados")
}

/**
 * Entidad Producto del caso: ID, nombre, categoría, precio unitario,
 * stock actual y ruta/URI de la imagen referencial.
 *
 * Más adelante se convertirá en entidad de Room (@Entity).
 */
data class Producto(
    val id: Int,
    val nombre: String,
    val categoria: CategoriaProducto,
    val precio: Int,          // CLP por unidad de venta
    val stock: Int,
    val imagenUri: String? = null
)
