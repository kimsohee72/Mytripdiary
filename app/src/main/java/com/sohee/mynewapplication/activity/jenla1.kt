package com.sohee.mynewapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sohee.mynewapplication.Jenla1Adapter
import com.sohee.mynewapplication.R
import com.sohee.mynewapplication.databinding.ActivityJenla1Binding
import com.sohee.mynewapplication.dialog.CustomDialog
import com.sohee.mynewapplication.dialog.CustomDialogInterface
import com.sohee.mynewapplication.model.Jenla1
import com.sohee.mynewapplication.viewmodel.Jenla1ViewModel


class jenla1 : AppCompatActivity(), CustomDialogInterface, SearchView.OnQueryTextListener {

    private lateinit var binding : ActivityJenla1Binding
    private lateinit var userViewModel : Jenla1ViewModel
    private val adapter: Jenla1Adapter by lazy { Jenla1Adapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_jenla1)

        // 뷰모델 연결
        userViewModel = ViewModelProvider(this, Jenla1ViewModel.Factory(application)).get(
            Jenla1ViewModel::class.java)

        // 아이템을 가로로 하나씩 보여줌
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        // 어댑터 연결
        binding.recyclerView.adapter = adapter

        userViewModel.readAllData.observe(this, Observer {
            adapter.setData(it)
        })
    }

    // 서치뷰 생성
    override fun onCreateOptionsMenu(menu: Menu?): Boolean{
        menuInflater.inflate(R.menu.main_menu,menu)

        val search = menu?.findItem(R.id.menu_search)
        val searchView = search?.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        // 서치뷰 검색버튼 클릭 시
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        // 서치뷰 텍스트 변경 시
        if(newText != null){
            searchDatabase(newText)
        }
        return true
    }

    // 검색
    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"

        userViewModel.searchDatabase(searchQuery).observe(this) { adapter.setData(it) }
    }

    // Fab 클릭시 다이얼로그 띄움
    fun onFabClicked(view : View){
        val customDialog = CustomDialog(this,this)
        customDialog.show()
    }

    // 다이얼로그에서 추가버튼 클릭 됐을 때
    override fun onAddButtonClicked(place : String, feeling : String) {
        val user = Jenla1(0,place,feeling)
        userViewModel.addUser(user)
        Toast.makeText(this, "다녀온 곳 : $place , 감상 : $feeling 추가", Toast.LENGTH_SHORT).show()
    }

    // 다이얼로그에서 취소버튼 클릭 됐을 때
    override fun onCancelButtonClicked() {
        Toast.makeText(this, "취소", Toast.LENGTH_LONG).show()
    }
}