package com.oculus.horizon.logging;

import android.net.Uri;
import com.facebook.common.string.StringUtil;
import com.facebook.inject.ApplicationScopeClassInit;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightSingletonProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.UL;
import com.oculus.horizon.logging.LoggingKeys;
import com.oculus.horizon.logging.LoggingModule;
import com.oculus.horizon.logging.TrackingViews;
import com.oculus.horizon.logging.contract.FunnelContract;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.logging.utils.UtilsModule;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Provider;

@Deprecated
@Dependencies({"_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID"})
@ApplicationScoped
public class OculusLogger {
    private static volatile OculusLogger _UL__ULSEP_com_oculus_horizon_logging_OculusLogger_ULSEP_INSTANCE;
    private InjectionContext _UL_mInjectionContext;

    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_horizon_logging_OculusLogger_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(LoggingModule.UL_id._UL__ULSEP_com_oculus_horizon_logging_OculusLogger_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_horizon_logging_OculusLogger_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightSingletonProvider.get(LoggingModule.UL_id._UL__ULSEP_com_oculus_horizon_logging_OculusLogger_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final OculusLogger _UL__ULSEP_com_oculus_horizon_logging_OculusLogger_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (OculusLogger) UL.factorymap.get(LoggingModule.UL_id._UL__ULSEP_com_oculus_horizon_logging_OculusLogger_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final OculusLogger _UL__ULSEP_com_oculus_horizon_logging_OculusLogger_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        if (_UL__ULSEP_com_oculus_horizon_logging_OculusLogger_ULSEP_INSTANCE == null) {
            synchronized (OculusLogger.class) {
                ApplicationScopeClassInit start = ApplicationScopeClassInit.start(_UL__ULSEP_com_oculus_horizon_logging_OculusLogger_ULSEP_INSTANCE, injectorLike);
                if (start != null) {
                    try {
                        _UL__ULSEP_com_oculus_horizon_logging_OculusLogger_ULSEP_INSTANCE = new OculusLogger(injectorLike.getApplicationInjector());
                    } finally {
                        start.finish();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_horizon_logging_OculusLogger_ULSEP_INSTANCE;
    }

    @Inject
    public OculusLogger(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(1, injectorLike);
    }

    private Event newViewEvent() {
        return ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.VIEW);
    }

    private void reportViewWithFunnelAction(String str, Event event) {
        ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).reportFunnelAction(FunnelContract.NAV_TO_VR_FUNNEL_NAME, str, null, null);
        event.addExtra("event_value", str).logAndRelease();
    }

    public void reportSectionView(String str) {
        reportSectionView(str, null);
    }

    public void reportSectionView(String str, String str2) {
        Event addExtra = newViewEvent().addExtra(LoggingKeys.CATEGORY_ID, str);
        if (str2 != null) {
            addExtra.addExtra(LoggingKeys.CRITERIA_ID, str2);
        }
        reportViewWithFunnelAction(LoggingValues.SECTION, addExtra);
    }

    public void reportDeepLinkTap(Uri uri) {
        Event addExtra = ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.DEEP_LINK).addExtra("uri", uri.toString()).addExtra("path", uri.getPath());
        for (String str : uri.getQueryParameterNames()) {
            List<String> queryParameters = uri.getQueryParameters(str);
            if (queryParameters.size() == 1) {
                addExtra.addExtra(LoggingKeys.PARAM_PREFIX + str, queryParameters.get(0));
            } else {
                addExtra.addExtra(LoggingKeys.PARAM_PREFIX + str, queryParameters.toString());
            }
        }
        String queryParameter = uri.getQueryParameter("item_id");
        if (queryParameter != null) {
            addExtra.addExtra("item_id", queryParameter);
        }
        String queryParameter2 = uri.getQueryParameter("referrer");
        if (queryParameter2 != null) {
            addExtra.addExtra("referrer", queryParameter2);
        }
        String queryParameter3 = uri.getQueryParameter("source");
        if (queryParameter3 != null) {
            addExtra.addExtra("source", queryParameter3);
        }
        String queryParameter4 = uri.getQueryParameter(LoggingKeys.REFERRER_MEDIUM);
        if (queryParameter4 != null) {
            addExtra.addExtra(LoggingKeys.REFERRER_MEDIUM, queryParameter4);
        }
        String queryParameter5 = uri.getQueryParameter(LoggingKeys.REFERRER_CAMPAIGN);
        if (queryParameter5 != null) {
            addExtra.addExtra(LoggingKeys.REFERRER_CAMPAIGN, queryParameter5);
        }
        String queryParameter6 = uri.getQueryParameter(LoggingKeys.REFERRER_TERM);
        if (queryParameter6 != null) {
            addExtra.addExtra(LoggingKeys.REFERRER_TERM, queryParameter6);
        }
        String queryParameter7 = uri.getQueryParameter("content");
        if (queryParameter7 != null) {
            addExtra.addExtra("content", queryParameter7);
        }
        addExtra.logAndRelease();
    }

    public void reportSearchEnter() {
        reportViewWithFunnelAction(LoggingEvents.SEARCH_ENTER, newViewEvent());
    }

    public void reportSearchQuery(String str) {
        Event createEvent = ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.SEARCH_QUERY);
        createEvent.addExtra(LoggingKeys.SEARCH_QUERY, str);
        createEvent.logAndRelease();
    }

    public void reportTileClick(TrackingViews.View view, String str, String str2) {
        Event createEvent = ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.TILE_CLICK);
        createEvent.addExtra("item_id", str);
        createEvent.addExtra("source", view.toString());
        createEvent.addExtra(LoggingKeys.SEARCH_QUERY, str2);
        createEvent.logAndRelease();
    }

    public void reportSearchExit() {
        ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.SEARCH_EXIT).logAndRelease();
    }

