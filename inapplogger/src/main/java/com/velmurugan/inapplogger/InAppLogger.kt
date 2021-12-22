package com.velmurugan.inapplogger

import android.content.Context

class InAppLogger(context: Context, inAppLogView: InAppLogView?= null) : LoggerManager(context) {

    init {
        inAppLogView?.let {
            register(it)
        }
        deleteOldLog()
    }

    override fun e(msg: String) {
        saveLog("<font color=#cc0029>$msg</font>")
    }

    override fun w(msg: String) {
        saveLog("<font color=#FFC107>$msg</font>")
    }

    override fun i(msg: String) {
        saveLog("<font color=#2196F3>$msg</font>")
    }



}