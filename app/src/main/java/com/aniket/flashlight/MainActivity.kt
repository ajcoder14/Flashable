package com.aniket.flashlight

import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aniket.flashlight.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //here we bind with xml file
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        //here we apply btn listener and change flash light condition
        binding!!.btnFlash.setOnClickListener {
            if (binding!!.btnFlash.text.toString() == "Turn On") {
                binding!!.btnFlash.text = "Turn Off"
                binding!!.imgFlash.setImageResource(R.drawable.flashlight_on)
                changeLightState(true)
            } else {
                binding!!.btnFlash.text = "Turn On"
                binding!!.imgFlash.setImageResource(R.drawable.flashlight_off)
                changeLightState(false)
            }
        }
    }

    //it is interact with camera system and change its state according to condition
    private fun changeLightState(state: Boolean) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            val cameraManager = getSystemService(CAMERA_SERVICE) as CameraManager
            var camId: String? = null
            try {
                camId = cameraManager.cameraIdList[0]
                cameraManager.setTorchMode(camId, state)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        binding!!.btnFlash.text = "Turn On"

        if (binding!!.btnFlash.text.toString() == "Turn Off") {
            binding!!.btnFlash.text = "Turn On"
            binding!!.imgFlash.setImageResource(R.drawable.flashlight_off)
            changeLightState(false)
        }
    }
}