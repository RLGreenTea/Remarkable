package com.rl.remarkable

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.AdapterView
import org.json.JSONArray

class ShortClick(private val context: Context, private val jsonArray: JSONArray): AdapterView.OnItemClickListener {
    override fun onItemClick(p0: AdapterView<*>?, view: View, position: Int, p3: Long) {
        val openURL = Intent(Intent.ACTION_VIEW)
        openURL.data = Uri.parse(jsonArray.getJSONObject(position).getString("content"))
        context.startActivity(openURL)
    }
}