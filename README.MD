# Compose Multiplatform Application

## Before running!
 - check your system with [KDoctor](https://github.com/Kotlin/kdoctor)
 - install JDK 17 or higher on your machine
 - add `local.properties` file to the project root and set a path to Android SDK there

### Android
To run the application on android device/emulator:  
 - open project in Android Studio and run imported android run configuration

To build the application bundle:
 - run `./gradlew :composeApp:assembleDebug`
 - find `.apk` file in `composeApp/build/outputs/apk/debug/composeApp-debug.apk`
Run android simulator UI tests: `./gradlew :composeApp:pixel5Check`

### iOS
To run the application on iPhone device/simulator:
 - Open `iosApp/iosApp.xcproject` in Xcode and run standard configuration
 - Or use [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) for Android Studio
Run iOS simulator UI tests: `./gradlew :composeApp:iosSimulatorArm64Test`

<img width="394" alt="Screenshot 2024-08-08 at 13 10 52" src="https://github.com/user-attachments/assets/f489f512-0e5f-47e0-bde6-53e1e963aba3">

<img width="418" alt="Screenshot 2024-08-08 at 13 15 09" src="https://github.com/user-attachments/assets/6c5f2afd-35af-472b-9071-cce8e1c5a157">
