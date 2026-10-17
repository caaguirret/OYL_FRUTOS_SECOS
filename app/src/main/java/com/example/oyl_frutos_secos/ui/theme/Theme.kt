package com.example.oyl_frutos_secos.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = NuezClaro,
    onPrimary = NuezOscuro,
    secondary = HojaClara,
    tertiary = MielClara,
    background = FondoOscuro,
    surface = SuperficieOscura,
    onBackground = Crema,
    onSurface = Crema,
    error = Color(0xFFFFB4A8)
)

private val LightColorScheme = lightColorScheme(
    primary = Nuez,
    onPrimary = Color.White,
    primaryContainer = MielSuave,
    onPrimaryContainer = NuezOscuro,
    secondary = Hoja,
    onSecondary = Color.White,
    secondaryContainer = HojaSuave,
    tertiary = Miel,
    background = Crema,
    onBackground = TextoOscuro,
    surface = CremaClara,
    onSurface = TextoOscuro,
    error = Arcilla,
    errorContainer = ArcillaSuave
)

@Composable
fun OYL_FRUTOS_SECOSTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // Se usa la paleta propia de la marca (sin colores dinámicos del sistema)
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
