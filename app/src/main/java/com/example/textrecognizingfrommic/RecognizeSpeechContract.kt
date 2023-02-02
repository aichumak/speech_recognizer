package com.example.textrecognizingfrommic

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import androidx.activity.result.contract.ActivityResultContract
import java.util.*

class RecognizeSpeechContract : ActivityResultContract<Unit, String?>() {

    override fun createIntent(context: Context, input: Unit): Intent {
        return Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        }
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        if (intent == null) return null
        if (resultCode != Activity.RESULT_OK) return null

        return intent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0)
    }
}
