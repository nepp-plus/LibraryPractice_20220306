package com.neppplus.librarypractice_20220306

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    fun setupEvents() {

        btnCall.setOnClickListener {

//            권한이 있는지 확인하고 진행.
//            확인 => 획득 / 거부인지 상황에 따라 다른 행동.

            val pl = object : PermissionListener {

                override fun onPermissionGranted() {

//                    권한이 획득 되었을때 할 행동 적는 함수


//                    실제 : (권한이 있을때만) CALL 기능 실제 활용

                    val myUri = Uri.parse("tel:010-5555-6666")
                    val myIntent = Intent( Intent.ACTION_CALL, myUri )
                    startActivity(myIntent)

                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {

//                    최종 권한 거부 되었을때 할 행동.
                    Toast.makeText(this@MainActivity, "권한이 거부되어 전화 연결이 불가능합니다.", Toast.LENGTH_SHORT)
                        .show()

                }

            }


//            실제로 권한을 물어보자.
            TedPermission.create()
                .setPermissionListener(pl)
                .setPermissions(Manifest.permission.CALL_PHONE)
                .check()


        }

        imgProfile.setOnClickListener {

//            사진 크게 보는 화면으로 넘어가자.

            val myIntent = Intent(this, ViewPhotoActivity::class.java)
            startActivity(myIntent)

        }

    }

    fun setValues() {

//        인터넷상의 이미지를 곧바로 이미지뷰에 반영.

        Glide.with(this).load("https://img0.yna.co.kr/photo/ap/2011/04/13/PAP20110413127601034_P2.jpg").into(imgInternet)

    }

}