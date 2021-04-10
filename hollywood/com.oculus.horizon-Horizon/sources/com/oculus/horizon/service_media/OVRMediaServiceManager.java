package com.oculus.horizon.service_media;

import X.AbstractC06640p5;
import X.AnonymousClass006;
import X.AnonymousClass04J;
import X.AnonymousClass0DC;
import X.AnonymousClass0J2;
import X.AnonymousClass0JA;
import X.AnonymousClass0NO;
import X.AnonymousClass0QC;
import X.AnonymousClass0Rg;
import X.AnonymousClass0b9;
import X.AnonymousClass117;
import X.C003108z;
import X.C02600ao;
import X.C02780bN;
import X.C02880bg;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Pair;
import android.view.Surface;
import androidx.annotation.VisibleForTesting;
import com.facebook.AccessToken;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.inject.Assisted;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.facebook.ultralight.Inject;
import com.google.common.base.Strings;
import com.oculus.device.DeviceType;
import com.oculus.horizon.R;
import com.oculus.horizon.abuse_prevention.AbuseAVCapture;
import com.oculus.horizon.abuse_prevention.AbuseReportFileUtils;
import com.oculus.horizon.api.ApiRequestManager;
import com.oculus.horizon.api.common.WebRTCHandshake;
import com.oculus.horizon.api.media.GetCastAnswerRequest;
import com.oculus.horizon.api.media.GetCastAnswerResponse;
import com.oculus.horizon.api.media.SetCastOfferRequest;
import com.oculus.horizon.api.media.SetCastOfferResponse;
import com.oculus.horizon.cast.CastAnalytics;
import com.oculus.horizon.cast.CastHTTPServerBase;
import com.oculus.horizon.cast.CastHTTPServerForWeb;
import com.oculus.horizon.cast.CastHTTPServerManager;
import com.oculus.horizon.cast.CastStopSource;
import com.oculus.horizon.cast.Message;
import com.oculus.horizon.cast.VideoSpec;
import com.oculus.horizon.fbconnect.FBConnectHelper;
import com.oculus.horizon.media_session.MediaSessionCallback;
import com.oculus.horizon.platform.PresenceManager;
import com.oculus.horizon.platformplugin.PlatformPluginManager;
import com.oculus.horizon.profile.UserProfileHelper;
import com.oculus.horizon.service.ExternalPlatformLocal;
import com.oculus.horizon.service_media.FileCapture;
import com.oculus.horizon.service_media.contract.OVRMediaServiceContract;
import com.oculus.horizon.service_media.screenshot.ScreenshotManager;
import com.oculus.horizon.service_media.vrcast.VrCastManager;
import com.oculus.horizon.util.permissions.PermissionRequest;
import com.oculus.http.core.base.ApiCallback;
import com.oculus.http.core.base.ApiError;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.mediaupload.api.MediaUploaderMetadataHelper;
import com.oculus.mediaupload.io.FileUtils;
import com.oculus.mediaupload.io.MediaUploaderDB;
import com.oculus.mediaupload.model.MediaMetadata;
import com.oculus.os.SettingsManager;
import com.oculus.security.basecomponent.OculusPublicBroadcastReceiver;
import com.oculus.socialplatform.util.SocialPlatformVersionUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONException;
import org.json.JSONObject;

@Dependencies({"_UL__ULSEP_com_oculus_horizon_service_ULUNDERSCORE_media_vrcast_VrCastManager_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_profile_UserProfileHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_service_ExternalPlatformLocal_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_platformplugin_PlatformPluginManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_service_ULUNDERSCORE_media_OVRLivestreamingErrorUtility_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_platform_PresenceManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_utils_EventManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_abuse_ULUNDERSCORE_prevention_AbuseAVCapture_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_service_ULUNDERSCORE_media_FileCapture_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_media_ULUNDERSCORE_session_MediaSessionLifecycleManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_fbconnect_FBConnectHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_api_ApiRequestManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_service_ULUNDERSCORE_media_SurfaceCapture_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_service_ULUNDERSCORE_media_OVRMediaServiceNotification_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_service_ULUNDERSCORE_media_ForegroundAppChecker_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_service_ULUNDERSCORE_media_WebRTCCapture_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_cast_CastHTTPServerManagerProvider_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_service_ULUNDERSCORE_media_screenshot_ScreenshotManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_mediaupload_api_MediaUploaderMetadataHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_service_ULUNDERSCORE_media_InstantReplayHelper_ULSEP_BINDING_ID"})
public class OVRMediaServiceManager implements MediaSessionCallback, PlatformPluginManager.Client, VrCastManager.ErrorCallback {
    public static final int CAST_WWW_ANSWER_POLL_INTERVAL_MS = 1000;
    public static final int CAST_WWW_ANSWER_POLL_MAX_ATTEMPT = 20;
    public static final String CONTROL_CAST_FBPERMISSION = "com.oculus.fbpermission.CONTROL_CAST";
    public static final double DEFAULT_SCREENSHOT_DELAY = 5.0d;
    public static final int DEFAULT_SCREENSHOT_HEIGHT = 1024;
    public static final int DEFAULT_SCREENSHOT_WIDTH = 1024;
    public static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
    public static final String LIVESTREAMING_STATUS_CHANGE = "com.oculus.livestreaming_status_change";
    public static final String LS_STATUS_COMMENTS_VISIBLE_INTENT_KEY = "comments_visible";
    public static final String LS_STATUS_IS_PAUSED_INTENT_KEY = "is_paused";
    public static final String LS_STATUS_LIVESTREAMING_ENABLED_INTENT_KEY = "livestreaming_enabled";
    public static final String LS_STATUS_LIVESTREAMING_TYPE_INTENT_KEY = "livestreaming_type";
    public static final String LS_STATUS_MIC_ENABLED_INTENT_KEY = "mic_enabled";
    public static final Set<String> PRIVILEGED_COMMANDS = new HashSet(Arrays.asList(OVRMediaServiceContract.START_FACEBOOK_STREAMING, OVRMediaServiceContract.START_PARTY_VIDEO_STREAMING, OVRMediaServiceContract.STOP_FACEBOOK_STREAMING, OVRMediaServiceContract.STOP_PARTY_VIDEO_STREAMING, OVRMediaServiceContract.UPDATE_LIVESTREAMING_MICROPHONE, "permission_status", OVRMediaServiceContract.GET_IS_LIVESTREAMING_ENABLED, OVRMediaServiceContract.UPDATE_LIVESTREAMING_COMMENTS_VISIBILITY));
    public static final double SCREENSHOT_SHORTCUT_DELAY = 0.0d;
    public static final long SWITCH_SOURCE_DELAY = 200;
    public static final String TAG = "OVRMediaServiceManager";
    public static final String VRCAMERA_SURFACE_CHANGE = "com.oculus.vrcamera_surface_change";
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    @Eager
    public final AbuseAVCapture mAbuseAVCapture;
    @Nullable
    public AbuseRecordingOverlayManager mAbuseRecordingOverlayManager;
    @Inject
    @Eager
    public final ApiRequestManager mApiRequestManager;
    public final CastBroadcastReceiver mCaptureReceiver = new CastBroadcastReceiver();
    public final Handler mCastEventHandler;
    public final CastHTTPServerManager mCastHTTPServerManager;
    @Nullable
    public String mCastSessionHandshakeId;
    public CastToBrowserStatus mCastToBrowserStatus = CastToBrowserStatus.NOT_INITIATED;
    @Nullable
    public Handler mCastWWWAnswerPoller;
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;
    public VideoCaptureStatus mDefaultCaptureStatus = VideoCaptureStatus.OFF;
    @Inject
    @Eager
    public final ExternalPlatformLocal mExternalPlatformLocal;
    @Inject
    @Eager
    public final FBConnectHelper mFBConnectHelper;
    @Inject
    @Eager
    public final FileCapture mFileCapture;
    @Inject
    @Eager
    public final ForegroundAppChecker mForegroundAppChecker;
    public final ForegroundServiceInterface mForegroundService;
    public boolean mInitialized;
    public final AtomicBoolean mIsDataChannelEnabled = new AtomicBoolean(false);
    public boolean mIsSocialPlatformPartyInfraValid;
    public final AtomicBoolean mIsTwilightDataChannelReady = new AtomicBoolean(false);
    @Inject
    @Eager
    public final OVRLivestreamingErrorUtility mLivestreamingErrorUtility;
    @Nullable
    public AbstractLiveStreamingManager mLivestreamingManager;
    public final HandlerThread mNetworkHandlerThreader;
    @Inject
    @Eager
    public final OVRMediaServiceNotification mOVRMediaServiceNotification;
    @Nullable
    public String mPackageName;
    @Inject
    @Eager
    public final PlatformPluginManager mPlatformPluginManager;
    public int mPollCounter = 0;
    @Inject
    @Eager
    public final PresenceManager mPresenceManager;
    public final ScreenshotManager.ScreenshotListener mScreenshotListener = new ScreenshotManager.ScreenshotListener() {
        /* class com.oculus.horizon.service_media.OVRMediaServiceManager.AnonymousClass1 */

        @Override // com.oculus.horizon.service_media.screenshot.ScreenshotManager.ScreenshotListener
        public final void A5s(File file, boolean z) {
            OVRMediaServiceManager.this.A0X(file, false, z);
        }
    };
    @Inject
    @Eager
    public final SurfaceCapture mSurfaceCapture;
    @Inject
    @Eager
    public final UserProfileHelper mUserProfileHelper;
    @Nullable
    public String mVideoCaptureTargetPackage;
    @Nullable
    public VrCastManager mVrCastManager;
    @Inject
    @Eager
    public final WebRTCCapture mWebRTCCapture;

    public enum CaptureErrorType {
        STOPPED_BY_ANOTHER_CAPTURE,
        GENERIC
    }

