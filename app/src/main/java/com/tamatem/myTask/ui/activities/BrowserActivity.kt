package com.tamatem.myTask.ui.activities

import android.app.Activity
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.tamatem.myTask.R

class BrowserActivity : AppCompatActivity() {
    val activity: Activity = this
    var webView: WebView? = null
    var ivBackToolBar: ImageView? = null
    var btnForward: Button? = null
    var btnBack: Button? = null
    var btnRefresh: Button? = null
    var linearProgressIndicator: LinearProgressIndicator? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)
        initView()
        intiListener()
        loadWebView()
    }

    private fun loadWebView() {
        try {
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            webView?.getSettings()?.setJavaScriptEnabled(true)
            webView?.setWebViewClient(object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    return false
                }

                override fun onPageFinished(view: WebView, url: String) {
                    try {
                        linearProgressIndicator!!.visibility = View.GONE
                    } catch (e: Exception) {
                    }
                }
            })
            try {
                webView?.loadUrl("https://tamatemplus.com")
                webView?.getSettings()?.setCacheMode(WebSettings.LOAD_NO_CACHE)
                webView?.setWebChromeClient(WebChromeClient())
                webView?.getSettings()?.setDomStorageEnabled(true)
            } catch (e: Exception) {
            }
        } catch (e: Exception) {
        }
    }

    private fun intiListener() {
        ivBackToolBar?.setOnClickListener(View.OnClickListener { finish() })
        btnForward?.setOnClickListener(View.OnClickListener {
            if (webView!!.canGoForward()) {
                webView!!.goForward()
            }
        })
        btnBack?.setOnClickListener(View.OnClickListener {
            if (webView?.canGoBack()!!) {
                webView?.goBack()
            } else {
                finish()
            }
        })
        btnRefresh?.setOnClickListener(View.OnClickListener { webView!!.reload() })
    }

    private fun initView() {
        btnForward = findViewById<View>(R.id.btnForward) as Button
        btnRefresh = findViewById<View>(R.id.btnRefresh) as Button
        btnBack = findViewById<View>(R.id.btnBack) as Button
        ivBackToolBar = findViewById<View>(R.id.ivBackToolBar) as ImageView
        webView = findViewById<View>(R.id.webView) as WebView
        linearProgressIndicator = findViewById(R.id.linearProgressIndicator)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    try {
                        if (webView?.canGoBack()!!) {
                            webView?.goBack()
                        } else {
                            finish()
                        }
                    } catch (e: Exception) {
                    }
                    return true
                }
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}