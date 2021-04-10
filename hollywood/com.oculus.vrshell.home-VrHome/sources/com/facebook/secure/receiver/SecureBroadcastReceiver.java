package com.facebook.secure.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.facebook.secure.intent.IntentScope;
import com.facebook.secure.logger.IntentLogger;
import com.facebook.secure.logger.IntentLoggerHelper;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.switchoff.DefaultSwitchOffs;
import com.facebook.secure.trustedapp.checker.Checker;
import com.facebook.secure.trustedapp.checker.CheckerBase;

public abstract class SecureBroadcastReceiver extends BroadcastReceiver implements BroadcastReceiverLike {
    private Checker mChecker = CheckerBase.ALWAYS_ALLOW;
    private String mEndpointName;
    private IntentScope mScope;

    /* access modifiers changed from: protected */
    public abstract ActionReceiver findReceiverForIntent(Context context, String str);

    public abstract IntentFilter getIntentFilter();

    /* access modifiers changed from: protected */
    public abstract boolean isActionRemoved(String str);

    /* access modifiers changed from: protected */
    public String getTag() {
        return "SecureBroadcastReceiver";
    }

    public IntentLogger getIntentLogger() {
        return IntentLoggerHelper.DO_NOTHING;
    }

    /* access modifiers changed from: protected */
    public void handleMissingReceiver(Context context, String action) {
        String tag = getTag();
        Log.e(tag, "Rejected the intent for the receiver because it was not registered: " + action + ":" + tag);
    }

    public IntentScope getIntentScope() {
        return this.mScope;
    }

    public Reporter getReporter() {
        return null;
    }

    public void setIntentScope(IntentScope scope) {
        this.mScope = scope;
    }

    /* access modifiers changed from: protected */
    public synchronized void addAdditionalPermissionChecker(Checker checker) {
        this.mChecker = this.mChecker.and(checker);
    }

    /* access modifiers changed from: protected */
    public final synchronized boolean shouldAllowIntent(Context context, Intent intent) {
        return this.mChecker.shouldAllowIntent(context, this, intent, getReporter());
    }

    /* access modifiers changed from: protected */
    public boolean shouldProcessBroadcast(Context context, Intent intent) {
        return this.mScope == null || this.mScope.enforceReceiverIntent(intent, context) != null;
    }

    public final void onReceive(Context context, Intent intent) {
        onBeforeReceive(context);
        String endpointName = (String) checkNotNull(this.mEndpointName);
        String action = intent.getAction();
        if (action == null) {
            Log.e(getTag(), "action is null for SecureBroadcastReceiver");
            return;
        }
        ActionReceiver receiver = findReceiverForIntent(context, action);
        if (receiver != null) {
            if (!DefaultSwitchOffs.general().check(context, endpointObjectFor(receiver), intent)) {
                getIntentLogger().logIntent(endpointName, null, "deny", intent);
            } else if (!shouldAllowIntent(context, intent) || !shouldProcessBroadcast(context, intent)) {
                getIntentLogger().logIntent(endpointName, null, "deny", intent);
            } else {
                getIntentLogger().logIntent(endpointName, null, "allow", intent);
                receiver.onReceive(context, intent, this);
            }
        } else if (!isActionRemoved(action)) {
            getIntentLogger().logIntent(endpointName, null, "deny", intent);
            handleMissingReceiver(context, action);
        }
    }

    /* access modifiers changed from: protected */
    public Object endpointObjectFor(ActionReceiver actionReceiver) {
        return actionReceiver;
    }

    protected static <T> T checkNotNull(T obj) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException("Object is null!");
    }

    /* access modifiers changed from: protected */
    public void onBeforeReceive(Context context) {
        if (this.mEndpointName == null) {
            this.mEndpointName = String.format("%s/%s", context.getPackageName(), getClass().getName());
        }
    }
}
