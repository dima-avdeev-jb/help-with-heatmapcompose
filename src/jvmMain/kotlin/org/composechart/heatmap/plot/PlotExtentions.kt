package ru.madin.neurooptima2.plot

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size

fun Size.map2Local(mapper: LocalMapper, X: Float, Y: Float): Offset {
    return mapper.mapXYtoCanvasOffset(
        size = this, x = X, y = Y
    )
}




