package com.jonadaly.brood.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.jonadaly.brood.db.dao.ExampleDao
import com.jonadaly.brood.db.entities.Example

@Database(entities = arrayOf(Example::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
}