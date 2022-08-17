/* For Google Pixel 3 API 30 */

package com.rl.remarkable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.rl.remarkable.databinding.ActivityMainBinding
import org.json.JSONArray
import java.io.FileInputStream
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var file: FileInputStream
    private lateinit var jsonString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(!Environment.isExternalStorageManager()) {
            startActivityForResult(Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION), 101)
        }

        json("/storage/emulated/0/Documents/Remarkable.json")
    }

    private fun json(filePath: String) {

        var fileExist = true

        try {
            file = FileInputStream(filePath)
        } catch (e: java.io.FileNotFoundException) {
            fileExist = false
        }

        if(fileExist) {
            jsonString = file.bufferedReader().use{it.readText()}
        } else {
            jsonString = """[ { "title":"Example Title", "content":"http://www.example.com/" } ]"""
            AlertDialog.Builder(this)
                .setTitle("Exception")
                .setMessage("File Not Found")
                .setPositiveButton("OK",null)
                .show()
        }

        val jsonArray = JSONArray(jsonString)

        val key = arrayOfNulls<String>(jsonArray.length())

        for(i in 0 until jsonArray.length()) {
            key[i] = jsonArray.getJSONObject(i).getString("title")
        }

        val adapter= ArrayAdapter(this, android.R.layout.simple_list_item_1, key)
        binding.listview.adapter = adapter

        binding.listview.onItemLongClickListener = LongClick(this, jsonArray)
        binding.listview.onItemClickListener = ShortClick(this, jsonArray)
    }
}