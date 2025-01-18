package fr.mekbal_dev.recipe_app

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.bumptech.glide.Glide
import fr.mekbal_dev.recipe_app.activities.CategoryMealsActivity

import fr.mekbal_dev.recipe_app.activities.MealActivity
import fr.mekbal_dev.recipe_app.adapter.Category_Adapter
import fr.mekbal_dev.recipe_app.adapter.Popular_Adapter
import fr.mekbal_dev.recipe_app.databinding.FragmentHomeBinding
import fr.mekbal_dev.recipe_app.pojo.Category
import fr.mekbal_dev.recipe_app.pojo.MealsByCategory
import fr.mekbal_dev.recipe_app.pojo.Meal
import fr.mekbal_dev.recipe_app.viewmodel.HomeViewModel


class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homemvvm: HomeViewModel
    private lateinit var randomMeal: Meal
    private  lateinit var popularAdapter: Popular_Adapter
    private lateinit var categoryAdapter: Category_Adapter

    companion object{
        const val MEAL_ID = "fr.mekbal_dev.recipe_app.idMeal"
        const val MEAL_NAME = "fr.mekbal_dev.recipe_app.nameMeal"
        const val MEAL_THUMB = "fr.mekbal_dev.recipe_app.thumbMeal"
        const val NAME_CATEGORY = "fr.mekbal_dev.recipe_app.nameCategory"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homemvvm = ViewModelProvider(this).get(HomeViewModel::class.java)
        popularAdapter = Popular_Adapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homemvvm.getRandomMeal()
        observerRandomMeal()
        onRandomMealClick()

        homemvvm.getPopularItems()
        observePopulatItems()
        preparePopularItemsRecyclerViews()

        onpopularItemClick()

        homemvvm.getAllCategories()
        observerAllCategories()
        prepareAllCategoriesItemsRecycleViews()

        categoryClick()
    }

    private fun categoryClick() {
        categoryAdapter.CategorySelected={Category->
            val intent = Intent(activity, CategoryMealsActivity::class.java)
            intent.putExtra(NAME_CATEGORY, Category.strCategory)
            startActivity(intent)
        }
    }

    private fun prepareAllCategoriesItemsRecycleViews() {
        categoryAdapter = Category_Adapter()
        binding.categoryRycleView.apply {
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            adapter = categoryAdapter
        }
    }

    private fun observerAllCategories() {
        homemvvm.observeAllCategoriesLiveData().observe(viewLifecycleOwner, Observer{
            categories ->
                categoryAdapter.setCategoryList(categories)
        })
    }

    private fun onpopularItemClick() {
        popularAdapter.onItemClick = {
            meal ->
                val intent = Intent(activity, MealActivity::class.java)
                intent.putExtra(MEAL_ID, meal.idMeal)
                intent.putExtra(MEAL_NAME, meal.strMeal)
                intent.putExtra(MEAL_THUMB, meal.strMealThumb)
                startActivity(intent)
        }
    }

    private fun preparePopularItemsRecyclerViews() {
        binding.popularRecyclerView.apply {
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            adapter = popularAdapter
        }
    }

    private fun observePopulatItems() {
        homemvvm.observerPopularItemsLiveData().observe(viewLifecycleOwner, {
            mealList ->
                popularAdapter.setMeals(mealsList = mealList as List<MealsByCategory>)
        })
    }

    private fun onRandomMealClick() {
        binding.cadrImageRand.setOnClickListener{
            val intent = Intent(activity, MealActivity::class.java)
            intent.putExtra(MEAL_ID, randomMeal.idMeal)
            intent.putExtra(MEAL_NAME, randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB, randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observerRandomMeal() {
        homemvvm.observeRandomMealLivedata().observe(viewLifecycleOwner)
        { meal ->
            Glide.with(this@HomeFragment)
                .load(meal.strMealThumb)
                .into(binding.imageRand)
            this.randomMeal = meal
        }
    }


}