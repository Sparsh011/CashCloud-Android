package com.sparshchadha.stocktracker

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.zip.GZIPInputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<TextView>(R.id.tv1).text = getStocks()
    }

    private fun getStocks(): CharSequence {
        val json = this.assets.open("stocks.json").bufferedReader().use { it.readText() }
        val gson = Gson()
        val data: List<Map<String, String>> = gson.fromJson(json, List::class.java) as List<Map<String, String>>
        return data.toString()
    }
}