package com.carloshns.bin2dec

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.carloshns.bin2dec.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setupObservers()
    }

    fun setupObservers(){
        viewModel
                .binaryString
                .observe(this, Observer {
                    Log.d("Logando", "String: $it and isValid: ${viewModel.isValidNumber.value}")
                })


        viewModel
                .binaryNumber
                .observe(this, Observer {
                    Log.d("Logando", "Int: ${it.toString()} and isValid: ${viewModel.isValidNumber.value}")
                })


        viewModel.isValidNumber.observe(this, Observer {
            if(it == true){
                binding.binaryTextInput.clearFocus()
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("decimal", viewModel.decimalNumber.value)
                intent.putExtra("binary", viewModel.binaryNumber.value)
                startActivity(intent)
            } else {
                Snackbar.make(binding.root, "Your number must have 8 digits (0 and 1 only) ...", Snackbar.LENGTH_SHORT).show()
            }
        })
    }
}