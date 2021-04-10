package com.oculus.auth.service.passwordencryption;

import X.AbstractC06640p5;
import X.AnonymousClass0J2;
import X.AnonymousClass0QB;
import X.AnonymousClass0QC;
import X.AnonymousClass0b9;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import com.oculus.horizon.fbconnect.FBConnectHelper;
import com.oculus.security.basecomponent.OculusPublicBroadcastReceiver;
import javax.annotation.Nullable;

public class KeyFetchingTriggersReceiver extends OculusPublicBroadcastReceiver implements AnonymousClass0QB {
    public static final String ACTION_MOUNT_STATE_CHANGED = "com.oculus.intent.action.MOUNT_STATE_CHANGED";
    public static final String KEY_STATE = "state";
    public static final String TAG = "KeyFetchingTriggersReceiver";
    public AnonymousClass0QC _UL_mInjectionContext;

    public KeyFetchingTriggersReceiver() {
        super("android.net.wifi.STATE_CHANGE", "com.oculus.intent.action.MOUNT_STATE_CHANGED");
    }

    public static final void _UL_staticInjectMe(AbstractC06640p5 r2, KeyFetchingTriggersReceiver keyFetchingTriggersReceiver) {
        keyFetchingTriggersReceiver._UL_mInjectionContext = new AnonymousClass0QC(3, r2);
    }

    private void handleMountStateChanged(Intent intent) {
        if (intent.getBooleanExtra("state", false)) {
            logKeyFetchingTrigger(PasswordEncryptionLogger.TRIGGER_SCREEN_ON, null);
            ((PasswordEncryptionHelper) AnonymousClass0J2.A03(0, 42, this._UL_mInjectionContext)).triggerKeyFetching();
        }
    }

    private void handleNetworkStateChanged(Intent intent) {
        NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
        if (networkInfo != null && networkInfo.isConnected()) {
            logKeyFetchingTrigger(PasswordEncryptionLogger.TRIGGER_WIFI_CONNECTED, null);
            ((PasswordEncryptionHelper) AnonymousClass0J2.A03(0, 42, this._UL_mInjectionContext)).triggerKeyFetching();
        }
    }

    private void logKeyFetchingTrigger(@Nullable String str, @Nullable String str2) {
        ((PasswordEncryptionLogger) AnonymousClass0J2.A03(2, 359, this._UL_mInjectionContext)).logKeyFetchingEvent(PasswordEncryptionLogger.EVENT_KEY_FETCHING_TRIGGER, str, str2);
    }

    public static final void _UL_injectMe(Context context, KeyFetchingTriggersReceiver keyFetchingTriggersReceiver) {
        _UL_staticInjectMe((AbstractC06640p5) AnonymousClass0J2.get(context), keyFetchingTriggersReceiver);
    }

    @Override // com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase
    public void onReceive(Context context, Intent intent, AnonymousClass0b9 r7) {
        _UL_injectMe(context, this);
        String action = intent.getAction();
        if (action != null && !((FBConnectHelper) AnonymousClass0J2.A03(1, 486, this._UL_mInjectionContext)).isFacebookLinked()) {
            int hashCode = action.hashCode();
            if (hashCode != -1542846910) {
                if (hashCode == -343630553 && action.equals("android.net.wifi.STATE_CHANGE")) {
                    handleNetworkStateChanged(intent);
                }
            } else if (action.equals("com.oculus.intent.action.MOUNT_STATE_CHANGED")) {
                handleMountStateChanged(intent);
            }
        }
    }
}
