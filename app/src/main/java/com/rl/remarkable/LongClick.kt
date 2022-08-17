package com.rl.remarkable

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import org.json.JSONArray
import kotlin.Long

class LongClick(private val context: Context, private val jsonArray: JSONArray): AdapterView.OnItemLongClickListener {

    private val inputTitle = EditText(context)
    private val inputContent = EditText(context)

    override fun onItemLongClick(p0: AdapterView<*>?, view: View, position: Int, p3: Long): Boolean {

        inputTitle.setText(jsonArray.getJSONObject(position).getString("title"))
        inputContent.setText(jsonArray.getJSONObject(position).getString("content"))

        inputTitle.hint = "Title"
        inputContent.hint = "Content"

        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL

        layout.addView(inputTitle)
        layout.addView(inputContent)

        AlertDialog.Builder(context)
            .setView(layout)
            .setPositiveButton("OK",null)
            .setNeutralButton("Cancel", null)
            .show()

        return true
    }
}