package com.mrx.mediaplayerwithbroadcastreceiver

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mrx.mediaplayerwithbroadcastreceiver.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//      toDo  binding.progressBar.progress

        val mediaPlayerReceiver = MediaPlayerReceiver()
        registerReceiver(mediaPlayerReceiver, IntentFilter(BROADCAST_CONSTANTS.ID.value))

        mediaPlayerReceiver.getLiveDataDuration().observe(this) {
            binding.durationTrack.text = it
        }

        binding.playOrStop.setOnClickListener {
            sendBroadcast(Intent().apply {
                action = BROADCAST_CONSTANTS.ID.value
                putExtra(BROADCAST_CONSTANTS.STRING_DATA_TYPE_KEY.value, BROADCAST_CONSTANTS.SWITCH_STATE.value)
            })
        }
    }

    companion object {
        const val TAG = "myTag"
    }
}