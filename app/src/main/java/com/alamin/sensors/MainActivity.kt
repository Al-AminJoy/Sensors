package com.alamin.sensors

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alamin.sensors.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        val sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        sensorManager?.let {
            val accelerometerSensor = it.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

            accelerometerSensor?.let {
                sensorManager.registerListener(object : SensorEventListener {
                    override fun onSensorChanged(event: SensorEvent) {
                        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER){
                            binding.txtAccelerometer.text = "X- Axis : ${event.values[0]} \nY- Axis : ${event.values[1]} \nZ- Axis : ${event.values[2]} \n"
                        }
                    }

                    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
                    }

                },it,SensorManager.SENSOR_DELAY_NORMAL)
            }
        }
    }
}