# Android_Reference

## FirstFragment , SecondFragment
- jetpack Navigation, safe args

## RetrofitFragment
- Retrofit2, Coroutine, RecyclerView, Glide, ViewModel, Databinding

## MainActivity
- Notification (8.0이상부터 channel 생성, S 이상부터 FLAG다름) 
- SharedPreference save and load
- Worker 호출

## CustomApplication
- Notification channel 생성 (오레오부터 채널 생성해줘야함)

## Worker
- Android 12 (S) 이전 버전에서는 Workmanager + 포그라운드 사용 가능
- PeriodicWorker는 최소 15분 간격 가능
- CustomWorker, CustomCoroutineWorker - 백그라운드에서 수행할 일 작성
- CustomWorkRequest - request를 만들어서 enqueue하면 수행됨

## Vibrator
- manifests 권한필요, API 26~30 / 31 ~ 에 따라 코드 갈림


## 플러그인 및 라이브러리
- safe args 쓰려면 Project build.gradle에 추가   
buildscript {   
    repositories {   
        google()   
    }   
    dependencies {   
        def nav_version = "2.5.3"   
        classpath "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"   
    }   
}   
##
    id 'androidx.navigation.safeargs.kotlin'
    id 'kotlin-kapt'


    implementation 'com.squareup.retrofit2:retrofit:2.6.2'
    implementation "com.squareup.retrofit2:converter-moshi:2.4.0"

    implementation "androidx.fragment:fragment-ktx:1.5.5"

    implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.5.1"

    implementation "androidx.navigation:navigation-runtime-ktx:2.5.3"
    implementation "androidx.navigation:navigation-fragment-ktx:2.5.3"
    implementation "androidx.navigation:navigation-ui-ktx:2.5.3"

    implementation "androidx.room:room-ktx:2.4.3"

    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1"

    implementation "androidx.work:work-runtime-ktx:2.7.1"

    implementation "androidx.recyclerview:recyclerview:1.2.1"
    // For control over item selection of both touch and mouse driven selection
    implementation "androidx.recyclerview:recyclerview-selection:1.1.0"

    implementation 'com.github.bumptech.glide:glide:4.14.2'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.14.2'
