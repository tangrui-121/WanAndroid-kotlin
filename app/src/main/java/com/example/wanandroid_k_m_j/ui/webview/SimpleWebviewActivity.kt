package com.example.wanandroid_k_m_j.ui.webview

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.wanandroid_k_m_j.R
import com.example.wanandroid_k_m_j.databinding.ActivitySimpleWebviewBinding
import com.example.wanandroid_k_m_j.exts.applyWindowInsets
import com.example.wanandroid_k_m_j.exts.safelyInsets
import com.example.wanandroid_k_m_j.utils.ToastAction.toast
import com.wanandroid.base.BaseActivity

class SimpleWebviewActivity : BaseActivity() {

    private val mViewBinding by viewBinding(ActivitySimpleWebviewBinding::bind)

    override val layoutId: Int
        get() = R.layout.activity_simple_webview

    companion object {
        const val EXTRA_URL = "url"
        const val EXTRA_TITLE = "title"

        @JvmStatic
        fun start(context: Context, url: String, title: String = "") =
            context.startActivity(
                Intent(context, SimpleWebviewActivity::class.java)
                    .putExtra(EXTRA_URL, url)
                    .putExtra(EXTRA_TITLE, title)
            )
    }

    lateinit var url_: String
    lateinit var title_: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding.root.applyWindowInsets {
            val insets = it.safelyInsets()
            mViewBinding.root.setPadding(insets.left, insets.top, insets.right, insets.bottom)
        }
        url_ = intent.getStringExtra(EXTRA_URL).toString()
        title_ = intent.getStringExtra(EXTRA_TITLE).toString()
        initWebview()
        mViewBinding.webView.addJavascriptInterface(SimpleJavascript(), "baseJavascript")
        mViewBinding.webView.setWebChromeClient(webChromeClient)
        mViewBinding.webView.setWebViewClient(webViewClient)
        dealWithUrl()
    }

    private fun dealWithUrl() {
        if (!isValid(url_)) {
            toast("???????????????????????????????????????")
            return
        }
        if (url_.startsWith("http")) mViewBinding.webView.loadUrl(url_)
    }

    //WebViewClient????????????WebView?????????????????????????????????
    private val webViewClient: WebViewClient = object : WebViewClient() {
        override fun onPageFinished(view: WebView?, url: String?) {
            mViewBinding.progressBar.visibility = View.GONE
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            mViewBinding.progressBar.visibility = View.VISIBLE
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
            return super.shouldOverrideUrlLoading(view, url)
        }
    }

    private val webChromeClient: WebChromeClient = object : WebChromeClient() {
        override fun onJsAlert(
            webView: WebView,
            url: String?,
            message: String?,
            result: JsResult
        ): Boolean {
            return true
        }

        override fun onGeolocationPermissionsShowPrompt(
            origin: String?,
            callback: GeolocationPermissions.Callback
        ) {
            callback.invoke(origin, true, false)
            super.onGeolocationPermissionsShowPrompt(origin, callback)
        }

        //??????????????????
        override fun onReceivedTitle(view: WebView?, title: String) {
            super.onReceivedTitle(view, title)
            mToolbar.setTitle(if (title_.isNotEmpty()) title_ else title)
        }

        //??????????????????
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            mViewBinding.progressBar.progress = newProgress
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mViewBinding.webView.canGoBack()) {
                mViewBinding.webView.goBack()
                return true
            } else {
                finish()
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    /**
     * ??????????????????
     */
    private inner class SimpleJavascript {

        @JavascriptInterface
        fun close() {
            finish()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebview() {
        val webSettings: WebSettings = mViewBinding.webView.getSettings()
        webSettings.javaScriptEnabled = true
        webSettings.useWideViewPort = true
        webSettings.loadWithOverviewMode = true // ????????????????????????
        webSettings.setSupportZoom(true) //????????????????????????true??????????????????????????????
        webSettings.builtInZoomControls = true //????????????????????????????????????false?????????WebView????????????
        webSettings.displayZoomControls = false //???????????????????????????
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE //??????webview?????????
        webSettings.allowFileAccess = true
        webSettings.domStorageEnabled = true
        webSettings.setGeolocationEnabled(true)
        webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
    }

    fun isValid(v: String?): Boolean {
        return !(v == null || v == "null") && !v.trim { it <= ' ' }.isEmpty()
    }
}