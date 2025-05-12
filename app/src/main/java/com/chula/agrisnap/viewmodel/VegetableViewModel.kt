package com.chula.agrisnap.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.chula.agrisnap.data.VegetableDatabase
import com.chula.agrisnap.model.Vegetable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class VegetableViewModel(app: Application) : AndroidViewModel(app) {

    private val context = app.applicationContext
    private val vegetableDao = VegetableDatabase.getDatabase(app).vegetableDao()

    // LiveData for observing changes from the database
    val allVegetables: LiveData<List<Vegetable>> = vegetableDao.getAllVegetables()

    // State to hold and observe vegetables dynamically in Compose
    private val _vegetables = mutableStateOf<List<Vegetable>>(emptyList())
    val vegetables: State<List<Vegetable>> = _vegetables

    init {
        // Update the _vegetables state whenever LiveData is updated
        allVegetables.observeForever { vegetableList ->
            _vegetables.value = vegetableList
        }
    }

    // Function to add a new vegetable
    fun addVegetable(name: String, price: Double, phone: String, imageUri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val savedImagePath = saveImageToInternalStorage(Uri.parse(imageUri))
            val newVegetable = Vegetable(
                name = name,
                price = price,
                phone = phone,
                imagePath = savedImagePath // use saved image path
            )
            vegetableDao.insertVegetable(newVegetable)
        }
    }

    // Function to update an existing vegetable
    fun updateVegetable(updatedVegetable: Vegetable) {
        viewModelScope.launch(Dispatchers.IO) {
            vegetableDao.updateVegetable(updatedVegetable)
        }
    }

    // Function to delete a vegetable
    fun deleteVegetable(vegetable: Vegetable) {
        viewModelScope.launch(Dispatchers.IO) {
            // Delete image from storage
            deleteImageFromInternalStorage(vegetable.imagePath)
            vegetableDao.deleteVegetable(vegetable)
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