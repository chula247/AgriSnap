package com.chula.agrisnap.viewmodel

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.chula.agrisnap.data.OfferDatabase
import com.chula.agrisnap.model.Offer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class OfferViewModel(app: Application) : AndroidViewModel(app) {

    private val context = app.applicationContext
    private val offerDao = OfferDatabase.getDatabase(app).offerDao()

    // LiveData to observe the offer list
    val allOffers: LiveData<List<Offer>> = offerDao.getAllOffers()

    // Compose-friendly state holder
    private val _offers = mutableStateOf<List<Offer>>(emptyList())
    val offers: State<List<Offer>> = _offers

    init {
        allOffers.observeForever { offerList ->
            _offers.value = offerList
        }
    }

    // Add new offer
    fun addOffer(name: String, price: Double, phone: String, imageUri: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val savedImagePath = saveImageToInternalStorage(Uri.parse(imageUri))
            val newOffer = Offer(
                name = name,
                discount = price,
                phone = phone,
                imagePath = savedImagePath
            )
            offerDao.insertOffer(newOffer)
        }
    }

    // Update existing offer
    fun updateOffer(updatedOffer: Offer) {
        viewModelScope.launch(Dispatchers.IO) {
            offerDao.updateOffer(updatedOffer)
        }
    }

    // Delete an offer and its image
    fun deleteOffer(offer: Offer) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteImageFromInternalStorage(offer.imagePath)
            offerDao.deleteOffer(offer)
        }
    }

    // Save image to internal storage
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
