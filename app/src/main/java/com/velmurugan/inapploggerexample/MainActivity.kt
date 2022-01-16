package com.velmurugan.inapploggerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.velmurugan.inapplogger.InAppLogView
import com.velmurugan.inapplogger.InAppLogger

class MainActivity : AppCompatActivity() {

    lateinit var logger: InAppLogger
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val v = findViewById<InAppLogView>(R.id.tv)
        logger = InAppLogger(this, v)

        findViewById<Button>(R.id.buttonLogError).setOnClickListener {
            logger.e("error Message added")

        }
        findViewById<Button>(R.id.buttonLogWarning).setOnClickListener {
            logger.w("warring Message added")
        }

        findViewById<Button>(R.id.buttonLogInfo).setOnClickListener {
            logger.i("Info Message added")
        }
    }
}