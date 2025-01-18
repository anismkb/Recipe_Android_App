package fr.mekbal_dev.recipe_app.retrofit

import fr.mekbal_dev.recipe_app.pojo.CatgoryList
import fr.mekbal_dev.recipe_app.pojo.MealsByCategoryList
import retrofit2.Call
import fr.mekbal_dev.recipe_app.pojo.meal_list
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    fun getRandomMeal():Call<meal_list>

    @GET("lookup.php?")
    fun getMealById(@Query("i") id:String):Call<meal_list>

    @GET("filter.php?")
    fun getPopularItems(@Query("c") category:String):Call<MealsByCategoryList>

    @GET("categories.php")
    fun getAllCategories():Call<CatgoryList>

    @GET("filter.php")
    fun getMealsByCategoryName(@Query("c") nameCategory:String):Call<MealsByCategoryList>
}