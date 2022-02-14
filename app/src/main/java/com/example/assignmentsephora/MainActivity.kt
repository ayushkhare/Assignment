package com.example.assignmentsephora

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignmentsephora.databinding.ActivityMainBinding
import com.example.assignmentsephora.view.ProductListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = viewBinding.root
        setContentView(view)
        setupFragment(savedInstanceState)
    }

    private fun setupFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(
                    R.id.fragmentContainer,
                    ProductListFragment.newInstance(),
                    "ProductListFragment"
                )
                .commit()
        }
    }
}