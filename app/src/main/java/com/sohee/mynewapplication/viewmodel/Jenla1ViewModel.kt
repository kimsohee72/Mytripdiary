package com.sohee.mynewapplication.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.sohee.mynewapplication.data.Jenla1Database
import com.sohee.mynewapplication.model.Jenla1
import com.sohee.mynewapplication.repository.Jenla1Respository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Jenla1ViewModel (application: Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<Jenla1>>
    private val repository : Jenla1Respository

    init {
        val userDao = Jenla1Database.getDatabase(application)!!.Jenla1Dao()
        repository = Jenla1Respository(userDao)
        readAllData = repository.readAllData.asLiveData()
    }

    fun addUser(user : Jenla1){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user : Jenla1){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user : Jenla1){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Jenla1>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

    // ViewModel에 파라미터를 넘기기 위해서, 파라미터를 포함한 Factory 객체를 생성하기 위한 클래스
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return Jenla1ViewModel(application) as T
        }
    }
}