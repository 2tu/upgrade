# UpgradeLibrary

UpgradeLibrary is a use DownloadManager for upgrade in Android applications.

UpgradeLibrary check isEnable DownloadManger,show dialog user enable.

## Requirements

UpgradeLibrary can be included in has DownloadManger Android device application.

UpgradeLibrary supports Android 2.3.2 (Gingerbread API level 9) and later.

## Using UpgradeLibrary in your application

If you are building with Gradle, simply add the following line to the dependencies section of your build.gradle file:

#### Step 1. Add the JitPack repository to your build file  
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}

#### Step 2. Add the dependency  

	dependencies {
		compile 'com.github.2tu:upgrade:0.1'
	}
