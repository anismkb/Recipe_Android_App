package fr.mekbal_dev.recipe_app

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.customview.widget.ViewDragHelper.Callback
import com.bumptech.glide.Glide
import fr.mekbal_dev.recipe_app.databinding.FragmentHomeBinding
import fr.mekbal_dev.recipe_app.pojo.Meal
import fr.mekbal_dev.recipe_app.pojo.meal_list
import fr.mekbal_dev.recipe_app.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Response


class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RetrofitInstance.api.getRandomMeal().enqueue(object : retrofit2.Callback<meal_list>{
            override fun onResponse(call: Call<meal_list>, response: Response<meal_list>) {
                if(response.body() != null){
                    val randomMeal: Meal = response.body()!!.meals[0]
                    Glide.with(this@HomeFragment).load(randomMeal.strMealThumb).into(binding.imageRand)
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<meal_list>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }

        })

    }


}