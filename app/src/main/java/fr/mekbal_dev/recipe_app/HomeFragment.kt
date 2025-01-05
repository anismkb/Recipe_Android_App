package fr.mekbal_dev.recipe_app

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import fr.mekbal_dev.recipe_app.databinding.FragmentHomeBinding
import fr.mekbal_dev.recipe_app.pojo.Meal
import fr.mekbal_dev.recipe_app.viewmodel.HomeViewModel


class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homemvvm: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homemvvm = ViewModelProvider(this).get(HomeViewModel::class.java)
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
        homemvvm.getRandomMeal();
        observerRandomMeal()
    }

    private fun observerRandomMeal() {
        homemvvm.observeRandomMealLivedata().observe(viewLifecycleOwner, object : Observer<Meal>{
            override fun onChanged(value: Meal) {
                Glide.with(this@HomeFragment)
                    .load(value.strMealThumb)
                    .into(binding.imageRand)
            }
        })
    }


}