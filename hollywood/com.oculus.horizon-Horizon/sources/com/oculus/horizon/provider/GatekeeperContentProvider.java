package com.oculus.horizon.provider;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass117;
import X.C01020Iw;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.Uri;
import com.facebook.ultralight.Eager;
import com.google.inject.name.Named;
import com.oculus.common.packagescache.PackageManagerUtils;
import com.oculus.content.OculusPublicContentProvider;
import com.oculus.horizon.profile.UserProfileHelper;
import com.oculus.horizon.service.CallerUtils;
import com.oculus.horizon.service.OVRModule;
import java.util.HashMap;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Provider;

public class GatekeeperContentProvider extends OculusPublicContentProvider {
    public static final HashMap<String, String> SYSTEM_UTILITIES_REQUIRED_MIN_VERSIONS = new HashMap<String, String>() {
        /* class com.oculus.horizon.provider.GatekeeperContentProvider.AnonymousClass1 */

        {
            put("com.oculus.vrshell", "2.0.35");
            put("com.oculus.vrshell.home", "3.16.0");
            put("com.oculus.systemactivities", "1.14.3");
            put("com.oculus.systemdriver", "1.6.3");
        }
    };
    public static final String TAG = "GatekeeperContentProvider";
    public AnonymousClass0QC _UL_mInjectionContext;
    @Named(OVRModule.ASSISTANT_ON_OCULUS)
    @Inject
    public Provider<Boolean> mAssistantOnOculus;
    @Named(OVRModule.ASSISTANT_OCULUS_USE_MESSAGE_BUS)
    @Inject
    public Provider<Boolean> mAssistantOnOculusUseMessageBus;
    @Inject
    @Eager
    public CallerUtils mCallerUtils;
    @Named(OVRModule.DEVICECONFIG_TEST_73)
    @Inject
    public Provider<Boolean> mDeviceConfigTest73;
    @Named(OVRModule.OCULUS_TREX_BODYAPI_KILLSW)
    @Inject
    public Provider<Boolean> mDisableTrexBodyApi;
    @Named(OVRModule.OCULUS_TREX_FITNESSTRACKER_KILLSW)
    @Inject
    public Provider<Boolean> mDisableTrexFitnessTracker;
    @Named(OVRModule.OCULUS_TREX_RUNTIMECLIENT_KILLSW)
    @Inject
    public Provider<Boolean> mDisableTrexRuntimeClient;
    @Named(OVRModule.OCULUS_TREX_RUNTIMESERVICE_KILLSW)
    @Inject
    public Provider<Boolean> mDisableTrexRuntimeService;
    @Named(OVRModule.OCULUS_TREX_SPATIALPERSISTENCE_KILLSW)
    @Inject
    public Provider<Boolean> mDisableTrexSpatialPersistence;
    @Named(OVRModule.OCULUS_TREX_SUPERRES_KILLSW)
    @Inject
    public Provider<Boolean> mDisableTrexSuperRes;
    @Named(OVRModule.ENABLE_APP_SAFETY_BINARY_CHECK)
    @Inject
    public Provider<Boolean> mEnableAppSafetyBinaryCheck;
    @Named(OVRModule.ENABLE_BAD_DISCHARGE_STATS)
    @Inject
    public Provider<Boolean> mEnableBadDischargeStats;
    @Named(OVRModule.ENABLE_BATTERY_DRAIN_SERVICE)
    @Inject
    public Provider<Boolean> mEnableBatteryDrainService;
    @Named(OVRModule.ENABLE_BUGREPORT_SERVICE)
    @Inject
    public Provider<Boolean> mEnableBugReportService;
    @Named(OVRModule.ENABLE_CHROMECAST_101_ERROR_KEEP_CONNECTED)
    @Inject
    public Provider<Boolean> mEnableChromecast101ErrorKeepConnected;
    @Named(OVRModule.ENABLE_CHROMECAST_FORCE_H264_FOR_SMART_TVS)
    @Inject
    public Provider<Boolean> mEnableChromecastForceH264ForSmartTvs;
    @Named(OVRModule.ENABLE_CHROMECAST_H264_OVERRIDE)
    @Inject
    public Provider<Boolean> mEnableChromecastH264Override;
    @Named(OVRModule.ENABLE_CHROMECAST_INCREASE_PING_FAILURE_TOLERANCE)
    @Inject
    public Provider<Boolean> mEnableChromecastIncreasePingFailureTolerance;
    @Named(OVRModule.ENABLE_CHROMECAST_INIT_NSD_ON_DISCOVERY)
    @Inject
    public Provider<Boolean> mEnableChromecastInitNsdOnDiscovery;
    @Named(OVRModule.ENABLE_CHROMECAST_LONGER_PING_INTERVAL)
    @Inject
    public Provider<Boolean> mEnableChromecastLongerPingInterval;
    @Named(OVRModule.ENABLE_CHROMECAST_REMOVE_PING)
    @Inject
    public Provider<Boolean> mEnableChromecastRemovePing;
    @Named(OVRModule.ENABLE_CHROMECASTING)
    @Inject
    public Provider<Boolean> mEnableChromecasting;
    @Named(OVRModule.ENABLE_CONFIGURABLE_MTP_DIALOG)
    @Inject
    public Provider<Boolean> mEnableConfigurableMtpDialog;
    @Named(OVRModule.ENABLE_CONTROLLER_LED_CONTROL)
    @Inject
    public Provider<Boolean> mEnableControllerLedControl;
    @Named(OVRModule.ENABLE_DEVICECONFIG_TEST)
    @Inject
    public Provider<Boolean> mEnableDeviceConfigTest;
    @Named(OVRModule.ENABLE_DOUBLETAP_PASSTHROUGH)
    @Inject
    public Provider<Boolean> mEnableDoubletapPassthrough;
    @Named(OVRModule.ENABLE_FLAIL_LOCKOUT)
    @Inject
    public Provider<Boolean> mEnableFlailLockout;
    @Named(OVRModule.ENABLE_GUARDIAN_INTERNAL_ANCHOR)
    @Inject
    public Provider<Boolean> mEnableGuardianInternalAnchor;
    @Named(OVRModule.ENABLE_GUARDIAN_NONSENSITIVE_TELEMETRY)
    @Inject
    public Provider<Boolean> mEnableGuardianNonsensitiveTelemetry;
    @Named(OVRModule.ENABLE_GUARDIAN_ON_SCREEN_CAPTURE)
    @Inject
    public Provider<Boolean> mEnableGuardianOnScreenCapture;
    @Named(OVRModule.ENABLE_HAND_TRACKING_FREQUENCY_INTERNAL)
    @Inject
    public Provider<Boolean> mEnableHandTrackingFrequencyInternal;
    @Named(OVRModule.HAND_TRACKING_GA)
    @Inject
    public Provider<Boolean> mEnableHandTrackingGA;
    @Named(OVRModule.ENABLE_HOMEBUTTON_EATTRIGGER_SPOTFIX)
    @Inject
    public Provider<Boolean> mEnableHomeButtonEatTriggerSpotFix;
    @Named(OVRModule.ENABLE_HOMEBUTTON_FREEZEINPUT)
    @Inject
    public Provider<Boolean> mEnableHomeButtonFreezeInput;
    @Named(OVRModule.ENABLE_HOMEBUTTON_FREEZEINPUT_API_CHECK)
    @Inject
    public Provider<Boolean> mEnableHomeButtonFreezeInputApiCheck;
    @Named(OVRModule.ENABLE_INTRUSION_DETECTION)
    @Inject
    public Provider<Boolean> mEnableIntrusionDetection;
    @Named(OVRModule.ENABLE_INTRUSION_DETECTION_HEADLESS)
    @Inject
    public Provider<Boolean> mEnableIntrusionDetectionHeadless;
    @Named(OVRModule.ENABLE_LINK_NO_MODAL)
    @Inject
    public Provider<Boolean> mEnableLinkNoModal;
    @Named(OVRModule.ENABLE_LINK_VRSHELL_MODAL)
    @Inject
    public Provider<Boolean> mEnableLinkVrShellModal;
    @Named(OVRModule.ENABLE_LOCATION_SERVICE)
    @Inject
    public Provider<Boolean> mEnableLocationService;
    @Named(OVRModule.ENABLE_MOBILE_SCREENSHOT_SHORTCUT)
    @Inject
    public Provider<Boolean> mEnableMobileScreenshotShortcut;
    @Named(OVRModule.ENABLE_MR_DESK_CREATION)
    @Inject
    public Provider<Boolean> mEnableMrDeskCreation;
    @Named(OVRModule.ENABLE_MRSERVICE_INSIGHTSDK)
    @Inject
    public Provider<Boolean> mEnableMrServiceInsightSDK;
    @Named(OVRModule.ENABLE_MULTI_USER)
    @Inject
    public Provider<Boolean> mEnableMultiUser;
    @Named(OVRModule.ENABLE_NEW_NOTIFICATIONS_SOUND)
    @Inject
    public Provider<Boolean> mEnableNewNotificationsSound;
    @Named(OVRModule.ENABLE_NOTIFICATION_DND_AGGREGATION)
    @Inject
    public Provider<Boolean> mEnableNotificationDNDAggregation;
    @Named(OVRModule.ENABLE_NOTIFICATION_SOFT_FOLLOW)
    @Inject
    public Provider<Boolean> mEnableNotificationSoftFollow;
    @Named(OVRModule.ENABLE_OCULUS_MEDIA_SHARED_MIC)
    @Inject
    public Provider<Boolean> mEnableOculusMediaSharedMic;
    @Named(OVRModule.ENABLE_PASSTHROUGH_QUICKBOOT)
    @Inject
    public Provider<Boolean> mEnablePassthroughQuickboot;
    @Named(OVRModule.ENABLE_PHONE_NOTIFICATIONS)
    @Inject
    public Provider<Boolean> mEnablePhoneNotifications;
    @Named(OVRModule.ENABLE_QUEST_GLANCEABLE_BOUNDS)
    @Inject
    public Provider<Boolean> mEnableQuestGlanceableBounds;
    @Named(OVRModule.ENABLE_REALITY_TUNER)
    @Inject
    public Provider<Boolean> mEnableRealityTuner;
    @Named(OVRModule.ENABLE_RETAIL_DEMO)
    @Inject
    public Provider<Boolean> mEnableRetailDemo;
    @Named(OVRModule.SETTINGS_HAND_TRACKING)
    @Inject
    public Provider<Boolean> mEnableSettingsHandTracking;
    @Named(OVRModule.ENABLE_SMALL_FINGER_OPENING_PINCH_THRESHOLD)
    @Inject
    public Provider<Boolean> mEnableSmallFingerOpeningPinchThreshold;
    @Named(OVRModule.ENABLE_SPATIAL_ANCHOR)
    @Inject
    public Provider<Boolean> mEnableSpatialAnchor;
    @Named(OVRModule.ENABLE_STATIONARY_GUARDIAN_V2)
    @Inject
    public Provider<Boolean> mEnableStationaryGuardianV2;
    @Named(OVRModule.ENABLE_TOUR_GUIDE)
    @Inject
    public Provider<Boolean> mEnableTourGuide;
    @Named(OVRModule.ENABLE_VR_BUGREPORTER)
    @Inject
    public Provider<Boolean> mEnableVrBugReporter;
    @Named(OVRModule.ENABLE_VRSHELL_FOCUS_AWARENESS_FORCE_ENABLE)
    @Inject
    public Provider<Boolean> mEnableVrShellFocusAwarenessForceEnable;
    @Named(OVRModule.ENABLE_VRSHELL_FOCUS_AWARENESS_PUSH_ROLLOUT)
    @Inject
    public Provider<Boolean> mEnableVrShellFocusAwarenessPushRollout;
    @Named(OVRModule.ENABLE_VRSHELL_IAP_IN_OVERLAY_DISABLE)
    @Inject
    public Provider<Boolean> mEnableVrShellIapInOverlayDisable;
    @Named(OVRModule.ENABLE_VRSHELL_SYSICONS_HANDS)
    @Inject
    public Provider<Boolean> mEnableVrShellSysiconsHands;
    @Named(OVRModule.HOME_SHORTPRESS_LOGGING)
    @Inject
    public Provider<Boolean> mHomeShortPressLogging;
    @Named(OVRModule.IS_ABUSE_PREVENTION_OVERLAYS_ENABLED_GK)
    @Inject
    public Provider<Boolean> mIsAPOverlaysEnabled;
    @Named(OVRModule.ENABLE_P2P_DTX_GK)
    @Inject
    public Provider<Boolean> mIsDTXEnabled;
    @Named(OVRModule.IS_LIVESTREAMING_ENABLED_GK)
    @Inject
    public Provider<Boolean> mIsLivestreamingEnabled;
    @Named(OVRModule.IS_OSUPDATER_NONPERSISTENT_ENABLED_GK)
    @Inject
    public Provider<Boolean> mIsOSUpdaterNonpersistentEnabled;
    @Named(OVRModule.IS_OSUPDATER_TEST_MODE_ENABLED_GK)
    @Inject
    public Provider<Boolean> mIsOSUpdaterTestModeEnabled;
    @Named(OVRModule.IS_OSUPDATER_TRAFFIC_TRACING_ENABLED_GK)
    @Inject
    public Provider<Boolean> mIsOSUpdaterTrafficTracingEnabled;
    @Named(OVRModule.IS_SA_TRANSCRIBE_ENABLED_GK)
    @Inject
    public Provider<Boolean> mIsSATranscribeEnabled;
    @Named(OVRModule.IS_SYSTEM_UTILITIES_ANYTIMEUI_ENABLED_GK)
    @Inject
    public Provider<Boolean> mIsSystemUtilitiesAnytimeUIEnabled;
    @Named(OVRModule.IS_SYSTEM_UTILITIES_ENABLED_GK)
    @Inject
    public Provider<Boolean> mIsSystemUtilitiesEnabled;
    @Named(OVRModule.IS_SYSTEM_UTILITIES_EXTRA_COMMANDS_ENABLED_GK)
    @Inject
    public Provider<Boolean> mIsSystemUtilitiesExtraCommandsEnabled;
    @Named(OVRModule.MOBILE_BREAKPAD)
    @Inject
    public Provider<Boolean> mMobileBreakpad;
    @Named(OVRModule.MOBILE_POINTER_POSE)
    @Inject
    public Provider<Boolean> mMobilePointerPose;
    @Named(OVRModule.MOBILE_SPACEWARP)
    @Inject
    public Provider<Boolean> mMobileSpaceWarp;
    @Named(OVRModule.QUEST_PLAYSPACE_SCAN)
    @Inject
    public Provider<Boolean> mQuestPlayspaceScan;
    @Named(OVRModule.QUEST_PLAYSPACE_SCAN_HEADLESS)
    @Inject
    public Provider<Boolean> mQuestPlayspaceScanHeadless;
    @Named(OVRModule.USE_DSAT_FOR_PARTY_VOIP_GK)
    @Inject
    public Provider<Boolean> mUseDsatForPartyVoipGK;
    @Named(OVRModule.USE_VERTS_PARTY_VOIP_GK)
    @Inject
    public Provider<Boolean> mUseVertsPartyVoipGK;
    @Inject
    @Eager
    public UserProfileHelper mUserProfileHelper;

