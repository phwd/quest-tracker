package com.facebook.ultralight;

import com.facebook.common.android.AndroidModule;
import com.facebook.common.manifest.ManifestModule;
import com.facebook.common.time.TimeModule;
import com.facebook.config.application.FbAppTypeModule;
import com.facebook.gk.sessionless.GkSessionlessModule;
import com.facebook.inject.AbstractPrivateModule;
import com.facebook.inject.ApplicationScope;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.Binder;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.ContextScope;
import com.facebook.inject.ContextScoped;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.SpecialModule;
import com.facebook.inject.UltralightMultiBind;
import com.facebook.mobileconfig.impl.module.MobileConfigFactoryImplModule;
import com.facebook.mobileconfig.ota.MobileConfigOTAUtilModule;
import com.facebook.tigon.oktigon.OkTigonService;
import com.facebook.tigon.oktigon.OkTigonServiceHolder;
import com.google.inject.Key;
import com.oculus.android.os.internal.inject.InternalModule;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.auth.device.DeviceAuthTokenStore;
import com.oculus.auth.device.noop_subscriber.NoOpDeviceAuthTokenSubscriber;
import com.oculus.authapi.inject.CallerInfoProviderImpl;
import com.oculus.authapi.inject.OVRAuthModule;
import com.oculus.common.init.AppInitModule;
import com.oculus.dsatauth.DsatFetcher;
import com.oculus.errorreporting.ErrorReportingModule;
import com.oculus.executors.ExecutorsModule;
import com.oculus.http.common.HttpModule;
import com.oculus.http.core.ApiModule;
import com.oculus.http.core.ApiResponseConverter;
import com.oculus.http.core.HttpCoreLogger;
import com.oculus.http.core.LoggingErrorHandler;
import com.oculus.http.core.endpoint.EndpointModule;
import com.oculus.http.core.interceptor.OculusAuthorizationInterceptor;
import com.oculus.http.socketconfig.SocketConfigModule;
import com.oculus.http.useragent.UserAgentFactory;
import com.oculus.http.useragent.UserAgentModule;
import com.oculus.locale.LocaleModule;
import com.oculus.logging.utils.EventManagerImpl;
import com.oculus.logging.utils.StorageLoggingUtils;
import com.oculus.logging.utils.UtilsModule;
import com.oculus.managed.ManagedMode;
import com.oculus.mobileconfig.init.MobileConfigConfiguration;
import com.oculus.mobileconfig.init.MobileConfigInit;
import com.oculus.mobileconfig.init.MobileConfigInitModule;
import com.oculus.mobileconfig.init.MobileConfigLoginHandler;
import com.oculus.mobileconfig.init.MobileConfigLogoutHandler;
import com.oculus.mobileconfig.updater.MobileConfigUpdaterInit;
import com.oculus.multiuser.UserClassifier;
import com.oculus.oktigon.OculusOkTigonModule;
import com.oculus.ossdk.inject.OsSdkModule;
import com.oculus.time.Clock;
import com.oculus.updater.OculusUpdaterAppModule;
import com.oculus.updater.core.broadcast.BroadcastState;
import com.oculus.updater.core.logging.OSUpdateEventLogger;
import com.oculus.updater.core.monitors.BatteryMonitor;
import com.oculus.updater.core.monitors.CheckPeriodMonitor;
import com.oculus.updater.core.monitors.ConditionManager;
import com.oculus.updater.core.monitors.IdleMonitor;
import com.oculus.updater.core.monitors.SystemReceiver;
import com.oculus.updater.core.monitors.SystemUpdatePolicyMonitor;
import com.oculus.updater.core.monitors.WifiMonitor;
import com.oculus.updater.core.os.DeviceTokenUpdateSubscriber;
import com.oculus.updater.core.os.OSUpdateManager;
import com.oculus.updater.core.os.SystemProperties;
import com.oculus.updater.core.os.UpdateEngine;
import com.oculus.updater.core.os.UpdateMonitor;
import com.oculus.updater.core.os.contract.OSUpdateServiceContract;
import com.oculus.updater.credentialsmanager.CredentialsManagerModule;
import com.oculus.updater.credentialsmanager.OSUpdateAuthDatastore;
import com.oculus.updater.credentialsmanager.OSUpdateCredentialsManager;
import com.oculus.updater.device.DeviceInfo;
import com.oculus.updater.gk.GatekeeperHelper;
import com.oculus.updater.init.AppInitializer;
import com.oculus.updater.net.ApiDispatcher;
import com.oculus.updater.net.NetworkModule;
import com.oculus.updater.net.OSUpdateProvider;
import com.oculus.updater.ovrsvcclient.OVRServiceClient;
import com.oculus.updater.prefs.OSUpdaterSharedPreferences;
import com.oculus.updater.prefs.PrefsModule;
import com.oculus.util.device.DeviceUtils;
import com.oculus.util.task.TaskDelayFactory;
import com.oculus.xanalytics.OculusXAnalyticsModule;
import com.oculus.xanalytics.OculusXAnalyticsProvider;

public final class UL {
    public static final boolean USE_STATIC_DI = true;

