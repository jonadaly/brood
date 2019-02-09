package com.jonadaly.brood.ui.main

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.content.SharedPreferences
import android.util.Log
import com.jonadaly.brood.App
import com.jonadaly.brood.R
import com.jonadaly.brood.api.BroodApi
import com.jonadaly.brood.core.BaseViewModel
import com.jonadaly.brood.db.AppDatabase
import com.jonadaly.brood.db.entities.Brood
import com.jonadaly.brood.db.entities.Chicken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MainActivityViewModel(app: Application) : BaseViewModel(app) {

    private val TAG: String = this.javaClass.simpleName

    private val disposables = CompositeDisposable()

    val brood by lazy { MutableLiveData<Brood>() }
    val localChicken by lazy { MutableLiveData<Chicken>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    @Inject
    lateinit var db: AppDatabase

    @Inject
    lateinit var api: BroodApi

    @Inject
    lateinit var preferences: SharedPreferences

    init {
        (app as? App)?.component?.inject(this)
    }

    fun loadBrood() {
        loading.value = true
        val setLocalChickenUuid = "fd0bf3a9-74fa-4962-96cd-f57c19280650"
        val setBroodUuid = "ee181707-da61-4d9d-85b9-a17f0fbc1533"
        val editor = preferences.edit()
        editor.putString("brood_uuid", setBroodUuid)
        editor.putString("chicken_uuid", setLocalChickenUuid)
        editor.apply()
        val broodUuid = preferences.getString("brood_uuid", "") ?: ""
        val localChickenUuid = preferences.getString("chicken_uuid", "") ?: ""
        // TODO deal with empty
        disposables.add(api.getBroodById(broodUuid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { Log.i(TAG, "Subscribed") }
                .subscribe(
                        { brood ->
                            Log.i(TAG, "Succeeded")
                            this.brood.value = brood
                            localChicken.value = brood.chickens.first { it.uuid == localChickenUuid }
                            loading.value = false
                        },
                        {
                            Log.i(TAG, "Error")
                            loading.value = false
                        }
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