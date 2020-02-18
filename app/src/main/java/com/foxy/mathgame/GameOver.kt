package com.foxy.mathgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game_over.*

class GameOver : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        val points = intent.getIntExtra("points", 0)
        tv_points.text = points.toString()

        btn_restart.setOnClickListener { restartGame() }
        btn_exit.setOnClickListener { exitGame() }
    }

    private fun restartGame() {
        val intent = Intent(this, StartGame::class.java)
        startActivity(intent)
        finish()
    }

    private fun exitGame() {
        finish()
    }
}
