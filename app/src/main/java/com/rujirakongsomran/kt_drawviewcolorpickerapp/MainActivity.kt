package com.rujirakongsomran.kt_drawviewcolorpickerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.rujirakongsomran.kt_drawviewcolorpickerapp.databinding.ActivityMainBinding
import petrov.kristiyan.colorpicker.ColorPicker

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        drawingButtonSetup()

    }

    private fun drawingButtonSetup() {
        binding.fabColorPicker.setOnClickListener {
            val colorPicker = ColorPicker(this)
            colorPicker.setOnFastChooseColorListener(object :
                ColorPicker.OnFastChooseColorListener {
                override fun setOnFastChooseColorListener(position: Int, color: Int) {
                    binding.dvDrawView.setColor(color)
                }

                override fun onCancel() {
                    colorPicker.dismissDialog()
                }

            })
                .disableDefaultButtons(true)
                .setColumns(5)
                .setRoundColorButton(true)
                .show()
        }

        binding.fabUndo.setOnClickListener {
            binding.dvDrawView.undo()

            Snackbar.make(it, "Undo Successful", Snackbar.LENGTH_SHORT)
                .setAction("Redo") {
                    binding.dvDrawView.undo()
                }.show()
        }
    }
}