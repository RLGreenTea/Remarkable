package com.rl.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var listview: ListView
    private lateinit var json: JSONArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listview = findViewById(R.id.listview)
        readJSON()

        listview.setOnItemClickListener { _, _, position, _ ->
            AlertDialog.Builder(this)
                .setTitle(json.getJSONObject(position).getString("title"))
                .setMessage(json.getJSONObject(position).getString("content"))
                .show()
        }
    }

    private fun readJSON() {

        // json file to String
        val jsonString: String = assets.open("listview.json").bufferedReader().use{it.readText()}

        // String to json Array
        json = JSONArray(jsonString)

        val key = arrayOfNulls<String>(json.length())

        for(i in 0 until json.length()) {
            key[i] = json.getJSONObject(i).getString("title")
        }

        val adapter= ArrayAdapter(this, android.R.layout.simple_list_item_1, key)
        listview.adapter = adapter
    }
}