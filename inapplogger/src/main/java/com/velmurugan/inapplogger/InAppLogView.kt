package com.velmurugan.inapplogger

import android.content.Context
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.util.AttributeSet
import android.view.View
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat

class InAppLogView constructor(context: Context, attrs: AttributeSet): LinearLayoutCompat(context, attrs),
    MessageObserver {

    val inAppLogger: InAppLogger by lazy {
        InAppLogger(this.context)
    }

    var textView: TextView? = null
    var scrollView: ScrollView? = null

    var view: View = inflate(context, R.layout.layout_inapp, this)

    override fun onFinishInflate() {
        super.onFinishInflate()
        textView = findViewById(R.id.tvText)
        scrollView = findViewById(R.id.scrollView)
        textView?.text = inAppLogger.getLog().toString()
    }

    override fun update() {
        val msg = inAppLogger.getLog().toString()
        textView?.text = Html.fromHtml(msg,FROM_HTML_MODE_LEGACY)
        scrollView?.fullScroll(View.FOCUS_DOWN)
    }


}