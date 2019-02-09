package com.jonadaly.brood.ui.main

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import com.jonadaly.brood.R
import com.jonadaly.brood.core.BaseActivity
import com.jonadaly.brood.databinding.ActivityMainBinding
import com.jonadaly.brood.db.entities.Chicken
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.max


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

//        chickens.add(Chicken(1, "chickenuuid1", "Albert", "2018-01-01T00:00:00.000Z", "01d", "\uD83D\uDE48", 22.1f, "someuuid2", 1))
//        chickens.add(Chicken(2, "chickenuuid2", "Barbara", "2018-01-01T00:00:00.000Z", "03d", "\uD83E\uDD16", 20.1f, "someuuid1", 1))
//        chickens.add(Chicken(3, "chickenuuid3", "Claire", "2018-01-01T00:00:00.000Z", "10d", "\uD83D\uDC69", 24.1f, "someuuid3", 1))
//        chickens.add(Chicken(4, "chickenuuid4", "Jon", "2018-01-01T00:00:00.000Z", "09d", "\uD83D\uDE3B", -8.1f, "someuuid4", 1))
//        chickens.add(Chicken(5, "chickenuuid5", "Cat", "2018-01-01T00:00:00.000Z", "11d", "\uD83D\uDD74", 3.5f, "someuuid4", 1))
//        chickens.add(Chicken(6, "chickenuuid6", "Michelle", "2018-01-01T00:00:00.000Z", "50d", "\uD83D\uDC7D", 8.0f, "someuuid4", 1))

        chickenList.layoutManager = LinearLayoutManager(this)
        chickenList.adapter = ChickenAdapter(chickens, this)

        chickenList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        viewModel.loadBrood()

//        myStatusChar.maxEmojiCount = 1
//        myStatusChar.setOnKeyListener(object : View.OnKeyListener {
//            override fun onKey(v: View, keyCode: Int, event: KeyEvent)
//            {
//                return keyCode == KeyEvent.KEYCODE_DEL
//        })

        myStatusChar.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
                if (s == null) {
                    return
                }
//                s.delete(0, test)

                val len = s.length
                if (len == 0) {
                    return
                }
                Log.e("TEST", "${s[len-1]} is of type ${Character.getType(s[len-1])}")
                if (Character.isLetterOrDigit(s[len - 1])) {
                    s.delete(0, len-1)
                    return
                }
                s.delete(0, max(0, len - 2))
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                           after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int,
                                       count: Int) {
//                test = start
            }
        })

        viewModel.localChicken.observe(this, Observer { chicken ->
            myStatusChar.setText(chicken?.status)
            val timestr = chicken?.created?.take(16)?.replace("T", " @ ")
            myStatusTime.text = "Last updated ${timestr}"
        })

        viewModel.brood.observe(this, Observer { brood ->
            Log.i(TAG, "Observing...")
            broodUuid.text = brood?.uuid
            toolbar.title = brood?.name
            chickens.clear()
            brood?.chickens?.forEach {
                chickens.add(it)
            }
            (chickenList.adapter as ChickenAdapter).notifyDataSetChanged()
        })

        viewModel.loading.observe(this, Observer { l ->
            loadingBar.visibility = when (l) {
                true -> View.VISIBLE
                false -> View.GONE
                else -> View.GONE
            }
        })
    }
}
