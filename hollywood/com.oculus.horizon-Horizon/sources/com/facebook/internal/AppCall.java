package com.facebook.internal;

import android.content.Intent;
import java.util.UUID;

public class AppCall {
    public static AppCall currentPendingCall;
    public UUID callId;
    public int requestCode;
    public Intent requestIntent;

    public static synchronized AppCall finishPendingCall(UUID uuid, int i) {
        synchronized (AppCall.class) {
            AppCall appCall = currentPendingCall;
            if (appCall == null || !appCall.callId.equals(uuid) || appCall.requestCode != i) {
                return null;
            }
            setCurrentPendingCall(null);
            return appCall;
        }
    }

    public static synchronized boolean setCurrentPendingCall(AppCall appCall) {
        boolean z;
        synchronized (AppCall.class) {
            AppCall appCall2 = currentPendingCall;
            currentPendingCall = appCall;
            z = false;
            if (appCall2 != null) {
                z = true;
            }
        }
        return z;
    }

    public static AppCall getCurrentPendingCall() {
        return currentPendingCall;
    }

    public UUID getCallId() {
        return this.callId;
    }

    public int getRequestCode() {
        return this.requestCode;
    }

    public Intent getRequestIntent() {
        return this.requestIntent;
    }

    public boolean setPending() {
        return setCurrentPendingCall(this);
    }

    public void setRequestCode(int i) {
        this.requestCode = i;
    }

    public void setRequestIntent(Intent intent) {
        this.requestIntent = intent;
    }

    public AppCall(int i) {
        this(i, UUID.randomUUID());
    }

    public AppCall(int i, UUID uuid) {
        this.callId = uuid;
        this.requestCode = i;
    }
}
