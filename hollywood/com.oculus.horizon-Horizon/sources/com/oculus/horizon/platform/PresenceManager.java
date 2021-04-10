package com.oculus.horizon.platform;

import X.AbstractC06640p5;
import X.AnonymousClass0NO;
import X.AnonymousClass0Pi;
import X.AnonymousClass0QC;
import X.AnonymousClass0p1;
import X.AnonymousClass0p2;
import X.AnonymousClass117;
import X.C003108z;
import X.C01010Iv;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.WorkerThread;
import com.facebook.inject.ApplicationScoped;
import com.facebook.inject.ForAppContext;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.google.common.base.Preconditions;
import com.google.common.collect.HashBiMap;
import com.oculus.horizon.api.platformsdk.HeartbeatResponse;
import com.oculus.horizon.platformplugin.PlatformPluginManager;
import com.oculus.horizon.profile.UserProfileHelper;
import com.oculus.horizon.vr_lifecycle.VRLifecycleSessionManager;
import com.oculus.http.core.ApiModule;
import com.oculus.http.core.annotations.OculusRestAdapter;
import com.oculus.http.core.common.DsatAuthorization;
import com.oculus.library.model.App;
import com.oculus.libraryapi.OVRLibrary;
import com.oculus.libraryapi.OVRLibraryModule;
import com.oculus.partystatemanager.PartyStateManager;
import com.oculus.processtokentracker.ProcessTokenTracker;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Provider;
import retrofit.RestAdapter;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Headers;
import retrofit.http.POST;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_retrofit_RestAdapter_ULSEP_com_oculus_http_core_annotations_OculusRestAdapter_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_platform_PlatformContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_platformplugin_PlatformPluginManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_profile_UserProfileHelper_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_libraryapi_OVRLibrary_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_horizon_vr_ULUNDERSCORE_lifecycle_VRLifecycleSessionManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID"})
@ApplicationScoped
public class PresenceManager implements Runnable, ProcessTokenTracker.ProcessListener {
    public static final String ACTION_ENTITLEMENT_SHARING_WARNING = "com.oculus.vrshell.intent.action.ENTITLEMENT_SHARING_WARNING";
    public static final long DEFAULT_HEARTBEAT_TIMEOUT_SECONDS = 30;
    public static final String DEFAULT_LAUNCH_TYPE = "UNKNOWN";
    public static final HashSet<String> DO_NOT_HEARTBEAT_AS_THESE_SERVICES = new HashSet<>(Arrays.asList("com.oculus.panelapp.dialog.DialogPanelService", "com.oculus.vrshell.home.SocialService", "com.oculus.socialplatform.SocialService", "com.oculus.vrshell.home.SystemUtilitiesService", "com.oculus.vrshell.panels.AnytimeUI.AnytimeUIAndroidPanelService", "com.oculus.panelapp.anytimeui.AnytimeUIAndroidPanelService", "com.oculus.vrshell.home.SocialDialogsService", "com.oculus.socialplatform.SocialDialogsService"));
    public static final long LOG_TIMEOUT_MILLIS = 70000;
    public static final String TAG = "PresenceManager";
    public static volatile PresenceManager _UL__ULSEP_com_oculus_horizon_platform_PresenceManager_ULSEP_INSTANCE;
    public AnonymousClass0QC _UL_mInjectionContext;
    public AnonymousClass0p2<String, String> mAppIDtoPackageName;
    public final Context mContext;
    public boolean mIsHorizonInForeground = false;
    public boolean mIsLivestreaming = false;
    public boolean mIsMessengerAuthenticatedOnDevice = false;
    @Nullable
    public Boolean mIsUnlocked = null;
    public String mLastLogAppID;
    public long mLastLogElapsedTime;
    @Nullable
    public Long mLastReportedUnlockTime = null;
    @Nullable
    public Long mLastUnlockTime = null;
    public OVRLibrary mLibrary;
    public final List<Listener> mListeners = new CopyOnWriteArrayList();
    public boolean mLivestreamingCommentsVisible = false;
    public final Object mLock = new Object();
    public final Object mLockStateMutex = new Object();
    public final Methods mMethods;
    @Nullable
    public String mPanelAppPackageName;
    public final PlatformContext mPlatformContext;
    public final PlatformPluginManager mPlatformPluginManager;
    public Thread mPresenceThread;
    public long mPresenceThreadStartTime = 0;
    public HashMap<String, Pair<HashSet<Integer>, String>> mRichPresenceJSONMap;
    public final AnonymousClass0p1<VRLifecycleSessionManager> mSessionManagerLazy;
    public final UserProfileHelper mUserProfileHelper;

