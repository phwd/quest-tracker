package com.oculus.horizon.platformplugin;

import X.AbstractC06600ny;
import X.AnonymousClass006;
import X.AnonymousClass0CC;
import X.AnonymousClass0J2;
import X.AnonymousClass0NO;
import X.AnonymousClass0b9;
import X.C02780bN;
import X.C02870bf;
import X.C02880bg;
import X.C03160cK;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.Settings;
import com.oculus.antipiracy.AntiPiracyManager;
import com.oculus.auth.handler.LogoutHandler;
import com.oculus.horizon.R;
import com.oculus.horizon.api.graphql.GraphQLSocialQuery;
import com.oculus.horizon.logging.OculusLogger;
import com.oculus.horizon.media_session.MediaSessionCallback;
import com.oculus.horizon.media_session.MediaSessionLifecycleManager;
import com.oculus.horizon.notifications.core.NotificationBuilder;
import com.oculus.horizon.platformplugin.MC;
import com.oculus.horizon.platformsdk.DeeplinkIntentUtils;
import com.oculus.horizon.profile.UserProfileHelper;
import com.oculus.horizon.social.SocialMethods;
import com.oculus.horizon.social.api.UserGetCurrentPartyResponse;
import com.oculus.horizon.vr.VRStateManager;
import com.oculus.http.core.base.ApiCallback;
import com.oculus.http.core.base.ApiError;
import com.oculus.logging.utils.Event;
import com.oculus.logging.utils.EventManager;
import com.oculus.managed.ManagedMode;
import com.oculus.os.SettingsManager;
import com.oculus.os.Version;
import com.oculus.partycontroller.PartyController;
import com.oculus.partystatemanager.IPartiesNativeModuleManager;
import com.oculus.partystatemanager.PartyStateManager;
import com.oculus.partystatemanager.logging.OculusPartyVoipEvent;
import com.oculus.partystatemanager.logging.OculusPartyVoipPauseResumeEvent;
import com.oculus.partystatemanager.logging.PartyEventFactory;
import com.oculus.partystatemanager.logging.PartyEventFields;
import com.oculus.platform.loader.EntryPoint;
import com.oculus.platform.util.OVRError;
import com.oculus.platform.util.Util;
import com.oculus.platforminitexception.PlatformInitException;
import com.oculus.security.basecomponent.OculusLocalSecureBroadcastReceiver;
import com.oculus.security.basecomponent.OculusPublicBroadcastReceiver;
import com.oculus.unlockulus_helper.UnlockulusHelper;
import com.oculus.util.WeakRef;
import com.oculus.util.constants.MicrophoneMuteState;
import com.oculus.util.constants.PlatformPluginConstants;
import com.oculus.util.device.DeviceUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import javax.annotation.Nullable;

