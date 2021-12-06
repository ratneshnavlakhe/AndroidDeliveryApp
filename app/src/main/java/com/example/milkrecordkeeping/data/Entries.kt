package com.example.milkrecordkeeping.data

import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

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
    val quantity: Double,
    @ColumnInfo(name = "session")
    val session: String,
    @ColumnInfo(name = "agent_id")
    val agentId: Int,
    @ColumnInfo(name = "created_at")
    val createdDate: String,
    @ColumnInfo(name = "delivery_date", defaultValue = "")
    val deliveryDate: String
)

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE milk_entries_backup(" +
                    "id INTEGER NOT NULL," +
                    " rate REAL NOT NULL," +
                    " quantity REAL NOT NULL, " +
                    "session TEXT NOT NULL," +
                    " agent_id INTEGER NOT NULL, " +
                    "created_at TEXT NOT NULL, " +
                    "delivery_date TEXT NOT NULL DEFAULT '', " +
                    "PRIMARY KEY(id), " +
                    " FOREIGN KEY (agent_id) REFERENCES agent(id) ON DELETE CASCADE)"
        )
        database.execSQL("INSERT INTO milk_entries_backup(id, rate, quantity, session, agent_id, created_at) SELECT id, rate, quantity, session, agent_id, created_at FROM milk_entries")
        database.execSQL("DROP TABLE milk_entries")
        database.execSQL("ALTER TABLE milk_entries_backup RENAME TO milk_entries")
        database.execSQL("CREATE INDEX index_milk_entries_agent_id ON milk_entries(agent_id)")
    }

}

enum class Session(val code: Int) {
    MORNING(0), EVENING(1);
}
