package lol.slava.civicstest

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import lol.slava.civicstest.databinding.ActivityQuizBinding
import java.lang.reflect.Type


lateinit var questionList: ArrayList<Question>

class QuizActivity : AppCompatActivity(), View.OnClickListener {
    private var position: Int = 0
    private lateinit var binding: ActivityQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        binding = ActivityQuizBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("questions", null)
        if (json != null) {
            val type: Type = object : TypeToken<ArrayList<Question?>?>() {}.type
            @Suppress("UNCHECKED_CAST") // TODO maybe fix this later
            questionList = gson.fromJson<Any>(json, type) as ArrayList<Question>
        } else {
            questionList = Questions.getQuestions()
        }

        binding.questionBox.movementMethod = ScrollingMovementMethod()

        newQuestion()

        binding.submitButton.setOnClickListener(this)
        binding.nextButton.setOnClickListener(this)
    }

    private fun showAnswer() {
        binding.questionBox.text =
            buildString {
                append(questionList[position].correct.toString())
                append(":")
                append(questionList[position].answers)
            }
    }

    private fun storeChanges() {
        val sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)
        val gson = Gson()
        val editor = sharedPreferences.edit()
        val json: String = gson.toJson(questionList)
        editor.putString("questions", json)
        editor.apply()
        Toast.makeText(this, "Saved changes", Toast.LENGTH_SHORT).show()
    }

    private fun newQuestion() {
        while (position < questionList.size) {
            val question = questionList[position]

            if (!question.correct) {
                binding.questionBox.text = question.question
                binding.answerBox.setText("")

                return
            } else {
                position++
            }
        }

        Toast.makeText(
            this,
            "All out of questions!", Toast.LENGTH_SHORT
        ).show()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.submitButton -> {
                questionList[position].correct = questionList[position].answers.contains(
                    binding.answerBox.text.toString(),
                    true
                )
                storeChanges()
                showAnswer()
            }

            R.id.nextButton -> {
                position++
                newQuestion()
            }
        }
    }
}