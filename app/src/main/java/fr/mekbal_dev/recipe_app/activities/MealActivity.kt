package fr.mekbal_dev.recipe_app.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import fr.mekbal_dev.recipe_app.HomeFragment
import fr.mekbal_dev.recipe_app.R
import fr.mekbal_dev.recipe_app.databinding.ActivityMealBinding
import fr.mekbal_dev.recipe_app.pojo.Meal
import fr.mekbal_dev.recipe_app.viewmodel.HomeViewModel
import fr.mekbal_dev.recipe_app.viewmodel.MealViewModel

class MealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMealBinding
    private lateinit var mealName:String
    private lateinit var mealId:String
    private lateinit var mealThumb:String
    private lateinit var YoutubLink:String
    private lateinit var mealMVVM:MealViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getMealInformationIntent()
        setInformationInViews()
        mealMVVM = ViewModelProvider(this).get(MealViewModel::class.java)
        mealMVVM.getMealById(mealId)
        observerMealDetailsLiveData()
        LoadingCase()
        onYoutubeImageClick()
    }

    private fun onYoutubeImageClick() {
        binding.Youtube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(YoutubLink))
            startActivity(intent)
        }
    }

    private fun observerMealDetailsLiveData() {
        mealMVVM.observerMealDetailsLiveData().observe(this, object : Observer<Meal> {
            override fun onChanged(value: Meal) {
                binding.category.text = "Category : ${value.strCategory}"
                binding.area.text = "Area : ${value.strArea}"
                binding.description.text = "${value.strInstructions}"
                YoutubLink= value.strYoutube.toString()
                onResponseCase()
            }

        }) 
    }

    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(mealThumb).into(binding.mealImageDetails)

        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInformationIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName= intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }

    private fun LoadingCase(){
        binding.progresBar.visibility = View.VISIBLE
        binding.description.visibility = View.INVISIBLE
        binding.category.visibility = View.INVISIBLE
        binding.area.visibility = View.INVISIBLE
    }

    private fun onResponseCase(){
        binding.progresBar.visibility = View.INVISIBLE
        binding.description.visibility = View.VISIBLE
        binding.category.visibility = View.VISIBLE
        binding.area.visibility = View.VISIBLE
    }
}