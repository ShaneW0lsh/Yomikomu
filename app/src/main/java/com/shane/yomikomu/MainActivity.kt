package com.shane.yomikomu

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.io.FileInputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {

    //private var selectedURI: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainButton = findViewById<Button>(R.id.main_button)
        mainButton.setOnClickListener {
            openFileDialogue()
        }

    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val uri = data?.data
                unzip(uri, Environment.getExternalStorageDirectory().path + "/Yomikomu/")
                //val path = uri?.path
                //Log.i("File's path", path.toString())
            }
        }

    private fun openFileDialogue() {
        var data = Intent(Intent.ACTION_GET_CONTENT)
        data.addCategory(Intent.CATEGORY_OPENABLE)
        data.type = "application/epub+zip"
        data = Intent.createChooser(data, "Choose a file")
        resultLauncher.launch(data)
    }

    private fun unzip(inputEpub: Uri?, output: String): Boolean {
        try {
            val filename: String
            val inputStream: FileInputStream = applicationContext.contentResolver.openInputStream(inputEpub) as FileInputStream


        } catch(e: IOException) {
            e.printStackTrace()
            return false
        }
        return true
    }
}