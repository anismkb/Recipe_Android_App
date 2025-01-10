package fr.mekbal_dev.recipe_app.viewmodel

import android.os.Build.VERSION_CODES.O
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.mekbal_dev.recipe_app.pojo.Meal
import fr.mekbal_dev.recipe_app.pojo.meal_list
import fr.mekbal_dev.recipe_app.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Response
import kotlin.math.log

class MealViewModel : ViewModel(){

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
}