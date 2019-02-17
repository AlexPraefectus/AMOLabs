package com.example.amolabs

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml
        when(item.itemId){
            R.id.lab1task1 -> {
                val intent = Intent(this, Lab1Task1::class.java)
                startActivity(intent)
            }
            R.id.lab1task2 -> {
                val intent = Intent(this, Lab1Task2::class.java)
                startActivity(intent)
            }
            R.id.lab1task3 -> {
                val intent = Intent(this, Lab1Task3::class.java)
                startActivity(intent)
            }
            else -> super.onOptionsItemSelected(item)

        }
        return when (item.itemId) {
//            R.id.action_settings -> true
            R.id.lab1task1 -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