    public static final void _UL_staticInjectMe(AbstractC06640p5 r2, GatekeeperContentProvider gatekeeperContentProvider) {
        gatekeeperContentProvider._UL_mInjectionContext = new AnonymousClass0QC(2, r2);
        gatekeeperContentProvider.mUserProfileHelper = (UserProfileHelper) AnonymousClass117.A00(68, r2);
        gatekeeperContentProvider.mCallerUtils = CallerUtils._UL__ULSEP_com_oculus_horizon_service_CallerUtils_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mIsAPOverlaysEnabled = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_abuse_ULUNDERSCORE_prevention_ULUNDERSCORE_overlays_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mIsLivestreamingEnabled = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_livestreaming_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mIsSystemUtilitiesEnabled = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_vrshell_ULUNDERSCORE_enable_ULUNDERSCORE_system_ULUNDERSCORE_utilities_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mIsSystemUtilitiesExtraCommandsEnabled = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_vrshell_ULUNDERSCORE_enable_ULUNDERSCORE_system_ULUNDERSCORE_utilities_ULUNDERSCORE_extra_ULUNDERSCORE_cmds_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mIsSystemUtilitiesAnytimeUIEnabled = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_vrshell_ULUNDERSCORE_enable_ULUNDERSCORE_system_ULUNDERSCORE_utilities_ULUNDERSCORE_anytimeui_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mIsDTXEnabled = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_enable_ULUNDERSCORE_p2p_ULUNDERSCORE_dtx_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mIsSATranscribeEnabled = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_sa_ULUNDERSCORE_transcribe_ULUNDERSCORE_enabled_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mIsOSUpdaterNonpersistentEnabled = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_osupdater_ULUNDERSCORE_enable_ULUNDERSCORE_nonpersistent_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mIsOSUpdaterTestModeEnabled = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_osupdater_ULUNDERSCORE_enable_ULUNDERSCORE_test_ULUNDERSCORE_mode_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mIsOSUpdaterTrafficTracingEnabled = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_osupdater_ULUNDERSCORE_enable_ULUNDERSCORE_traffic_ULUNDERSCORE_tracing_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mMobileBreakpad = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_mobile_ULUNDERSCORE_breakpad_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableBadDischargeStats = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_batterystats_ULUNDERSCORE_bad_ULUNDERSCORE_sleep_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableLocationService = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_coremobileservices_ULUNDERSCORE_location_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableVrBugReporter = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_vrbugreporter_ULUNDERSCORE_enabled_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableMultiUser = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_mobile_ULUNDERSCORE_multi_ULUNDERSCORE_user_ULUNDERSCORE_enabled_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableChromecasting = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_horizon_ULUNDERSCORE_chromecasting_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableTourGuide = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_tour_ULUNDERSCORE_guide_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableGuardianOnScreenCapture = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_enable_ULUNDERSCORE_guardian_ULUNDERSCORE_on_ULUNDERSCORE_screen_ULUNDERSCORE_capture_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableFlailLockout = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_enable_ULUNDERSCORE_flail_ULUNDERSCORE_lockout_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mHomeShortPressLogging = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_home_ULUNDERSCORE_shortpress_ULUNDERSCORE_logging_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableMrServiceInsightSDK = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_mrservice_ULUNDERSCORE_insightsdk_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mQuestPlayspaceScan = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_quest_ULUNDERSCORE_playspace_ULUNDERSCORE_scan_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mQuestPlayspaceScanHeadless = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_quest_ULUNDERSCORE_playspace_ULUNDERSCORE_scan_ULUNDERSCORE_headless_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mMobileSpaceWarp = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_mobile_ULUNDERSCORE_spacewarp_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mAssistantOnOculus = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_assistant_ULUNDERSCORE_on_ULUNDERSCORE_oculus_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mAssistantOnOculusUseMessageBus = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_assistant_ULUNDERSCORE_oculus_ULUNDERSCORE_use_ULUNDERSCORE_message_ULUNDERSCORE_bus_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mUseVertsPartyVoipGK = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_party_ULUNDERSCORE_voip_ULUNDERSCORE_use_ULUNDERSCORE_verts_ULUNDERSCORE_impl_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableNotificationSoftFollow = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_systemux_ULUNDERSCORE_notifications_ULUNDERSCORE_enable_ULUNDERSCORE_soft_ULUNDERSCORE_follow_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableNotificationDNDAggregation = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_systemux_ULUNDERSCORE_notifications_ULUNDERSCORE_enable_ULUNDERSCORE_dnd_ULUNDERSCORE_count_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableHandTrackingGA = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_mobile_ULUNDERSCORE_hand_ULUNDERSCORE_tracking_ULUNDERSCORE_ga_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableSettingsHandTracking = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_mobile_ULUNDERSCORE_settings_ULUNDERSCORE_hand_ULUNDERSCORE_tracking_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableDoubletapPassthrough = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_quest_ULUNDERSCORE_passthrough_ULUNDERSCORE_ondemand_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableRealityTuner = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_guardian_ULUNDERSCORE_reality_ULUNDERSCORE_tuner_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableIntrusionDetection = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_intrusion_ULUNDERSCORE_detection_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableIntrusionDetectionHeadless = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_intrusion_ULUNDERSCORE_detection_ULUNDERSCORE_headless_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableGuardianInternalAnchor = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_guardian_ULUNDERSCORE_internal_ULUNDERSCORE_anchor_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableDeviceConfigTest = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_deviceconfig_ULUNDERSCORE_test_ULUNDERSCORE_enabled_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mDeviceConfigTest73 = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_deviceconfig_ULUNDERSCORE_test_ULUNDERSCORE_73_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableAppSafetyBinaryCheck = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oc_ULUNDERSCORE_app_ULUNDERSCORE_safety_ULUNDERSCORE_binary_ULUNDERSCORE_check_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableQuestGlanceableBounds = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_quest_ULUNDERSCORE_glanceable_ULUNDERSCORE_bounds_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnablePhoneNotifications = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_mobile_ULUNDERSCORE_enable_ULUNDERSCORE_phone_ULUNDERSCORE_notifications_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableVrShellSysiconsHands = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_vrshell_ULUNDERSCORE_sysicons_ULUNDERSCORE_hands_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableGuardianNonsensitiveTelemetry = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_guardian_ULUNDERSCORE_nonsensitive_ULUNDERSCORE_telemetry_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableVrShellFocusAwarenessPushRollout = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_vrshell_ULUNDERSCORE_focus_ULUNDERSCORE_awareness_ULUNDERSCORE_push_ULUNDERSCORE_rollout_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableVrShellFocusAwarenessForceEnable = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_vrshell_ULUNDERSCORE_focus_ULUNDERSCORE_awareness_ULUNDERSCORE_force_ULUNDERSCORE_enable_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableVrShellIapInOverlayDisable = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_vrshell_ULUNDERSCORE_iap_ULUNDERSCORE_in_ULUNDERSCORE_overlay_ULUNDERSCORE_disable_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableNewNotificationsSound = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_new_ULUNDERSCORE_notifications_ULUNDERSCORE_sound_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableMobileScreenshotShortcut = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_mobile_ULUNDERSCORE_screenshot_ULUNDERSCORE_shortcut_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableChromecastLongerPingInterval = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_chromecast_ULUNDERSCORE_longer_ULUNDERSCORE_ping_ULUNDERSCORE_interval_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableChromecastIncreasePingFailureTolerance = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_chromecast_ULUNDERSCORE_increase_ULUNDERSCORE_ping_ULUNDERSCORE_failure_ULUNDERSCORE_tolerance_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableChromecastRemovePing = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_chromecast_ULUNDERSCORE_remove_ULUNDERSCORE_ping_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableChromecast101ErrorKeepConnected = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_chromecast_ULUNDERSCORE_101_ULUNDERSCORE_error_ULUNDERSCORE_keep_ULUNDERSCORE_connected_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableChromecastInitNsdOnDiscovery = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_chromecast_ULUNDERSCORE_init_ULUNDERSCORE_nsd_ULUNDERSCORE_on_ULUNDERSCORE_discovery_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableChromecastForceH264ForSmartTvs = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_chromecast_ULUNDERSCORE_force_ULUNDERSCORE_h264_ULUNDERSCORE_for_ULUNDERSCORE_smart_ULUNDERSCORE_tvs_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableChromecastH264Override = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_chromecast_ULUNDERSCORE_h264_ULUNDERSCORE_override_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableRetailDemo = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_vrshell_ULUNDERSCORE_core_ULUNDERSCORE_quest_ULUNDERSCORE_retaildemo_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableLinkVrShellModal = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_link_ULUNDERSCORE_vrshell_ULUNDERSCORE_modal2_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableLinkNoModal = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_link_ULUNDERSCORE_no_ULUNDERSCORE_modal_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableConfigurableMtpDialog = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_configurable_ULUNDERSCORE_mtp_ULUNDERSCORE_dialog_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mUseDsatForPartyVoipGK = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_platformsdk_ULUNDERSCORE_parties_ULUNDERSCORE_voip_ULUNDERSCORE_with_ULUNDERSCORE_dsats_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableBugReportService = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_bugreport_ULUNDERSCORE_service_ULUNDERSCORE_enabled_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnablePassthroughQuickboot = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_quest_ULUNDERSCORE_passthrough_ULUNDERSCORE_background_ULUNDERSCORE_quickboot_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableStationaryGuardianV2 = new C01020Iw(5, r2);
        gatekeeperContentProvider.mMobilePointerPose = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_mobile_ULUNDERSCORE_pointer_ULUNDERSCORE_pose_ULUNDERSCORE_filter_ULUNDERSCORE_tuning_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableHomeButtonFreezeInput = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_mobile_ULUNDERSCORE_homebutton_ULUNDERSCORE_freezeinput_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableHomeButtonFreezeInputApiCheck = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_mobile_ULUNDERSCORE_homebutton_ULUNDERSCORE_freezeinput_ULUNDERSCORE_apichk_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableHomeButtonEatTriggerSpotFix = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_mobile_ULUNDERSCORE_homebutton_ULUNDERSCORE_eattrigger_ULUNDERSCORE_spotfix_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mDisableTrexRuntimeClient = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_trex_ULUNDERSCORE_runtimeclient_ULUNDERSCORE_killsw_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mDisableTrexRuntimeService = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_trex_ULUNDERSCORE_runtimeservice_ULUNDERSCORE_killsw_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mDisableTrexFitnessTracker = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_trex_ULUNDERSCORE_fitnesstracker_ULUNDERSCORE_killsw_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mDisableTrexBodyApi = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_trex_ULUNDERSCORE_bodyapi_ULUNDERSCORE_killsw_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mDisableTrexSpatialPersistence = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_trex_ULUNDERSCORE_spatialpersistence_ULUNDERSCORE_killsw_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mDisableTrexSuperRes = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_trex_ULUNDERSCORE_superres_ULUNDERSCORE_killsw_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableMrDeskCreation = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_mobile_ULUNDERSCORE_guardian_ULUNDERSCORE_mr_ULUNDERSCORE_desk_ULUNDERSCORE_gk_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableHandTrackingFrequencyInternal = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_mobile_ULUNDERSCORE_enable_ULUNDERSCORE_hand_ULUNDERSCORE_tracking_ULUNDERSCORE_frequency_ULUNDERSCORE_inter_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableSmallFingerOpeningPinchThreshold = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_mobile_ULUNDERSCORE_enable_ULUNDERSCORE_small_ULUNDERSCORE_finger_ULUNDERSCORE_opening_ULUNDERSCORE_pinch_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableControllerLedControl = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_mobile_ULUNDERSCORE_enable_ULUNDERSCORE_controller_ULUNDERSCORE_led_ULUNDERSCORE_control_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableSpatialAnchor = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_mobile_ULUNDERSCORE_guardian_ULUNDERSCORE_spatial_ULUNDERSCORE_anchor_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableBatteryDrainService = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_battery_ULUNDERSCORE_drain_ULUNDERSCORE_service_ULUNDERSCORE_enabled_ULSEP_ACCESS_METHOD(r2);
        gatekeeperContentProvider.mEnableOculusMediaSharedMic = OVRModule._UL__ULSEP_javax_inject_Provider_ULLT_java_lang_Boolean_ULGT__ULSEP_com_google_inject_name_Named_ULUNDERSCORE_oculus_ULUNDERSCORE_media_ULUNDERSCORE_shared_ULUNDERSCORE_mic_ULSEP_ACCESS_METHOD(r2);
    }

