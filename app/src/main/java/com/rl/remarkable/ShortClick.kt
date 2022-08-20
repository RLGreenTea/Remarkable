package com.rl.remarkable

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import org.json.JSONObject
import java.io.File

class ShortClick(private val context: Context, private val filePath: String): View.OnClickListener {

    private val inputTitle = EditText(context)
    private val inputContent = EditText(context)

    override fun onClick(view: View) {

        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL

        inputTitle.hint = "Title"
        inputContent.hint = "Content"

        layout.addView(inputTitle)
        layout.addView(inputContent)

        AlertDialog.Builder(context)
            .setTitle("New")
            .setView(layout)
            .setCancelable(false)
            .setPositiveButton("Done") { _, _ ->

                if(inputTitle.text.toString() != "" && inputContent.text.toString() == "") {

                    File(filePath).writeText(
                        JSON().read(filePath).put(
                                JSONObject().put("title",inputTitle.text)
                                    .put("content",inputContent.text)
                        ).toString()
                    )
                }

                inputTitle.text = null
                inputContent.text = null

                layout.removeView(inputTitle)
                layout.removeView(inputContent)
            }
            .setNegativeButton("Cancel") { _, _ ->
                layout.removeView(inputTitle)
                layout.removeView(inputContent)
            }
            .show()
    }
}