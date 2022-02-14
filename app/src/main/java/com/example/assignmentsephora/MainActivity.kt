package com.example.assignmentsephora

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.assignmentsephora.databinding.ActivityMainBinding
import com.example.assignmentsephora.model.ProductData
import com.example.assignmentsephora.view.ProductClickListener
import com.example.assignmentsephora.view.ProductListFragment

class MainActivity : AppCompatActivity(), ProductClickListener {

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

    override fun onClickProduct(product: ProductData) {
        Toast.makeText(this, "${product.id}", Toast.LENGTH_SHORT).show()
    }
}