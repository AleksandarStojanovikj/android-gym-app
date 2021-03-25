# android-gym-app mobile

## Description

Mobile app for a school project that manages gym offers and subscriptions.

## Installation
#### Clone the repository 
```bash 
$ git clone https://github.com/AleksandarStojanovikj/android-gym-app.git
```

#### Update properties

Set the URL to the API in `GymApiClient.java`


```java
 private static final String BASE_URL = "YOUR_URL";
```

#### Build APK
```bash
$ cd android-gym-app/mobile
$ ./gradlew assembleDebug
```
This creates an APK named `app-debug.apk` in `android-gym-app/mobile/build/outputs/apk/`. The file is already signed with the debug key and aligned with [zipalign](https://developer.android.com/studio/command-line/zipalign), so you can immediately install it on a device.

Or alternatively you can build it and deploy it through [Android Studio](https://developer.android.com/studio).
