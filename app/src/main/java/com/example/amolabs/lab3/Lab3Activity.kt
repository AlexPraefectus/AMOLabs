package com.example.amolabs.lab3

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import com.example.amolabs.R
import com.example.amolabs.lab3.interpolation.EyetkenInterpolator
import com.example.amolabs.lab3.interpolation.Interpolator
import com.example.amolabs.lab3.interpolation.LagrangeInterpolator
import com.example.amolabs.lab3.interpolation.NewtonInterpolator
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

import kotlinx.android.synthetic.main.activity_lab3.*
import kotlinx.android.synthetic.main.content_lab3.*
import java.lang.NumberFormatException
import kotlin.collections.ArrayList
import kotlin.math.cos
import kotlin.math.pow
import android.text.method.ScrollingMovementMethod
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet


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
    var baseEntries = ArrayList<Entry>()
    var entries = ArrayList<Entry>()

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
            res += "(%.5f , %.5f)\n".format(i.x, i.y)
        }
        res += "\n\n\n"
        return res
    }

    private fun drawBaseEntries(){
        val dataSetCircleColors = ArrayList<Int>()
        dataSetCircleColors.add(Color.BLUE)
        val dataSet = LineDataSet(baseEntries, "Базові Значення") // add baseEntries to dataset
        dataSet.color = Color.BLUE
        dataSet.circleColors = dataSetCircleColors
        val lineData = LineData(dataSet)
        chart.data = lineData
        textView11.text = entryListToString(baseEntries)
        chart.invalidate()
    }

    private fun drawEntries(){
        val datasets = ArrayList<ILineDataSet>()

        val base = LineDataSet(baseEntries, "Базові Значення")
        val baseCircleColors = ArrayList<Int>()
        baseCircleColors.add(Color.BLUE)
        base.color = Color.BLUE
        base.circleColors = baseCircleColors

        val new = LineDataSet(entries, "Значення з інтерполяцією")
        val newCircleColors = ArrayList<Int>()
        newCircleColors.add(Color.RED)
        new.color = Color.RED
        new.circleColors = newCircleColors

        datasets.add(new)
        datasets.add(base)
        val data = LineData(datasets)
        chart.data = data
        textView11.text = entryListToString(entries)
        chart.invalidate()

    }


    private fun configureChart(){
        chart.isDragXEnabled = true
        chart.isDragYEnabled = true
        chart.isScaleYEnabled = false
        chart.isScaleXEnabled = true
        chart.description.text = "Графік функції"
    }


    private fun interpolate(){
        val interpolator:Interpolator
        var dotsInBetween = 2
        try{
            dotsInBetween = interpolation_steps.text.toString().toInt() + 1
        }catch (e: NumberFormatException){
            alertView("Некоректна кількість кроків інтерполяції, використано 1")
        }
        when(radio_group_lab3.checkedRadioButtonId){
            lagrange.id -> interpolator = LagrangeInterpolator()
            newton.id -> interpolator = NewtonInterpolator()
            eyetken.id -> interpolator = EyetkenInterpolator()
            else -> interpolator = LagrangeInterpolator()
        }
        val newEntries = ArrayList<Entry>(baseEntries.size * (dotsInBetween + 1) - 1)
        for(i in 0 until baseEntries.size - 1){
            newEntries.add(baseEntries[i])
            for (j in 1..dotsInBetween){
                newEntries.add(interpolator.interpolate(baseEntries, baseEntries[i].x + (baseEntries[i+1].x - baseEntries[i].x)/dotsInBetween*j))
            }
        }
        newEntries.add(baseEntries[baseEntries.size-1])
        entries = newEntries
        drawEntries()
    }

    private fun drawBaseValues(){
        try {
            baseEntries = getBaseVals(
                rangeStart.text.toString().toDouble(),
                rangeEnd.text.toString().toDouble(),
                dotAmount.text.toString().toInt())
        }catch (e: NumberFormatException){
            alertView("Некорректний формат введених номерів")
        }
        if (baseEntries.isEmpty()) return
        drawBaseEntries()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab3)
        setSupportActionBar(toolbar)
        textView11.movementMethod = ScrollingMovementMethod()

        lagrange.toggle()  // lagrange interpolation is default method
        configureChart()
        base_vals_lab3.setOnClickListener {drawBaseValues()}
        interpolate_lab3.setOnClickListener {interpolate()}
    }

}
