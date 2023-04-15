package com.example.cryptocurrency.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrency.databinding.CoinListItemBinding
import com.example.cryptocurrency.domain.model.Coin


class CoinListAdapter(private val clickListener: CoinListener) : androidx.recyclerview.widget.ListAdapter<Coin,CoinListViewHolder> (CoinDiffUtil()){


    class CoinDiffUtil : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem == newItem
        }

    }
    class CoinListener(val clickListener: (coin: Coin) -> Unit) {
        fun onClick(coin: Coin) = clickListener(coin)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        return CoinListViewHolder.from(parent) as CoinListViewHolder
    }

    override fun onBindViewHolder(holder: CoinListViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

}

class CoinListViewHolder( private var binding:CoinListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(coin: Coin, clickListener: CoinListAdapter.CoinListener) {
        binding.coin = coin
        binding.executePendingBindings()
        binding.clickListener = clickListener
    }

    companion object {
        fun from(parent: ViewGroup): RecyclerView.ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = CoinListItemBinding.inflate(layoutInflater, parent, false)
            return CoinListViewHolder(binding)
        }
    }



}