@SuppressLint({"MissingNativeLoadLibrary"})
public class PlatformPluginManager implements PartyController, IPartiesNativeModuleManager, MediaSessionCallback, LogoutHandler {
    public static final int REAPER_INTERVAL_MS = 1000;
    public static final String RUN_PARTY_CHAT_ENFORCER = "com.oculus.horizon.RUN_PARTY_CHAT_ENFORCER";
    public static final String SYSTEM_VOIP_VOLUME_KEY = "volume";
    public static final String TAG = "PlatformPluginManager";
    public OculusPublicBroadcastReceiver mBroadcastReceiver = null;
    public ArrayList<WeakRef<Client>> mClients = new ArrayList<>();
    public final Object mConnectionsLock = new Object();
    public final Context mContext;
    public final DeeplinkIntentUtils mDeeplinkIntentUtils;
    public final DeviceUtils mDeviceUtils;
    public boolean mInitialized = false;
    public final Object mInitializedLock = new Object();
    public final MediaSessionLifecycleManager mLifecycleManager;
    public final ManagedMode mManagedMode;
    public final AbstractC06600ny mMobileConfig;
    public final Object mNativeCodeLock = new Object();
    public final NotificationBuilder mNotificationBuilder;
    public final OculusLogger mOculusLogger;
    public final OculusLocalSecureBroadcastReceiver mPartyChatReceiver = new OculusLocalSecureBroadcastReceiver(RUN_PARTY_CHAT_ENFORCER) {
        /* class com.oculus.horizon.platformplugin.PlatformPluginManager.AnonymousClass1 */

        @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
        public final void onReceive(Context context, Intent intent, AnonymousClass0b9 r8) {
            if (intent.getAction().equals(PlatformPluginManager.RUN_PARTY_CHAT_ENFORCER)) {
                PartyStateManager partyStateManager = PlatformPluginManager.this.mPartyStateManager;
                SocialMethods socialMethods = partyStateManager.mSocialMethods;
                socialMethods.mMethods.userGetCurrentParty(GraphQLSocialQuery.USER_GET_CURRENT_PARTY, new ApiCallback<UserGetCurrentPartyResponse>(false, null) {
                    /* class com.oculus.partystatemanager.PartyStateManager.AnonymousClass1 */
                    public final /* synthetic */ boolean val$isOnAppSwitch;
                    public final /* synthetic */ boolean val$isRemount = false;
                    public final /* synthetic */ String val$packageName;

                    {
                        this.val$isOnAppSwitch = r3;
                        this.val$packageName = r4;
                    }

                    @Override // com.oculus.http.core.base.ApiCallback
                    public final void onError(ApiError apiError) {
                        AnonymousClass0NO.A0B(PartyStateManager.TAG, "Error fetching current party, I don't know what to do", apiError);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                    @Override // com.oculus.http.core.base.ApiCallback
                    public final void onResponse(UserGetCurrentPartyResponse userGetCurrentPartyResponse) {
                        UserGetCurrentPartyResponse.Party party;
                        String str;
                        String str2;
                        UserGetCurrentPartyResponse userGetCurrentPartyResponse2 = userGetCurrentPartyResponse;
                        if (userGetCurrentPartyResponse2 == null || (party = userGetCurrentPartyResponse2.current_party) == null || (str = party.id) == null) {
                            synchronized (PartyStateManager.this.mPartiesStateLock) {
                            }
                            PartyStateManager partyStateManager = PartyStateManager.this;
                            partyStateManager.mNativeAPI.A1a();
                            synchronized (partyStateManager.mPartiesStateLock) {
                                partyStateManager.mPartyChatStarted = false;
                            }
                            if (PartyStateManager.this.mPartyServiceDelegate == null) {
                                return;
                            }
                        } else {
                            Long valueOf = Long.valueOf(Long.parseLong(str));
                            if (valueOf == null) {
                                AnonymousClass0NO.A0E(PartyStateManager.TAG, "Failed to parse party id into long.  Party ID: %s", str);
                                return;
                            }
                            PartyStateManager partyStateManager2 = PartyStateManager.this;
                            if (partyStateManager2.mPartyServiceDelegate == null) {
                                synchronized (partyStateManager2.mPartiesStateLock) {
                                }
                                if (!this.val$isRemount) {
                                    PartyStateManager.this.A01(valueOf.longValue());
                                }
                                if (!this.val$isOnAppSwitch || ((str2 = this.val$packageName) != null && PartyStateManager.WHITELIST_VOIP_CONNECTION_TOAST.containsValue(str2))) {
                                    PartyStateManager.this.mNativeAPI.A5X(this.val$isOnAppSwitch);
                                    return;
                                }
                                return;
                            }
                        }
                        throw null;
                    }
                });
            }
        }
    };
    public final PartyEventFactory mPartyEventFactory;
    public final PartyStateManager mPartyStateManager;
    @Nullable
    public Timer mReaper = null;
    public Object mReaperLock = new Object();
    public final C02870bf mTrustedApp;
    public final UnlockulusHelper mUnlockulusHelper;
    public final UserProfileHelper mUserProfileHelper;
    public final VRStateManager mVRStateManager;
    public final Object mVrStateManagerLock = new Object();

    public interface Client {
        void showPartyChatResume();

        void showPartyChatSuspend();
    }

    public static class MultiwayConnectionState {
        public static final int CLOSED = 4;
        public static final int CONNECTED = 3;
        public static final int CONNECTING = 2;
        public static final int CONTACTING = 1;
        public static final int ERROR = 5;
        public static final int UNINITIALIZED = 0;

        @Retention(RetentionPolicy.SOURCE)
        public @interface MultiwayConnectionStateType {
        }
    }

    public static class PartyChatConnectionReason {
        public static final int ERROR = 5;
        public static final int NOT_APPLICABLE = 0;
        public static final int PAUSE = 3;
        public static final int RESUME = 2;
        public static final int START = 1;
        public static final int STOP = 4;
    }

    public static native void nativeCapturedAudioRenderer_Delete(long j);

    public static native long nativeCapturedAudioRenderer_New();

    public static native int nativeCapturedAudioRenderer_RetrievePCM(long j, short[] sArr, int i, int i2, int i3);

    public static native void nativeCapturedAudioRenderer_StorePCM(long j, short[] sArr, int i, int i2, int i3, float f, long j2, int i4);

    public static native String[] nativeGetCloudStorage2DownloadConflictFiles(String str, String str2);

    public static native String[] nativeGetCloudStorage2MatchingFiles(String[] strArr, String[] strArr2);

    public static native String nativeGetCloudStorage2MetadataJsonFromFiles(String str, String[] strArr);

    public static native String nativeGetCloudStorage2SyncFileActions(String str, String str2);

    public static native String[] nativeGetCloudStorage2UploadConflictFiles(String str, String str2);

    public static native boolean nativeGetIsLivestreamingMicrophoneMuted();

    public static native int nativeGetSharedMicSocketFD(long j);

    public static native void nativeGetSharedMicrophoneData(long j, int i, Bundle bundle);

    public static native void nativeGetSystemVoipData(boolean z, Bundle bundle);

    public static native String nativeGetSystemVoipMicrophoneMuted();

    public static native boolean nativeGetSystemVoipPassthrough();

    public static native String nativeGetSystemVoipStatus();

    public static native float nativeGetSystemVoipVolume();

    public static native void nativeGetVideoStreamingParticipants(Bundle bundle);

    public static native void nativeGetVideoStreamingTrackForParticipant(long j, Bundle bundle);

    public static native void nativeInit(Context context);

    public static native void nativeInitializeForCasting(String str, String str2, String str3);

    public static native void nativeInitializeForCastingNativeReceiver(String str, String str2, String str3, int i, int i2, int i3, int i4);

    public static native void nativeInitializeForCastingWithVideoSpecAndBitrateAndDataChannel(String str, String str2, String str3, int i, int i2, int i3, int i4, boolean z);

    public static native void nativeInitializeForCastingWithVideoSpecAndDataChannel(String str, String str2, String str3, int i, int i2, int i3, boolean z);

    public static native void nativeInitializeMWSConnection(String str, String str2);

    public static native void nativePartyChatStart(long j);

    public static native void nativePartyChatStop();

    public static native boolean nativePartyHasParticipantRecentlySpoken(long j);

    public static native void nativeSendToJsonDataChannel(String str);

    public static native void nativeSetCapturedPCM(short[] sArr, int i, int i2, float f, long j, int i3);

    public static native void nativeSetHeadphoneState(boolean z);

    public static native void nativeSetHeadsetMountState(boolean z);

    public static native void nativeSetLivestreamingMicrophoneMuted(boolean z);

    public static native void nativeSetPartyGameAudioMuted(boolean z);

    public static native void nativeSetSystemVoipDisabled(boolean z);

    public static native void nativeSetSystemVoipMicrophoneMuted(int i, Bundle bundle);

    public static native int nativeSetSystemVoipPassthrough(boolean z);

    public static native void nativeSetSystemVoipSuppressed(boolean z, Bundle bundle);

    public static native void nativeSetSystemVoipVolume(float f);

    public static native void nativeSetTuneInModeEnabled(boolean z);

    public static native void nativeSharedMicrophoneDisableNoiseSuppressor(long j);

    public static native void nativeSharedMicrophoneEnableNoiseSuppressor(long j);

    public static native void nativeSharedMicrophoneStop(long j);

    public static native void nativeStartLiveStreaming(String str);

    public static native void nativeStartPartyVideoStreaming(String str);

    public static native void nativeStopLiveStreaming();

    public static native void nativeStopPartyVideoStreaming();

    public static native void nativeTearDown();

    public final void A06(Client client) {
        Throwable th;
        Throwable th2;
        synchronized (this.mConnectionsLock) {
            if (client != null) {
                if (this.mClients.isEmpty()) {
                    synchronized (this.mInitializedLock) {
                        try {
                            if (!this.mInitialized && A03() && (this.mUserProfileHelper.mCredentialsProvider.get() != null || this.mManagedMode.isEnterpriseModeEnabled)) {
                                synchronized (this.mNativeCodeLock) {
                                    try {
                                        nativeInit(this.mContext);
                                    } catch (Throwable th3) {
                                        th2 = th3;
                                        throw th2;
                                    }
                                }
                                AnonymousClass3 r1 = new OculusPublicBroadcastReceiver("android.intent.action.HEADSET_PLUG") {
                                    /* class com.oculus.horizon.platformplugin.PlatformPluginManager.AnonymousClass3 */

                                    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
                                    public final void onReceive(Context context, Intent intent, AnonymousClass0b9 r6) {
                                        if (intent.getAction().equals("android.intent.action.HEADSET_PLUG")) {
                                            boolean z = false;
                                            int intExtra = intent.getIntExtra("state", 0);
                                            intent.getIntExtra("microphone", 0);
                                            intent.getStringExtra("name");
                                            PlatformPluginManager platformPluginManager = PlatformPluginManager.this;
                                            if (intExtra != 0) {
                                                z = true;
                                            }
                                            synchronized (platformPluginManager.mNativeCodeLock) {
                                                PlatformPluginManager.nativeSetHeadphoneState(z);
                                            }
                                        }
                                    }
                                };
                                this.mBroadcastReceiver = r1;
                                r1.registerReceiver(this.mContext);
                                this.mPartyChatReceiver.registerReceiver(this.mContext);
                                boolean isWiredHeadsetOn = ((AudioManager) this.mContext.getSystemService("audio")).isWiredHeadsetOn();
                                synchronized (this.mNativeCodeLock) {
                                    try {
                                        nativeSetHeadphoneState(isWiredHeadsetOn);
                                    } catch (Throwable th4) {
                                        th2 = th4;
                                        throw th2;
                                    }
                                }
                                this.mLifecycleManager.A01(this);
                                this.mInitialized = true;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            throw th;
                        }
                    }
                }
                if (A05(this) && A03()) {
                    synchronized (this.mReaperLock) {
                        try {
                            if (this.mReaper == null) {
                                Timer timer = new Timer();
                                this.mReaper = timer;
                                timer.scheduleAtFixedRate(new TimerTask() {
                                    /* class com.oculus.horizon.platformplugin.PlatformPluginManager.AnonymousClass2 */

                                    public final void run() {
                                        PlatformPluginManager platformPluginManager = PlatformPluginManager.this;
                                        synchronized (platformPluginManager.mConnectionsLock) {
                                            Iterator<WeakRef<Client>> it = platformPluginManager.mClients.iterator();
                                            while (it.hasNext()) {
                                                if (it.next().get() == null) {
                                                    it.remove();
                                                    platformPluginManager.mClients.size();
                                                }
                                            }
                                            if (platformPluginManager.mClients.isEmpty()) {
                                                PlatformPluginManager.A02(platformPluginManager);
                                            }
                                        }
                                    }
                                }, 0, 1000);
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            throw th;
                        }
                    }
                    if (!A08(client)) {
                        this.mClients.add(new WeakRef<>(client));
                    }
                }
            }
        }
    }

    @Override // com.oculus.horizon.media_session.MediaSessionCallback
    public final void A6D() {
    }

    @Override // com.oculus.horizon.media_session.MediaSessionCallback
    public final boolean A8z() {
        return false;
    }

    public static Bundle A00(Bundle bundle, String str) {
        return Util.makeErrorResult(new Throwable(AnonymousClass006.A08("SystemVoip ", str, " permission denied, requested by app id ", bundle.getString("app_id"))), AnonymousClass006.A07("SystemVoip ", str, " permission denied"), OVRError.UNKNOWN_ERROR.mCode);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0086, code lost:
        r1 = r9.mReaperLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0088, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r0 = r9.mReaper;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x008b, code lost:
        if (r0 == null) goto L_0x0093;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008d, code lost:
        r0.cancel();
        r9.mReaper = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0093, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0094, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void A02(com.oculus.horizon.platformplugin.PlatformPluginManager r9) {
        /*
        // Method dump skipped, instructions count: 158
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.horizon.platformplugin.PlatformPluginManager.A02(com.oculus.horizon.platformplugin.PlatformPluginManager):void");
    }

    private boolean A03() {
        boolean z;
        synchronized (this.mVrStateManagerLock) {
            z = true;
            if ((this.mDeviceUtils.A04() || Settings.Global.getInt(this.mContext.getContentResolver(), AntiPiracyManager.VR_DEVELOPER_MODE, 0) == 0) && !this.mVRStateManager.mIsDocked) {
                z = false;
            }
        }
        return z;
    }

    public static boolean A04() {
        return new SettingsManager().getBoolean("mic_muted", false);
    }

    public static final boolean A05(PlatformPluginManager platformPluginManager) {
        boolean z;
        synchronized (platformPluginManager.mInitializedLock) {
            z = platformPluginManager.mInitialized;
        }
        return z;
    }

    public final void A07(Client client) {
        synchronized (this.mConnectionsLock) {
            if (A08(client)) {
                this.mClients.remove(new WeakRef(client));
                this.mClients.size();
            }
            if (this.mClients.isEmpty()) {
                A02(this);
            }
        }
    }

    public final boolean A08(Client client) {
        synchronized (this.mConnectionsLock) {
            int i = 0;
            while (true) {
                ArrayList<WeakRef<Client>> arrayList = this.mClients;
                if (i >= arrayList.size()) {
                    return false;
                }
                WeakRef<Client> weakRef = arrayList.get(i);
                if (weakRef != null && weakRef.get() == client) {
                    return true;
                }
                i++;
            }
        }
    }

    public final boolean A09(String str) {
        if (!PlatformPluginConstants.WHITELIST_SYSTEM_VOIP_PACKAGES.contains(str) || !this.mTrustedApp.A06(this.mContext)) {
            return false;
        }
        return true;
    }

    @Override // com.oculus.partystatemanager.IPartiesNativeModuleManager
    public final void A1Y(boolean z, Bundle bundle) {
        synchronized (this.mNativeCodeLock) {
            nativeGetSystemVoipData(z, bundle);
        }
    }

    @Override // com.oculus.partystatemanager.IPartiesNativeModuleManager
    public final void A1b(boolean z, String str) {
        PartyEventFields.EventName eventName;
        synchronized (this.mNativeCodeLock) {
            nativeSetSystemVoipDisabled(z);
        }
        PartyEventFactory partyEventFactory = this.mPartyEventFactory;
        if (z) {
            eventName = PartyEventFields.EventName.VOIP_PAUSE;
        } else {
            eventName = PartyEventFields.EventName.VOIP_RESUME;
        }
        OculusPartyVoipPauseResumeEvent oculusPartyVoipPauseResumeEvent = new OculusPartyVoipPauseResumeEvent((EventManager) AnonymousClass0J2.A03(0, 242, partyEventFactory._UL_mInjectionContext), eventName, Long.toString(0), str);
        Event A22 = oculusPartyVoipPauseResumeEvent.mEventManager.A22(oculusPartyVoipPauseResumeEvent.mEvent);
        oculusPartyVoipPauseResumeEvent.A00(A22);
        A22.A5L();
    }

    @Override // com.oculus.partystatemanager.IPartiesNativeModuleManager
    public final void A1c(boolean z) {
        synchronized (this.mNativeCodeLock) {
            nativeSetSystemVoipPassthrough(z);
        }
    }

    @Override // com.oculus.partycontroller.PartyController
    public final Set<String> A3z() {
        return new HashSet();
    }

    @Override // com.oculus.partystatemanager.IPartiesNativeModuleManager
    public final void A5W() {
        if (this.mMobileConfig.A36(MC.android_oculus_horizon.is_microphone_setting_available) && this.mDeviceUtils.A04() && Version.CURRENT_SDK_VERSION >= 8 && A04()) {
            NotificationBuilder.Builder builder = new NotificationBuilder.Builder(this.mContext.getString(R.string.notifications_party_microphone_muted), "");
            builder.iconId = R.drawable.ic_notif_microphone;
            builder.suppressSound = true;
            builder.A01();
        }
    }

    @Override // com.oculus.horizon.media_session.MediaSessionCallback
    public final void A6G() {
        synchronized (this.mVrStateManagerLock) {
            VRStateManager vRStateManager = this.mVRStateManager;
            vRStateManager.mIsDocked = false;
            vRStateManager.mLastUndockTime.set(System.currentTimeMillis());
        }
        A02(this);
    }

    @Override // com.oculus.horizon.media_session.MediaSessionCallback
    public final void A6H() {
        synchronized (this.mVrStateManagerLock) {
            this.mVRStateManager.mIsDocked = true;
        }
    }

    @Override // com.oculus.auth.handler.LogoutHandler
    public final void beforeLogout() {
        AnonymousClass0NO.A08(TAG, "beforeLogout() Trying to teardown down the Plugin.");
        synchronized (this.mConnectionsLock) {
            this.mClients.clear();
        }
        A02(this);
    }

    @Override // com.oculus.partystatemanager.IPartiesNativeModuleManager
    public final void showPartyChatResume() {
        synchronized (this.mConnectionsLock) {
            Iterator<WeakRef<Client>> it = this.mClients.iterator();
            while (it.hasNext()) {
                Client client = it.next().get();
                if (client != null) {
                    client.showPartyChatResume();
                }
            }
        }
    }

    @Override // com.oculus.partystatemanager.IPartiesNativeModuleManager
    public final void showPartyChatSuspend() {
        synchronized (this.mConnectionsLock) {
            Iterator<WeakRef<Client>> it = this.mClients.iterator();
            while (it.hasNext()) {
                Client client = it.next().get();
                if (client != null) {
                    client.showPartyChatSuspend();
                }
            }
        }
    }

    public static Bundle A01(PlatformPluginManager platformPluginManager) {
        if (!A05(platformPluginManager)) {
            return Util.makeErrorResult(null, "PlatformPlugin not initialized", OVRError.UNKNOWN_ERROR.mCode);
        }
        return null;
    }

    @Override // com.oculus.partystatemanager.IPartiesNativeModuleManager
    public final void A1Z(long j) {
        if (!A05(this)) {
            AnonymousClass0NO.A08(TAG, "PlatformPlugin is not initialized");
            return;
        }
        synchronized (this.mNativeCodeLock) {
            nativePartyChatStart(j);
        }
        PartyEventFactory partyEventFactory = this.mPartyEventFactory;
        OculusPartyVoipEvent oculusPartyVoipEvent = new OculusPartyVoipEvent((EventManager) AnonymousClass0J2.A03(0, 242, partyEventFactory._UL_mInjectionContext), PartyEventFields.EventName.VOIP_START, Long.toString(j));
        Event A22 = oculusPartyVoipEvent.mEventManager.A22(oculusPartyVoipEvent.mEvent);
        oculusPartyVoipEvent.A00(A22);
        A22.A5L();
    }

    @Override // com.oculus.partystatemanager.IPartiesNativeModuleManager
    public final void A1a() {
        if (!A05(this)) {
            AnonymousClass0NO.A08(TAG, "PlatformPlugin is not initialized");
            return;
        }
        synchronized (this.mNativeCodeLock) {
            nativePartyChatStop();
        }
        PartyEventFactory partyEventFactory = this.mPartyEventFactory;
        OculusPartyVoipEvent oculusPartyVoipEvent = new OculusPartyVoipEvent((EventManager) AnonymousClass0J2.A03(0, 242, partyEventFactory._UL_mInjectionContext), PartyEventFields.EventName.VOIP_STOP, Long.toString(0));
        Event A22 = oculusPartyVoipEvent.mEventManager.A22(oculusPartyVoipEvent.mEvent);
        oculusPartyVoipEvent.A00(A22);
        A22.A5L();
    }

    @Override // com.oculus.partycontroller.PartyController
    public final boolean A3U(long j) {
        boolean nativePartyHasParticipantRecentlySpoken;
        if (A01(this) != null) {
            AnonymousClass0NO.A09(TAG, "getHasParticipantRecentlySpoken - got init error");
            return false;
        }
        synchronized (this.mNativeCodeLock) {
            nativePartyHasParticipantRecentlySpoken = nativePartyHasParticipantRecentlySpoken(j);
        }
        return nativePartyHasParticipantRecentlySpoken;
    }

    @Override // com.oculus.partycontroller.PartyController
    public final float A46() throws PlatformInitException {
        float nativeGetSystemVoipVolume;
        if (A05(this)) {
            synchronized (this.mNativeCodeLock) {
                nativeGetSystemVoipVolume = nativeGetSystemVoipVolume();
            }
            return nativeGetSystemVoipVolume;
        }
        throw new PlatformInitException("PlatformPlugin is not loaded");
    }

    @Override // com.oculus.partycontroller.PartyController
    public final boolean A47() {
        return false;
    }

    @Override // com.oculus.partycontroller.PartyController
    public final String A4V() throws PlatformInitException, IllegalArgumentException {
        if (A05(this)) {
            Bundle bundle = new Bundle();
            synchronized (this.mNativeCodeLock) {
                nativeGetSystemVoipData(false, bundle);
            }
            return bundle.getString("system_voip_state");
        }
        throw new PlatformInitException("PlatformPlugin is not loaded");
    }

    @Override // com.oculus.partystatemanager.IPartiesNativeModuleManager
    public final void A5X(boolean z) {
        String nativeGetSystemVoipMicrophoneMuted;
        if (!A05(this)) {
            nativeGetSystemVoipMicrophoneMuted = "UNKNOWN";
        } else {
            synchronized (this.mNativeCodeLock) {
                nativeGetSystemVoipMicrophoneMuted = nativeGetSystemVoipMicrophoneMuted();
            }
        }
        boolean z2 = true;
        if (MicrophoneMuteState.A00(nativeGetSystemVoipMicrophoneMuted) != 1) {
            z2 = false;
        }
        if (!A04() && !z2 && this.mMobileConfig.A36(MC.android_oculus_horizon.is_oculus_party_voip_rejoin_enabled)) {
            if (!z || this.mMobileConfig.A36(MC.android_oculus_horizon.is_oculus_party_voip_rejoin_on_app_switch_enabled)) {
                NotificationBuilder.Builder builder = new NotificationBuilder.Builder(this.mContext.getString(R.string.notifications_party_chat_started_title_v2), this.mContext.getString(R.string.notifications_party_chat_started_body_v2));
                builder.iconId = R.drawable.ic_notif_microphone;
                builder.A01();
            }
        }
    }

    @Override // com.oculus.horizon.media_session.MediaSessionCallback
    public final void A6C() {
    }

    @Override // com.oculus.partycontroller.PartyController
    public final void A8i(float f) throws PlatformInitException {
        if (A05(this)) {
            synchronized (this.mNativeCodeLock) {
                nativeSetSystemVoipVolume(f);
            }
            return;
        }
        throw new PlatformInitException("PlatformPlugin is not loaded");
    }

    @Override // com.oculus.partycontroller.PartyController
    public final String A8p(int i) throws PlatformInitException, IllegalArgumentException {
        if (!A05(this)) {
            throw new PlatformInitException("PlatformPlugin is not loaded");
        } else if (i == 0 || i == 1 || i == 2) {
            Bundle bundle = new Bundle();
            synchronized (this.mNativeCodeLock) {
                nativeSetSystemVoipMicrophoneMuted(i, bundle);
            }
            return bundle.getString("system_voip_state");
        } else {
            throw new IllegalArgumentException("Unrecognized mute state");
        }
    }

    @Override // com.oculus.partycontroller.PartyController
    public final Bundle A9J(long j) {
        Bundle A01 = A01(this);
        if (A01 != null) {
            return A01;
        }
        try {
            this.mPartyStateManager.A01(j);
            return new Bundle();
        } catch (IllegalArgumentException e) {
            return Util.makeErrorResult(null, e.getMessage(), OVRError.UNKNOWN_ERROR.mCode);
        }
    }

    @Override // com.oculus.partycontroller.PartyController
    public final Bundle A9S() {
        Bundle A01 = A01(this);
        if (A01 != null) {
            return A01;
        }
        PartyStateManager partyStateManager = this.mPartyStateManager;
        partyStateManager.mNativeAPI.A1a();
        synchronized (partyStateManager.mPartiesStateLock) {
            partyStateManager.mPartyChatStarted = false;
        }
        return new Bundle();
    }

    @Override // com.oculus.partycontroller.PartyController
    public final int A8j(long j, int i) {
        return 0;
    }

    public PlatformPluginManager(Context context, VRStateManager vRStateManager, UserProfileHelper userProfileHelper, MediaSessionLifecycleManager mediaSessionLifecycleManager, OculusLogger oculusLogger, DeviceUtils deviceUtils, SocialMethods socialMethods, NotificationBuilder notificationBuilder, AbstractC06600ny r12, ManagedMode managedMode, DeeplinkIntentUtils deeplinkIntentUtils, PartyEventFactory partyEventFactory) {
        this.mContext = context;
        this.mVRStateManager = vRStateManager;
        this.mUserProfileHelper = userProfileHelper;
        this.mLifecycleManager = mediaSessionLifecycleManager;
        this.mOculusLogger = oculusLogger;
        this.mDeviceUtils = deviceUtils;
        this.mNotificationBuilder = notificationBuilder;
        this.mMobileConfig = r12;
        UnlockulusHelper unlockulusHelper = new UnlockulusHelper();
        this.mUnlockulusHelper = unlockulusHelper;
        AnonymousClass0CC r1 = new AnonymousClass0CC();
        if (unlockulusHelper.A00(context)) {
            r1.A04(C02780bN.A01);
        }
        r1.A04(C02780bN.A0L);
        r1.A04(C02780bN.A0P);
        this.mTrustedApp = C02880bg.A01(r1.build());
        this.mManagedMode = managedMode;
        this.mDeeplinkIntentUtils = deeplinkIntentUtils;
        this.mPartyEventFactory = partyEventFactory;
        this.mPartyStateManager = new PartyStateManager(this, socialMethods);
        C03160cK.A05(EntryPoint.PLATFORM_SDK_32_BINARY, 0);
        C03160cK.A05("ovrplatformplugin", 0);
    }
}
