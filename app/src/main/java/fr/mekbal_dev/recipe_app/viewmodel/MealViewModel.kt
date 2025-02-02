package fr.mekbal_dev.recipe_app.viewmodel

import android.os.Build.VERSION_CODES.O
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.mekbal_dev.recipe_app.database.MealDao
import fr.mekbal_dev.recipe_app.database.MealDataBase
import fr.mekbal_dev.recipe_app.pojo.Meal
import fr.mekbal_dev.recipe_app.pojo.meal_list
import fr.mekbal_dev.recipe_app.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import kotlin.math.log

class MealViewModel(val mealDatabase : MealDataBase): ViewModel(){

    private var DetailsMeal = MutableLiveData<Meal>();

    fun getMealById(id:String) {
        RetrofitInstance.api.getMealById(id).enqueue(object : retrofit2.Callback<meal_list> {
            override fun onResponse(call: Call<meal_list>, response: Response<meal_list>) {
                if(response.body()!=null){
                     DetailsMeal.value= response.body()!!.meals[0]
                }else{
                    return
                }
            }
            override fun onFailure(call: Call<meal_list>, t: Throwable) {
                Log.d("MealActivity", t.message.toString())
            }
        })
    }

    fun observerMealDetailsLiveData():LiveData<Meal>{
        return DetailsMeal;
    }

    fun insertFavMeal(meal: Meal) {
        viewModelScope.launch(Dispatchers.IO) {
            mealDatabase.mealDao().InsertMeal(meal)
        }
    }

    fun deleteFavMeal(meal: Meal){
        viewModelScope.launch {
            mealDatabase.mealDao().DeleteMeal(meal);
        }
    }

}