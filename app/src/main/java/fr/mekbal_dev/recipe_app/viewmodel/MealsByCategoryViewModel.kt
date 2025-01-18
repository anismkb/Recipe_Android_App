package fr.mekbal_dev.recipe_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.mekbal_dev.recipe_app.pojo.MealsByCategory
import fr.mekbal_dev.recipe_app.pojo.MealsByCategoryList
import fr.mekbal_dev.recipe_app.pojo.meal_list
import fr.mekbal_dev.recipe_app.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class MealsByCategoryViewModel : ViewModel() {

    val mealsLiveData = MutableLiveData<List<MealsByCategory>>()
    
    fun getMealsByCategoy(category:String){
        RetrofitInstance.api.getMealsByCategoryName(category).enqueue(object : retrofit2.Callback<MealsByCategoryList> {
            override fun onResponse(
                call: Call<MealsByCategoryList>,
                response: Response<MealsByCategoryList>
            ) {
                return response.body().let { mealsList->
                    if (mealsList != null) {
                        mealsLiveData.postValue(mealsList.meals)
                    }
                    else{
                        return
                    }
                }
            }

            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                Log.d("CategoryMealsViewModel", t.message.toString())
            }

        })
    }

    fun observeMealsByCategory(): LiveData<List<MealsByCategory>>{
        return mealsLiveData
    }
}