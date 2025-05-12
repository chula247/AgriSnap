package com.chula.agrisnap.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.chula.agrisnap.data.AppDatabase
import com.chula.agrisnap.model.Fruit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.chula.agrisnap.data.FruitDatabase

class FruitViewModel(app: Application) : AndroidViewModel(app) {

    private val context = app.applicationContext
    private val fruitDao = FruitDatabase.getDatabase(app).fruitDao()

    // LiveData for observing changes from the database
    val allFruits: LiveData<List<Fruit>> = fruitDao.getAllFruits()

    // State to hold and observe fruits dynamically in Compose
    private val _fruits = mutableStateOf<List<Fruit>>(emptyList())
    val fruits: State<List<Fruit>> = _fruits

    init {
        // Update the _fruits state whenever LiveData is updated
        allFruits.observeForever { fruitList ->
            _fruits.value = fruitList
        }
    }

    // Function to add a new fruit item
    fun addFruit(name: String, price: Double, phone: String, imageUri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val savedImagePath = saveImageToInternalStorage(Uri.parse(imageUri))
            val newFruit = Fruit(
                name = name,
                price = price,
                phone = phone,
                imagePath = savedImagePath // use saved image path
            )
            fruitDao.insertFruit(newFruit)
        }
    }

    // Function to update an existing fruit item
    fun updateFruit(updatedFruit: Fruit) {
        viewModelScope.launch(Dispatchers.IO) {
            fruitDao.updateFruit(updatedFruit)
        }
    }

    // Function to delete a fruit item
    fun deleteFruit(fruit: Fruit) {
        viewModelScope.launch(Dispatchers.IO) {
            // Delete image from storage
            deleteImageFromInternalStorage(fruit.imagePath)
            fruitDao.deleteFruit(fruit)
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
