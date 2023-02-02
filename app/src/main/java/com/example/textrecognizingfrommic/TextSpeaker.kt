package com.example.textrecognizingfrommic

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

class TextSpeaker(
    private val context: Context
) {

    private var textToSpeech: TextToSpeech? = null

    init {
        textToSpeech = TextToSpeech(
            context
        ) { status ->
            if (status != TextToSpeech.ERROR) {
                textToSpeech?.language = Locale.getDefault()
            }
        }
    }

    fun speakText(text: String) {
        textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }
}
