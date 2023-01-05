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

## ForegroundService
- API 28이상부터 포그라운드서비스 권한 필요   
- Android 12이상은 startForegroundServcie()   
- 12 이전은 startForeground()   

- 포그라운드 서비스가 location , camera, microphone에 접근하려면 manifest안에   
<service ...   
   android:foregroundServiceType="location|camera|microphone" />   
   
### onStartCommand의 반환값
- START_NOT_STICKY: 서비스를 명시적으로 다시 시작할 때 까지 만들지 않습니다.
- START_STICKY: 서비스를 다시 만들지만 마지막 Intent를 onStartCommand의 인자로 다시 전달하지 않습니다. 이는 일단 계속 살아있어야되지만 별다른 동작이 필요하지 않은 음악앱같은 서비스에 적절합니다.   
- START_REDELIVER_INTENT: 마지막 Intent를 onStartCommand의 인자로 다시 전달해줍니다. 즉각적인 반응이 필요한 파일 다운로드 서비스 같은 곳에 적합합니다.   

###
- Foreground를 실행시킨 후에 CoroutineWorker를 실행하면 Activity를 종료해도 진동이 잘 울림   
- ForegroundService - Notification 창에 고정되고, Activity를 실행한 것과 동일한 기능을 함   

### Channel,Notification id관계
- Channel id 와 Notification id 동일 -> 1개의 알림창만 생성
- Channel id 와 Notification id 다름 -> 여러개의 알림창 생성
- Channel id가 1개면 시스템 정보에서 1개 채널만 생성
- Channel id가 여러개면 시스템 정보에서 여러개 채널 생성

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
