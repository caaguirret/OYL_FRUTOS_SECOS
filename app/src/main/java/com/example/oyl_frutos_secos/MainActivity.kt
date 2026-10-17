package com.example.oyl_frutos_secos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.oyl_frutos_secos.ui.HomeScreen
import com.example.oyl_frutos_secos.ui.theme.OYL_FRUTOS_SECOSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OYL_FRUTOS_SECOSTheme {
                HomeScreen()
            }
        }
    }
}
