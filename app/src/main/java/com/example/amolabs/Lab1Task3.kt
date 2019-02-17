package com.example.amolabs

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_lab1_task3.*
import kotlinx.android.synthetic.main.content_lab1_task3.*
import java.lang.NumberFormatException
import kotlin.math.abs
import kotlin.math.sqrt

class Lab1Task3 : AppCompatActivity() {

    private fun calculateResult(p:Int, a: Double, b:Double): Double{
        var sum = 0.0
        var sumInner1 = 0.0
        var sumInner2 = 0.0
        for (i in 1 .. p){
            sumInner1 = 0.0
            for (j in 1 .. p){
                sumInner2 = 0.0
                for (k in 1 .. p){
                    sumInner2 += (i * j * k)
                }
                sumInner1 += (i * j * sumInner2)
                print("Inner2 $sumInner2 Inner1: $sumInner1\n")
            }
            sum += i * sumInner1
            print("Inner1: $sumInner1\n")
        }
        sum *= sqrt(a + b)
        return sum
    }

    private fun alertView(message: String) {
        val builder = AlertDialog.Builder(this@Lab1Task3)
        builder.setTitle("Information")
        builder.setMessage(message)
        builder.setPositiveButton("YES") { _, _ ->}
        val dialog = builder.create()
        dialog.show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab1_task3)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        button3.setOnClickListener{
            var a:Double
            var b:Double
            var p:Int
            try{
                a = l1t3pA.text.toString().toDouble()
                b = l1t3pB.text.toString().toDouble()
                p = l1t3pP.text.toString().toInt()
            }catch (e:NumberFormatException){
                alertView("Неправильний формат введеного номеру")
                return@setOnClickListener
            }
            if (p < 1){
                alertView("P повинно бути більше або дорівнювати 1")
                return@setOnClickListener
            }
            if ((a + b) < 0){
                alertView("Сума a та b повинна бути більшою за 0")
                return@setOnClickListener
            }
            val result = calculateResult(p, a, b)
            if (!result.equals(Double.NaN) && !abs(result).equals(Double.POSITIVE_INFINITY)){
                alertView("Результат: %.2f".format(result))
            }else{
                alertView("Неможливо обрахувати вираз для наданих значень")
            }
        }
    }

}
