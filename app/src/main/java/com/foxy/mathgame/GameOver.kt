package com.foxy.mathgame

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_game_over.*

class GameOver : AppCompatActivity() {

    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        supportActionBar?.hide()

        pref = getSharedPreferences("pref", 0)
        var savedPoints = pref.getInt("savedPoints", 0)
        val editor = pref.edit()

        val points = intent.getIntExtra("points", 0)

        if (points > savedPoints) {
            savedPoints = points
            editor.putInt("savedPoints", savedPoints)
            editor.apply()
            tv_high_score.visibility = View.VISIBLE
        }

        tv_points.text = points.toString()
        tv_best.text = savedPoints.toString()

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
