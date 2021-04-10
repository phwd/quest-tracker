package com.facebook.secure.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import com.facebook.secure.logger.IntentLogger;
import com.facebook.secure.logger.IntentLoggerHelper;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.switchoff.DefaultSwitchOffs;
import com.facebook.secure.trustedapp.checker.Checker;

public abstract class PublicBaseIntentServiceWithSwitchOff extends IntentService {
    private String mEndpointName;
    private Checker mPermissionChecker;

    /* access modifiers changed from: protected */
    public IBinder doBind(Intent intent) {
        return null;
    }

    public Reporter getReporter() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract void onSecuredHandleIntent(Intent intent);

    public IntentLogger getIntentLogger() {
        return IntentLoggerHelper.DO_NOTHING;
    }

    public void onCreate() {
        super.onCreate();
        this.mEndpointName = String.format("%s/%s", getPackageName(), getClass().getName());
    }

    public final void onHandleIntent(Intent intent) {
        if (intent != null && !DefaultSwitchOffs.general().check(this, this, intent)) {
            getIntentLogger().logIntent(this.mEndpointName, "onHandleIntent", "deny", intent);
        } else if (intent == null || shouldAllowIntent(intent)) {
            getIntentLogger().logIntent(this.mEndpointName, "onHandleIntent", "allow", intent);
            onSecuredHandleIntent(intent);
        } else {
            getIntentLogger().logIntent(this.mEndpointName, "onHandleIntent", "deny", intent);
        }
    }

    public final IBinder onBind(Intent intent) {
        if (!DefaultSwitchOffs.general().check(this, this, intent)) {
            getIntentLogger().logIntent(this.mEndpointName, "onBind", "deny", intent);
            return null;
        } else if (!shouldAllowIntent(intent)) {
            getIntentLogger().logIntent(this.mEndpointName, "onBind", "deny", intent);
            return null;
        } else {
            getIntentLogger().logIntent(this.mEndpointName, "onBind", "allow", intent);
            return doBind(intent);
        }
    }

    /* access modifiers changed from: protected */
    public final synchronized boolean shouldAllowIntent(Intent intent) {
        return this.mPermissionChecker.shouldAllowIntent(this, this, intent, getReporter());
    }
}
