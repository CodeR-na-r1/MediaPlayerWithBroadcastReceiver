package com.mrx.mediaplayerwithbroadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MediaPlayerReceiver : BroadcastReceiver() {
    private val liveDataDuration by lazy { MutableLiveData<String>("") }
    fun getLiveDataDuration(): LiveData<String> {
        return liveDataDuration
    }

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(MediaPlayerService.TAG, "MediaPlayerReceiver onReceive")

        if (intent.getStringExtra(BROADCAST_CONSTANTS.STRING_DATA_TYPE_KEY.value) == BROADCAST_CONSTANTS.STRING_DATA_DURATION_KEY.value) {
            Log.d("myTag", "MediaPlayerReceiver DURATION_TRACK")
            Log.d("myTag", intent.getStringExtra(BROADCAST_CONSTANTS.DURATION_TRACK.value)?:"noooone")

            liveDataDuration.value = intent.getStringExtra(BROADCAST_CONSTANTS.DURATION_TRACK.value)
        }
    }
}