    public static final class id {
        public static final int _UL__ULSEP_android_accounts_AccountManager_ULSEP_BINDING_ID = 2094;
        public static final int _UL__ULSEP_android_app_ActivityManager_ULSEP_BINDING_ID = 2069;
        public static final int _UL__ULSEP_android_app_Activity_ULSEP_BINDING_ID = 2132;
        public static final int _UL__ULSEP_android_app_AlarmManager_ULSEP_BINDING_ID = 2113;
        public static final int _UL__ULSEP_android_app_DownloadManager_ULSEP_BINDING_ID = 2122;
        public static final int _UL__ULSEP_android_app_KeyguardManager_ULSEP_BINDING_ID = 2127;
        public static final int _UL__ULSEP_android_app_NotificationManager_ULSEP_BINDING_ID = 2065;
        public static final int _UL__ULSEP_android_app_SearchManager_ULSEP_BINDING_ID = 2084;
        public static final int _UL__ULSEP_android_app_Service_ULSEP_BINDING_ID = 2103;
        public static final int _UL__ULSEP_android_app_admin_DevicePolicyManager_ULSEP_BINDING_ID = 2110;
        public static final int _UL__ULSEP_android_content_ClipboardManager_ULSEP_BINDING_ID = 2106;
        public static final int _UL__ULSEP_android_content_ContentResolver_ULSEP_BINDING_ID = 2123;
        public static final int _UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID = 3;
        public static final int _UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID = 61;
        public static final int _UL__ULSEP_android_content_SharedPreferences_ULSEP_BINDING_ID = 2068;
        public static final int _UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_updater_prefs_DeviceProtectedPrefs_ULSEP_BINDING_ID = 98;
        public static final int _UL__ULSEP_android_content_pm_ApplicationInfo_ULSEP_BINDING_ID = 2063;
        public static final int _UL__ULSEP_android_content_pm_PackageInfo_ULSEP_BINDING_ID = 69;
        public static final int _UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID = 31;
        public static final int _UL__ULSEP_android_hardware_SensorManager_ULSEP_BINDING_ID = 2134;
        public static final int _UL__ULSEP_android_location_Geocoder_ULSEP_BINDING_ID = 2074;
        public static final int _UL__ULSEP_android_location_LocationManager_ULSEP_BINDING_ID = 2080;
        public static final int _UL__ULSEP_android_media_AudioManager_ULSEP_BINDING_ID = 2049;
        public static final int _UL__ULSEP_android_media_MediaPlayer_ULSEP_BINDING_ID = 2111;
        public static final int _UL__ULSEP_android_media_MediaRecorder_ULSEP_BINDING_ID = 2104;
        public static final int _UL__ULSEP_android_net_ConnectivityManager_ULSEP_BINDING_ID = 74;
        public static final int _UL__ULSEP_android_net_NetworkInfo_ULSEP_BINDING_ID = 2056;
        public static final int _UL__ULSEP_android_net_wifi_WifiManager_ULSEP_BINDING_ID = 29;
        public static final int _UL__ULSEP_android_os_Handler_ULSEP_BINDING_ID = 2066;
        public static final int _UL__ULSEP_android_os_Handler_ULSEP_com_oculus_executors_ForUiThread_ULSEP_BINDING_ID = 2114;
        public static final int _UL__ULSEP_android_os_Looper_ULSEP_com_oculus_executors_ForUiThread_ULSEP_BINDING_ID = 56;
        public static final int _UL__ULSEP_android_os_PowerManager_ULSEP_BINDING_ID = 14;
        public static final int _UL__ULSEP_android_os_Vibrator_ULSEP_BINDING_ID = 2062;
        public static final int _UL__ULSEP_android_renderscript_RenderScript_ULSEP_BINDING_ID = 2064;
        public static final int _UL__ULSEP_android_telephony_TelephonyManager_ULSEP_BINDING_ID = 2098;
        public static final int _UL__ULSEP_android_view_LayoutInflater_ULSEP_BINDING_ID = 2115;
        public static final int _UL__ULSEP_android_view_WindowManager_ULSEP_BINDING_ID = 2067;
        public static final int _UL__ULSEP_android_view_accessibility_AccessibilityManager_ULSEP_BINDING_ID = 2076;
        public static final int _UL__ULSEP_android_view_inputmethod_InputMethodManager_ULSEP_BINDING_ID = 2081;
        public static final int _UL__ULSEP_androidx_fragment_app_FragmentActivity_ULSEP_BINDING_ID = 2077;
        public static final int _UL__ULSEP_androidx_localbroadcastmanager_content_LocalBroadcastManager_ULSEP_BINDING_ID = 2071;
        public static final int _UL__ULSEP_com_facebook_auth_viewercontext_ViewerContextManager_ULSEP_com_facebook_auth_viewercontext_ViewerContextManagerForApp_ULSEP_BINDING_ID = 6;
        public static final int _UL__ULSEP_com_facebook_auth_viewercontext_ViewerContextManager_ULSEP_com_facebook_auth_viewercontext_ViewerContextManagerForContext_ULSEP_BINDING_ID = 48;
        public static final int _UL__ULSEP_com_facebook_common_android_FbLocalBroadcastManager_ULSEP_BINDING_ID = 2059;
        public static final int _UL__ULSEP_com_facebook_common_manifest_AppBuildInfoReader_ULSEP_BINDING_ID = 42;
        public static final int _UL__ULSEP_com_facebook_common_manifest_AppBuildInfo_ULSEP_BINDING_ID = 66;
        public static final int _UL__ULSEP_com_facebook_common_manifest_ManifestReader_ULSEP_BINDING_ID = 105;
        public static final int _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID = 91;
        public static final int _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID = 12;
        public static final int _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID = 39;
        public static final int _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreManager_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID = 2119;
        public static final int _UL__ULSEP_com_facebook_gk_store_GatekeeperStore_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID = 62;
        public static final int _UL__ULSEP_com_facebook_gk_store_GatekeeperWriter_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID = 37;
        public static final int _UL__ULSEP_com_facebook_gk_store_GkAccessorByName_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID = 2092;
        public static final int _UL__ULSEP_com_facebook_http_config_SocketConfig_ULSEP_BINDING_ID = 68;
        public static final int _UL__ULSEP_com_facebook_inject_ApplicationScope_ULSEP_BINDING_ID = 2060;
        public static final int _UL__ULSEP_com_facebook_inject_ContextScope_ULSEP_BINDING_ID = 2050;
        public static final int _UL__ULSEP_com_facebook_inject_FbInjector_ULSEP_BINDING_ID = 2129;
        public static final int _UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_BINDING_ID = 36;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID = 97;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_BINDING_ID = 89;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigDebugUtil_ULSEP_BINDING_ID = 2121;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_BINDING_ID = 49;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_BINDING_ID = 86;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigValueUtil_ULSEP_BINDING_ID = 2117;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_interfaces_MobileConfigInitInterface_ULSEP_BINDING_ID = 2073;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_interfaces_MobileConfigServiceHelperInterface_ULSEP_BINDING_ID = 2057;
        public static final int _UL__ULSEP_com_facebook_support_v4_net_ConnectivityManagerCompat_ULSEP_BINDING_ID = 2061;
        public static final int _UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_BINDING_ID = 96;
        public static final int _UL__ULSEP_com_facebook_tigon_oktigon_OkTigonService_ULSEP_BINDING_ID = 53;
        public static final int _UL__ULSEP_com_facebook_xanalytics_XAnalyticsProvider_ULSEP_BINDING_ID = 32;
        public static final int _UL__ULSEP_com_oculus_auth_credentials_CredentialsManager_ULSEP_BINDING_ID = 45;
        public static final int _UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID = 92;
        public static final int _UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenStore_ULSEP_BINDING_ID = 93;
        public static final int _UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenSubscriber_ULSEP_BINDING_ID = 23;
        public static final int _UL__ULSEP_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULSEP_BINDING_ID = 46;
        public static final int _UL__ULSEP_com_oculus_auth_storage_AuthDatastore_ULSEP_BINDING_ID = 16;
        public static final int _UL__ULSEP_com_oculus_authapi_AuthServiceClient_ULSEP_BINDING_ID = 2055;
        public static final int _UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_BINDING_ID = 54;
        public static final int _UL__ULSEP_com_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider_ULSEP_BINDING_ID = 2091;
        public static final int _UL__ULSEP_com_oculus_authapi_inject_CallerInfoProviderImpl_ULSEP_BINDING_ID = 77;
        public static final int _UL__ULSEP_com_oculus_base_app_AppInfo_ULSEP_BINDING_ID = 94;
        public static final int _UL__ULSEP_com_oculus_common_init_AppInitLock_ULSEP_BINDING_ID = 111;
        public static final int _UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_BINDING_ID = 102;
        public static final int _UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID = 113;
        public static final int _UL__ULSEP_com_oculus_executors_OculusThreadExecutor_ULSEP_BINDING_ID = 28;
        public static final int _UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_BINDING_ID = 100;
        public static final int _UL__ULSEP_com_oculus_http_core_HttpCoreLogger_ULSEP_BINDING_ID = 10;
        public static final int _UL__ULSEP_com_oculus_http_core_LoggingErrorHandler_ULSEP_BINDING_ID = 30;
        public static final int _UL__ULSEP_com_oculus_http_core_interceptor_OculusAuthorizationInterceptor_ULSEP_BINDING_ID = 26;
        public static final int _UL__ULSEP_com_oculus_http_core_interceptor_UserAgentInterceptor_ULSEP_BINDING_ID = 2099;
        public static final int _UL__ULSEP_com_oculus_http_useragent_UserAgentFactory_ULSEP_BINDING_ID = 5;
        public static final int _UL__ULSEP_com_oculus_logging_utils_EventManagerImpl_ULSEP_BINDING_ID = 34;
        public static final int _UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID = 87;
        public static final int _UL__ULSEP_com_oculus_logging_utils_StorageLoggingUtils_ULSEP_BINDING_ID = 50;
        public static final int _UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_BINDING_ID = 75;
        public static final int _UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigConfiguration_ULSEP_BINDING_ID = 1;
        public static final int _UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID = 109;
        public static final int _UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInit_ULSEP_BINDING_ID = 27;
        public static final int _UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigLoginHandler_ULSEP_BINDING_ID = 80;
        public static final int _UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigLogoutHandler_ULSEP_BINDING_ID = 59;
        public static final int _UL__ULSEP_com_oculus_multiuser_UserClassifier_ULSEP_BINDING_ID = 15;
        public static final int _UL__ULSEP_com_oculus_os_Controllers_ULSEP_BINDING_ID = 2072;
        public static final int _UL__ULSEP_com_oculus_os_DeviceAuth_ULSEP_BINDING_ID = 65;
        public static final int _UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID = 90;
        public static final int _UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID = 47;
        public static final int _UL__ULSEP_com_oculus_updater_core_broadcast_BroadcastState_ULSEP_BINDING_ID = 106;
        public static final int _UL__ULSEP_com_oculus_updater_core_logging_OSUpdateEventLogger_ULSEP_BINDING_ID = 57;
        public static final int _UL__ULSEP_com_oculus_updater_core_monitors_BatteryMonitor_ULSEP_BINDING_ID = 116;
        public static final int _UL__ULSEP_com_oculus_updater_core_monitors_CheckPeriodMonitor_ULSEP_BINDING_ID = 88;
        public static final int _UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_BINDING_ID = 112;
        public static final int _UL__ULSEP_com_oculus_updater_core_monitors_IdleMonitor_ULSEP_BINDING_ID = 35;
        public static final int _UL__ULSEP_com_oculus_updater_core_monitors_SystemReceiver_ULSEP_BINDING_ID = 7;
        public static final int _UL__ULSEP_com_oculus_updater_core_monitors_SystemUpdatePolicyMonitor_ULSEP_BINDING_ID = 19;
        public static final int _UL__ULSEP_com_oculus_updater_core_monitors_WifiMonitor_ULSEP_BINDING_ID = 78;
        public static final int _UL__ULSEP_com_oculus_updater_core_os_DeviceTokenUpdateSubscriber_ULSEP_BINDING_ID = 99;
        public static final int _UL__ULSEP_com_oculus_updater_core_os_OSUpdateManager_ULSEP_BINDING_ID = 67;
        public static final int _UL__ULSEP_com_oculus_updater_core_os_SystemProperties_ULSEP_BINDING_ID = 24;
        public static final int _UL__ULSEP_com_oculus_updater_core_os_UpdateEngine_ULSEP_BINDING_ID = 43;
        public static final int _UL__ULSEP_com_oculus_updater_core_os_UpdateMonitor_ULSEP_BINDING_ID = 71;
        public static final int _UL__ULSEP_com_oculus_updater_core_os_contract_OSUpdateServiceContract_ULSEP_BINDING_ID = 58;
        public static final int _UL__ULSEP_com_oculus_updater_credentialsmanager_OSUpdateAuthDatastore_ULSEP_BINDING_ID = 114;
        public static final int _UL__ULSEP_com_oculus_updater_credentialsmanager_OSUpdateCredentialsManager_ULSEP_BINDING_ID = 73;
        public static final int _UL__ULSEP_com_oculus_updater_device_DeviceInfo_ULSEP_BINDING_ID = 38;
        public static final int _UL__ULSEP_com_oculus_updater_gk_GatekeeperHelper_ULSEP_BINDING_ID = 118;
        public static final int _UL__ULSEP_com_oculus_updater_init_AppInitializer_ULSEP_BINDING_ID = 110;
        public static final int _UL__ULSEP_com_oculus_updater_net_ApiDispatcher_ULSEP_BINDING_ID = 70;
        public static final int _UL__ULSEP_com_oculus_updater_net_OSUpdateProvider_ULSEP_BINDING_ID = 79;
        public static final int _UL__ULSEP_com_oculus_updater_ovrsvcclient_OVRServiceClient_ULSEP_BINDING_ID = 117;
        public static final int _UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID = 72;
        public static final int _UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_BINDING_ID = 13;
        public static final int _UL__ULSEP_com_oculus_xanalytics_OculusXAnalyticsProvider_ULSEP_BINDING_ID = 115;
        public static final int _UL__ULSEP_java_lang_Boolean_ULSEP_com_oculus_http_core_annotations_ReportGraphBackendException_ULSEP_BINDING_ID = 22;
        public static final int _UL__ULSEP_java_lang_Integer_ULSEP_com_facebook_common_android_AndroidSdkVersion_ULSEP_BINDING_ID = 2088;
        public static final int _UL__ULSEP_java_lang_Object_ULSEP_com_facebook_common_android_WifiP2pManagerSystemService_ULSEP_BINDING_ID = 2052;
        public static final int _UL__ULSEP_java_lang_Runtime_ULSEP_BINDING_ID = 2058;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_facebook_common_android_PackageName_ULSEP_BINDING_ID = 2118;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_facebook_http_annotations_UserAgentString_ULSEP_BINDING_ID = 0;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_BINDING_ID = 108;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_BINDING_ID = 21;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_BINDING_ID = 82;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_BINDING_ID = 33;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_BINDING_ID = 17;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentAppVersionMap_ULSEP_BINDING_ID = 18;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentString_ULSEP_BINDING_ID = 52;
        public static final int _UL__ULSEP_java_util_Locale_ULSEP_BINDING_ID = 41;
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_credentials_CredentialsChangedHandler_ULGT__ULSEP_BINDING_ID = 2089;
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LoginHandler_ULGT__ULSEP_BINDING_ID = 51;
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LogoutHandler_ULGT__ULSEP_BINDING_ID = 107;
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_BINDING_ID = 20;
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsHighPriorityInit_ULSEP_BINDING_ID = 8;
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsInitInEnterpriseMode_ULSEP_BINDING_ID = 2070;
        public static final int _UL__ULSEP_java_util_TimeZone_ULSEP_com_oculus_time_ForOculusTimeZone_ULSEP_BINDING_ID = 2085;
        public static final int _UL__ULSEP_okhttp3_CertificatePinner_ULSEP_BINDING_ID = 4;
        public static final int _UL__ULSEP_okhttp3_ConnectionSpec_ULSEP_BINDING_ID = 40;
        public static final int _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_BINDING_ID = 44;
        public static final int _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_facebook_tigon_oktigon_OkTigonClient_ULSEP_BINDING_ID = 104;
        public static final int _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_customuseragentclient_CustomUserAgentHttpClient_ULSEP_BINDING_ID = 84;
        public static final int _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_BINDING_ID = 95;
        public static final int _UL__ULSEP_retrofit_ErrorHandler_ULSEP_BINDING_ID = 55;
        public static final int _UL__ULSEP_retrofit_RestAdapter_LogLevel_ULSEP_BINDING_ID = 11;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_CustomUserAgentOculusRestAdapter_ULSEP_BINDING_ID = 2130;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_BINDING_ID = 2051;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_BINDING_ID = 2086;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoRestAdapter_ULSEP_BINDING_ID = 2053;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID = 101;

