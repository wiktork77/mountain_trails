package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Trail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val receivedIntent: Intent = getIntent()
        val receivedData = receivedIntent.extras
        val trailNumber = receivedData?.getInt("trailNum" ,0) ?: 0
        val trailTitle = receivedData?.getString("trailName", " a")
        applyTheme(trailNumber)

        setContentView(R.layout.activity_trail)

        setup()
        adjustActivityLook(trailNumber!!, trailTitle!!)
    }

    private fun adjustActivityLook(trailNumber: Int, trailTitle: String) {
        val imageId = when (trailNumber) {
            1 -> R.drawable.t1_1
            2 -> R.drawable.t1_2
            3 -> R.drawable.t1_3
            4 -> R.drawable.t2_1
            5 -> R.drawable.t2_2
            6 -> R.drawable.t2_3
            7 -> R.drawable.t3_1
            8 -> R.drawable.t3_2
            9 -> R.drawable.t3_3
            else -> R.drawable.szlak_w_gorach
        }
        val trailDescription = when (trailNumber) {
            1 -> getString(R.string.desc1_1)
            2 -> getString(R.string.desc1_2)
            3 -> getString(R.string.desc1_3)
            4 -> getString(R.string.desc2_1)
            5 -> getString(R.string.desc2_2)
            6 -> getString(R.string.desc2_3)
            7 -> getString(R.string.desc3_1)
            8 -> getString(R.string.desc3_2)
            9 -> getString(R.string.desc3_3)
            else -> " "
        }
        val trailImage: ImageView = findViewById(R.id.tvTrailPhoto)
        val trailName: TextView = findViewById(R.id.tvTrailTitle)
        val trailDescriptionContent: TextView = findViewById(R.id.tvTrailDescriptionContent)
        trailDescriptionContent.text = trailDescription
        trailImage.setImageResource(imageId)
        trailName.text = trailTitle
    }

    private fun setup() {
        val backBtn: Button = findViewById(R.id.btnBack)
        backBtn.setOnClickListener { _ ->
            onBackPressed()
        }
    }

    private fun applyTheme(trailNum: Int) {
        if (trailNum > 6) {
            setTheme(R.style.AppBlueTheme)
        } else if (trailNum > 3) {
            setTheme(R.style.AppGreenTheme)
        } else {
            setTheme(R.style.AppRedTheme)
        }
    }
}
