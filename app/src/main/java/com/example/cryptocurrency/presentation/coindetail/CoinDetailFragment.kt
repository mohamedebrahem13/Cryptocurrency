package com.example.cryptocurrency.presentation.coindetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.cryptocurrency.databinding.FragmentCoinDetailBinding
import com.example.cryptocurrency.presentation.CoinListViewModel
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CoinDetailFragment : Fragment() {
    private val viewModle: CoinDetailViewModel by viewModels()

    private lateinit var binding:FragmentCoinDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentCoinDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

       val coin_id=CoinDetailFragmentArgs.fromBundle(requireArguments())
        viewModle.getCoin(coin_id.selectedId)


        lifecycleScope.launch{

            viewModle.state.collect{
                when {
                    it.error.isNotBlank() -> {
                        binding.error.text=it.error
                        binding.statusLoadingWheel.visibility=View.GONE

                    }
                    it.isLoading -> {
                        binding.error.visibility=View.GONE
                        binding.statusLoadingWheel.visibility=View.VISIBLE

                    }
                    else -> {
                        binding.error.visibility=View.GONE
                        binding.coinDetail= it.coin!!
                        addChips(it.coin.tags)
                        binding.statusLoadingWheel.visibility=View.GONE


                    }
                }
            }
        }

        return binding.root
    }


    fun addChips(list: List<String>){
        for(i in list){
            val chip=Chip(this.context)
            chip.text= i
            binding.chibgroub.addView(chip)
        }

    }
}

