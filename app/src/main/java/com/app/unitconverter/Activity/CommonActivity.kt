package com.app.unitconverter.Activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.app.unitconverter.R
import com.app.unitconverter.databinding.ActivityCommonBinding
import kotlin.math.log

@SuppressLint("SetTextI18n")
class CommonActivity : AppCompatActivity() {

    lateinit var binding: ActivityCommonBinding
    private var fromUnit: String? = null
    private var toUnit: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCommonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val unitType = intent.getStringExtra("unitType")
        setupDefaultSelection(unitType.toString())

        when (unitType) {
            "Distance" -> {

                binding.apply {
                    mainTitle.text = "Distance"
                    selectUnit.text = "Distance"
                    relativeLFrom.setOnClickListener {
                        showPopupMenu(it, "From", "Distance")
                    }
                    relativeLTo.setOnClickListener {
                        showPopupMenu(it, "To", "Distance")
                    }
                }
            }

            "Volume" -> {

                binding.apply {
                    mainTitle.text = "Volume"
                    selectUnit.text = "Volume"
                    relativeLFrom.setOnClickListener {
                        showPopupMenu(it, "From", "Volume")
                    }
                    relativeLTo.setOnClickListener {
                        showPopupMenu(it, "To", "Volume")
                    }
                }
            }

            "Weight" -> {
                binding.apply {
                    mainTitle.text = "Weight"
                    selectUnit.text = "Weight"
                    relativeLFrom.setOnClickListener {
                        showPopupMenu(it, "From", "Weight")
                    }
                    relativeLTo.setOnClickListener {
                        showPopupMenu(it, "To", "Weight")
                    }
                }

            }

            "Temperature" -> {
                binding.apply {
                    mainTitle.text = "Temperature"
                    selectUnit.text = "Temperature"
                    relativeLFrom.setOnClickListener {
                        showPopupMenu(it, "From", "Temperature")
                    }
                    relativeLTo.setOnClickListener {
                        showPopupMenu(it, "To", "Temperature")
                    }
                }

            }

            "Speed" -> {
                binding.apply {
                    mainTitle.text = "Speed"
                    selectUnit.text = "Speed"
                    relativeLFrom.setOnClickListener {
                        showPopupMenu(it, "From", "Speed")
                    }
                    relativeLTo.setOnClickListener {
                        showPopupMenu(it, "To", "Speed")
                    }
                }

            }

            "Energy" -> {
                binding.apply {
                    mainTitle.text = "Energy"
                    selectUnit.text = "Energy"
                    relativeLFrom.setOnClickListener {
                        showPopupMenu(it, "From", "Energy")
                    }
                    relativeLTo.setOnClickListener {
                        showPopupMenu(it, "To", "Energy")
                    }
                }

            }

            "Power" -> {
                binding.apply {
                    mainTitle.text = "Power"
                    selectUnit.text = "Power"
                    relativeLFrom.setOnClickListener {
                        showPopupMenu(it, "From", "Power")
                    }
                    relativeLTo.setOnClickListener {
                        showPopupMenu(it, "To", "Power")
                    }
                }
            }

            "Time" -> {
                binding.apply {
                    mainTitle.text = "Time"
                    selectUnit.text = "Time"
                    relativeLFrom.setOnClickListener {
                        showPopupMenu(it, "From", "Time")
                    }
                    relativeLTo.setOnClickListener {
                        showPopupMenu(it, "To", "Time")
                    }
                }

            }

            "Area" -> {
                binding.apply {
                    mainTitle.text = "Area"
                    selectUnit.text = "Area"
                    relativeLFrom.setOnClickListener {
                        showPopupMenu(it, "From", "Area")
                    }
                    relativeLTo.setOnClickListener {
                        showPopupMenu(it, "To", "Area")
                    }
                }
            }

            "Storage" -> {
                binding.apply {
                    mainTitle.text = "Digital Storage"
                    selectUnit.text = "Digital Storage"
                    relativeLFrom.setOnClickListener {
                        showPopupMenu(it, "From", "Storage")
                    }
                    relativeLTo.setOnClickListener {
                        showPopupMenu(it, "To", "Storage")
                    }
                }
            }
        }
        binding.btnClickConvert.setOnClickListener {


            val value =  binding.enterValue.text.toString().toDoubleOrNull()
                if (value != null) {

                    if (fromUnit != null && toUnit != null) {

                        val result = convertUnits(value, fromUnit!!, toUnit!!)
                        binding.showAns.text = "Result: $result $toUnit"
                        binding.showAns.setTextColor(resources.getColor(R.color.blue, theme))
                    } else {
                        binding.showAns.text = "Please select both units"
                        binding.showAns.setTextColor(resources.getColor(R.color.red, theme))
                    }
                } else {
                    binding.showAns.text = "Please enter a valid value and select both units"
                    binding.showAns.setTextColor(resources.getColor(R.color.red, theme))
                }

        }
    }
    private fun setupDefaultSelection(unitType: String) {
        val defaultPopupItems = when (unitType) {
            "Distance" -> resources.getStringArray(R.array.Distance)
            "Volume" -> resources.getStringArray(R.array.Volume)
            "Weight" -> resources.getStringArray(R.array.Weight)
            "Temperature" -> resources.getStringArray(R.array.Temperature)
            "Speed" -> resources.getStringArray(R.array.Speed)
            "Energy" -> resources.getStringArray(R.array.Energy)
            "Power" -> resources.getStringArray(R.array.Power)
            "Time" -> resources.getStringArray(R.array.Time)
            "Area" -> resources.getStringArray(R.array.Area)
            "Storage" -> resources.getStringArray(R.array.Storage)
            else -> arrayOf("No items available")
        }

        fromUnit = defaultPopupItems[0]
        toUnit = if (defaultPopupItems.size > 1) defaultPopupItems[1] else defaultPopupItems[0]

        binding.SUnitFrom.text = fromUnit
        binding.SUnitTo.text = toUnit
    }


    private fun showPopupMenu(view: View, selectionType: String, unitType: String) {
        val popupMenu = PopupMenu(this, view)

        val popupItems = when (unitType) {
            "Distance" -> resources.getStringArray(R.array.Distance)
            "Volume" -> resources.getStringArray(R.array.Volume)
            "Weight" -> resources.getStringArray(R.array.Weight)
            "Temperature" -> resources.getStringArray(R.array.Temperature)
            "Speed" -> resources.getStringArray(R.array.Speed)
            "Energy" -> resources.getStringArray(R.array.Energy)
            "Power" -> resources.getStringArray(R.array.Power)
            "Time" -> resources.getStringArray(R.array.Time)
            "Area" -> resources.getStringArray(R.array.Area)
            "Storage" -> resources.getStringArray(R.array.Storage)
            else -> arrayOf("No items available")
        }

        val filteredPopupItems = if (selectionType == "To" && fromUnit != null) {
            popupItems.filter { it != fromUnit }
        } else {
            popupItems.toList()
        }

        filteredPopupItems.forEachIndexed { index, item ->
            popupMenu.menu.add(0, index, index, item)
        }

        // Handle menu item clicks
        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            val itemId = menuItem.itemId
            val selectedItem = filteredPopupItems[itemId]

            when (selectionType) {
                "From" -> {
                    binding.SUnitFrom.text = selectedItem
                    fromUnit = selectedItem
                    if (toUnit == fromUnit) {
                        toUnit = filteredPopupItems.firstOrNull { it != fromUnit } ?: filteredPopupItems[0]
                        binding.SUnitTo.text = toUnit
                    }
                }

                "To" -> {
                    if (selectedItem == fromUnit) {
                        binding.SUnitTo.text = ""
                        toUnit = ""
                    } else {
                        binding.SUnitTo.text = selectedItem
                        toUnit = selectedItem
                    }
                }
            }
            true
        }
        popupMenu.show()
    }

    @SuppressLint("DefaultLocale")
    private fun convertUnits(value: Double, fromUnit: String, toUnit: String): Double {
        Log.d("ConversionDebug", "Value: $value, From: $fromUnit, To: $toUnit")
        return when (fromUnit to toUnit) {


            /* *****************************  Distance conversions  *****************************  */
            "Kilometer" to "Centimeter" -> String.format("%.4f", value * 100000.00).toDouble()
            "Kilometer" to "Meter" -> String.format("%.4f", value * 1000.00).toDouble()
            "Kilometer" to "Millimeter" -> String.format("%.4f", value * 1000000.00).toDouble()
            "Kilometer" to "Micrometers" -> String.format("%.4f", value * 1000000000.00).toDouble()
            "Kilometer" to "Foot" -> String.format("%.4f", value * 3280.84).toDouble()
            "Kilometer" to "Inch" -> String.format("%.4f", value * 39370.1).toDouble()

            "Centimeter" to "Kilometer" -> String.format("%.8f", value / 100000.00).toDouble()
            "Centimeter" to "Meter" -> String.format("%.4f", value / 100.00).toDouble()
            "Centimeter" to "Millimeter" -> String.format("%.4f", value * 10.00).toDouble()
            "Centimeter" to "Micrometers" -> String.format("%.4f", value * 10000.00).toDouble()
            "Centimeter" to "Foot" -> String.format("%.4f", value / 30.48).toDouble()
            "Centimeter" to "Inch" -> String.format("%.4f", value / 2.54).toDouble()

            "Meter" to "Centimeter" -> String.format("%.4f", value * 100.00).toDouble()
            "Meter" to "Kilometer" -> String.format("%.4f", value / 1000.00).toDouble()
            "Meter" to "Millimeter" -> String.format("%.4f", value * 1000.00).toDouble()
            "Meter" to "Micrometers" -> String.format("%.4f", value * 1000000.00).toDouble()
            "Meter" to "Foot" -> String.format("%.4f", value * 3.281).toDouble()
            "Meter" to "Inch" -> String.format("%.4f", value * 39.3701).toDouble()

            "Millimeter" to "Centimeter" -> String.format("%.4f", value / 10.00).toDouble()
            "Millimeter" to "Kilometer" -> String.format("%.8f", value / 100000.00).toDouble()
            "Millimeter" to "Meter" -> String.format("%.4f", value / 1000.00).toDouble()
            "Millimeter" to "Micrometers" -> String.format("%.4f", value * 1000.00).toDouble()
            "Millimeter" to "Foot" -> String.format("%.4f", value / 304.8).toDouble()
            "Millimeter" to "Inch" -> String.format("%.4f", value / 25.4).toDouble()

            "Micrometers" to "Centimeter" -> String.format("%.4f", value / 10000.00).toDouble()
            "Micrometers" to "Kilometer" -> value / 1000000000.00
            "Micrometers" to "Meter" -> value / 1000000.00
            "Micrometers" to "Millimeter" -> String.format("%.4f", value / 1000.00).toDouble()
            "Micrometers" to "Foot" -> String.format("%.8f", value / 304800.0).toDouble()
            "Micrometers" to "Inch" -> String.format("%.8f", value / 25400.0).toDouble()

            "Foot" to "Kilometer" -> String.format("%.4f", value / 3280.84).toDouble()
            "Foot" to "Centimeter" -> String.format("%.4f", value * 30.48).toDouble()
            "Foot" to "Meter" -> String.format("%.4f", value / 3.281).toDouble()
            "Foot" to "Millimeter" -> String.format("%.4f", value * 304.8).toDouble()
            "Foot" to "Micrometers" -> String.format("%.4f", value * 304800.0).toDouble()
            "Foot" to "Inch" -> String.format("%.4f", value * 12.0).toDouble()

            "Inch" to "Kilometer" -> String.format("%.8f", value / 39370.1).toDouble()
            "Inch" to "Centimeter" -> String.format("%.4f", value * 2.54).toDouble()
            "Inch" to "Meter" -> String.format("%.4f", value / 39.3701).toDouble()
            "Inch" to "Millimeter" -> String.format("%.4f", value * 25.4).toDouble()
            "Inch" to "Micrometers" -> String.format("%.4f", value * 25400.0).toDouble()
            "Inch" to "Foot" -> String.format("%.4f", value / 12.0).toDouble()

            /* *****************************  Volume conversions  ***************************** */
            "Liter" to "Milliliter" -> String.format("%.4f", value * 1000.0).toDouble()
            "Liter" to "Cubic meter" -> String.format("%.4f", value / 1000.0).toDouble()
            "Milliliter" to "Liter" -> String.format("%.4f", value / 1000.0).toDouble()
            "Milliliter" to "Cubic meter" -> String.format("%.4f", value / 1000000.0).toDouble()
            "Cubic meter" to "Liter" -> String.format("%.4f", value * 1000.0).toDouble()
            "Cubic meter" to "Milliliter" -> String.format("%.4f", value * 1000000.0).toDouble()

            /* *****************************  Weight conversions  ***************************** */
            "Kilogram" to "Gram" -> String.format("%.4f", value * 1000.0).toDouble()
            "Kilogram" to "Milligram" -> String.format("%.4f", value * 1000000.0).toDouble()
            "Kilogram" to "Microgram" -> String.format("%.4f", value * 1000000000.0).toDouble()
            "Kilogram" to "Stone" -> String.format("%.4f", value * 0.157473).toDouble()
            "Kilogram" to "Tonne" -> String.format("%.4f", value * 0.001).toDouble()

            "Gram" to "Kilogram" -> String.format("%.4f", value / 1000.0).toDouble()
            "Gram" to "Milligram" -> String.format("%.4f", value * 1000.0).toDouble()
            "Gram" to "Microgram" -> String.format("%.4f", value * 1000000.0).toDouble()
            "Gram" to "Stone" -> String.format("%.8f", value * 0.000157473).toDouble()
            "Gram" to "Tonne" -> String.format("%.8f", value / 1000000.0).toDouble()

            "Milligram" to "Kilogram" -> String.format("%.8f", value / 1000000.0).toDouble()
            "Milligram" to "Gram" -> String.format("%.4f", value / 1000.0).toDouble()
            "Milligram" to "Microgram" -> String.format("%.4f", value * 1000.0).toDouble()
            "Milligram" to "Stone" -> String.format("%.8f", value / 6350000.0).toDouble()
            "Milligram" to "Tonne" -> String.format("%.10f", value / 1000000000.0).toDouble()

            "Microgram" to "Kilogram" -> value / 1000000000.0
            "Microgram" to "Gram" -> String.format("%.8f", value / 1000000.0).toDouble()
            "Microgram" to "Milligram" -> String.format("%.4f", value / 1000.0).toDouble()
            "Microgram" to "Stone" -> value / 635000000000.0
            "Microgram" to "Tonne" -> value / 1000000000000.0

            "Stone" to "Kilogram" -> String.format("%.8f", value * 6.35029).toDouble()
            "Stone" to "Gram" -> String.format("%.4f", value * 6350.29).toDouble()
            "Stone" to "Milligram" -> String.format("%.4f", value * 635029000.0).toDouble()
            "Stone" to "Microgram" -> String.format("%.4f", value * 635029000000.0).toDouble()
            "Stone" to "Tonne" -> String.format("%.8f", value * 0.000157473).toDouble()

            "Tonne" to "Kilogram" -> String.format("%.4f", value * 1000.0).toDouble()
            "Tonne" to "Gram" -> String.format("%.4f", value * 1000000.0).toDouble()
            "Tonne" to "Milligram" -> String.format("%.4f", value * 1000000000.0).toDouble()
            "Tonne" to "Microgram" -> String.format("%.4f", value * 1000000000000.0).toDouble()
            "Tonne" to "Stone" -> String.format("%.4f", value * 157.473).toDouble()

            /* *****************************  Temperature conversions  ***************************** */
            "Degree Celsius" to "Fahrenheit" -> String.format("%.2f", (value * 9 / 5) + 32).toDouble()
            "Degree Celsius" to "Kelvin" -> String.format("%.2f", value + 273.15).toDouble()
            "Fahrenheit" to "Degree Celsius" -> String.format("%.2f", (value - 32) * 5 / 9).toDouble()
            "Fahrenheit" to "Kelvin" -> String.format("%.2f", (value + 459.67) * 5 / 9).toDouble()
            "Kelvin" to "Degree Celsius" -> String.format("%.2f", value - 273.15).toDouble()
            "Kelvin" to "Fahrenheit" -> String.format("%.2f", (value * 9 / 5) - 459.67).toDouble()

            /* *****************************  Speed conversions  ***************************** */
            "Kilometer per hour" to "Meter per second" -> String.format("%.4f", value / 3.6).toDouble()
            "Kilometer per hour" to "Foot per second" -> String.format("%.6f", value * 0.911344).toDouble()
            "Kilometer per hour" to "Mile per hour" -> String.format("%.4f", value * 0.621371).toDouble()
            "Kilometer per hour" to "knot" -> String.format("%.6f", value / 1.852).toDouble()

            "Meter per second" to "Kilometer per hour" -> String.format("%.4f", value * 3.6).toDouble()
            "Meter per second" to "Foot per second" -> String.format("%.4f", value * 3.28084).toDouble()
            "Meter per second" to "Mile per hour" -> String.format("%.4f", value * 2.23694).toDouble()
            "Meter per second" to "knot" -> String.format("%.6f", value * 1.944).toDouble()

            "Foot per second" to "Kilometer per hour" -> String.format("%.4f", value / 0.911344).toDouble()
            "Foot per second" to "Meter per second" -> String.format("%.4f", value / 3.28084).toDouble()
            "Foot per second" to "Mile per hour" -> String.format("%.4f", value / 1.467).toDouble()
            "Foot per second" to "knot" -> String.format("%.6f", value / 1.688).toDouble()

            "Mile per hour" to "Kilometer per hour" -> String.format("%.4f", value / 0.621371).toDouble()
            "Mile per hour" to "Meter per second" -> String.format("%.4f", value / 2.23694).toDouble()
            "Mile per hour" to "Foot per second" -> String.format("%.4f", value * 1.467).toDouble()
            "Mile per hour" to "knot" -> String.format("%.6f", value / 1.151).toDouble()
            "knot" to "Kilometer per hour" -> String.format("%.6f", value * 1.852).toDouble()
            "knot" to "Meter per second" -> String.format("%.4f", value / 1.94384).toDouble()
            "knot" to "Foot per second" -> String.format("%.6f", value * 1.688).toDouble()
            "knot" to "Mile per hour" -> String.format("%.4f", value * 1.15078).toDouble()

            /* *****************************  Energy conversions  ***************************** */
            "Joule" to "Kilojoule" -> String.format("%.4f", value / 1000.0).toDouble()
            "Joule" to "Gram calorie" -> String.format("%.4f", value * 0.239006).toDouble()
            "Joule" to "Kilo calorie" -> String.format("%.4f", value / 4184.0).toDouble()
            "Joule" to "Watt hour" -> String.format("%.10f", value / 3600000.0).toDouble()
            "Joule" to "Kilowatt hour" -> String.format("%.10f", value / 3.6e+6).toDouble()
            "Joule" to "Electron volt" -> String.format("%.4f", value / 1.60218e-19).toDouble()
            "Joule" to "Foot-pound" -> String.format("%.4f", value / 1.35582).toDouble()

            "Kilojoule" to "Joule" -> String.format("%.4f", value * 1000.0).toDouble()
            "Kilojoule" to "Gram calorie" -> String.format("%.4f", value * 239.006).toDouble()
            "Kilojoule" to "Kilo calorie" -> String.format("%.8f", value / 4.184).toDouble()
            "Kilojoule" to "Watt hour" -> String.format("%.4f", value / 3.6).toDouble()
            "Kilojoule" to "Kilowatt hour" -> String.format("%.8f", value / 3600).toDouble()
            "Kilojoule" to "Electron volt" -> String.format("%.4f", value / 1.60218e-19).toDouble()
            "Kilojoule" to "Foot-pound" -> String.format("%.4f", value * 737.6).toDouble()

            "Gram calorie" to "Joule" -> String.format("%.4f", value / 0.239006).toDouble()
            "Gram calorie" to "Kilo calorie" -> String.format("%.4f", value / 1000).toDouble()
            "Gram calorie" to "Watt hour" -> String.format("%.6f", value / 860.4).toDouble()
            "Gram calorie" to "Kilowatt hour" -> String.format("%.8f", value / 860400).toDouble()
            "Gram calorie" to "Electron volt" -> String.format("%.4f", value * 2.611e+19).toDouble()
            "Gram calorie" to "Foot-pound" -> String.format("%.4f", value * 3.086).toDouble()
            "Gram calorie" to "Kilojoule" -> String.format("%.4f", value * 0.004184).toDouble()

            "Kilo calorie" to "Joule" -> String.format("%.4f", value * 4184.0).toDouble()
            "Kilo calorie" to "Gram calorie" -> String.format("%.4f", value * 1000).toDouble()
            "Kilo calorie" to "Kilojoule" -> String.format("%.4f", value * 4.184).toDouble()
            "Kilo calorie" to "Watt hour" -> String.format("%.8f", value * 1.162).toDouble()
            "Kilo calorie" to "Kilowatt hour" -> String.format("%.8f", value / 860.4).toDouble()
            "Kilo calorie" to "Electron volt" -> String.format("%.4f", value * 2.611e+22).toDouble()
            "Kilo calorie" to "Foot-pound" -> String.format("%.4f", value * 3086).toDouble()

            "Watt hour" to "Kilowatt hour" -> String.format("%.4f", value / 1000.0).toDouble()
            "Watt hour" to "Electron volt" -> String.format("%.4f", value * 2.247e+22).toDouble()
            "Watt hour" to "Joule" -> String.format("%.4f", value * 3600).toDouble()
            "Watt hour" to "Kilojoule" -> String.format("%.4f", value * 3.6).toDouble()
            "Watt hour" to "Gram calorie" -> String.format("%.4f", value * 860.4).toDouble()
            "Watt hour" to "Kilo calorie" -> String.format("%.4f", value / 1.162).toDouble()
            "Watt hour" to "Foot-pound" -> String.format("%.4f", value * 2655.22).toDouble()

            "Kilowatt hour" to "Joule" -> String.format("%.4f", value * 3600000000.0).toDouble()
            "Kilowatt hour" to "Kilojoule" -> String.format("%.4f", value * 3600).toDouble()
            "Kilowatt hour" to "Gram calorie" -> String.format("%.4f", value * 860421).toDouble()
            "Kilowatt hour" to "Kilo calorie" -> String.format("%.4f", value * 860.421).toDouble()
            "Kilowatt hour" to "Watt hour" -> String.format("%.4f", value * 1000.0).toDouble()
            "Kilowatt hour" to "Electron volt" -> String.format("%.4f", value * 2.247e+25).toDouble()
            "Kilowatt hour" to "Foot-pound" -> String.format("%.4f", value * 2.655e+6).toDouble()

            "Electron volt" to "Watt hour" -> value / 2.247e+22
            "Electron volt" to "Kilowatt hour" -> value / 2.247e+25
            "Electron volt" to "Joule" -> value / 6.242e+18
            "Electron volt" to "Kilojoule" -> value / 6.241999999999999E21
            "Electron volt" to "Gram calorie" -> value / 2.611e+19
            "Electron volt" to "Kilo calorie" -> value / 2.611e+22
            "Electron volt" to "Foot-pound" -> value / 8.462e+18

            "Foot-pound" to "Joule" -> String.format("%.4f", value * 1.35582).toDouble()
            "Foot-pound" to "Kilojoule" -> String.format("%.4f", value * 0.0135582).toDouble()
            "Foot-pound" to "Gram calorie" -> String.format("%.8f", value / 3.086).toDouble()
            "Foot-pound" to "Kilo calorie" -> String.format("%.8f", value / 3086).toDouble()
            "Foot-pound" to "Watt hour" -> String.format("%.8f", value / 2655).toDouble()
            "Foot-pound" to "Kilowatt hour" -> String.format("%.12f", value / 2.655e+6).toDouble()
            "Foot-pound" to "Electron volt" -> String.format("%.4f", value * 8.462e+18).toDouble()

            /* *****************************  Time conversions  ***************************** */
            "Nanosecond" to "Microsecond" -> String.format("%.4f", value / 1000.0).toDouble()
            "Nanosecond" to "Millisecond" -> value / 1000000.0
            "Nanosecond" to "second" -> value / 1000000000.0
            "Nanosecond" to "Hour" -> value / 3600000000000.0
            "Nanosecond" to "Minute" -> value / 60000000000.0
            "Nanosecond" to "Day" -> value / 86400000000000.0
            "Nanosecond" to "Week" -> value / 604800000000000.0
            "Nanosecond" to "Month" -> value / 2592000000000000.0
            "Nanosecond" to "Year" -> value / 3153600000000000.0
            "Nanosecond" to "Decade" -> value / 31536000000000000.0
            "Nanosecond" to "Century" -> value / 315360000000000000.0

            "Microsecond" to "Nanosecond" -> String.format("%.4f", value * 1000.0).toDouble()
            "Microsecond" to "Millisecond" -> String.format("%.4f", value / 1000.0).toDouble()
            "Microsecond" to "second" -> value / 1000000.0
            "Microsecond" to "Hour" -> value / 3600000000.0
            "Microsecond" to "Minute" -> value / 60000000.0
            "Microsecond" to "Day" -> value / 86400000000.0
            "Microsecond" to "Week" -> value / 6048000000000.0
            "Microsecond" to "Month" -> -value / 25920000000000.0
            "Microsecond" to "Year" -> value / 315360000000000.0
            "Microsecond" to "Decade" -> value / 3153600000000000.0
            "Microsecond" to "Century" -> value / 31536000000000000.0

            "Millisecond" to "Nanosecond" -> String.format("%.4f", value * 1000000.0).toDouble()
            "Millisecond" to "Microsecond" -> String.format("%.4f", value * 1000.0).toDouble()
            "Millisecond" to "second" -> String.format("%.4f", value / 1000.0).toDouble()
            "Millisecond" to "Minute" -> value / 60000.0
            "Millisecond" to "Hour" -> value / 3600000.0
            "Millisecond" to "Day" -> value / 86400000.0
            "Millisecond" to "Week" -> value / 6048000000.0
            "Millisecond" to "Month" -> value / 25920000000.0
            "Millisecond" to "Year" -> value / 315360000000.0
            "Millisecond" to "Decade" -> value / 3153600000000.0
            "Millisecond" to "Century" -> value / 31536000000000.0

            "second" to "Nanosecond" -> String.format("%.4f", value * 1000000000.0).toDouble()
            "second" to "Microsecond" -> String.format("%.4f", value * 1000000.0).toDouble()
            "second" to "Millisecond" -> String.format("%.4f", value * 1000.0).toDouble()
            "second" to "Hour" -> String.format("%.4f", value / 3600.0).toDouble()
            "second" to "Minute" -> String.format("%.4f", value / 60.0).toDouble()
            "second" to "Day" -> value / 86400.0
            "second" to "Week" -> value / 604800.0
            "second" to "Month" -> value / 2592000.0
            "second" to "Year" -> value / 31536000.0
            "second" to "Decade" -> value / 315360000.0
            "second" to "Century" -> value / 3153600000.0

            "Hour" to "Nanosecond" -> String.format("%.4f", value * 3600000000000.0).toDouble()
            "Hour" to "Microsecond" -> String.format("%.4f", value * 3600000000.0).toDouble()
            "Hour" to "Millisecond" -> String.format("%.4f", value * 3600000.0).toDouble()
            "Hour" to "second" -> String.format("%.4f", value * 3600.0).toDouble()
            "Hour" to "Minute" -> String.format("%.4f", value * 60.0).toDouble()
            "Hour" to "Day" -> String.format("%.4f", value / 24.0).toDouble()
            "Hour" to "Week" -> String.format("%.4f", value / 168.0).toDouble()
            "Hour" to "Month" -> String.format("%.4f", value / 730.0).toDouble()
            "Hour" to "Year" -> value / 8760.0
            "Hour" to "Decade" -> value / 87600.0
            "Hour" to "Century" -> value / 876000.0

            "Minute" to "Nanosecond" -> String.format("%.4f", value * 60000000000.0).toDouble()
            "Minute" to "Microsecond" -> String.format("%.4f", value * 60000000.0).toDouble()
            "Minute" to "Millisecond" -> String.format("%.4f", value * 60000.0).toDouble()
            "Minute" to "second" -> String.format("%.4f", value * 60.0).toDouble()
            "Minute" to "Hour" -> String.format("%.4f", value / 60.0).toDouble()
            "Minute" to "Day" -> String.format("%.4f", value / 1440.0).toDouble()
            "Minute" to "Week" -> String.format("%.4f", value / 10080.0).toDouble()
            "Minute" to "Month" -> value / 43800.0
            "Minute" to "Year" -> value / 525600.0
            "Minute" to "Decade" -> value / 5256000.0
            "Minute" to "Century" -> value / 52560000.0

            "Day" to "Nanosecond" -> String.format("%.4f", value * 86400000000000.0).toDouble()
            "Day" to "Microsecond" -> String.format("%.4f", value * 86400000000.0).toDouble()
            "Day" to "Millisecond" -> String.format("%.4f", value * 86400000.0).toDouble()
            "Day" to "second" -> String.format("%.4f", value * 86400.0).toDouble()
            "Day" to "Hour" -> String.format("%.4f", value * 24.0).toDouble()
            "Day" to "Minute" -> String.format("%.4f", value * 1440.0).toDouble()
            "Day" to "Week" -> String.format("%.4f", value / 7.0).toDouble()
            "Day" to "Month" -> String.format("%.4f", value / 30.0).toDouble()
            "Day" to "Year" -> String.format("%.4f", value / 365.0).toDouble()
            "Day" to "Decade" -> String.format("%.4f", value / 3650.0).toDouble()
            "Day" to "Century" -> value / 36500.0

            "Week" to "Nanosecond" -> String.format("%.4f", value * 604800000000000.0).toDouble()
            "Week" to "Microsecond" -> String.format("%.4f", value * 60480000000.0).toDouble()
            "Week" to "Millisecond" -> String.format("%.4f", value * 604800000.0).toDouble()
            "Week" to "second" -> String.format("%.4f", value * 604800.0).toDouble()
            "Week" to "Hour" -> String.format("%.4f", value * 168.0).toDouble()
            "Week" to "Minute" -> String.format("%.4f", value * 10080.0).toDouble()
            "Week" to "Day" -> String.format("%.4f", value * 7.0).toDouble()
            "Week" to "Month" -> String.format("%.4f", value / 4.0).toDouble()
            "Week" to "Year" -> String.format("%.4f", value / 52.0).toDouble()
            "Week" to "Decade" -> String.format("%.4f", value / 520.0).toDouble()
            "Week" to "Century" -> String.format("%.4f", value / 5200.0).toDouble()

            "Month" to "Nanosecond" -> String.format("%.4f", value * 2592000000000000.0).toDouble()
            "Month" to "Microsecond" -> String.format("%.4f", value * 25920000000.0).toDouble()
            "Month" to "Millisecond" -> String.format("%.4f", value * 259200000.0).toDouble()
            "Month" to "second" -> String.format("%.4f", value * 2592000.0).toDouble()
            "Month" to "Hour" -> String.format("%.4f", value * 730.0).toDouble()
            "Month" to "Minute" -> String.format("%.4f", value * 43800.0).toDouble()
            "Month" to "Day" -> String.format("%.8f", value * 30.0).toDouble()
            "Month" to "Week" -> String.format("%.8f", value * 4.0).toDouble()
            "Month" to "Year" -> String.format("%.8f", value / 12.0).toDouble()
            "Month" to "Decade" -> String.format("%.8f", value / 120.0).toDouble()
            "Month" to "Century" -> String.format("%.8f", value / 1200.0).toDouble()

            "Year" to "Nanosecond" -> String.format("%.4f", value * 31536000000000000.0).toDouble()
            "Year" to "Microsecond" -> String.format("%.4f", value * 31536000000000.0).toDouble()
            "Year" to "Millisecond" -> String.format("%.4f", value * 315360000000.0).toDouble()
            "Year" to "second" -> String.format("%.4f", value * 31536000.0).toDouble()
            "Year" to "Hour" -> String.format("%.4f", value * 8760.0).toDouble()
            "Year" to "Minute" -> String.format("%.4f", value * 525600.0).toDouble()
            "Year" to "Day" -> String.format("%.4f", value * 365.0).toDouble()
            "Year" to "Week" -> String.format("%.4f", value * 52.0).toDouble()
            "Year" to "Month" -> String.format("%.4f", value * 12.0).toDouble()
            "Year" to "Decade" -> String.format("%.4f", value / 10.0).toDouble()
            "Year" to "Century" -> String.format("%.4f", value / 100.0).toDouble()

            "Decade" to "Nanosecond" -> String.format("%.4f", value * 315360000000000000.0).toDouble()
            "Decade" to "Microsecond" -> String.format("%.4f", value * 3153600000000000.0).toDouble()
            "Decade" to "Millisecond" -> String.format("%.4f", value * 315360000000000.0).toDouble()
            "Decade" to "second" -> String.format("%.4f", value * 3153600000.0).toDouble()
            "Decade" to "Hour" -> String.format("%.4f", value * 87600.0).toDouble()
            "Decade" to "Minute" -> String.format("%.4f", value * 5256000.0).toDouble()
            "Decade" to "Day" -> String.format("%.4f", value * 3650.0).toDouble()
            "Decade" to "Week" -> String.format("%.4f", value * 520.0).toDouble()
            "Decade" to "Month" -> String.format("%.4f", value * 120.0).toDouble()
            "Decade" to "Year" -> String.format("%.4f", value * 10.0).toDouble()
            "Decade" to "Century" -> String.format("%.4f", value / 10.0).toDouble()

            "Century" to "Nanosecond" -> String.format("%.4f", value * 3153600000000000000.0).toDouble()
            "Century" to "Microsecond" -> String.format("%.4f", value * 31536000000000000.0).toDouble()
            "Century" to "Millisecond" -> String.format("%.4f", value * 3153600000000000.0).toDouble()
            "Century" to "second" -> String.format("%.4f", value * 31536000000.0).toDouble()
            "Century" to "Hour" -> String.format("%.4f", value * 876000.0).toDouble()
            "Century" to "Minute" -> String.format("%.4f", value * 52560000.0).toDouble()
            "Century" to "Day" -> String.format("%.4f", value * 36500.0).toDouble()
            "Century" to "Week" -> String.format("%.4f", value * 5200.0).toDouble()
            "Century" to "Month" -> String.format("%.4f", value * 1200.0).toDouble()
            "Century" to "Year" -> String.format("%.4f", value * 100.0).toDouble()
            "Century" to "Decade" -> String.format("%.4f", value * 10.0).toDouble()

            /* *****************************  Area conversions  ***************************** */
            "Square Kilometer" to "Square Meter" -> String.format("%.4f", value * 1000000.0).toDouble()

            "Square Kilometer" to "Square Mile" -> String.format("%.4f", value * 0.386).toDouble()
            "Square Kilometer" to "Square Foot" -> String.format("%.4f", value * 10760000.0).toDouble()

            "Square Kilometer" to "Square Inch" -> String.format("%.4f", value * 1.55e+7).toDouble()
            "Square Kilometer" to "Hectare" -> String.format("%.4f", value * 100.0).toDouble()
            "Square Kilometer" to "Acre" -> String.format("%.4f", value / 247.1).toDouble()
            "Square Kilometer" to "Square yard" -> String.format("%.4f", value * 11959900.0).toDouble()

            "Square Meter" to "Square Kilometer" -> String.format("%.8f", value / 1000000.0).toDouble()

            "Square Meter" to "Square Mile" -> String.format("%.8f", value / 2590000.0).toDouble()
            "Square Meter" to "Square Foot" -> String.format("%.4f", value * 10.7639).toDouble()
            "Square Meter" to "Square Inch" -> String.format("%.4f", value * 1550).toDouble()
            "Square Meter" to "Hectare" -> String.format("%.4f", value / 10000.0).toDouble()
            "Square Meter" to "Acre" -> String.format("%.4f", value / 4046.86).toDouble()
            "Square Meter" to "Square yard" -> String.format("%.4f", value * 1.19599).toDouble()

            "Square Mile" to "Square Kilometer" -> String.format("%.4f", value * 2.59).toDouble()
            "Square Mile" to "Square Meter" -> String.format("%.4f", value * 2590000.0).toDouble()
            "Square Mile" to "Square Foot" -> String.format("%.4f", value * 2787840.0).toDouble()
            "Square Mile" to "Square Inch" -> String.format("%.4f", value * 4.014e+9).toDouble()
            "Square Mile" to "Hectare" -> String.format("%.4f", value * 259.099).toDouble()
            "Square Mile" to "Acre" -> String.format("%.4f", value * 640.0).toDouble()
            "Square Mile" to "Square yard" -> String.format("%.4f", value * 3097600.0).toDouble()

            "Square Foot" to "Square Kilometer" -> value / 10760000.0
            "Square Foot" to "Square Meter" -> String.format("%.4f", value / 10.7639).toDouble()
            "Square Foot" to "Square Mile" -> String.format("%.8f", value / 2787840.0).toDouble()
            "Square Foot" to "Square Inch" -> String.format("%.4f", value * 144.0).toDouble()
            "Square Foot" to "Hectare" -> value / 10760.0
            "Square Foot" to "Acre" -> value / 43560.0
            "Square Foot" to "Square yard" -> String.format("%.4f", value * 0.111111).toDouble()

            "Square Inch" to "Square Kilometer" -> value / 1.55e+7
            "Square Inch" to "Square Meter" -> String.format("%.8f", value / 1550.003).toDouble()
            "Square Inch" to "Square Mile" -> value / 64516.0
            "Square Inch" to "Square Foot" -> String.format("%.4f", value / 144.0).toDouble()
            "Square Inch" to "Hectare" -> value / 1550000.0
            "Square Inch" to "Acre" -> value / 6272640.0
            "Square Inch" to "Square yard" -> String.format("%.4f", value / 1296.0).toDouble()

            "Hectare" to "Square Kilometer" -> String.format("%.4f", value / 100.0).toDouble()
            "Hectare" to "Square Meter" -> String.format("%.4f", value * 10000.0).toDouble()
            "Hectare" to "Square Mile" -> String.format("%.4f", value / 259.099).toDouble()
            "Hectare" to "Square Foot" -> String.format("%.4f", value * 10760.0).toDouble()
            "Hectare" to "Square Inch" -> String.format("%.4f", value * 1550000.0).toDouble()
            "Hectare" to "Acre" -> String.format("%.4f", value * 2.471).toDouble()
            "Hectare" to "Square yard" -> String.format("%.4f", value * 1195990.0).toDouble()

            "Acre" to "Square Kilometer" -> String.format("%.4f", value / 247.1).toDouble()
            "Acre" to "Square Meter" -> String.format("%.4f", value * 4046.86).toDouble()
            "Acre" to "Square Mile" -> String.format("%.4f", value / 640.0).toDouble()
            "Acre" to "Square Foot" -> String.format("%.4f", value * 43560.0).toDouble()
            "Acre" to "Square Inch" -> String.format("%.4f", value * 6272640.0).toDouble()
            "Acre" to "Hectare" -> String.format("%.4f", value / 2.471).toDouble()
            "Acre" to "Square yard" -> String.format("%.4f", value * 4840.0).toDouble()

            "Square yard" to "Square Kilometer" -> value / 11959900.0
            "Square yard" to "Square Meter" -> String.format("%.4f", value / 1.19599).toDouble()
            "Square yard" to "Square Mile" -> value / 3097600.0
            "Square yard" to "Square Foot" -> String.format("%.4f", value / 0.111111).toDouble()
            "Square yard" to "Square Inch" -> String.format("%.4f", value * 1296.0).toDouble()
            "Square yard" to "Hectare" -> value / 1195990.0
            "Square yard" to "Acre" -> String.format("%.4f", value / 4840.0).toDouble()

            /* *****************************  Storage conversions  ***************************** */
            "Byte" to "Kilobyte" -> value / 1024.0
            "Byte" to "Megabyte" -> value / (1024 * 1024)
            "Byte" to "Terabyte" -> value / (1024 * 1024 * 1024)
            "Byte" to "Gigabyte" -> value / (1024 * 1024 * 1024 * 1024)

            "Kilobyte" to "Byte" -> value * 1024.0
            "Kilobyte" to "Megabyte" -> value / 1024.0
            "Kilobyte" to "Gigabyte" -> value / (1024 * 1024)
            "Kilobyte" to "Terabyte" -> value / (1024 * 1024 * 1024)


            "Megabyte" to "Kilobyte" -> value * 1024.0
            "Megabyte" to "Gigabyte" -> value / 1024.0
            "Megabyte" to "Byte" -> value * 1024 * 1024
            "Megabyte" to "Terabyte" -> value / (1024 * 1024)

            "Gigabyte" to "Megabyte" -> value * 1024.0
            "Gigabyte" to "Terabyte" -> value / 1024.0
            "Gigabyte" to "Byte" -> value * 1024 * 1024 * 1024
            "Gigabyte" to "Kilobyte" -> value * (1024 * 1024)

            "Terabyte" to "Gigabyte" -> value * 1024.0
            "Terabyte" to "Byte" -> value * 1024 * 1024 * 1024 * 1024
            "Terabyte" to "Megabyte" -> value * (1024 * 1024)
            "Terabyte" to "Kilobyte" -> value * (1024 * 1024 * 1024)

            /* *****************************    conversions  ***************************** */
            "Watt" to "Kilowatt" -> value / 1000.0
            "Watt" to "Megawatt" -> value / 1000000.0
            "Watt" to "Gigawatt" -> value / 1000000000.0
            "Watt" to "Terawatt" -> value / 1000000000000.0

            "Kilowatt" to "Watt" -> value * 1000.0
            "Kilowatt" to "Megawatt" -> value / 1000.0
            "Kilowatt" to "Gigawatt" -> value / 1000000.0
            "Kilowatt" to "Terawatt" -> value / 1000000000.0

            "Megawatt" to "Kilowatt" -> value * 1000.0
            "Megawatt" to "Watt" -> value * 1000000.0
            "Megawatt" to "Gigawatt" -> value / 1000.0
            "Megawatt" to "Terawatt" -> value / 1000000.0

            "Gigawatt" to "Megawatt" -> value * 1000.0
            "Gigawatt" to "Kilowatt" -> value * 1000000.0
            "Gigawatt" to "Watt" -> value * 1000000000.0
            "Gigawatt" to "Terawatt" -> value / 1000.0

            "Terawatt" to "Kilowatt" -> value * 1000000000.0
            "Terawatt" to "Megawatt" -> value * 1000000.0
            "Terawatt" to "Watt" -> value * 1000000000000.0
            "Terawatt" to "Gigawatt" -> value * 1000.0


            else -> {
                value
            }
        }

    }
}
