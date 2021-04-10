package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0kf  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02970kf extends BroadcastReceiver implements AnonymousClass0jg {
    public AnonymousClass0kS mChecker = AnonymousClass0TP.A01;
    @Nullable
    public String mEndpointName;
    @Nullable
    public AnonymousClass0jJ mScope;

    public synchronized void addAdditionalPermissionChecker(AnonymousClass0kS r2) {
        this.mChecker = this.mChecker.A1R(r2);
    }

    public Object endpointObjectFor(AbstractC02700jf r1) {
        return r1;
    }

    @Nullable
    public abstract AbstractC02700jf findReceiverForIntent(Context context, String str);

    public abstract IntentFilter getIntentFilter();

    @Nullable
    public AbstractC02660jW getReporter() {
        return null;
    }

    public String getTag() {
        return "SecureBroadcastReceiver";
    }

    public abstract boolean isActionRemoved(String str);

    public final synchronized boolean shouldAllowIntent(Context context, Intent intent) {
        return this.mChecker.AAL(context, this, intent, getReporter());
    }

    public static <T> T checkNotNull(@Nullable T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException("Object is null!");
    }

    public void handleMissingReceiver(Context context, String str) {
        Log.e("SecureBroadcastReceiver", AnonymousClass006.A0B("Rejected the intent for the receiver because it was not registered: ", str, ":", "SecureBroadcastReceiver"));
    }

    public void onBeforeReceive(Context context) {
        if (this.mEndpointName == null) {
            this.mEndpointName = String.format("%s/%s", context.getPackageName(), getClass().getName());
        }
    }

    public boolean shouldProcessBroadcast(Context context, Intent intent) {
        AnonymousClass0jJ r0 = this.mScope;
        if (r0 == null || r0.A2p(intent, context) != null) {
            return true;
        }
        return false;
    }

    public AbstractC03010kk getIntentLogger() {
        return C02640jT.A00;
    }

    @Nullable
    public AnonymousClass0jJ getIntentScope() {
        return this.mScope;
    }

    public final void onReceive(Context context, Intent intent) {
        onBeforeReceive(context);
        String str = this.mEndpointName;
        checkNotNull(str);
        String action = intent.getAction();
        if (action == null) {
            Log.e("SecureBroadcastReceiver", "action is null for SecureBroadcastReceiver");
            return;
        }
        AbstractC02700jf findReceiverForIntent = findReceiverForIntent(context, action);
        if (findReceiverForIntent != null) {
            if (!C02780jp.A01().A00(context, endpointObjectFor(findReceiverForIntent), intent) || !shouldAllowIntent(context, intent) || !shouldProcessBroadcast(context, intent)) {
                getIntentLogger().logIntent(str, null, "deny", intent);
                return;
            }
            getIntentLogger().logIntent(str, null, "allow", intent);
            findReceiverForIntent.onReceive(context, intent, this);
        } else if (!isActionRemoved(action)) {
            getIntentLogger().logIntent(str, null, "deny", intent);
            handleMissingReceiver(context, action);
        }
    }

    public void setIntentScope(AnonymousClass0jJ r1) {
        this.mScope = r1;
    }
}
