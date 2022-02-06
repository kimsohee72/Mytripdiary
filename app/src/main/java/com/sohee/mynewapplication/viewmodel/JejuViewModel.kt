package com.sohee.mynewapplication.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.sohee.mynewapplication.data.JejuDatabase
import com.sohee.mynewapplication.model.Jeju
import com.sohee.mynewapplication.repository.JejuRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JejuViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<Jeju>>
    private val repository : JejuRepository

    init {
        val userDao = JejuDatabase.getDatabase(application)!!.jejuDao()
        repository = JejuRepository(userDao)
        readAllData = repository.readAllData.asLiveData()
    }

    fun addUser(user : Jeju){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user : Jeju){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user : Jeju){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Jeju>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

    // ViewModel에 파라미터를 넘기기 위해서, 파라미터를 포함한 Factory 객체를 생성하기 위한 클래스
    class Factory( val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return JejuViewModel(application) as T
        }
    }
}