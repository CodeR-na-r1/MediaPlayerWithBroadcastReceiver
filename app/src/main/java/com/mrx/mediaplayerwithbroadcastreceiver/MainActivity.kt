package com.mrx.mediaplayerwithbroadcastreceiver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.mrx.mediaplayerwithbroadcastreceiver.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MediaPlayerService.startService(applicationContext, TRACK_NAME)

        binding.playOrStop.setOnClickListener(playOrStopButtonListener)

        binding.toSecondActivity.setOnClickListener(toSecondActivityButtonListener)
    }

    private val playOrStopButtonListener = { view: View ->
        sendBroadcast(Intent().apply {
            action = BROADCAST_CONSTANTS.ID.value
            putExtra(BROADCAST_CONSTANTS.STRING_DATA_TYPE_KEY.value, BROADCAST_CONSTANTS.SWITCH_STATE.value)
        })
    }

    private val toSecondActivityButtonListener = { view: View ->
        startActivity(Intent(this, DetailActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()

        MediaPlayerService.stopService(applicationContext)
    }

    companion object {
        const val TAG = "myTag"

        const val TRACK_NAME = "DU_HAST.mp3"
    }
}