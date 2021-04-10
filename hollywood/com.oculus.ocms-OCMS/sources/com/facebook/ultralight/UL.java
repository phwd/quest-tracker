package com.facebook.ultralight;

import com.facebook.common.android.AndroidModule;
import com.facebook.common.json.FbJsonModule;
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
import com.facebook.mobileconfig.metadata.MobileConfigParamsMapModule;
import com.facebook.mobileconfig.ota.MobileConfigOTAUtilModule;
import com.facebook.tigon.oktigon.OkTigonService;
import com.facebook.tigon.oktigon.OkTigonServiceHolder;
import com.google.inject.Key;
import com.oculus.android.os.internal.inject.InternalModule;
import com.oculus.app.standalone.StandaloneApplicationLike;
import com.oculus.app.standalone.StandaloneApplicationLikeModule;
import com.oculus.appmanager.assets.AssetStorage;
import com.oculus.appmanager.assets.AssetsDumper;
import com.oculus.appmanager.assets.AssetsMethods;
import com.oculus.appmanager.assets.InstallWatcher;
import com.oculus.appmanager.assets.database.AssetSQLHelper;
import com.oculus.appmanager.downloader.OculusDownloaderModule;
import com.oculus.appmanager.downloader.OculusFileDownloader;
import com.oculus.appmanager.downloader.progress.OculusDownloadProgressTracker;
import com.oculus.appmanager.downloader.progress.OuculusDownloadProgressTrackerModule;
import com.oculus.appmanager.info.ApkUpdateInfoDispatcher;
import com.oculus.appmanager.info.ApkUpdateInfoProvider;
import com.oculus.appmanager.info.ApkUpdateStorage;
import com.oculus.appmanager.info.InfoUtils;
import com.oculus.appmanager.info.database.ApkUpdateDatabaseSupplier;
import com.oculus.appmanager.info.database.ApkUpdateExtrasManager;
import com.oculus.appmanager.info.schema.ApkUpdateExtrasTable;
import com.oculus.appmanager.info.schema.ApkUpdateSchemaPart;
import com.oculus.appmanager.info.schema.ApkUpdateTable;
import com.oculus.appmanager.installer.analytics.InstallerAnalytics;
import com.oculus.appmanager.installer.broadcast.AssetBroadcastDispatch;
import com.oculus.appmanager.installer.common.CryptoMethods;
import com.oculus.appmanager.installer.common.EcdsaSignatureVerifier;
import com.oculus.appmanager.installer.common.InstallerFileUtils;
import com.oculus.appmanager.installer.contract.InstallerServiceContract;
import com.oculus.appmanager.installer.dumper.InstallerServiceDumper;
import com.oculus.appmanager.installer.events.EventsModule;
import com.oculus.appmanager.installer.notification.InstallerNotificationManager;
import com.oculus.appmanager.installer.notification.NotificationDownloadListener;
import com.oculus.appmanager.installer.notification.NotificationEligibility;
import com.oculus.appmanager.installer.notification.NotificationEventListener;
import com.oculus.appmanager.installer.notification.NotificationUpdateInfoListener;
import com.oculus.appmanager.installer.notification.contract.InstallerNotificationsContract;
import com.oculus.appmanager.installer.service.AnalyticsUpdateStateListener;
import com.oculus.appmanager.installer.service.DefaultFlow;
import com.oculus.appmanager.installer.service.DozeDelayHelper;
import com.oculus.appmanager.installer.service.InstallQueue;
import com.oculus.appmanager.installer.service.InstallerCancelHelper;
import com.oculus.appmanager.installer.service.InstallerCleanUpHelper;
import com.oculus.appmanager.installer.service.InstallerConsistencyHelper;
import com.oculus.appmanager.installer.service.InstallerDownloadListener;
import com.oculus.appmanager.installer.service.InstallerEventEmitter;
import com.oculus.appmanager.installer.service.InstallerFailureHelper;
import com.oculus.appmanager.installer.service.InstallerRetryHelper;
import com.oculus.appmanager.installer.service.InstallerServiceDownloadHelper;
import com.oculus.appmanager.installer.service.InstallerServiceInstallHelper;
import com.oculus.appmanager.installer.service.InstallerServiceInstallLock;
import com.oculus.appmanager.installer.service.InstallerServiceManager;
import com.oculus.appmanager.installer.service.InstallerServiceUninstallHelper;
import com.oculus.appmanager.installer.service.InstallerServiceVerificationHelper;
import com.oculus.appmanager.installer.service.PackageBroadcastListener;
import com.oculus.appmanager.installer.service.UpdateStateListener;
import com.oculus.appmanager.installer.service.util.InstallerServiceUtil;
import com.oculus.appmanager.patcher.Patcher;
import com.oculus.appmanager.patcher.RsyncNativeLibrary;
import com.oculus.appmanager.uninstaller.events.UninstallCompleteEventProvider;
import com.oculus.appmanager.uninstaller.tasks.UninstallerAsyncTaskProvider;
import com.oculus.appmanager.util.FileOps;
import com.oculus.appmanager.util.InstallationDiskSpaceUtil;
import com.oculus.appmanager.vrsign.VRSignVerifier;
import com.oculus.auth.credentials.CredentialsModule;
import com.oculus.auth.device.DeviceAuthTokenStore;
import com.oculus.auth.device.noop_subscriber.NoOpDeviceAuthTokenSubscriber;
import com.oculus.auth.device.noop_subscriber.NoOpDeviceAuthTokenSubscriberModule;
import com.oculus.auth.receiver.LoginHandlersRunner;
import com.oculus.auth.util.AccessTokenUtils;
import com.oculus.authapi.inject.CallerInfoProviderImpl;
import com.oculus.authapi.inject.OVRAuthModule;
import com.oculus.autoupdates.AutoUpdateScheduler;
import com.oculus.autoupdates.AutoUpdateTimeTracker;
import com.oculus.autoupdates.AutoUpdatesInit;
import com.oculus.autoupdates.AutoUpdatesManager;
import com.oculus.autoupdates.AutoUpdatesModule;
import com.oculus.autoupdates.PermissionManager;
import com.oculus.autoupdates.database.AutoUpdatesDBManager;
import com.oculus.autoupdates.dumper.AutoUpdatesDumperPlugin;
import com.oculus.common.init.AppInitModule;
import com.oculus.common.init.impl.AppInitializer;
import com.oculus.common.packagescache.PackageManagerUtils;
import com.oculus.config.ConfigModule;
import com.oculus.coreapps.CoreAppManager;
import com.oculus.debug.DebugMode;
import com.oculus.downloader.contract.DownloaderContract;
import com.oculus.downloader.dispatcher.OculusDownloadListenerDispatcher;
import com.oculus.downloader.extras.DownloadExtras;
import com.oculus.downloader.init.DownloaderInit;
import com.oculus.dsatauth.DsatFetcher;
import com.oculus.dumpsysledger.DumpsysInit;
import com.oculus.dumpsysledger.DumpsysLedgerMapper;
import com.oculus.errorreporting.ErrorReportingModule;
import com.oculus.executors.ExecutorsModule;
import com.oculus.horizon.api.ApiRequestManager;
import com.oculus.horizon.logging.OculusLogger;
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
import com.oculus.installer.AssetManager;
import com.oculus.installer.DownloadAndInstallApi;
import com.oculus.installer.FileIntegrityLogger;
import com.oculus.installer.InstallerAccessTokenFetcher;
import com.oculus.library.auth.LibraryAuthListener;
import com.oculus.library.database.LibraryDatabaseSupplier;
import com.oculus.library.database.LibrarySchemaPart;
import com.oculus.library.database.LibraryStorage;
import com.oculus.library.database.LibraryTable;
import com.oculus.library.job.LibraryJobInit;
import com.oculus.library.job.LibraryJobScheduler;
import com.oculus.library.net.LibraryMethods;
import com.oculus.library.refresher.LibraryCacheRefresher;
import com.oculus.library.security.TrustedApplications;
import com.oculus.library.sync.LibrarySyncHelper;
import com.oculus.library.sync.LibrarySyncMethods;
import com.oculus.library.utils.AppInternalConverter;
import com.oculus.library.utils.AppSharingUtils;
import com.oculus.library.utils.app.AppConverterUtilsModule;
import com.oculus.libraryapi.OVRLibraryModule;
import com.oculus.libraryapi.dumper.OVRLibraryDumperPlugin;
import com.oculus.license.LicenseHelper;
import com.oculus.license.LicenseMethods;
import com.oculus.locale.LocaleModule;
import com.oculus.logging.utils.EventManagerImpl;
import com.oculus.logging.utils.StorageLoggingUtils;
import com.oculus.logging.utils.UtilsModule;
import com.oculus.managed.ManagedMode;
import com.oculus.mobileconfig.dumper.MobileConfigDumperPlugin;
import com.oculus.mobileconfig.init.MobileConfigConfiguration;
import com.oculus.mobileconfig.init.MobileConfigInit;
import com.oculus.mobileconfig.init.MobileConfigInitModule;
import com.oculus.mobileconfig.init.MobileConfigLoginHandler;
import com.oculus.mobileconfig.init.MobileConfigLogoutHandler;
import com.oculus.mobileconfig.updater.MobileConfigUpdaterInit;
import com.oculus.multiuser.UserClassifier;
import com.oculus.ocms.app.OCMSAppModule;
import com.oculus.ocms.app.OCMSConfigValueProvider;
import com.oculus.ocms.app.OCMSConfigurationPrefs;
import com.oculus.ocms.app.OCMSCredentialsManager;
import com.oculus.ocms.defaultapps.DefaultAppsInstaller;
import com.oculus.ocms.defaultapps.DefaultAppsMethods;
import com.oculus.ocms.defaultapps.DefaultAppsPrefs;
import com.oculus.ocms.defaultapps.DefaultAppsSetupListener;
import com.oculus.ocms.defaultapps.dumper.DefaultAppsDumperPlugin;
import com.oculus.ocms.defaultapps.graphql.DefaultAppsGraphQLQuery;
import com.oculus.ocms.library.EntitlementsInstallerEventListener;
import com.oculus.ocms.library.OVRLibraryInstallerEventListener;
import com.oculus.ocms.library.provider.LibraryDownloadListener;
import com.oculus.ocms.library.provider.SharedAppsHelper;
import com.oculus.ocms.locationservices.LocationServiceModule;
import com.oculus.ocms.locationservices.dumper.LocationServiceDumper;
import com.oculus.ocms.locationservices.graphql.ApiDispatcher;
import com.oculus.oculustestsettings.OculusTestSettingsDefaultImpl;
import com.oculus.oculustestsettings.OculusTestSettingsModule;
import com.oculus.oktigon.OculusOkTigonModule;
import com.oculus.ossdk.inject.OsSdkModule;
import com.oculus.profileapi.OVRProfile;
import com.oculus.security.basecomponent.OculusContentProviderLogger;
import com.oculus.security.basecomponent.OculusIntentLogger;
import com.oculus.time.Clock;
import com.oculus.unlockulus_helper.inject.UnlockulusModule;
import com.oculus.userserver.api.inject.UserServerInjectorModule;
import com.oculus.util.device.DeviceUtils;
import com.oculus.util.inject.UtilModule;
import com.oculus.util.service.ServiceFutures;
import com.oculus.util.task.TaskDelayFactory;
import com.oculus.util.thread.ThreadUtils;
import com.oculus.xanalytics.OculusXAnalyticsModule;
import com.oculus.xanalytics.OculusXAnalyticsProvider;

public final class UL {
    public static final boolean USE_STATIC_DI = true;

