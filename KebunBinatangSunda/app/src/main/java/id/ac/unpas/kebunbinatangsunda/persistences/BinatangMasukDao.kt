package id.ac.unpas.kebunbinatangsunda.persistences

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.unpas.kebunbinatangsunda.model.BinatangMasuk

@Dao
interface BinatangMasukDao {
    @Query("SELECT * FROM BinatangMasuk")
    fun loadAll(): LiveData<List<BinatangMasuk>>
    @Query("SELECT * FROM BinatangMasuk WHERE id = :id")
    fun find(id: String): BinatangMasuk?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg items: BinatangMasuk)
    @Delete
    fun delete(item: BinatangMasuk)
}