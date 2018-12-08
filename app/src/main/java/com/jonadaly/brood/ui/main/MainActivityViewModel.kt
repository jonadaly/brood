package com.jonadaly.brood.ui.main

import android.app.Application
import com.jonadaly.brood.App
import com.jonadaly.brood.R
import com.jonadaly.brood.core.BaseViewModel
import com.jonadaly.brood.db.AppDatabase
import javax.inject.Inject

class MainActivityViewModel(app: Application) : BaseViewModel(app) {
    // private var examples: LiveData<List<Example>>? = null

    @Inject
    lateinit var db: AppDatabase

    init {
        (app as? App)?.component?.inject(this)
    }

    fun getAppName() = getApplication<Application>().resources.getString(R.string.app_name)

    /* Example usage of LiveData:
    fun getExamples(): LiveData<List<Example>> {
        if (examples == null) {
            examples = MutableLiveData<List<Note>>()
            loadExamples()
        }
        return examples!!;
    }

    private fun loadExamples() {
        examples = db.exampleDao.getExamples()
    }
     */
}