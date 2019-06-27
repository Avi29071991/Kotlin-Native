import java.text.SimpleDateFormat
import java.util.*

/**
 * Android implementation of method to get the device OS name
 * @return String value of the i.e. Android
 */
actual fun getPlatform(): String {
    return "Android"
}

/**
 * Android method implementation for getting date
 * @param time time in milliseconds
 * @return Array containing day , date and month for provided time in milliseconds
 */
actual fun getDate(time: Long): Array<String> {
    val date = Date(time * 1000)
    val df2 = SimpleDateFormat(dateFormat, Locale.US)
    val dateText = df2.format(date)
    if (!dateText.isNullOrEmpty()) {
        return dateText.split("\\s".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    }
    return emptyArray()
}

