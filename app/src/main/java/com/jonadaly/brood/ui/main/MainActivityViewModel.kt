package com.jonadaly.brood.ui.main

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.jonadaly.brood.App
import com.jonadaly.brood.R
import com.jonadaly.brood.api.BroodApi
import com.jonadaly.brood.core.BaseViewModel
import com.jonadaly.brood.db.AppDatabase
import com.jonadaly.brood.db.entities.Brood
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainActivityViewModel(app: Application) : BaseViewModel(app) {

    private val TAG: String = this.javaClass.simpleName

    private val disposables = CompositeDisposable()

    val brood by lazy { MutableLiveData<Brood>() }

    @Inject
    lateinit var db: AppDatabase

    @Inject
    lateinit var api: BroodApi

    init {
        (app as? App)?.component?.inject(this)
    }

    fun getAppName() = getApplication<Application>().resources.getString(R.string.app_name)

    fun loadBrood() {
        disposables.add(api.getBroodById("ee181707-da61-4d9d-85b9-a17f0fbc1533")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { Log.i(TAG, "Subscribed") }
                .subscribe(
                        {
                            Log.i(TAG, "Succeeded")
                            brood.value = it
                        },
                        { Log.i(TAG, "Error") }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

//    private fun loadExamples() {
//        examples = db.exampleDao.getExamples()
//    }
}