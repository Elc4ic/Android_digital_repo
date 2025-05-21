package com.example.digital_kaf.data

object TimeParser {

    fun getDuration(duration: Long): String {
        var durationR = duration
        val metrics = listOf(2629746000L, 86400000L, 3600000L, 60000L, 1000L)
        val times = listOf("месяц", "день", "часа", "минут", "секунд")
        var now = 0
        val max = 2
        val sb = StringBuilder()
        for (i in metrics.indices) {
            val n = durationR / metrics[i]
            durationR -= n * metrics[i]
            if (n != 0L && now < max) {
                sb.append("$n ${times[i]} ")
                now++
            }
        }
        return sb.toString().trim()
    }

    fun timeBack(endTime: Long): String {
        val back = System.currentTimeMillis() - endTime
        val metrics = listOf(31536000000L, 2629746000L, 86400000L, 3600000L, 60000L, 1000L)
        for (i in metrics.indices) {
            val n = back / metrics[i]
            if (n != 0L) {
                return when (i) {
                    0 -> getDate(endTime)
                    1 -> getDate(back)
                    else -> getBack(n, i)
                }
            }
        }
        return ""
    }

    fun getBack(n: Long, i: Int): String {
        val times = listOf("год", "месяц", "день", "часа", "минут", "секунд")
        return "$n ${times[i]} назад"
    }

    fun getDate(time: Long): String {
        var timeR = time
        val metrics = listOf(31536000000L, 2629746000L, 86400000L)
        var day = 0L
        var month = 0L
        var year = 0L
        year += timeR / metrics[0]
        timeR -= year * metrics[0]
        month += timeR / metrics[1]
        timeR -= month * metrics[1]
        day += timeR / metrics[2]
        timeR -= day * metrics[2]
        return "$day.$month.${year + 1970}"
    }

    fun parseTimer(time: Long): String {
        var timeR = time
        val metrics = listOf(2629746000L, 86400000L, 3600000L, 60000L, 1000L)
        val sb = StringBuilder()
        for (i in metrics.indices) {
            val n = timeR / metrics[i]
            timeR -= n * metrics[i]
            if (n != 0L) {
                sb.append("$n:")
            }
        }
        return sb.toString()
    }
}