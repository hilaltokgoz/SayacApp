package com.sayacapp

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.sayacapp.databinding.ActivityMainBinding
import timber.log.Timber
//onSaveInstanceState için key tanımlama
const val KEY_SANIYE="timer_saniye_key"
const val KEY_SAYI="sayi_key"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var counterTimer:CounterTimer
    private var number=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Log.i("MainActivity","onCreate Çağrıldı")
        Timber.i("onCreate Çağrıldı")  //Timber Kütüphanesine Çevrilmiş Hali
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        counterTimer= CounterTimer(this.lifecycle) //tımer initialize

        var defaultValue = 0
        binding.apply {
            counterTextView.text=number.toString()
            //bundle da veri var mı yok mu kontrol edilir varsa geri alınır.
        if (savedInstanceState !=null){
             number=savedInstanceState.getInt(KEY_SANIYE,0)
              counterTextView.text=number.toString()
             counterTimer.countedSeconds=savedInstanceState.getInt(KEY_SANIYE,0)
        }

            counterButton.setOnClickListener {
                val getValue=counterTextView.text.toString().toInt()
                number=getValue+1
                counterTextView.text = number.toString()
            }
        }
    }
    override fun onStart() {
        super.onStart()
      // counterTimer.startTimer()
        Timber.i("onStart() Çağrıldı") //  Log.i("MainActivity","onStart Çağrıldı")
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
       // counterTimer.stopTimer()
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
    //Verileri korumak için olışturduğumuz fonksiyondur.
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
       //Veriyi kaydetme
        outState.putInt(KEY_SANIYE,counterTimer.countedSeconds)//key-value
        outState.putInt(KEY_SAYI,number)
        Timber.i(number.toString())
        Timber.i("onSaveInstanceState çalıştı") //sayımız korundu.
    }
}