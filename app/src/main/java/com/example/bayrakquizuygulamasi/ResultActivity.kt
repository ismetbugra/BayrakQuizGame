package com.example.bayrakquizuygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bayrakquizuygulamasi.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dogruSayac=intent.getIntExtra("dogruSayac",0)
        binding.sonucTextView.text="$dogruSayac Doğru ${5-dogruSayac} Yanlış!"
        binding.yuzdeTextView.text="% ${dogruSayac*20} Başarı!"

        binding.tekrarButton.setOnClickListener {
            startActivity(Intent(this@ResultActivity,QuizActivity::class.java))
            finish()
        }
    }
}