package com.jonadaly.brood.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.jonadaly.brood.db.dao.BroodDao
import com.jonadaly.brood.db.dao.ChickenDao
import com.jonadaly.brood.db.entities.Brood
import com.jonadaly.brood.db.entities.Chicken

@Database(entities = arrayOf(Brood::class, Chicken::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun broodDao(): BroodDao
    abstract fun chickenDao(): ChickenDao
}