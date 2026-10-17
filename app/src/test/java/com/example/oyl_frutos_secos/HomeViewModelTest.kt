package com.example.oyl_frutos_secos

import com.example.oyl_frutos_secos.model.CategoriaProducto
import com.example.oyl_frutos_secos.viewmodel.HomeViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class HomeViewModelTest {

    @Test
    fun sinFiltro_muestraTodosLosProductos() {
        val viewModel = HomeViewModel()
        val estado = viewModel.uiState.value

        assertEquals(estado.productos.size, estado.productosFiltrados.size)
    }

    @Test
    fun alSeleccionarCategoria_soloMuestraEsaCategoria() {
        val viewModel = HomeViewModel()

        viewModel.seleccionarCategoria(CategoriaProducto.SEMILLAS)

        val filtrados = viewModel.uiState.value.productosFiltrados
        assertTrue(filtrados.isNotEmpty())
        assertTrue(filtrados.all { it.categoria == CategoriaProducto.SEMILLAS })
    }
}
