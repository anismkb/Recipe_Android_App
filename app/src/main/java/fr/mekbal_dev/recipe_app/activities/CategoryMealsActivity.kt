package fr.mekbal_dev.recipe_app.activities

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import fr.mekbal_dev.recipe_app.HomeFragment
import fr.mekbal_dev.recipe_app.R
import fr.mekbal_dev.recipe_app.databinding.ActivityCategoryMealsBinding
import fr.mekbal_dev.recipe_app.pojo.Meal
import fr.mekbal_dev.recipe_app.pojo.MealsByCategory
import fr.mekbal_dev.recipe_app.viewmodel.MealsByCategoryViewModel

class CategoryMealsActivity : AppCompatActivity() {

    lateinit var binding : ActivityCategoryMealsBinding
    lateinit var mealsByCategoryViewModel: MealsByCategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mealsByCategoryViewModel = ViewModelProvider(this)[MealsByCategoryViewModel::class.java]

        mealsByCategoryViewModel.getMealsByCategoy(intent.getStringExtra(HomeFragment.NAME_CATEGORY)!!)
        observeMealsByCategory()
    }

    private fun observeMealsByCategory() {
        mealsByCategoryViewModel.observeMealsByCategory().observe(this, Observer{ MealsList ->
            MealsList.forEach {
                Log.d("test log ", it.strMeal)
            }
        })
    }


}