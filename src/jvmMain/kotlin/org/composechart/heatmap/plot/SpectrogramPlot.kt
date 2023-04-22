package ru.madin.neurooptima2.plot

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import org.jetbrains.skia.FilterBlurMode
import ru.madin.neurooptima2.dataset.SpectrogramLine
import ru.madin.neurooptima2.theme.Colors
import java.awt.Color


const val chartPaddingPercent = 5f


@Composable
fun SpectrogramPlot(
    modifier: Modifier = Modifier,
    xrange: IntRange,
    yrange: IntRange,
    data: List<SpectrogramLine> = listOf()
) {
    val localMapper by remember {
        mutableStateOf(
            LocalMapper(
                max_x = xrange.last.toFloat(),
                min_x = xrange.first.toFloat(),
                max_y = yrange.last.toFloat(),
                min_y = yrange.first.toFloat(),
                paddingXPercent = chartPaddingPercent,
                paddingYPercent = chartPaddingPercent
            )
        )
    }
    PlotChartRectangleAxesOnCanvas(
        modifier = modifier,
        mapper = localMapper,
        stepGridX = 100f,
        stepGridY = 1f,
        series = data
    )
}


@Composable
fun PlotChartRectangleAxesOnCanvas(
    modifier: Modifier = Modifier,
    mapper: LocalMapper,
    stepGridX: Float,
    stepGridY: Float,
    series: List<SpectrogramLine> = listOf()
) {

    Canvas(modifier = modifier.fillMaxSize().background(Colors.chartBackGround)) {
        val chartWidth = size.width
        val chartHeight = size.height
        val paddingVertical = chartWidth * chartPaddingPercent / 100
        val paddingHorizontal = chartHeight * chartPaddingPercent / 100
        val chartWidthWithoutPadding = chartWidth - paddingHorizontal * 2
        val chartHeightWithoutPadding = chartHeight - paddingVertical * 2
        drawRect(
            color = Colors.chartLine,
            topLeft = size.map2Local(mapper, mapper.min_x, mapper.max_y),
            size = Size(chartWidthWithoutPadding, chartHeightWithoutPadding),
            style = androidx.compose.ui.graphics.drawscope.Stroke(width = 2f),
        )
        series.forEach { line ->
            line.dots.forEach { value ->
                 drawRect(
                      color = Colors.chartLine,
                      alpha = value.z,
                      topLeft = size.map2Local(mapper, value.x, value.y + 1),
                      size = mapper.recSize(size),
                      style = Fill,
                  )
            }
        }
    }

}
