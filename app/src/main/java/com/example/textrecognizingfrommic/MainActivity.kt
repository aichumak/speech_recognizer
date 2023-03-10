package com.example.textrecognizingfrommic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.textrecognizingfrommic.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = checkNotNull(_binding)
    private val speechRecognizer = SpeechRecognizer(
        activityResultRegistry,
        this
    ) {
        binding.textView.text = it
    }
    private lateinit var textSpeaker: TextSpeaker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        textSpeaker = TextSpeaker(applicationContext)
        initClickListeners()
    }

    private fun initClickListeners() {
        binding.recognizeButton.setOnClickListener {
            speechRecognizer.recognizeSpeech()
        }
        binding.textToSpeechButton.setOnClickListener {
            textSpeaker.speakText(binding.textView.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
