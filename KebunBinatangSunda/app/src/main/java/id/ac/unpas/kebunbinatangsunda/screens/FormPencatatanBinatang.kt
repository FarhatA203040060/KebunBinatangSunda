package id.ac.unpas.kebunbinatangsunda.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import id.ac.unpas.kebunbinatangsunda.model.BinatangMasuk
import id.ac.unpas.kebunbinatangsunda.persistences.BinatangMasukDao
import id.ac.unpas.kebunbinatangsunda.ui.theme.Purple700
import id.ac.unpas.kebunbinatangsunda.ui.theme.Teal200
import kotlinx.coroutines.launch

@Composable
fun FormPencatatanBinatang(binatangMasukDao: BinatangMasukDao) {
    val tanggalmasuk = remember { mutableStateOf(TextFieldValue("")) }
    val namabinatang = remember { mutableStateOf(TextFieldValue("")) }
    val asalbinatang = remember { mutableStateOf(TextFieldValue("")) }
    val beratbinatang = remember { mutableStateOf(TextFieldValue("")) }
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "Tanggal Masuk") },
            value = tanggalmasuk.value,
            onValueChange = {
                tanggalmasuk.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            placeholder = { Text(text = "yyyy-mm-dd") }
        )
        OutlinedTextField(
            label = { Text(text = "Nama Binatang") },
            value = namabinatang.value,
            onValueChange = {
                namabinatang.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "XXXXX") }
        )
        OutlinedTextField(
            label = { Text(text = "Asal Binatang") },
            value = asalbinatang.value,
            onValueChange = {
                asalbinatang.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "XXXXX") }
        )
        OutlinedTextField(
            label = { Text(text = "Berat") },
            value = beratbinatang.value,
            onValueChange = {
                beratbinatang.value = it
            },
            modifier = Modifier.padding(4.dp).fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "5") }
        )
        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier.padding(4.dp).fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                val id = uuid4().toString()
                val item = BinatangMasuk(id, tanggalmasuk.value.text, namabinatang.value.text, asalbinatang.value.text,
                    beratbinatang.value.text)
                scope.launch {
                    binatangMasukDao.insertAll(item)
                }
                tanggalmasuk.value = TextFieldValue("")
                namabinatang.value = TextFieldValue("")
                asalbinatang.value = TextFieldValue("")
                beratbinatang.value = TextFieldValue("")
            }, colors = loginButtonColors) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                tanggalmasuk.value = TextFieldValue("")
                namabinatang.value = TextFieldValue("")
                asalbinatang.value = TextFieldValue("")
                beratbinatang.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}