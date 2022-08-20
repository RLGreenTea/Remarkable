package com.rl.remarkable

import android.content.Context
import androidx.appcompat.app.AlertDialog
import org.json.JSONArray
import java.io.File
import java.lang.Exception

class JSON {

    fun read(filePath: String): JSONArray {
        try {
            File(filePath).readText()
        } catch (e: Exception) {
            File(filePath).writeText("""[{"title":"Example Title","content":"http://www.example.com/"}]""")
        }
        return JSONArray(File(filePath).readText())
    }

    fun load(context: Context, filePath: String): Array<String> {
        try {
            File(filePath).readText()
        } catch (e: Exception) {
            File(filePath).writeText("""[ { "title":"Example Title", "content":"http://www.example.com/" } ]""")
            AlertDialog.Builder(context)
                .setTitle("File not Found")
                .setMessage("Remarkable.json not found\nSystem has created one")
                .setPositiveButton("OK", null)
                .show()
        }
        val jsonArray = JSONArray(File(filePath).readText())

        val list = Array(jsonArray.length()) { "it = $it" }
        for(i in 0 until jsonArray.length()) {
            list[i] = jsonArray.getJSONObject(i).getString("title")
        }
        return list
    }
}