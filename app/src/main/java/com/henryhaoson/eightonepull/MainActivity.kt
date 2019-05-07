package com.henryhaoson.eightonepull

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.ext.rtmp.RtmpDataSourceFactory
import android.net.Uri
import android.util.Log
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.TrackSelection
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.BandwidthMeter


class MainActivity : AppCompatActivity() {
    companion object {
        const val MEDIA_URL = "media_url"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initiate Player
        //Create a default TrackSelector
        val bandwidthMeter = DefaultBandwidthMeter()

        val url = intent.extras.get(MEDIA_URL).toString()
        Log.d("url-----------", url)
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

        //Create the player
        val player = ExoPlayerFactory.newSimpleInstance(this, trackSelector)
        val playerView: PlayerView = findViewById(R.id.simple_player)
        playerView.setPlayer(player)

        val rtmpDataSourceFactory = RtmpDataSourceFactory()
        // This is the MediaSource representing the media to be played.
        val videoSource = ExtractorMediaSource.Factory(rtmpDataSourceFactory)
            .createMediaSource(Uri.parse(url.toString()))

        // Prepare the player with the source.
        player.prepare(videoSource)
        //auto start playing
        player.playWhenReady = true
    }
}
