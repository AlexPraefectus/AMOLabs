package com.example.amolabs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_lab1_task1.*
import kotlinx.android.synthetic.main.content_lab1_task1.*
import android.support.v7.app.AlertDialog
import kotlin.math.sqrt


class Lab1Task1 : AppCompatActivity() {

    private fun alertView(message: String) {
        val builder = AlertDialog.Builder(this@Lab1Task1)
        builder.setTitle("Information")
        builder.setMessage(message)
        builder.setPositiveButton("YES") { _, _ ->}
        val dialog = builder.create()
        dialog.show()
    }

    private fun calculateResult(paramA: Double, paramB:Double, paramC: Double, paramD:Double): Double{
        return (sqrt(paramA) + paramB * paramB)/
                (sqrt(paramB) - paramA * paramA) +
                (sqrt(paramA * paramB / paramC / paramD))

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab1_task1)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val a = editText  // short bindings to input fields
        val b = editText2
        val c = editText3
        val d = editText6
        button.setOnClickListener{
            if (a.text.isNotEmpty() && b.text.isNotEmpty() && c.text.isNotEmpty() && d.text.isNotEmpty()){ // all params required
                var paramA: Double = Double.NaN
                var paramB: Double = Double.NaN
                var paramC: Double = Double.NaN
                var paramD: Double = Double.NaN
                try{
                    paramA = a.text.toString().toDouble()
                    paramB = b.text.toString().toDouble()
                    paramC = c.text.toString().toDouble()
                    paramD = d.text.toString().toDouble()
                }
                catch (e:NumberFormatException){
                    alertView("Can't convert to your input to numbers")
                }
                val result = calculateResult(paramA, paramB, paramC, paramD)
                if (result.equals(Double.NaN) || result.equals(Double.POSITIVE_INFINITY) || result.equals(Double.NEGATIVE_INFINITY)){
                    alertView("Wrong parameters, check Your input")
                } else alertView("Result is %.2f".format(result))
            }
        }
    }

}
