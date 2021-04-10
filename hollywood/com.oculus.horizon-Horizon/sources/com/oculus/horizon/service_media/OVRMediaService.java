package com.oculus.horizon.service_media;

import X.AnonymousClass0J2;
import X.AnonymousClass0QC;
import X.AnonymousClass0b4;
import X.AnonymousClass0bQ;
import X.AnonymousClass0bU;
import X.AnonymousClass117;
import X.C02780bN;
import X.C02790bO;
import X.C02870bf;
import X.C02880bg;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentCallbacks2;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import com.facebook.infer.annotation.Initializer;
import com.facebook.ultralight.Eager;
import com.oculus.aidl.OVRMediaServiceInterface;
import com.oculus.appmanager.verifier.TrustedAppVerifier;
import com.oculus.common.build.BuildConstants;
import com.oculus.horizon.R;
import com.oculus.horizon.media_session.MediaSessionLifecycleManager;
import com.oculus.horizon.platform.PresenceManager;
import com.oculus.horizon.platformplugin.PlatformPluginManager;
import com.oculus.horizon.service.CallerUtils;
import com.oculus.horizon.service_media.AppSwitchManager;
import com.oculus.horizon.service_media.ForegroundAppChecker;
import com.oculus.horizon.service_media.contract.OVRMediaServiceContract;
import com.oculus.horizon.service_media.vrcast.VrCastServicePackageVerifier;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.os.SettingsManager;
import com.oculus.security.basecomponent.OculusPublicService;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import javax.annotation.Nullable;
import javax.inject.Inject;

