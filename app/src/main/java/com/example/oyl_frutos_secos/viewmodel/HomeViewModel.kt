package com.example.oyl_frutos_secos.viewmodel

import androidx.lifecycle.ViewModel
import com.example.oyl_frutos_secos.model.CategoriaProducto
import com.example.oyl_frutos_secos.model.Producto
import com.example.oyl_frutos_secos.repository.ProductoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
 * Estado que la pantalla Home necesita para dibujarse.
 * La UI solo lee este objeto; no calcula nada por su cuenta.
 */
data class HomeUiState(
    val productos: List<Producto> = emptyList(),
    val categoriaSeleccionada: CategoriaProducto? = null // null = todas
) {
    val productosFiltrados: List<Producto>
        get() = if (categoriaSeleccionada == null) {
            productos
        } else {
            productos.filter { it.categoria == categoriaSeleccionada }
        }
}

/**
 * Lógica de presentación de la pantalla Home (MVVM).
 */
class HomeViewModel(
    repository: ProductoRepository = ProductoRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        HomeUiState(productos = repository.obtenerProductos())
    )
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    fun seleccionarCategoria(categoria: CategoriaProducto?) {
        _uiState.update { it.copy(categoriaSeleccionada = categoria) }
    }
}
