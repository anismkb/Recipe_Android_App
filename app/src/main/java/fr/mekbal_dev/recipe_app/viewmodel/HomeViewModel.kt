package fr.mekbal_dev.recipe_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.mekbal_dev.recipe_app.pojo.Meal
import fr.mekbal_dev.recipe_app.pojo.meal_list
import fr.mekbal_dev.recipe_app.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class HomeViewModel: ViewModel() {

    private var randomMealLiveData = MutableLiveData<Meal>()
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

    fun observeRandomMealLivedata(): LiveData<Meal> {
        return randomMealLiveData
    }
}