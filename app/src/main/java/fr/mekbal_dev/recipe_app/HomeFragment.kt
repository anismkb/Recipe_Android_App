package fr.mekbal_dev.recipe_app

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.bumptech.glide.Glide

import fr.mekbal_dev.recipe_app.activities.MealActivity
import fr.mekbal_dev.recipe_app.adapter.Popular_Adapter
import fr.mekbal_dev.recipe_app.databinding.FragmentHomeBinding
import fr.mekbal_dev.recipe_app.pojo.CategoryMeal
import fr.mekbal_dev.recipe_app.pojo.Meal
import fr.mekbal_dev.recipe_app.viewmodel.HomeViewModel


class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homemvvm: HomeViewModel
    private lateinit var randomMeal: Meal
    private  lateinit var popularAdapter: Popular_Adapter

    companion object{
        const val MEAL_ID = "fr.mekbal_dev.recipe_app.idMeal"
        const val MEAL_NAME = "fr.mekbal_dev.recipe_app.nameMeal"
        const val MEAL_THUMB = "fr.mekbal_dev.recipe_app.thumbMeal"
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
                popularAdapter.setMeals(mealsList = mealList as List<CategoryMeal>)
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