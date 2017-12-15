# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Applications/android-sdk-macosx/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

## Retrofit
## https://github.com/square/retrofit
-dontwarn okio.**
-dontwarn javax.annotation.**
## https://square.github.io/retrofit/
-dontnote retrofit2.Platform
-dontwarn retrofit2.Platform$Java8
-keepattributes Signature
-keepattributes Exceptions
##

## Picasso
## https://github.com/square/picasso
-dontwarn com.squareup.okhttp.**
##

## Moshi
## https://github.com/square/moshi
-dontwarn okio.**
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault
-keepclasseswithmembers class * {
    @com.squareup.moshi.* <methods>;
}
-keep @com.squareup.moshi.JsonQualifier interface *
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}
## https://github.com/square/moshi/issues/345
-dontwarn org.jetbrains.annotations.**
-keep class com.kazakago.cleanarchitecture.web.entity.** { *; }
-keep class com.kazakago.cleanarchitecture.data.file.entity.** { *; }
##

## Kodein
## https://salomonbrys.github.io/Kodein/
-keepattributes Signature
##
