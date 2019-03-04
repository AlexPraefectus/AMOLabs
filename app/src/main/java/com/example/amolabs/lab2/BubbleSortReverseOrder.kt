package com.example.amolabs.lab2

import java.util.Collections.swap

class BubbleSortReverseOrder:SortingStrategy {
    override fun sort(array: List<Double>): List<Double> {
        for (i  in 0 until array.size) {
            for (j  in 1 until array.size - i) {
                if (array[j - 1] < array[j]) {
                    swap(array, j - 1, j)
                }
            }
        }
        return array
    }
}