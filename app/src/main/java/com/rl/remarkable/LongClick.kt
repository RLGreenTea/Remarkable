package com.rl.remarkable

import android.content.Context
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import org.json.JSONArray
import kotlin.Long

class LongClick(private val context: Context, private val jsonArray: JSONArray): AdapterView.OnItemLongClickListener {
    override fun onItemLongClick(p0: AdapterView<*>?, view: View, position: Int, p3: Long): Boolean {
        AlertDialog.Builder(context)
            .setTitle(jsonArray.getJSONObject(position).getString("title"))
            .setMessage(jsonArray.getJSONObject(position).getString("content"))
            .show()
        return true
    }
}