    public static final class id {
        public static final int _UL__ULSEP_android_accounts_AccountManager_ULSEP_BINDING_ID = 2097;
        public static final int _UL__ULSEP_android_app_ActivityManager_ULSEP_BINDING_ID = 2070;
        public static final int _UL__ULSEP_android_app_Activity_ULSEP_BINDING_ID = 2135;
        public static final int _UL__ULSEP_android_app_AlarmManager_ULSEP_BINDING_ID = 2119;
        public static final int _UL__ULSEP_android_app_DownloadManager_ULSEP_BINDING_ID = 212;
        public static final int _UL__ULSEP_android_app_KeyguardManager_ULSEP_BINDING_ID = 2130;
        public static final int _UL__ULSEP_android_app_NotificationManager_ULSEP_BINDING_ID = 34;
        public static final int _UL__ULSEP_android_app_SearchManager_ULSEP_BINDING_ID = 2083;
        public static final int _UL__ULSEP_android_app_Service_ULSEP_BINDING_ID = 2109;
        public static final int _UL__ULSEP_android_app_admin_DevicePolicyManager_ULSEP_BINDING_ID = 2116;
        public static final int _UL__ULSEP_android_app_job_JobScheduler_ULSEP_BINDING_ID = 2098;
        public static final int _UL__ULSEP_android_bluetooth_BluetoothAdapter_ULSEP_BINDING_ID = 193;
        public static final int _UL__ULSEP_android_bluetooth_BluetoothManager_ULSEP_BINDING_ID = 2138;
        public static final int _UL__ULSEP_android_bluetooth_le_BluetoothLeScanner_ULSEP_BINDING_ID = 2096;
        public static final int _UL__ULSEP_android_content_ClipboardManager_ULSEP_BINDING_ID = 2113;
        public static final int _UL__ULSEP_android_content_ContentResolver_ULSEP_BINDING_ID = 2126;
        public static final int _UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID = 4;
        public static final int _UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID = 79;
        public static final int _UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_config_annotations_GatekeeperSharedPrefs_ULSEP_BINDING_ID = 80;
        public static final int _UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_config_annotations_MobileConfigSharedPrefs_ULSEP_BINDING_ID = 2106;
        public static final int _UL__ULSEP_android_content_pm_ApplicationInfo_ULSEP_BINDING_ID = 2066;
        public static final int _UL__ULSEP_android_content_pm_PackageInfo_ULSEP_BINDING_ID = 194;
        public static final int _UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID = 40;
        public static final int _UL__ULSEP_android_hardware_SensorManager_ULSEP_BINDING_ID = 2137;
        public static final int _UL__ULSEP_android_location_Geocoder_ULSEP_BINDING_ID = 2074;
        public static final int _UL__ULSEP_android_location_LocationManager_ULSEP_BINDING_ID = 2079;
        public static final int _UL__ULSEP_android_media_AudioManager_ULSEP_BINDING_ID = 2049;
        public static final int _UL__ULSEP_android_media_MediaPlayer_ULSEP_BINDING_ID = 2117;
        public static final int _UL__ULSEP_android_media_MediaRecorder_ULSEP_BINDING_ID = 2110;
        public static final int _UL__ULSEP_android_net_ConnectivityManager_ULSEP_BINDING_ID = 196;
        public static final int _UL__ULSEP_android_net_NetworkInfo_ULSEP_BINDING_ID = 2056;
        public static final int _UL__ULSEP_android_net_wifi_WifiManager_ULSEP_BINDING_ID = 2105;
        public static final int _UL__ULSEP_android_os_Handler_ULSEP_BINDING_ID = 2068;
        public static final int _UL__ULSEP_android_os_Handler_ULSEP_com_oculus_executors_ForUiThread_ULSEP_BINDING_ID = 195;
        public static final int _UL__ULSEP_android_os_Looper_ULSEP_com_oculus_executors_ForUiThread_ULSEP_BINDING_ID = 71;
        public static final int _UL__ULSEP_android_os_PowerManager_ULSEP_BINDING_ID = 151;
        public static final int _UL__ULSEP_android_os_Vibrator_ULSEP_BINDING_ID = 2065;
        public static final int _UL__ULSEP_android_renderscript_RenderScript_ULSEP_BINDING_ID = 2067;
        public static final int _UL__ULSEP_android_telephony_TelephonyManager_ULSEP_BINDING_ID = 2102;
        public static final int _UL__ULSEP_android_view_LayoutInflater_ULSEP_BINDING_ID = 2121;
        public static final int _UL__ULSEP_android_view_WindowManager_ULSEP_BINDING_ID = 2069;
        public static final int _UL__ULSEP_android_view_accessibility_AccessibilityManager_ULSEP_BINDING_ID = 2075;
        public static final int _UL__ULSEP_android_view_inputmethod_InputMethodManager_ULSEP_BINDING_ID = 2080;
        public static final int _UL__ULSEP_androidx_fragment_app_FragmentActivity_ULSEP_BINDING_ID = 2077;
        public static final int _UL__ULSEP_androidx_localbroadcastmanager_content_LocalBroadcastManager_ULSEP_BINDING_ID = 2071;
        public static final int _UL__ULSEP_com_facebook_auth_viewercontext_ViewerContextManager_ULSEP_com_facebook_auth_viewercontext_ViewerContextManagerForApp_ULSEP_BINDING_ID = 8;
        public static final int _UL__ULSEP_com_facebook_auth_viewercontext_ViewerContextManager_ULSEP_com_facebook_auth_viewercontext_ViewerContextManagerForContext_ULSEP_BINDING_ID = 65;
        public static final int _UL__ULSEP_com_facebook_common_android_FbLocalBroadcastManager_ULSEP_BINDING_ID = 2060;
        public static final int _UL__ULSEP_com_facebook_common_build_SignatureType_ULSEP_BINDING_ID = 2078;
        public static final int _UL__ULSEP_com_facebook_common_identifiers_UniqueIdGenerator_ULSEP_BINDING_ID = 2111;
        public static final int _UL__ULSEP_com_facebook_common_json_FbObjectMapper_ULSEP_BINDING_ID = 145;
        public static final int _UL__ULSEP_com_facebook_common_json_JsonLoggerStub_ULSEP_BINDING_ID = 20;
        public static final int _UL__ULSEP_com_facebook_common_json_JsonLogger_ULSEP_BINDING_ID = 2128;
        public static final int _UL__ULSEP_com_facebook_common_json_ObjectMapperWithUncheckedException_ULSEP_BINDING_ID = 2134;
        public static final int _UL__ULSEP_com_facebook_common_manifest_AppBuildInfoReader_ULSEP_BINDING_ID = 55;
        public static final int _UL__ULSEP_com_facebook_common_manifest_AppBuildInfo_ULSEP_BINDING_ID = 84;
        public static final int _UL__ULSEP_com_facebook_common_manifest_ManifestReader_ULSEP_BINDING_ID = 238;
        public static final int _UL__ULSEP_com_facebook_common_time_AwakeTimeSinceBootClock_ULSEP_BINDING_ID = 190;
        public static final int _UL__ULSEP_com_facebook_common_time_Clock_ULSEP_BINDING_ID = 248;
        public static final int _UL__ULSEP_com_facebook_common_time_Clock_ULSEP_com_facebook_common_time_CurrentThreadTime_ULSEP_BINDING_ID = 2104;
        public static final int _UL__ULSEP_com_facebook_common_time_CurrentThreadTimeClock_ULSEP_BINDING_ID = 226;
        public static final int _UL__ULSEP_com_facebook_common_time_MonotonicClock_ULSEP_BINDING_ID = 211;
        public static final int _UL__ULSEP_com_facebook_common_time_MonotonicClock_ULSEP_com_facebook_common_time_ElapsedAwakeTimeSinceBoot_ULSEP_BINDING_ID = 2122;
        public static final int _UL__ULSEP_com_facebook_common_time_MonotonicClock_ULSEP_com_facebook_common_time_ElapsedRealtimeSinceBoot_ULSEP_BINDING_ID = 2100;
        public static final int _UL__ULSEP_com_facebook_common_time_MonotonicNanoClock_ULSEP_BINDING_ID = 2082;
        public static final int _UL__ULSEP_com_facebook_common_time_RealtimeSinceBootClock_ULSEP_BINDING_ID = 98;
        public static final int _UL__ULSEP_com_facebook_common_time_SystemClock_ULSEP_BINDING_ID = 97;
        public static final int _UL__ULSEP_com_facebook_config_application_FbAppType_ULSEP_BINDING_ID = 90;
        public static final int _UL__ULSEP_com_facebook_config_application_IntendedAudience_ULSEP_BINDING_ID = 2081;
        public static final int _UL__ULSEP_com_facebook_config_application_PlatformAppConfig_ULSEP_BINDING_ID = 2108;
        public static final int _UL__ULSEP_com_facebook_config_application_Product_ULSEP_BINDING_ID = 2048;
        public static final int _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID = 216;
        public static final int _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID = 142;
        public static final int _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID = 52;
        public static final int _UL__ULSEP_com_facebook_gk_store_GatekeeperStoreManager_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID = 2125;
        public static final int _UL__ULSEP_com_facebook_gk_store_GatekeeperStore_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID = 191;
        public static final int _UL__ULSEP_com_facebook_gk_store_GatekeeperWriter_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID = 171;
        public static final int _UL__ULSEP_com_facebook_gk_store_GkAccessorByName_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_BINDING_ID = 2095;
        public static final int _UL__ULSEP_com_facebook_http_config_NetworkConfig_ULSEP_BINDING_ID = 2131;
        public static final int _UL__ULSEP_com_facebook_http_config_SocketConfig_ULSEP_BINDING_ID = 87;
        public static final int _UL__ULSEP_com_facebook_inject_ApplicationScope_ULSEP_BINDING_ID = 2062;
        public static final int _UL__ULSEP_com_facebook_inject_ContextScope_ULSEP_BINDING_ID = 2050;
        public static final int _UL__ULSEP_com_facebook_inject_FbInjector_ULSEP_BINDING_ID = 2132;
        public static final int _UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_BINDING_ID = 49;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID = 109;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_BINDING_ID = 101;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigDebugUtil_ULSEP_BINDING_ID = 213;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_BINDING_ID = 66;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_BINDING_ID = 100;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigValueUtil_ULSEP_BINDING_ID = 197;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_interfaces_MobileConfigInitInterface_ULSEP_BINDING_ID = 2073;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_interfaces_MobileConfigServiceHelperInterface_ULSEP_BINDING_ID = 2057;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_metadata_ParamsMapList_ULSEP_BINDING_ID = 230;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_metadata_ParamsMapList_ULSEP_com_facebook_mobileconfig_metadata_FromOTAParamsMap_ULSEP_BINDING_ID = 2107;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_metadata_ParamsMapList_ULSEP_com_facebook_mobileconfig_metadata_OTAAddedParamsMap_ULSEP_BINDING_ID = 2118;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_metadata_ParamsMapList_ULSEP_com_facebook_mobileconfig_metadata_WithoutVirtualGKs_ULSEP_BINDING_ID = 2136;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_ota_MobileConfigOTAUtil_ULSEP_BINDING_ID = 31;
        public static final int _UL__ULSEP_com_facebook_mobileconfig_override_OverrideUtil_ULSEP_BINDING_ID = 2127;
        public static final int _UL__ULSEP_com_facebook_support_v4_net_ConnectivityManagerCompat_ULSEP_BINDING_ID = 2063;
        public static final int _UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_BINDING_ID = 106;
        public static final int _UL__ULSEP_com_facebook_tigon_oktigon_OkTigonService_ULSEP_BINDING_ID = 178;
        public static final int _UL__ULSEP_com_facebook_wifiscan_WifiScan_ULSEP_BINDING_ID = 222;
        public static final int _UL__ULSEP_com_facebook_xanalytics_XAnalyticsProvider_ULSEP_BINDING_ID = 168;
        public static final int _UL__ULSEP_com_fasterxml_jackson_core_JsonFactory_ULSEP_BINDING_ID = 2120;
        public static final int _UL__ULSEP_com_fasterxml_jackson_core_JsonFactory_ULSEP_com_facebook_common_json_SmileJsonFactory_ULSEP_BINDING_ID = 2064;
        public static final int _UL__ULSEP_com_fasterxml_jackson_databind_ObjectMapper_ULSEP_BINDING_ID = 181;
        public static final int _UL__ULSEP_com_fasterxml_jackson_dataformat_smile_SmileFactory_ULSEP_BINDING_ID = 217;
        public static final int _UL__ULSEP_com_google_common_util_concurrent_ListeningExecutorService_ULSEP_com_oculus_autoupdates_AutoUpdateExecutor_ULSEP_BINDING_ID = 149;
        public static final int _UL__ULSEP_com_oculus_android_os_internal_UserHandleInternal_ULSEP_BINDING_ID = 192;
        public static final int _UL__ULSEP_com_oculus_app_standalone_StandaloneApplicationLike_ULSEP_BINDING_ID = 187;
        public static final int _UL__ULSEP_com_oculus_appmanager_assets_AssetStorage_ULSEP_BINDING_ID = 85;
        public static final int _UL__ULSEP_com_oculus_appmanager_assets_AssetsDumper_ULSEP_BINDING_ID = 250;
        public static final int _UL__ULSEP_com_oculus_appmanager_assets_AssetsMethods_ULSEP_BINDING_ID = 119;
        public static final int _UL__ULSEP_com_oculus_appmanager_assets_InstallWatcher_ULSEP_BINDING_ID = 120;
        public static final int _UL__ULSEP_com_oculus_appmanager_assets_database_AssetSQLHelper_ULSEP_BINDING_ID = 70;
        public static final int _UL__ULSEP_com_oculus_appmanager_downloader_OculusFileDownloader_ULSEP_BINDING_ID = 125;
        public static final int _UL__ULSEP_com_oculus_appmanager_downloader_progress_OculusDownloadProgressTracker_ULSEP_BINDING_ID = 54;
        public static final int _UL__ULSEP_com_oculus_appmanager_info_ApkUpdateInfoDispatcher_ULSEP_BINDING_ID = 103;
        public static final int _UL__ULSEP_com_oculus_appmanager_info_ApkUpdateInfoProvider_ULSEP_BINDING_ID = 234;
        public static final int _UL__ULSEP_com_oculus_appmanager_info_ApkUpdateStorage_ULSEP_BINDING_ID = 110;
        public static final int _UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID = 227;
        public static final int _UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateDatabaseSupplier_ULSEP_BINDING_ID = 38;
        public static final int _UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateExtrasManager_ULSEP_BINDING_ID = 72;
        public static final int _UL__ULSEP_com_oculus_appmanager_info_schema_ApkUpdateExtrasTable_ULSEP_BINDING_ID = 198;
        public static final int _UL__ULSEP_com_oculus_appmanager_info_schema_ApkUpdateSchemaPart_ULSEP_BINDING_ID = 215;
        public static final int _UL__ULSEP_com_oculus_appmanager_info_schema_ApkUpdateTable_ULSEP_BINDING_ID = 247;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_analytics_InstallerAnalytics_ULSEP_BINDING_ID = 130;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_broadcast_AssetBroadcastDispatch_ULSEP_BINDING_ID = 13;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_common_CryptoMethods_ULSEP_BINDING_ID = 93;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_common_EcdsaSignatureVerifier_ULSEP_BINDING_ID = 221;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_common_InstallerDownloadStatusChecker_ULSEP_BINDING_ID = 2123;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_BINDING_ID = 107;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_contract_InstallerServiceContract_ULSEP_BINDING_ID = 3;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_dumper_InstallerServiceDumper_ULSEP_BINDING_ID = 5;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_events_InstallerEventBus_ULSEP_BINDING_ID = 244;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_notification_InstallerNotificationManager_ULSEP_BINDING_ID = 94;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationDownloadListener_ULSEP_BINDING_ID = 144;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationEligibility_ULSEP_BINDING_ID = 210;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationEventListener_ULSEP_BINDING_ID = 141;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationUpdateInfoListener_ULSEP_BINDING_ID = 223;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_notification_contract_InstallerNotificationsContract_ULSEP_BINDING_ID = 115;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_service_AnalyticsUpdateStateListener_ULSEP_BINDING_ID = 41;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_service_DefaultFlow_ULSEP_BINDING_ID = 83;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_service_DozeDelayHelper_ULSEP_BINDING_ID = 47;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_service_InstallQueue_ULSEP_BINDING_ID = 233;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerCancelHelper_ULSEP_BINDING_ID = 164;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerCleanUpHelper_ULSEP_BINDING_ID = 200;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerConsistencyHelper_ULSEP_BINDING_ID = 86;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerDownloadListener_ULSEP_BINDING_ID = 140;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerEventEmitter_ULSEP_BINDING_ID = 189;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerFailureHelper_ULSEP_BINDING_ID = 167;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerRetryHelper_ULSEP_BINDING_ID = 56;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceDownloadHelper_ULSEP_BINDING_ID = 139;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceInstallHelper_ULSEP_BINDING_ID = 240;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceInstallLock_ULSEP_BINDING_ID = 15;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceManager_ULSEP_BINDING_ID = 99;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceUninstallHelper_ULSEP_BINDING_ID = 205;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceVerificationHelper_ULSEP_BINDING_ID = 78;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_service_PackageBroadcastListener_ULSEP_BINDING_ID = 147;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_service_SDKVersionCheck_ULSEP_BINDING_ID = 2112;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_service_UpdateStateListener_ULSEP_BINDING_ID = 69;
        public static final int _UL__ULSEP_com_oculus_appmanager_installer_service_util_InstallerServiceUtil_ULSEP_BINDING_ID = 58;
        public static final int _UL__ULSEP_com_oculus_appmanager_patcher_Patcher_ULSEP_BINDING_ID = 24;
        public static final int _UL__ULSEP_com_oculus_appmanager_patcher_RsyncNativeLibrary_ULSEP_BINDING_ID = 12;
        public static final int _UL__ULSEP_com_oculus_appmanager_uninstaller_events_UninstallCompleteEventProvider_ULSEP_BINDING_ID = 26;
        public static final int _UL__ULSEP_com_oculus_appmanager_uninstaller_tasks_UninstallerAsyncTaskProvider_ULSEP_BINDING_ID = 183;
        public static final int _UL__ULSEP_com_oculus_appmanager_util_FileOps_ULSEP_BINDING_ID = 127;
        public static final int _UL__ULSEP_com_oculus_appmanager_util_InstallationDiskSpaceUtil_ULSEP_BINDING_ID = 74;
        public static final int _UL__ULSEP_com_oculus_appmanager_vrsign_VRSignVerifier_ULSEP_BINDING_ID = 158;
        public static final int _UL__ULSEP_com_oculus_auth_credentials_CredentialsManager_ULSEP_BINDING_ID = 174;
        public static final int _UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_BINDING_ID = 104;
        public static final int _UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenStore_ULSEP_BINDING_ID = 219;
        public static final int _UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenSubscriber_ULSEP_BINDING_ID = 165;
        public static final int _UL__ULSEP_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULSEP_BINDING_ID = 64;
        public static final int _UL__ULSEP_com_oculus_auth_receiver_BaseLoginHandler_ULSEP_BINDING_ID = 28;
        public static final int _UL__ULSEP_com_oculus_auth_receiver_LoginHandlersRunner_ULSEP_BINDING_ID = 33;
        public static final int _UL__ULSEP_com_oculus_auth_receiver_LogoutHandlersRunner_ULSEP_BINDING_ID = 2061;
        public static final int _UL__ULSEP_com_oculus_auth_storage_AuthDatastore_ULSEP_BINDING_ID = 18;
        public static final int _UL__ULSEP_com_oculus_auth_util_AccessTokenUtils_ULSEP_BINDING_ID = 73;
        public static final int _UL__ULSEP_com_oculus_authapi_AuthServiceClient_ULSEP_BINDING_ID = 2055;
        public static final int _UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_BINDING_ID = 179;
        public static final int _UL__ULSEP_com_oculus_authapi_inject_AuthServiceClientAuxiliaryProvider_ULSEP_BINDING_ID = 2094;
        public static final int _UL__ULSEP_com_oculus_authapi_inject_CallerInfoProviderImpl_ULSEP_BINDING_ID = 199;
        public static final int _UL__ULSEP_com_oculus_autoupdates_AutoUpdateScheduler_ULSEP_BINDING_ID = 91;
        public static final int _UL__ULSEP_com_oculus_autoupdates_AutoUpdateTimeTracker_ULSEP_BINDING_ID = 37;
        public static final int _UL__ULSEP_com_oculus_autoupdates_AutoUpdatesInit_ULSEP_BINDING_ID = 96;
        public static final int _UL__ULSEP_com_oculus_autoupdates_AutoUpdatesManager_ULSEP_BINDING_ID = 249;
        public static final int _UL__ULSEP_com_oculus_autoupdates_PermissionManager_ULSEP_BINDING_ID = 242;
        public static final int _UL__ULSEP_com_oculus_autoupdates_database_AutoUpdatesDBHelper_ULSEP_BINDING_ID = 21;
        public static final int _UL__ULSEP_com_oculus_autoupdates_database_AutoUpdatesDBManager_ULSEP_BINDING_ID = 48;
        public static final int _UL__ULSEP_com_oculus_autoupdates_dumper_AutoUpdatesDumperPlugin_ULSEP_BINDING_ID = 143;
        public static final int _UL__ULSEP_com_oculus_base_app_AppInfo_ULSEP_BINDING_ID = 218;
        public static final int _UL__ULSEP_com_oculus_base_app_ApplicationLike_ULSEP_BINDING_ID = 2;
        public static final int _UL__ULSEP_com_oculus_common_init_AppInitLock_ULSEP_BINDING_ID = 122;
        public static final int _UL__ULSEP_com_oculus_common_init_impl_AppInitializer_ULSEP_BINDING_ID = 176;
        public static final int _UL__ULSEP_com_oculus_common_packagescache_PackageManagerUtils_ULSEP_BINDING_ID = 92;
        public static final int _UL__ULSEP_com_oculus_config_ConfigController_ULSEP_BINDING_ID = 2087;
        public static final int _UL__ULSEP_com_oculus_config_gatekeeper_DynamicGatekeeperRegistry_ULSEP_BINDING_ID = 186;
        public static final int _UL__ULSEP_com_oculus_config_gatekeeper_GatekeeperRegistry_ULSEP_BINDING_ID = 30;
        public static final int _UL__ULSEP_com_oculus_coreapps_CoreAppManager_ULSEP_BINDING_ID = 156;
        public static final int _UL__ULSEP_com_oculus_debug_DebugMode_ULSEP_BINDING_ID = 241;
        public static final int _UL__ULSEP_com_oculus_downloader_OculusDownloader_ULSEP_BINDING_ID = 112;
        public static final int _UL__ULSEP_com_oculus_downloader_contract_DownloaderContract_ULSEP_BINDING_ID = 232;
        public static final int _UL__ULSEP_com_oculus_downloader_dispatcher_OculusDownloadListenerDispatcher_ULSEP_BINDING_ID = 51;
        public static final int _UL__ULSEP_com_oculus_downloader_extras_DownloadExtras_ULSEP_BINDING_ID = 27;
        public static final int _UL__ULSEP_com_oculus_downloader_init_DownloaderInit_ULSEP_BINDING_ID = 228;
        public static final int _UL__ULSEP_com_oculus_downloader_progress_DownloadProgressTracker_ULSEP_BINDING_ID = 81;
        public static final int _UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_BINDING_ID = 113;
        public static final int _UL__ULSEP_com_oculus_dumpsysledger_DumpsysInit_ULSEP_BINDING_ID = 22;
        public static final int _UL__ULSEP_com_oculus_dumpsysledger_DumpsysLedgerMapper_ULSEP_BINDING_ID = 17;
        public static final int _UL__ULSEP_com_oculus_dumpsysledger_DumpsysLedgerProvider_ULSEP_BINDING_ID = 2099;
        public static final int _UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_BINDING_ID = 123;
        public static final int _UL__ULSEP_com_oculus_executors_OculusThreadExecutor_ULSEP_BINDING_ID = 36;
        public static final int _UL__ULSEP_com_oculus_horizon_api_ApiDispatcher_ULSEP_BINDING_ID = 62;
        public static final int _UL__ULSEP_com_oculus_horizon_api_ApiRequestManager_ULSEP_BINDING_ID = 114;
        public static final int _UL__ULSEP_com_oculus_horizon_foreground_ApplicationForegroundTracker_ULSEP_BINDING_ID = 2115;
        public static final int _UL__ULSEP_com_oculus_horizon_logging_HorizonForegroundListener_ULSEP_BINDING_ID = 2076;
        public static final int _UL__ULSEP_com_oculus_horizon_logging_OculusLogger_ULSEP_BINDING_ID = 235;
        public static final int _UL__ULSEP_com_oculus_horizon_logging_QPLogEventBuilder_ULSEP_BINDING_ID = 2088;
        public static final int _UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_BINDING_ID = 224;
        public static final int _UL__ULSEP_com_oculus_http_core_HttpCoreLogger_ULSEP_BINDING_ID = 11;
        public static final int _UL__ULSEP_com_oculus_http_core_LoggingErrorHandler_ULSEP_BINDING_ID = 39;
        public static final int _UL__ULSEP_com_oculus_http_core_interceptor_OculusAuthorizationInterceptor_ULSEP_BINDING_ID = 32;
        public static final int _UL__ULSEP_com_oculus_http_core_interceptor_UserAgentInterceptor_ULSEP_BINDING_ID = 2103;
        public static final int _UL__ULSEP_com_oculus_http_useragent_UserAgentFactory_ULSEP_BINDING_ID = 134;
        public static final int _UL__ULSEP_com_oculus_installer_AssetManager_ULSEP_BINDING_ID = 88;
        public static final int _UL__ULSEP_com_oculus_installer_DownloadAndInstallApi_ULSEP_BINDING_ID = 75;
        public static final int _UL__ULSEP_com_oculus_installer_FileIntegrityLogger_ULSEP_BINDING_ID = 135;
        public static final int _UL__ULSEP_com_oculus_installer_InstallerAccessTokenFetcher_ULSEP_BINDING_ID = 137;
        public static final int _UL__ULSEP_com_oculus_library_auth_LibraryAuthListener_ULSEP_BINDING_ID = 61;
        public static final int _UL__ULSEP_com_oculus_library_database_LibraryDatabaseSupplier_ULSEP_BINDING_ID = 206;
        public static final int _UL__ULSEP_com_oculus_library_database_LibrarySchemaPart_ULSEP_BINDING_ID = 231;
        public static final int _UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_BINDING_ID = 180;
        public static final int _UL__ULSEP_com_oculus_library_database_LibraryTable_ULSEP_BINDING_ID = 201;
        public static final int _UL__ULSEP_com_oculus_library_job_LibraryJobInit_ULSEP_BINDING_ID = 161;
        public static final int _UL__ULSEP_com_oculus_library_job_LibraryJobScheduler_ULSEP_BINDING_ID = 108;
        public static final int _UL__ULSEP_com_oculus_library_net_LibraryMethods_ULSEP_BINDING_ID = 10;
        public static final int _UL__ULSEP_com_oculus_library_refresher_LibraryCacheRefresher_ULSEP_BINDING_ID = 150;
        public static final int _UL__ULSEP_com_oculus_library_security_AccessControl_ULSEP_BINDING_ID = 2089;
        public static final int _UL__ULSEP_com_oculus_library_security_TrustedApplications_ULSEP_BINDING_ID = 129;
        public static final int _UL__ULSEP_com_oculus_library_sync_LibrarySyncHelper_ULSEP_BINDING_ID = 184;
        public static final int _UL__ULSEP_com_oculus_library_sync_LibrarySyncMethods_ULSEP_BINDING_ID = 148;
        public static final int _UL__ULSEP_com_oculus_library_utils_AppInternalConverter_ULSEP_BINDING_ID = 131;
        public static final int _UL__ULSEP_com_oculus_library_utils_AppSharingUtils_ULSEP_BINDING_ID = 170;
        public static final int _UL__ULSEP_com_oculus_library_utils_app_AppConverter_ULSEP_BINDING_ID = 225;
        public static final int _UL__ULSEP_com_oculus_libraryapi_OVRLibraryInternal_ULSEP_BINDING_ID = 177;
        public static final int _UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID = 229;
        public static final int _UL__ULSEP_com_oculus_libraryapi_dumper_OVRLibraryDumperPlugin_ULSEP_BINDING_ID = 173;
        public static final int _UL__ULSEP_com_oculus_license_LicenseHelper_ULSEP_BINDING_ID = 16;
        public static final int _UL__ULSEP_com_oculus_license_LicenseMethods_ULSEP_BINDING_ID = 185;
        public static final int _UL__ULSEP_com_oculus_logging_utils_EventManagerImpl_ULSEP_BINDING_ID = 44;
        public static final int _UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID = 214;
        public static final int _UL__ULSEP_com_oculus_logging_utils_StorageLoggingUtils_ULSEP_BINDING_ID = 175;
        public static final int _UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_BINDING_ID = 89;
        public static final int _UL__ULSEP_com_oculus_mobileconfig_dumper_MobileConfigDumperPlugin_ULSEP_BINDING_ID = 121;
        public static final int _UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigConfiguration_ULSEP_BINDING_ID = 0;
        public static final int _UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_BINDING_ID = 246;
        public static final int _UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInit_ULSEP_BINDING_ID = 166;
        public static final int _UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigLoginHandler_ULSEP_BINDING_ID = 202;
        public static final int _UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigLogoutHandler_ULSEP_BINDING_ID = 77;
        public static final int _UL__ULSEP_com_oculus_mobileconfig_updater_MobileConfigUpdaterInit_ULSEP_BINDING_ID = 1;
        public static final int _UL__ULSEP_com_oculus_multiuser_UserClassifier_ULSEP_BINDING_ID = 153;
        public static final int _UL__ULSEP_com_oculus_ocms_app_OCMSConfigValueProvider_ULSEP_BINDING_ID = 46;
        public static final int _UL__ULSEP_com_oculus_ocms_app_OCMSConfigurationPrefs_ULSEP_BINDING_ID = 251;
        public static final int _UL__ULSEP_com_oculus_ocms_app_OCMSCredentialsManager_ULSEP_BINDING_ID = 118;
        public static final int _UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsInstaller_ULSEP_BINDING_ID = 7;
        public static final int _UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsMethods_ULSEP_BINDING_ID = 220;
        public static final int _UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsPrefs_ULSEP_BINDING_ID = 209;
        public static final int _UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsSetupListener_ULSEP_BINDING_ID = 163;
        public static final int _UL__ULSEP_com_oculus_ocms_defaultapps_dumper_DefaultAppsDumperPlugin_ULSEP_BINDING_ID = 169;
        public static final int _UL__ULSEP_com_oculus_ocms_defaultapps_graphql_DefaultAppsGraphQLQuery_ULSEP_BINDING_ID = 57;
        public static final int _UL__ULSEP_com_oculus_ocms_library_EntitlementsInstallerEventListener_ULSEP_BINDING_ID = 60;
        public static final int _UL__ULSEP_com_oculus_ocms_library_OVRLibraryInstallerEventListener_ULSEP_BINDING_ID = 43;
        public static final int _UL__ULSEP_com_oculus_ocms_library_provider_LibraryDownloadListener_ULSEP_BINDING_ID = 25;
        public static final int _UL__ULSEP_com_oculus_ocms_library_provider_SharedAppsHelper_ULSEP_BINDING_ID = 95;
        public static final int _UL__ULSEP_com_oculus_ocms_library_receiver_InstallExistingReceiver_ULSEP_BINDING_ID = 2059;
        public static final int _UL__ULSEP_com_oculus_ocms_locationservices_LocationServiceModule_OcmsMonotonicNanoClock_ULSEP_BINDING_ID = 126;
        public static final int _UL__ULSEP_com_oculus_ocms_locationservices_dumper_LocationServiceDumper_ULSEP_BINDING_ID = 50;
        public static final int _UL__ULSEP_com_oculus_ocms_locationservices_graphql_ApiDispatcher_ULSEP_BINDING_ID = 29;
        public static final int _UL__ULSEP_com_oculus_oculustestsettings_OculusTestSettingsDefaultImpl_ULSEP_BINDING_ID = 204;
        public static final int _UL__ULSEP_com_oculus_oculustestsettings_OculusTestSettings_ULSEP_BINDING_ID = 35;
        public static final int _UL__ULSEP_com_oculus_os_Controllers_ULSEP_BINDING_ID = 2072;
        public static final int _UL__ULSEP_com_oculus_os_DeviceAuth_ULSEP_BINDING_ID = 82;
        public static final int _UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_BINDING_ID = 102;
        public static final int _UL__ULSEP_com_oculus_profileapi_OVRProfile_ULSEP_BINDING_ID = 236;
        public static final int _UL__ULSEP_com_oculus_security_basecomponent_OculusContentProviderLogger_ULSEP_BINDING_ID = 138;
        public static final int _UL__ULSEP_com_oculus_security_basecomponent_OculusIntentLogger_ULSEP_BINDING_ID = 155;
        public static final int _UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID = 63;
        public static final int _UL__ULSEP_com_oculus_unlockulus_ULUNDERSCORE_helper_UnlockulusHelper_ULSEP_BINDING_ID = 6;
        public static final int _UL__ULSEP_com_oculus_userserver_api_inject_OculusUserManagerProvider_ULSEP_BINDING_ID = 2086;
        public static final int _UL__ULSEP_com_oculus_userserver_api_sharing_SharingManager_ULSEP_BINDING_ID = 76;
        public static final int _UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_BINDING_ID = 146;
        public static final int _UL__ULSEP_com_oculus_util_service_ServiceFutures_ULSEP_BINDING_ID = 239;
        public static final int _UL__ULSEP_com_oculus_util_task_TaskDelayFactory_ULSEP_BINDING_ID = 136;
        public static final int _UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID = 243;
        public static final int _UL__ULSEP_com_oculus_util_vr_VRUtils_ULSEP_BINDING_ID = 162;
        public static final int _UL__ULSEP_com_oculus_xanalytics_OculusXAnalyticsProvider_ULSEP_BINDING_ID = 124;
        public static final int _UL__ULSEP_java_lang_Boolean_ULSEP_com_facebook_common_build_IsInternalBuild_ULSEP_BINDING_ID = 2093;
        public static final int _UL__ULSEP_java_lang_Boolean_ULSEP_com_facebook_common_build_IsWorkBuild_ULSEP_BINDING_ID = 2054;
        public static final int _UL__ULSEP_java_lang_Boolean_ULSEP_com_oculus_http_core_annotations_ReportGraphBackendException_ULSEP_BINDING_ID = 160;
        public static final int _UL__ULSEP_java_lang_Integer_ULSEP_com_facebook_common_android_AndroidSdkVersion_ULSEP_BINDING_ID = 2091;
        public static final int _UL__ULSEP_java_lang_Object_ULSEP_com_facebook_common_android_WifiP2pManagerSystemService_ULSEP_BINDING_ID = 2052;
        public static final int _UL__ULSEP_java_lang_Runtime_ULSEP_BINDING_ID = 2058;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_facebook_common_android_PackageName_ULSEP_BINDING_ID = 2124;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_facebook_config_application_ApiConnectionType_ULSEP_BINDING_ID = 2114;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_facebook_http_annotations_UserAgentString_ULSEP_BINDING_ID = 128;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_facebook_mobileconfig_metadata_FromOTAParamsMap_ULSEP_BINDING_ID = 2090;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_facebook_mobileconfig_metadata_OTAAddedParamsMap_ULSEP_BINDING_ID = 2129;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_BINDING_ID = 245;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_BINDING_ID = 159;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_BINDING_ID = 207;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_BINDING_ID = 42;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_BINDING_ID = 154;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentAppVersionMap_ULSEP_BINDING_ID = 19;
        public static final int _UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentString_ULSEP_BINDING_ID = 68;
        public static final int _UL__ULSEP_java_util_GregorianCalendar_ULSEP_BINDING_ID = 2101;
        public static final int _UL__ULSEP_java_util_Locale_ULSEP_BINDING_ID = 172;
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_facebook_stetho_dumpapp_DumperPlugin_ULGT__ULSEP_BINDING_ID = 23;
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_appmanager_info_ApkUpdateInfoListener_ULGT__ULSEP_BINDING_ID = 188;
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_credentials_CredentialsChangedHandler_ULGT__ULSEP_BINDING_ID = 2092;
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LoginHandler_ULGT__ULSEP_BINDING_ID = 67;
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LogoutHandler_ULGT__ULSEP_BINDING_ID = 117;
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_BINDING_ID = 157;
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsHighPriorityInit_ULSEP_BINDING_ID = 9;
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsInitInEnterpriseMode_ULSEP_BINDING_ID = 45;
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_common_packagescache_PackagesListener_ULGT__ULSEP_BINDING_ID = 132;
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_config_interfaces_Configuration_ULGT__ULSEP_BINDING_ID = 203;
        public static final int _UL__ULSEP_java_util_Set_ULLT_com_oculus_downloader_OculusDownloadListener_ULGT__ULSEP_BINDING_ID = 152;
        public static final int _UL__ULSEP_java_util_Set_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_config_annotations_GatekeeperSet_ULSEP_BINDING_ID = 116;
        public static final int _UL__ULSEP_java_util_TimeZone_ULSEP_com_oculus_time_ForOculusTimeZone_ULSEP_BINDING_ID = 2084;
        public static final int _UL__ULSEP_okhttp3_CertificatePinner_ULSEP_BINDING_ID = 133;
        public static final int _UL__ULSEP_okhttp3_ConnectionSpec_ULSEP_BINDING_ID = 53;
        public static final int _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_BINDING_ID = 59;
        public static final int _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_facebook_tigon_oktigon_OkTigonClient_ULSEP_BINDING_ID = 237;
        public static final int _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_customuseragentclient_CustomUserAgentHttpClient_ULSEP_BINDING_ID = 208;
        public static final int _UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_BINDING_ID = 105;
        public static final int _UL__ULSEP_retrofit_ErrorHandler_ULSEP_BINDING_ID = 182;
        public static final int _UL__ULSEP_retrofit_RestAdapter_LogLevel_ULSEP_BINDING_ID = 14;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_CustomUserAgentOculusRestAdapter_ULSEP_BINDING_ID = 2133;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookApiRestAdapter_ULSEP_BINDING_ID = 2051;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphRestAdapter_ULSEP_BINDING_ID = 2085;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoRestAdapter_ULSEP_BINDING_ID = 2053;
        public static final int _UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID = 111;

