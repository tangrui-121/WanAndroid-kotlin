package com.example.wanandroid_k_m_j.livedata_activityresult

import android.Manifest
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.wanandroid_k_m_j.R
import com.example.wanandroid_k_m_j.databinding.ActivityTakePhotoBinding
import com.example.wanandroid_k_m_j.exts.singleClick
import com.example.wanandroid_k_m_j.utils.ToastAction.toast

class TakePhotoActivity : AppCompatActivity() {

//    private val getACallback =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            if (it.resultCode == Activity.RESULT_OK) {
//
//            }
//        }

    private val mViewBinding by viewBinding(ActivityTakePhotoBinding::bind)

    private var requestPermissionLiveData = RequestPermissionLiveData(activityResultRegistry, "key")

    private var takePhotoLiveData = TakePhotoLiveData(activityResultRegistry, "key")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_photo)

        mViewBinding.btTakepermission.singleClick { requestPermissionLiveData.requestPermission(Manifest.permission.CAMERA) }

        requestPermissionLiveData.observe(this) { isGranted ->
            toast("权限请求结果 $isGranted")
        }

        mViewBinding.btTakephoto.singleClick { takePhotoLiveData.takePhoto() }

        takePhotoLiveData.observe(this) { bitmap ->
            mViewBinding.ivTakephoto.setImageBitmap(bitmap)
        }
    }
}