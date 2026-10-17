package com.example.oyl_frutos_secos.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.oyl_frutos_secos.model.CategoriaProducto
import com.example.oyl_frutos_secos.model.Producto
import com.example.oyl_frutos_secos.repository.ProductoRepository
import com.example.oyl_frutos_secos.ui.theme.Arcilla
import com.example.oyl_frutos_secos.ui.theme.ArcillaSuave
import com.example.oyl_frutos_secos.ui.theme.Hoja
import com.example.oyl_frutos_secos.ui.theme.HojaSuave
import com.example.oyl_frutos_secos.ui.theme.Miel
import com.example.oyl_frutos_secos.ui.theme.MielSuave
import com.example.oyl_frutos_secos.ui.theme.NuezOscuro
import com.example.oyl_frutos_secos.ui.theme.OYL_FRUTOS_SECOSTheme
import com.example.oyl_frutos_secos.viewmodel.HomeUiState
import com.example.oyl_frutos_secos.viewmodel.HomeViewModel
import java.text.NumberFormat
import java.util.Locale

/**
 * Pantalla con estado: obtiene el ViewModel y se lo pasa a [HomeContent].
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    HomeContent(
        uiState = uiState,
        onCategoriaSeleccionada = viewModel::seleccionarCategoria,
        modifier = modifier
    )
}

/**
 * Contenido sin estado propio: solo dibuja lo que recibe.
 * Así se puede previsualizar y probar fácilmente.
 */
@Composable
fun HomeContent(
    uiState: HomeUiState,
    onCategoriaSeleccionada: (CategoriaProducto?) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        // El encabezado dibuja detrás de la barra de estado (edge-to-edge)
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            EncabezadoMarca(cantidadProductos = uiState.productosFiltrados.size)

            // Filtros por categoría (se desplazan si no caben)
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    ChipCategoria(
                        texto = "Todos",
                        seleccionado = uiState.categoriaSeleccionada == null,
                        onClick = { onCategoriaSeleccionada(null) }
                    )
                }
                items(CategoriaProducto.entries) { categoria ->
                    ChipCategoria(
                        texto = categoria.emoji + " " + categoria.etiqueta,
                        seleccionado = uiState.categoriaSeleccionada == categoria,
                        onClick = { onCategoriaSeleccionada(categoria) }
                    )
                }
            }

            // Lista de productos (con animación al filtrar)
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
            ) {
                items(uiState.productosFiltrados, key = { it.id }) { producto ->
                    ProductoCard(producto, modifier = Modifier.animateItem())
                }
            }

            // Acción principal (por ahora sin navegación)
            Button(
                onClick = { /* TODO: navegar al carrito */ },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .heightIn(min = 52.dp)
            ) {
                Text("🛒  Ver carrito", style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}

/**
 * Encabezado con degradado y animación de entrada.
 */
@Composable
private fun EncabezadoMarca(cantidadProductos: Int) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideInVertically { -it / 2 }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(MaterialTheme.colorScheme.primary, NuezOscuro)
                    ),
                    shape = RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)
                )
                .statusBarsPadding()
                .padding(horizontal = 20.dp, vertical = 20.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "🥜 OYL Frutos Secos",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Naturales, frescos y a buen precio",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.85f)
                )
                Text(
                    text = "$cantidadProductos productos disponibles",
                    style = MaterialTheme.typography.labelLarge,
                    color = Miel
                )
            }
        }
    }
}

@Composable
private fun ChipCategoria(texto: String, seleccionado: Boolean, onClick: () -> Unit) {
    FilterChip(
        selected = seleccionado,
        onClick = onClick,
        label = { Text(texto) },
        shape = RoundedCornerShape(50),
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
private fun ProductoCard(producto: Producto, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // "Foto" provisoria: emoji sobre fondo de color según categoría
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(producto.categoria.colorSuave),
                contentAlignment = Alignment.Center
            ) {
                Text(producto.categoria.emoji, style = MaterialTheme.typography.headlineSmall)
            }

            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    producto.nombre,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    producto.categoria.etiqueta,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }

            Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(
                    formatoPesos(producto.precio),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                InsigniaStock(producto.stock)
            }
        }
    }
}

/**
 * Etiqueta de stock: verde (hay), miel (quedan pocos) o roja (agotado).
 */
@Composable
private fun InsigniaStock(stock: Int) {
    val (texto, fondo, color) = when {
        stock <= 0 -> Triple("Agotado", ArcillaSuave, Arcilla)
        stock <= 5 -> Triple("¡Quedan $stock!", MielSuave, Color(0xFF8A5A00))
        else -> Triple("Stock: $stock", HojaSuave, Hoja)
    }
    Surface(shape = RoundedCornerShape(50), color = fondo) {
        Text(
            text = texto,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = color,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
        )
    }
}

private val CategoriaProducto.emoji: String
    get() = when (this) {
        CategoriaProducto.FRUTOS_SECOS -> "🥜"
        CategoriaProducto.SEMILLAS -> "🌻"
        CategoriaProducto.DESHIDRATADOS -> "🍇"
    }

private val CategoriaProducto.colorSuave: Color
    get() = when (this) {
        CategoriaProducto.FRUTOS_SECOS -> MielSuave
        CategoriaProducto.SEMILLAS -> HojaSuave
        CategoriaProducto.DESHIDRATADOS -> Color(0xFFEBDDF3)
    }

private fun formatoPesos(monto: Int): String =
    NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-CL")).format(monto)

@Preview(showBackground = true)
@Composable
private fun HomeContentPreview() {
    OYL_FRUTOS_SECOSTheme {
        HomeContent(
            uiState = HomeUiState(productos = ProductoRepository().obtenerProductos()),
            onCategoriaSeleccionada = {}
        )
    }
}
