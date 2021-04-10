package com.facebook.secure.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.facebook.secure.logger.IntentLogger;
import com.facebook.secure.logger.IntentLoggerHelper;
import com.facebook.secure.logger.LocalReporter;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.switchoff.DefaultSwitchOffs;
import com.facebook.secure.trustedapp.checker.Checker;
import com.facebook.secure.trustedapp.checker.CheckerBase;
import java.util.Locale;

public abstract class PublicBaseServiceWithSwitchOff extends Service {
    private boolean mCalledOnCreate = false;
    private boolean mCalledOnDestroy = false;
    private boolean mCalledOnStartCommand = false;
    private String mEndpointName;
    private Checker mPermissionChecker = CheckerBase.ALWAYS_ALLOW;
    private final Reporter mReporter = new LocalReporter();

    /* access modifiers changed from: protected */
    public IBinder doBind(Intent intent) {
        return null;
    }

    public IntentLogger getIntentLogger() {
        return IntentLoggerHelper.DO_NOTHING;
    }

    /* JADX INFO: finally extract failed */
    public final void onCreate() {
        if (this.mCalledOnCreate) {
            onFail("Class %s called onCreate twice. This may be due to calling super.onCreate instead of super.onFbCreate", getClass().getName());
            super.onCreate();
            return;
        }
        try {
            this.mCalledOnCreate = true;
            this.mEndpointName = String.format("%s/%s", getPackageName(), getClass().getName());
            doCreate();
            this.mCalledOnCreate = false;
        } catch (Throwable th) {
            this.mCalledOnCreate = false;
            throw th;
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

    /* JADX INFO: finally extract failed */
    public final int onStartCommand(Intent intent, int i, int i2) {
        if (this.mCalledOnStartCommand) {
            onFail("Class %s called onStartCommand twice. This may be due to calling super.onStartCommand instead of super.onFbStartCommand", getClass().getName());
            getIntentLogger().logIntent(this.mEndpointName, "onStartCommand", "deny", intent);
            return super.onStartCommand(intent, i, i2);
        } else if (!DefaultSwitchOffs.general().check(this, this, intent)) {
            getIntentLogger().logIntent(this.mEndpointName, "onStartCommand", "deny", intent);
            return super.onStartCommand(intent, i, i2);
        } else if (!shouldAllowIntent(intent)) {
            getIntentLogger().logIntent(this.mEndpointName, "onStartCommand", "deny", intent);
            return super.onStartCommand(intent, i, i2);
        } else {
            try {
                this.mCalledOnStartCommand = true;
                getIntentLogger().logIntent(this.mEndpointName, "onStartCommand", "allow", intent);
                int doStartCommand = doStartCommand(intent, i, i2);
                this.mCalledOnStartCommand = false;
                return doStartCommand;
            } catch (Throwable th) {
                this.mCalledOnStartCommand = false;
                throw th;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public final void onDestroy() {
        if (this.mCalledOnDestroy) {
            onFail("Class %s called onDestroy twice. This may be due to calling super.onDestroy instead of super.onFbDestroy", getClass().getName());
            super.onDestroy();
            return;
        }
        try {
            this.mCalledOnDestroy = true;
            doDestroy();
            this.mCalledOnDestroy = false;
        } catch (Throwable th) {
            this.mCalledOnDestroy = false;
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    public void doCreate() {
        super.onCreate();
    }

    /* access modifiers changed from: protected */
    public int doStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    /* access modifiers changed from: protected */
    public void doDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onFail(String str, Object... objArr) {
        this.mReporter.report("PublicBaseServiceWithSwitchOff", String.format(Locale.US, str, objArr), new Throwable());
    }

    /* access modifiers changed from: protected */
    public synchronized void addAdditionalPermissionChecker(Checker checker) {
        this.mPermissionChecker = this.mPermissionChecker.and(checker);
    }

    /* access modifiers changed from: protected */
    public final synchronized boolean shouldAllowIntent(Intent intent) {
        return this.mPermissionChecker.shouldAllowIntent(this, this, intent, this.mReporter);
    }
}
