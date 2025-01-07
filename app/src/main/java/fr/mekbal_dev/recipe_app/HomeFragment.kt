package fr.mekbal_dev.recipe_app

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import fr.mekbal_dev.recipe_app.activities.MealActivity
import fr.mekbal_dev.recipe_app.databinding.FragmentHomeBinding
import fr.mekbal_dev.recipe_app.pojo.Meal
import fr.mekbal_dev.recipe_app.viewmodel.HomeViewModel


class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homemvvm: HomeViewModel
    private lateinit var randomMeal: Meal

    companion object{
        const val MEAL_ID = "fr.mekbal_dev.recipe_app.idMeal"
        const val MEAL_NAME = "fr.mekbal_dev.recipe_app.nameMeal"
        const val MEAL_THUMB = "fr.mekbal_dev.recipe_app.thumbMeal"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homemvvm = ViewModelProvider(this).get(HomeViewModel::class.java)
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
        homemvvm.getRandomMeal();
        observerRandomMeal()
        onRandomMealClick()
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