package org.eshendo.soopra.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import org.eshendo.soopra.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window?.let { window ->
            window.statusBarColor = ContextCompat.getColor(this, R.color.statusBarPurple)
        }
    }
}