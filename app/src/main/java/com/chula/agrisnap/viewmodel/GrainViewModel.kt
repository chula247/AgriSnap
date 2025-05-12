package com.chula.agrisnap.viewmodel

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.chula.agrisnap.data.AppDatabase
import com.chula.agrisnap.model.Grain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.chula.agrisnap.data.GrainDatabase

class GrainViewModel(app: Application) : AndroidViewModel(app) {

    private val context = app.applicationContext
    private val grainDao = GrainDatabase.getDatabase(app).grainDao()

    // LiveData for observing changes from the database
    val allGrains: LiveData<List<Grain>> = grainDao.getAllGrains()

    // State to hold and observe grains dynamically in Compose
    private val _grains = mutableStateOf<List<Grain>>(emptyList())
    val grains: State<List<Grain>> = _grains

    init {
        // Update the _grains state whenever LiveData is updated
        allGrains.observeForever { grainList ->
            _grains.value = grainList
        }
    }

    // Function to add a new grain item
    fun addGrain(name: String, price: Double, phone: String, imageUri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val savedImagePath = saveImageToInternalStorage(Uri.parse(imageUri))
            val newGrain = Grain(
                name = name,
                price = price,
                phone = phone,
                imagePath = savedImagePath // use saved image path
            )
            grainDao.insertGrain(newGrain)
        }
    }

    // Function to update an existing grain item
    fun updateGrain(updatedGrain: Grain) {
        viewModelScope.launch(Dispatchers.IO) {
            grainDao.updateGrain(updatedGrain)
        }
    }

    // Function to delete a grain item
    fun deleteGrain(grain: Grain) {
        viewModelScope.launch(Dispatchers.IO) {
            // Delete image from storage
            deleteImageFromInternalStorage(grain.imagePath)
            grainDao.deleteGrain(grain)
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
