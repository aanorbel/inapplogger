package com.velmurugan.inapplogger

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.util.AttributeSet
import android.view.View
import android.widget.*
import androidx.appcompat.widget.LinearLayoutCompat


class InAppLogView constructor(context: Context, attrs: AttributeSet?) :
    LinearLayoutCompat(context, attrs),
    MessageObserver {

    constructor(context: Context) : this(context, null)

    val inAppLogger: InAppLogger by lazy {
        InAppLogger(this.context)
    }

    var textView: TextView? = null
    var scrollView: ScrollView? = null
    var spinnerTag: Spinner? = null
    var btnClear: Button? = null
    var btnClose: Button? = null
    var selectedTag = LogType.ALL.name
    private val tags = LogType.getAllTypes()
    var view: View = inflate(context, R.layout.layout_inapp, this)

    override fun onFinishInflate() {
        super.onFinishInflate()
        textView = findViewById(R.id.tvText)
        scrollView = findViewById(R.id.scrollView)
        spinnerTag = findViewById(R.id.spinnerTag)
        btnClear = findViewById(R.id.btnCleanLog)
        btnClose = findViewById(R.id.btnCloseLog)
        textView?.text = inAppLogger.getLog().toString()
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            context,R.layout.layout_log_type,tags
        )
        spinnerTag?.adapter = adapter
        spinnerTag?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

                selectedTag = parent.getItemAtPosition(position).toString()
                update(selectedTag)

            } // to close the onItemSelected

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        btnClear?.setOnClickListener {
            inAppLogger.deleteOldLog()
            update(selectedTag)
        }

        btnClose?.setOnClickListener {
            visibility = View.GONE
        }


    }

    override fun update(tag: String?) {
        Handler(Looper.getMainLooper()).post {
            val msg = inAppLogger.getLog(selectedTag).toString()
            textView?.text = Html.fromHtml(msg, FROM_HTML_MODE_LEGACY)
            scrollView?.fullScroll(View.FOCUS_DOWN)
        }

    }


}