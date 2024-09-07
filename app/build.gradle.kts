plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.devtools.ksp)
}

android {
    namespace = "com.sagmade.vinoapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sagmade.vinoapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    viewBinding { enable = true }
    packagingOptions {
        resources {
            excludes += setOf(
                "META-INF/*.kotlin_module",
                "META-INF/AL2.0",
                "META-INF/LGPL2.1",
                "META-INF/LICENSE.md",
                "META-INF/LICENSE-notice.md"
            )
        }
    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //Glide
    implementation(libs.com.google.dagger)
    implementation(libs.com.github.bumptech.glide)

    //Retrofit
    implementation(libs.com.squareup.retrofit2)
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")


    //swipeRefreshLayout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    //Room
    implementation(libs.androidx.room)
    ksp("androidx.room:room-compiler:2.6.1")

    //Navegation
    implementation(libs.androidx.navigation)
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")



    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
