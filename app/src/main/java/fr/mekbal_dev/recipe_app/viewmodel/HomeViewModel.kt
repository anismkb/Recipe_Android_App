package fr.mekbal_dev.recipe_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.mekbal_dev.recipe_app.database.MealDataBase
import fr.mekbal_dev.recipe_app.pojo.Category
import fr.mekbal_dev.recipe_app.pojo.CatgoryList
import fr.mekbal_dev.recipe_app.pojo.MealsByCategory
import fr.mekbal_dev.recipe_app.pojo.MealsByCategoryList
import fr.mekbal_dev.recipe_app.pojo.Meal
import fr.mekbal_dev.recipe_app.pojo.meal_list
import fr.mekbal_dev.recipe_app.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class HomeViewModel(
    val mealdatabase : MealDataBase
): ViewModel() {

    private var randomMealLiveData = MutableLiveData<Meal>()
    private var popularItemsLiveData = MutableLiveData<List<MealsByCategory>>()
    private var categoriesLiveData = MutableLiveData<List<Category>>()
    private var FavoritMealsLiveData = mealdatabase.mealDao().getAll()

    fun getRandomMeal(){
        RetrofitInstance.api.getRandomMeal().enqueue(object : retrofit2.Callback<meal_list>{
            override fun onResponse(call: Call<meal_list>, response: Response<meal_list>) {
                if(response.body() != null){
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal
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
        RetrofitInstance.api.getPopularItems("Seafood").enqueue(object : retrofit2.Callback<MealsByCategoryList> {
            override fun onResponse(call: Call<MealsByCategoryList>, response: Response<MealsByCategoryList>) {
                if(response.body() != null){
                    popularItemsLiveData.value = response.body()!!.meals
                }
            }
            override fun onFailure(call: Call<MealsByCategoryList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }
        })
    }

    fun getAllCategories(){
        RetrofitInstance.api.getAllCategories().enqueue(object : retrofit2.Callback<CatgoryList>{
            override fun onResponse(call: Call<CatgoryList>, response: Response<CatgoryList>) {
                if(response.body() != null){
                    categoriesLiveData.value = response.body()!!.categories
                }
            }

            override fun onFailure(call: Call<CatgoryList>, t: Throwable) {
                Log.d("Category not loaded", t.message.toString())
            }

        })
    }

    fun observeRandomMealLivedata(): LiveData<Meal> {
        return randomMealLiveData
    }

    fun observerPopularItemsLiveData(): LiveData<List<MealsByCategory>>{
        return popularItemsLiveData
    }

    fun observeAllCategoriesLiveData() : LiveData<List<Category>>{
        return categoriesLiveData
    }

    fun observeFavoritMealLiveData(): LiveData<List<Meal>> {
        return FavoritMealsLiveData
    }
}