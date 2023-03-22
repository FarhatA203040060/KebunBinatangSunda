package id.ac.unpas.kebunbinatangsunda.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import id.ac.unpas.kebunbinatangsunda.model.BinatangMasuk

@Database(entities = [BinatangMasuk::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun binatangMasukDao(): BinatangMasukDao
}