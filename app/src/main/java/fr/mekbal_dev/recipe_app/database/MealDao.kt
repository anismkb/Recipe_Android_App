package fr.mekbal_dev.recipe_app.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import fr.mekbal_dev.recipe_app.pojo.Meal
import retrofit2.http.Query


@Dao
interface MealDao {

    @Insert
    fun InsertMeal(meal : Meal)

    @Update
    fun UpdateMeal(meal:Meal)

    @Delete
    fun DeleteMeal(meal : Meal)

    @androidx.room.Query("SELECT * FROM Meal")
    fun getAll(): LiveData<List<Meal>>
}