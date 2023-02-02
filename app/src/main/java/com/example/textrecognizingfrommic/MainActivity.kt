package com.example.textrecognizingfrommic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.textrecognizingfrommic.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val speechRecognizer = CustomSpeechRecognizer(
        activityResultRegistry,
        this
    ) {
        binding.textView.text = it
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickListeners()
    }

    private fun initClickListeners() {
        binding.button.setOnClickListener {
            speechRecognizer.recognizeSpeech()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
