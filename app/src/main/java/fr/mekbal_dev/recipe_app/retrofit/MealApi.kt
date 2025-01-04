package fr.mekbal_dev.recipe_app.retrofit

import retrofit2.Call
import fr.mekbal_dev.recipe_app.pojo.meal_list
import retrofit2.http.GET

interface MealApi {

    @GET("random.php")
    fun getRandomMeal():Call<meal_list>
}