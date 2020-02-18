package com.foxy.mathgame

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_start_game.*
import java.util.*
import kotlin.collections.ArrayList

class StartGame : AppCompatActivity() {

    private var op1 = 0
    private var op2 = 0
    private var correctAnswer = 0
    private var points = 0
    private var numberOfQuestions = 0
    private var millisUntilFinished: Long = 30000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_game)
        supportActionBar?.hide()

        btn0.setOnClickListener(listener)
        btn1.setOnClickListener(listener)
        btn2.setOnClickListener(listener)
        btn3.setOnClickListener(listener)
        startGame()
    }

    private fun startGame() {
        tv_timer.text = getString(R.string.tv_timer, millisUntilFinished / 1000)
        tv_points.text = getString(R.string.tv_points, points, numberOfQuestions)
        generateQuestion()
        object : CountDownTimer(millisUntilFinished, 1000) {
            override fun onFinish() {
                btn0.isClickable = false
                btn1.isClickable = false
                btn2.isClickable = false
                btn3.isClickable = false
                startGameOver()
            }

            override fun onTick(millis: Long) {
                tv_timer.text = getString(R.string.tv_timer, millis / 1000)
            }

        }.start()
    }

    private fun generateQuestion() {
        numberOfQuestions++
        val btnIds = arrayListOf(btn0.id, btn1.id, btn2.id, btn3.id)
        val operators = arrayListOf("+", "-", "*", "÷")
        val random = Random()
        var selectedOperator = operators[random.nextInt(4)]

        op1 = random.nextInt(10)
        op2 = random.nextInt(9) + 1
        correctAnswer = getAnswer(selectedOperator)
        tv_sum.text = "$op1 $selectedOperator $op2 = "

        val correctAnswerPosition = random.nextInt(4)
        findViewById<Button>(btnIds[correctAnswerPosition]).text = "$correctAnswer"

        var incorrectAnswer: Int
        val incorrectAnswers = ArrayList<Int>()
        while (incorrectAnswers.size < btnIds.size) {
            op1 = random.nextInt(10)
            op2 = random.nextInt(9) + 1
            selectedOperator = operators[random.nextInt(4)]
            incorrectAnswer = getAnswer(selectedOperator)
            if (correctAnswer == incorrectAnswer) {
                continue
            }
            incorrectAnswers.add(incorrectAnswer)
        }

        for (i in btnIds.indices) {
            if (i == correctAnswerPosition) {
                continue
            }
            findViewById<Button>(btnIds[i]).text = "${incorrectAnswers[i]}"
        }
    }

    private fun getAnswer(operator: String) : Int {
        return when(operator) {
            "+" -> op1 + op2
            "-" -> op1 - op2
            "*" -> op1 * op2
            "÷" -> op1 / op2
            else -> 0
        }
    }

    private fun startGameOver() {
        val intent = Intent(this, GameOver::class.java)
        intent.putExtra("points", points)
        startActivity(intent)
        finish()
    }

    private val listener = View.OnClickListener {
        val answer = (it as Button).text.toString().toInt()
        if (answer == correctAnswer) {
            points++
            tv_result.text = getString(R.string.tv_result_correct)
        } else {
            tv_result.text = getString(R.string.tv_result_wrong)
        }
        tv_points.text = getString(R.string.tv_points, points, numberOfQuestions)

        chooseAnswer()
    }

    private fun chooseAnswer() {
        generateQuestion()
    }
}