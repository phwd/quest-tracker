package com.facebook.common.android;

import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.Service;
import android.app.admin.DevicePolicyManager;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.location.Geocoder;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.PowerManager;
import android.os.Vibrator;
import android.renderscript.RenderScript;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.inputmethod.InputMethodManager;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.facebook.inject.AbstractLibraryModule;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.support.v4.net.ConnectivityManagerCompat;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import java.lang.annotation.Annotation;
import javax.annotation.Nullable;

@InjectorModule
public class AndroidModule extends AbstractLibraryModule {
    private static final Object _UL__ULSEP_android_content_pm_PackageInfo_ULSEP_LOCK = new Object();
    private static volatile PackageManager _UL__ULSEP_android_content_pm_PackageManager_ULSEP_INSTANCE;
    private static final Object _UL__ULSEP_android_content_pm_PackageManager_ULSEP_LOCK = new Object();
    private static final Object _UL__ULSEP_android_location_Geocoder_ULSEP_LOCK = new Object();
    private static final Object _UL__ULSEP_android_renderscript_RenderScript_ULSEP_LOCK = new Object();
    private static final Object _UL__ULSEP_androidx_localbroadcastmanager_content_LocalBroadcastManager_ULSEP_LOCK = new Object();
    private static final Object _UL__ULSEP_java_lang_String_ULSEP_com_facebook_common_android_PackageName_ULSEP_LOCK = new Object();

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_android_accounts_AccountManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_accounts_AccountManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(AccountManager.class)));
        public static final int _UL__ULSEP_android_app_ActivityManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_app_ActivityManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(ActivityManager.class)));
        public static final int _UL__ULSEP_android_app_Activity_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_app_Activity_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(Activity.class)));
        public static final int _UL__ULSEP_android_app_AlarmManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_app_AlarmManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(AlarmManager.class)));
        public static final int _UL__ULSEP_android_app_DownloadManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_app_DownloadManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(DownloadManager.class)));
        public static final int _UL__ULSEP_android_app_KeyguardManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_app_KeyguardManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(KeyguardManager.class)));
        public static final int _UL__ULSEP_android_app_NotificationManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_app_NotificationManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(NotificationManager.class)));
        public static final int _UL__ULSEP_android_app_SearchManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_app_SearchManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(SearchManager.class)));
        public static final int _UL__ULSEP_android_app_Service_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_app_Service_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(Service.class)));
        public static final int _UL__ULSEP_android_app_admin_DevicePolicyManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_app_admin_DevicePolicyManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(DevicePolicyManager.class)));
        public static final int _UL__ULSEP_android_content_ClipboardManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_content_ClipboardManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(ClipboardManager.class)));
        public static final int _UL__ULSEP_android_content_ContentResolver_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_content_ContentResolver_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(ContentResolver.class)));
        public static final int _UL__ULSEP_android_content_pm_ApplicationInfo_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_content_pm_ApplicationInfo_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(ApplicationInfo.class)));
        public static final int _UL__ULSEP_android_content_pm_PackageInfo_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_content_pm_PackageInfo_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(PackageInfo.class)));
        public static final int _UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(PackageManager.class)));
        public static final int _UL__ULSEP_android_hardware_SensorManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_hardware_SensorManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(SensorManager.class)));
        public static final int _UL__ULSEP_android_location_Geocoder_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_location_Geocoder_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(Geocoder.class)));
        public static final int _UL__ULSEP_android_location_LocationManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_location_LocationManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(LocationManager.class)));
        public static final int _UL__ULSEP_android_media_AudioManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_media_AudioManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(AudioManager.class)));
        public static final int _UL__ULSEP_android_media_MediaPlayer_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_media_MediaPlayer_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(MediaPlayer.class)));
        public static final int _UL__ULSEP_android_media_MediaRecorder_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_media_MediaRecorder_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(MediaRecorder.class)));
        public static final int _UL__ULSEP_android_net_ConnectivityManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_net_ConnectivityManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(ConnectivityManager.class)));
        public static final int _UL__ULSEP_android_net_NetworkInfo_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_net_NetworkInfo_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(NetworkInfo.class)));
        public static final int _UL__ULSEP_android_net_wifi_WifiManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_net_wifi_WifiManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(WifiManager.class)));
        public static final int _UL__ULSEP_android_os_Handler_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_os_Handler_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(Handler.class)));
        public static final int _UL__ULSEP_android_os_PowerManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_os_PowerManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(PowerManager.class)));
        public static final int _UL__ULSEP_android_os_Vibrator_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_os_Vibrator_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(Vibrator.class)));
        public static final int _UL__ULSEP_android_renderscript_RenderScript_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_renderscript_RenderScript_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(RenderScript.class)));
        public static final int _UL__ULSEP_android_telephony_TelephonyManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_telephony_TelephonyManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(TelephonyManager.class)));
        public static final int _UL__ULSEP_android_view_LayoutInflater_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_view_LayoutInflater_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(LayoutInflater.class)));
        public static final int _UL__ULSEP_android_view_WindowManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_view_WindowManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(WindowManager.class)));
        public static final int _UL__ULSEP_android_view_accessibility_AccessibilityManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_view_accessibility_AccessibilityManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(AccessibilityManager.class)));
        public static final int _UL__ULSEP_android_view_inputmethod_InputMethodManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_android_view_inputmethod_InputMethodManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(InputMethodManager.class)));
        public static final int _UL__ULSEP_androidx_fragment_app_FragmentActivity_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_androidx_fragment_app_FragmentActivity_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(FragmentActivity.class)));
        public static final int _UL__ULSEP_androidx_localbroadcastmanager_content_LocalBroadcastManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_androidx_localbroadcastmanager_content_LocalBroadcastManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(LocalBroadcastManager.class)));
        public static final int _UL__ULSEP_com_facebook_common_android_FbLocalBroadcastManager_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_facebook_common_android_FbLocalBroadcastManager_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(FbLocalBroadcastManager.class)));
        public static final int _UL__ULSEP_com_facebook_support_v4_net_ConnectivityManagerCompat_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_com_facebook_support_v4_net_ConnectivityManagerCompat_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(ConnectivityManagerCompat.class)));
        public static final int _UL__ULSEP_java_lang_Integer_ULSEP_com_facebook_common_android_AndroidSdkVersion_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_java_lang_Integer_ULSEP_com_facebook_common_android_AndroidSdkVersion_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(Integer.class, (Class<? extends Annotation>) AndroidSdkVersion.class)));
        public static final int _UL__ULSEP_java_lang_Object_ULSEP_com_facebook_common_android_WifiP2pManagerSystemService_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_java_lang_Object_ULSEP_com_facebook_common_android_WifiP2pManagerSystemService_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(Object.class, (Class<? extends Annotation>) WifiP2pManagerSystemService.class)));
        public static final int _UL__ULSEP_java_lang_Runtime_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_java_lang_Runtime_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(Runtime.class)));
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_facebook_common_android_PackageName_ULSEP_BINDING_ID = (UL.USE_STATIC_DI ? UL.id._UL__ULSEP_java_lang_String_ULSEP_com_facebook_common_android_PackageName_ULSEP_BINDING_ID : UL.id.dynamicId(Key.get(String.class, (Class<? extends Annotation>) PackageName.class)));
    }

    @AutoGeneratedAccessMethod
    public static final PackageInfo _UL__ULSEP_android_content_pm_PackageInfo_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (PackageInfo) UL.factorymap.get(UL_id._UL__ULSEP_android_content_pm_PackageInfo_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final PackageManager _UL__ULSEP_android_content_pm_PackageManager_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_android_content_pm_PackageManager_ULSEP_INSTANCE == null) {
            synchronized (_UL__ULSEP_android_content_pm_PackageManager_ULSEP_LOCK) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_android_content_pm_PackageManager_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        _UL__ULSEP_android_content_pm_PackageManager_ULSEP_INSTANCE = providePackageManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(injectorLike.getApplicationInjector()));
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_android_content_pm_PackageManager_ULSEP_INSTANCE;
    }

    @AutoGeneratedFactoryMethod
    public static final ConnectivityManager _UL__ULSEP_android_net_ConnectivityManager_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideConnectivityManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @AutoGeneratedFactoryMethod
    public static final WifiManager _UL__ULSEP_android_net_wifi_WifiManager_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return provideWifiManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @AutoGeneratedFactoryMethod
    public static final PowerManager _UL__ULSEP_android_os_PowerManager_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return providePowerManager(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(injectorLike));
    }

    @ProviderMethod
    static PowerManager providePowerManager(@UnsafeContextInjection Context context) {
        return (PowerManager) context.getSystemService("power");
    }

    @ProviderMethod
    @Nullable
    static ConnectivityManager provideConnectivityManager(@UnsafeContextInjection Context context) {
        try {
            return (ConnectivityManager) context.getSystemService("connectivity");
        } catch (Exception unused) {
            return null;
        }
    }

    @ProviderMethod
    static WifiManager provideWifiManager(@UnsafeContextInjection Context context) {
        return (WifiManager) context.getApplicationContext().getSystemService("wifi");
    }

    @ApplicationScoped
    @ProviderMethod
    static PackageManager providePackageManager(@UnsafeContextInjection Context context) {
        return context.getApplicationContext().getPackageManager();
    }
}