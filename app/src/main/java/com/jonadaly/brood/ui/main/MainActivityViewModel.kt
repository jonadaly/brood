package com.jonadaly.brood.ui.main

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.view.View
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

    private val disposables = CompositeDisposable()

    val broodLiveData: MutableLiveData<Brood> = MutableLiveData()

    private var brood: LiveData<Brood>? = null

    @Inject
    lateinit var db: AppDatabase

    @Inject
    lateinit var api: BroodApi

    init {
        (app as? App)?.component?.inject(this)
    }

    fun getAppName() = getApplication<Application>().resources.getString(R.string.app_name)

//    fun getBrood(): LiveData<Brood> {
//        if (brood == null) {
//            brood = MutableLiveData<Brood>()
//            loadBrood()
//        }
//        return brood!!
//    }
//
//    fun loadBrood(): Brood {
//        disposables.add(api.getBroodById("1234")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe { loadingVisibility.value = View.VISIBLE }
//                .subscribe(
//                        { onRetrievePostListSuccess() },
//                        { onRetrievePostListError() }
//                )
//        )
//    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

//    private fun loadExamples() {
//        examples = db.exampleDao.getExamples()
//    }
}