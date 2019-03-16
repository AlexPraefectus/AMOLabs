package com.example.amolabs.lab3.interpolation

import com.example.amolabs.lab3.taskFunction
import com.github.mikephil.charting.data.Entry
import kotlin.math.max



class LagrangeInterpolator:Interpolator {
    override fun interpolate(dots: ArrayList<Entry>, newDot: Entry): Entry {
        var min = Double.MAX_VALUE
        var max = Double.MIN_VALUE
        for (i:Entry in dots){
            if (i.x < min) min = i.x.toDouble()
            if (i.x > max) max = i.x.toDouble()

        }
        val step = (max - min)/dots.size
        var sign = 1
        var sum = 0.0
        var mul = 1.0
        if (dots.size % 2 == 1){
            sign = -1
        }

        for (i in 0..dots.size){
            for (j in 0..dots.size)
                if (i == j) continue
                mul *= ((newDot.x - dots[0].x)/step)

            sum += mul / factorial(i) / factorial(dots.size - i)
            mul = 1.0
            sign *= -1
        }
        return Entry(sum.toFloat(), taskFunction(sum).toFloat())
    }
}