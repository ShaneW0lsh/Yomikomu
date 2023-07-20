package com.shane.yomikomu.data

import android.content.Context
import android.util.Log
import com.shane.yomikomu.model.HTMLElement
import nl.siegmann.epublib.epub.EpubReader
import java.nio.charset.StandardCharsets

class Datasource(private val context: Context) {

    fun loadHTMLElements(): List<HTMLElement> {
        TODO("Use parser here instead. You should render only tags inside body tag")
        val HTMLList = retrieveBookData().split("\n").filterNot { it.isBlank() }
        val returnList = mutableListOf<HTMLElement>()

        for (elementString in HTMLList)
            returnList.add(HTMLElement(elementString))

        Log.d("LIST", returnList[20].toString())
        Log.d("LIST", returnList[21].toString())

        return returnList
    }

    private fun retrieveBookData(): String {
        val book = EpubReader().readEpub(context.assets.open("8.epub"))

        var dataBytes = byteArrayOf()
        for (c in book.contents) dataBytes += c.data

        return String(dataBytes, StandardCharsets.UTF_8)
    }
}