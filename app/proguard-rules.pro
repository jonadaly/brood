# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

## RULES FOR OKIO AND OKHTTP, USED BY RETROFIT
-dontwarn okio.**
-dontwarn okhttp3.**
-dontwarn retrofit2.Platform$Java8

## RULES FOR MPANDROIDCHART
-keep class com.github.mikephil.charting.** { *; }

## RULES FOR REALM
-keep class io.realm.annotations.RealmModule
-keep @io.realm.annotations.RealmModule class *
-keep class io.realm.internal.Keep
-keep @io.realm.internal.Keep class * { *; }
-dontwarn javax.**
-dontwarn io.realm.*

## RULES FOR DAGGER2
-dontwarn com.google.errorprone.annotations.**

## RULES FOR PICASSO
-dontwarn com.squareup.okhttp.**