    public void reportLogoutEvent() {
        ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.LOGOUT).logAndRelease();
    }

    public void reportLaunchSuccess(String str) {
        ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.LAUNCH).addExtra("event_value", str).logAndRelease();
    }

    public void reportCoreAppsStart(String str, String[] strArr, String[] strArr2, String str2) {
        reportCoreAppsEvent(LoggingEvents.CORE_APP_START, str, strArr, strArr2, str2);
    }

    public void reportCoreAppsEnd(String str, String[] strArr, String[] strArr2, String str2) {
        reportCoreAppsEvent(LoggingEvents.CORE_APP_END, str, strArr, strArr2, str2);
    }

    private void reportCoreAppsEvent(String str, String str2, String[] strArr, String[] strArr2, String str3) {
        for (int i = 0; i < strArr.length; i++) {
            ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(str).appendStorageInformation().addExtra("event_value", strArr[i]).addExtra(LoggingKeys.ITEM_STATUS, strArr2[i]).addExtra(LoggingKeys.INSTALL_ID, str2).addExtra("source", str3).logAndRelease();
        }
    }

    public void reportCoreAppDownloadSuccess(String str, String str2, int i, String str3) {
        ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.CORE_APP_DOWNLOAD_SUCCESS).appendStorageInformation().addExtra("event_value", str2).addExtra(LoggingKeys.INSTALL_ID, str).addExtra(LoggingKeys.APP_VERSION_CODE, i).addExtra("source", str3).logAndRelease();
    }

    public void reportCoreAppDownloadFailure(String str, String str2, int i, String str3, int i2) {
        ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.CORE_APP_DOWNLOAD_FAILURE).appendStorageInformation().addExtra("event_value", str2).addExtra(LoggingKeys.INSTALL_ID, str).addExtra(LoggingKeys.APP_VERSION_CODE, i).addExtra("source", str3).addExtra("error_code", i2).logAndRelease();
    }

    public void reportCoreAppInstallSuccess(String str, String str2, int i, String str3) {
        ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.CORE_APP_INSTALL_SUCCESS).appendStorageInformation().addExtra("event_value", str2).addExtra(LoggingKeys.INSTALL_ID, str).addExtra(LoggingKeys.APP_VERSION_CODE, i).addExtra("source", str3).logAndRelease();
    }

    public void reportCoreAppInstallFailure(String str, String str2, int i, String str3, String str4) {
        ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.CORE_APP_INSTALL_FAILURE).appendStorageInformation().addExtra("event_value", str2).addExtra(LoggingKeys.INSTALL_ID, str).addExtra(LoggingKeys.APP_VERSION_CODE, i).addExtra("source", str3).addExtra("error_code", str4).logAndRelease();
    }

    public void reportLiveStreaminSessionJoinInVR() {
        ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.LIVE_STREAMING_SESSION_JOIN_IN_VR).logAndRelease();
    }

    public Event getNotificationsPreferenceChangeEvent(String str, String str2, boolean z) {
        return ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.NOTIFICATIONS_PREFERENCE_CHANGE).addExtra(LoggingKeys.NOTIFICATION_MEDIUM, str).addExtra(LoggingKeys.NOTIFICATION_PREFERENCE_CHANGE_LOCATION, str2).addExtra(LoggingKeys.OPT_OUT, z);
    }

    public void reportNotificationsPreferenceChangeFromPartyCallSettings(String str, String str2, boolean z) {
        getNotificationsPreferenceChangeEvent(str, "party call settings", z).addExtra(LoggingKeys.NOTIFICATION_PREFERENCE_TYPE, str2).logAndRelease();
    }

    public void reportAppManagerForceSyncSuccess(String str, long j, boolean z) {
        ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.APPMANAGER_FORCE_SYNC_SUCCESS).addExtra(LoggingKeys.FORCE_SYNC_REQUEST_ID, str).addExtra(LoggingKeys.FORCE_SYNC_EXECUTION_DELAY, j).addExtra(LoggingKeys.FORCE_SYNC_WAKEUP_DEVICE, z).logAndRelease();
    }

    public void reportAppManagerForceSyncFailure(String str) {
        ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.APPMANAGER_FORCE_SYNC_FAILURE).addExtra("reason", str).logAndRelease();
    }

    private Event newFacebookReauthEvent(boolean z, boolean z2, boolean z3) {
        return ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.FACEBOOK_REAUTH).addExtra(LoggingKeys.FACEBOOK_REAUTH_KEYS.IS_VALID_BEFORE_REAUTH, z).addExtra(LoggingKeys.FACEBOOK_REAUTH_KEYS.IS_VALID_AFTER_REAUTH, z2).addExtra(LoggingKeys.FACEBOOK_REAUTH_KEYS.IS_REFRESHED, z3);
    }

    public void reportFacebookReauthSuccess(boolean z, boolean z2) {
        newFacebookReauthEvent(z, true, z2).logAndRelease();
    }

    public void reportFacebookReauthError(boolean z, @Nullable String str, @Nullable String str2, @Nullable String str3) {
        newFacebookReauthEvent(z, false, false).addExtra("error_code", str).addExtra("error_subcode", str2).addExtra("error_message", str3).logAndRelease();
    }

    public void reportAppStart2D(String str, String str2, int i) {
        ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.APP_START_2D).addExtra(LoggingKeys.APP_PACKAGE_NAME, str).addExtra(LoggingKeys.APP_VERSION_NAME, str2).addExtra(LoggingKeys.APP_VERSION_CODE, i).logAndRelease();
    }

    public void reportAppEnd2D(String str, String str2, int i, long j) {
        ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.APP_END_2D).addExtra(LoggingKeys.APP_PACKAGE_NAME, str).addExtra(LoggingKeys.APP_VERSION_NAME, str2).addExtra(LoggingKeys.APP_VERSION_CODE, i).addExtra(LoggingKeys.ACTIVE_DURATION_MS, j).logAndRelease();
    }

    public static class EventLinkToPdpModal {

        public enum Source {
            VIA_JOIN_NOW,
            VIA_SUBSCRIBE
        }

        public static class Data {
            public final String mEventId;
            public final boolean mIsSubscribed;
            public final Source mSource;

            public Data(String str, Source source, boolean z) {
                this.mEventId = str;
                this.mSource = source;
                this.mIsSubscribed = z;
            }
        }
    }

    public void reportStartRecording(String str, String str2) {
        if (str == null) {
            str = "";
        }
        ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.ABUSE_RECORDING_START).addExtra(LoggingKeys.RECORDING_UUID, str).addExtra("error", str2).logAndRelease();
    }

    public void reportCancelRecording(String str, String str2, boolean z, String str3) {
        if (str == null) {
            str = "";
        }
        ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.ABUSE_RECORDING_CANCEL).addExtra(LoggingKeys.RECORDING_UUID, str).addExtra("source", str2).addExtra(LoggingKeys.SUCCESS, z).addExtra("error", str3).logAndRelease();
    }

    public void reportStopRecording(String str, String str2, boolean z, String str3) {
        if (str == null) {
            str = "";
        }
        ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.ABUSE_RECORDING_STOP).addExtra(LoggingKeys.RECORDING_UUID, str).addExtra("source", str2).addExtra(LoggingKeys.SUCCESS, z).addExtra("error", str3).logAndRelease();
    }

    public VideoUploaderLogger newAbusePreventionLogger(String str, String str2) {
        return new VideoUploaderLogger(str, str2);
    }

    public class VideoUploaderLogger {
        @Nullable
        public Long audioInputFileSize = null;
        @Nullable
        public Long muxedVideoFileSize = null;
        public final String recordingUUID;
        public final String reportID;
        @Nullable
        public String uploadSessionID = null;
        @Nullable
        public Long videoInputFileSize = null;

        public VideoUploaderLogger(String str, String str2) {
            this.recordingUUID = str;
            this.reportID = str2;
        }

        private void log(Event event) {
            event.addExtra(LoggingKeys.RECORDING_UUID, this.recordingUUID).addExtra(LoggingKeys.REPORT_ID, this.reportID);
            Long l = this.videoInputFileSize;
            if (l != null) {
                event.addExtra(LoggingKeys.VIDEO_INPUT_FILE_SIZE, l.longValue());
            }
            Long l2 = this.audioInputFileSize;
            if (l2 != null) {
                event.addExtra(LoggingKeys.AUDIO_INPUT_FILE_SIZE, l2.longValue());
            }
            Long l3 = this.muxedVideoFileSize;
            if (l3 != null) {
                event.addExtra(LoggingKeys.MUXED_VIDEO_FILE_SIZE, l3.longValue());
            }
            String str = this.uploadSessionID;
            if (str != null) {
                event.addExtra(LoggingKeys.UPLOAD_SESSION_ID, str);
            }
            event.logAndRelease();
        }

        public void reportVideoUploadStart() {
            log(((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, OculusLogger.this._UL_mInjectionContext)).createEvent(LoggingEvents.ABUSE_VIDEO_UPLOAD_START));
        }

        public void reportVideoUploadFailure(Exception exc) {
            log(((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, OculusLogger.this._UL_mInjectionContext)).createEvent(LoggingEvents.ABUSE_VIDEO_UPLOAD_FAILURE).addExtra("reason", getStackTraceString(exc)));
        }

        public void reportVideoUploadSuccess() {
            log(((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, OculusLogger.this._UL_mInjectionContext)).createEvent(LoggingEvents.ABUSE_VIDEO_UPLOAD_SUCCESS));
        }

        private String getStackTraceString(Throwable th) {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            th.printStackTrace(printWriter);
            printWriter.flush();
            return stringWriter.toString();
        }

        public void reportVideoUploadRetry(Exception exc, String str, int i) {
            log(((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, OculusLogger.this._UL_mInjectionContext)).createEvent(LoggingEvents.ABUSE_VIDEO_UPLOAD_RETRY).addExtra("reason", getStackTraceString(exc)).addExtra(LoggingKeys.RETRY_NUMBER, i).addExtra("method", str));
        }
    }

    public void reportPlatformPluginUnload(long j) {
        ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.PLATFORMPLUGIN_UNLOAD_NATIVE).addExtra(LoggingKeys.TEARDOWN_INTERVAL_MS, j).logAndRelease();
    }

    public void reportStandaloneDefaultAppSetupResult(boolean z, List<String> list, List<String> list2, List<String> list3, List<String> list4, List<String> list5) {
        ((EventManager) FbInjector.lazyInstance(0, UtilsModule.UL_id._UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID, this._UL_mInjectionContext)).createEvent(LoggingEvents.STANDALONE_DEFAULT_APP_SETUP_RESULT).addExtra(LoggingKeys.StandaloneSetupAppInstall.HIGH_PRI_APPS_SETUP, z).addExtra("install_success", StringUtil.join(",", list)).addExtra(LoggingKeys.StandaloneSetupAppInstall.NUM_SUCCESS, list.size()).addExtra("download_failure", StringUtil.join(",", list2)).addExtra(LoggingKeys.StandaloneSetupAppInstall.NUM_DOWNLOAD_FAILURE, list2.size()).addExtra("install_failure", StringUtil.join(",", list3)).addExtra(LoggingKeys.StandaloneSetupAppInstall.NUM_INSTALL_FAILURE, list3.size()).addExtra(LoggingKeys.StandaloneSetupAppInstall.IGNORED, StringUtil.join(",", list4)).addExtra(LoggingKeys.StandaloneSetupAppInstall.NUM_IGNORED, list4.size()).addExtra("unknown", StringUtil.join(",", list5)).addExtra(LoggingKeys.StandaloneSetupAppInstall.NUM_UNKNOWN, list5.size()).logAndRelease();
    }
}
