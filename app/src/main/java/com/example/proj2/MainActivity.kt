package com.example.proj2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proj2.fragment.CelebrityListFragment
import com.example.proj2.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CelebrityListFragment())
                .commit()
        }
    }
}