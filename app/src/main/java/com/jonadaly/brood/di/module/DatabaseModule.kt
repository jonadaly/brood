package com.jonadaly.brood.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.jonadaly.brood.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun getDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "brood-db").build()
    }

}