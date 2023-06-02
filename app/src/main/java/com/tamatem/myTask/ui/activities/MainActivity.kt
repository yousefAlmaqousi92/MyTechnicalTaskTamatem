package com.tamatem.myTask.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.tamatem.myTask.R


open class MainActivity : AppCompatActivity() {

    var btnOpenBrowser: Button? = null
    var ivClose: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        intiView()
        intiListener()

    }

    private fun intiListener() {
        btnOpenBrowser?.setOnClickListener {
            val intent = Intent(this@MainActivity, BrowserActivity::class.java)
            startActivity(intent)
        }
        ivClose?.setOnClickListener { finish() }
    }

    private fun intiView() {
        btnOpenBrowser = findViewById<View>(R.id.btnOpenBrowser) as Button
        ivClose = findViewById<View>(R.id.ivClose) as ImageView
    }
}