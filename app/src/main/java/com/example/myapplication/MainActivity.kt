package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.view.menu.MenuBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        menu?.findItem(R.menu.main_menu)?.title = "hello"
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var trail: Int = 0;
        when (item.itemId) {
            R.id.opt1_1 -> {
                trail = 1
            }
            R.id.opt1_2 -> {
                trail = 2
            }
            R.id.opt1_3 -> {
                trail = 3
            }
            R.id.opt2_1 -> {
                trail = 4
            }
            R.id.opt2_2 -> {
                trail = 5
            }
            R.id.opt2_3 -> {
                trail = 6
            }
            R.id.opt3_1 -> {
                trail = 7
            }
            R.id.opt3_2 -> {
                trail = 8
            }
            R.id.opt3_3 -> {
                trail = 9
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

        val myIntent = Intent(this, Trail::class.java)
        myIntent.putExtra("trailNum", trail)
        myIntent.putExtra("trailName", item.title)
        startActivity(myIntent)

        return true
    }



    private fun enableNightMode() {
        val sp = getSharedPreferences("themePreference", MODE_PRIVATE)
        val spEdit = sp.edit()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        spEdit.putInt("themeMode", 1);
        spEdit.apply()
    }
    private fun disableNightMode() {
        val sp = getSharedPreferences("themePreference", MODE_PRIVATE)
        val spEdit = sp.edit()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        spEdit.putInt("themeMode", 0);
        spEdit.apply()
    }

    private fun setup() {
        // toolbar
        setSupportActionBar(findViewById(R.id.mainToolbar))
        supportActionBar?.title = "⛰️ Szlaki górskie"
        // buttons
        val dayModeButton: Button = findViewById(R.id.btnDayMode)
        val nightModeButton: Button = findViewById(R.id.btnNightMode)
        nightModeButton.setOnClickListener { _ ->
            enableNightMode()
        }
        dayModeButton.setOnClickListener { _ ->
            disableNightMode()
        }

        // shared preferences
        val sp = getSharedPreferences("themePreference", MODE_PRIVATE)
        val mode = sp.getInt("themeMode", 0)
        applyRightTheme(mode)
    }

    private fun applyRightTheme(mode: Int) {
        if (mode == 1) {
            enableNightMode()
        } else {
            disableNightMode()
        }
    }
}