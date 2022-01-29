package com.velmurugan.inapploggerexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import com.velmurugan.inapplogger.InAppLogView
import com.velmurugan.inapplogger.InAppLogger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class MainActivity : AppCompatActivity() {

    lateinit var logger: InAppLogger
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val v = findViewById<InAppLogView>(R.id.tv)
        logger = InAppLogger.loggerInstance!!
        logger.attachView(v)

        findViewById<AppCompatButton>(R.id.gotoSecondActivity).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        findViewById<Button>(R.id.buttonLogError).setOnClickListener {
            logger.e("error Message added")

        }
        findViewById<Button>(R.id.buttonLogApi).setOnClickListener {
           // logger.e("error Message added")

            CoroutineScope(Dispatchers.IO).launch {

               /* val requestBody =
                    "Hello".toRequestBody("application/json; charset=utf-8".toMediaType())*/

               val response = ApiService.getInstance(this@MainActivity).getAllMovieQuery("hello path","location")
                if (response.isSuccessful) {
                    withContext(Dispatchers.Main) {
                    }
                } else {
                    logger.e("failed")
                }
            }
        }
        findViewById<Button>(R.id.buttonLogWarning).setOnClickListener {
            logger.w("warring Message added")
        }

        findViewById<Button>(R.id.buttonLogInfo).setOnClickListener {
            logger.i("Info Message added")
        }
    }
}