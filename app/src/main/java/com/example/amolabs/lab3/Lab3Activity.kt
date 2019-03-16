package com.example.amolabs.lab3

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import com.example.amolabs.R
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

import kotlinx.android.synthetic.main.activity_lab3.*
import kotlinx.android.synthetic.main.content_lab3.*
import java.lang.NumberFormatException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.cos
import kotlin.math.pow

/**
    calculate function from the task
 */
fun taskFunction(x: Double): Double{
    return cos(x + x.pow(cos(x)))
}

/**
 * calculate base values (no interpolation, only formula)
 * @param start x of first dot
 * @param end x of last dot
 * @param dotAmount amount of dots in base data
 */
fun getBaseVals(start: Double, end: Double, dotAmount: Int): ArrayList<Entry>{
    val lst = ArrayList<Entry>()
    var x:Double
    var y:Double
    for (i in 1..dotAmount){
        x = start + (end - start) / dotAmount * i
        y = taskFunction(x)
        lst.add(Entry(x.toFloat(), y.toFloat()))
    }
    return lst
}

class Lab3Activity : AppCompatActivity() {

    private fun alertView(message: String) {
        val builder = AlertDialog.Builder(this@Lab3Activity)
        builder.setTitle("Повідомлення")
        builder.setMessage(message)
        builder.setPositiveButton("YES") { _, _ ->}
        val dialog = builder.create()
        dialog.show()
    }

    private fun entryListToString(arr: ArrayList<Entry>): String{
        var res = "Значення:\n"
        for (i: Entry in arr){
            res += "(%.2f , %.2f)\n".format(i.x, i.y)
        }
        res += "\n\n\n"
        return res
    }

    private fun interpolate(){

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab3)
        setSupportActionBar(toolbar)
        lagrange.toggle()
        val chart = chart
        chart.isDragXEnabled = true
        chart.isDragYEnabled = true
        chart.isScaleYEnabled = false
        chart.isScaleXEnabled = true

        var entries = ArrayList<Entry>()


        base_vals_lab3.setOnClickListener{
            try {
                entries = getBaseVals(
                    rangeStart.text.toString().toDouble(),
                    rangeEnd.text.toString().toDouble(),
                    dotAmount.text.toString().toInt())
            }catch (e: NumberFormatException){
                alertView("Некорректний формат введених номерів")
            }
            if (entries.isEmpty()) return@setOnClickListener
            val dataSet = LineDataSet(entries, "Values") // add entries to dataset
            dataSet.color = Color.CYAN
            val lineData = LineData(dataSet)
            chart.data = lineData
            textView11.text = entryListToString(entries)
            chart.invalidate()
        }

        interpolate_lab3.setOnClickListener {interpolate()}
    }

}
