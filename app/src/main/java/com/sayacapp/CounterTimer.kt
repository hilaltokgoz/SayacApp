package com.sayacapp

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import timber.log.Timber

// Code with ❤️
//┌──────────────────────────┐
//│ Created by Hilal TOKGOZ       │
//│ ──────────────────────── │
//│ hilaltokgoz98@gmail.com                 │            
//│ ──────────────────────── │
//│ 16.05.2022                  │
//└──────────────────────────┘
//Lifecycle değişkeni:Sayac timer'ın gözlemleyeceği lifecycle
class CounterTimer(lifecycle:Lifecycle): LifecycleEventObserver {
    var countedSeconds = 0
    private var handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable

    init {
        lifecycle.addObserver(this) //lifecycle observer burası
    }
    //Timer çalıştır
    fun startTimer() {
        runnable = Runnable {
            countedSeconds++
            Timber.i("Timer sn: $countedSeconds")
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }

    //Timer durdur
    fun stopTimer() {
        handler.removeCallbacks(runnable)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event==Lifecycle.Event.ON_START) startTimer()
        else if (event==Lifecycle.Event.ON_STOP) stopTimer()
    }

}