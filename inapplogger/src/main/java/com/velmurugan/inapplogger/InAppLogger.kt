package com.velmurugan.inapplogger

import android.content.Context

class InAppLogger(context: Context) : LoggerManager(context) {


    companion object {

        var loggerInstance: InAppLogger? = null

        @Synchronized
        fun initialize(context: Context): InAppLogger {
            if (loggerInstance == null) {
                loggerInstance = InAppLogger(context)
            }
            return loggerInstance!!
        }
    }

    fun attachView(inAppLogView: InAppLogView) {
        register(inAppLogView)
    }


    override fun e(msg: String) {
        saveLog("<font color=#cc0029>${ LogType.ERROR.name} : $msg</font>",LogType.ERROR.name)
    }

    override fun w(msg: String) {
        saveLog("<font color=#FFC107>${LogType.WARNING.name} : $msg</font>",LogType.WARNING.name)
    }

    override fun i(msg: String) {
        saveLog("<font color=#2196F3>${LogType.INFO.name} : $msg</font>", LogType.INFO.name)
    }

    override fun api(msg: String) {
        saveLog("<font color=#C1C1C1>${LogType.API.name} : $msg</font>", LogType.API.name)
    }


}