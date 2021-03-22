package com.example.stopwatch

import android.graphics.Color
import android.graphics.drawable.TransitionDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var stopAt: Long = 0
    private var count = 0
    private var running = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)

        val transition: TransitionDrawable = mainContainer.background as TransitionDrawable
        btnStart.setOnClickListener {
            transition.startTransition(500)
            stopWatch.setTextColor(Color.WHITE)
            stopWatch.base = SystemClock.elapsedRealtime() - stopAt
            stopWatch.start()
            running = true
            btnStop.visibility = View.VISIBLE
            btnStop.isEnabled = true
            btnRestart.visibility = View.VISIBLE
            btnRestart.isEnabled = true
            btnStart.visibility = View.INVISIBLE
            btnStart.isEnabled = false
            count = 0
            btnStop.text = "Stop"
        }

        btnStop.setOnClickListener {
            if (count == 0) {
                stopAt = SystemClock.elapsedRealtime() - stopWatch.base
                stopWatch.stop()
                Log.d("Button count 0", stopAt.toString())
                btnStop.text = "Resume"
                count = 1
            } else if (count == 1) {
                stopWatch.base = SystemClock.elapsedRealtime() - stopAt
                Log.d("Button count 1", stopAt.toString())
                stopWatch.start()
                btnStop.text = "Stop"
                count = 0
            }
        }

        btnRestart.setOnClickListener {
            if (count == 0) {
                stopWatch.stop()
            }
            count = 0
            stopWatch.base = SystemClock.elapsedRealtime()
            stopAt = 0L
            transition.reverseTransition(500)
            stopWatch.setTextColor(Color.BLACK)
            btnStop.visibility = View.INVISIBLE
            btnStop.isEnabled = false
            btnRestart.visibility = View.INVISIBLE
            btnRestart.isEnabled = false
            btnStart.visibility = View.VISIBLE
            btnStart.isEnabled = true
        }


    }
}