package com.velmurugan.inapplogger

import android.content.Context
import android.text.format.DateFormat
import java.util.*

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
        saveLog("<font color=#cc0029>${DateFormat.format("yyyy-MM-dd hh:mm:ss a", Date())} : ${ LogType.ERROR.name} : $msg</font>",LogType.ERROR.name)
    }

    override fun w(msg: String) {
        saveLog("<font color=#FFC107>${DateFormat.format("yyyy-MM-dd hh:mm:ss a", Date())} : ${LogType.WARNING.name} : $msg</font>",LogType.WARNING.name)
    }

    override fun i(msg: String) {
        saveLog("<font color=#2196F3>${DateFormat.format("yyyy-MM-dd hh:mm:ss a", Date())} : ${LogType.INFO.name} : $msg</font>", LogType.INFO.name)
    }

    override fun api(msg: String) {
        saveLog("<font color=#C1C1C1>${DateFormat.format("yyyy-MM-dd hh:mm:ss a", Date())} : ${LogType.API.name} : $msg</font>", LogType.API.name)
    }


}