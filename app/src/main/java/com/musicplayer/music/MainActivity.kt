package com.musicplayer.music

import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.concurrent.schedule


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val start: Button = findViewById(R.id.btn_play)
        val stop: Button = findViewById(R.id.btn_stop)
        val pause: Button = findViewById(R.id.btn_pause)
        val next: Button = findViewById(R.id.btn_next)
        val prev: Button = findViewById(R.id.btn_prev)
        var list = listOf(
            R.raw.bhagat,
            R.raw.young,
            R.raw.music,
            R.raw.shoot,
            R.raw.raam,
            R.raw.chaska,
            R.raw.panga,
            R.raw.perfect
        )
        var mp: MediaPlayer = MediaPlayer.create(this, list[0])
        val seekBar: SeekBar = findViewById(R.id.progress)
        seekBar.setMax(mp.getDuration())
        var current: Int = 0
        var i: Int = current
        var j: Int = current

        fun loop() {
            mp.setOnCompletionListener {
                current++
                if (current > list.lastIndex) {
                    current = 0
                }
                mp = MediaPlayer.create(this, list[current])
                mp.start()
                seekBar.setProgress(0)
            }
        }



        start.setOnClickListener {
            if(!mp.isPlaying) {
                mp = MediaPlayer.create(this, list[current])
                mp.start()
            }
        }
        stop.setOnClickListener {
            if(mp.isPlaying) {
                mp.stop()
                mp = MediaPlayer.create(this, list[0])
                seekBar.setProgress(0)
            }
        }
        pause.setOnClickListener {
            mp.pause()
        }
        next.setOnClickListener {
            if(mp.isPlaying) {
                mp.stop()
            }
            i++
            if(i > list.lastIndex) {
                i = 0
            }
            mp = MediaPlayer.create(this, list[current])
            mp.start()
            current = i
            seekBar.setProgress(0)


        }
        prev.setOnClickListener {
            if(mp.isPlaying) {
                mp.stop()
            }
            j--
            if(j < 0) {
                j = list.lastIndex
            }
            mp = MediaPlayer.create(this, list[current])
            mp.start()
            current = j
            seekBar.setProgress(0)
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p: Int, b: Boolean) {
                if (b) {
                    mp.seekTo(p)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })



    }

}