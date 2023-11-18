package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.core.view.get

class MainActivity : AppCompatActivity() {
    private var fType  = Typeface.NORMAL
    private var fSize: Int? = null
    private var fColor: Int? = null
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


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        when (v?.id) {
            R.id.btnFType -> {
                menuInflater.inflate(R.menu.font_type_menu, menu)
                handleCtxMenuChecks(menu, ContextMenuType.TYPE)
            }
            R.id.btnFSize -> {
                menuInflater.inflate(R.menu.font_size_menu, menu)
                handleCtxMenuChecks(menu, ContextMenuType.SIZE)
            }
            R.id.btnFColor -> {
                menuInflater.inflate(R.menu.font_color_menu, menu)
                handleCtxMenuChecks(menu, ContextMenuType.COLOR)
            }
        }

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

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var mode: String = ""
        when (item.itemId) {
            R.id.fTypeNormal -> {
                fType = Typeface.NORMAL
                mode = "ftype"
            }
            R.id.fTypeBold -> {
                fType = Typeface.BOLD
                mode = "ftype"
            }
            R.id.fTypeItalic -> {
                fType = Typeface.ITALIC
                mode = "ftype"
            }
            R.id.fSize12 -> {
                fSize = 12
                mode = "fsize"
            }
            R.id.fSize14 -> {
                fSize = 14
                mode = "fsize"
            }
            R.id.fSize16 -> {
                fSize = 16
                mode = "fsize"
            }
            R.id.fSize18 -> {
                fSize = 18
                mode = "fsize"
            }
            R.id.fColorWhite -> {
                fColor = Color.WHITE
                mode = "fcolor"
            }
            R.id.fColorBlack -> {
                fColor = Color.BLACK
                mode = "fcolor"
            }
            R.id.fColorRed -> {
                fColor = Color.RED
                mode = "fcolor"
            }
            R.id.fColorPink -> {
                fColor = Color.MAGENTA
                mode = "fcolor"
            }
        }
        applyToAllChildren(mode)
        return super.onContextItemSelected(item)
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

        bindButtons()

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

    private fun bindButtons() {
        val dayModeButton: Button = findViewById(R.id.btnDayMode)
        val nightModeButton: Button = findViewById(R.id.btnNightMode)
        val fTypeBtn: Button = findViewById(R.id.btnFType)
        val fSizeBtn: Button = findViewById(R.id.btnFSize)
        val fColor: Button = findViewById(R.id.btnFColor)
        nightModeButton.setOnClickListener { _ ->
            enableNightMode()
        }
        dayModeButton.setOnClickListener { _ ->
            disableNightMode()
        }
        registerForContextMenu(fTypeBtn)
        registerForContextMenu(fSizeBtn)
        registerForContextMenu(fColor)
    }


    private fun handleCtxMenuChecks(menu: Menu?, type: ContextMenuType) {
        var optNum: Int? = null
        when (type) {
            ContextMenuType.TYPE -> {
                when(fType) {
                    Typeface.NORMAL -> optNum = 0
                    Typeface.BOLD -> optNum = 1
                    Typeface.ITALIC -> optNum = 2
                }

            }
            ContextMenuType.SIZE -> {
                when(fSize) {
                    12 -> optNum = 0
                    14 -> optNum = 1
                    16 -> optNum = 2
                    18 -> optNum = 3
                }
            }
            ContextMenuType.COLOR -> {
                when (fColor) {
                    Color.WHITE -> optNum = 0
                    Color.BLACK -> optNum = 1
                    Color.RED -> optNum = 2
                    Color.MAGENTA -> optNum = 3
                }
            }
        }
        if (optNum != null) {
            menu?.getItem(optNum)?.setChecked(true)
        }

    }

    private fun applyToAllChildren(mode: String) {
        val root: ViewGroup = ((findViewById<View>(android.R.id.content) as ViewGroup).getChildAt(0) as ViewGroup)
        val layout: ViewGroup = root.get(1) as ViewGroup;
        for (i in 0..<layout.childCount) {
            var item: View = layout.get(i)
            when (mode.lowercase()) {
                "ftype" -> {
                    if (item is TextView) {
                        item.setTypeface(null, fType)
                    }
                    if (item is Button) {
                        item.setTypeface(null, fType)
                    }
                }
                "fsize" -> {
                    if (item is TextView && item.id != R.id.tvAuthorName) {
                        item.setTextSize(TypedValue.COMPLEX_UNIT_DIP, fSize!!.toFloat())
                    }
                    if (item is Button) {
                        item.setTextSize(TypedValue.COMPLEX_UNIT_DIP, fSize!!.toFloat())
                    }
                }
                "fcolor" -> {
                    if (item is TextView) {
                        item.setTextColor(fColor!!)
                    }
                    if (item is Button) {
                        item.setTextColor(fColor!!)
                    }
                }
            }

        }
    }
}

enum class ContextMenuType {
    TYPE,
    SIZE,
    COLOR
}