        public static final int dynamicId(Key key) {
            return 0;
        }

        public static final int id(int i) {
            return i;
        }

        public static final Key intToKey(int i) {
            return null;
        }
    }

    public static final class multibindmap {
        public static final int[] _UL__ULSEP_java_util_Set_ULLT_com_facebook_stetho_dumpapp_DumperPlugin_ULGT__ULSEP_BINDING_ID = {id.id(250), id.id(5), id.id(143), id.id(173), id.id(121), id.id(169), id.id(50)};
        public static final int[] _UL__ULSEP_java_util_Set_ULLT_com_oculus_appmanager_info_ApkUpdateInfoListener_ULGT__ULSEP_BINDING_ID = {id.id(69), id.id(41), id.id(223), id.id(60)};
        public static final int[] _UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_credentials_CredentialsChangedHandler_ULGT__ULSEP_BINDING_ID = new int[0];
        public static final int[] _UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LoginHandler_ULGT__ULSEP_BINDING_ID = {id.id(61), id.id(202)};
        public static final int[] _UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LogoutHandler_ULGT__ULSEP_BINDING_ID = {id.id(61), id.id(77)};
        public static final int[] _UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_BINDING_ID = {id.id(120), id.id(141), id.id(144), id.id(22), id.id(96), id.id(228), id.id(161), id.id(1), id.id(25), id.id(43), id.id(60)};
        public static final int[] _UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsHighPriorityInit_ULSEP_BINDING_ID = {id.id(110), id.id(166)};
        public static final int[] _UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsInitInEnterpriseMode_ULSEP_BINDING_ID = new int[0];
        public static final int[] _UL__ULSEP_java_util_Set_ULLT_com_oculus_common_packagescache_PackagesListener_ULGT__ULSEP_BINDING_ID = {id.id(147)};
        public static final int[] _UL__ULSEP_java_util_Set_ULLT_com_oculus_config_interfaces_Configuration_ULGT__ULSEP_BINDING_ID = {id.id(0)};
        public static final int[] _UL__ULSEP_java_util_Set_ULLT_com_oculus_downloader_OculusDownloadListener_ULGT__ULSEP_BINDING_ID = {id.id(140)};
        public static final int[] _UL__ULSEP_java_util_Set_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_config_annotations_GatekeeperSet_ULSEP_BINDING_ID = new int[0];
    }

