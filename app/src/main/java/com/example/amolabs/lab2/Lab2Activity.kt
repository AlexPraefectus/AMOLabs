package com.example.amolabs.lab2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import kotlinx.android.synthetic.main.activity_lab2.*
import kotlinx.android.synthetic.main.content_lab2.*
import java.io.FileNotFoundException
import java.nio.charset.Charset


class Lab2Activity : AppCompatActivity() {
    private lateinit var strategy: SortingStrategy
    private lateinit var input: List<Double>
    private var selectedFile: Uri? = null
    private val permission = "android.permission.READ_EXTERNAL_STORAGE"

    private fun updateTextView(){
        lab2TextView.text = this.input.joinToString("\n", "Дані:\n")
    }

    private fun alertView(message: String) {
        val builder = AlertDialog.Builder(this@Lab2Activity)
        builder.setTitle("Інформація")
        builder.setMessage(message)
        builder.setPositiveButton("Зрозуміло") { _, _ ->}
        val dialog = builder.create()
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 111 && resultCode == RESULT_OK) {
            this.selectedFile = data?.data //The uri with the location of the file
            try{
                var stream = contentResolver.openInputStream(this.selectedFile as Uri)
                this.input = stream.bufferedReader().use { it.readText() }.split("\n").map { it.toDouble() }
                alertView("Дані зчитано")
                updateTextView()
            }catch (e: FileNotFoundException){
                alertView("Файл не знайдено, спробуйте ще")
            }catch (e: NumberFormatException){
                e.printStackTrace()
                alertView("Некорректний формат вхідного файлу")
            }
        }else{
            alertView("Файл не було обрано")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.amolabs.R.layout.activity_lab2)
        setSupportActionBar(toolbar)
        lab2TextView.movementMethod = ScrollingMovementMethod()

        lab2ButtonRead.setOnClickListener{
            try{
                this.input = lab2ArrayInput.text.split("\n").map { it.toDouble() }
                updateTextView()
            }catch (e: java.lang.NumberFormatException){
                print(e.printStackTrace())
                alertView("Некоректний формат введених даних")
            }
        }

        lab2ButtonSort.setOnClickListener{
            if (::input.isInitialized){
                this.strategy = BubbleSort()
                if (lab2switchDescending.isChecked && lab2SwitchOptimization.isChecked){
                    this.strategy = BubbleSortReverseOrderFlag()
                    alertView("Сортування з оптимізацією")
                }
                if (lab2switchDescending.isChecked && !lab2SwitchOptimization.isChecked){
                    this.strategy = BubbleSortReverseOrder()
                }
                if (!lab2switchDescending.isChecked && lab2SwitchOptimization.isChecked){
                    this.strategy = BubleSortFlag()
                    alertView("Сортування з оптимізацією")
                }
                if (!lab2switchDescending.isChecked && lab2SwitchOptimization.isChecked){
                    this.strategy = BubbleSort()
                }
                this.input = this.strategy.sort(this.input)
                updateTextView()
            }else{
                alertView("Спроба відсортувати дані до вводу")
            }
        }
        lab2ButtonFile.setOnClickListener{
            requestPermissions(arrayOf(permission), 101)
            val intent = Intent()
                .setType("*/*")
                .setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
