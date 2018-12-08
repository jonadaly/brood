package com.jonadaly.brood.ui.main

import com.jonadaly.brood.R
import com.jonadaly.brood.core.BaseActivity
import com.jonadaly.brood.databinding.ActivityMainBinding

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>(MainActivityViewModel::class.java) {

    override fun initViewModel(viewModel: MainActivityViewModel) {
        binding.viewModel = viewModel
    }

    override fun getLayoutRes() = R.layout.activity_main
}
