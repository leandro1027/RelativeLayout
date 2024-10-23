package com.example.relativelayout

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.relativelayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonCalculate.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if(view.id == R.id.button_calculate){
            calcular()
        }
    }

    private fun isvalid() : Boolean{
        return (binding.editDistance.text.toString() != " " &&
                binding.editPrice.text.toString() != " " &&
                binding.editAutonomy.text.toString() != " " &&
                binding.editToll.text.toString() != " " &&
                binding.editAutonomy.text.toString().toFloat() != 0f
                )
    }

    @SuppressLint("SetTextI18n")
    private  fun calcular() {
        if (isvalid()) {
            val distance = binding.editDistance.text.toString().toFloat()
            val price = binding.editPrice.text.toString().toFloat()
            val autonomY = binding.editAutonomy.text.toString().toFloat()
            val toll = binding.editToll.text.toString().toFloat()
            val totalValue = (distance * price) / autonomY + toll
            binding.textGastoTotalLabel.text = "R$ ${"%.2f".format(totalValue)}"
        } else {
            Toast.makeText(
                this,
                getString(R.string.favor_preencher_todos_os_campos),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}


