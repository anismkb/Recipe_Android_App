package fr.mekbal_dev.recipe_app.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import fr.mekbal_dev.recipe_app.HomeFragment
import fr.mekbal_dev.recipe_app.HomeFragment.Companion.MEAL_ID
import fr.mekbal_dev.recipe_app.HomeFragment.Companion.MEAL_NAME
import fr.mekbal_dev.recipe_app.HomeFragment.Companion.MEAL_THUMB
import fr.mekbal_dev.recipe_app.HomeFragment.Companion.NAME_CATEGORY
import fr.mekbal_dev.recipe_app.adapter.MealsCategory_Adapter
import fr.mekbal_dev.recipe_app.databinding.ActivityCategoryMealsBinding

import fr.mekbal_dev.recipe_app.viewmodel.MealsByCategoryViewModel

class CategoryMealsActivity : AppCompatActivity() {

    lateinit var binding : ActivityCategoryMealsBinding
    lateinit var mealsByCategoryViewModel: MealsByCategoryViewModel
    lateinit var mealscategoryAdapter: MealsCategory_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryMealsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mealsByCategoryViewModel = ViewModelProvider(this)[MealsByCategoryViewModel::class.java]

        prepareRecyclerView()

        mealsByCategoryViewModel.getMealsByCategoy(intent.getStringExtra(HomeFragment.NAME_CATEGORY)!!)
        observeMealsByCategory()

        onClickrecipe()
    }

    private fun onClickrecipe() {
        mealscategoryAdapter.mealSelected={meal->
            val intent = Intent(this, MealActivity::class.java)
            intent.putExtra(MEAL_ID, meal.idMeal)
            intent.putExtra(MEAL_NAME, meal.strMeal)
            intent.putExtra(MEAL_THUMB, meal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observeMealsByCategory() {
        mealsByCategoryViewModel.observeMealsByCategory().observe(this, Observer{ MealsList ->
            mealscategoryAdapter.setMealsList(MealsList)
            binding.cont.text = mealscategoryAdapter.itemCount.toString()+ " Recipes"
        })
    }

    private fun prepareRecyclerView(){
        mealscategoryAdapter = MealsCategory_Adapter()
        binding.reycleViewCategoryItem.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = mealscategoryAdapter
        }
    }


}