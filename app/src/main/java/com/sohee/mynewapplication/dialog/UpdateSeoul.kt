package com.sohee.mynewapplication.dialog

import android.os.Bundle
import android.text.TextUtils
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sohee.mynewapplication.R
import com.sohee.mynewapplication.databinding.UpdateActivitySeoulBinding
import com.sohee.mynewapplication.model.Seoul
import com.sohee.mynewapplication.viewmodel.SeoulViewModel


class UpdateSeoul : AppCompatActivity() {
    private lateinit var binding : UpdateActivitySeoulBinding
    private lateinit var userViewModel : SeoulViewModel
    private var currentId : Int? = null
    private lateinit var currentPlace : String
    private lateinit var currentFeeling : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 타이틀 삭제
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = DataBindingUtil.setContentView(this, R.layout.update_activity_seoul)

        // 뷰모델 연결
        userViewModel = ViewModelProvider(this, SeoulViewModel.Factory(application)).get(SeoulViewModel::class.java)

        // 데이터가 넘어왔을 때
        if(intent.hasExtra("currentId") && intent.hasExtra("currentPlace") && intent.hasExtra("currentFeeling")){
            // 현재 정보를 할당해줌
            currentId = Integer.parseInt(intent.getStringExtra("currentId"))
            currentPlace = intent.getStringExtra("currentPlace").toString()
            currentFeeling = intent.getStringExtra("currentFeeling").toString()

            // 에디트뷰에 수정하기 전 값을 보여줌
            binding.placeEditView.setText(currentPlace)
            binding.feelingEditView.setText(currentFeeling)
        }
        else{
            Toast.makeText(this,"불러오기 실패", Toast.LENGTH_SHORT).show()
        }

        // 수정 버튼 클릭 시
        binding.updateBtn.setOnClickListener {

            val place = binding.placeEditView.text.toString()
            var feeling = binding.feelingEditView.text.toString()

            // 입력하지 않았을 때
            if ( TextUtils.isEmpty(place) || TextUtils.isEmpty(feeling) ){
                Toast.makeText(this, "데이터를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }

            // 입력 창이 비어 있지 않을 때
            else{
                val user = Seoul(currentId!!,place,feeling)
                userViewModel.updateUser(user)
                Toast.makeText(this, "다녀온 곳 : $place , 감상 : $feeling 로 수정", Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        // 취소 버튼 클릭 시
        binding.cancelBtn.setOnClickListener {
            finish()
        }

        // 삭제 버튼 클릭 시
        binding.deleteBtn.setOnClickListener {
            val user = Seoul(currentId!!,currentPlace,currentFeeling!!)
            userViewModel.deleteUser(user)
            Toast.makeText(this, "다녀온 곳 : $currentPlace , 감상 : $currentFeeling 삭제", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}