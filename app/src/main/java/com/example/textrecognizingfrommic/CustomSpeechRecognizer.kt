package com.example.textrecognizingfrommic

import android.media.AudioAttributes
import android.media.SoundPool
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.lifecycle.LifecycleOwner

class CustomSpeechRecognizer(
    activityResultRegistry: ActivityResultRegistry,
    lifecycleOwner: LifecycleOwner,
    callBack: (text: String?) -> Unit
) {

    companion object {

        const val SPEECH_RECOGNIZING_REGISTRY_KEY = "SPEECH_RECOGNIZING_REGISTRY_KEY"
    }

    private var sounds: SoundPool? = null
    private val getRecognizedText: ActivityResultLauncher<Unit> = activityResultRegistry.register(
        SPEECH_RECOGNIZING_REGISTRY_KEY,
        lifecycleOwner,
        RecognizeSpeechContract(),
        callBack
    )

    init {
        initSoundPool()
    }

    private fun initSoundPool() {
        val attributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        sounds = SoundPool.Builder()
            .setAudioAttributes(attributes)
            .build()
    }

    fun recognizeSpeech() {
        getRecognizedText.launch(Unit)
    }
}
