package com.oculus.partystatemanager;

import X.AnonymousClass0MD;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.VisibleForTesting;
import com.google.common.collect.ImmutableMap;
import com.oculus.horizon.social.SocialMethods;
import com.oculus.horizon.social.api.UserGetCurrentPartyResponse;
import com.oculus.http.core.base.ApiCallback;
import com.oculus.http.core.base.ApiError;
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
    public IPartyServiceDelegate mPartyServiceDelegate;
    public final SocialMethods mSocialMethods;
    public boolean mSystemVoipDisabled = false;

    public static /* synthetic */ String access$000() {
        return TAG;
    }

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
    private void updateSystemVoipDisabled(boolean r5, boolean r6) {
        /*
            r4 = this;
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
            r1.callNativeSetSystemVoipDisabled(r5, r0)     // Catch:{ all -> 0x0027 }
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
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.partystatemanager.PartyStateManager.updateSystemVoipDisabled(boolean, boolean):void");
    }

    public boolean isAppIDWhitelistedForPassthrough(String str) {
        return WHITELIST_PASSTHROUGH.containsKey(str);
    }

    public boolean isPackageWhitelistedForPassthrough(String str) {
        return WHITELIST_PASSTHROUGH.containsValue(str);
    }

    public boolean isPackageWhitelistedForVoipConnectionToast(@Nullable String str) {
        if (str == null) {
            return false;
        }
        return WHITELIST_VOIP_CONNECTION_TOAST.containsValue(str);
    }

    public void onAppStart(Context context, String str, boolean z) {
        if (str.equals("com.oculus.vralertservice") || str.equals("com.android.systemui")) {
            updateSystemVoipDisabled(z, true);
        } else {
            updateSystemVoipDisabled(z, false);
            if (!z) {
                enforcePartyChatStateConsistency(true, str, false);
            }
        }
        if (!WHITELIST_PASSTHROUGH.containsValue(str)) {
            this.mNativeAPI.callNativeSetSystemVoipPassthrough(false);
        }
        Bundle bundle = new Bundle();
        this.mNativeAPI.callNativeGetSystemVoipData(false, bundle);
        if (!bundle.containsKey("system_voip_state")) {
            AnonymousClass0MD.A04(TAG, "onAppStart nativeGetSystemVoipData didn't contain state");
            return;
        }
        Intent intent = new Intent(SYSTEM_VOIP_STATE_ACTION);
        intent.setPackage(str);
        intent.putExtra("system_voip_state", bundle.getString("system_voip_state"));
        context.sendBroadcast(intent);
    }

    public void onAppStop(String str) {
        if (str != null && WHITELIST_PASSTHROUGH.containsKey(str)) {
            this.mNativeAPI.callNativeSetSystemVoipPassthrough(false);
        }
    }

    public void startPartyChat(long j) throws IllegalArgumentException {
        if (j != -1) {
            this.mNativeAPI.maybeShowMicrophoneMutedToast();
            this.mNativeAPI.callNativePartyChatStart(j);
            synchronized (this.mPartiesStateLock) {
                this.mPartyChatStarted = true;
            }
            return;
        }
        throw new IllegalArgumentException("Party id not set");
    }

    public void stopPartyChat() {
        this.mNativeAPI.callNativePartyChatStop();
        synchronized (this.mPartiesStateLock) {
            this.mPartyChatStarted = false;
        }
    }

    static {
        ImmutableMap.Builder A04 = ImmutableMap.A04();
        A04.put("1101959559889232", "com.oculus.rooms");
        A04.put("1031607236937163", "com.oculus.vrshell");
        A04.put("882179625204268", "com.oculus.hackulus");
        WHITELIST_PASSTHROUGH = A04.build();
        ImmutableMap.Builder A042 = ImmutableMap.A04();
        A042.put("1031607236937163", "com.oculus.vrshell");
        WHITELIST_VOIP_CONNECTION_TOAST = A042.build();
    }

    public PartyStateManager(IPartiesNativeModuleManager iPartiesNativeModuleManager, SocialMethods socialMethods) {
        this.mNativeAPI = iPartiesNativeModuleManager;
        this.mSocialMethods = socialMethods;
    }

    public void setServiceDelegate(IPartyServiceDelegate iPartyServiceDelegate) {
        this.mPartyServiceDelegate = iPartyServiceDelegate;
    }

    public void enforcePartyChatStateConsistency() {
        enforcePartyChatStateConsistency(false, null, false);
    }

    public void enforcePartyChatStateConsistency(final boolean z, @Nullable final String str, final boolean z2) {
        this.mSocialMethods.userGetCurrentParty(new ApiCallback<UserGetCurrentPartyResponse>() {
            /* class com.oculus.partystatemanager.PartyStateManager.AnonymousClass1 */

            @Override // com.oculus.http.core.base.ApiCallback
            public void onError(ApiError apiError) {
                AnonymousClass0MD.A07(PartyStateManager.TAG, "Error fetching current party, I don't know what to do", apiError);
            }

            public void onResponse(UserGetCurrentPartyResponse userGetCurrentPartyResponse) {
                UserGetCurrentPartyResponse.Party party;
                String str;
                if (userGetCurrentPartyResponse == null || (party = userGetCurrentPartyResponse.current_party) == null || (str = party.id) == null) {
                    synchronized (PartyStateManager.this.mPartiesStateLock) {
                    }
                    PartyStateManager.this.stopPartyChat();
                    IPartyServiceDelegate iPartyServiceDelegate = PartyStateManager.this.mPartyServiceDelegate;
                    if (iPartyServiceDelegate != null) {
                        iPartyServiceDelegate.onUserNotInParty();
                        return;
                    }
                    return;
                }
                Long valueOf = Long.valueOf(Long.parseLong(str));
                if (valueOf == null) {
                    AnonymousClass0MD.A09(PartyStateManager.TAG, "Failed to parse party id into long.  Party ID: %s", str);
                    return;
                }
                IPartyServiceDelegate iPartyServiceDelegate2 = PartyStateManager.this.mPartyServiceDelegate;
                if (iPartyServiceDelegate2 != null) {
                    iPartyServiceDelegate2.onUserInParty(valueOf.longValue());
                }
                synchronized (PartyStateManager.this.mPartiesStateLock) {
                }
                if (z2) {
                    PartyStateManager.this.mNativeAPI.callNativeStartOrResume(valueOf.longValue());
                } else {
                    PartyStateManager.this.startPartyChat(valueOf.longValue());
                }
                if (!z || PartyStateManager.this.isPackageWhitelistedForVoipConnectionToast(str)) {
                    PartyStateManager.this.mNativeAPI.maybeShowPartyVoipConnectedToast(z);
                }
            }
        });
    }
}
