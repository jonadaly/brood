package com.jonadaly.brood.db.entities

import android.arch.persistence.room.*

@Entity(foreignKeys = [
    ForeignKey(
        entity = Brood::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("brood_id")
    )
], indices = [
    Index("brood_id")
])
data class Chicken(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var broodUuid: String = "",
    var name: String = "",
    var created: String = "",
    var openweatherIconId: String = "",
    var status: String = "",
    var temperature: Float = 0f,
    var uuid: String = "",
    @ColumnInfo(name = "brood_id") var broodId: Long = -1
)