    private UL() {
    }

    @AutoGeneratedSwitchSubClass(0)
    public static final class factorymap0 {
        private factorymap0() {
        }

        @AutoGeneratedSwitchSubMethod(0)
        private static Object get0(int i, InjectorLike injectorLike) {
            switch ((i >> 0) & 127) {
                case 0:
                    return MobileConfigConfiguration._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigConfiguration_ULSEP_FACTORY_METHOD(injectorLike);
                case 1:
                    return MobileConfigUpdaterInit._UL__ULSEP_com_oculus_mobileconfig_updater_MobileConfigUpdaterInit_ULSEP_FACTORY_METHOD(injectorLike);
                case 2:
                    return StandaloneApplicationLikeModule._UL__ULSEP_com_oculus_base_app_ApplicationLike_ULSEP_FACTORY_METHOD(injectorLike);
                case 3:
                    return InstallerServiceContract._UL__ULSEP_com_oculus_appmanager_installer_contract_InstallerServiceContract_ULSEP_FACTORY_METHOD(injectorLike);
                case 4:
                    return BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_FACTORY_METHOD(injectorLike);
                case 5:
                    return InstallerServiceDumper._UL__ULSEP_com_oculus_appmanager_installer_dumper_InstallerServiceDumper_ULSEP_FACTORY_METHOD(injectorLike);
                case 6:
                    return UnlockulusModule._UL__ULSEP_com_oculus_unlockulus_ULUNDERSCORE_helper_UnlockulusHelper_ULSEP_FACTORY_METHOD(injectorLike);
                case 7:
                    return DefaultAppsInstaller._UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsInstaller_ULSEP_FACTORY_METHOD(injectorLike);
                case 8:
                    return BundledAndroidModule._UL__ULSEP_com_facebook_auth_viewercontext_ViewerContextManager_ULSEP_com_facebook_auth_viewercontext_ViewerContextManagerForApp_ULSEP_FACTORY_METHOD(injectorLike);
                case 9:
                    return new UltralightMultiBind(injectorLike, multibindmap._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsHighPriorityInit_ULSEP_BINDING_ID);
                case 10:
                    return LibraryMethods._UL__ULSEP_com_oculus_library_net_LibraryMethods_ULSEP_FACTORY_METHOD(injectorLike);
                case 11:
                    return HttpCoreLogger._UL__ULSEP_com_oculus_http_core_HttpCoreLogger_ULSEP_FACTORY_METHOD(injectorLike);
                case 12:
                    return RsyncNativeLibrary._UL__ULSEP_com_oculus_appmanager_patcher_RsyncNativeLibrary_ULSEP_FACTORY_METHOD(injectorLike);
                case 13:
                    return AssetBroadcastDispatch._UL__ULSEP_com_oculus_appmanager_installer_broadcast_AssetBroadcastDispatch_ULSEP_FACTORY_METHOD(injectorLike);
                case 14:
                    return ApiModule._UL__ULSEP_retrofit_RestAdapter_LogLevel_ULSEP_FACTORY_METHOD(injectorLike);
                case 15:
                    return InstallerServiceInstallLock._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceInstallLock_ULSEP_FACTORY_METHOD(injectorLike);
                case 16:
                    return LicenseHelper._UL__ULSEP_com_oculus_license_LicenseHelper_ULSEP_FACTORY_METHOD(injectorLike);
                case 17:
                    return DumpsysLedgerMapper._UL__ULSEP_com_oculus_dumpsysledger_DumpsysLedgerMapper_ULSEP_FACTORY_METHOD(injectorLike);
                case 18:
                    return OCMSAppModule._UL__ULSEP_com_oculus_auth_storage_AuthDatastore_ULSEP_FACTORY_METHOD(injectorLike);
                case 19:
                    return UserAgentModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentAppVersionMap_ULSEP_FACTORY_METHOD(injectorLike);
                case 20:
                    return FbJsonModule._UL__ULSEP_com_facebook_common_json_JsonLoggerStub_ULSEP_FACTORY_METHOD(injectorLike);
                case 21:
                    return AutoUpdatesModule._UL__ULSEP_com_oculus_autoupdates_database_AutoUpdatesDBHelper_ULSEP_FACTORY_METHOD(injectorLike);
                case 22:
                    return DumpsysInit._UL__ULSEP_com_oculus_dumpsysledger_DumpsysInit_ULSEP_FACTORY_METHOD(injectorLike);
                case 23:
                    return new UltralightMultiBind(injectorLike, multibindmap._UL__ULSEP_java_util_Set_ULLT_com_facebook_stetho_dumpapp_DumperPlugin_ULGT__ULSEP_BINDING_ID);
                case 24:
                    return Patcher._UL__ULSEP_com_oculus_appmanager_patcher_Patcher_ULSEP_FACTORY_METHOD(injectorLike);
                case 25:
                    return LibraryDownloadListener._UL__ULSEP_com_oculus_ocms_library_provider_LibraryDownloadListener_ULSEP_FACTORY_METHOD(injectorLike);
                case 26:
                    return new UninstallCompleteEventProvider(injectorLike);
                case 27:
                    return DownloadExtras._UL__ULSEP_com_oculus_downloader_extras_DownloadExtras_ULSEP_FACTORY_METHOD(injectorLike);
                case 28:
                    return OCMSAppModule._UL__ULSEP_com_oculus_auth_receiver_BaseLoginHandler_ULSEP_FACTORY_METHOD(injectorLike);
                case 29:
                    return ApiDispatcher._UL__ULSEP_com_oculus_ocms_locationservices_graphql_ApiDispatcher_ULSEP_FACTORY_METHOD(injectorLike);
                case 30:
                    return ConfigModule._UL__ULSEP_com_oculus_config_gatekeeper_GatekeeperRegistry_ULSEP_FACTORY_METHOD(injectorLike);
                case 31:
                    return MobileConfigOTAUtilModule._UL__ULSEP_com_facebook_mobileconfig_ota_MobileConfigOTAUtil_ULSEP_FACTORY_METHOD(injectorLike);
                case 32:
                    return OculusAuthorizationInterceptor._UL__ULSEP_com_oculus_http_core_interceptor_OculusAuthorizationInterceptor_ULSEP_FACTORY_METHOD(injectorLike);
                case 33:
                    return LoginHandlersRunner._UL__ULSEP_com_oculus_auth_receiver_LoginHandlersRunner_ULSEP_FACTORY_METHOD(injectorLike);
                case 34:
                    return AndroidModule._UL__ULSEP_android_app_NotificationManager_ULSEP_FACTORY_METHOD(injectorLike);
                case 35:
                    return OculusTestSettingsModule._UL__ULSEP_com_oculus_oculustestsettings_OculusTestSettings_ULSEP_FACTORY_METHOD(injectorLike);
                case 36:
                    return ExecutorsModule._UL__ULSEP_com_oculus_executors_OculusThreadExecutor_ULSEP_FACTORY_METHOD(injectorLike);
                case 37:
                    return AutoUpdateTimeTracker._UL__ULSEP_com_oculus_autoupdates_AutoUpdateTimeTracker_ULSEP_FACTORY_METHOD(injectorLike);
                case 38:
                    return ApkUpdateDatabaseSupplier._UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateDatabaseSupplier_ULSEP_FACTORY_METHOD(injectorLike);
                case 39:
                    return LoggingErrorHandler._UL__ULSEP_com_oculus_http_core_LoggingErrorHandler_ULSEP_FACTORY_METHOD(injectorLike);
                case 40:
                    return AndroidModule._UL__ULSEP_android_content_pm_PackageManager_ULSEP_FACTORY_METHOD(injectorLike);
                case 41:
                    return AnalyticsUpdateStateListener._UL__ULSEP_com_oculus_appmanager_installer_service_AnalyticsUpdateStateListener_ULSEP_FACTORY_METHOD(injectorLike);
                case 42:
                    return OCMSAppModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_OculusApiEndpoint_ULSEP_FACTORY_METHOD(injectorLike);
                case 43:
                    return OVRLibraryInstallerEventListener._UL__ULSEP_com_oculus_ocms_library_OVRLibraryInstallerEventListener_ULSEP_FACTORY_METHOD(injectorLike);
                case 44:
                    return EventManagerImpl._UL__ULSEP_com_oculus_logging_utils_EventManagerImpl_ULSEP_FACTORY_METHOD(injectorLike);
                case 45:
                    return new UltralightMultiBind(injectorLike, multibindmap._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_com_oculus_common_init_NeedsInitInEnterpriseMode_ULSEP_BINDING_ID);
                case 46:
                    return OCMSConfigValueProvider._UL__ULSEP_com_oculus_ocms_app_OCMSConfigValueProvider_ULSEP_FACTORY_METHOD(injectorLike);
                case 47:
                    return DozeDelayHelper._UL__ULSEP_com_oculus_appmanager_installer_service_DozeDelayHelper_ULSEP_FACTORY_METHOD(injectorLike);
                case 48:
                    return AutoUpdatesDBManager._UL__ULSEP_com_oculus_autoupdates_database_AutoUpdatesDBManager_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_BINDING_ID /*{ENCODED_INT: 49}*/:
                    return BundledAndroidModule._UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_FACTORY_METHOD(injectorLike);
                case 50:
                    return LocationServiceDumper._UL__ULSEP_com_oculus_ocms_locationservices_dumper_LocationServiceDumper_ULSEP_FACTORY_METHOD(injectorLike);
                case 51:
                    return OculusDownloadListenerDispatcher._UL__ULSEP_com_oculus_downloader_dispatcher_OculusDownloadListenerDispatcher_ULSEP_FACTORY_METHOD(injectorLike);
                case 52:
                    return GkSessionlessModule._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreLogger_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_FACTORY_METHOD(injectorLike);
                case 53:
                    return HttpModule._UL__ULSEP_okhttp3_ConnectionSpec_ULSEP_FACTORY_METHOD(injectorLike);
                case 54:
                    return OculusDownloadProgressTracker._UL__ULSEP_com_oculus_appmanager_downloader_progress_OculusDownloadProgressTracker_ULSEP_FACTORY_METHOD(injectorLike);
                case 55:
                    return ManifestModule._UL__ULSEP_com_facebook_common_manifest_AppBuildInfoReader_ULSEP_FACTORY_METHOD(injectorLike);
                case 56:
                    return InstallerRetryHelper._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerRetryHelper_ULSEP_FACTORY_METHOD(injectorLike);
                case 57:
                    return DefaultAppsGraphQLQuery._UL__ULSEP_com_oculus_ocms_defaultapps_graphql_DefaultAppsGraphQLQuery_ULSEP_FACTORY_METHOD(injectorLike);
                case 58:
                    return InstallerServiceUtil._UL__ULSEP_com_oculus_appmanager_installer_service_util_InstallerServiceUtil_ULSEP_FACTORY_METHOD(injectorLike);
                case 59:
                    return HttpModule._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_FACTORY_METHOD(injectorLike);
                case 60:
                    return EntitlementsInstallerEventListener._UL__ULSEP_com_oculus_ocms_library_EntitlementsInstallerEventListener_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_oculus_library_auth_LibraryAuthListener_ULSEP_BINDING_ID /*{ENCODED_INT: 61}*/:
                    return LibraryAuthListener._UL__ULSEP_com_oculus_library_auth_LibraryAuthListener_ULSEP_FACTORY_METHOD(injectorLike);
                case 62:
                    return com.oculus.horizon.api.ApiDispatcher._UL__ULSEP_com_oculus_horizon_api_ApiDispatcher_ULSEP_FACTORY_METHOD(injectorLike);
                case 63:
                    return Clock._UL__ULSEP_com_oculus_time_Clock_ULSEP_FACTORY_METHOD(injectorLike);
                case 64:
                    return NoOpDeviceAuthTokenSubscriber._UL__ULSEP_com_oculus_auth_device_noop_ULUNDERSCORE_subscriber_NoOpDeviceAuthTokenSubscriber_ULSEP_FACTORY_METHOD(injectorLike);
                case 65:
                    return BundledAndroidModule._UL__ULSEP_com_facebook_auth_viewercontext_ViewerContextManager_ULSEP_com_facebook_auth_viewercontext_ViewerContextManagerForContext_ULSEP_FACTORY_METHOD(injectorLike);
                case 66:
                    return MobileConfigFactoryImplModule._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_FACTORY_METHOD(injectorLike);
                case 67:
                    return new UltralightMultiBind(injectorLike, multibindmap._UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LoginHandler_ULGT__ULSEP_BINDING_ID);
                case 68:
                    return UserAgentModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_UserAgentString_ULSEP_FACTORY_METHOD(injectorLike);
                case 69:
                    return UpdateStateListener._UL__ULSEP_com_oculus_appmanager_installer_service_UpdateStateListener_ULSEP_FACTORY_METHOD(injectorLike);
                case 70:
                    return AssetSQLHelper._UL__ULSEP_com_oculus_appmanager_assets_database_AssetSQLHelper_ULSEP_FACTORY_METHOD(injectorLike);
                case 71:
                    return ExecutorsModule._UL__ULSEP_android_os_Looper_ULSEP_com_oculus_executors_ForUiThread_ULSEP_FACTORY_METHOD(injectorLike);
                case 72:
                    return ApkUpdateExtrasManager._UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateExtrasManager_ULSEP_FACTORY_METHOD(injectorLike);
                case 73:
                    return AccessTokenUtils._UL__ULSEP_com_oculus_auth_util_AccessTokenUtils_ULSEP_FACTORY_METHOD(injectorLike);
                case 74:
                    return InstallationDiskSpaceUtil._UL__ULSEP_com_oculus_appmanager_util_InstallationDiskSpaceUtil_ULSEP_FACTORY_METHOD(injectorLike);
                case 75:
                    return DownloadAndInstallApi._UL__ULSEP_com_oculus_installer_DownloadAndInstallApi_ULSEP_FACTORY_METHOD(injectorLike);
                case 76:
                    return UserServerInjectorModule._UL__ULSEP_com_oculus_userserver_api_sharing_SharingManager_ULSEP_FACTORY_METHOD(injectorLike);
                case 77:
                    return MobileConfigLogoutHandler._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigLogoutHandler_ULSEP_FACTORY_METHOD(injectorLike);
                case 78:
                    return InstallerServiceVerificationHelper._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceVerificationHelper_ULSEP_FACTORY_METHOD(injectorLike);
                case 79:
                    return BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_FACTORY_METHOD(injectorLike);
                case 80:
                    return ConfigModule._UL__ULSEP_android_content_SharedPreferences_ULSEP_com_oculus_config_annotations_GatekeeperSharedPrefs_ULSEP_FACTORY_METHOD(injectorLike);
                case 81:
                    return OuculusDownloadProgressTrackerModule._UL__ULSEP_com_oculus_downloader_progress_DownloadProgressTracker_ULSEP_FACTORY_METHOD(injectorLike);
                case 82:
                    return OsSdkModule._UL__ULSEP_com_oculus_os_DeviceAuth_ULSEP_FACTORY_METHOD(injectorLike);
                case 83:
                    return DefaultFlow._UL__ULSEP_com_oculus_appmanager_installer_service_DefaultFlow_ULSEP_FACTORY_METHOD(injectorLike);
                case 84:
                    return ManifestModule._UL__ULSEP_com_facebook_common_manifest_AppBuildInfo_ULSEP_FACTORY_METHOD(injectorLike);
                case 85:
                    return AssetStorage._UL__ULSEP_com_oculus_appmanager_assets_AssetStorage_ULSEP_FACTORY_METHOD(injectorLike);
                case 86:
                    return InstallerConsistencyHelper._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerConsistencyHelper_ULSEP_FACTORY_METHOD(injectorLike);
                case 87:
                    return SocketConfigModule._UL__ULSEP_com_facebook_http_config_SocketConfig_ULSEP_FACTORY_METHOD(injectorLike);
                case 88:
                    return AssetManager._UL__ULSEP_com_oculus_installer_AssetManager_ULSEP_FACTORY_METHOD(injectorLike);
                case 89:
                    return ManagedMode._UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_FACTORY_METHOD(injectorLike);
                case 90:
                    return FbAppTypeModule._UL__ULSEP_com_facebook_config_application_FbAppType_ULSEP_FACTORY_METHOD(injectorLike);
                case 91:
                    return AutoUpdateScheduler._UL__ULSEP_com_oculus_autoupdates_AutoUpdateScheduler_ULSEP_FACTORY_METHOD(injectorLike);
                case 92:
                    return PackageManagerUtils._UL__ULSEP_com_oculus_common_packagescache_PackageManagerUtils_ULSEP_FACTORY_METHOD(injectorLike);
                case 93:
                    return CryptoMethods._UL__ULSEP_com_oculus_appmanager_installer_common_CryptoMethods_ULSEP_FACTORY_METHOD(injectorLike);
                case 94:
                    return InstallerNotificationManager._UL__ULSEP_com_oculus_appmanager_installer_notification_InstallerNotificationManager_ULSEP_FACTORY_METHOD(injectorLike);
                case 95:
                    return SharedAppsHelper._UL__ULSEP_com_oculus_ocms_library_provider_SharedAppsHelper_ULSEP_FACTORY_METHOD(injectorLike);
                case 96:
                    return AutoUpdatesInit._UL__ULSEP_com_oculus_autoupdates_AutoUpdatesInit_ULSEP_FACTORY_METHOD(injectorLike);
                case 97:
                    return TimeModule._UL__ULSEP_com_facebook_common_time_SystemClock_ULSEP_FACTORY_METHOD(injectorLike);
                case 98:
                    return TimeModule._UL__ULSEP_com_facebook_common_time_RealtimeSinceBootClock_ULSEP_FACTORY_METHOD(injectorLike);
                case 99:
                    return InstallerServiceManager._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceManager_ULSEP_FACTORY_METHOD(injectorLike);
                case 100:
                    return MobileConfigFactoryImplModule._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigManagerSingletonHolder_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_FACTORY_METHOD(injectorLike);
                case 101:
                    return MobileConfigInitModule._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_com_facebook_mobileconfig_factory_module_SessionlessMC_ULSEP_FACTORY_METHOD(injectorLike);
                case 102:
                    return OsSdkModule._UL__ULSEP_com_oculus_os_SettingsManager_ULSEP_FACTORY_METHOD(injectorLike);
                case 103:
                    return ApkUpdateInfoDispatcher._UL__ULSEP_com_oculus_appmanager_info_ApkUpdateInfoDispatcher_ULSEP_FACTORY_METHOD(injectorLike);
                case 104:
                    return CredentialsModule._UL__ULSEP_com_oculus_auth_credentials_Credentials_ULSEP_FACTORY_METHOD(injectorLike);
                case 105:
                    return ApiModule._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_defaultclient_DefaultHttpClient_ULSEP_FACTORY_METHOD(injectorLike);
                case 106:
                    return OkTigonServiceHolder._UL__ULSEP_com_facebook_tigon_oktigon_OkTigonServiceHolder_ULSEP_FACTORY_METHOD(injectorLike);
                case 107:
                    return InstallerFileUtils._UL__ULSEP_com_oculus_appmanager_installer_common_InstallerFileUtils_ULSEP_FACTORY_METHOD(injectorLike);
                case 108:
                    return LibraryJobScheduler._UL__ULSEP_com_oculus_library_job_LibraryJobScheduler_ULSEP_FACTORY_METHOD(injectorLike);
                case 109:
                    return MobileConfigInitModule._UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_FACTORY_METHOD(injectorLike);
                case 110:
                    return ApkUpdateStorage._UL__ULSEP_com_oculus_appmanager_info_ApkUpdateStorage_ULSEP_FACTORY_METHOD(injectorLike);
                case 111:
                    return ApiModule._UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_FACTORY_METHOD(injectorLike);
                case 112:
                    return OculusDownloaderModule._UL__ULSEP_com_oculus_downloader_OculusDownloader_ULSEP_FACTORY_METHOD(injectorLike);
                case 113:
                    return DsatFetcher._UL__ULSEP_com_oculus_dsatauth_DsatFetcher_ULSEP_FACTORY_METHOD(injectorLike);
                case 114:
                    return ApiRequestManager._UL__ULSEP_com_oculus_horizon_api_ApiRequestManager_ULSEP_FACTORY_METHOD(injectorLike);
                case 115:
                    return InstallerNotificationsContract._UL__ULSEP_com_oculus_appmanager_installer_notification_contract_InstallerNotificationsContract_ULSEP_FACTORY_METHOD(injectorLike);
                case 116:
                    return new UltralightMultiBind(injectorLike, multibindmap._UL__ULSEP_java_util_Set_ULLT_java_lang_String_ULGT__ULSEP_com_oculus_config_annotations_GatekeeperSet_ULSEP_BINDING_ID);
                case id._UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LogoutHandler_ULGT__ULSEP_BINDING_ID /*{ENCODED_INT: 117}*/:
                    return new UltralightMultiBind(injectorLike, multibindmap._UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LogoutHandler_ULGT__ULSEP_BINDING_ID);
                case 118:
                    return OCMSCredentialsManager._UL__ULSEP_com_oculus_ocms_app_OCMSCredentialsManager_ULSEP_FACTORY_METHOD(injectorLike);
                case 119:
                    return AssetsMethods._UL__ULSEP_com_oculus_appmanager_assets_AssetsMethods_ULSEP_FACTORY_METHOD(injectorLike);
                case 120:
                    return InstallWatcher._UL__ULSEP_com_oculus_appmanager_assets_InstallWatcher_ULSEP_FACTORY_METHOD(injectorLike);
                case 121:
                    return MobileConfigDumperPlugin._UL__ULSEP_com_oculus_mobileconfig_dumper_MobileConfigDumperPlugin_ULSEP_FACTORY_METHOD(injectorLike);
                case 122:
                    return AppInitModule._UL__ULSEP_com_oculus_common_init_AppInitLock_ULSEP_FACTORY_METHOD(injectorLike);
                case 123:
                    return ErrorReportingModule._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_FACTORY_METHOD(injectorLike);
                case 124:
                    return OculusXAnalyticsProvider._UL__ULSEP_com_oculus_xanalytics_OculusXAnalyticsProvider_ULSEP_FACTORY_METHOD(injectorLike);
                case 125:
                    return OculusFileDownloader._UL__ULSEP_com_oculus_appmanager_downloader_OculusFileDownloader_ULSEP_FACTORY_METHOD(injectorLike);
                case 126:
                    return LocationServiceModule._UL__ULSEP_com_oculus_ocms_locationservices_LocationServiceModule_OcmsMonotonicNanoClock_ULSEP_FACTORY_METHOD(injectorLike);
                case 127:
                    return FileOps._UL__ULSEP_com_oculus_appmanager_util_FileOps_ULSEP_FACTORY_METHOD(injectorLike);
                default:
                    throw new IllegalArgumentException("Invalid Static DI binding id");
            }
        }

