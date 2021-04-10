package com.facebook.secure.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
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
    public Object endpointObjectFor(ActionReceiver actionReceiver) {
        return actionReceiver;
    }

    /* access modifiers changed from: protected */
    public abstract ActionReceiver findReceiverForIntent(Context context, String str);

    public Reporter getReporter() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String getTag() {
        return "SecureBroadcastReceiver";
    }

    /* access modifiers changed from: protected */
    public abstract boolean isActionRemoved(String str);

    public IntentLogger getIntentLogger() {
        return IntentLoggerHelper.DO_NOTHING;
    }

    /* access modifiers changed from: protected */
    public void handleMissingReceiver(Context context, String str) {
        String tag = getTag();
        Log.e(tag, "Rejected the intent for the receiver because it was not registered: " + str + ":" + tag);
    }

    public void setIntentScope(IntentScope intentScope) {
        this.mScope = intentScope;
    }

    /* access modifiers changed from: protected */
    public final synchronized boolean shouldAllowIntent(Context context, Intent intent) {
        return this.mChecker.shouldAllowIntent(context, this, intent, getReporter());
    }

    /* access modifiers changed from: protected */
    public boolean shouldProcessBroadcast(Context context, Intent intent) {
        IntentScope intentScope = this.mScope;
        return intentScope == null || intentScope.enforceReceiverIntent(intent, context) != null;
    }

    public final void onReceive(Context context, Intent intent) {
        onBeforeReceive(context, intent);
        String action = intent.getAction();
        ActionReceiver findReceiverForIntent = findReceiverForIntent(context, action);
        if (findReceiverForIntent != null) {
            endpointObjectFor(findReceiverForIntent);
            if (!DefaultSwitchOffs.general().check(context, findReceiverForIntent, intent)) {
                getIntentLogger().logIntent(this.mEndpointName, null, "deny", intent);
            } else if (!shouldAllowIntent(context, intent) || !shouldProcessBroadcast(context, intent)) {
                getIntentLogger().logIntent(this.mEndpointName, null, "deny", intent);
            } else {
                getIntentLogger().logIntent(this.mEndpointName, null, "allow", intent);
                findReceiverForIntent.onReceive(context, intent, this);
            }
        } else if (!isActionRemoved(action)) {
            getIntentLogger().logIntent(this.mEndpointName, null, "deny", intent);
            handleMissingReceiver(context, action);
        }
    }

    protected static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException("Object is null!");
    }

    /* access modifiers changed from: protected */
    public void onBeforeReceive(Context context, Intent intent) {
        if (this.mEndpointName == null) {
            this.mEndpointName = String.format("%s/%s", context.getPackageName(), getClass().getName());
        }
    }
}
