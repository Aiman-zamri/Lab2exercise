package com.example.lab2exercise

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Locale

class AnalysisResult : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_analysis_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // --- TAMBAHAN KOD UNTUK ANALISIS ---
        val textNama = findViewById<TextView>(R.id.textNama)
        val textTarikh = findViewById<TextView>(R.id.textTarikh)

        // Terima data dari Intent
        val nama = intent.getStringExtra("EXTRA_NAMA") ?: ""
        val tarikhStr = intent.getStringExtra("EXTRA_TARIKH") ?: ""

        // --- PART A: ANALISIS NAMA ---
        val cleanName = nama.replace(" ", "")
        val totalChar = cleanName.length
        val vowelCount = nama.filter { it.lowercaseChar() in "aeiou" }.length
        val repeatedChars = cleanName.lowercase()
            .groupBy { it }
            .filter { it.value.size > 1 }
            .keys
            .joinToString(", ")

        textNama.text = "Nama: $nama\n" +
                "Jumlah abjad: $totalChar\n" +
                "Bilangan vokal: $vowelCount\n" +
                "Abjad berulang: ${if (repeatedChars.isEmpty()) "Tiada" else repeatedChars}"

        // --- PART B: ANALISIS TARIKH LAHIR (Guna Calendar - Serasi Semua API) ---
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val birthDate = sdf.parse(tarikhStr)

            if (birthDate != null) {
                val dob = Calendar.getInstance().apply { time = birthDate }
                val today = Calendar.getInstance()

                // Kira Umur
                var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
                if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
                    age--
                }

                // Kira Hari Lahir dalam Bahasa Melayu
                val days = arrayOf("Ahad", "Isnin", "Selasa", "Rabu", "Khamis", "Jumaat", "Sabtu")
                val dayOfWeek = days[dob.get(Calendar.DAY_OF_WEEK) - 1]

                textTarikh.text = "Umur anda: $age tahun\nDilahirkan pada: $dayOfWeek"
            } else {
                textTarikh.text = "Umur anda: Format tarikh salah\nDilahirkan pada: -"
            }
        } catch (e: Exception) {
            textTarikh.text = "Umur anda: Format tarikh salah (Guna YYYY-MM-DD)\nDilahirkan pada: -"
        }

    }
}