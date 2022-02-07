package com.sohee.mynewapplication.viewmodel


import android.app.Application
import androidx.lifecycle.*
import com.sohee.mynewapplication.data.SeoulDatabase
import com.sohee.mynewapplication.model.Seoul
import com.sohee.mynewapplication.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



// 뷰모델은 DB에 직접 접근하지 않아야함. Repository 에서 데이터 통신.
class SeoulViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<Seoul>>
    private val repository : UserRepository

    init {
        val userDao = SeoulDatabase.getDatabase(application)!!.SeoulDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData.asLiveData()
    }

    fun addUser(user : Seoul){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user : Seoul){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user : Seoul){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Seoul>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

    // ViewModel에 파라미터를 넘기기 위해서, 파라미터를 포함한 Factory 객체를 생성하기 위한 클래스
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SeoulViewModel(application) as T
        }
    }
}