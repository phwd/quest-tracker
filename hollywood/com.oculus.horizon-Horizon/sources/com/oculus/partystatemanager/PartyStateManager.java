package com.oculus.partystatemanager;

import androidx.annotation.VisibleForTesting;
import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.social.SocialMethods;
import java.util.Map;
import javax.annotation.Nullable;

public class PartyStateManager {
    public static final String SYSTEM_VOIP_STATE_ACTION = "com.oculus.system_voip_state";
    @VisibleForTesting
    public static final String SYSTEM_VOIP_STATE_KEY = "system_voip_state";
    public static final String TAG = "PartyStateManager";
    public static final Map<String, String> WHITELIST_PASSTHROUGH;
    public static final Map<String, String> WHITELIST_VOIP_CONNECTION_TOAST;
    public final IPartiesNativeModuleManager mNativeAPI;
    public final Object mPartiesStateLock = new Object();
    public boolean mPartyChatStarted = false;
    @Nullable
    public final IPartyServiceDelegate mPartyServiceDelegate;
    public final SocialMethods mSocialMethods;
    public boolean mSystemVoipDisabled = false;

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0017, code lost:
        if (r2 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0019, code lost:
        if (r5 != false) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        r4.mNativeAPI.showPartyChatResume();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        r4.mNativeAPI.showPartyChatSuspend();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0026, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        if (r6 != false) goto L_?;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A00(com.oculus.partystatemanager.PartyStateManager r4, boolean r5, boolean r6) {
        /*
            java.lang.Object r3 = r4.mPartiesStateLock
            monitor-enter(r3)
            boolean r0 = r4.mSystemVoipDisabled     // Catch:{ all -> 0x0027 }
            if (r0 != r5) goto L_0x0009
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            return
        L_0x0009:
            r4.mSystemVoipDisabled = r5     // Catch:{ all -> 0x0027 }
            boolean r2 = r4.mPartyChatStarted     // Catch:{ all -> 0x0027 }
            com.oculus.partystatemanager.IPartiesNativeModuleManager r1 = r4.mNativeAPI     // Catch:{ all -> 0x0027 }
            java.lang.String r0 = "onAppSwitch"
            r1.A1b(r5, r0)     // Catch:{ all -> 0x0027 }
            monitor-exit(r3)     // Catch:{ all -> 0x0027 }
            if (r6 != 0) goto L_0x0020
            if (r2 == 0) goto L_0x0020
            if (r5 != 0) goto L_0x0021
            com.oculus.partystatemanager.IPartiesNativeModuleManager r0 = r4.mNativeAPI
            r0.showPartyChatResume()
        L_0x0020:
            return
        L_0x0021:
            com.oculus.partystatemanager.IPartiesNativeModuleManager r0 = r4.mNativeAPI
            r0.showPartyChatSuspend()
            return
        L_0x0027:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.partystatemanager.PartyStateManager.A00(com.oculus.partystatemanager.PartyStateManager, boolean, boolean):void");
    }

    public final void A01(long j) throws IllegalArgumentException {
        if (j != -1) {
            this.mNativeAPI.A5W();
            this.mNativeAPI.A1Z(j);
            synchronized (this.mPartiesStateLock) {
                this.mPartyChatStarted = true;
            }
            return;
        }
        throw new IllegalArgumentException("Party id not set");
    }

    static {
        ImmutableMap.Builder A01 = ImmutableMap.A01();
        A01.put("1101959559889232", "com.oculus.rooms");
        A01.put("1031607236937163", "com.oculus.vrshell");
        A01.put("882179625204268", "com.oculus.hackulus");
        WHITELIST_PASSTHROUGH = A01.build();
        ImmutableMap.Builder A012 = ImmutableMap.A01();
        A012.put("1031607236937163", "com.oculus.vrshell");
        WHITELIST_VOIP_CONNECTION_TOAST = A012.build();
    }

    public PartyStateManager(IPartiesNativeModuleManager iPartiesNativeModuleManager, SocialMethods socialMethods) {
        this.mNativeAPI = iPartiesNativeModuleManager;
        this.mSocialMethods = socialMethods;
    }
}