    public class CastBroadcastReceiver extends OculusPublicBroadcastReceiver {
        public CastBroadcastReceiver() {
            super(OVRMediaServiceContract.GET_CAPTURE_STATE_ACTION, OVRMediaServiceContract.START_CAST_SERVER, OVRMediaServiceContract.STOP_CAST_SERVER);
        }

        @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
        public final void onReceive(Context context, Intent intent, AnonymousClass0b9 r6) {
            String action = intent.getAction();
            if (action == null) {
                AnonymousClass0NO.A08(OVRMediaServiceManager.TAG, "Capture receiver got null action!");
            } else if (action.equals(OVRMediaServiceContract.GET_CAPTURE_STATE_ACTION)) {
                OVRMediaServiceManager oVRMediaServiceManager = OVRMediaServiceManager.this;
                oVRMediaServiceManager.mFileCapture.A02();
                OVRMediaServiceManager.A04(oVRMediaServiceManager);
            } else if (action.equals(OVRMediaServiceContract.START_CAST_SERVER)) {
                OVRMediaServiceManager.this.A0T(intent, CastHTTPServerBase.StartSource.BROADCAST, false);
            } else if (action.equals(OVRMediaServiceContract.STOP_CAST_SERVER)) {
                OVRMediaServiceManager.this.A0W(CastStopSource.BROADCAST);
            }
        }
    }

    public enum CastToBrowserStatus {
        NOT_INITIATED,
        INITIATED,
        POLLING_WWW_ANSWER,
        TIME_OUT,
        CASTING_TO_BROWSER
    }

    public static class DataChannelProtocolIds {
        public static final int HELLO = 1;
    }

    public static class DataChannelProtocolKeys {
        public static final String ID = "id";
        public static final String PAYLOAD = "payload";
    }

    public enum VideoCaptureStatus {
        OFF,
        ON,
        WAITING
    }

