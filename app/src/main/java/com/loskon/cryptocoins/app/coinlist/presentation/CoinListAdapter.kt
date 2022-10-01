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
import java.math.BigDecimal
import java.math.RoundingMode

@SuppressLint("NotifyDataSetChanged")
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
                tvCoinPrice.text = getPriceWithCurrencySign(currentPrice)
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

    private fun getPriceWithCurrencySign(number: Double): String {
        return if (currency == CoinListFragment.DOLLAR_CURRENCY) {
            "$ $number"
        } else {
            "€ $number"
        }
    }

    private fun getPriceChangePercentageWithSign(number: Double): String {
        val decimal = BigDecimal(number).setScale(2, RoundingMode.HALF_UP)

        return if (decimal < BigDecimal.ZERO) {
            decimal.toString().replace("-", "- ").plus("%")
        } else if (decimal > BigDecimal.ZERO) {
            "+ $decimal%"
        } else {
            decimal.toString().plus("%")
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