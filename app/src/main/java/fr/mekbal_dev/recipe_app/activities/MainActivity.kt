package fr.mekbal_dev.recipe_app.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.mekbal_dev.recipe_app.R
import fr.mekbal_dev.recipe_app.database.MealDataBase
import fr.mekbal_dev.recipe_app.viewmodel.HomeViewModel
import fr.mekbal_dev.recipe_app.viewmodel.HomeViewModelFactory

class MainActivity : AppCompatActivity() {

    val viewModel : HomeViewModel by lazy {
        val database = MealDataBase.getInstance(this)
        val homeViewModelProviderFactory = HomeViewModelFactory(database)
        ViewModelProvider(this, homeViewModelProviderFactory)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val bottomnavigation = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navcontroller = Navigation.findNavController(this, R.id.host_fragment)

        NavigationUI.setupWithNavController(bottomnavigation, navcontroller)
    }
}