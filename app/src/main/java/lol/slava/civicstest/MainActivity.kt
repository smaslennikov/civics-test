package lol.slava.civicstest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import lol.slava.civicstest.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.startButton.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.resetCorrect.setOnClickListener {
            val sharedPreferences = getSharedPreferences("prefs", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("questions", null)
            editor.apply()
            Toast.makeText(this, "Reset settings", Toast.LENGTH_SHORT).show()
        }
    }
}