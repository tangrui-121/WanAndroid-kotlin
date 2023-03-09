
object AppConfig {
    const val versionName = "1.0"
    const val versionCode = 1
    const val applicationId = "com.example.wanandroid_k_m_j"
    const val buildToolsVersion = "30.0.3"
    const val compileSdkVersion = 31
    const val targetSdkVersion = 31
    const val minSdkVersion = 21
}

object Version {
    const val gradleVersion = "4.2.1"
    const val kotlinVersion = "1.5.21"
    const val appcompatVersion = "1.3.1"
    const val ktxVersion = "1.3.2"
    const val constraintlayoutVersion = "2.1.1"
    const val junitVersion = "4.12"
    const val extUnitVersion = "1.1.0"
    const val espressoVersion = "3.2.0"
    const val retrofitVersion = "2.9.0"

    //https://github.com/Kotlin/kotlinx.coroutines
    const val coroutinesVersion = "1.5.0"

    //http://jcenter.bintray.com/com/squareup/okhttp3/logging-interceptor/
    const val okhttp3LogVersion = "4.9.1"
    const val materialVersion = "1.4.0"
    const val libVersionCode = 30
    const val libVersionName = "2.1.1"
    const val hilt_version  = "2.38.1"
}

object WanAndroidExt {
    const val buildGradle = "com.android.tools.build:gradle:${Version.gradleVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlinVersion}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Version.kotlinVersion}"
    const val appcompat = "androidx.appcompat:appcompat:${Version.appcompatVersion}"
    const val ktx = "androidx.core:core-ktx:${Version.ktxVersion}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Version.constraintlayoutVersion}"
    const val junit = "junit:junit:${Version.junitVersion}"
    const val extUnit = "androidx.test.ext:junit:${Version.extUnitVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Version.espressoVersion}"

    //https://github.com/square/retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofitVersion}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Version.retrofitVersion}"

    //https://github.com/google/gson
    const val gson = "com.google.code.gson:gson:2.8.9"
    const val okhttp3Log = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp3LogVersion}"

    //https://github.com/Kotlin/kotlinx.coroutines
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutinesVersion}"
    const val material = "com.google.android.material:material:${Version.materialVersion}"

    // https://github.com/objectbox/objectbox-java
    const val objectboxGradlePlugin = "io.objectbox:objectbox-gradle-plugin:3.0.1"

    const val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.4"
    const val activityKtx = "androidx.activity:activity-ktx:1.2.3"

    //https://github.com/kirich1409/ViewBindingPropertyDelegate viewbinding+委托
    const val viewBinding = "com.github.kirich1409:viewbindingpropertydelegate:1.4.7"

    //吐司框架：https://github.com/getActivity/ToastUtils
    const val toastUtils = "com.github.getActivity:ToastUtils:9.5"

    // 沉浸式框架：https://github.com/gyf-dev/ImmersionBar
    const val ImmersionBar = "com.gyf.immersionbar:immersionbar:3.0.0"

    // basequickadapter
    const val BasequickAdapter = "com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.4"
    // 下拉刷新
    const val Smartrefresh = "com.scwang.smartrefresh:SmartRefreshLayout:1.1.0"
    const val SmartrefreshHead = "com.scwang.smartrefresh:SmartRefreshHeader:1.1.0"
    // cookie持久化
    const val cookie = "com.github.franmontiel:PersistentCookieJar:v1.0.1"
    // hilt
    const val hilt_plugin = "com.google.dagger:hilt-android-gradle-plugin:${Version.hilt_version}"
    const val hilt = "com.google.dagger:hilt-android:${Version.hilt_version}"
    const val hilt_compiler = "com.google.dagger:hilt-android-compiler:${Version.hilt_version}"

    // anko
    const val anko = "org.jetbrains.anko:anko-sdk25:0.10.8"

    // mmkv
    const val mmkv = "com.tencent:mmkv-static:1.0.23"

    // banner
    const val banner = "io.github.youth5201314:banner:2.2.2"

    // glide
    const val glide = "com.github.bumptech.glide:glide:4.13.0"

    // BRV
    const val BRV = "com.github.liangjingkanji:BRV:1.3.63"
}