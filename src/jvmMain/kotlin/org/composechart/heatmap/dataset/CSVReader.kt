package ru.madin.neurooptima2.dataset

import java.io.InputStream

object CSVReader {
    fun readCsvToSpectrogram(inputStream: InputStream): List<SpectrogramLine> {
        val reader = inputStream.bufferedReader()
        val header = reader.readLine()
        return reader.lineSequence()
            .filter { it.isNotBlank() }
            .mapIndexed { i, line ->
                val phases = line.split(',', ignoreCase = false, limit = 16)
                val dots = phases.mapIndexed { index, value ->
                    SDot(
                        x = i.toFloat(),
                        y = index.toFloat(),
                        z = value.toFloat(),
                    )
                }
                SpectrogramLine(dots)
            }.toList()
    }
}