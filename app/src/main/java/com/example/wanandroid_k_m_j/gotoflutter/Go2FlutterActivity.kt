package com.example.wanandroid_k_m_j.gotoflutter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.wanandroid_k_m_j.R
import com.example.wanandroid_k_m_j.databinding.ActivityGo2FlutterBinding
import com.example.wanandroid_k_m_j.exts.singleClick
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class Go2FlutterActivity : AppCompatActivity() {

    /**
     * 原生跳转flutter卡顿
     * 缓存FlutterEngine
     */
    companion object {
        //缓存 FlutterEngine 的 key
        const val FLUTTER_ENGINE_ID = "default"
    }

    //FlutterEngine
    private lateinit var flutterEngine: FlutterEngine

    private val mViewBinding by viewBinding(ActivityGo2FlutterBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go2_flutter)

        mViewBinding.gotologin.singleClick {
            startActivity(
                FlutterActivity.withNewEngine()
                    .initialRoute("tab1") //initialRoute是Android跳转到flutter需要的参数，这里传入“tab1”,表示跳转到flutter的tab1页面
                    .build(this)
            )
        }
        mViewBinding.gotoset.singleClick {
            //  startActivity(FlutterActivity.withNewEngine() .initialRoute("home") .build(this))

            startActivity(FlutterActivity.withCachedEngine(FLUTTER_ENGINE_ID).build(this))
        }
        mViewBinding.gotoroundimage.singleClick {
            startActivity(
                FlutterActivity.withNewEngine()
                    .initialRoute("roundImage")
                    .build(this)
            )
        }

        //初始化 FlutterEngine
        flutterEngine = initFlutterEngine(FLUTTER_ENGINE_ID)
    }

    /**
     * 初始化 FlutterEngine
     * 一般在跳转前调用，从缓存中取出 FlutterEngine，这样可以加快我们页面的一个跳转
     */
    private fun initFlutterEngine(engineId: String): FlutterEngine {
        //创建 FlutterEngine
        val flutterEngine = FlutterEngine(this)
        //指定要跳转的 Flutter 页面
        flutterEngine.navigationChannel.setInitialRoute("main")
        flutterEngine.dartExecutor.executeDartEntrypoint(DartExecutor.DartEntrypoint.createDefault())
        //缓存 FlutterEngine
        val flutterEngineCache = FlutterEngineCache.getInstance()
        flutterEngineCache.put(engineId, flutterEngine)
        return flutterEngine
    }

    override fun onDestroy() {
        super.onDestroy()
        flutterEngine.destroy()
    }
}