#**************************基本不用动区域******************************
#---------------------------------基本指令区----------------------------------
# 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
# 代码混淆压缩比，在0~7之间，默认为5
-optimizationpasses 5
# 优化时允许优化并修改类和类的成员的访问修饰符
-allowaccessmodification
# 抑制警告 忽略警告
-ignorewarnings
# 关闭压缩功能。默认情况下，会开启压缩;
-dontshrink
# 关闭优化功能。默认情况下启用优化。
#-dontoptimize
# 不使用大小写混合类名(windows对大小写不敏感，必须开启此项)
-dontusemixedcaseclassnames
# 处理第三方中非public类
-dontskipnonpubliclibraryclasses
# 处理第三方中public的类成员
-dontskipnonpubliclibraryclassmembers
# 关闭预校验
-dontpreverify
# 在处理期间打印日志
-verbose
# apk包内所有 class 的内部结构
-dump class_files.txt
# 未混淆的类和成员
-printseeds seeds.txt
# 列出从 apk 中删除的代码
-printusage unused.txt
# 混淆前后的映射
-printmapping mapping.txt
# 保护代码中的Annotation(注解)等不被混淆
-keepattributes *Annotation*,InnerClasses,Signature,EnclosingMethod,SourceFile,LineNumberTable
# 把所有重命名的类重新打包到给定的单一包中,默认根目录
-repackageclasses a
#-obfuscationdictionary {filename}    使用给定文件中的关键字作为要混淆方法的名称   不一定是用abcd...
###################################################################################

-dontwarn com.thoughtworks.xstream.io.xml.StaxWriter
-dontwarn java.nio.file.Files
-dontwarn java.nio.file.Path
-dontwarn java.nio.file.OpenOption
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn org.apache.http.**
-dontwarn android.net.http.AndroidHttpClient
-dontwarn com.android.volley.toolbox.**
-dontwarn android.support.**
-dontwarn com.google.android.gms.**
-dontwarn com.thoughtworks.xstream.**
#----------------------------------------------------------------------------
#androidx
-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**
#androidx
#---------------------------------默认保留区---------------------------------
#继承activity,application,service,broadcastReceiver,contentprovider....不进行混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Application
-keep public class * extends android.support.multidex.MultiDexApplication
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class * extends android.support.v4.**
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.** {*;}
-keep class javax.xml**  { *; }
-keep class android.os.** {*;}
-keep class android.support.** { *;}
-keep interface android.support.** { *;}
-keep class android.support.v4.** { *; }
-dontwarn android.support.v4.**
-dontwarn android.support.**
# 保留自定义控件（继承自View）不被混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
#这个主要是在layout 中写的onclick方法android:onclick="onClick"，不进行混淆
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
-dontwarn android.support.v4.**
-keep class android.support.v4.** {
    <fields>;
    <methods>;
}
-keep interface  android.support.v4.app.** {
    <fields>;
    <methods>;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
# 对于R（资源）下的所有类及其方法，都不能被混淆
-keep class **.R$* {
 *;
}
# 对于带有回调函数Event的，不能被混淆
-keepclassmembers class * {
    void *(*Event);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
#// natvie 方法不混淆
-keepclasseswithmembernames class * {
    native <methods>;
}
# Understand the @Keep support annotation.
-keep class android.support.annotation.Keep
-keep @android.support.annotation.Keep class * {*;}
-keepclasseswithmembers class * {
    @android.support.annotation.Keep <methods>;
}
-keepclasseswithmembers class * {
    @android.support.annotation.Keep <fields>;
}
-keepclasseswithmembers class * {
    @android.support.annotation.Keep <init>(...);
}
#----------------------------------------------------------------------------

#---------------------------------webview------------------------------------
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, java.lang.String);}

-keepclassmembers class * extends android.webkit.WebChromeClient {
  public void *(android.webkit.WebView, java.lang.String);
}
#---------------------------------------------------------------------------------------------------

#自己项目特殊处理代码

#okhttp
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**

# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Exceptions

# Retrolambda
-dontwarn java.lang.invoke.*

# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
###rxandroid-1.2.1
-keepclassmembers class rx.android.**{*;}

# Gson
-keep class com.google.gson.stream.** { *; }
-keepattributes EnclosingMethod
-keep class org.xz_sale.entity.**{*;}
-keep class com.google.gson.** {*;}
-keep class com.google.**{*;}
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
# Prevent R8 from leaving Data object members always null
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}
# Gson

-dontwarn **.bean.**
-keep class **.bean.**{*;}

-dontwarn com.smart.gc.greendao.**
-keep class com.smart.gc.greendao.**{*;}

#JNI
-dontwarn com.smart.gc.service.CanService
-keep class com.smart.gc.service.CanService { *;}

-dontwarn com.smart.gc.life.LifeMainBroadcastReceiver
-keep class com.smart.gc.life.LifeMainBroadcastReceiver {*;}

#CanFrame
-dontwarn com.smart.gc.can.CanFrame
-keep class com.smart.gc.can.CanFrame { *;}

 #eventbus混淆
-keepclassmembers class * {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# Only required if you use AsyncExecutor
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
    <init>(java.lang.Throwable);
}
 #eventbus混淆

 #Glide
-dontwarn com.bumptech.glide.**
-keep class com.bumptech.glide.**{*;}
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
 #Glide

#Bugly-Start
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
#End-Bugly

#greendao
-keep class org.greenrobot.greendao.**{*;}
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties
-keep class net.sqlcipher.database.**{*;}
-keep public interface net.sqlcipher.database.**
-dontwarn net.sqlcipher.database.**
-dontwarn org.greenrobot.greendao.**
#greendao

#smarttable
-dontwarn com.bin.david.form.**
-keep class com.bin.david.form.**{*;}
#smarttable

#mqtt
-dontwarn org.eclipse.paho.**
-keep class org.eclipse.paho.**{*;}
#mqtt

#convenientbanner
-dontwarn com.bigkoo.convenientbanner.**
-keep class com.bigkoo.convenientbanner.**{*;}
-keep public class * extends android.widget.BaseAdapter {*;}
#convenientbanner

#progressbar
-dontwarn fr.castorflex.android.circularprogressbar.**
-keep class fr.castorflex.android.circularprogressbar.**{*;}
#progressbar

#友盟+
-keep class com.umeng.** {*;}
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#友盟+

#qrcode
-dontwarn com.github.yoojia.qrcode.**
-keep class com.github.yoojia.qrcode.**{*;}
#qrcode

#dialog
-dontwarn androidx.swiperefreshlayout.widget.**
-keep class androidx.swiperefreshlayout.widget.**{*;}
#dialog

#tencent
-dontwarn com.tencent.**
-keep class com.tencent.**{*;}
#tencent