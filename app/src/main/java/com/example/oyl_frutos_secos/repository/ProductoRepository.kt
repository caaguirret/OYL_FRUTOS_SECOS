package com.example.oyl_frutos_secos.repository

import com.example.oyl_frutos_secos.model.CategoriaProducto
import com.example.oyl_frutos_secos.model.Producto

/**
 * Origen de datos de productos.
 *
 * Por ahora devuelve un catálogo sintético en memoria (el caso prohíbe usar
 * datos reales). Cuando se integre Room o la API REST, solo cambia esta clase
 * y los ViewModels no se enteran.
 */
class ProductoRepository {

    fun obtenerProductos(): List<Producto> = listOf(
        Producto(1, "Almendras", CategoriaProducto.FRUTOS_SECOS, precio = 12990, stock = 25),
        Producto(2, "Nueces mariposa", CategoriaProducto.FRUTOS_SECOS, precio = 11990, stock = 18),
        Producto(3, "Maní sin sal", CategoriaProducto.FRUTOS_SECOS, precio = 4990, stock = 40),
        Producto(4, "Pistachos", CategoriaProducto.FRUTOS_SECOS, precio = 16990, stock = 3),
        Producto(5, "Semillas de chía", CategoriaProducto.SEMILLAS, precio = 3990, stock = 30),
        Producto(6, "Semillas de zapallo", CategoriaProducto.SEMILLAS, precio = 6990, stock = 22),
        Producto(7, "Linaza", CategoriaProducto.SEMILLAS, precio = 2990, stock = 35),
        Producto(8, "Arándanos deshidratados", CategoriaProducto.DESHIDRATADOS, precio = 9990, stock = 15),
        Producto(9, "Mango deshidratado", CategoriaProducto.DESHIDRATADOS, precio = 8490, stock = 0),
        Producto(10, "Pasas rubias", CategoriaProducto.DESHIDRATADOS, precio = 4490, stock = 28)
    )
}
