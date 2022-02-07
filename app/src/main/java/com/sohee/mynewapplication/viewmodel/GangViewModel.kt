package com.sohee.mynewapplication.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.sohee.mynewapplication.data.GangDatabase
import com.sohee.mynewapplication.model.Gang
import com.sohee.mynewapplication.repository.GangRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GangViewModel (application: Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<Gang>>
    private val repository : GangRespository

    init {
        val userDao = GangDatabase.getDatabase(application)!!.gangDao()
        repository = GangRespository(userDao)
        readAllData = repository.readAllData.asLiveData()
    }

    fun addUser(user : Gang){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user : Gang){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user : Gang){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Gang>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

    // ViewModel에 파라미터를 넘기기 위해서, 파라미터를 포함한 Factory 객체를 생성하기 위한 클래스
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return GangViewModel(application) as T
        }
    }
}