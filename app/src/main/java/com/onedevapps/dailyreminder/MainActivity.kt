package com.onedevapps.dailyreminder

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.onedevapps.dailyreminder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setSupportActionBar(binding.toolBar)
        supportActionBar?.title = ""
        binding.toolBar.navigationIcon?.setTint(Color.WHITE)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


        menuInflater.inflate(R.menu.toolbar_menu , menu)


        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        var id = item.itemId


        if (id == R.id.threeDot){

            Toast.makeText(this, "More Clicked" ,Toast.LENGTH_SHORT).show()


        }
        else if (id == R.id.menuPlus){

            Toast.makeText(this, "Plus Clicked" ,Toast.LENGTH_SHORT).show()


        }

        else if (id == R.id.app_bar_search){

            Toast.makeText(this, "Plus Clicked" ,Toast.LENGTH_SHORT).show()

        }

        return super.onOptionsItemSelected(item)

    }
}