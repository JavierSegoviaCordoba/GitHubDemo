plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(28)
    buildToolsVersion = "29.0.0"
    defaultConfig {
        applicationId = "com.javiersc.githubdemo"
        minSdkVersion(21)
        targetSdkVersion(28)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dataBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.40")
    implementation("androidx.appcompat:appcompat:1.1.0-beta01")
    implementation("androidx.core:core-ktx:1.0.2")
    implementation("androidx.constraintlayout:constraintlayout:2.0.0-beta2")
    //RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.1.0-alpha06")
    //Jetpack
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0-alpha01")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0-alpha01")
    implementation("androidx.room:room-runtime:2.1.0")
    kapt("androidx.room:room-compiler:2.1.0")
    implementation("androidx.room:room-ktx:2.1.0")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.6.0")
    implementation("com.squareup.retrofit2:converter-gson:2.6.0")
    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.0-M2")
    //Glide
    implementation("com.github.bumptech.glide:glide:4.9.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.9.0")
    //Koin
    implementation("org.koin:koin-androidx-viewmodel:2.0.1")
    implementation("org.koin:koin-androidx-ext:2.0.1")
    //Arrow
    implementation("io.arrow-kt:arrow-core-data:0.9.0")
    //Material Components
    implementation("com.google.android.material:material:1.1.0-alpha07")
    //GitHubApi wrapper
    implementation(project(":githubapi"))
}
