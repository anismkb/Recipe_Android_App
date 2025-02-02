package fr.mekbal_dev.recipe_app.database

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.mekbal_dev.recipe_app.pojo.Meal

@Database(entities = [Meal::class], version = 1)
@TypeConverters(MealTypeConverter::class)
abstract class MealDataBase : RoomDatabase() {

    abstract fun mealDao():MealDao

    companion object{
        @Volatile
        var db: MealDataBase? = null

        @Synchronized // 1 thread pour cette instance
        fun getInstance(context: Context): MealDataBase{
            if(db == null){
                db = Room.databaseBuilder(
                    context,
                    MealDataBase::class.java,
                    "databse-meal"
                ).fallbackToDestructiveMigration().build() //pour le rebuild
            }

            return db as MealDataBase
        }
    }
}