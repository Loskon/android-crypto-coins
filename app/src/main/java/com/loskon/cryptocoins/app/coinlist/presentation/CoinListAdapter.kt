package com.loskon.cryptocoins.app.coinlist.presentation

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.loskon.cryptocoins.R
import com.loskon.cryptocoins.base.extension.view.setDebounceClickListener
import com.loskon.cryptocoins.base.viewbinding.viewBinding
import com.loskon.cryptocoins.databinding.ItemCoinBinding
import com.loskon.cryptocoins.domain.CoinModel
import com.loskon.cryptocoins.utils.ImageLoader

@SuppressLint("NotifyDataSetChanged, SetTextI18n")
class CoinListAdapter : RecyclerView.Adapter<CoinListAdapter.CoinListViewHolder>() {

    private var list: List<CoinModel> = emptyList()
    private var currency: String = CoinListFragment.DOLLAR_CURRENCY

    private var onItemClick: ((CoinModel) -> Unit)? = null

    override fun getItemCount(): Int = list.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        return CoinListViewHolder(parent.viewBinding(ItemCoinBinding::inflate))
    }

    override fun onBindViewHolder(holder: CoinListViewHolder, position: Int) {
        val coin = list[position]

        with(holder.binding) {
            coin.apply {
                val colorId = getCoinPriceChangeColorId(priceChangePercentage)

                ImageLoader.load(ivCoin, imageUrl)
                tvCoinName.text = name
                tvCoinSymbol.text = symbol
                tvCoinPrice.text = getPriceWithCurrencySign(currentPrice.toString())
                tvCoinPriceChange.text = getPriceChangePercentageWithSign(priceChangePercentage)
                tvCoinPriceChange.setTextColor(tvCoinPriceChange.context.getColor(colorId))
                root.setDebounceClickListener { onItemClick?.invoke(this) }
            }
        }
    }

    private fun getCoinPriceChangeColorId(number: Double): Int {
        return if (number < 0.0) {
            R.color.red
        } else if (number > 0.0) {
            R.color.green
        } else {
            R.color.light_gray
        }
    }

    private fun getPriceWithCurrencySign(currentPrice: String): String {
        return if (currency == CoinListFragment.DOLLAR_CURRENCY) {
            "$ $currentPrice"
        } else {
            "€ $currentPrice"
        }
    }

    private fun getPriceChangePercentageWithSign(number: Double): String {
        return if (number < 0.0) {
            number.toString().replace("-", "- ").plus("%")
        } else if (number > 0.0) {
            "+ $number%"
        } else {
            number.toString().plus("%")
        }
    }

    fun setCurrency(currency: String) {
        this.currency = currency
    }

    fun setItems(list: List<CoinModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClick: ((CoinModel) -> Unit)?) {
        this.onItemClick = onItemClick
    }

    class CoinListViewHolder(val binding: ItemCoinBinding) : RecyclerView.ViewHolder(binding.root)
}