    public interface Listener {
        @WorkerThread
        void onHeartbeat();

        @WorkerThread
        void onHeartbeatFailed();
    }

    public interface Methods {
        @POST("/user_heartbeat?current_status=ONLINE")
        @Headers({DsatAuthorization.ENABLE})
        @FormUrlEncoded
        HeartbeatResponse heartbeatForUser(@Field("app_id_override") String str, @Field("launch_type") String str2, @Field("push_token") String str3, @Field("panel_package_name") String str4, @Field("is_livestreaming") String str5, @Field("livestreaming_comments_visible") String str6, @Field("is_messenger_enabled") String str7, @Field("rich_presence_options") String str8, @Nullable @Field("is_unlocked") Boolean bool, @Nullable @Field("seconds_since_unlock") Integer num);
    }

    public void onHeadsetDisconnected() {
        this.mLastLogAppID = null;
        this.mLastLogElapsedTime = 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:102:0x017b, code lost:
        return new android.util.Pair<>(null, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x017c, code lost:
        r1 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x017d, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0192, code lost:
        return new android.util.Pair<>(null, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x0193, code lost:
        X.AnonymousClass0NO.A0B(com.oculus.horizon.platform.PresenceManager.TAG, "Heartbeat error.", r1);
        r6 = new com.oculus.http.core.base.ApiError(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x01a6, code lost:
        if (r6.A01(com.oculus.http.core.base.ApiErrorCodes.ERROR_INVALID_RICH_PRESENCE_ARGS) != false) goto L_0x01a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x01a8, code lost:
        r6 = A01(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x01b8, code lost:
        r28.mRichPresenceJSONMap.put(r6, new android.util.Pair<>(r0.first, ""));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x01c6, code lost:
        r1 = r28.mListeners.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x01d0, code lost:
        if (r1.hasNext() != false) goto L_0x01d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x01d2, code lost:
        r1.next().onHeartbeatFailed();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x01e3, code lost:
        if (r6.A01(com.oculus.http.core.base.ApiErrorCodes.ERROR_SUBCODE_TOO_MANY_IN_USE_DEVICES) == false) goto L_0x0206;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:?, code lost:
        new com.oculus.os.LockscreenManager(r28.mContext).showTooManyDevicesScreen();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x01f7, code lost:
        monitor-enter(r28.mLockStateMutex);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:?, code lost:
        r28.mIsUnlocked = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x01ff, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0200, code lost:
        X.AnonymousClass0NO.A0B(com.oculus.horizon.platform.PresenceManager.TAG, "Error showing lockscreen", r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x020d, code lost:
        if (r6.A01(com.oculus.http.core.base.ApiErrorCodes.ERROR_SUBCODE_ENTITLEMENT_USE_LIMIT_EXCEEDED) != false) goto L_0x020f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x020f, code lost:
        r1 = new android.content.Intent(com.oculus.horizon.platform.PresenceManager.ACTION_ENTITLEMENT_SHARING_WARNING);
        r1.setPackage("com.oculus.vrshell");
        r28.mContext.sendBroadcast(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004a, code lost:
        if (r1 >= 0) goto L_0x004c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x016c, code lost:
        r1 = e;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:101:? A[ExcHandler: InterruptedException (unused java.lang.InterruptedException), SYNTHETIC, Splitter:B:10:0x0025] */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0188  */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0193  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.util.Pair<com.oculus.horizon.api.platformsdk.HeartbeatResponse, java.lang.Boolean> A00() {
        /*
        // Method dump skipped, instructions count: 551
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.platform.PresenceManager.A00():android.util.Pair");
    }

    @Nullable
    private String A01(String str) {
        if (this.mAppIDtoPackageName.containsKey(str)) {
            return this.mAppIDtoPackageName.get(str);
        }
        App A02 = this.mLibrary.A02(str);
        if (A02 == null) {
            return null;
        }
        String str2 = A02.packageName;
        this.mAppIDtoPackageName.A2r(str, str2);
        return str2;
    }

    private boolean A02() {
        String str;
        String str2;
        KeyguardManager keyguardManager = (KeyguardManager) this.mContext.getSystemService("keyguard");
        if (keyguardManager == null) {
            str = TAG;
            str2 = "keyguardManager is null";
        } else {
            if (!keyguardManager.inKeyguardRestrictedInputMode()) {
                PowerManager powerManager = (PowerManager) this.mContext.getSystemService("power");
                if (powerManager != null) {
                    return powerManager.isInteractive();
                }
                str = TAG;
                str2 = "PowerManager is null";
            }
            return false;
        }
        AnonymousClass0NO.A08(str, str2);
        return false;
    }

    private boolean A03(String str, @Nullable String str2) {
        Thread thread;
        boolean z = true;
        boolean z2 = !TextUtils.equals(str, this.mLastLogAppID);
        boolean z3 = !TextUtils.equals(str2, this.mPanelAppPackageName);
        if (!z2 && !z3) {
            z = false;
        }
        this.mLastLogAppID = str;
        this.mPanelAppPackageName = str2;
        this.mSessionManagerLazy.get().A03(str2);
        if (z && (thread = this.mPresenceThread) != null) {
            thread.interrupt();
        }
        synchronized (this.mLock) {
            this.mLock.notifyAll();
        }
        return z;
    }

    @AutoGeneratedAccessMethod
    public static final AnonymousClass0p1 _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_horizon_platform_PresenceManager_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01010Iv(91, r2);
    }

    @AutoGeneratedAccessMethod
    public static final PresenceManager _UL__ULSEP_com_oculus_horizon_platform_PresenceManager_ULSEP_ACCESS_METHOD(AbstractC06640p5 r1) {
        return (PresenceManager) AnonymousClass117.A00(91, r1);
    }

    @AutoGeneratedFactoryMethod
    public static final PresenceManager _UL__ULSEP_com_oculus_horizon_platform_PresenceManager_ULSEP_FACTORY_METHOD(AbstractC06640p5 r11) {
        if (_UL__ULSEP_com_oculus_horizon_platform_PresenceManager_ULSEP_INSTANCE == null) {
            synchronized (PresenceManager.class) {
                AnonymousClass0Pi A00 = AnonymousClass0Pi.A00(_UL__ULSEP_com_oculus_horizon_platform_PresenceManager_ULSEP_INSTANCE, r11);
                if (A00 != null) {
                    try {
                        AbstractC06640p5 applicationInjector = r11.getApplicationInjector();
                        _UL__ULSEP_com_oculus_horizon_platform_PresenceManager_ULSEP_INSTANCE = new PresenceManager(applicationInjector, C003108z.A00(applicationInjector), ApiModule.A0C(applicationInjector), PlatformContext._UL__ULSEP_com_oculus_horizon_platform_PlatformContext_ULSEP_ACCESS_METHOD(applicationInjector), (PlatformPluginManager) AnonymousClass117.A00(160, applicationInjector), (UserProfileHelper) AnonymousClass117.A00(68, applicationInjector), OVRLibraryModule.A00(applicationInjector), new C01010Iv(8, applicationInjector));
                    } finally {
                        A00.A01();
                    }
                }
            }
        }
        return _UL__ULSEP_com_oculus_horizon_platform_PresenceManager_ULSEP_INSTANCE;
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_horizon_platform_PresenceManager_ULGT__ULSEP_ACCESS_METHOD(AbstractC06640p5 r2) {
        return new C01010Iv(91, r2);
    }

    public void addAppIdToPackageName(String str, String str2) {
        if (!this.mAppIDtoPackageName.containsKey(str)) {
            this.mAppIDtoPackageName.A2r(str, str2);
        }
    }

    @Nullable
    public String getCurrentAppOrPanelAppID() {
        App A01;
        String str = null;
        if (!TextUtils.isEmpty(this.mPanelAppPackageName) && (A01 = this.mLibrary.A01(this.mPanelAppPackageName)) != null) {
            str = A01.id;
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        return this.mLastLogAppID;
    }

    public String getCurrentRichPresenceJSON() {
        String str;
        Pair<HashSet<Integer>, String> pair;
        if (this.mIsHorizonInForeground) {
            str = "826037204154824";
        } else {
            str = this.mLastLogAppID;
        }
        String A01 = A01(str);
        if (A01 == null || (pair = this.mRichPresenceJSONMap.get(A01)) == null) {
            return "";
        }
        return (String) pair.second;
    }

    public void livestreamingStateChanged(boolean z, boolean z2) {
        this.mIsLivestreaming = z;
        this.mLivestreamingCommentsVisible = z2;
    }

    public void logWasCalled(String str, @Nullable String str2) {
        if (!(str2 == null && (str2 = this.mAppIDtoPackageName.A4s().get(str)) == null) && !str.equals("com.oculus.telemetry")) {
            if (!str.equals("com.oculus.systemactivities") && !str.equals("com.oculus.vrshell") && !str.equals("com.oculus.shellenv")) {
                this.mSessionManagerLazy.get().A03(str);
                String str3 = this.mLastLogAppID;
                if (A03(str2, null)) {
                    PlatformPluginManager platformPluginManager = this.mPlatformPluginManager;
                    if (!PlatformPluginManager.A05(platformPluginManager)) {
                        AnonymousClass0NO.A08(PlatformPluginManager.TAG, "onAppStop called before init");
                    } else {
                        PartyStateManager partyStateManager = platformPluginManager.mPartyStateManager;
                        if (str3 != null && PartyStateManager.WHITELIST_PASSTHROUGH.containsKey(str3)) {
                            partyStateManager.mNativeAPI.A1c(false);
                        }
                    }
                }
            }
            this.mLastLogElapsedTime = SystemClock.elapsedRealtime();
            if (!this.mAppIDtoPackageName.containsKey(str2)) {
                this.mAppIDtoPackageName.A2r(str2, str);
            }
        }
    }

    @Override // com.oculus.processtokentracker.ProcessTokenTracker.ProcessListener
    public void onDeath(String str, int i) {
        if (str.equals(this.mAppIDtoPackageName.get(this.mLastLogAppID))) {
            this.mAppIDtoPackageName.remove(this.mLastLogAppID);
            A03("1031607236937163", null);
        }
        Pair<HashSet<Integer>, String> pair = this.mRichPresenceJSONMap.get(str);
        if (pair != null) {
            AbstractCollection abstractCollection = (AbstractCollection) pair.first;
            abstractCollection.remove(Integer.valueOf(i));
            if (abstractCollection.isEmpty()) {
                this.mRichPresenceJSONMap.remove(str);
            }
        }
    }

    public void onLockStateChange() {
        boolean z;
        synchronized (this.mLockStateMutex) {
            boolean equals = Boolean.TRUE.equals(this.mIsUnlocked);
            Boolean valueOf = Boolean.valueOf(A02());
            this.mIsUnlocked = valueOf;
            if (!valueOf.booleanValue() || equals) {
                z = false;
            } else {
                this.mLastUnlockTime = Long.valueOf(SystemClock.elapsedRealtime());
                z = true;
            }
        }
        if (z) {
            this.mPresenceThread.interrupt();
        }
    }

    @Override // com.oculus.processtokentracker.ProcessTokenTracker.ProcessListener
    public void onRegister(String str, int i) {
        Pair<HashSet<Integer>, String> pair = this.mRichPresenceJSONMap.get(str);
        if (pair != null) {
            ((AbstractCollection) pair.first).add(Integer.valueOf(i));
            return;
        }
        HashSet hashSet = new HashSet();
        hashSet.add(Integer.valueOf(i));
        this.mRichPresenceJSONMap.put(str, new Pair<>(hashSet, ""));
    }

    public void panelApplicationChanged(String str, String str2) {
        if (TextUtils.equals(this.mLastLogAppID, "1031607236937163") || !DO_NOT_HEARTBEAT_AS_THESE_SERVICES.contains(str2)) {
            A03("1031607236937163", str);
        }
    }

    public void registerListener(Listener listener) {
        Preconditions.checkState(!this.mListeners.contains(listener), "This listener has already been registered");
        this.mListeners.add(listener);
    }

    public void run() {
        while (true) {
            long j = 30;
            Pair<HeartbeatResponse, Boolean> A00 = A00();
            HeartbeatResponse heartbeatResponse = (HeartbeatResponse) A00.first;
            if (!((Boolean) A00.second).booleanValue()) {
                if (heartbeatResponse != null) {
                    j = Math.max(heartbeatResponse.seconds_until_next_call_due, 5L);
                }
                try {
                    Thread.sleep(TimeUnit.SECONDS.toMillis(j));
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    public void setForeground(boolean z) {
        this.mIsHorizonInForeground = z;
        if (z) {
            synchronized (this.mLock) {
                this.mLock.notifyAll();
            }
        }
    }

    public void setRichPresence(String str, String str2, final ResultReceiver resultReceiver) {
        int i;
        Pair<HashSet<Integer>, String> pair = this.mRichPresenceJSONMap.get(str);
        if (pair == null) {
            AnonymousClass0NO.A09(TAG, "Ignore unregistered process trying to set rich presence");
            i = 0;
        } else if (str2.equals(pair.second)) {
            i = -1;
        } else {
            this.mRichPresenceJSONMap.put(str, new Pair<>(pair.first, str2));
            registerListener(new Listener() {
                /* class com.oculus.horizon.platform.PresenceManager.AnonymousClass1 */

                @Override // com.oculus.horizon.platform.PresenceManager.Listener
                public void onHeartbeat() {
                    resultReceiver.send(-1, new Bundle());
                    PresenceManager.this.unregisterListener(this);
                }

                @Override // com.oculus.horizon.platform.PresenceManager.Listener
                public void onHeartbeatFailed() {
                    resultReceiver.send(0, new Bundle());
                    PresenceManager.this.unregisterListener(this);
                }
            });
            this.mPresenceThread.interrupt();
            return;
        }
        resultReceiver.send(i, new Bundle());
    }

    public void start() {
        Thread thread = new Thread(this);
        this.mPresenceThread = thread;
        thread.start();
        this.mPresenceThreadStartTime = SystemClock.elapsedRealtime();
    }

    public void unregisterListener(Listener listener) {
        Preconditions.checkState(this.mListeners.contains(listener), "This listener is currently not registered");
        this.mListeners.remove(listener);
    }

    @Inject
    public PresenceManager(AbstractC06640p5 r4, @ForAppContext Context context, @OculusRestAdapter RestAdapter restAdapter, PlatformContext platformContext, PlatformPluginManager platformPluginManager, UserProfileHelper userProfileHelper, OVRLibrary oVRLibrary, AnonymousClass0p1<VRLifecycleSessionManager> r11) {
        this._UL_mInjectionContext = new AnonymousClass0QC(1, r4);
        this.mContext = context;
        this.mMethods = (Methods) restAdapter.create(Methods.class);
        this.mPlatformContext = platformContext;
        this.mPlatformPluginManager = platformPluginManager;
        this.mUserProfileHelper = userProfileHelper;
        this.mLibrary = oVRLibrary;
        this.mSessionManagerLazy = r11;
        this.mRichPresenceJSONMap = new HashMap<>();
        this.mAppIDtoPackageName = new HashBiMap();
    }

    @Nullable
    public String getCurrentAppPackageName() {
        return this.mPanelAppPackageName;
    }

    public String getLastRunningAppIDThatIsNotSysUtilsOrSysActivities() {
        return this.mLastLogAppID;
    }
}
