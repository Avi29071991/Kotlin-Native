/**
 * Expected method to get the device OS name
 * @return String value of the OS (i.e. Android/IOS)
 */
expect fun getPlatform(): String

/**
 * Expected method for getting date depending upon the device OS
 * @param time time in milliseconds
 * @return Array containing day , date and month for provided time in milliseconds
 */
expect fun getDate(time: Long): Array<String>

/**
 * Method just for demo
 */
fun sayHelloWorld() {
    println("Hello World ${getPlatform()} devs")
}

var dateFormat = "EEE dd MMM"

