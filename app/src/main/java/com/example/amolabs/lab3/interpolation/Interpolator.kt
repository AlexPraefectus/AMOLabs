package com.example.amolabs.lab3.interpolation

import com.github.mikephil.charting.data.Entry

interface Interpolator {
    /**
     * return new dot value
     */
    public fun interpolate(dots: ArrayList<Entry>, newDot: Entry):Entry
}