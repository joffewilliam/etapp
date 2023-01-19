package com.example.myapplication


import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.time.format.DateTimeFormatter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.*


class MainActivity : AppCompatActivity() {

    private val breakTime by lazy { 10 }
    private lateinit var distanceEditText: EditText
    private lateinit var averageSpeedEditText: EditText
    private lateinit var drivingTimeEditText: EditText
    private lateinit var startTimeEditText: EditText
    private lateinit var resultTextView: TextView
    private lateinit var calculateButton: Button
    private lateinit var resetButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        distanceEditText = findViewById(R.id.distance_edit_text)
        averageSpeedEditText = findViewById(R.id.average_speed_edit_text)
        drivingTimeEditText = findViewById(R.id.driving_time_edit_text)
        startTimeEditText = findViewById(R.id.start_time_edit_text)
        resultTextView = findViewById(R.id.result_text_view)
        calculateButton = findViewById(R.id.calculate_button)
        resetButton = findViewById(R.id.reset_button)

        calculateButton.setOnClickListener {
            try {
                val distanceString = distanceEditText.text.toString().trim()
                val averageSpeedString = averageSpeedEditText.text.toString().trim()
                val drivingTimeString = drivingTimeEditText.text.toString().trim()
                val startTimeString = startTimeEditText.text.toString().trim()
                val distance = distanceString.toDouble()
                val averageSpeed = averageSpeedString.toDouble()
                val duration = distance / averageSpeed
                val drivingTime = drivingTimeString.toDouble()
                val startTime = LocalTime.parse(startTimeString)
                val totalDrivingTime = duration + if (duration > 11) breakTime else 0
                val breaks = if (drivingTime >= duration) 0 else totalDrivingTime / drivingTime
                val endTime = startTime.plusHours(duration.toLong())
                val endTimeFormatted = endTime.toString()
                val breaksFormatted = breaks.toInt()

                val result = getString(R.string.eta_and_breaks, endTimeFormatted, breaksFormatted)
                resultTextView.text = result

            } catch (e: Exception) {
                resultTextView.text = getString(R.string.invalid_input)
            }
        }

        resetButton.setOnClickListener {
            distanceEditText.text.clear()
            startTimeEditText.text.clear()
            drivingTimeEditText.text.clear()
            averageSpeedEditText.text.clear()
            resultTextView.text = ""
        }
    }
}
