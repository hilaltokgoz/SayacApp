package com.sayacapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.sayacapp.databinding.ActivityMainBinding
import timber.log.Timber
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Log.i("MainActivity","onCreate Çağrıldı")
        Timber.i("onCreate Çağrıldı")  //Timber Kütüphanesine Çevrilmiş Hali
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var defaultValue = 0
        binding.apply {
            counterTextView.text = defaultValue.toString()
            counterButton.setOnClickListener {
                defaultValue++
                counterTextView.text = defaultValue.toString()
            }
        }
    }
    override fun onStart() {
        super.onStart()
        //  Log.i("MainActivity","onStart Çağrıldı")
        Timber.i("onStart() Çağrıldı")
    }
    override fun onRestart() {
        super.onRestart()
        Timber.i("onRestart() Çağrıldı")
    }
    override fun onResume() {
        super.onResume()
        Timber.i("onResume() Çağrıldı")
    }
    override fun onPause() {
        super.onPause()
        Timber.i("onPause() Çağrıldı")
    }
    override fun onStop() {
        super.onStop()
        Timber.i("onStop() Çağrıldı")
    }
    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy() Çağrıldı")
    }
    //Paylaş butonuna basılınca alttan çıkması için intent oluşturuldu.
    private fun onShare() {
        val share = Intent.createChooser(Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, binding.counterTextView.text.toString())
        }, null)
        startActivity(share)
    }
    //Menu Fonksiyonları
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.counter_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.shareAction -> onShare()
        }
        return super.onOptionsItemSelected(item)
    }
}