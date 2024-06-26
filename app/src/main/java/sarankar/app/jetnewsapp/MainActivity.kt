package sarankar.app.jetnewsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import sarankar.app.jetnewsapp.ui.JetNewsApp
import sarankar.app.jetnewsapp.ui.theme.JetNewsAppTheme
import sarankar.app.jetnewsapp.ui.theme.Montserrat

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val appContainer = (application as JetNewsApplication).container
        setContent {
            val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass
            JetNewsApp(
                appContainer = appContainer,
                widthSizeClass = widthSizeClass,
            )
        }
    }
}
