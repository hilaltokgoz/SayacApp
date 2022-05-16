package com.sayacapp

import android.app.Application
import timber.log.Timber

// Code with ❤️
//┌──────────────────────────┐
//│ Created by Hilal TOKGOZ  │
//│ ──────────────────────── │
//│ hilaltokgoz98@gmail.com  │
//│ ──────────────────────── │
//│ 1.05.2022                │
//└──────────────────────────┘
//Timber kütüphanesi için oluşturulan global applicationClass'ı 
class CounterApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree()) //Timber Kütüphanesi başlatıldı.
    }

}