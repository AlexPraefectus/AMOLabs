package com.example.amolabs.lab4

import java.math.BigDecimal

fun function(x3Coef: BigDecimal, x2Coef: BigDecimal, x1Coef: BigDecimal, x0Coef: BigDecimal, xVal: BigDecimal): BigDecimal{
    return x3Coef * xVal * xVal * xVal +
            x2Coef * xVal * xVal +
            x1Coef * xVal +
            x0Coef
}