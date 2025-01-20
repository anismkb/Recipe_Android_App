package fr.mekbal_dev.recipe_app.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import fr.mekbal_dev.recipe_app.HomeFragment
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

    }

    private fun observeMealsByCategory() {
        mealsByCategoryViewModel.observeMealsByCategory().observe(this, Observer{ MealsList ->
            mealscategoryAdapter.setMealsList(MealsList)
            binding.cont.text = HomeFragment.NAME_CATEGORY +" : "+ mealscategoryAdapter.itemCount.toString()
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