    /* JADX WARNING: Removed duplicated region for block: B:43:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0147  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final synchronized android.os.Bundle A00(com.oculus.horizon.service_media.OVRMediaServiceManager r9, boolean r10, boolean r11) {
        /*
        // Method dump skipped, instructions count: 451
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.service_media.OVRMediaServiceManager.A00(com.oculus.horizon.service_media.OVRMediaServiceManager, boolean, boolean):android.os.Bundle");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        if (r2.mIsLivestreaming == false) goto L_0x0019;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void A06(com.oculus.horizon.service_media.OVRMediaServiceManager r5) {
        /*
            monitor-enter(r5)
            java.lang.String r0 = "com.oculus.systemactivities.LOCAL_STREAM_STATE_UPDATE"
            android.content.Intent r4 = new android.content.Intent     // Catch:{ all -> 0x0033 }
            r4.<init>(r0)     // Catch:{ all -> 0x0033 }
            java.lang.String r3 = "is_capture_running"
            com.oculus.horizon.service_media.AbstractLiveStreamingManager r2 = r5.mLivestreamingManager     // Catch:{ all -> 0x0033 }
            if (r2 == 0) goto L_0x0019
            com.oculus.horizon.service_media.AbstractLiveStreamingManager$StreamingType r1 = r2.mStreamingType     // Catch:{ all -> 0x0033 }
            com.oculus.horizon.service_media.AbstractLiveStreamingManager$StreamingType r0 = com.oculus.horizon.service_media.AbstractLiveStreamingManager.StreamingType.CAST     // Catch:{ all -> 0x0033 }
            if (r1 != r0) goto L_0x0019
            boolean r1 = r2.mIsLivestreaming     // Catch:{ all -> 0x0033 }
            r0 = 1
            if (r1 != 0) goto L_0x001a
        L_0x0019:
            r0 = 0
        L_0x001a:
            r4.putExtra(r3, r0)     // Catch:{ all -> 0x0033 }
            java.lang.String r0 = "com.oculus.vrshell"
            r4.setPackage(r0)     // Catch:{ all -> 0x0033 }
            android.content.Context r0 = r5.mContext     // Catch:{ all -> 0x0033 }
            r0.sendBroadcast(r4)     // Catch:{ all -> 0x0033 }
            java.lang.String r0 = "com.oculus.systemux"
            r4.setPackage(r0)     // Catch:{ all -> 0x0033 }
            android.content.Context r0 = r5.mContext     // Catch:{ all -> 0x0033 }
            r0.sendBroadcast(r4)     // Catch:{ all -> 0x0033 }
            monitor-exit(r5)
            return
        L_0x0033:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.service_media.OVRMediaServiceManager.A06(com.oculus.horizon.service_media.OVRMediaServiceManager):void");
    }

    public static synchronized void A08(OVRMediaServiceManager oVRMediaServiceManager, CaptureErrorType captureErrorType) {
        String string;
        synchronized (oVRMediaServiceManager) {
            if (captureErrorType.ordinal() != 0) {
                string = oVRMediaServiceManager.mContext.getString(R.string.streaming_error_notification_message);
            } else {
                string = oVRMediaServiceManager.mContext.getString(R.string.stopped_by_another_capture_notification_message);
            }
            OVRMediaServiceNotification oVRMediaServiceNotification = oVRMediaServiceManager.mOVRMediaServiceNotification;
            OVRMediaServiceNotification.A03(oVRMediaServiceNotification, oVRMediaServiceNotification.mContext.getString(R.string.streaming_error_notification_title), string);
            oVRMediaServiceManager.A02();
        }
    }

    public static synchronized void A09(OVRMediaServiceManager oVRMediaServiceManager, String str) {
        synchronized (oVRMediaServiceManager) {
            if (oVRMediaServiceManager.A0c()) {
                oVRMediaServiceManager.mLivestreamingManager.mIsPaused = true;
                OVRMediaServiceNotification oVRMediaServiceNotification = oVRMediaServiceManager.mOVRMediaServiceNotification;
                String str2 = oVRMediaServiceManager.mPackageName;
                if (str2 != null && !oVRMediaServiceNotification.mBlacklistLivestreamingPackages.contains(str2)) {
                    OVRMediaServiceNotification.A02(oVRMediaServiceNotification, oVRMediaServiceNotification.mContext.getString(R.string.livestreaming_unsupported_application_title), oVRMediaServiceNotification.mContext.getString(R.string.livestreaming_unsupported_application_message));
                }
                oVRMediaServiceManager.mWebRTCCapture.A00(str, null);
            }
        }
    }

    public static synchronized void A0A(OVRMediaServiceManager oVRMediaServiceManager, String str) {
        synchronized (oVRMediaServiceManager) {
            if (oVRMediaServiceManager.A0c()) {
                oVRMediaServiceManager.mLivestreamingManager.mIsPaused = false;
                WebRTCCapture webRTCCapture = oVRMediaServiceManager.mWebRTCCapture;
                Surface surface = webRTCCapture.mSurface;
                if (surface != null) {
                    webRTCCapture.A00(str, surface);
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000a, code lost:
        if (r2 != com.oculus.horizon.service_media.AbstractLiveStreamingManager.StreamingType.CAST) goto L_0x000c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized boolean A0D(com.oculus.horizon.service_media.OVRMediaServiceManager r3) {
        /*
            monitor-enter(r3)
            com.oculus.horizon.service_media.AbstractLiveStreamingManager r0 = r3.mLivestreamingManager     // Catch:{ all -> 0x000f }
            if (r0 == 0) goto L_0x000c
            com.oculus.horizon.service_media.AbstractLiveStreamingManager$StreamingType r2 = r0.mStreamingType     // Catch:{ all -> 0x000f }
            com.oculus.horizon.service_media.AbstractLiveStreamingManager$StreamingType r1 = com.oculus.horizon.service_media.AbstractLiveStreamingManager.StreamingType.CAST     // Catch:{ all -> 0x000f }
            r0 = 1
            if (r2 == r1) goto L_0x000d
        L_0x000c:
            r0 = 0
        L_0x000d:
            monitor-exit(r3)
            return r0
        L_0x000f:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.service_media.OVRMediaServiceManager.A0D(com.oculus.horizon.service_media.OVRMediaServiceManager):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0033, code lost:
        if (r2.mIsLivestreaming == false) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0051, code lost:
        if (r1 == false) goto L_0x0053;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized android.os.Bundle A0G() {
        /*
        // Method dump skipped, instructions count: 108
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.service_media.OVRMediaServiceManager.A0G():android.os.Bundle");
    }

    @Nullable
    public final synchronized Bundle A0H() {
        Bundle bundle;
        this.mForegroundService.A9d();
        bundle = new Bundle();
        if (A0c()) {
            bundle = this.mLivestreamingManager.A03(this.mContext);
            this.mLivestreamingManager = null;
        }
        A04(this);
        this.mPresenceManager.livestreamingStateChanged(false, false);
        return bundle;
    }

    public final synchronized Bundle A0I(Bundle bundle) {
        Bundle bundle2;
        String string = bundle.getString("package_name");
        bundle2 = new Bundle();
        bundle2.putBoolean(OVRMediaServiceContract.INTENT_KEY_STREAMING_ENABLED, this.mExternalPlatformLocal.checkAppMediaCapabilityGranted(string).mAllowLivestreaming);
        return bundle2;
    }

    public final synchronized Bundle A0J(Bundle bundle) {
        Bundle bundle2;
        Bundle bundle3;
        String str;
        String str2;
        String str3;
        LivestreamStartStatus livestreamStartStatus;
        JSONObject jSONObject;
        String str4;
        String str5;
        String str6;
        AbstractLiveStreamingManager abstractLiveStreamingManager = this.mLivestreamingManager;
        if (abstractLiveStreamingManager == null) {
            bundle2 = AbstractLiveStreamingManager.A02(LivestreamStartStatus.NO_PACKAGE_SET);
        } else {
            if (!(abstractLiveStreamingManager instanceof FacebookLiveStreamingManager)) {
                if (!(abstractLiveStreamingManager instanceof CastNativeReceiverManager)) {
                    CastLiveStreamingManager castLiveStreamingManager = (CastLiveStreamingManager) abstractLiveStreamingManager;
                    String str7 = castLiveStreamingManager.mEndpoint;
                    String str8 = castLiveStreamingManager.mSessionId;
                    VideoSpec videoSpec = castLiveStreamingManager.mVideoSpec;
                    PlatformPluginManager.nativeInitializeForCastingWithVideoSpecAndBitrateAndDataChannel(str7, "", str8, videoSpec.mHeight, videoSpec.mWidth, videoSpec.mFps, castLiveStreamingManager.mBitrate, castLiveStreamingManager.mEnableDataChannel);
                    castLiveStreamingManager.mIsInitialized = true;
                } else {
                    CastNativeReceiverManager castNativeReceiverManager = (CastNativeReceiverManager) abstractLiveStreamingManager;
                    String str9 = castNativeReceiverManager.mEndpoint;
                    VideoSpec videoSpec2 = castNativeReceiverManager.mVideoSpec;
                    PlatformPluginManager.nativeInitializeForCastingNativeReceiver(str9, "", "", videoSpec2.mWidth, videoSpec2.mHeight, videoSpec2.mFps, 5000);
                    castNativeReceiverManager.mIsInitialized = true;
                }
                bundle3 = null;
            } else {
                FacebookLiveStreamingManager facebookLiveStreamingManager = (FacebookLiveStreamingManager) abstractLiveStreamingManager;
                if (!bundle.containsKey(LiveStreamingLocation.INTENT_KEY)) {
                    AnonymousClass0NO.A09(FacebookLiveStreamingManager.TAG, "Missing Location parameter");
                }
                if (!bundle.containsKey("microphone_status")) {
                    str5 = FacebookLiveStreamingManager.TAG;
                    str6 = "Missing MicrophoneStatus parameter";
                } else {
                    LiveStreamingLocation fromValue = LiveStreamingLocation.fromValue(bundle.getInt(LiveStreamingLocation.INTENT_KEY, LiveStreamingLocation.TIMELINE.getValue()));
                    if (fromValue != LiveStreamingLocation.TIMELINE || bundle.containsKey(LiveStreamingAudience.INTENT_KEY)) {
                        LiveStreamingAudience fromValue2 = LiveStreamingAudience.fromValue(bundle.getInt(LiveStreamingAudience.INTENT_KEY, LiveStreamingAudience.ONLY_ME.getValue()));
                        if (fromValue != LiveStreamingLocation.GROUP || bundle.containsKey(FacebookLiveStreamingManager.KEY_LIVESTREAM_GROUP)) {
                            bundle3 = null;
                            String string = bundle.getString(FacebookLiveStreamingManager.KEY_LIVESTREAM_GROUP, null);
                            if (fromValue != LiveStreamingLocation.PAGE || bundle.containsKey(FacebookLiveStreamingManager.KEY_LIVESTREAM_PAGE)) {
                                String string2 = bundle.getString(FacebookLiveStreamingManager.KEY_LIVESTREAM_PAGE, null);
                                boolean z = bundle.getBoolean(FacebookLiveStreamingManager.KEY_LIVESTREAM_SHOULD_TAG_GAME, false);
                                AccessToken currentFBAccessToken = facebookLiveStreamingManager.mFBConnectHelper.getCurrentFBAccessToken();
                                if (currentFBAccessToken == null) {
                                    str2 = null;
                                    str = null;
                                } else {
                                    str2 = currentFBAccessToken.token;
                                    str = str2;
                                }
                                if (str2 == null) {
                                    AnonymousClass0NO.A08(FacebookLiveStreamingManager.TAG, "No access token. Is your FB connected?");
                                    livestreamStartStatus = LivestreamStartStatus.NO_FB_CONNECT;
                                } else {
                                    String str10 = null;
                                    if (fromValue == LiveStreamingLocation.TIMELINE) {
                                        str3 = AnonymousClass006.A07("https://graph.facebook.com/me/live_videos?privacy=%7B%22value%22%3A%22", fromValue2.toGraphAPIString(), "%22%7D");
                                    } else if (fromValue == LiveStreamingLocation.GROUP && !Strings.isNullOrEmpty(string)) {
                                        str3 = new Uri.Builder().scheme("https").authority("graph.facebook.com").appendPath(string).appendPath(FacebookLiveStreamingManager.url_path).build().toString();
                                    } else if (fromValue != LiveStreamingLocation.PAGE || Strings.isNullOrEmpty(string2)) {
                                        str3 = "";
                                    } else {
                                        str3 = new Uri.Builder().scheme("https").authority("graph.facebook.com").appendPath(string2).appendPath(FacebookLiveStreamingManager.url_path).build().toString();
                                    }
                                    if (fromValue == LiveStreamingLocation.PAGE) {
                                        try {
                                            str4 = FacebookLiveStreamingManager.A00(new HttpGet(new Uri.Builder().scheme("https").authority("graph.facebook.com").appendPath(string2).appendQueryParameter("fields", "access_token").build().toString()), str).getString("access_token");
                                        } catch (IOException | JSONException e) {
                                            try {
                                                AnonymousClass0NO.A0B(FacebookLiveStreamingManager.TAG, "Could not get page access token while starting livestream", e);
                                                str4 = null;
                                            } catch (IOException | JSONException e2) {
                                                AnonymousClass0NO.A0B(FacebookLiveStreamingManager.TAG, "Could not get streamID while starting livestream", e2);
                                            }
                                        }
                                        if (!Strings.isNullOrEmpty(str4)) {
                                            jSONObject = FacebookLiveStreamingManager.A00(new HttpPost(str3), str4);
                                        } else {
                                            jSONObject = null;
                                        }
                                    } else {
                                        jSONObject = FacebookLiveStreamingManager.A00(new HttpPost(str3), str);
                                    }
                                    if (jSONObject != null) {
                                        str10 = jSONObject.getString("id");
                                    }
                                    if (str10 == null) {
                                        livestreamStartStatus = LivestreamStartStatus.NO_SESSION_ID;
                                    } else {
                                        PlatformPluginManager.nativeInitializeMWSConnection(str, str10);
                                        facebookLiveStreamingManager.mAccessToken = str;
                                        facebookLiveStreamingManager.mStreamID = str10;
                                        facebookLiveStreamingManager.mShouldTagGame = z;
                                        facebookLiveStreamingManager.mIsInitialized = true;
                                    }
                                }
                                bundle3 = AbstractLiveStreamingManager.A02(livestreamStartStatus);
                            } else {
                                str5 = FacebookLiveStreamingManager.TAG;
                                str6 = "Missing Page id parameter";
                            }
                        } else {
                            str5 = FacebookLiveStreamingManager.TAG;
                            str6 = "Missing Group id parameter";
                        }
                    } else {
                        str5 = FacebookLiveStreamingManager.TAG;
                        str6 = "Missing Audience parameter";
                    }
                }
                AnonymousClass0NO.A08(str5, str6);
                livestreamStartStatus = LivestreamStartStatus.MISSING_PARAMETERS;
                bundle3 = AbstractLiveStreamingManager.A02(livestreamStartStatus);
            }
            if (bundle3 != null) {
                return bundle3;
            }
            A0U(bundle);
            ExternalPlatformLocal.AppMediaCapabilities checkAppMediaCapabilityGranted = this.mExternalPlatformLocal.checkAppMediaCapabilityGranted(this.mPackageName);
            bundle2 = A00(this, checkAppMediaCapabilityGranted.mAllowLivestreaming, checkAppMediaCapabilityGranted.mAllowVrCasting);
        }
        return bundle2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00e9, code lost:
        if (r0 != false) goto L_0x005d;
     */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized android.os.Bundle A0K(android.os.Bundle r8, com.oculus.horizon.service_media.SurfaceSource r9) {
        /*
        // Method dump skipped, instructions count: 266
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.service_media.OVRMediaServiceManager.A0K(android.os.Bundle, com.oculus.horizon.service_media.SurfaceSource):android.os.Bundle");
    }

    public final synchronized AnonymousClass0DC<Void> A0N(String str, String str2) {
        AnonymousClass0DC<Void> r0;
        VrCastManager A01 = A01(this);
        if (A01 == null) {
            r0 = AnonymousClass0DC.A03(new RuntimeException("VrCastManager is null"));
        } else {
            A05(this);
            r0 = A01.startCastingWithTimeoutAsync(str, str2);
        }
        return r0;
    }

    public final synchronized void A0Q() {
        A05(this);
        this.mLivestreamingManager = new FacebookLiveStreamingManager((MediaUploaderMetadataHelper) AnonymousClass0J2.A03(4, 197, this._UL_mInjectionContext), this.mFBConnectHelper, this.mPlatformPluginManager);
    }

    public final synchronized void A0R() {
        if (!this.mInitialized) {
            this.mForegroundService.A5V();
            this.mInitialized = true;
        }
    }

    public final synchronized void A0S() {
        AbuseRecordingOverlayManager abuseRecordingOverlayManager = this.mAbuseRecordingOverlayManager;
        if (abuseRecordingOverlayManager != null) {
            Context context = this.mContext;
            Intent intent = new Intent(OverlayUtils.START_RENDERING_OVERLAY);
            intent.putExtra(OverlayUtils.OVERLAYS_TYPE, "empty");
            context.sendBroadcast(intent);
            abuseRecordingOverlayManager.mOverlayStartTime = 0;
            abuseRecordingOverlayManager.mRecordingUUID = "";
            this.mAbuseRecordingOverlayManager = null;
        }
    }

    public final synchronized void A0V(Bundle bundle) {
        Intent intent;
        boolean z = bundle.getBoolean(OVRMediaServiceContract.INTENT_KEY_COMMENTS_VISIBLE);
        if (A0c()) {
            AbstractLiveStreamingManager abstractLiveStreamingManager = this.mLivestreamingManager;
            Context context = this.mContext;
            if (abstractLiveStreamingManager instanceof FacebookLiveStreamingManager) {
                FacebookLiveStreamingManager facebookLiveStreamingManager = (FacebookLiveStreamingManager) abstractLiveStreamingManager;
                facebookLiveStreamingManager.mCommentsVisible = z;
                if (z) {
                    intent = new Intent(OverlayUtils.START_RENDERING_OVERLAY);
                    intent.putExtra(OverlayUtils.OVERLAYS_TYPE, "livestreaming_comments");
                    intent.putExtra("broadcast_id", facebookLiveStreamingManager.mStreamID);
                } else {
                    intent = new Intent(OverlayUtils.STOP_RENDERING_OVERLAY);
                }
                context.sendBroadcast(intent);
            }
            PresenceManager presenceManager = this.mPresenceManager;
            AbstractLiveStreamingManager abstractLiveStreamingManager2 = this.mLivestreamingManager;
            presenceManager.livestreamingStateChanged(abstractLiveStreamingManager2.mIsLivestreaming, abstractLiveStreamingManager2.mCommentsVisible);
            Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, this._UL_mInjectionContext)).A22(OVRMediaServiceContract.LIVESTREAMING_COMMENTS_VISIBLITY_EVENT);
            A22.A15("package_name", this.mPresenceManager.mLastLogAppID);
            A22.A16(OVRMediaServiceContract.LIVESTREAMING_COMMENTS_VISIBILITY, this.mLivestreamingManager.mCommentsVisible);
            A22.A5L();
        }
    }

    public final synchronized void A0Y(@Nullable String str) {
        VideoCaptureStatus videoCaptureStatus;
        if (TextUtils.isEmpty(str)) {
            AnonymousClass0NO.A08(TAG, "Unsupported target package for default capture");
        } else {
            if (!str.equals(this.mPackageName)) {
                videoCaptureStatus = VideoCaptureStatus.WAITING;
                String.valueOf(videoCaptureStatus);
            } else if (A0E(this, false)) {
                videoCaptureStatus = VideoCaptureStatus.ON;
                String.valueOf(videoCaptureStatus);
            } else {
                videoCaptureStatus = VideoCaptureStatus.OFF;
                String.valueOf(videoCaptureStatus);
            }
            String.valueOf(str);
            this.mDefaultCaptureStatus = videoCaptureStatus;
            this.mVideoCaptureTargetPackage = str;
        }
    }

    public final synchronized void A0Z(String str) {
        VrCastManager vrCastManager = this.mVrCastManager;
        if (vrCastManager != null) {
            vrCastManager.stopCast(str);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0008, code lost:
        if (r1 == false) goto L_0x000a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean A0c() {
        /*
            r2 = this;
            monitor-enter(r2)
            com.oculus.horizon.service_media.AbstractLiveStreamingManager r0 = r2.mLivestreamingManager     // Catch:{ all -> 0x000d }
            if (r0 == 0) goto L_0x000a
            boolean r1 = r0.mIsInitialized     // Catch:{ all -> 0x000d }
            r0 = 1
            if (r1 != 0) goto L_0x000b
        L_0x000a:
            r0 = 0
        L_0x000b:
            monitor-exit(r2)
            return r0
        L_0x000d:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.service_media.OVRMediaServiceManager.A0c():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000c, code lost:
        if (r1.mIsLivestreaming == false) goto L_0x000e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean A0d() {
        /*
            r2 = this;
            monitor-enter(r2)
            com.oculus.horizon.service_media.AbstractLiveStreamingManager r1 = r2.mLivestreamingManager     // Catch:{ all -> 0x0011 }
            if (r1 == 0) goto L_0x000e
            boolean r0 = r1.mIsInitialized     // Catch:{ all -> 0x0011 }
            if (r0 == 0) goto L_0x000e
            boolean r1 = r1.mIsLivestreaming     // Catch:{ all -> 0x0011 }
            r0 = 1
            if (r1 != 0) goto L_0x000f
        L_0x000e:
            r0 = 0
        L_0x000f:
            monitor-exit(r2)
            return r0
        L_0x0011:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.service_media.OVRMediaServiceManager.A0d():boolean");
    }

    public final synchronized boolean A0e() {
        boolean z;
        FileCapture.Mode mode;
        if (this.mDefaultCaptureStatus == VideoCaptureStatus.ON) {
            VideoCaptureStatus videoCaptureStatus = VideoCaptureStatus.OFF;
            String.valueOf(videoCaptureStatus);
            String.valueOf((Object) null);
            this.mDefaultCaptureStatus = videoCaptureStatus;
            this.mVideoCaptureTargetPackage = null;
        }
        if (this.mFileCapture.A05()) {
            FileCapture fileCapture = this.mFileCapture;
            synchronized (fileCapture.mLock) {
                mode = fileCapture.mCaptureMode;
            }
            if (mode == FileCapture.Mode.CAPTURE_TO_DISK) {
                FileCapture fileCapture2 = this.mFileCapture;
                String str = this.mPackageName;
                z = true;
                synchronized (fileCapture2.mLock) {
                    try {
                        fileCapture2.mNotifyMediaReady = true;
                        fileCapture2.A9R(str);
                    } finally {
                    }
                }
            }
        }
        this.mFileCapture.A02();
        z = false;
        return z;
    }

    @Override // com.oculus.horizon.media_session.MediaSessionCallback
    public final void A6C() {
        A0C(true);
    }

    @Override // com.oculus.horizon.media_session.MediaSessionCallback
    public final void A6D() {
        A0C(false);
    }

    @Override // com.oculus.horizon.media_session.MediaSessionCallback
    public final void A6H() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000e, code lost:
        if (r1.mStreamingType != com.oculus.horizon.service_media.AbstractLiveStreamingManager.StreamingType.CAST) goto L_0x0010;
     */
    @Override // com.oculus.horizon.media_session.MediaSessionCallback
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean A8z() {
        /*
            r3 = this;
            monitor-enter(r3)
            com.oculus.horizon.service_media.AbstractLiveStreamingManager r1 = r3.mLivestreamingManager     // Catch:{ all -> 0x0013 }
            if (r1 == 0) goto L_0x0010
            boolean r0 = r1.mIsInitialized     // Catch:{ all -> 0x0013 }
            if (r0 == 0) goto L_0x0010
            com.oculus.horizon.service_media.AbstractLiveStreamingManager$StreamingType r2 = r1.mStreamingType     // Catch:{ all -> 0x0013 }
            com.oculus.horizon.service_media.AbstractLiveStreamingManager$StreamingType r1 = com.oculus.horizon.service_media.AbstractLiveStreamingManager.StreamingType.CAST     // Catch:{ all -> 0x0013 }
            r0 = 1
            if (r2 == r1) goto L_0x0011
        L_0x0010:
            r0 = 0
        L_0x0011:
            monitor-exit(r3)
            return r0
        L_0x0013:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.service_media.OVRMediaServiceManager.A8z():boolean");
    }

    @Override // com.oculus.horizon.service_media.vrcast.VrCastManager.ErrorCallback
    public final synchronized void onVrCastError() {
        AbstractLiveStreamingManager abstractLiveStreamingManager = this.mLivestreamingManager;
        if (abstractLiveStreamingManager != null) {
            abstractLiveStreamingManager.A03(this.mContext);
            this.mLivestreamingManager = null;
        }
        A08(this, CaptureErrorType.GENERIC);
    }

    /* renamed from: com.oculus.horizon.service_media.OVRMediaServiceManager$9  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass9 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizon$service_media$OVRMediaServiceManager$CaptureErrorType;
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizon$service_media$OVRMediaServiceManager$CastToBrowserStatus;
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$horizon$service_media$OVRMediaServiceManager$VideoCaptureStatus;

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|(2:17|18)|19|21|22|24) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0035 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x003d */
        static {
            /*
                com.oculus.horizon.service_media.OVRMediaServiceManager$VideoCaptureStatus[] r0 = com.oculus.horizon.service_media.OVRMediaServiceManager.VideoCaptureStatus.values()
                int r0 = r0.length
                int[] r1 = new int[r0]
                com.oculus.horizon.service_media.OVRMediaServiceManager.AnonymousClass9.$SwitchMap$com$oculus$horizon$service_media$OVRMediaServiceManager$VideoCaptureStatus = r1
                r4 = 1
                com.oculus.horizon.service_media.OVRMediaServiceManager$VideoCaptureStatus r0 = com.oculus.horizon.service_media.OVRMediaServiceManager.VideoCaptureStatus.OFF     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r1[r0] = r4     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r3 = 2
                com.oculus.horizon.service_media.OVRMediaServiceManager$VideoCaptureStatus r0 = com.oculus.horizon.service_media.OVRMediaServiceManager.VideoCaptureStatus.ON     // Catch:{ NoSuchFieldError -> 0x001b }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r1[r0] = r3     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                r2 = 3
                com.oculus.horizon.service_media.OVRMediaServiceManager$VideoCaptureStatus r0 = com.oculus.horizon.service_media.OVRMediaServiceManager.VideoCaptureStatus.WAITING     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r1[r0] = r2     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.horizon.service_media.OVRMediaServiceManager$CastToBrowserStatus[] r0 = com.oculus.horizon.service_media.OVRMediaServiceManager.CastToBrowserStatus.values()
                int r0 = r0.length
                int[] r1 = new int[r0]
                com.oculus.horizon.service_media.OVRMediaServiceManager.AnonymousClass9.$SwitchMap$com$oculus$horizon$service_media$OVRMediaServiceManager$CastToBrowserStatus = r1
                com.oculus.horizon.service_media.OVRMediaServiceManager$CastToBrowserStatus r0 = com.oculus.horizon.service_media.OVRMediaServiceManager.CastToBrowserStatus.INITIATED     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r1[r0] = r4     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                com.oculus.horizon.service_media.OVRMediaServiceManager$CastToBrowserStatus r0 = com.oculus.horizon.service_media.OVRMediaServiceManager.CastToBrowserStatus.CASTING_TO_BROWSER     // Catch:{ NoSuchFieldError -> 0x003d }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r1[r0] = r3     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                com.oculus.horizon.service_media.OVRMediaServiceManager$CastToBrowserStatus r0 = com.oculus.horizon.service_media.OVRMediaServiceManager.CastToBrowserStatus.TIME_OUT     // Catch:{ NoSuchFieldError -> 0x0045 }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0045 }
                r1[r0] = r2     // Catch:{ NoSuchFieldError -> 0x0045 }
            L_0x0045:
                com.oculus.horizon.service_media.OVRMediaServiceManager$CaptureErrorType[] r0 = com.oculus.horizon.service_media.OVRMediaServiceManager.CaptureErrorType.values()
                int r0 = r0.length
                int[] r1 = new int[r0]
                com.oculus.horizon.service_media.OVRMediaServiceManager.AnonymousClass9.$SwitchMap$com$oculus$horizon$service_media$OVRMediaServiceManager$CaptureErrorType = r1
                com.oculus.horizon.service_media.OVRMediaServiceManager$CaptureErrorType r0 = com.oculus.horizon.service_media.OVRMediaServiceManager.CaptureErrorType.STOPPED_BY_ANOTHER_CAPTURE     // Catch:{ NoSuchFieldError -> 0x0056 }
                int r0 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0056 }
                r1[r0] = r4     // Catch:{ NoSuchFieldError -> 0x0056 }
            L_0x0056:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.service_media.OVRMediaServiceManager.AnonymousClass9.<clinit>():void");
        }
    }

    @Nullable
    public static VrCastManager A01(OVRMediaServiceManager oVRMediaServiceManager) {
        VrCastManager vrCastManager = oVRMediaServiceManager.mVrCastManager;
        if (vrCastManager != null) {
            return vrCastManager;
        }
        if (VrCastManager.isCompatibleOSVersion() && DeviceType.current() != DeviceType.Seacliff) {
            VrCastManager vrCastManager2 = (VrCastManager) AnonymousClass0J2.A04(232, oVRMediaServiceManager._UL_mInjectionContext);
            vrCastManager2.mErrorCallback = oVRMediaServiceManager;
            oVRMediaServiceManager.mVrCastManager = vrCastManager2;
        }
        return oVRMediaServiceManager.mVrCastManager;
    }

    private void A02() {
        OVRLivestreamingErrorUtility oVRLivestreamingErrorUtility = this.mLivestreamingErrorUtility;
        OVRLivestreamingErrorUtility.A00(oVRLivestreamingErrorUtility, "com.oculus.systemactivities");
        OVRLivestreamingErrorUtility.A00(oVRLivestreamingErrorUtility, "com.oculus.vrshell.home");
        OVRLivestreamingErrorUtility.A00(oVRLivestreamingErrorUtility, "com.oculus.socialplatform");
        OVRLivestreamingErrorUtility oVRLivestreamingErrorUtility2 = this.mLivestreamingErrorUtility;
        Intent intent = new Intent(OVRMediaServiceContract.LOCALSTREAMING_STATUS_CHANGE);
        intent.setPackage("com.oculus.vrshell");
        intent.putExtra("is_capture_running", 0);
        oVRLivestreamingErrorUtility2.mContext.sendBroadcast(intent);
    }

    private final void A03() {
        String str;
        if (!TextUtils.isEmpty(this.mPackageName)) {
            A0S();
            AbuseAVCapture abuseAVCapture = this.mAbuseAVCapture;
            String str2 = this.mPackageName;
            synchronized (abuseAVCapture.mLock) {
                str = abuseAVCapture.mRecordingUUID;
            }
            if (str != null) {
                abuseAVCapture.A04(str2, str, AbuseAVCapture.CancelSource.OTHER_CAPTURE);
            }
        }
    }

    public static void A04(OVRMediaServiceManager oVRMediaServiceManager) {
        if (oVRMediaServiceManager.mPlatformPluginManager.A08(oVRMediaServiceManager)) {
            Intent intent = new Intent("com.oculus.livestreaming_status_change");
            intent.putExtras(oVRMediaServiceManager.A0G());
            intent.setPackage(oVRMediaServiceManager.mPackageName);
            oVRMediaServiceManager.mContext.sendBroadcast(intent);
            intent.setPackage("com.oculus.systemux");
            oVRMediaServiceManager.mContext.sendBroadcast(intent);
            if (!"com.oculus.vrshell".equals(oVRMediaServiceManager.mPackageName)) {
                intent.setPackage("com.oculus.vrshell");
                oVRMediaServiceManager.mContext.sendBroadcast(intent);
            }
            A06(oVRMediaServiceManager);
        }
    }

    @VisibleForTesting
    public static final void A05(OVRMediaServiceManager oVRMediaServiceManager) {
        oVRMediaServiceManager.A0W(CastStopSource.OTHER_CAPTURE_STARTING);
        AbstractLiveStreamingManager abstractLiveStreamingManager = oVRMediaServiceManager.mLivestreamingManager;
        if (abstractLiveStreamingManager == null || !abstractLiveStreamingManager.mIsLivestreaming) {
            AbuseAVCapture abuseAVCapture = oVRMediaServiceManager.mAbuseAVCapture;
            if (abuseAVCapture != null && abuseAVCapture.A03()) {
                OVRMediaServiceNotification oVRMediaServiceNotification = oVRMediaServiceManager.mOVRMediaServiceNotification;
                OVRMediaServiceNotification.A03(oVRMediaServiceNotification, oVRMediaServiceNotification.mContext.getString(R.string.abuse_recording_interrupted_hmd_unmount_title), oVRMediaServiceNotification.mContext.getString(R.string.stopped_by_another_capture_notification_message));
            }
        } else {
            oVRMediaServiceManager.A0H();
            A08(oVRMediaServiceManager, CaptureErrorType.STOPPED_BY_ANOTHER_CAPTURE);
        }
        oVRMediaServiceManager.A0Z(VrCastManager.CALLER_CONTEXT_ANOTHER_CAPTURE);
        oVRMediaServiceManager.A0e();
        oVRMediaServiceManager.A03();
        Handler handler = oVRMediaServiceManager.mCastWWWAnswerPoller;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public static void A07(OVRMediaServiceManager oVRMediaServiceManager, @Nullable Surface surface) {
        if (oVRMediaServiceManager.mPlatformPluginManager.A08(oVRMediaServiceManager)) {
            Intent intent = new Intent("com.oculus.vrcamera_surface_change");
            intent.setPackage(oVRMediaServiceManager.mPackageName);
            intent.putExtra("surface", surface);
            intent.putExtra("com.oculus.vrcamera_surface_change", "BEGIN_VIDEO_CAPTURE_WITH_VRCAMERA_SURFACE");
            oVRMediaServiceManager.mContext.sendBroadcast(intent);
        }
    }

    public static void A0B(@Nullable OVRMediaServiceManager oVRMediaServiceManager, String str, ExternalPlatformLocal.AppMediaCapabilities appMediaCapabilities) {
        if (oVRMediaServiceManager.mFileCapture.A05() && !appMediaCapabilities.mAllowScreenRecording) {
            oVRMediaServiceManager.mOVRMediaServiceNotification.A05(str);
        }
        if (oVRMediaServiceManager.mSurfaceCapture.mSurfaceCaptureStarted && !appMediaCapabilities.mAllowVrCasting) {
            oVRMediaServiceManager.mOVRMediaServiceNotification.A05(str);
        }
    }

    private final void A0C(boolean z) {
        Throwable th;
        FileCapture.Mode mode;
        try {
            SettingsManager settingsManager = new SettingsManager();
            if (z) {
                this.mCaptureReceiver.registerReceiver(this.mContext);
                if (((InstantReplayHelper) AnonymousClass0J2.A03(7, 219, this._UL_mInjectionContext)).A00(settingsManager, this.mPackageName, this)) {
                    A0a(true);
                    A0E(this, true);
                }
            } else {
                try {
                    this.mCaptureReceiver.unregisterReceiver(this.mContext);
                } catch (IllegalArgumentException e) {
                    AnonymousClass0NO.A0C(TAG, "Receiver was not registered", e);
                }
                A0e();
                if (this.mFileCapture.A05()) {
                    FileCapture fileCapture = this.mFileCapture;
                    synchronized (fileCapture.mLock) {
                        try {
                            mode = fileCapture.mCaptureMode;
                        } catch (Throwable th2) {
                            th = th2;
                            throw th;
                        }
                    }
                    if (mode == FileCapture.Mode.ABUSE_CAPTURE) {
                        OVRMediaServiceNotification oVRMediaServiceNotification = this.mOVRMediaServiceNotification;
                        OVRMediaServiceNotification.A03(oVRMediaServiceNotification, oVRMediaServiceNotification.mContext.getString(R.string.abuse_recording_interrupted_hmd_unmount_title), oVRMediaServiceNotification.mContext.getString(R.string.abuse_recording_interrupted_hmd_unmount_subtitle));
                        A03();
                    }
                }
            }
            PlatformPluginManager platformPluginManager = this.mPlatformPluginManager;
            if (z && !PlatformPluginManager.A05(platformPluginManager)) {
                synchronized (platformPluginManager.mVrStateManagerLock) {
                    try {
                        platformPluginManager.mVRStateManager.mIsDocked = true;
                    } catch (Throwable th3) {
                        th = th3;
                        throw th;
                    }
                }
            }
            platformPluginManager.A06(this);
            if (PlatformPluginManager.A01(platformPluginManager) == null) {
                synchronized (platformPluginManager.mNativeCodeLock) {
                    try {
                        PlatformPluginManager.nativeSetHeadsetMountState(z);
                    } catch (Throwable th4) {
                        th = th4;
                        throw th;
                    }
                }
            }
        } catch (Exception e2) {
            String name = e2.getClass().getName();
            if (name.contentEquals("android.os.DeadSystemException") || name.contentEquals("android.os.DeadObjectException")) {
                AnonymousClass0NO.A0B(TAG, "shutting down", e2);
                return;
            }
            throw e2;
        }
    }

    @VisibleForTesting
    public static final boolean A0E(OVRMediaServiceManager oVRMediaServiceManager, boolean z) {
        File file;
        if (TextUtils.isEmpty(oVRMediaServiceManager.mPackageName)) {
            AnonymousClass0NO.A08(TAG, "No package name set");
        } else {
            A05(oVRMediaServiceManager);
            if (!oVRMediaServiceManager.mExternalPlatformLocal.checkAppMediaCapabilityGranted(oVRMediaServiceManager.mPackageName).mAllowScreenRecording) {
                oVRMediaServiceManager.mOVRMediaServiceNotification.A05(oVRMediaServiceManager.mPackageName);
                return false;
            } else if (AnonymousClass04J.A01(oVRMediaServiceManager.mContext, EXTERNAL_STORAGE_PERMISSION) != 0) {
                PermissionRequest permissionRequest = new PermissionRequest(oVRMediaServiceManager.mContext, new PermissionRequest.Callback() {
                    /* class com.oculus.horizon.service_media.OVRMediaServiceManager.AnonymousClass8 */

                    @Override // com.oculus.horizon.util.permissions.PermissionRequest.Callback
                    public final void A6R(boolean z, long j) {
                        if (z && j == 100000) {
                            OVRMediaServiceManager oVRMediaServiceManager = OVRMediaServiceManager.this;
                            FileCapture.A01(oVRMediaServiceManager.mFileCapture, oVRMediaServiceManager.mPackageName, null, FileCapture.Mode.CAPTURE_TO_DISK, false);
                        }
                    }
                });
                try {
                    String[] split = permissionRequest.mContext.getPackageManager().getPackageInfo("com.oculus.home", 0).versionName.split("\\.");
                    if (Double.parseDouble(AnonymousClass006.A07(split[0], ".", split[1])) >= 3.46d) {
                        Intent intent = new Intent(PermissionRequest.PERMISSION_INTENT_ACTION_NAME);
                        intent.addFlags(268435456);
                        intent.putExtra(PermissionRequest.PERMISSION_INTENT_KEY, EXTERNAL_STORAGE_PERMISSION);
                        intent.putExtra("platform_request_id", 100000L);
                        intent.putExtra(PermissionRequest.ACTION_NAME_INTENT_KEY, PermissionRequest.PERMISSION_CALLBACK_INTENT_ACTION_NAME);
                        new OculusPublicBroadcastReceiver(PermissionRequest.PERMISSION_CALLBACK_INTENT_ACTION_NAME) {
                            /* class com.oculus.horizon.util.permissions.PermissionRequest.AnonymousClass1 */
                            public final /* synthetic */ long val$requestId = 100000;

                            /* JADX WARNING: Code restructure failed: missing block: B:5:0x0028, code lost:
                                if (r1.equals("granted") == false) goto L_0x002a;
                             */
                            @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
                            /* Code decompiled incorrectly, please refer to instructions dump. */
                            public final void onReceive(android.content.Context r7, android.content.Intent r8, X.AnonymousClass0b9 r9) {
                                /*
                                    r6 = this;
                                    java.lang.String r2 = "platform_request_id"
                                    r0 = 0
                                    long r2 = r8.getLongExtra(r2, r0)
                                    java.lang.String r0 = "permission_status"
                                    java.lang.String r1 = r8.getStringExtra(r0)
                                    long r4 = r6.val$requestId
                                    int r0 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
                                    if (r0 != 0) goto L_0x002e
                                    com.oculus.horizon.util.permissions.PermissionRequest r0 = com.oculus.horizon.util.permissions.PermissionRequest.this
                                    android.content.Context r0 = r0.mContext
                                    r6.unregisterReceiver(r0)
                                    com.oculus.horizon.util.permissions.PermissionRequest r0 = com.oculus.horizon.util.permissions.PermissionRequest.this
                                    com.oculus.horizon.util.permissions.PermissionRequest$Callback r4 = r0.mCallback
                                    if (r1 == 0) goto L_0x002a
                                    java.lang.String r0 = "granted"
                                    boolean r1 = r1.equals(r0)
                                    r0 = 1
                                    if (r1 != 0) goto L_0x002b
                                L_0x002a:
                                    r0 = 0
                                L_0x002b:
                                    r4.A6R(r0, r2)
                                L_0x002e:
                                    return
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.util.permissions.PermissionRequest.AnonymousClass1.onReceive(android.content.Context, android.content.Intent, X.0b9):void");
                            }
                        }.registerReceiver(permissionRequest.mContext);
                        permissionRequest.mContext.startActivity(intent);
                        return false;
                    }
                } catch (PackageManager.NameNotFoundException e) {
                    AnonymousClass0NO.A0B(PermissionRequest.TAG, "Home is not installed", e);
                    return false;
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e2) {
                    AnonymousClass0NO.A0B(PermissionRequest.TAG, "Invalid Home version name", e2);
                    return false;
                }
            } else {
                FileCapture.A01(oVRMediaServiceManager.mFileCapture, oVRMediaServiceManager.mPackageName, null, FileCapture.Mode.CAPTURE_TO_DISK, z);
                if (z) {
                    return true;
                }
                FileCapture fileCapture = oVRMediaServiceManager.mFileCapture;
                synchronized (fileCapture) {
                    file = fileCapture.mRecordingFile;
                }
                if (file == null) {
                    AnonymousClass0NO.A08(TAG, "Null media file when saving screen capture metadata.");
                    return true;
                }
                oVRMediaServiceManager.A0X(file, false, false);
                return true;
            }
        }
        return false;
    }

    public final Bundle A0L(String str) {
        JSONObject jSONObject;
        boolean A05;
        Bundle bundle = new Bundle();
        bundle.putString(OVRMediaServiceContract.ABUSE_REPORT_PACKAGE_NAME, this.mPackageName);
        if (TextUtils.isEmpty(this.mPackageName)) {
            A05 = false;
        } else {
            AbuseAVCapture abuseAVCapture = this.mAbuseAVCapture;
            synchronized (abuseAVCapture.mLock) {
                jSONObject = abuseAVCapture.mAbuseReportParams;
            }
            if (jSONObject != null) {
                jSONObject.toString();
                bundle.putString(OVRMediaServiceContract.KEY_ABUSE_REPORT_PARAMS, jSONObject.toString());
            }
            A0S();
            A05 = this.mAbuseAVCapture.A05(this.mPackageName, str, AbuseAVCapture.StopSource.USER);
        }
        bundle.putBoolean(OVRMediaServiceContract.BROADCAST_CAPTURE_ABUSE_STATUS, A05);
        return bundle;
    }

    public final void A0P() {
        this.mForegroundService.A9d();
        synchronized (this) {
            if (this.mInitialized) {
                A0W(CastStopSource.STANDBY);
                this.mPlatformPluginManager.A07(this);
                A0H();
                A03();
                A0a(true);
                VrCastManager vrCastManager = this.mVrCastManager;
                if (vrCastManager != null) {
                    vrCastManager.unbindVrCastService();
                    this.mVrCastManager = null;
                }
                this.mInitialized = false;
            }
        }
    }

    public final void A0U(Bundle bundle) {
        if (bundle.containsKey("microphone_status")) {
            boolean z = false;
            if (LiveStreamingMicrophoneStatus.fromValue(bundle.getInt("microphone_status", -1)) != LiveStreamingMicrophoneStatus.MICROPHONE_ON) {
                z = true;
            }
            this.mPlatformPluginManager.A06(this);
            PlatformPluginManager platformPluginManager = this.mPlatformPluginManager;
            if (PlatformPluginManager.A05(platformPluginManager)) {
                synchronized (platformPluginManager.mNativeCodeLock) {
                    PlatformPluginManager.nativeSetLivestreamingMicrophoneMuted(z);
                }
            }
        }
    }

    public final void A0W(CastStopSource castStopSource) {
        String A01 = this.mCastHTTPServerManager.A01();
        if (A01 == null) {
            A01 = "";
        }
        CastHTTPServerManager castHTTPServerManager = this.mCastHTTPServerManager;
        synchronized (castHTTPServerManager) {
            CastHTTPServerBase castHTTPServerBase = castHTTPServerManager.mCastHTTPServer;
            if (castHTTPServerBase != null) {
                castHTTPServerBase.A07(castStopSource);
                castHTTPServerManager.mCastHTTPServer = null;
            }
        }
        if (!this.mIsDataChannelEnabled.get()) {
            return;
        }
        if (!A0D(this)) {
            AnonymousClass0NO.A08(TAG, "sendJsonData is called in none cast streaming type");
        } else {
            PlatformPluginManager.nativeSendToJsonDataChannel(new Message(A01, Message.Type.STOP, castStopSource.getName()).A01().toString());
        }
    }

    public final void A0X(File file, boolean z, boolean z2) {
        if (((AnonymousClass0Rg) AnonymousClass0J2.A03(6, 399, this._UL_mInjectionContext)).A36(36310306355413018L)) {
            MediaMetadata mediaMetadata = new MediaMetadata(this.mPresenceManager.getCurrentAppOrPanelAppID(), this.mPresenceManager.getCurrentRichPresenceJSON(), z, z2);
            Context context = this.mContext;
            String name = file.getName();
            try {
                File file2 = new File(context.getFilesDir(), FileUtils.MEDIA_METADATA_PATH);
                file2.mkdirs();
                MediaUploaderDB.A04(new FileOutputStream(new File(file2, StringFormatUtil.formatStrLocaleSafe("%s.%s", name, FileUtils.MEDIA_METADATA_EXT))), mediaMetadata.A00().toString());
            } catch (FileNotFoundException | SecurityException | JSONException e) {
                AnonymousClass0NO.A0B(MediaUploaderDB.TAG, "Failed to write media metadata", e);
            }
        }
    }

    public final void A0a(boolean z) {
        if (!z) {
            this.mFileCapture.A04();
        } else if (this.mFileCapture.A04()) {
            FileCapture fileCapture = this.mFileCapture;
            synchronized (fileCapture.mLock) {
                if (fileCapture.mAutoCaptureForReportUUID != null) {
                    fileCapture.mAutoCaptureForReportUUID = null;
                }
            }
        }
    }

    public final boolean A0b() {
        boolean z;
        FileCapture fileCapture = this.mFileCapture;
        synchronized (fileCapture.mLock) {
            z = fileCapture.mIsInstantReplay;
        }
        if (z) {
            return A0e();
        }
        return false;
    }

    @Override // com.oculus.horizon.platformplugin.PlatformPluginManager.Client
    public final void showPartyChatResume() {
        OVRMediaServiceNotification oVRMediaServiceNotification = this.mOVRMediaServiceNotification;
        OVRMediaServiceNotification.A02(oVRMediaServiceNotification, oVRMediaServiceNotification.mContext.getString(R.string.notifications_party_reestablish_voip), oVRMediaServiceNotification.mContext.getString(R.string.notifications_party_reestablish_voip_message));
    }

    @Override // com.oculus.horizon.platformplugin.PlatformPluginManager.Client
    public final void showPartyChatSuspend() {
        OVRMediaServiceNotification oVRMediaServiceNotification = this.mOVRMediaServiceNotification;
        OVRMediaServiceNotification.A02(oVRMediaServiceNotification, oVRMediaServiceNotification.mContext.getString(R.string.notifications_party_pause_voip), oVRMediaServiceNotification.mContext.getString(R.string.notifications_party_pause_voip_message));
    }

    @Inject
    public OVRMediaServiceManager(AbstractC06640p5 r5, @Assisted ForegroundServiceInterface foregroundServiceInterface) {
        this._UL_mInjectionContext = new AnonymousClass0QC(8, r5);
        this.mContext = C003108z.A02(r5);
        this.mUserProfileHelper = (UserProfileHelper) AnonymousClass117.A00(68, r5);
        this.mExternalPlatformLocal = ExternalPlatformLocal._UL__ULSEP_com_oculus_horizon_service_ExternalPlatformLocal_ULSEP_ACCESS_METHOD(r5);
        this.mPlatformPluginManager = (PlatformPluginManager) AnonymousClass117.A00(160, r5);
        this.mLivestreamingErrorUtility = (OVRLivestreamingErrorUtility) AnonymousClass117.A00(22, r5);
        this.mPresenceManager = PresenceManager._UL__ULSEP_com_oculus_horizon_platform_PresenceManager_ULSEP_ACCESS_METHOD(r5);
        this.mAbuseAVCapture = (AbuseAVCapture) AnonymousClass117.A00(203, r5);
        this.mFileCapture = (FileCapture) AnonymousClass117.A00(525, r5);
        this.mFBConnectHelper = FBConnectHelper._UL__ULSEP_com_oculus_horizon_fbconnect_FBConnectHelper_ULSEP_ACCESS_METHOD(r5);
        this.mApiRequestManager = ApiRequestManager._UL__ULSEP_com_oculus_horizon_api_ApiRequestManager_ULSEP_ACCESS_METHOD(r5);
        this.mSurfaceCapture = (SurfaceCapture) AnonymousClass117.A00(125, r5);
        this.mOVRMediaServiceNotification = (OVRMediaServiceNotification) AnonymousClass117.A00(149, r5);
        this.mForegroundAppChecker = (ForegroundAppChecker) AnonymousClass117.A00(192, r5);
        this.mWebRTCCapture = (WebRTCCapture) AnonymousClass117.A00(536, r5);
        this.mForegroundService = foregroundServiceInterface;
        this.mIsSocialPlatformPartyInfraValid = SocialPlatformVersionUtil.A02(this.mContext);
        SocialPlatformVersionUtil.A01(new SocialPlatformVersionUtil.SubscribeCallback() {
            /* class com.oculus.horizon.service_media.OVRMediaServiceManager.AnonymousClass2 */

            @Override // com.oculus.socialplatform.util.SocialPlatformVersionUtil.SubscribeCallback
            public final void onSubscribed(boolean z) {
                OVRMediaServiceManager.this.mIsSocialPlatformPartyInfraValid = z;
            }
        });
        HandlerThread handlerThread = new HandlerThread("NetworkHandlerThreader");
        this.mNetworkHandlerThreader = handlerThread;
        handlerThread.start();
        AnonymousClass3 r3 = new Handler(this.mNetworkHandlerThreader.getLooper()) {
            /* class com.oculus.horizon.service_media.OVRMediaServiceManager.AnonymousClass3 */

            public final void handleMessage(android.os.Message message) {
                int i = message.what;
                if (i == 1) {
                    Object obj = message.obj;
                    if (obj == null) {
                        AnonymousClass0NO.A08(OVRMediaServiceManager.TAG, "No parameters passed to CAST_EVENT_MESSAGE_ID_START");
                        return;
                    }
                    CastHTTPServerManager.StartCastParams startCastParams = (CastHTTPServerManager.StartCastParams) obj;
                    int A3d = ((AnonymousClass0Rg) AnonymousClass0J2.A03(6, 399, OVRMediaServiceManager.this._UL_mInjectionContext)).A3d(36591777037287441L, OVRMediaServiceContract.DEFAULT_PHONE_CASTING_BITRATE);
                    OVRMediaServiceManager oVRMediaServiceManager = OVRMediaServiceManager.this;
                    String str = startCastParams.endpoint;
                    String str2 = startCastParams.sessionId;
                    VideoSpec videoSpec = startCastParams.spec;
                    boolean z = startCastParams.enableDataChannel;
                    synchronized (oVRMediaServiceManager) {
                        oVRMediaServiceManager.mPlatformPluginManager.A06(oVRMediaServiceManager);
                        oVRMediaServiceManager.mLivestreamingManager = new CastLiveStreamingManager(oVRMediaServiceManager.mPlatformPluginManager, str, str2, videoSpec, A3d, z);
                        Bundle bundle = new Bundle();
                        bundle.putInt("microphone_status", LiveStreamingMicrophoneStatus.MICROPHONE_OFF.getValue());
                        oVRMediaServiceManager.A0J(bundle);
                    }
                    OVRMediaServiceManager.this.mIsDataChannelEnabled.set(startCastParams.enableDataChannel);
                    if (!OVRMediaServiceManager.this.mIsDataChannelEnabled.get()) {
                        OVRMediaServiceManager oVRMediaServiceManager2 = OVRMediaServiceManager.this;
                        ExternalPlatformLocal.AppMediaCapabilities checkAppMediaCapabilityGranted = oVRMediaServiceManager2.mExternalPlatformLocal.checkAppMediaCapabilityGranted(oVRMediaServiceManager2.mPackageName);
                        OVRMediaServiceManager oVRMediaServiceManager3 = OVRMediaServiceManager.this;
                        CastHTTPServerManager castHTTPServerManager = oVRMediaServiceManager3.mCastHTTPServerManager;
                        String str3 = oVRMediaServiceManager3.mPackageName;
                        if (str3 == null) {
                            str3 = "";
                        }
                        castHTTPServerManager.A02("castingStart", str3, checkAppMediaCapabilityGranted.mAllowScreenRecording, checkAppMediaCapabilityGranted.mAllowVrCasting);
                    }
                } else if (i != 2) {
                    if (i == 3 && OVRMediaServiceManager.A0D(OVRMediaServiceManager.this)) {
                        OVRMediaServiceManager.this.A0H();
                        OVRMediaServiceManager.A08(OVRMediaServiceManager.this, CaptureErrorType.GENERIC);
                    }
                } else if (OVRMediaServiceManager.A0D(OVRMediaServiceManager.this)) {
                    OVRMediaServiceManager.this.A0H();
                    OVRMediaServiceManager.this.mIsTwilightDataChannelReady.set(false);
                    OVRMediaServiceManager.this.mIsDataChannelEnabled.set(false);
                } else {
                    OVRMediaServiceManager.A06(OVRMediaServiceManager.this);
                }
            }
        };
        this.mCastEventHandler = r3;
        this.mCastHTTPServerManager = new CastHTTPServerManager((AnonymousClass0JA) AnonymousClass0J2.A03(2, 146, this._UL_mInjectionContext), this.mContext, r3);
    }

    public final Bundle A0F() {
        A05(this);
        Bundle bundle = new Bundle();
        boolean z = false;
        if (TextUtils.isEmpty(this.mPackageName)) {
            bundle.putBoolean(OVRMediaServiceContract.BROADCAST_CAPTURE_ABUSE_STATUS, false);
        } else if (this.mWebRTCCapture.A00(this.mPackageName, null)) {
            String A01 = this.mAbuseAVCapture.A01(this.mPackageName, null);
            if (A01 != null) {
                z = true;
            }
            bundle.putBoolean(OVRMediaServiceContract.BROADCAST_CAPTURE_ABUSE_STATUS, z);
            if (z) {
                bundle.putString("recording_uuid", A01);
                return bundle;
            }
        }
        return bundle;
    }

    public final Bundle A0M(JSONObject jSONObject) {
        A05(this);
        Bundle bundle = new Bundle();
        boolean z = false;
        if (TextUtils.isEmpty(this.mPackageName)) {
            bundle.putBoolean(OVRMediaServiceContract.BROADCAST_CAPTURE_ABUSE_STATUS, false);
        } else if (this.mWebRTCCapture.A00(this.mPackageName, null)) {
            String A01 = this.mAbuseAVCapture.A01(this.mPackageName, jSONObject);
            if (A01 != null) {
                z = true;
            }
            bundle.putBoolean(OVRMediaServiceContract.BROADCAST_CAPTURE_ABUSE_STATUS, z);
            if (z) {
                this.mAbuseAVCapture.A02(this.mPackageName);
                bundle.putString("recording_uuid", A01);
                return bundle;
            }
        }
        return bundle;
    }

    @Nullable
    public final String A0O(String str) {
        String obj = UUID.randomUUID().toString();
        try {
            File A00 = AbuseReportFileUtils.A00(this.mContext, obj);
            AbuseReportFileUtils.A04(A00);
            File file = new File(A00, AbuseReportFileUtils.VIDEO_RECORDING_FILE);
            File file2 = new File(str);
            FileInputStream fileInputStream = new FileInputStream(file2);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read < 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.flush();
                try {
                    fileOutputStream.getFD().sync();
                    fileOutputStream.close();
                    if (file2.length() != file.length()) {
                        file2.length();
                        file.length();
                        return null;
                    }
                    AbuseReportFileUtils.A02(this.mContext, obj);
                    return obj;
                } catch (IOException unused) {
                    throw new IOException("IOException when getting file from URI:\" + contentURIFilePath");
                }
            } catch (Throwable th) {
                fileOutputStream.flush();
                try {
                    fileOutputStream.getFD().sync();
                    fileOutputStream.close();
                    throw th;
                } catch (IOException unused2) {
                    throw new IOException("IOException when getting file from URI:\" + contentURIFilePath");
                }
            }
        } catch (IOException e) {
            AnonymousClass0NO.A0B(TAG, "Failed to copy media file for abuse report.", e);
            return null;
        }
    }

    public final void A0T(Intent intent, CastHTTPServerBase.StartSource startSource, Boolean bool) {
        CastHTTPServerBase castHTTPServerBase;
        Object obj;
        A05(this);
        if (!bool.booleanValue()) {
            this.mCastHTTPServerManager.A00(CastHTTPServerManager.CastServerType.MOBILE_DEVICE, intent, startSource);
            this.mExternalPlatformLocal.checkAppMediaCapabilityGranted(this.mPackageName);
            return;
        }
        Pair<String, String> A00 = this.mCastHTTPServerManager.A00(CastHTTPServerManager.CastServerType.WEB, intent, startSource);
        CastHTTPServerManager castHTTPServerManager = this.mCastHTTPServerManager;
        synchronized (castHTTPServerManager) {
            castHTTPServerBase = castHTTPServerManager.mCastHTTPServer;
        }
        final CastHTTPServerForWeb castHTTPServerForWeb = (CastHTTPServerForWeb) castHTTPServerBase;
        if (castHTTPServerForWeb != null && (obj = A00.first) != null && !((String) obj).isEmpty()) {
            this.mCastToBrowserStatus = CastToBrowserStatus.INITIATED;
            Handler handler = this.mCastWWWAnswerPoller;
            if (handler != null) {
                handler.removeCallbacksAndMessages(null);
            }
            this.mPollCounter = 0;
            this.mCastWWWAnswerPoller = new Handler();
            this.mApiRequestManager.post(new SetCastOfferRequest((String) A00.second), new ApiCallback<SetCastOfferResponse>() {
                /* class com.oculus.horizon.service_media.OVRMediaServiceManager.AnonymousClass4 */

                @Override // com.oculus.http.core.base.ApiCallback
                public final void onError(ApiError apiError) {
                    AnonymousClass0NO.A0E(OVRMediaServiceManager.TAG, "failed to set cast offer: %s", apiError.getMessage());
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                @Override // com.oculus.http.core.base.ApiCallback
                public final void onResponse(SetCastOfferResponse setCastOfferResponse) {
                    String str;
                    String str2;
                    SetCastOfferResponse setCastOfferResponse2 = setCastOfferResponse;
                    SetCastOfferResponse.CastSessionHandshake castSessionHandshake = setCastOfferResponse2.mCastSessionHandshake;
                    if (castSessionHandshake == null || castSessionHandshake.id == null) {
                        str = OVRMediaServiceManager.TAG;
                        str2 = "Unexpected null CastSessionHandshake ID from response";
                    } else {
                        OVRMediaServiceManager oVRMediaServiceManager = OVRMediaServiceManager.this;
                        String str3 = castSessionHandshake.pin;
                        Intent intent = new Intent(OVRMediaServiceContract.BROADCAST_CAST_WWW_OFFER_SET);
                        intent.setPackage("com.oculus.systemux");
                        intent.putExtra("pin", str3);
                        C02600ao.A00().A07(C02880bg.A02(C02780bN.A1F, new HashSet(Arrays.asList("com.oculus.systemux")))).A03(intent, oVRMediaServiceManager.mContext);
                        OVRMediaServiceManager oVRMediaServiceManager2 = OVRMediaServiceManager.this;
                        String str4 = setCastOfferResponse2.mCastSessionHandshake.id;
                        oVRMediaServiceManager2.mCastSessionHandshakeId = str4;
                        Handler handler = oVRMediaServiceManager2.mCastWWWAnswerPoller;
                        if (handler != null) {
                            oVRMediaServiceManager2.mCastToBrowserStatus = CastToBrowserStatus.POLLING_WWW_ANSWER;
                            handler.post(new Runnable(str4, castHTTPServerForWeb) {
                                /* class com.oculus.horizon.service_media.OVRMediaServiceManager.AnonymousClass5 */
                                public final /* synthetic */ CastHTTPServerForWeb val$castHTTPServerForWeb;
                                public final /* synthetic */ String val$id;

                                {
                                    this.val$id = r2;
                                    this.val$castHTTPServerForWeb = r3;
                                }

                                public final void run() {
                                    OVRMediaServiceManager.this.mApiRequestManager.post(new GetCastAnswerRequest(this.val$id), new ApiCallback<GetCastAnswerResponse>() {
                                        /* class com.oculus.horizon.service_media.OVRMediaServiceManager.AnonymousClass5.AnonymousClass1 */

                                        @Override // com.oculus.http.core.base.ApiCallback
                                        public final void onError(ApiError apiError) {
                                            AnonymousClass0NO.A0E(OVRMediaServiceManager.TAG, "Failed to get cast answer: %s", apiError.getMessage());
                                        }

                                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                                        @Override // com.oculus.http.core.base.ApiCallback
                                        public final void onResponse(GetCastAnswerResponse getCastAnswerResponse) {
                                            GetCastAnswerResponse getCastAnswerResponse2 = getCastAnswerResponse;
                                            WebRTCHandshake webRTCHandshake = getCastAnswerResponse2.cast_session_handshake;
                                            if (webRTCHandshake == null) {
                                                AnonymousClass0NO.A08(OVRMediaServiceManager.TAG, "Received null cast_session_handshake");
                                            } else if (!webRTCHandshake.answer.isEmpty()) {
                                                OVRMediaServiceManager.this.mContext.sendBroadcast(new Intent(OVRMediaServiceContract.BROADCAST_CAST_WWW_ANSWER_RECEIVED));
                                                AnonymousClass5 r2 = AnonymousClass5.this;
                                                OVRMediaServiceManager.this.mCastToBrowserStatus = CastToBrowserStatus.CASTING_TO_BROWSER;
                                                CastHTTPServerForWeb castHTTPServerForWeb = r2.val$castHTTPServerForWeb;
                                                String str = getCastAnswerResponse2.cast_session_handshake.answer;
                                                synchronized (castHTTPServerForWeb.mLock) {
                                                    try {
                                                        castHTTPServerForWeb.mAnswerJSONString = new JSONObject().put(CastHTTPServerBase.OVRPLATFORM_KEY_ANSWER_SDP, str).put(CastHTTPServerBase.OVRPLATFORM_KEY_RTC_CONNECTION_ID, "").put(CastHTTPServerBase.OVRPLATFORM_KEY_RTC_SESSION_ID, castHTTPServerForWeb.mSessionId).toString();
                                                    } catch (JSONException e) {
                                                        castHTTPServerForWeb.mCastAnalytics.A06(castHTTPServerForWeb.mSessionId, e.getMessage(), true);
                                                        AnonymousClass0NO.A0B(CastHTTPServerForWeb.TAG, "Failed to create answer json object", e);
                                                    }
                                                    CountDownLatch countDownLatch = castHTTPServerForWeb.mOfferAnswerFence;
                                                    if (countDownLatch != null) {
                                                        countDownLatch.countDown();
                                                    }
                                                    CastAnalytics castAnalytics = castHTTPServerForWeb.mCastAnalytics;
                                                    String str2 = castHTTPServerForWeb.mSessionId;
                                                    castAnalytics.A05(str2, str2, true);
                                                }
                                                Handler handler = OVRMediaServiceManager.this.mCastWWWAnswerPoller;
                                                if (handler != null) {
                                                    handler.removeCallbacksAndMessages(null);
                                                }
                                            }
                                        }
                                    });
                                    OVRMediaServiceManager oVRMediaServiceManager = OVRMediaServiceManager.this;
                                    int i = oVRMediaServiceManager.mPollCounter + 1;
                                    oVRMediaServiceManager.mPollCounter = i;
                                    Handler handler = oVRMediaServiceManager.mCastWWWAnswerPoller;
                                    if (handler == null) {
                                        return;
                                    }
                                    if (i < 20) {
                                        handler.postDelayed(this, 1000);
                                        return;
                                    }
                                    oVRMediaServiceManager.mCastToBrowserStatus = CastToBrowserStatus.TIME_OUT;
                                    handler.removeCallbacksAndMessages(null);
                                }
                            });
                            return;
                        }
                        str = OVRMediaServiceManager.TAG;
                        str2 = "Unexpected null mCastWWWAnswerPoller";
                    }
                    AnonymousClass0NO.A08(str, str2);
                }
            });
        }
    }

    @Override // com.oculus.horizon.media_session.MediaSessionCallback
    public final void A6G() {
        A0P();
    }
}
