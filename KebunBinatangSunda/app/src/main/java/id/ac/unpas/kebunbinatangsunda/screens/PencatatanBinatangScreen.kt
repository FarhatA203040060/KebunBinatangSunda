package id.ac.unpas.kebunbinatangsunda.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.room.Room
import id.ac.unpas.kebunbinatangsunda.model.BinatangMasuk
import id.ac.unpas.kebunbinatangsunda.persistences.AppDatabase
import id.ac.unpas.kebunbinatangsunda.screens.FormPencatatanBinatang
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun PencatatanBinatangScreen() {
    val context = LocalContext.current
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "penyimpanan-binatang"
    ).build()
    val binatangMasukDao = db.binatangMasukDao()

    val list : LiveData<List<BinatangMasuk>> = binatangMasukDao.loadAll()
    val items: List<BinatangMasuk> by list.observeAsState(initial = listOf())

    Column(modifier = Modifier.fillMaxWidth()) {
        FormPencatatanBinatang(binatangMasukDao)
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(items = items, itemContent = { item ->
                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Tanggal Masuk", fontSize = 14.sp)
                        Text(
                            text = item.tanggalmasuk, fontSize = 16.sp, fontWeight =
                            FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Nama Binatang", fontSize = 14.sp)
                        Text(
                            text = item.namabinatang, fontSize = 16.sp, fontWeight =
                            FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Asal Negara Binatang", fontSize = 14.sp)
                        Text(
                            text = item.asalBinatang, fontSize = 16.sp, fontWeight =
                            FontWeight.Bold
                        )
                    }
                    Column(modifier = Modifier.weight(3f)) {
                        Text(text = "Berat Binatang", fontSize = 14.sp)
                        Text(text = "${item.beratbinatang} Kg", fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)
                    }
                }
                Divider(modifier = Modifier.fillMaxWidth())
            })
        }
    }
}