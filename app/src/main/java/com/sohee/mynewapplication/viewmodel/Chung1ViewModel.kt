package com.sohee.mynewapplication.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.sohee.mynewapplication.data.Chung1Database
import com.sohee.mynewapplication.model.Chung1
import com.sohee.mynewapplication.repository.Chung1Respository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Chung1ViewModel (application: Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<Chung1>>
    private val repository : Chung1Respository

    init {
        val userDao = Chung1Database.getDatabase(application)!!.chung1Dao()
        repository = Chung1Respository(userDao)
        readAllData = repository.readAllData.asLiveData()
    }

    fun addUser(user : Chung1){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user : Chung1){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user : Chung1){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Chung1>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

    // ViewModel에 파라미터를 넘기기 위해서, 파라미터를 포함한 Factory 객체를 생성하기 위한 클래스
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return Chung1ViewModel(application) as T
        }
    }
}