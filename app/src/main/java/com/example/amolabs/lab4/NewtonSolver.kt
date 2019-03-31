package com.example.amolabs.lab4

import java.math.BigDecimal


class NewtonSolver(
    private val x3Coef: Double,
    private val x2Coef: Double,
    private val x1Coef: Double,
    private val x0Coef: Double,
    private val startInterval: Double,
    private val endInterval: Double,
    private val precision: Double
){
    private val precisionSeparation = 1e-1

    private fun derivative(x: BigDecimal, h: BigDecimal = BigDecimal(1e-4)): BigDecimal{
        val funAtX = function(BigDecimal(x3Coef),
                              BigDecimal(x2Coef),
                              BigDecimal(x1Coef),
                              BigDecimal(x0Coef),
                              x)
        val funAtXPlusH = function(BigDecimal(x3Coef),
                                   BigDecimal(x2Coef),
                                   BigDecimal(x1Coef),
                                   BigDecimal(x0Coef),
                                   x + h)
        return (funAtXPlusH - funAtX)/h
    }

    private tailrec fun newtonIterations(lastCalculatedX: BigDecimal, a:BigDecimal): BigDecimal {
        val oldFunction = function(BigDecimal(x3Coef), BigDecimal(x2Coef), BigDecimal(x1Coef), BigDecimal(x0Coef), lastCalculatedX)
        val oldDerivative = derivative(lastCalculatedX)
        val newX = lastCalculatedX - (oldFunction / oldDerivative)
        val diff = newX - lastCalculatedX
        if (diff.abs() < BigDecimal(precision)){
            return newX
        }
        if ((newX - a).abs() < BigDecimal(precision)){
            return a
        }
        return newtonIterations(newX, a)
    }

    fun solve(): List<Double>{
        val eqSolutions = ArrayList<Double>()
        var previousPointer = startInterval
        var pointer = startInterval + precisionSeparation
        var funPointer: BigDecimal
        var funPreviousPointer = function(BigDecimal(x3Coef),
                                          BigDecimal(x2Coef),
                                          BigDecimal(x1Coef),
                                          BigDecimal(x0Coef),
                                          BigDecimal(previousPointer))
        while (pointer < endInterval){
            funPointer = function(BigDecimal(x3Coef),
                                  BigDecimal(x2Coef),
                                  BigDecimal(x1Coef),
                                  BigDecimal(x0Coef),
                                  BigDecimal(pointer))
            // calculated values on the edges of the interval
            if (funPointer * funPreviousPointer < BigDecimal(0)){
                eqSolutions.add(newtonIterations(BigDecimal(pointer), BigDecimal(previousPointer)).toDouble())
            }
            funPreviousPointer = funPointer
            previousPointer = pointer
            pointer += precisionSeparation
        }
        return eqSolutions
    }
}