object AppConfig {
    const val versionName = "1.0"
    const val versionCode = 1
    const val applicationId = "win.regin.mvvm"
    const val buildToolsVersion = "29.0.0"
    const val compileSdkVersion = 28
    const val targetSdkVersion = 28
    const val minSdkVersion = 23
}

object Version {

    const val gradleVersion = "3.4.1"
    const val kotlinVersion = "1.3.40"
    const val appcompatVersion = "1.0.2"
    const val ktxVersion = "1.0.2"
    const val constraintlayoutVersion = "1.1.3"
    const val junitVersion = "4.12"
    const val extUnitVersion = "1.1.0"
    const val espressoVersion = "3.2.0"
    const val lifecycleVersion = "2.2.0-alpha01"
    const val retrofitVersion = "2.6.0"
    const val coroutinesVersion = "1.2.1"
    const val okhttp3LogVersion = "3.12.1"
    const val ankoVersion = "0.10.7"
    const val materialVersion = "1.1.0-alpha07"
    const val objectboxVersion  = "2.3.4"
    const val utilcodeVersion="1.24.4"
    const val fragmentationVersion="1.0.1"
    const val baseAdapterVersion="2.9.45-androidx"
    const val superTextVersion="2.2.4"
    const val smartVersion="1.1.0-andx-11"
    const val glideVersion="4.9.0"
}

object Ext {
    const val buildGradle = "com.android.tools.build:gradle:${Version.gradleVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlinVersion}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Version.kotlinVersion}"
    const val appcompat = "androidx.appcompat:appcompat:${Version.appcompatVersion}"
    const val ktx = "androidx.core:core-ktx:${Version.ktxVersion}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Version.constraintlayoutVersion}"

    const val junit = "junit:junit:${Version.junitVersion}"
    const val extUnit = "androidx.test.ext:junit:${Version.extUnitVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Version.espressoVersion}"

    //https://developer.android.google.cn/jetpack/androidx/releases/lifecycle#declaring_dependencies
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Version.lifecycleVersion}"
    const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycleVersion}"

    //https://github.com/square/retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofitVersion}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Version.retrofitVersion}"
    const val okhttp3Log = "com.squareup.okhttp3:logging-interceptor:${Version.okhttp3LogVersion}"

    //https://github.com/Kotlin/kotlinx.coroutines
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutinesVersion}"
    const val anko = "org.jetbrains.anko:anko-commons:${Version.ankoVersion}"
    const val material = "com.google.android.material:material:${Version.materialVersion}"

    // objectbox
    const val objectboxProcessor = "io.objectbox:objectbox-processor:${Version.objectboxVersion}"
    const val objectboxKotlin = "io.objectbox:objectbox-kotlin:${Version.objectboxVersion}"
    const val objectboxAndroid = "io.objectbox:objectbox-android:${Version.objectboxVersion}"
    const val objectboxGradlePlugin = "io.objectbox:objectbox-gradle-plugin:${Version.objectboxVersion}"
    const val objectboxBrowser = "io.objectbox:objectbox-android-objectbrowser:${Version.objectboxVersion}"

    //util
    const val util = "com.blankj:utilcodex:${Version.utilcodeVersion}"

    //fragment
    const val fragmentation ="me.yokeyword:fragmentationx:${Version.fragmentationVersion}"

    //baseAdapter
    const val baseAdapter ="com.github.CymChad:BaseRecyclerViewAdapterHelper:${Version.baseAdapterVersion}"
    const val superText ="com.github.lygttpod:SuperTextView:${Version.superTextVersion}"
    const val smartRefresh ="com.scwang.smartrefresh:SmartRefreshLayout:${Version.smartVersion}"
    const val glide ="com.github.bumptech.glide:glide:${Version.glideVersion}"
    const val glideCompiler ="com.github.bumptech.glide:compiler:${Version.glideVersion}"


}