package fr.mekbal_dev.recipe_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import fr.mekbal_dev.recipe_app.activities.MainActivity
import fr.mekbal_dev.recipe_app.adapter.FavCategory_Adapter
import fr.mekbal_dev.recipe_app.adapter.MealsCategory_Adapter
import fr.mekbal_dev.recipe_app.database.MealDataBase
import fr.mekbal_dev.recipe_app.databinding.FragmentSearchBinding
import fr.mekbal_dev.recipe_app.pojo.meal_list
import fr.mekbal_dev.recipe_app.viewmodel.HomeViewModel


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var MealsrecyclerViewAdapter: FavCategory_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prpareReycleView()
        binding.imgBack.setOnClickListener{searchMeal()}
        observeSearchMeal()
    }

    private fun observeSearchMeal() {
        viewModel.observeSearchMealLiveData().observe(viewLifecycleOwner, Observer { mealList ->
            MealsrecyclerViewAdapter.differ.submitList(mealList)
        })
    }

    private fun searchMeal() {
        val query = binding.searchBox.text.toString()
        if(query.isNotEmpty()){
            viewModel.getSearchMeal(query)
        }
    }

    private fun prpareReycleView() {
        MealsrecyclerViewAdapter = FavCategory_Adapter()
        binding.reaserchReycle.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = MealsrecyclerViewAdapter
        }
    }


}