package com.example.civicstest

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import com.example.civicstest.databinding.ActivityQuizBinding

private lateinit var binding: ActivityQuizBinding

class QuizActivity : AppCompatActivity(), View.OnClickListener {
    private var position: Int = 0
    private var questionList: ArrayList<Question>? = null
    private var selectedAnswer: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        binding = ActivityQuizBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        questionList = Questions.getQuestions()
        newQuestion()

        binding.submitButton.setOnClickListener(this)
        binding.nextButton.setOnClickListener(this)
    }

    private fun showAnswer() {
        binding.questionBox.text =
            questionList!![position].correct.toString() + "\n" +
            questionList!![position].answers
    }

    private fun newQuestion() {
        position++

        val question = questionList!![position]

        while (position <= questionList!!.size) {
            if (!questionList!![position].correct) {
                binding.questionBox.text = question.question
                binding.answerBox.setText("")

                return
            } else {
                position++
            }
        }

        Toast.makeText(
            this,
            "You have successfully completed the Quiz", Toast.LENGTH_SHORT
        ).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.submitButton -> {
                questionList!![position].correct = questionList!![position].answers.contains(
                    binding.answerBox.text.toString(),
                    true
                )
                showAnswer()
            }

            R.id.nextButton -> {
                newQuestion()
            }
        }
    }
}