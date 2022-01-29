package com.velmurugan.inapploggerexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.velmurugan.inapplogger.InAppLogger

class SecondActivity : AppCompatActivity() {

    lateinit var logger: InAppLogger

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        logger = InAppLogger.loggerInstance!!
        logger.attachView(findViewById(R.id.tv2))

        findViewById<AppCompatButton>(R.id.btnLogError).setOnClickListener {

            logger.e("Error from second activity")
        }


    }
}