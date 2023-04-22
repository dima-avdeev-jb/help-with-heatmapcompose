package ru.madin.neurooptima2.plot

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import kotlin.math.floor
import kotlin.math.round

class LocalMapper(
    val max_x: Float,
    val min_x: Float,
    val max_y: Float,
    val min_y: Float,
    val paddingXPercent : Float = 5f,
    val paddingYPercent : Float = 5f,
    val xStep : Float = 1f,
    val yStep : Float = 1f
) {
    val x_range = kotlin.math.abs(max_x - min_x)
    val y_range = kotlin.math.abs(max_y - min_y)

    fun mapXYwithinRanges(x: Float, y: Float): Pair<Float, Float> {
        val x_mapped = (x - min_x) / x_range
        val y_mapped = (y - min_y) / y_range
        return Pair(x_mapped, y_mapped)
    }

    fun mapXYtoCanvasOffset(size: Size, x: Float, y: Float): Offset {
        val mapped = mapXYwithinRanges(x, y)
        val paddingVertical = size.width * paddingYPercent / 100
        val paddingHorizontal = size.height * paddingXPercent / 100

        val cWidth = size.width - paddingHorizontal * 2
        val cHeight = size.height - paddingVertical * 2
        return Offset(
            round( mapped.first * cWidth + paddingHorizontal) ,
            round(cHeight - mapped.second * cHeight + paddingVertical)
        )
    }

    fun recSize(size: Size): Size {
        val paddingVertical = size.width * paddingYPercent / 100
        val paddingHorizontal = size.height * paddingXPercent / 100
        val cWidth = size.width - paddingHorizontal * 2
        val cHeight = size.height - paddingVertical * 2

        return Size(
            cWidth * (xStep / x_range),
            cHeight * (yStep / y_range)
        )
    }
}
