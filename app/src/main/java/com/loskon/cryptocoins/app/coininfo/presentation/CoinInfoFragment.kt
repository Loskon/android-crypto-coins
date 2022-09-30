package com.loskon.cryptocoins.app.coininfo.presentation

import android.os.Bundle
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
import timber.log.Timber

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

        installObservers()
        setupViewListener()
    }

    private fun installObservers() {
        viewModel.coinInfoFlow.observe(viewLifecycleOwner) {
            Timber.d(it.toString())
            binding.swCoinInfo.isVisible = true
            binding.tbCoinInfo.title = it.name
            ImageLoader.load(binding.ivCoinInfo, it.imageUrls.name)
            binding.tvCoinInfoDescription.text = it.description.name
            binding.tvCoinInfoCategories.text = it.categories.joinToString(separator = ", ")
        }
    }

    private fun setupViewListener() {
        binding.tbCoinInfo.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.incErrorCoinInfo.btnToTry.setDebounceClickListener {
            viewModel.performCoinRequest(args.id)
        }
    }
}
