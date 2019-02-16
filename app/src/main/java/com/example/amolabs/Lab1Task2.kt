package com.example.amolabs

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_lab1_task2.*
import kotlinx.android.synthetic.main.content_lab1_task2.*
import java.lang.NumberFormatException
import kotlin.math.abs
import kotlin.math.pow

class Lab1Task2 : AppCompatActivity() {

    private fun alertView(message: String) {
        val builder = AlertDialog.Builder(this@Lab1Task2)
        builder.setTitle("Information")
        builder.setMessage(message)
        builder.setPositiveButton("YES") { _, _ ->}
        val dialog = builder.create()
        dialog.show()
    }

    private fun calculateResult(k: Double, a: Double, c:Double): Double{
        return if (k < 10){
            (a + c).pow(4) + (a - c).pow(2)
        }else{
            (a - c).pow(3) + (a + c).pow(2)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab1_task2)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val formK = editText4
        val formA = editText5
        val formC = editText7
        
        var paramK: Double
        var paramA: Double
        var paramC: Double

        button2.setOnClickListener {
            try {
                paramK = formK.text.toString().toDouble()
                paramA = formA.text.toString().toDouble()
                paramC = formC.text.toString().toDouble()
            }catch (e:NumberFormatException){
                alertView("Некорректний формат вводу")
                return@setOnClickListener
            }
            val result = calculateResult(paramK, paramA, paramC)
            if (!result.equals(Double.NaN) && !abs(result).equals(Double.POSITIVE_INFINITY)){
                alertView("Результат обчислень: %.2f".format(result))
            }else{
                alertView("Виникла помилка при обрахуванні результату, можливо дану формулу неможливо обчислити з наданими даними")
            }
        }


    }

}
