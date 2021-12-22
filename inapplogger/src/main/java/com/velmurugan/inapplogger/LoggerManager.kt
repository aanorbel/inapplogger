package com.velmurugan.inapplogger

import android.content.Context
import android.util.Log
import java.io.*


abstract class LoggerManager(private val context: Context) : ILogger, Subject {

    //https://www.codemetrix.in/2019/09/write-logs-into-log-file-in-android.html
    private var observers = mutableListOf<MessageObserver>()
    private val logFileDir: File by lazy {
        File("${context.filesDir}/Log")
    }

    private val logFile: File by lazy {
        File("$logFileDir/logger.txt")
    }

    init {

    }

    protected fun saveLog(msg: String) {
        try {
            if (!logFileDir.exists()) {
                logFileDir.mkdir()
            }

            if (!logFile.exists()) {
                logFile.createNewFile()
            }

            val buf = BufferedWriter(FileWriter(logFile, true))
            buf.append("<p>")
            buf.append(msg)
            buf.append("</p>")
            buf.close()

        } catch (ex: Exception) {
            Log.e("Logger", ex.toString())

        }
        notifyMessage(msg)
    }

    override fun register(observer: MessageObserver) {
        observers.add(observer)
    }

    override fun unRegister(observer: MessageObserver) {
        observers.remove(observer)
    }

    override fun notifyMessage(msg: String) {
        observers.forEach {
            it.update()
        }
    }


    fun getLog(): StringBuilder {

        //Read text from file
        val text = StringBuilder()

        try {
            val br = BufferedReader(FileReader(logFile))
            var line: String?
            while (br.readLine().also { line = it } != null) {
                text.append(line)
                text.append('\n')
            }
            br.close()
        } catch (e: IOException) {
            Log.e("Logger", e.toString())
        }
        return text
    }

    protected fun deleteOldLog() {
        if (logFile.exists()) {
            logFile.delete()
        }
    }

}