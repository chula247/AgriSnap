package com.chula.agrisnap.viewmodel


import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.chula.agrisnap.data.AppDatabase
import com.chula.agrisnap.model.Dairy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class DairyViewModel(app: Application) : AndroidViewModel(app) {

    private val context = app.applicationContext
    private val dairyDao = AppDatabase.getDatabase(app).dairyDao()

    // LiveData for observing changes from the database
    val allDairies: LiveData<List<Dairy>> = dairyDao.getAllDairies()

    // State to hold and observe dairies dynamically in Compose
    private val _dairies = mutableStateOf<List<Dairy>>(emptyList())
    val dairies: State<List<Dairy>> = _dairies

    init {
        // Update the _dairies state whenever LiveData is updated
        allDairies.observeForever { dairyList ->
            _dairies.value = dairyList
        }
    }

    // Function to add a new dairy item
    fun addDairy(name: String, price: Double, phone: String, imageUri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val savedImagePath = saveImageToInternalStorage(Uri.parse(imageUri))
            val newDairy = Dairy(
                name = name,
                price = price,
                phone = phone,
                imagePath = savedImagePath // use saved image path
            )
            dairyDao.insertDairy(newDairy)
        }
    }

    // Function to update an existing dairy item
    fun updateDairy(updatedDairy: Dairy) {
        viewModelScope.launch(Dispatchers.IO) {
            dairyDao.updateDairy(updatedDairy)
        }
    }

    // Function to delete a dairy item
    fun deleteDairy(dairy: Dairy) {
        viewModelScope.launch(Dispatchers.IO) {
            // Delete image from storage
            deleteImageFromInternalStorage(dairy.imagePath)
            dairyDao.deleteDairy(dairy)
        }
    }

    // Save image permanently to internal storage
    private fun saveImageToInternalStorage(uri: Uri): String {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val fileName = "IMG_${System.currentTimeMillis()}.jpg"
        val file = File(context.filesDir, fileName)

        inputStream?.use { input ->
            FileOutputStream(file).use { output ->
                input.copyTo(output)
            }
        }

        return file.absolutePath
    }

    // Delete image from internal storage
    private fun deleteImageFromInternalStorage(path: String) {
        try {
            val file = File(path)
            if (file.exists()) {
                file.delete()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
