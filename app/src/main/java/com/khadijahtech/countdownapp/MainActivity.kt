package com.khadijahtech.countdownapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.khadijahtech.countdownapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var score:Int = 0

    private var gameStarted = false
    private lateinit var countDownTimer: CountDownTimer
    private var initialTimer: Long = 60000
    private var interval:Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.gameScoreTextView.text = getString(R.string.total_score, 0)

        binding.button.setOnClickListener {
            incrementScore()
            gameStarted = true
        }

        resetGame()
    }

    private fun resetGame()
    {
        score=0
        binding.gameScoreTextView.text=getString(R.string.total_score, score.toString())

        val timeLeft = initialTimer/1000  //60
        binding.pastTimeTextView.text=getString(R.string.past_time, timeLeft.toString())

        //object from CountDownTimer
        countDownTimer = object:CountDownTimer(initialTimer, interval){
            override fun onFinish() {
                Toast.makeText(this@MainActivity,
                getString(R.string.final_game, score.toString()),
                Toast.LENGTH_LONG).show()
            }

            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished/1000
                binding.pastTimeTextView.text = getString(R.string.past_time, timeLeft.toString())
            }
        }
        gameStarted=false
    }
    private fun incrementScore()
    {
        if(!gameStarted) {
            //start timer
            countDownTimer.start()
            gameStarted = true
        }
        score ++
        binding.gameScoreTextView.text = getString(R.string.total_score, score.toString())
    }
}