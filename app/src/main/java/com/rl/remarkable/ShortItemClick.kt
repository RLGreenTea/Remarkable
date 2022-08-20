package com.rl.remarkable

import android.view.View
import android.widget.AdapterView
import com.google.android.material.snackbar.Snackbar

class ShortItemClick(private val filePath: String): AdapterView.OnItemClickListener {

    override fun onItemClick(p0: AdapterView<*>?, view: View, position: Int, p3: Long) {

        Snackbar.make(view, JSON().read(filePath).getJSONObject(position).getString("content"), Snackbar.ANIMATION_MODE_SLIDE).show()
    }
}