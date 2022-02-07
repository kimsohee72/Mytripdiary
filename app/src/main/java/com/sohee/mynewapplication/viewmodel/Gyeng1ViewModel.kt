package com.sohee.mynewapplication.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.sohee.mynewapplication.data.Gyeng1Database
import com.sohee.mynewapplication.model.Gyeng1
import com.sohee.mynewapplication.repository.Gyeng1Respository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Gyeng1ViewModel (application: Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<Gyeng1>>
    private val repository : Gyeng1Respository

    init {
        val userDao = Gyeng1Database.getDatabase(application)!!.Gyeng1Dao()
        repository = Gyeng1Respository(userDao)
        readAllData = repository.readAllData.asLiveData()
    }

    fun addUser(user : Gyeng1){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user : Gyeng1){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user : Gyeng1){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Gyeng1>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

    // ViewModel에 파라미터를 넘기기 위해서, 파라미터를 포함한 Factory 객체를 생성하기 위한 클래스
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return Gyeng1ViewModel(application) as T
        }
    }
}