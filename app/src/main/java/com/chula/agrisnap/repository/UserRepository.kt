package com.chula.agrisnap.repository

import com.chula.agrisnap.data.UserDao
import com.chula.agrisnap.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun registerUser(user: User) {
        userDao.registerUser(user)
    }

    suspend fun loginUser(email: String, password: String): User? {
        return userDao.loginUser(email, password)
    }
}