public class OVRMediaService extends OculusPublicService implements ForegroundServiceInterface, ComponentCallbacks2 {
    public static final String ACCEPT_LIVESTREAM_AUTOMATION_INTENTS_KEY = "ovr.livestream.accept_automation_intents";
    public static final int NOTIFICATION_ID = 135234;
    public static final String TAG = "OVRMediaService";
    public static final C02870bf mOculusAssistantTrustedApp = C02880bg.A02(new HashSet(Collections.singleton(C02780bN.A04)), new HashSet(Collections.singleton(BuildConstants.PACKAGE_NAME_ASSISTANT)));
    public static final C02870bf mShellHomeTrustedApp;
    public static final C02870bf mSocialPlatformTrustedApp;
    public static final C02870bf mSystemDriverTrustedApp;
    public static final C02870bf mSystemUXTrustedApp = C02880bg.A02(new HashSet(Collections.singleton(C02780bN.A0M)), new HashSet(Collections.singleton("com.oculus.systemux")));
    public static final C02870bf mVrShellTrustedApp;
    public AnonymousClass0QC _UL_mInjectionContext;
    public final AppSwitchManager.AppSwitchListener mAppSwitchListener = new AppSwitchManager.AppSwitchListener() {
        /* class com.oculus.horizon.service_media.OVRMediaService.AnonymousClass1 */

        @Override // com.oculus.horizon.service_media.AppSwitchManager.AppSwitchListener
        public final void onAppSwitch(String str) {
            OVRMediaService oVRMediaService = OVRMediaService.this;
            oVRMediaService.mOVRMediaServiceManager.mPackageName = str;
            oVRMediaService.mAppSwitchManager.A01(str);
        }

        @Override // com.oculus.horizon.service_media.AppSwitchManager.AppSwitchListener
        public final void onForegroundAppUpdate() {
            AppSwitchManager appSwitchManager = OVRMediaService.this.mAppSwitchManager;
            appSwitchManager.A01(appSwitchManager.mPackageName);
        }
    };
    @Inject
    @Eager
    public AppSwitchManager mAppSwitchManager;
    public final OVRMediaServiceInterface.Stub mBinder = new OVRMediaServiceInterface.Stub() {
        /* class com.oculus.horizon.service_media.OVRMediaService.AnonymousClass2 */

        /* JADX WARNING: Code restructure failed: missing block: B:22:0x006a, code lost:
            if ("com.oculus.socialplatform".equals(r6) != false) goto L_0x006c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x006e, code lost:
            if (r2 == false) goto L_0x0070;
         */
        @Override // com.oculus.aidl.OVRMediaServiceInterface
        @javax.annotation.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final android.os.Bundle sendCommandToMediaService(android.os.Bundle r15) throws android.os.RemoteException {
            /*
            // Method dump skipped, instructions count: 1344
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.service_media.OVRMediaService.AnonymousClass2.sendCommandToMediaService(android.os.Bundle):android.os.Bundle");
        }

        @Override // com.oculus.aidl.OVRMediaServiceInterface
        public final Bundle sendLivestreamingRawPCMData(Bundle bundle) {
            Throwable th;
            AudioCapture audioCapture;
            OVRMediaService oVRMediaService = OVRMediaService.this;
            AppSwitchManager appSwitchManager = oVRMediaService.mAppSwitchManager;
            short[] shortArray = bundle.getShortArray("pcm_data");
            int i = bundle.getInt("num_channels", 2);
            int i2 = bundle.getInt("samples_per_second", 48000);
            int i3 = bundle.getInt("bits_per_sample", 16);
            float f = bundle.getFloat("volume_float", 1.0f);
            long j = bundle.getLong("track_id", 0);
            int callingUid = OVRMediaServiceInterface.Stub.getCallingUid();
            Integer A00 = ForegroundAppChecker.A00(oVRMediaService, appSwitchManager.mPackageName);
            if (A00 == null) {
                return null;
            }
            if (!A00.equals(Integer.valueOf(callingUid)) && ((!((ForegroundAppChecker) AnonymousClass0J2.A03(0, 192, appSwitchManager._UL_mInjectionContext)).A02(callingUid) || !((ForegroundAppChecker) AnonymousClass0J2.A03(0, 192, appSwitchManager._UL_mInjectionContext)).A02(A00.intValue())) && !((ForegroundAppChecker) AnonymousClass0J2.A03(0, 192, appSwitchManager._UL_mInjectionContext)).A01().booleanValue())) {
                return null;
            }
            OVRMediaServiceManager oVRMediaServiceManager = appSwitchManager.mOVRMediaServiceManager;
            synchronized (oVRMediaServiceManager) {
                FileCapture fileCapture = oVRMediaServiceManager.mFileCapture;
                synchronized (fileCapture.mLock) {
                    try {
                        audioCapture = fileCapture.mAudioCapture;
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                }
                if (audioCapture != null) {
                    synchronized (audioCapture.mLock) {
                        try {
                            PlatformPluginManager.nativeCapturedAudioRenderer_StorePCM(audioCapture.mNativeAudioRenderer, shortArray, shortArray.length, i2, i, f, j, i3);
                        } catch (Throwable th3) {
                            th = th3;
                            throw th;
                        }
                    }
                }
                if (oVRMediaServiceManager.A0c() && !oVRMediaServiceManager.mLivestreamingManager.mIsPaused && (oVRMediaServiceManager.mForegroundAppChecker.A03(oVRMediaServiceManager.mPackageName) || oVRMediaServiceManager.mForegroundAppChecker.A01().booleanValue())) {
                    oVRMediaServiceManager.mPlatformPluginManager.A06(oVRMediaServiceManager);
                    PlatformPluginManager.nativeSetCapturedPCM(shortArray, i2, i, f, j, i3);
                }
            }
            return null;
        }
    };
    @Inject
    @Eager
    public CallerUtils mCallerUtils;
    @Inject
    @Eager
    public ForegroundAppChecker mForegroundAppChecker;
    @Inject
    @Eager
    public HorizonCallerPackageVerifier mHorizonPackageVerifier;
    public OVRMediaServiceManager mOVRMediaServiceManager;
    @Inject
    @Eager
    public OVRMediaServiceManagerProvider mOVRMediaServiceManagerProvider;
    @Inject
    @Eager
    public PresenceManager mPresenceManager;
    public SettingsManager mSettingsManager;
    @Inject
    @Eager
    public TrustedAppVerifier mTrustedAppVerifier;
    @Inject
    @Eager
    public VrCastServicePackageVerifier mVrCastServicePackageVerifier;

    public class PresenceManagerListener implements PresenceManager.Listener {
        @Nullable
        public String mPackageName = null;

        @Override // com.oculus.horizon.platform.PresenceManager.Listener
        public final void onHeartbeatFailed() {
        }

        public PresenceManagerListener() {
        }

        @Override // com.oculus.horizon.platform.PresenceManager.Listener
        public final void onHeartbeat() {
            OVRMediaService oVRMediaService = OVRMediaService.this;
            String str = oVRMediaService.mPresenceManager.mPanelAppPackageName;
            if (str != null && !str.equals(this.mPackageName)) {
                this.mPackageName = str;
                OVRMediaServiceManager oVRMediaServiceManager = oVRMediaService.mOVRMediaServiceManager;
                OVRMediaServiceManager.A0B(oVRMediaServiceManager, str, oVRMediaServiceManager.mExternalPlatformLocal.checkAppMediaCapabilityGranted(str));
            }
        }
    }

    public static boolean A02(Context context, @Nullable C02790bO r4, String str, C02870bf r6) {
        String A01;
        if (!(r4 == null || (A01 = r4.A01()) == null || !str.equals(A01))) {
            if (!C02870bf.A03(AnonymousClass0bU.A03(context, A01))) {
                return r6.A07(C02870bf.A00(r4.A00, context), context);
            }
            if (!Build.FINGERPRINT.endsWith("/release-keys") || ((context.getApplicationInfo().flags & 2) != 0 && C02870bf.A03(AnonymousClass0bU.A03(context, context.getPackageName())))) {
                return true;
            }
        }
        return false;
    }

    @Override // com.oculus.horizon.service_media.ForegroundServiceInterface
    public final void A9d() {
        stopForeground(true);
        stopSelf();
    }

    static {
        AnonymousClass0bQ r4 = C02780bN.A0L;
        mVrShellTrustedApp = C02880bg.A02(new HashSet(Collections.singleton(r4)), new HashSet(Arrays.asList("com.oculus.vrshell", "com.oculus.shellenv")));
        mShellHomeTrustedApp = C02880bg.A02(new HashSet(Collections.singleton(r4)), new HashSet(Collections.singleton("com.oculus.vrshell.home")));
        mSocialPlatformTrustedApp = C02880bg.A02(new HashSet(Collections.singleton(r4)), new HashSet(Collections.singleton("com.oculus.socialplatform")));
        mSystemDriverTrustedApp = C02880bg.A02(new HashSet(Collections.singleton(r4)), new HashSet(Collections.singleton("com.oculus.systemdriver")));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0073, code lost:
        if (r4 == null) goto L_0x0077;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A00(android.content.Intent r10, java.lang.String r11) {
        /*
        // Method dump skipped, instructions count: 262
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.service_media.OVRMediaService.A00(android.content.Intent, java.lang.String):void");
    }

    private boolean A01() {
        if (!this.mOVRMediaServiceManager.A0d()) {
            return true;
        }
        OVRMediaServiceNotification oVRMediaServiceNotification = (OVRMediaServiceNotification) AnonymousClass0J2.A03(1, 149, this._UL_mInjectionContext);
        OVRMediaServiceNotification.A03(oVRMediaServiceNotification, oVRMediaServiceNotification.mContext.getString(R.string.capture_disabled_title), null);
        return false;
    }

    @Override // com.oculus.horizon.service_media.ForegroundServiceInterface
    public final void A5V() {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(this, OVRMediaService.class));
        intent.putExtra("message_type", OVRMediaServiceContract.KILL_SERVICE);
        AnonymousClass0b4 r3 = new AnonymousClass0b4();
        r3.A04(intent, getClassLoader());
        startForeground(NOTIFICATION_ID, new Notification.Builder(this).setSmallIcon(R.drawable.app_icon).setContentTitle(getString(R.string.livestreaming_notification_title)).setContentText(getString(R.string.livestreaming_notification_subtitle)).setContentIntent(PendingIntent.getService(this, 0, AnonymousClass0b4.A01(r3, this), AnonymousClass0b4.A00(r3, 0))).build());
    }

    public final void onTrimMemory(int i) {
        OVRMediaServiceManager oVRMediaServiceManager = this.mOVRMediaServiceManager;
        if (oVRMediaServiceManager != null && i == 15 && oVRMediaServiceManager.A0b()) {
            OVRMediaServiceNotification oVRMediaServiceNotification = oVRMediaServiceManager.mOVRMediaServiceNotification;
            OVRMediaServiceNotification.A03(oVRMediaServiceNotification, oVRMediaServiceNotification.mContext.getString(R.string.instant_replay_stopped_low_memory_title), null);
            Event A22 = ((EventManager) AnonymousClass0J2.A03(0, 242, oVRMediaServiceManager._UL_mInjectionContext)).A22(OVRMediaServiceContract.INSTANT_REPLAY_STOPPED_LOW_MEMORY);
            String str = oVRMediaServiceManager.mPackageName;
            if (str != null) {
                A22.A15(OVRMediaServiceContract.CONTENT_PACKAGE_NAME, str);
            }
            A22.A5L();
            oVRMediaServiceManager.A0a(true);
        }
    }

    @Override // X.AnonymousClass1U9, com.oculus.security.basecomponent.OculusPublicService
    @Initializer
    public final void doCreate() {
        super.doCreate();
        AnonymousClass0J2 r2 = AnonymousClass0J2.get(this);
        this._UL_mInjectionContext = new AnonymousClass0QC(2, r2);
        this.mOVRMediaServiceManagerProvider = (OVRMediaServiceManagerProvider) AnonymousClass117.A00(427, r2);
        this.mCallerUtils = CallerUtils._UL__ULSEP_com_oculus_horizon_service_CallerUtils_ULSEP_ACCESS_METHOD(r2);
        this.mVrCastServicePackageVerifier = VrCastServicePackageVerifier._UL__ULSEP_com_oculus_horizon_service_ULUNDERSCORE_media_vrcast_VrCastServicePackageVerifier_ULSEP_ACCESS_METHOD(r2);
        this.mTrustedAppVerifier = (TrustedAppVerifier) AnonymousClass117.A00(501, r2);
        this.mHorizonPackageVerifier = (HorizonCallerPackageVerifier) AnonymousClass117.A00(166, r2);
        this.mAppSwitchManager = (AppSwitchManager) AnonymousClass117.A00(471, r2);
        this.mForegroundAppChecker = (ForegroundAppChecker) AnonymousClass117.A00(192, r2);
        this.mPresenceManager = PresenceManager._UL__ULSEP_com_oculus_horizon_platform_PresenceManager_ULSEP_ACCESS_METHOD(r2);
        OVRMediaServiceManager oVRMediaServiceManager = new OVRMediaServiceManager(this.mOVRMediaServiceManagerProvider, this);
        this.mOVRMediaServiceManager = oVRMediaServiceManager;
        ((MediaSessionLifecycleManager) AnonymousClass0J2.A03(1, 99, oVRMediaServiceManager._UL_mInjectionContext)).A01(oVRMediaServiceManager);
        this.mOVRMediaServiceManager.A0R();
        ForegroundAppChecker foregroundAppChecker = this.mForegroundAppChecker;
        foregroundAppChecker.mAppSwitchListener = this.mAppSwitchListener;
        if (foregroundAppChecker.mTopWindowManager == null) {
            ForegroundAppChecker.TopWindowManager topWindowManager = new ForegroundAppChecker.TopWindowManager();
            foregroundAppChecker.mTopWindowManager = topWindowManager;
            topWindowManager.A00();
        }
        AppSwitchManager appSwitchManager = this.mAppSwitchManager;
        OVRMediaServiceManager oVRMediaServiceManager2 = this.mOVRMediaServiceManager;
        appSwitchManager.mOVRMediaServiceManager = oVRMediaServiceManager2;
        oVRMediaServiceManager2.A0a(true);
        SettingsManager settingsManager = new SettingsManager();
        this.mSettingsManager = settingsManager;
        this.mAppSwitchManager.A00(settingsManager, this);
        this.mPresenceManager.registerListener(new PresenceManagerListener());
    }

    @Override // X.AnonymousClass1U9
    public final void doDestroy() {
        super.doDestroy();
        OVRMediaServiceManager oVRMediaServiceManager = this.mOVRMediaServiceManager;
        ((MediaSessionLifecycleManager) AnonymousClass0J2.A03(1, 99, oVRMediaServiceManager._UL_mInjectionContext)).A02(oVRMediaServiceManager);
    }

    @Override // X.AnonymousClass1U9
    public final IBinder doBind(Intent intent) {
        return this.mBinder;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:164:0x034e */
    /* JADX DEBUG: Multi-variable search result rejected for r3v5, resolved type: com.oculus.horizon.service_media.OVRMediaServiceManager */
    /* JADX DEBUG: Multi-variable search result rejected for r0v58, resolved type: android.os.Handler */
    /* JADX DEBUG: Multi-variable search result rejected for r1v24, resolved type: com.oculus.horizon.cast.CastHTTPServerManager */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v19, types: [com.oculus.horizon.service_media.ForegroundAppChecker$TopWindowManager, java.lang.Object, android.content.Intent, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v23, types: [android.os.Bundle] */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v47 */
    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Removed duplicated region for block: B:227:0x04f6  */
    /* JADX WARNING: Removed duplicated region for block: B:228:0x04f7  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x019e  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01b0  */
    /* JADX WARNING: Unknown variable types count: 1 */
    @Override // X.AnonymousClass1U9
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int doStartCommand(final android.content.Intent r30, int r31, int r32) {
        /*
        // Method dump skipped, instructions count: 1902
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.service_media.OVRMediaService.doStartCommand(android.content.Intent, int, int):int");
    }
}
