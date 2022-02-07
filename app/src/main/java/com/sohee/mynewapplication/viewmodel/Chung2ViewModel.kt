package com.sohee.mynewapplication.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.sohee.mynewapplication.data.Chung2Database
import com.sohee.mynewapplication.model.Chung2
import com.sohee.mynewapplication.repository.Chung2Respository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Chung2ViewModel (application: Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<Chung2>>
    private val repository : Chung2Respository

    init {
        val userDao = Chung2Database.getDatabase(application)!!.chung2Dao()
        repository = Chung2Respository(userDao)
        readAllData = repository.readAllData.asLiveData()
    }

    fun addUser(user : Chung2){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user : Chung2){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user : Chung2){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Chung2>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

    // ViewModel에 파라미터를 넘기기 위해서, 파라미터를 포함한 Factory 객체를 생성하기 위한 클래스
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return Chung2ViewModel(application) as T
        }
    }
}