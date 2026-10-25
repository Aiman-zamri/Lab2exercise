package com.example.lab2exercise

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val editName= findViewById<EditText>(R.id.editNama)
        val editTarikh=findViewById<EditText>(R.id.editTarikh)
        val btnAnalisis= findViewById<Button>(R.id.btnAnalisis)

        btnAnalisis.setOnClickListener {
            val nama =editName.text.toString()
            val tarikh= editTarikh.text.toString()

            val intent = Intent(this, AnalysisResult::class.java).apply{
                putExtra("EXTRA_NAMA", nama)
                putExtra("EXTRA_TARIKH",tarikh)

            }
            startActivity(intent)

        }

    }

}