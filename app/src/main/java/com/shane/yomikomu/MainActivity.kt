package com.shane.yomikomu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.webkit.WebView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun loadWebViewData(unencodedHtml: String) {
        val webBookContents: WebView = findViewById(R.id.web_book_contents)

        val encodedHtml = Base64.encodeToString(unencodedHtml.toByteArray(), Base64.NO_PADDING)
        webBookContents.loadData(encodedHtml, "text/html", "base64")
    }
}