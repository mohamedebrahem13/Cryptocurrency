package com.example.cryptocurrency.presentation.coindetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.cryptocurrency.data.remote.dto.Team
import com.example.cryptocurrency.databinding.FragmentCoinDetailBinding
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {
    private val viewModel: CoinDetailViewModel by viewModels()
    private var coinDetailAdapter: CoinDetailAdapter? = null


    private lateinit var binding:FragmentCoinDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentCoinDetailBinding.inflate(inflater)
        setupRecyclerView()

       val coinId=CoinDetailFragmentArgs.fromBundle(requireArguments())
        viewModel.getCoin(coinId.selectedId)


        lifecycleScope.launch{

            viewModel.state.collect{
                when {
                    it.error.isNotBlank() -> {
                        binding.error.visibility=View.VISIBLE
                        binding.error.text=it.error
                        binding.statusLoadingWheel.visibility=View.GONE
                        binding.TeamMembers.visibility=View.GONE
                        binding.isActive.visibility=View.GONE
                        binding.rank.visibility=View.GONE

                    }
                    it.isLoading -> {
                        binding.error.visibility=View.GONE
                        binding.statusLoadingWheel.visibility=View.VISIBLE
                        binding.TeamMembers.visibility=View.INVISIBLE
                        binding.isActive.visibility=View.INVISIBLE
                        binding.rank.visibility=View.INVISIBLE



                    }
                    else -> {
                        binding.error.visibility=View.GONE
                        binding.coinDetail= it.coin!!
                        addChips(it.coin.tags)
                        bindListOfTeam(it.coin.team)
                        binding.TeamMembers.visibility=View.VISIBLE
                        binding.rank.visibility=View.VISIBLE
                        binding.isActive.visibility=View.VISIBLE
                        binding.statusLoadingWheel.visibility=View.GONE


                    }
                }
            }
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        coinDetailAdapter= CoinDetailAdapter()
        binding.teamRecycler.adapter = coinDetailAdapter
    }
    private fun bindListOfTeam(list: List<Team>){
        if(list.isEmpty()){
            binding.teamRecycler.visibility=View.GONE
            binding.TeamMembers.visibility=View.GONE
        }else{
            coinDetailAdapter?.submitList(list)
            Log.v("team", list.toString())
        }

    }

    private fun addChips(list: List<String>){
        for(i in list){
            val chip=Chip(this.context)
            chip.text= i
            binding.chibgroub.addView(chip)
        }

    }
}

