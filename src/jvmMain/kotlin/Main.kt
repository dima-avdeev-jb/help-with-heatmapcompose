import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    Canvas(modifier = Modifier.fillMaxSize().background(Color.White)) {
        val chartWidth = size.width
        val chartHeight = size.height
        var x = 0f
//        val rectSize: Float = 50f // works fine
        val rectSize: Float = 50.5f // Show grid
        while (x < chartWidth) {
            var y = 0f
            while (y < chartHeight) {
                drawRect(Color.Black, Offset(x, y), Size(rectSize, rectSize))
                y += rectSize
            }
            x += rectSize
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
