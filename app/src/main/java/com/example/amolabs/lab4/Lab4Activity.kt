package com.example.amolabs.lab4

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.example.amolabs.R

import kotlinx.android.synthetic.main.activity_lab4.*
import kotlinx.android.synthetic.main.content_lab4.*
import java.lang.Exception

class Lab4Activity : AppCompatActivity() {
    private fun alertView(message: String) {
        val builder = AlertDialog.Builder(this@Lab4Activity)
        builder.setTitle("Інформація")
        builder.setMessage(message)
        builder.setPositiveButton("Зрозуміло") { _, _ ->}
        val dialog = builder.create()
        dialog.show()
    }

    private lateinit var newtonSolver: NewtonSolver
    private var x3Coef = 0.0
    private var x2Coef = 0.0
    private var x1Coef = 0.0
    private var x0Coef = 0.0
    private var startInterval = 0.0
    private var endInterval = 0.0
    private var precision = 0.0

    @Suppress("JoinDeclarationAndAssignment")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab4)
        setSupportActionBar(toolbar)

        lab4solve.setOnClickListener{
            val solutions: List<Double>
            try {
                this.x3Coef = lab4x3Coef.text.toString().toDouble()
                this.x2Coef = lab4x2Coef.text.toString().toDouble()
                this.x1Coef = lab4x1Coef.text.toString().toDouble()
                this.x0Coef = lab4x0Coef.text.toString().toDouble()
                this.startInterval = lab4start.text.toString().toDouble()
                this.endInterval = lab4end.text.toString().toDouble()
                this.precision = lab4presicion.text.toString().toDouble()
                this.newtonSolver = NewtonSolver(this.x3Coef, this.x2Coef, this.x1Coef, this.x0Coef, this.startInterval, this.endInterval, this.precision)
            }catch (e: Exception){
                alertView("Помилка при обробці даних")
                return@setOnClickListener
            }
            solutions = newtonSolver.solve()
            if (solutions.isEmpty()){
                alertView("Коренів рівняння не знайдено")
            }
            var buf = ""
            for (i in solutions){
                buf += (i.toString() + '\n')
            }
            alertView("Рішення:$buf")
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
