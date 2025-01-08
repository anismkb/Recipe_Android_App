package fr.mekbal_dev.recipe_app.retrofit

import retrofit2.Call
import fr.mekbal_dev.recipe_app.pojo.meal_list
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    fun getRandomMeal():Call<meal_list>

    @GET("lookup.php?")
    fun getMealById(@Query("i") id:String):Call<meal_list>
}