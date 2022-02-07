package com.sohee.mynewapplication.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.sohee.mynewapplication.data.Jenla2Database
import com.sohee.mynewapplication.model.Jenla2
import com.sohee.mynewapplication.repository.Jenla2Respository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Jenla2ViewModel (application: Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<Jenla2>>
    private val repository : Jenla2Respository

    init {
        val userDao = Jenla2Database.getDatabase(application)!!.Jenla2Dao()
        repository = Jenla2Respository(userDao)
        readAllData = repository.readAllData.asLiveData()
    }

    fun addUser(user : Jenla2){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user : Jenla2){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user : Jenla2){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Jenla2>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

    // ViewModel에 파라미터를 넘기기 위해서, 파라미터를 포함한 Factory 객체를 생성하기 위한 클래스
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return Jenla2ViewModel(application) as T
        }
    }
}