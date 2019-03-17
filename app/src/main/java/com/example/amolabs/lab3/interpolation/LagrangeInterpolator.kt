package com.example.amolabs.lab3.interpolation

import com.example.amolabs.lab3.factorial
import com.example.amolabs.lab3.taskFunction
import com.github.mikephil.charting.data.Entry


class LagrangeInterpolator:Interpolator {
    override fun interpolate(dots: ArrayList<Entry>, newDot: Float): Entry {
        var sum_ = 0.0
        var product:Double
        for (i in 0 until dots.size){
            product = 1.0
            for (j in 0 until dots.size){
                if (i != j) product *= ((newDot - dots[j].x) / (dots[i].x - dots[j].x))
            }
            sum_ += (product * dots[i].y)
        }
        return Entry(newDot, sum_.toFloat())
    }
}