package com.jonadaly.brood.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.jonadaly.brood.db.entities.Example

@Dao
interface ExampleDao {

    @Query("SELECT * FROM Example")
    fun getExamples(): LiveData<List<Example>>

    @Query("SELECT * FROM Example WHERE id = :exampleId")
    fun getExample(exampleId: Long): Example

    @Insert(onConflict = REPLACE)
    fun insertExample(example: Example)

    @Delete()
    fun deleteExample(example: Example)

    @Query("SELECT count(*) FROM Example")
    fun getCount(): Int

}