        public static final int dynamicId(Key key) {
            return 0;
        }

        public static final int id(int i) {
            return i;
        }
    }

    public static final class multibindmap {
        public static final int[] _UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_credentials_CredentialsChangedHandler_ULGT__ULSEP_BINDING_ID = new int[0];
        public static final int[] _UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LoginHandler_ULGT__ULSEP_BINDING_ID = {id.id(80), id.id(99)};
        public static final int[] _UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LogoutHandler_ULGT__ULSEP_BINDING_ID = {id.id(59)};
        public static final int[] _UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_BINDING_ID = {id.id(2), id.id(7)};
        public static final int[] _UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsHighPriorityInit_ULSEP_BINDING_ID = {id.id(27)};
        public static final int[] _UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsInitInEnterpriseMode_ULSEP_BINDING_ID = new int[0];
        public static final int[] _UL__ULSEP_java_util_Set_ULLT_com_oculus_config_interfaces_Configuration_ULGT__ULSEP_BINDING_ID = {id.id(1)};
    }

    private UL() {
    }

    @AutoGeneratedSwitchSubClass
    public static final class factorymap0 {
        private factorymap0() {
        }

        @AutoGeneratedSwitchSubMethod
        private static Object get0(int i, InjectorLike injectorLike) {
            switch ((i >> 0) & 127) {
                case 0:
                    return OculusOkTigonModule._UL__ULSEP_java_lang_String_ULSEP_com_facebook_http_annotations_UserAgentString_ULSEP_FACTORY_METHOD(injectorLike);
                case 1:
                    return MobileConfigConfiguration._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigConfiguration_ULSEP_FACTORY_METHOD(injectorLike);
                case 2:
                    return MobileConfigUpdaterInit._UL__ULSEP_com_oculus_mobileconfig_updater_MobileConfigUpdaterInit_ULSEP_FACTORY_METHOD(injectorLike);
                case 3:
                    return BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_FACTORY_METHOD(injectorLike);
                case 4:
                    return HttpModule._UL__ULSEP_okhttp3_CertificatePinner_ULSEP_FACTORY_METHOD(injectorLike);
                case 5:
                    return UserAgentFactory._UL__ULSEP_com_oculus_http_useragent_UserAgentFactory_ULSEP_FACTORY_METHOD(injectorLike);
                case 6:
                    return BundledAndroidModule._UL__ULSEP_com_facebook_auth_viewercontext_ViewerContextManager_ULSEP_com_facebook_auth_viewercontext_ViewerContextManagerForApp_ULSEP_FACTORY_METHOD(injectorLike);
                case 7:
                    return SystemReceiver._UL__ULSEP_com_oculus_updater_core_monitors_SystemReceiver_ULSEP_FACTORY_METHOD(injectorLike);
                case 8:
                    return new UltralightMultiBind(injectorLike, multibindmap._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsHighPriorityInit_ULSEP_BINDING_ID);
                case 9:
                    return TaskDelayFactory._UL__ULSEP_com_oculus_util_task_TaskDelayFactory_ULSEP_FACTORY_METHOD(injectorLike);
                case 10:
                    return HttpCoreLogger._UL__ULSEP_com_oculus_http_core_HttpCoreLogger_ULSEP_FACTORY_METHOD(injectorLike);
                case 11:
                    return NetworkModule._UL__ULSEP_retrofit_RestAdapter_LogLevel_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID /*{ENCODED_INT: 12}*/:
                    return GkSessionlessModule._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_BINDING_ID /*{ENCODED_INT: 13}*/:
                    return DeviceUtils._UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_android_os_PowerManager_ULSEP_BINDING_ID /*{ENCODED_INT: 14}*/:
                    return AndroidModule._UL__ULSEP_android_os_PowerManager_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_multiuser_UserClassifier_ULSEP_BINDING_ID /*{ENCODED_INT: 15}*/:
                    return UserClassifier._UL__ULSEP_com_oculus_multiuser_UserClassifier_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_auth_storage_AuthDatastore_ULSEP_BINDING_ID /*{ENCODED_INT: 16}*/:
                    return CredentialsManagerModule._UL__ULSEP_com_oculus_auth_storage_AuthDatastore_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_BINDING_ID /*{ENCODED_INT: 17}*/:
                    return OculusUpdaterAppModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentAppVersionMap_ULSEP_BINDING_ID /*{ENCODED_INT: 18}*/:
                    return UserAgentModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentAppVersionMap_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_core_monitors_SystemUpdatePolicyMonitor_ULSEP_BINDING_ID /*{ENCODED_INT: 19}*/:
                    return SystemUpdatePolicyMonitor._UL__ULSEP_com_oculus_updater_core_monitors_SystemUpdatePolicyMonitor_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_BINDING_ID /*{ENCODED_INT: 20}*/:
                    return new UltralightMultiBind(injectorLike, multibindmap._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_BINDING_ID);
                case id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_BINDING_ID /*{ENCODED_INT: 21}*/:
                    return EndpointModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_java_lang_Boolean_ULSEP_com_oculus_http_core_annotations_ReportGraphBackendException_ULSEP_BINDING_ID /*{ENCODED_INT: 22}*/:
                    return ApiModule._UL__ULSEP_java_lang_Boolean_ULSEP_com_oculus_http_core_annotations_ReportGraphBackendException_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenSubscriber_ULSEP_BINDING_ID /*{ENCODED_INT: 23}*/:
                    return CredentialsManagerModule._UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenSubscriber_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_core_os_SystemProperties_ULSEP_BINDING_ID /*{ENCODED_INT: 24}*/:
                    return SystemProperties._UL__ULSEP_com_oculus_updater_core_os_SystemProperties_ULSEP_FACTORY_METHOD(injectorLike);
                case 25:
                    return MobileConfigOTAUtilModule._UL__ULSEP_com_facebook_mobileconfig_ota_MobileConfigOTAUtil_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_http_core_interceptor_OculusAuthorizationInterceptor_ULSEP_BINDING_ID /*{ENCODED_INT: 26}*/:
                    return OculusAuthorizationInterceptor._UL__ULSEP_com_oculus_http_core_interceptor_OculusAuthorizationInterceptor_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInit_ULSEP_BINDING_ID /*{ENCODED_INT: 27}*/:
                    return MobileConfigInit._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInit_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_executors_OculusThreadExecutor_ULSEP_BINDING_ID /*{ENCODED_INT: 28}*/:
                    return ExecutorsModule._UL__ULSEP_com_oculus_executors_OculusThreadExecutor_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_android_net_wifi_WifiManager_ULSEP_BINDING_ID /*{ENCODED_INT: 29}*/:
                    return AndroidModule._UL__ULSEP_android_net_wifi_WifiManager_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_http_core_LoggingErrorHandler_ULSEP_BINDING_ID /*{ENCODED_INT: 30}*/:
                    return LoggingErrorHandler._UL__ULSEP_com_oculus_http_core_LoggingErrorHandler_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID /*{ENCODED_INT: 31}*/:
                    return AndroidModule._UL__ULSEP_android_content_pm_PackageManager_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_facebook_xanalytics_XAnalyticsProvider_ULSEP_BINDING_ID /*{ENCODED_INT: 32}*/:
                    return OculusXAnalyticsModule._UL__ULSEP_com_facebook_xanalytics_XAnalyticsProvider_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_BINDING_ID /*{ENCODED_INT: 33}*/:
                    return NetworkModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_logging_utils_EventManagerImpl_ULSEP_BINDING_ID /*{ENCODED_INT: 34}*/:
                    return EventManagerImpl._UL__ULSEP_com_oculus_logging_utils_EventManagerImpl_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_core_monitors_IdleMonitor_ULSEP_BINDING_ID /*{ENCODED_INT: 35}*/:
                    return IdleMonitor._UL__ULSEP_com_oculus_updater_core_monitors_IdleMonitor_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_BINDING_ID /*{ENCODED_INT: 36}*/:
                    return BundledAndroidModule._UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_facebook_gk_store_GatekeeperWriter_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID /*{ENCODED_INT: 37}*/:
                    return GkSessionlessModule._UL__ULSEP_com_facebook_gk_store_GatekeeperWriter_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_device_DeviceInfo_ULSEP_BINDING_ID /*{ENCODED_INT: 38}*/:
                    return DeviceInfo._UL__ULSEP_com_oculus_updater_device_DeviceInfo_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID /*{ENCODED_INT: 39}*/:
                    return GkSessionlessModule._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_okhttp3_ConnectionSpec_ULSEP_BINDING_ID /*{ENCODED_INT: 40}*/:
                    return HttpModule._UL__ULSEP_okhttp3_ConnectionSpec_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_java_util_Locale_ULSEP_BINDING_ID /*{ENCODED_INT: 41}*/:
                    return LocaleModule._UL__ULSEP_java_util_Locale_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_facebook_common_manifest_AppBuildInfoReader_ULSEP_BINDING_ID /*{ENCODED_INT: 42}*/:
                    return ManifestModule._UL__ULSEP_com_facebook_common_manifest_AppBuildInfoReader_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_core_os_UpdateEngine_ULSEP_BINDING_ID /*{ENCODED_INT: 43}*/:
                    return UpdateEngine._UL__ULSEP_com_oculus_updater_core_os_UpdateEngine_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_BINDING_ID /*{ENCODED_INT: 44}*/:
                    return HttpModule._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_auth_credentials_CredentialsManager_ULSEP_BINDING_ID /*{ENCODED_INT: 45}*/:
                    return CredentialsManagerModule._UL__ULSEP_com_oculus_auth_credentials_CredentialsManager_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULSEP_BINDING_ID /*{ENCODED_INT: 46}*/:
                    return NoOpDeviceAuthTokenSubscriber._UL__ULSEP_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID /*{ENCODED_INT: 47}*/:
                    return Clock._UL__ULSEP_com_oculus_time_Clock_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_facebook_auth_viewercontext_ViewerContextManager_ULSEP_com_facebook_auth_viewercontext_ViewerContextManagerForContext_ULSEP_BINDING_ID /*{ENCODED_INT: 48}*/:
                    return BundledAndroidModule._UL__ULSEP_com_facebook_auth_viewercontext_ViewerContextManager_ULSEP_com_facebook_auth_viewercontext_ViewerContextManagerForContext_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_BINDING_ID /*{ENCODED_INT: 49}*/:
                    return MobileConfigFactoryImplModule._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_logging_utils_StorageLoggingUtils_ULSEP_BINDING_ID /*{ENCODED_INT: 50}*/:
                    return StorageLoggingUtils._UL__ULSEP_com_oculus_logging_utils_StorageLoggingUtils_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LoginHandler_ULGT__ULSEP_BINDING_ID /*{ENCODED_INT: 51}*/:
                    return new UltralightMultiBind(injectorLike, multibindmap._UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LoginHandler_ULGT__ULSEP_BINDING_ID);
                case id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentString_ULSEP_BINDING_ID /*{ENCODED_INT: 52}*/:
                    return UserAgentModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentString_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_facebook_tigon_oktigon_OkTigonService_ULSEP_BINDING_ID /*{ENCODED_INT: 53}*/:
                    return OkTigonService._UL__ULSEP_com_facebook_tigon_oktigon_OkTigonService_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_BINDING_ID /*{ENCODED_INT: 54}*/:
                    return OVRAuthModule._UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_retrofit_ErrorHandler_ULSEP_BINDING_ID /*{ENCODED_INT: 55}*/:
                    return ApiModule._UL__ULSEP_retrofit_ErrorHandler_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_android_os_Looper_ULSEP_com_oculus_executors_ForUiThread_ULSEP_BINDING_ID /*{ENCODED_INT: 56}*/:
                    return ExecutorsModule._UL__ULSEP_android_os_Looper_ULSEP_com_oculus_executors_ForUiThread_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_core_logging_OSUpdateEventLogger_ULSEP_BINDING_ID /*{ENCODED_INT: 57}*/:
                    return OSUpdateEventLogger._UL__ULSEP_com_oculus_updater_core_logging_OSUpdateEventLogger_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_core_os_contract_OSUpdateServiceContract_ULSEP_BINDING_ID /*{ENCODED_INT: 58}*/:
                    return OSUpdateServiceContract._UL__ULSEP_com_oculus_updater_core_os_contract_OSUpdateServiceContract_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigLogoutHandler_ULSEP_BINDING_ID /*{ENCODED_INT: 59}*/:
                    return MobileConfigLogoutHandler._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigLogoutHandler_ULSEP_FACTORY_METHOD(injectorLike);
                case 60:
                    return TimeModule._UL__ULSEP_com_facebook_common_time_AwakeTimeSinceBootClock_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID /*{ENCODED_INT: 61}*/:
                    return BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_facebook_gk_store_GatekeeperStore_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID /*{ENCODED_INT: 62}*/:
                    return GkSessionlessModule._UL__ULSEP_com_facebook_gk_store_GatekeeperStore_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_FACTORY_METHOD(injectorLike);
                case 63:
                    return InternalModule._UL__ULSEP_com_oculus_android_os_internal_UserHandleInternal_ULSEP_FACTORY_METHOD(injectorLike);
                case 64:
                    return com.oculus.android.AndroidModule._UL__ULSEP_android_bluetooth_BluetoothAdapter_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_os_DeviceAuth_ULSEP_BINDING_ID /*{ENCODED_INT: 65}*/:
                    return OsSdkModule._UL__ULSEP_com_oculus_os_DeviceAuth_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_facebook_common_manifest_AppBuildInfo_ULSEP_BINDING_ID /*{ENCODED_INT: 66}*/:
                    return ManifestModule._UL__ULSEP_com_facebook_common_manifest_AppBuildInfo_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_core_os_OSUpdateManager_ULSEP_BINDING_ID /*{ENCODED_INT: 67}*/:
                    return OSUpdateManager._UL__ULSEP_com_oculus_updater_core_os_OSUpdateManager_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_facebook_http_config_SocketConfig_ULSEP_BINDING_ID /*{ENCODED_INT: 68}*/:
                    return SocketConfigModule._UL__ULSEP_com_facebook_http_config_SocketConfig_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_android_content_pm_PackageInfo_ULSEP_BINDING_ID /*{ENCODED_INT: 69}*/:
                    return MobileConfigInitModule._UL__ULSEP_android_content_pm_PackageInfo_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_net_ApiDispatcher_ULSEP_BINDING_ID /*{ENCODED_INT: 70}*/:
                    return ApiDispatcher._UL__ULSEP_com_oculus_updater_net_ApiDispatcher_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_core_os_UpdateMonitor_ULSEP_BINDING_ID /*{ENCODED_INT: 71}*/:
                    return UpdateMonitor._UL__ULSEP_com_oculus_updater_core_os_UpdateMonitor_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_BINDING_ID /*{ENCODED_INT: 72}*/:
                    return OSUpdaterSharedPreferences._UL__ULSEP_com_oculus_updater_prefs_OSUpdaterSharedPreferences_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_credentialsmanager_OSUpdateCredentialsManager_ULSEP_BINDING_ID /*{ENCODED_INT: 73}*/:
                    return OSUpdateCredentialsManager._UL__ULSEP_com_oculus_updater_credentialsmanager_OSUpdateCredentialsManager_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_android_net_ConnectivityManager_ULSEP_BINDING_ID /*{ENCODED_INT: 74}*/:
                    return AndroidModule._UL__ULSEP_android_net_ConnectivityManager_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_BINDING_ID /*{ENCODED_INT: 75}*/:
                    return ManagedMode._UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_FACTORY_METHOD(injectorLike);
                case 76:
                    return FbAppTypeModule._UL__ULSEP_com_facebook_config_application_FbAppType_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_authapi_inject_CallerInfoProviderImpl_ULSEP_BINDING_ID /*{ENCODED_INT: 77}*/:
                    return CallerInfoProviderImpl._UL__ULSEP_com_oculus_authapi_inject_CallerInfoProviderImpl_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_core_monitors_WifiMonitor_ULSEP_BINDING_ID /*{ENCODED_INT: 78}*/:
                    return WifiMonitor._UL__ULSEP_com_oculus_updater_core_monitors_WifiMonitor_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_net_OSUpdateProvider_ULSEP_BINDING_ID /*{ENCODED_INT: 79}*/:
                    return OSUpdateProvider._UL__ULSEP_com_oculus_updater_net_OSUpdateProvider_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigLoginHandler_ULSEP_BINDING_ID /*{ENCODED_INT: 80}*/:
                    return MobileConfigLoginHandler._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigLoginHandler_ULSEP_FACTORY_METHOD(injectorLike);
                case 81:
                    return new UltralightMultiBind(injectorLike, multibindmap._UL__ULSEP_java_util_Set_ULLT_com_oculus_config_interfaces_Configuration_ULGT__ULSEP_BINDING_ID);
                case id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_BINDING_ID /*{ENCODED_INT: 82}*/:
                    return EndpointModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_FACTORY_METHOD(injectorLike);
                case 83:
                    return TimeModule._UL__ULSEP_com_facebook_common_time_SystemClock_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_customuseragentclient_CustomUserAgentHttpClient_ULSEP_BINDING_ID /*{ENCODED_INT: 84}*/:
                    return ApiModule._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_customuseragentclient_CustomUserAgentHttpClient_ULSEP_FACTORY_METHOD(injectorLike);
                case 85:
                    return TimeModule._UL__ULSEP_com_facebook_common_time_RealtimeSinceBootClock_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_BINDING_ID /*{ENCODED_INT: 86}*/:
                    return MobileConfigFactoryImplModule._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID /*{ENCODED_INT: 87}*/:
                    return UtilsModule._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_core_monitors_CheckPeriodMonitor_ULSEP_BINDING_ID /*{ENCODED_INT: 88}*/:
                    return CheckPeriodMonitor._UL__ULSEP_com_oculus_updater_core_monitors_CheckPeriodMonitor_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_BINDING_ID /*{ENCODED_INT: 89}*/:
                    return MobileConfigInitModule._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID /*{ENCODED_INT: 90}*/:
                    return OsSdkModule._UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID /*{ENCODED_INT: 91}*/:
                    return GkSessionlessModule._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID /*{ENCODED_INT: 92}*/:
                    return CredentialsModule._UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenStore_ULSEP_BINDING_ID /*{ENCODED_INT: 93}*/:
                    return DeviceAuthTokenStore._UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenStore_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_base_app_AppInfo_ULSEP_BINDING_ID /*{ENCODED_INT: 94}*/:
                    return NetworkModule._UL__ULSEP_com_oculus_base_app_AppInfo_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_BINDING_ID /*{ENCODED_INT: 95}*/:
                    return ApiModule._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_BINDING_ID /*{ENCODED_INT: 96}*/:
                    return OkTigonServiceHolder._UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID /*{ENCODED_INT: 97}*/:
                    return MobileConfigInitModule._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_updater_prefs_DeviceProtectedPrefs_ULSEP_BINDING_ID /*{ENCODED_INT: 98}*/:
                    return PrefsModule._UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_updater_prefs_DeviceProtectedPrefs_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_core_os_DeviceTokenUpdateSubscriber_ULSEP_BINDING_ID /*{ENCODED_INT: 99}*/:
                    return DeviceTokenUpdateSubscriber._UL__ULSEP_com_oculus_updater_core_os_DeviceTokenUpdateSubscriber_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_BINDING_ID /*{ENCODED_INT: 100}*/:
                    return ApiResponseConverter._UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID /*{ENCODED_INT: 101}*/:
                    return ApiModule._UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_BINDING_ID /*{ENCODED_INT: 102}*/:
                    return DsatFetcher._UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_FACTORY_METHOD(injectorLike);
                case 103:
                    return TimeModule._UL__ULSEP_com_facebook_common_time_CurrentThreadTimeClock_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_facebook_tigon_oktigon_OkTigonClient_ULSEP_BINDING_ID /*{ENCODED_INT: 104}*/:
                    return OculusOkTigonModule._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_facebook_tigon_oktigon_OkTigonClient_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_facebook_common_manifest_ManifestReader_ULSEP_BINDING_ID /*{ENCODED_INT: 105}*/:
                    return ManifestModule._UL__ULSEP_com_facebook_common_manifest_ManifestReader_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_core_broadcast_BroadcastState_ULSEP_BINDING_ID /*{ENCODED_INT: 106}*/:
                    return BroadcastState._UL__ULSEP_com_oculus_updater_core_broadcast_BroadcastState_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LogoutHandler_ULGT__ULSEP_BINDING_ID /*{ENCODED_INT: 107}*/:
                    return new UltralightMultiBind(injectorLike, multibindmap._UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LogoutHandler_ULGT__ULSEP_BINDING_ID);
                case id._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_BINDING_ID /*{ENCODED_INT: 108}*/:
                    return EndpointModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID /*{ENCODED_INT: 109}*/:
                    return OculusUpdaterAppModule._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_init_AppInitializer_ULSEP_BINDING_ID /*{ENCODED_INT: 110}*/:
                    return AppInitializer._UL__ULSEP_com_oculus_updater_init_AppInitializer_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_common_init_AppInitLock_ULSEP_BINDING_ID /*{ENCODED_INT: 111}*/:
                    return AppInitModule._UL__ULSEP_com_oculus_common_init_AppInitLock_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_BINDING_ID /*{ENCODED_INT: 112}*/:
                    return ConditionManager._UL__ULSEP_com_oculus_updater_core_monitors_ConditionManager_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID /*{ENCODED_INT: 113}*/:
                    return ErrorReportingModule._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_credentialsmanager_OSUpdateAuthDatastore_ULSEP_BINDING_ID /*{ENCODED_INT: 114}*/:
                    return OSUpdateAuthDatastore._UL__ULSEP_com_oculus_updater_credentialsmanager_OSUpdateAuthDatastore_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_xanalytics_OculusXAnalyticsProvider_ULSEP_BINDING_ID /*{ENCODED_INT: 115}*/:
                    return OculusXAnalyticsProvider._UL__ULSEP_com_oculus_xanalytics_OculusXAnalyticsProvider_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_core_monitors_BatteryMonitor_ULSEP_BINDING_ID /*{ENCODED_INT: 116}*/:
                    return BatteryMonitor._UL__ULSEP_com_oculus_updater_core_monitors_BatteryMonitor_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_ovrsvcclient_OVRServiceClient_ULSEP_BINDING_ID /*{ENCODED_INT: 117}*/:
                    return OVRServiceClient._UL__ULSEP_com_oculus_updater_ovrsvcclient_OVRServiceClient_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_updater_gk_GatekeeperHelper_ULSEP_BINDING_ID /*{ENCODED_INT: 118}*/:
                    return GatekeeperHelper._UL__ULSEP_com_oculus_updater_gk_GatekeeperHelper_ULSEP_FACTORY_METHOD(injectorLike);
                default:
                    throw new IllegalArgumentException("Invalid Static DI binding id");
            }
        }

        @AutoGeneratedSwitchSubClassMasterMethod
        public static Object get(int i, InjectorLike injectorLike) {
            if (((i >> 7) & 15) == 0) {
                return get0(i, injectorLike);
            }
            throw new IllegalArgumentException("Invalid Static DI binding id");
        }
    }

    @AutoGeneratedSwitch
    public static final class factorymap {
        private factorymap() {
        }

        @AutoGeneratedSwitchMasterMethod
        public static Object get(int i, InjectorLike injectorLike) {
            if (((i >> 11) & 31) == 0) {
                return factorymap0.get(i, injectorLike);
            }
            throw new IllegalArgumentException("Invalid Static DI binding id");
        }
    }

    public static final class InitModule extends AbstractPrivateModule implements SpecialModule {
        @Override // com.facebook.inject.SpecialModule
        public void configure(Binder binder) {
            this.mBinder = binder;
            bindScope(ContextScoped.class, new ContextScope((FbInjector) binder.getInjector()));
            bindScope(ApplicationScoped.class, new ApplicationScope((FbInjector) binder.getInjector()));
        }
    }
}
