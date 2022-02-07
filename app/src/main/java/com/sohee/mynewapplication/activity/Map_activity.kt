package com.sohee.mynewapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sohee.mynewapplication.R
import kotlinx.android.synthetic.main.activity_map.*

class Map_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        seoul_button.setOnClickListener {
            startActivity(Intent(this,seoul::class.java))

        }
        ggd_button.setOnClickListener {
            startActivity(Intent(this,ggd::class.java))

        }
        gang_button.setOnClickListener {
            startActivity(Intent(this,gang::class.java))

        }
        gyeng1_button.setOnClickListener {
            startActivity(Intent(this,gyeng1::class.java))

        }
        gyeng2_button.setOnClickListener {
            startActivity(Intent(this,gyeng2::class.java))

        }
        jen1_button.setOnClickListener {
            startActivity(Intent(this,jenla1::class.java))

        }
        jen2_button.setOnClickListener {
            startActivity(Intent(this,jenla2::class.java))

        }
        chung1_button.setOnClickListener {
            startActivity(Intent(this,chung1::class.java))

        }
        chung2_button.setOnClickListener {
            startActivity(Intent(this,chung2::class.java))

        }
        jeju_button.setOnClickListener {
            startActivity(Intent(this,jeju::class.java))

        }

    }



}