        @AutoGeneratedSwitchSubMethod(1)
        private static Object get1(int i, InjectorLike injectorLike) {
            switch ((i >> 0) & 127) {
                case 0:
                    return OculusOkTigonModule._UL__ULSEP_java_lang_String_ULSEP_com_facebook_http_annotations_UserAgentString_ULSEP_FACTORY_METHOD(injectorLike);
                case 1:
                    return TrustedApplications._UL__ULSEP_com_oculus_library_security_TrustedApplications_ULSEP_FACTORY_METHOD(injectorLike);
                case 2:
                    return InstallerAnalytics._UL__ULSEP_com_oculus_appmanager_installer_analytics_InstallerAnalytics_ULSEP_FACTORY_METHOD(injectorLike);
                case 3:
                    return AppInternalConverter._UL__ULSEP_com_oculus_library_utils_AppInternalConverter_ULSEP_FACTORY_METHOD(injectorLike);
                case 4:
                    return new UltralightMultiBind(injectorLike, multibindmap._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_packagescache_PackagesListener_ULGT__ULSEP_BINDING_ID);
                case 5:
                    return HttpModule._UL__ULSEP_okhttp3_CertificatePinner_ULSEP_FACTORY_METHOD(injectorLike);
                case 6:
                    return UserAgentFactory._UL__ULSEP_com_oculus_http_useragent_UserAgentFactory_ULSEP_FACTORY_METHOD(injectorLike);
                case 7:
                    return FileIntegrityLogger._UL__ULSEP_com_oculus_installer_FileIntegrityLogger_ULSEP_FACTORY_METHOD(injectorLike);
                case 8:
                    return TaskDelayFactory._UL__ULSEP_com_oculus_util_task_TaskDelayFactory_ULSEP_FACTORY_METHOD(injectorLike);
                case 9:
                    return InstallerAccessTokenFetcher._UL__ULSEP_com_oculus_installer_InstallerAccessTokenFetcher_ULSEP_FACTORY_METHOD(injectorLike);
                case 10:
                    return OculusContentProviderLogger._UL__ULSEP_com_oculus_security_basecomponent_OculusContentProviderLogger_ULSEP_FACTORY_METHOD(injectorLike);
                case 11:
                    return InstallerServiceDownloadHelper._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceDownloadHelper_ULSEP_FACTORY_METHOD(injectorLike);
                case 12:
                    return InstallerDownloadListener._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerDownloadListener_ULSEP_FACTORY_METHOD(injectorLike);
                case 13:
                    return NotificationEventListener._UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationEventListener_ULSEP_FACTORY_METHOD(injectorLike);
                case 14:
                    return GkSessionlessModule._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreImpl_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_FACTORY_METHOD(injectorLike);
                case 15:
                    return AutoUpdatesDumperPlugin._UL__ULSEP_com_oculus_autoupdates_dumper_AutoUpdatesDumperPlugin_ULSEP_FACTORY_METHOD(injectorLike);
                case 16:
                    return NotificationDownloadListener._UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationDownloadListener_ULSEP_FACTORY_METHOD(injectorLike);
                case 17:
                    return FbJsonModule._UL__ULSEP_com_facebook_common_json_FbObjectMapper_ULSEP_FACTORY_METHOD(injectorLike);
                case 18:
                    return DeviceUtils._UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_FACTORY_METHOD(injectorLike);
                case 19:
                    return PackageBroadcastListener._UL__ULSEP_com_oculus_appmanager_installer_service_PackageBroadcastListener_ULSEP_FACTORY_METHOD(injectorLike);
                case 20:
                    return LibrarySyncMethods._UL__ULSEP_com_oculus_library_sync_LibrarySyncMethods_ULSEP_FACTORY_METHOD(injectorLike);
                case 21:
                    return AutoUpdatesModule._UL__ULSEP_com_google_common_util_concurrent_ListeningExecutorService_ULSEP_com_oculus_autoupdates_AutoUpdateExecutor_ULSEP_FACTORY_METHOD(injectorLike);
                case 22:
                    return LibraryCacheRefresher._UL__ULSEP_com_oculus_library_refresher_LibraryCacheRefresher_ULSEP_FACTORY_METHOD(injectorLike);
                case 23:
                    return AndroidModule._UL__ULSEP_android_os_PowerManager_ULSEP_FACTORY_METHOD(injectorLike);
                case 24:
                    return new UltralightMultiBind(injectorLike, multibindmap._UL__ULSEP_java_util_Set_ULLT_com_oculus_downloader_OculusDownloadListener_ULGT__ULSEP_BINDING_ID);
                case 25:
                    return UserClassifier._UL__ULSEP_com_oculus_multiuser_UserClassifier_ULSEP_FACTORY_METHOD(injectorLike);
                case 26:
                    return OCMSAppModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_useragent_AppNameInUserAgent_ULSEP_FACTORY_METHOD(injectorLike);
                case 27:
                    return OculusIntentLogger._UL__ULSEP_com_oculus_security_basecomponent_OculusIntentLogger_ULSEP_FACTORY_METHOD(injectorLike);
                case 28:
                    return CoreAppManager._UL__ULSEP_com_oculus_coreapps_CoreAppManager_ULSEP_FACTORY_METHOD(injectorLike);
                case 29:
                    return new UltralightMultiBind(injectorLike, multibindmap._UL__ULSEP_java_util_Set_ULLT_com_oculus_common_init_INeedInit_ULGT__ULSEP_BINDING_ID);
                case 30:
                    return VRSignVerifier._UL__ULSEP_com_oculus_appmanager_vrsign_VRSignVerifier_ULSEP_FACTORY_METHOD(injectorLike);
                case 31:
                    return EndpointModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphEndpoint_ULSEP_FACTORY_METHOD(injectorLike);
                case 32:
                    return OCMSAppModule._UL__ULSEP_java_lang_Boolean_ULSEP_com_oculus_http_core_annotations_ReportGraphBackendException_ULSEP_FACTORY_METHOD(injectorLike);
                case 33:
                    return LibraryJobInit._UL__ULSEP_com_oculus_library_job_LibraryJobInit_ULSEP_FACTORY_METHOD(injectorLike);
                case 34:
                    return UtilModule._UL__ULSEP_com_oculus_util_vr_VRUtils_ULSEP_FACTORY_METHOD(injectorLike);
                case 35:
                    return DefaultAppsSetupListener._UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsSetupListener_ULSEP_FACTORY_METHOD(injectorLike);
                case 36:
                    return InstallerCancelHelper._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerCancelHelper_ULSEP_FACTORY_METHOD(injectorLike);
                case 37:
                    return NoOpDeviceAuthTokenSubscriberModule._UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenSubscriber_ULSEP_FACTORY_METHOD(injectorLike);
                case 38:
                    return MobileConfigInit._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInit_ULSEP_FACTORY_METHOD(injectorLike);
                case 39:
                    return InstallerFailureHelper._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerFailureHelper_ULSEP_FACTORY_METHOD(injectorLike);
                case 40:
                    return OculusXAnalyticsModule._UL__ULSEP_com_facebook_xanalytics_XAnalyticsProvider_ULSEP_FACTORY_METHOD(injectorLike);
                case 41:
                    return DefaultAppsDumperPlugin._UL__ULSEP_com_oculus_ocms_defaultapps_dumper_DefaultAppsDumperPlugin_ULSEP_FACTORY_METHOD(injectorLike);
                case 42:
                    return AppSharingUtils._UL__ULSEP_com_oculus_library_utils_AppSharingUtils_ULSEP_FACTORY_METHOD(injectorLike);
                case 43:
                    return GkSessionlessModule._UL__ULSEP_com_facebook_gk_store_GatekeeperWriter_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_FACTORY_METHOD(injectorLike);
                case 44:
                    return LocaleModule._UL__ULSEP_java_util_Locale_ULSEP_FACTORY_METHOD(injectorLike);
                case 45:
                    return OVRLibraryDumperPlugin._UL__ULSEP_com_oculus_libraryapi_dumper_OVRLibraryDumperPlugin_ULSEP_FACTORY_METHOD(injectorLike);
                case 46:
                    return OCMSAppModule._UL__ULSEP_com_oculus_auth_credentials_CredentialsManager_ULSEP_FACTORY_METHOD(injectorLike);
                case 47:
                    return StorageLoggingUtils._UL__ULSEP_com_oculus_logging_utils_StorageLoggingUtils_ULSEP_FACTORY_METHOD(injectorLike);
                case 48:
                    return AppInitializer._UL__ULSEP_com_oculus_common_init_impl_AppInitializer_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_com_facebook_inject_InjectorLike_ULSEP_BINDING_ID /*{ENCODED_INT: 49}*/:
                    return OVRLibraryModule._UL__ULSEP_com_oculus_libraryapi_OVRLibraryInternal_ULSEP_FACTORY_METHOD(injectorLike);
                case 50:
                    return OkTigonService._UL__ULSEP_com_facebook_tigon_oktigon_OkTigonService_ULSEP_FACTORY_METHOD(injectorLike);
                case 51:
                    return OVRAuthModule._UL__ULSEP_com_oculus_authapi_OVRAuth_ULSEP_FACTORY_METHOD(injectorLike);
                case 52:
                    return LibraryStorage._UL__ULSEP_com_oculus_library_database_LibraryStorage_ULSEP_FACTORY_METHOD(injectorLike);
                case 53:
                    return OCMSAppModule._UL__ULSEP_com_fasterxml_jackson_databind_ObjectMapper_ULSEP_FACTORY_METHOD(injectorLike);
                case 54:
                    return ApiModule._UL__ULSEP_retrofit_ErrorHandler_ULSEP_FACTORY_METHOD(injectorLike);
                case 55:
                    return new UninstallerAsyncTaskProvider(injectorLike);
                case 56:
                    return LibrarySyncHelper._UL__ULSEP_com_oculus_library_sync_LibrarySyncHelper_ULSEP_FACTORY_METHOD(injectorLike);
                case 57:
                    return LicenseMethods._UL__ULSEP_com_oculus_license_LicenseMethods_ULSEP_FACTORY_METHOD(injectorLike);
                case 58:
                    return ConfigModule._UL__ULSEP_com_oculus_config_gatekeeper_DynamicGatekeeperRegistry_ULSEP_FACTORY_METHOD(injectorLike);
                case 59:
                    return StandaloneApplicationLike._UL__ULSEP_com_oculus_app_standalone_StandaloneApplicationLike_ULSEP_FACTORY_METHOD(injectorLike);
                case 60:
                    return new UltralightMultiBind(injectorLike, multibindmap._UL__ULSEP_java_util_Set_ULLT_com_oculus_appmanager_info_ApkUpdateInfoListener_ULGT__ULSEP_BINDING_ID);
                case id._UL__ULSEP_com_oculus_library_auth_LibraryAuthListener_ULSEP_BINDING_ID /*{ENCODED_INT: 61}*/:
                    return InstallerEventEmitter._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerEventEmitter_ULSEP_FACTORY_METHOD(injectorLike);
                case 62:
                    return TimeModule._UL__ULSEP_com_facebook_common_time_AwakeTimeSinceBootClock_ULSEP_FACTORY_METHOD(injectorLike);
                case 63:
                    return GkSessionlessModule._UL__ULSEP_com_facebook_gk_store_GatekeeperStore_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_FACTORY_METHOD(injectorLike);
                case 64:
                    return InternalModule._UL__ULSEP_com_oculus_android_os_internal_UserHandleInternal_ULSEP_FACTORY_METHOD(injectorLike);
                case 65:
                    return com.oculus.android.AndroidModule._UL__ULSEP_android_bluetooth_BluetoothAdapter_ULSEP_FACTORY_METHOD(injectorLike);
                case 66:
                    return MobileConfigInitModule._UL__ULSEP_android_content_pm_PackageInfo_ULSEP_FACTORY_METHOD(injectorLike);
                case 67:
                    return ExecutorsModule._UL__ULSEP_android_os_Handler_ULSEP_com_oculus_executors_ForUiThread_ULSEP_FACTORY_METHOD(injectorLike);
                case 68:
                    return AndroidModule._UL__ULSEP_android_net_ConnectivityManager_ULSEP_FACTORY_METHOD(injectorLike);
                case 69:
                    return MobileConfigFactoryImplModule._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigValueUtil_ULSEP_FACTORY_METHOD(injectorLike);
                case 70:
                    return ApkUpdateExtrasTable._UL__ULSEP_com_oculus_appmanager_info_schema_ApkUpdateExtrasTable_ULSEP_FACTORY_METHOD(injectorLike);
                case 71:
                    return CallerInfoProviderImpl._UL__ULSEP_com_oculus_authapi_inject_CallerInfoProviderImpl_ULSEP_FACTORY_METHOD(injectorLike);
                case 72:
                    return InstallerCleanUpHelper._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerCleanUpHelper_ULSEP_FACTORY_METHOD(injectorLike);
                case 73:
                    return LibraryTable._UL__ULSEP_com_oculus_library_database_LibraryTable_ULSEP_FACTORY_METHOD(injectorLike);
                case 74:
                    return MobileConfigLoginHandler._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigLoginHandler_ULSEP_FACTORY_METHOD(injectorLike);
                case 75:
                    return new UltralightMultiBind(injectorLike, multibindmap._UL__ULSEP_java_util_Set_ULLT_com_oculus_config_interfaces_Configuration_ULGT__ULSEP_BINDING_ID);
                case 76:
                    return OculusTestSettingsDefaultImpl._UL__ULSEP_com_oculus_oculustestsettings_OculusTestSettingsDefaultImpl_ULSEP_FACTORY_METHOD(injectorLike);
                case 77:
                    return InstallerServiceUninstallHelper._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceUninstallHelper_ULSEP_FACTORY_METHOD(injectorLike);
                case 78:
                    return LibraryDatabaseSupplier._UL__ULSEP_com_oculus_library_database_LibraryDatabaseSupplier_ULSEP_FACTORY_METHOD(injectorLike);
                case 79:
                    return EndpointModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookGraphVideoEndpoint_ULSEP_FACTORY_METHOD(injectorLike);
                case 80:
                    return ApiModule._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_oculus_http_customuseragentclient_CustomUserAgentHttpClient_ULSEP_FACTORY_METHOD(injectorLike);
                case 81:
                    return DefaultAppsPrefs._UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsPrefs_ULSEP_FACTORY_METHOD(injectorLike);
                case 82:
                    return NotificationEligibility._UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationEligibility_ULSEP_FACTORY_METHOD(injectorLike);
                case 83:
                    return LocationServiceModule._UL__ULSEP_com_facebook_common_time_MonotonicClock_ULSEP_FACTORY_METHOD(injectorLike);
                case 84:
                    return AndroidModule._UL__ULSEP_android_app_DownloadManager_ULSEP_FACTORY_METHOD(injectorLike);
                case 85:
                    return MobileConfigFactoryImplModule._UL__ULSEP_com_facebook_mobileconfig_impl_MobileConfigDebugUtil_ULSEP_FACTORY_METHOD(injectorLike);
                case 86:
                    return UtilsModule._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_FACTORY_METHOD(injectorLike);
                case 87:
                    return ApkUpdateSchemaPart._UL__ULSEP_com_oculus_appmanager_info_schema_ApkUpdateSchemaPart_ULSEP_FACTORY_METHOD(injectorLike);
                case 88:
                    return GkSessionlessModule._UL__ULSEP_com_facebook_gk_store_GatekeeperStoreConfig_ULSEP_com_facebook_gk_sessionless_Sessionless_ULSEP_FACTORY_METHOD(injectorLike);
                case 89:
                    return FbJsonModule._UL__ULSEP_com_fasterxml_jackson_dataformat_smile_SmileFactory_ULSEP_FACTORY_METHOD(injectorLike);
                case 90:
                    return OCMSAppModule._UL__ULSEP_com_oculus_base_app_AppInfo_ULSEP_FACTORY_METHOD(injectorLike);
                case 91:
                    return DeviceAuthTokenStore._UL__ULSEP_com_oculus_auth_device_DeviceAuthTokenStore_ULSEP_FACTORY_METHOD(injectorLike);
                case 92:
                    return DefaultAppsMethods._UL__ULSEP_com_oculus_ocms_defaultapps_DefaultAppsMethods_ULSEP_FACTORY_METHOD(injectorLike);
                case 93:
                    return EcdsaSignatureVerifier._UL__ULSEP_com_oculus_appmanager_installer_common_EcdsaSignatureVerifier_ULSEP_FACTORY_METHOD(injectorLike);
                case 94:
                    return LocationServiceModule._UL__ULSEP_com_facebook_wifiscan_WifiScan_ULSEP_FACTORY_METHOD(injectorLike);
                case 95:
                    return NotificationUpdateInfoListener._UL__ULSEP_com_oculus_appmanager_installer_notification_NotificationUpdateInfoListener_ULSEP_FACTORY_METHOD(injectorLike);
                case 96:
                    return ApiResponseConverter._UL__ULSEP_com_oculus_http_core_ApiResponseConverter_ULSEP_FACTORY_METHOD(injectorLike);
                case 97:
                    return AppConverterUtilsModule._UL__ULSEP_com_oculus_library_utils_app_AppConverter_ULSEP_FACTORY_METHOD(injectorLike);
                case 98:
                    return TimeModule._UL__ULSEP_com_facebook_common_time_CurrentThreadTimeClock_ULSEP_FACTORY_METHOD(injectorLike);
                case 99:
                    return InfoUtils._UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_FACTORY_METHOD(injectorLike);
                case 100:
                    return DownloaderInit._UL__ULSEP_com_oculus_downloader_init_DownloaderInit_ULSEP_FACTORY_METHOD(injectorLike);
                case 101:
                    return OVRLibraryModule._UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_FACTORY_METHOD(injectorLike);
                case 102:
                    return MobileConfigParamsMapModule._UL__ULSEP_com_facebook_mobileconfig_metadata_ParamsMapList_ULSEP_FACTORY_METHOD(injectorLike);
                case 103:
                    return LibrarySchemaPart._UL__ULSEP_com_oculus_library_database_LibrarySchemaPart_ULSEP_FACTORY_METHOD(injectorLike);
                case 104:
                    return DownloaderContract._UL__ULSEP_com_oculus_downloader_contract_DownloaderContract_ULSEP_FACTORY_METHOD(injectorLike);
                case 105:
                    return InstallQueue._UL__ULSEP_com_oculus_appmanager_installer_service_InstallQueue_ULSEP_FACTORY_METHOD(injectorLike);
                case 106:
                    return new ApkUpdateInfoProvider(injectorLike);
                case 107:
                    return OculusLogger._UL__ULSEP_com_oculus_horizon_logging_OculusLogger_ULSEP_FACTORY_METHOD(injectorLike);
                case 108:
                    return OVRProfile._UL__ULSEP_com_oculus_profileapi_OVRProfile_ULSEP_FACTORY_METHOD(injectorLike);
                case 109:
                    return OculusOkTigonModule._UL__ULSEP_okhttp3_OkHttpClient_ULSEP_com_facebook_tigon_oktigon_OkTigonClient_ULSEP_FACTORY_METHOD(injectorLike);
                case 110:
                    return ManifestModule._UL__ULSEP_com_facebook_common_manifest_ManifestReader_ULSEP_FACTORY_METHOD(injectorLike);
                case 111:
                    return ServiceFutures._UL__ULSEP_com_oculus_util_service_ServiceFutures_ULSEP_FACTORY_METHOD(injectorLike);
                case 112:
                    return InstallerServiceInstallHelper._UL__ULSEP_com_oculus_appmanager_installer_service_InstallerServiceInstallHelper_ULSEP_FACTORY_METHOD(injectorLike);
                case 113:
                    return DebugMode._UL__ULSEP_com_oculus_debug_DebugMode_ULSEP_FACTORY_METHOD(injectorLike);
                case 114:
                    return PermissionManager._UL__ULSEP_com_oculus_autoupdates_PermissionManager_ULSEP_FACTORY_METHOD(injectorLike);
                case 115:
                    return ThreadUtils._UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_FACTORY_METHOD(injectorLike);
                case 116:
                    return EventsModule._UL__ULSEP_com_oculus_appmanager_installer_events_InstallerEventBus_ULSEP_FACTORY_METHOD(injectorLike);
                case id._UL__ULSEP_java_util_Set_ULLT_com_oculus_auth_handler_LogoutHandler_ULGT__ULSEP_BINDING_ID /*{ENCODED_INT: 117}*/:
                    return EndpointModule._UL__ULSEP_java_lang_String_ULSEP_com_oculus_http_core_annotations_FacebookApiEndpoint_ULSEP_FACTORY_METHOD(injectorLike);
                case 118:
                    return OCMSAppModule._UL__ULSEP_com_oculus_mobileconfig_init_MobileConfigInitOptions_ULSEP_FACTORY_METHOD(injectorLike);
                case 119:
                    return ApkUpdateTable._UL__ULSEP_com_oculus_appmanager_info_schema_ApkUpdateTable_ULSEP_FACTORY_METHOD(injectorLike);
                case 120:
                    return LocationServiceModule._UL__ULSEP_com_facebook_common_time_Clock_ULSEP_FACTORY_METHOD(injectorLike);
                case 121:
                    return AutoUpdatesManager._UL__ULSEP_com_oculus_autoupdates_AutoUpdatesManager_ULSEP_FACTORY_METHOD(injectorLike);
                case 122:
                    return AssetsDumper._UL__ULSEP_com_oculus_appmanager_assets_AssetsDumper_ULSEP_FACTORY_METHOD(injectorLike);
                case 123:
                    return OCMSConfigurationPrefs._UL__ULSEP_com_oculus_ocms_app_OCMSConfigurationPrefs_ULSEP_FACTORY_METHOD(injectorLike);
                default:
                    throw new IllegalArgumentException("Invalid Static DI binding id");
            }
        }

        @AutoGeneratedSwitchSubClassMasterMethod
        public static Object get(int i, InjectorLike injectorLike) {
            int i2 = (i >> 7) & 15;
            if (i2 == 0) {
                return get0(i, injectorLike);
            }
            if (i2 == 1) {
                return get1(i, injectorLike);
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
