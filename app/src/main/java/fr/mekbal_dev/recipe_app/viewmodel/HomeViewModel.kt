package fr.mekbal_dev.recipe_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.mekbal_dev.recipe_app.pojo.CategoryMeal
import fr.mekbal_dev.recipe_app.pojo.List_Categories
import fr.mekbal_dev.recipe_app.pojo.Meal
import fr.mekbal_dev.recipe_app.pojo.meal_list
import fr.mekbal_dev.recipe_app.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class HomeViewModel: ViewModel() {

    private var randomMealLiveData = MutableLiveData<Meal>()
    private var popularItemsLiveData = MutableLiveData<List<CategoryMeal>>()

    fun getRandomMeal(){
        RetrofitInstance.api.getRandomMeal().enqueue(object : retrofit2.Callback<meal_list>{
            override fun onResponse(call: Call<meal_list>, response: Response<meal_list>) {
                if(response.body() != null){
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal
                    Log.d("Test dzdzdzdzdzd", "meal id ${randomMeal.idMeal}")
                }else{
                    Log.d("Test dzdzdzdzdzd", "meal id ")
                    return
                }
            }
            override fun onFailure(call: Call<meal_list>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }

        })
    }

    fun getPopularItems(){
        RetrofitInstance.api.getPopularItems("Seafood").enqueue(object : retrofit2.Callback<List_Categories> {
            override fun onResponse(call: Call<List_Categories>, response: Response<List_Categories>) {
                if(response.body() != null){
                    popularItemsLiveData.value = response.body()!!.meals
                }
            }
            override fun onFailure(call: Call<List_Categories>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }
        })
    }

    fun observeRandomMealLivedata(): LiveData<Meal> {
        return randomMealLiveData
    }

    fun observerPopularItemsLiveData(): LiveData<List<CategoryMeal>>{
        return popularItemsLiveData
    }
}