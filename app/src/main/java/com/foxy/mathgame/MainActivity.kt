package com.foxy.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        btn_play.setOnClickListener { startGame() }
        tv_play.setOnClickListener { startGame() }
    }

    private fun startGame() {
        val intent = Intent(this, StartGame::class.java)
        startActivity(intent)
        finish()
    }

}
