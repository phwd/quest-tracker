package com.facebook.secure.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import com.facebook.secure.logger.IntentLogger;
import com.facebook.secure.logger.IntentLoggerHelper;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.switchoff.DefaultSwitchOffs;
import com.facebook.secure.trustedapp.checker.Checker;
import com.facebook.secure.trustedapp.checker.CheckerBase;
import javax.annotation.Nullable;

public abstract class PublicBaseIntentServiceWithSwitchOff extends IntentService {
    public static final String SUB_ENDPOINT_ON_BIND = "onBind";
    public static final String SUB_ENDPOINT_ON_HANDLE_INTENT = "onHandleIntent";
    private String mEndpointName;
    private Checker mPermissionChecker = CheckerBase.ALWAYS_ALLOW;
    protected final String name;

    /* access modifiers changed from: protected */
    @Nullable
    public IBinder doBind(Intent intent) {
        return null;
    }

    @Nullable
    public Reporter getReporter() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract void onSecuredHandleIntent(@Nullable Intent intent);

    public PublicBaseIntentServiceWithSwitchOff(String str) {
        super(str);
        this.name = str;
    }

    public IntentLogger getIntentLogger() {
        return IntentLoggerHelper.DO_NOTHING;
    }

    public void onCreate() {
        super.onCreate();
        this.mEndpointName = String.format("%s/%s", getPackageName(), getClass().getName());
    }

    public final void onHandleIntent(@Nullable Intent intent) {
        if (intent != null && !DefaultSwitchOffs.general().check(this, this, intent)) {
            getIntentLogger().logIntent(this.mEndpointName, SUB_ENDPOINT_ON_HANDLE_INTENT, IntentLogger.Status.DENY, intent);
        } else if (intent == null || shouldAllowIntent(intent)) {
            getIntentLogger().logIntent(this.mEndpointName, SUB_ENDPOINT_ON_HANDLE_INTENT, IntentLogger.Status.ALLOW, intent);
            onSecuredHandleIntent(intent);
        } else {
            getIntentLogger().logIntent(this.mEndpointName, SUB_ENDPOINT_ON_HANDLE_INTENT, IntentLogger.Status.DENY, intent);
        }
    }

    @Nullable
    public final IBinder onBind(Intent intent) {
        if (!DefaultSwitchOffs.general().check(this, this, intent)) {
            getIntentLogger().logIntent(this.mEndpointName, "onBind", IntentLogger.Status.DENY, intent);
            return null;
        } else if (!shouldAllowIntent(intent)) {
            getIntentLogger().logIntent(this.mEndpointName, "onBind", IntentLogger.Status.DENY, intent);
            return null;
        } else {
            getIntentLogger().logIntent(this.mEndpointName, "onBind", IntentLogger.Status.ALLOW, intent);
            return doBind(intent);
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void addAdditionalPermissionChecker(Checker checker) {
        this.mPermissionChecker = this.mPermissionChecker.and(checker);
    }

    /* access modifiers changed from: protected */
    public final synchronized boolean shouldAllowIntent(Intent intent) {
        return this.mPermissionChecker.shouldAllowIntent(this, this, intent, getReporter());
    }
}
