package fr.mekbal_dev.recipe_app.database

import androidx.resourceinspection.annotation.Attribute
import androidx.room.TypeConverter
import androidx.room.TypeConverters


@TypeConverters
class MealTypeConverter {

    @TypeConverter
    fun FromAnytoString(attribute: Any):String{
        if(attribute==null){
            return ""
        }else{
            return attribute.toString()
        }
    }

    @TypeConverter
    fun FromStringtoAny(attribute: String):Any{
        if(attribute==null){
            return ""
        }else{
            return attribute
        }
    }
}