    @Override // X.AbstractC09361bk
    public int doDelete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // X.AbstractC09361bk
    @Nullable
    public String doGetType(Uri uri) {
        return null;
    }

    @Override // X.AbstractC09361bk
    @Nullable
    public Uri doInsert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // X.AbstractC09361bk
    public int doUpdate(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    private boolean isSystemUtilitiesRequiredMinVersionsInstalled() {
        PackageInfo A00;
        int i;
        int i2;
        for (String str : SYSTEM_UTILITIES_REQUIRED_MIN_VERSIONS.keySet()) {
            PackageManagerUtils packageManagerUtils = (PackageManagerUtils) AnonymousClass0J2.A03(1, 388, this._UL_mInjectionContext);
            synchronized (packageManagerUtils) {
                A00 = PackageManagerUtils.A00(packageManagerUtils, str);
            }
            String str2 = SYSTEM_UTILITIES_REQUIRED_MIN_VERSIONS.get(str);
            if (A00 == null || str2 == null) {
                AnonymousClass0NO.A0E(TAG, "SysUtils package version lookup failed for %s", str);
            } else {
                String[] split = A00.versionName.split("-")[0].split("\\.");
                String[] split2 = str2.split("\\.");
                int length = split.length;
                int length2 = split2.length;
                int max = Math.max(length, length2);
                for (int i3 = 0; i3 < max; i3++) {
                    if (i3 < length) {
                        try {
                            i = Integer.parseInt(split[i3]);
                        } catch (NumberFormatException unused) {
                        }
                    } else {
                        i = 0;
                    }
                    if (i3 < length2) {
                        i2 = Integer.parseInt(split2[i3]);
                    } else {
                        i2 = 0;
                    }
                    if (i >= i2) {
                        if (i > i2) {
                            break;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public static final void _UL_injectMe(Context context, GatekeeperContentProvider gatekeeperContentProvider) {
        _UL_staticInjectMe((AbstractC06640p5) AnonymousClass0J2.get(context), gatekeeperContentProvider);
    }

    @Override // com.oculus.content.OculusPublicContentProvider
    public void doInitialization() {
        _UL_injectMe(getContext(), this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:496:0x07be, code lost:
        if (r0 != false) goto L_0x07c1;
     */
    @Override // X.AbstractC09361bk
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.database.Cursor doQuery(android.net.Uri r8, java.lang.String[] r9, java.lang.String r10, java.lang.String[] r11, java.lang.String r12) {
        /*
        // Method dump skipped, instructions count: 2506
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.provider.GatekeeperContentProvider.doQuery(android.net.Uri, java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):android.database.Cursor");
    }
}
