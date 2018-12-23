package com.jonadaly.brood.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.jonadaly.brood.db.entities.Brood

@Dao
interface BroodDao {

    @Query("SELECT * FROM Brood")
    fun getBroods(): LiveData<List<Brood>>

    @Query("SELECT * FROM Brood WHERE id = :broodId")
    fun getBrood(broodId: Long): Brood

    @Insert(onConflict = REPLACE)
    fun insertBrood(brood: Brood)

    @Delete()
    fun deleteBrood(brood: Brood)

    @Query("SELECT count(*) FROM Brood")
    fun getCount(): Int

}
