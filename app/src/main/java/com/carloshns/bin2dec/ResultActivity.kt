package com.carloshns.bin2dec

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val intent = intent
        val decimal = intent.getIntExtra("decimal", 0)
        var binary = intent.getStringExtra("binary")

        val decimalTextView = findViewById<TextView>(R.id.decimal)
        val binaryTextView = findViewById<TextView>(R.id.binary)
        val buttonBack = findViewById<ImageButton>(R.id.goback)

        decimalTextView.text = decimal.toString()
        binaryTextView.text = binary

        buttonBack.setOnClickListener {
            finish()
        }
    }
}