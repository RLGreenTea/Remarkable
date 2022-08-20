package com.rl.remarkable

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import org.json.JSONArray
import java.io.*
import kotlin.Long

class LongItemClick(private val context: Context, private val filePath: String): AdapterView.OnItemLongClickListener {

    private val inputTitle = EditText(context)
    private val inputContent = EditText(context)

    override fun onItemLongClick(p0: AdapterView<*>?, view: View, position: Int, p3: Long): Boolean {

        inputTitle.hint = "Title"
        inputContent.hint = "Content"

        inputTitle.setText(JSON().read(filePath).getJSONObject(position).getString("title"))
        inputContent.setText(JSON().read(filePath).getJSONObject(position).getString("content"))

        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL

        layout.addView(inputTitle)
        layout.addView(inputContent)

        AlertDialog.Builder(context)
            .setTitle("Edit")
            .setView(layout)
            .setCancelable(false)
            .setPositiveButton("Done") { _, _ ->

                if(inputTitle.text.toString() != "") {
                    File(filePath).writeText(
                        JSON().read(filePath).toString().replace(
                            JSON().read(filePath).getJSONObject(position).getString("title"),
                            inputTitle.text.toString()
                        )
                    )

                }
                if(inputContent.text.toString() != "") {
                    File(filePath).writeText(
                        JSON().read(filePath).toString().replace(
                            JSON().read(filePath).getJSONObject(position).getString("content"),
                            inputTitle.text.toString()
                        )
                    )
                }

                layout.removeView(inputTitle)
                layout.removeView(inputContent)
            }
            .setNegativeButton("Cancel") { _, _ ->
                layout.removeView(inputTitle)
                layout.removeView(inputContent)
            }
            .setNeutralButton("Delete") { _, _ ->

                val list = JSONArray()
                val arr = JSON().read(filePath)
                for(i in 0 until arr.length()) {
                    if(i!=position) {
                        list.put(arr.get(i))
                    }
                }
                File(filePath).writeText(list.toString())

                layout.removeView(inputTitle)
                layout.removeView(inputContent)
            }
            .show()

        return true
    }
}