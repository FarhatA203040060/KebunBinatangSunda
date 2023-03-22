package id.ac.unpas.kebunbinatangsunda.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BinatangMasuk(
    @PrimaryKey val id: String,
    val tanggalmasuk: String,
    val namabinatang: String,
    val asalBinatang: String,
    val beratbinatang: String
)
