package com.jonadaly.brood.ui.main

import android.arch.lifecycle.Observer
import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.jonadaly.brood.R
import com.jonadaly.brood.core.BaseActivity
import com.jonadaly.brood.databinding.ActivityMainBinding
import com.jonadaly.brood.db.entities.Chicken
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.graphics.drawable.Drawable





class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>(MainActivityViewModel::class.java) {

    val chickens: ArrayList<Chicken> = ArrayList()

    private val TAG: String = this.javaClass.simpleName

    override fun initViewModel(viewModel: MainActivityViewModel) {
        binding.viewModel = viewModel
    }

    override fun getLayoutRes() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(findViewById(R.id.toolbar))

        chickens.add(Chicken(1, "chickenuuid1", "Albert", "2018-01-01T00:00:00.000Z", "01d", "\uD83D\uDE48", 22.1f, "someuuid2", 1))
        chickens.add(Chicken(2, "chickenuuid2", "Barbara", "2018-01-01T00:00:00.000Z", "03d", "\uD83E\uDD16", 20.1f, "someuuid1", 1))
        chickens.add(Chicken(3, "chickenuuid3", "Claire", "2018-01-01T00:00:00.000Z", "10d", "\uD83D\uDC69", 24.1f, "someuuid3", 1))
        chickens.add(Chicken(4, "chickenuuid4", "Jon", "2018-01-01T00:00:00.000Z", "09d", "\uD83D\uDE3B", -8.1f, "someuuid4", 1))
        chickens.add(Chicken(5, "chickenuuid5", "Cat", "2018-01-01T00:00:00.000Z", "11d", "\uD83D\uDD74", 3.5f, "someuuid4", 1))
        chickens.add(Chicken(6, "chickenuuid6", "Michelle", "2018-01-01T00:00:00.000Z", "50d", "\uD83D\uDC7D", 8.0f, "someuuid4", 1))

        chickenList.layoutManager = LinearLayoutManager(this)
        chickenList.adapter = ChickenAdapter(chickens, this)

        chickenList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        viewModel.loadBrood()

        viewModel.brood.observe(this, Observer {
            Log.i(TAG, "Observing...")
            broodUuid.text = it?.uuid
            toolbar.title = it?.name
        })
    }
}
