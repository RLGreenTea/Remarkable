/* For Google Pixel 3 API 30 */

package com.rl.remarkable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.widget.ArrayAdapter
import com.rl.remarkable.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val filePath: String = "/storage/emulated/0/Documents/Remarkable.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(!Environment.isExternalStorageManager()) {
            startActivityForResult(Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION), 101)
        }

        setup()

        binding.listview.onItemClickListener = ShortItemClick(filePath)
        binding.listview.onItemLongClickListener = LongItemClick(this, filePath)
        binding.fab.setOnClickListener(ShortClick(this, filePath))
        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing = false
            setup()
        }
    }

    private fun setup() {
        binding.listview.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, JSON().load(this, filePath))
    }
}