package com.example.milkrecordkeeping.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agent")
data class Agent(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val agentName: String,
    @ColumnInfo(name = "mobile")
    val agentMobile: String,
    @ColumnInfo(name = "address", defaultValue = "")
    val agentAddress: String,
    @ColumnInfo(name = "rate")
    val milkRate: Double,
    @ColumnInfo(name = "created_at")
    val createdDate: String
)
