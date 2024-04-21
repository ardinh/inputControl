package com.id.inputcontrol

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.id.inputcontrol.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private var assetDateAcquisition: Calendar? = null

    val SHORT_MONTHS = arrayOf(
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "Mei",
        "Jun",
        "Jul",
        "Agu",
        "Sep",
        "Okt",
        "Nov",
        "Des"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.date.setLabel("Masukan Tanggal")

        binding.date.setOnClickListener {
            showDatePickerDialog(
                minDate = assetDateAcquisition
            ) {
                binding.date.textValue = dateToShortString(it)
            }
        }



    }

    fun dateToShortString(calendar: Calendar): String {
        val dateOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val month = SHORT_MONTHS[calendar.get(Calendar.MONTH)]
        val year = calendar.get(Calendar.YEAR)
        return "$dateOfMonth $month $year"
    }

    private fun showDatePickerDialog(
        default: Calendar? = null,
        minDate: Calendar? = null,
        callback: (date: Calendar) -> Unit
    ) {
        val ref = default ?: Calendar.getInstance()

        val dialogManager = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val date = Calendar.getInstance()
                date.set(year, month, dayOfMonth)

                callback(date)
            },
            ref.get(Calendar.YEAR),
            ref.get(Calendar.MONTH),
            ref.get(Calendar.DAY_OF_MONTH)
        )

        dialogManager.apply {
            minDate?.let { datePicker.minDate = it.timeInMillis }
        }

        dialogManager.show()
    }
}