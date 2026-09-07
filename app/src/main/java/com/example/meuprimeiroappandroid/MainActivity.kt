package com.example.meuprimeiroappandroid

import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.meuprimeiroappandroid.broadcastreceiver.LowBatteryBroadcastReceiver
import com.example.meuprimeiroappandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val lowBatteryBroadcastReceiver = LowBatteryBroadcastReceiver()
    private val lowBatteryIntentFilter = IntentFilter("android.intent.action.BATTERY_LOW")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //val tvMyFirstAndroidApp = findViewById<TextView>(R.id.tvMyFirstAndroidApp)

        with(binding.tvMyFirstAndroidApp){
            text = "My First Android App!!"
            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
        }

        supportFragmentManager.beginTransaction().add(R.id.flMainContainer,BlankFragment.newInstance(
            "Hello", "Heloísa!"
        )).commit()

        registerReceiver(lowBatteryBroadcastReceiver, lowBatteryIntentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(lowBatteryBroadcastReceiver)
        Log.d("MainActivity", "onDestroy")
    }
}