import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.madin.neurooptima2.dataset.CSVReader
import ru.madin.neurooptima2.dataset.SpectrogramLine
import ru.madin.neurooptima2.plot.SpectrogramPlot
import java.io.File
import java.util.*


@Composable
@Preview
fun App(dataState : MutableStateFlow<List<SpectrogramLine>>) {

    val data by dataState.collectAsState()
    MaterialTheme {
        SpectrogramPlot(
            xrange = IntRange(start = 0, endInclusive = data.size),
            yrange = IntRange(0,15),
            data = data
        )
    }
}

fun main() = application {
    val data = CSVReader.readCsvToSpectrogram(File("src/jvmMain/resources/data.csv").inputStream())
    val dataState = MutableStateFlow<List<SpectrogramLine>>(data)
    Window(onCloseRequest = ::exitApplication) {
        App(dataState)
    }
}
