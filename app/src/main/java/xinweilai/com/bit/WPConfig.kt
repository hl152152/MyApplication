package xinweilai.com.bit


object WPConfig {
    /**
     *
     */

    private var PRODUCE: String? = "http://www.7ebit.info"//正式库地址
    private var TEST: String? = null

    //测试库服务器地址
    init {

        TEST = ""
    }

    const val PicBaseUrl = "" //单独数据
    /**
     *  isDebug = true的时候日志显示
     *   isDebug = false的时候日志不显示
     */
    const val isDebug = true
    val BaseUrl: String? = PRODUCE //正式
//    val BaseUrl: String? = TEST //测试

}