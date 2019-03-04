package com.example.amolabs.lab2

import java.util.Collections.swap

class BubleSortFlag: SortingStrategy {
    public override fun sort(array: List<Double>): List<Double> {
        var swappedElements : Boolean
        do {
            swappedElements = false
            for (i in 0..array.size - 2){
                if (array[i] > array[i + 1]){
                    swap(array, i, i + 1)
                    swappedElements = true
                }
            }
        } while (swappedElements);
        return array;
    }
}