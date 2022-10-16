package com.martinbg.androidvisuals

import android.content.res.Configuration
import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import com.martinbg.androidvisuals.databinding.ActivityMainBinding
import java.io.File
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {

    private val images = arrayListOf(
        R.drawable.ic_baseline_alarm_24,
        R.drawable.ic_baseline_attach_money_24,
        R.drawable.ic_baseline_brush_24,
        R.drawable.ic_baseline_build_24,
        R.drawable.ic_baseline_cake_24,
        R.drawable.ic_baseline_call_24,
        R.drawable.ic_baseline_coffee_24,
        R.drawable.ic_baseline_connected_tv_24,
        R.drawable.ic_baseline_cloud_upload_24,
        R.drawable.ic_baseline_local_airport_24,
        R.drawable.ic_baseline_local_gas_station_24,
        R.drawable.ic_baseline_local_taxi_24
    )
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        binding.textView.bringToFront()
        setRandomImage()

        binding.button.setOnClickListener {
            setRandomImage()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        when (newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> binding.imageView.maxHeight = dpToPx(100)
            else -> binding.imageView.maxHeight = dpToPx(150)
        }
    }

    private fun setRandomImage() {
        val resourceId = getRandomResourceId()

        binding.imageView.setImageResource(resourceId)

        val resourceName = resources.getResourceEntryName(resourceId)

        val value = TypedValue()
        resources.getValue(resourceId, value, true)
        val filename = File(value.string.toString()).name

        binding.resourceData =
            getString(R.string.file_name, resourceName, System.lineSeparator(), filename)
    }

    private fun dpToPx(dp: Int): Int {
        val density: Float = resources.displayMetrics.density
        return (dp.toFloat() * density).roundToInt()
    }

    private fun getRandomResourceId(): Int {
        val randomIndex = (0 until images.size).random()
        return images[randomIndex]
    }
}