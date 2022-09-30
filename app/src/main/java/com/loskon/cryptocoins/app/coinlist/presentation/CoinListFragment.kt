package com.loskon.cryptocoins.app.coinlist.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.loskon.cryptocoins.R
import com.loskon.cryptocoins.base.extension.coroutines.observe
import com.loskon.cryptocoins.base.extension.view.setDebounceClickListener
import com.loskon.cryptocoins.base.viewbinding.viewBinding
import com.loskon.cryptocoins.databinding.FragmentCoinListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CoinListFragment: Fragment(R.layout.fragment_coin_list) {

    private val binding by viewBinding(FragmentCoinListBinding::bind)
    private val viewModel by viewModel<CoinListViewModel>()

    private val coinListAdapter = CoinListAdapter()
    private var currency: String = DOLLAR_CURRENCY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) viewModel.performCoinsRequest(currency)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureRecyclerView()
        setupViewsListener()
        installObservers()
    }

    private fun configureRecyclerView() {
        with(binding.rvCoinList) {
            (itemAnimator as? SimpleItemAnimator)?.supportsChangeAnimations = false
            layoutManager = LinearLayoutManager(requireContext())
            adapter = coinListAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupViewsListener() {
        binding.chipUsd.setOnClickListener {
            currency = DOLLAR_CURRENCY
            viewModel.performCoinsRequest(currency)
        }
        binding.chipEur.setOnClickListener {
            currency = EURO_CURRENCY
            viewModel.performCoinsRequest(currency)
        }
        coinListAdapter.setOnItemClickListener { coin ->

        }
        binding.incErrorCoinList.btnToTry.setDebounceClickListener {
            viewModel.performCoinsRequest(currency)
        }
    }

    private fun installObservers() {
        viewModel.coinListStateFlow.observe(viewLifecycleOwner) { coins ->
            coinListAdapter.setCurrency(currency)
            coinListAdapter.setItems(coins)
        }
    }

    companion object {
        const val DOLLAR_CURRENCY = "usd"
        const val EURO_CURRENCY = "eur"
    }
}