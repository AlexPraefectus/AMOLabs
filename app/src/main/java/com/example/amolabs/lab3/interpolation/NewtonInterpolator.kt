package com.example.amolabs.lab3.interpolation

import com.github.mikephil.charting.data.Entry


fun finiteDifference(lst: ArrayList<Entry>): Double{
    if (lst.size == 2){
        return (lst[1].y - lst[0].y)/(lst[1].x - lst[0].x).toDouble()
    }
    val newLstLeft = ArrayList<Entry>()
    val newLstRight = ArrayList<Entry>()
    newLstLeft.addAll(lst.slice(0 until lst.size-1))
    newLstRight.addAll(lst.slice(1 until lst.size))
    return (finiteDifference(newLstRight) - finiteDifference(newLstLeft))/(lst[lst.size-1].x - lst[0].x)

}

class NewtonInterpolator: Interpolator {

    override fun interpolate(dots: ArrayList<Entry>, newDot: Float): Entry {
        var sum = dots[0].y.toDouble()
        for (i in 1 until dots.size){
            var product = 1.0
            var dif = finiteDifference(ArrayList(dots.slice(0..i)))
            for (j in 0 until i){
                product *= (newDot - dots[j].x)
            }
            sum += (dif * product)
        }
        return (Entry(newDot, sum.toFloat()))
    }
}