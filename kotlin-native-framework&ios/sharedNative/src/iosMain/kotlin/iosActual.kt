import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import platform.Foundation.*
import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue
import kotlin.coroutines.CoroutineContext

/**
 * IOS implementation of method to get the device OS name
 * @return String value of the i.e. IOS
 */
actual fun getPlatform(): String {
    return "IOS"
}

/**
 * Dispatcher class for providing IOS uiContext
 */
class MainDispatcher: CoroutineDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        dispatch_async(dispatch_get_main_queue()) { block.run() }
    }
}

/**
 * IOS method implementation for getting date
 * @param time time in milliseconds
 * @return Array containing day , date and month for provided time in milliseconds
 */
actual fun getDate(time: Long): Array<String> {
    val date = NSDate(timeIntervalSinceReferenceDate= time.toDouble())
    val dateFormatter = NSDateFormatter()
    dateFormatter.dateFormat = dateFormat
    val dateText = dateFormatter.stringFromDate(date)
    if (!dateText.isNullOrEmpty()) {
        return dateText.split("\\s".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
    }
    return emptyArray()
}