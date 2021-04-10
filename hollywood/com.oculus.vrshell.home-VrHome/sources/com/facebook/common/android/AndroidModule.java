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
import com.facebook.inject.InjectorLike;
import com.facebook.support.v4.net.ConnectivityManagerCompat;
import com.facebook.ultralight.UL;
import com.google.inject.Key;
import java.lang.annotation.Annotation;

public class AndroidModule extends AbstractLibraryModule {
    private static final Object $ul_$xXXandroid_content_pm_PackageInfo$xXXLOCK = new Object();
    private static final Object $ul_$xXXandroid_content_pm_PackageManager$xXXLOCK = new Object();
    private static final Object $ul_$xXXandroid_location_Geocoder$xXXLOCK = new Object();
    private static final Object $ul_$xXXandroid_renderscript_RenderScript$xXXLOCK = new Object();
    private static final Object $ul_$xXXandroidx_localbroadcastmanager_content_LocalBroadcastManager$xXXLOCK = new Object();
    private static final Object $ul_$xXXjava_lang_String$xXXcom_facebook_common_android_PackageName$xXXLOCK = new Object();

    public static final class UL_id {
        public static final int $ul_$xXXandroid_accounts_AccountManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_accounts_AccountManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(AccountManager.class)));
        public static final int $ul_$xXXandroid_app_Activity$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_app_Activity$xXXBINDING_ID : UL.id.dynamicId(Key.get(Activity.class)));
        public static final int $ul_$xXXandroid_app_ActivityManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_app_ActivityManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(ActivityManager.class)));
        public static final int $ul_$xXXandroid_app_AlarmManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_app_AlarmManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(AlarmManager.class)));
        public static final int $ul_$xXXandroid_app_DownloadManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_app_DownloadManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(DownloadManager.class)));
        public static final int $ul_$xXXandroid_app_KeyguardManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_app_KeyguardManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(KeyguardManager.class)));
        public static final int $ul_$xXXandroid_app_NotificationManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_app_NotificationManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(NotificationManager.class)));
        public static final int $ul_$xXXandroid_app_SearchManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_app_SearchManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(SearchManager.class)));
        public static final int $ul_$xXXandroid_app_Service$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_app_Service$xXXBINDING_ID : UL.id.dynamicId(Key.get(Service.class)));
        public static final int $ul_$xXXandroid_app_admin_DevicePolicyManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_app_admin_DevicePolicyManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(DevicePolicyManager.class)));
        public static final int $ul_$xXXandroid_content_ClipboardManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_content_ClipboardManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(ClipboardManager.class)));
        public static final int $ul_$xXXandroid_content_ContentResolver$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_content_ContentResolver$xXXBINDING_ID : UL.id.dynamicId(Key.get(ContentResolver.class)));
        public static final int $ul_$xXXandroid_content_pm_ApplicationInfo$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_content_pm_ApplicationInfo$xXXBINDING_ID : UL.id.dynamicId(Key.get(ApplicationInfo.class)));
        public static final int $ul_$xXXandroid_content_pm_PackageInfo$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_content_pm_PackageInfo$xXXBINDING_ID : UL.id.dynamicId(Key.get(PackageInfo.class)));
        public static final int $ul_$xXXandroid_content_pm_PackageManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_content_pm_PackageManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(PackageManager.class)));
        public static final int $ul_$xXXandroid_hardware_SensorManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_hardware_SensorManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(SensorManager.class)));
        public static final int $ul_$xXXandroid_location_Geocoder$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_location_Geocoder$xXXBINDING_ID : UL.id.dynamicId(Key.get(Geocoder.class)));
        public static final int $ul_$xXXandroid_location_LocationManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_location_LocationManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(LocationManager.class)));
        public static final int $ul_$xXXandroid_media_AudioManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_media_AudioManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(AudioManager.class)));
        public static final int $ul_$xXXandroid_media_MediaPlayer$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_media_MediaPlayer$xXXBINDING_ID : UL.id.dynamicId(Key.get(MediaPlayer.class)));
        public static final int $ul_$xXXandroid_media_MediaRecorder$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_media_MediaRecorder$xXXBINDING_ID : UL.id.dynamicId(Key.get(MediaRecorder.class)));
        public static final int $ul_$xXXandroid_net_ConnectivityManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_net_ConnectivityManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(ConnectivityManager.class)));
        public static final int $ul_$xXXandroid_net_NetworkInfo$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_net_NetworkInfo$xXXBINDING_ID : UL.id.dynamicId(Key.get(NetworkInfo.class)));
        public static final int $ul_$xXXandroid_net_wifi_WifiManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_net_wifi_WifiManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(WifiManager.class)));
        public static final int $ul_$xXXandroid_os_Handler$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_os_Handler$xXXBINDING_ID : UL.id.dynamicId(Key.get(Handler.class)));
        public static final int $ul_$xXXandroid_os_PowerManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_os_PowerManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(PowerManager.class)));
        public static final int $ul_$xXXandroid_os_Vibrator$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_os_Vibrator$xXXBINDING_ID : UL.id.dynamicId(Key.get(Vibrator.class)));
        public static final int $ul_$xXXandroid_renderscript_RenderScript$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_renderscript_RenderScript$xXXBINDING_ID : UL.id.dynamicId(Key.get(RenderScript.class)));
        public static final int $ul_$xXXandroid_telephony_TelephonyManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_telephony_TelephonyManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(TelephonyManager.class)));
        public static final int $ul_$xXXandroid_view_LayoutInflater$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_view_LayoutInflater$xXXBINDING_ID : UL.id.dynamicId(Key.get(LayoutInflater.class)));
        public static final int $ul_$xXXandroid_view_WindowManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_view_WindowManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(WindowManager.class)));
        public static final int $ul_$xXXandroid_view_accessibility_AccessibilityManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_view_accessibility_AccessibilityManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(AccessibilityManager.class)));
        public static final int $ul_$xXXandroid_view_inputmethod_InputMethodManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroid_view_inputmethod_InputMethodManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(InputMethodManager.class)));
        public static final int $ul_$xXXandroidx_fragment_app_FragmentActivity$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroidx_fragment_app_FragmentActivity$xXXBINDING_ID : UL.id.dynamicId(Key.get(FragmentActivity.class)));
        public static final int $ul_$xXXandroidx_localbroadcastmanager_content_LocalBroadcastManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXandroidx_localbroadcastmanager_content_LocalBroadcastManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(LocalBroadcastManager.class)));
        public static final int $ul_$xXXcom_facebook_common_android_FbLocalBroadcastManager$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXcom_facebook_common_android_FbLocalBroadcastManager$xXXBINDING_ID : UL.id.dynamicId(Key.get(FbLocalBroadcastManager.class)));
        public static final int $ul_$xXXcom_facebook_support_v4_net_ConnectivityManagerCompat$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXcom_facebook_support_v4_net_ConnectivityManagerCompat$xXXBINDING_ID : UL.id.dynamicId(Key.get(ConnectivityManagerCompat.class)));
        public static final int $ul_$xXXjava_lang_Integer$xXXcom_facebook_common_android_AndroidSdkVersion$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXjava_lang_Integer$xXXcom_facebook_common_android_AndroidSdkVersion$xXXBINDING_ID : UL.id.dynamicId(Key.get(Integer.class, (Class<? extends Annotation>) AndroidSdkVersion.class)));
        public static final int $ul_$xXXjava_lang_Object$xXXcom_facebook_common_android_WifiP2pManagerSystemService$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXjava_lang_Object$xXXcom_facebook_common_android_WifiP2pManagerSystemService$xXXBINDING_ID : UL.id.dynamicId(Key.get(Object.class, (Class<? extends Annotation>) WifiP2pManagerSystemService.class)));
        public static final int $ul_$xXXjava_lang_Runtime$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXjava_lang_Runtime$xXXBINDING_ID : UL.id.dynamicId(Key.get(Runtime.class)));
        public static final int $ul_$xXXjava_lang_String$xXXcom_facebook_common_android_PackageName$xXXBINDING_ID = (UL.USE_STATIC_DI ? UL.id.$ul_$xXXjava_lang_String$xXXcom_facebook_common_android_PackageName$xXXBINDING_ID : UL.id.dynamicId(Key.get(String.class, (Class<? extends Annotation>) PackageName.class)));
    }

    public static final PackageInfo $ul_$xXXandroid_content_pm_PackageInfo$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (PackageInfo) UL.factorymap.get(UL_id.$ul_$xXXandroid_content_pm_PackageInfo$xXXBINDING_ID, $ul_injector);
    }
}
