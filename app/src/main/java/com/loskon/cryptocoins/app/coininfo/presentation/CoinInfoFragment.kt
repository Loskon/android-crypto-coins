package com.loskon.cryptocoins.app.coininfo.presentation

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.loskon.cryptocoins.R
import com.loskon.cryptocoins.base.extension.coroutines.observe
import com.loskon.cryptocoins.base.extension.view.setDebounceClickListener
import com.loskon.cryptocoins.base.viewbinding.viewBinding
import com.loskon.cryptocoins.databinding.FragmentCoinInfoBinding
import com.loskon.cryptocoins.utils.ImageLoader
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoinInfoFragment : Fragment(R.layout.fragment_coin_info) {

    private val binding by viewBinding(FragmentCoinInfoBinding::bind)
    private val viewModel by viewModel<CoinInfoViewModel>()
    private val args by navArgs<CoinInfoFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) viewModel.performCoinRequest(args.id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbCoinInfo.title = args.name
        installObservers()
        setupViewsListeners()
    }

    private fun installObservers() {
        viewModel.coinInfoFlow.observe(viewLifecycleOwner) {
            when (it) {
                is CoinInfoState.Loading -> {
                    binding.indicatorCoinInfo.isVisible = true
                }
                is CoinInfoState.Success -> {
                    binding.indicatorCoinInfo.isVisible = false
                    binding.incErrorCoinInfo.root.isVisible = false
                    binding.swCoinInfo.isVisible = true

                    ImageLoader.load(binding.ivCoinInfo, it.coin.imageUrl)
                    binding.tvCoinInfoDescription.text = getFromHtmlText(it.coin.description.replace("\n", "<br/>"))
                    binding.tvCoinInfoCategories.text = it.coin.categories.joinToString(separator = ", ")
                }
                is CoinInfoState.Error -> {
                    binding.indicatorCoinInfo.isVisible = false
                    binding.incErrorCoinInfo.root.isVisible = true
                }
            }
        }
    }

    @Suppress("DEPRECATION")
    private fun getFromHtmlText(source: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(source, Html.FROM_HTML_MODE_COMPACT).toString()
        } else {
            Html.fromHtml(source).toString()
        }
    }

    private fun setupViewsListeners() {
        binding.tbCoinInfo.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.incErrorCoinInfo.btnToTry.setDebounceClickListener {
            viewModel.performCoinRequest(args.id)
        }
    }
}