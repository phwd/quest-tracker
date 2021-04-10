package com.facebook.secure.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.secure.intent.IntentScope;
import com.facebook.secure.logger.IntentLogger;
import com.facebook.secure.logger.IntentLoggerHelper;
import com.facebook.secure.logger.Reporter;
import com.facebook.secure.switchoff.DefaultSwitchOffs;
import com.facebook.secure.trustedapp.checker.Checker;
import com.facebook.secure.trustedapp.checker.CheckerBase;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class SecureBroadcastReceiver extends BroadcastReceiver implements BroadcastReceiverLike {
    private Checker mChecker = CheckerBase.ALWAYS_ALLOW;
    @Nullable
    private String mEndpointName;
    @Nullable
    private IntentScope mScope;

    /* access modifiers changed from: protected */
    public Object endpointObjectFor(ActionReceiver actionReceiver) {
        return actionReceiver;
    }

    /* access modifiers changed from: protected */
    @Nullable
    public abstract ActionReceiver findReceiverForIntent(Context context, String str);

    public abstract IntentFilter getIntentFilter();

    @Nullable
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

    @Nullable
    public IntentScope getIntentScope() {
        return this.mScope;
    }

    public void setIntentScope(IntentScope intentScope) {
        this.mScope = intentScope;
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
        IntentScope intentScope = this.mScope;
        return intentScope == null || intentScope.enforceReceiverIntent(intent, context) != null;
    }

    public final void onReceive(Context context, Intent intent) {
        onBeforeReceive(context);
        String str = (String) checkNotNull(this.mEndpointName);
        String action = intent.getAction();
        if (action == null) {
            Log.e(getTag(), "action is null for SecureBroadcastReceiver");
            return;
        }
        ActionReceiver findReceiverForIntent = findReceiverForIntent(context, action);
        if (findReceiverForIntent != null) {
            if (!DefaultSwitchOffs.general().check(context, endpointObjectFor(findReceiverForIntent), intent)) {
                getIntentLogger().logIntent(str, null, IntentLogger.Status.DENY, intent);
            } else if (!shouldAllowIntent(context, intent) || !shouldProcessBroadcast(context, intent)) {
                getIntentLogger().logIntent(str, null, IntentLogger.Status.DENY, intent);
            } else {
                getIntentLogger().logIntent(str, null, IntentLogger.Status.ALLOW, intent);
                findReceiverForIntent.onReceive(context, intent, this);
            }
        } else if (!isActionRemoved(action)) {
            getIntentLogger().logIntent(str, null, IntentLogger.Status.DENY, intent);
            handleMissingReceiver(context, action);
        }
    }

    protected static <T> T checkNotNull(@Nullable T t) {
        if (t != null) {
            return t;
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
