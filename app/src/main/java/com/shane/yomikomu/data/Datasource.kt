package com.shane.yomikomu.data

import android.content.Context
import android.util.Log
import com.shane.yomikomu.model.HTMLElement
import nl.siegmann.epublib.epub.EpubReader
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.nio.charset.StandardCharsets

class Datasource(private val context: Context) {

    fun loadHTMLElements(): List<HTMLElement> {
//        val HTMLList = retrieveBookHTML().split("\n").filterNot { it.isBlank() }
//        val returnList = mutableListOf<HTMLElement>()
//
//        for (elementString in HTMLList)
//            returnList.add(HTMLElement(elementString))

        val returnList = mutableListOf<HTMLElement>()
        val doc: Document = Jsoup.parse(retrieveBookHTML())
        val paragraphs: Elements = doc.select("p")

        Log.d("Paragraphs", paragraphs.html().toString())
        return returnList
    }

    private fun retrieveBookHTML(): String {
        val book = EpubReader().readEpub(context.assets.open("8.epub"))

        var dataBytes = byteArrayOf()
        for (c in book.contents) dataBytes += c.data

        return String(dataBytes, StandardCharsets.UTF_8)
    }
}