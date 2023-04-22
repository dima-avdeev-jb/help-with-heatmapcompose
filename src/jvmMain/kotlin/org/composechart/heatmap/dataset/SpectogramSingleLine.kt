package ru.madin.neurooptima2.dataset

import java.awt.Color

class SDot(
    var x: Float,
    val y: Float,
    val z: Float,


) {
    override fun toString(): String {
        return "[$x, $y, $z]"
    }

}

class SpectrogramLine(
    val dots: List<SDot>


) {
    override fun toString(): String {
        return dots.joinToString(separator = " ") { it.toString() }
    }
}