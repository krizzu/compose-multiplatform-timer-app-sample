package models


data class TimeUnit(val min: Int, val sec: Int = 0) {
    init {
        if (min < 0 || min > 60) {
            error("min value needs to be >= 0 or <= 60")
        }
        if (sec < 0 || sec > 60) {
            error("sec value needs to be >= 0 or <= 60")
        }
    }

    override fun toString(): String {
        val mins = if (min < 10) "0$min" else "$min"
        val secs = if (sec < 10) "0$sec" else "$sec"
        return "$mins:$secs min"
    }

    fun decreaseSecond(): TimeUnit {
        val nextSec = sec - 1
        if(nextSec < 0) {
            val nextMin = min - 1
            if(nextMin < 0) {
                return TimeUnit(0, 0)
            }
            return TimeUnit(min - 1, 59)
        }
        return TimeUnit(min, nextSec)
    }
}