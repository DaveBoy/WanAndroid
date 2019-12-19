object AppConfig {
    const val versionName = "1.0"
    const val versionCode = 1
    const val applicationId = "com.daveboy.wanandroid"
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
    const val baseAdapterVersion="2.9.45-androidx"
    const val smartVersion="1.1.0-andx-11"
    const val glideVersion="4.9.0"
    const val flexboxVersion="1.1.0"
    const val koin_version="2.1.0-alpha-8"
    const val viewPaper2Version="1.0.0"

}

object Ext {
    const val buildGradle = "com.android.tools.build:gradle:${Version.gradleVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlinVersion}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Version.kotlinVersion}"
    const val appcompat = "androidx.appcompat:appcompat:${Version.appcompatVersion}"
    const val ktx = "androidx.core:core-ktx:${Version.ktxVersion}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Version.constraintlayoutVersion}"
    const val viewPaper2 = "androidx.viewpager2:viewpager2:${Version.viewPaper2Version}"

    //test
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

    //util
    const val util = "com.blankj:utilcodex:${Version.utilcodeVersion}"

    //baseAdapter
    const val baseAdapter ="com.github.CymChad:BaseRecyclerViewAdapterHelper:${Version.baseAdapterVersion}"

    const val smartRefresh ="com.scwang.smartrefresh:SmartRefreshLayout:${Version.smartVersion}"
    const val glide ="com.github.bumptech.glide:glide:${Version.glideVersion}"
    const val glideCompiler ="com.github.bumptech.glide:compiler:${Version.glideVersion}"
    const val flexbox ="com.google.android:flexbox:${Version.flexboxVersion}"

    // Koin https://github.com/InsertKoinIO/koin
    const val koinAndroid= "org.koin:koin-androidx-scope:${Version.koin_version}"
    const val koinViewModel= "org.koin:koin-androidx-viewmodel:${Version.koin_version}"
    const val koinFragment= "org.koin:koin-androidx-fragment:${Version.koin_version}"
    const val koinExt= "org.koin:koin-androidx-ext:${Version.koin_version}"
}