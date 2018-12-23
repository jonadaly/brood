package com.jonadaly.brood.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.jonadaly.brood.db.entities.Chicken

@Dao
interface ChickenDao {

    @Query("SELECT * FROM Chicken")
    fun getChickens(): LiveData<List<Chicken>>

    @Query("SELECT * FROM Chicken WHERE id = :chickenId")
    fun getChicken(chickenId: Long): Chicken

    @Insert(onConflict = REPLACE)
    fun insertChicken(chicken: Chicken)

    @Delete()
    fun deleteChicken(chicken: Chicken)

    @Query("SELECT count(*) FROM Chicken")
    fun getCount(): Int

}
