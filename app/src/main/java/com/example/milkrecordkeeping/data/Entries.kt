package com.example.milkrecordkeeping.data

import androidx.room.*

@Entity(
    tableName = "milk_entries", foreignKeys = [ForeignKey(
        entity = Agent::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("agent_id"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["agent_id"])]
)
data class MilkEntries(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "rate")
    val rate: Double,
    @ColumnInfo(name = "quantity")
    val quantity: Int,
    @ColumnInfo(name = "session", defaultValue = "1")
    val session: Int,
    @ColumnInfo(name = "agent_id")
    val agentId: Int,
    @ColumnInfo(name = "created_at", defaultValue = "CURRENT_TIMESTAMP")
    val createdDate: String
)

enum class Session(val code: Int) {
    MORNING(0), EVENING(1);
}
