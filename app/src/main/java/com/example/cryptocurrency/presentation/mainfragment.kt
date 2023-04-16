package com.example.cryptocurrency.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cryptocurrency.databinding.FragmentMainfragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {
    private var coinListAdapter: CoinListAdapter? = null
    private val viewModle:CoinListViewModel by viewModels()
    private lateinit var binding : FragmentMainfragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentMainfragmentBinding.inflate(inflater)
        setupRecyclerView()
        viewModle.navigateToSelectedCoin.observe(viewLifecycleOwner) { coin ->
            coin?.let {
                this.findNavController().navigate(MainFragmentDirections.actionMainFragmentToCoinDetail(
                  selectedId = coin.id
                ))
                viewModle.navigated()
            }
        }

        lifecycleScope.launch{

            viewModle.state.collect{
                if (it.error.isNotBlank()){
                    binding.error.text=it.error
                    binding.statusLoadingWheel.visibility=View.GONE


                }else if (it.isLoading){
                    binding.statusLoadingWheel.visibility=View.VISIBLE

                }else{
                    binding.error.visibility=View.GONE
                    coinListAdapter?.submitList(it.coins)
                    binding.statusLoadingWheel.visibility=View.GONE

                }
            }
        }

        return binding.root
    }

    private fun setupRecyclerView() {
        coinListAdapter= CoinListAdapter(CoinListAdapter.CoinListener {
            viewModle.onCoinslected(it)

        })
        binding.recyclerview.adapter = coinListAdapter



    }
}