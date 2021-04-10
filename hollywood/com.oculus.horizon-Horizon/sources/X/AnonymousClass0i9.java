package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import androidx.annotation.Nullable;
import com.facebook.GraphRequest;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0i9  reason: invalid class name */
public abstract class AnonymousClass0i9 extends BroadcastReceiver implements AnonymousClass0b9 {
    public AbstractC02910bj mChecker = AbstractC00900Hh.A01;
    @Nullable
    public String mEndpointName;
    @Nullable
    public AbstractC02610as mScope;

    public synchronized void addAdditionalPermissionChecker(AbstractC02910bj r2) {
        this.mChecker = this.mChecker.A1E(r2);
    }

    public Object endpointObjectFor(AnonymousClass0b8 r1) {
        return r1;
    }

    @Nullable
    public abstract AnonymousClass0b8 findReceiverForIntent(Context context, String str);

    public abstract IntentFilter getIntentFilter();

    @Nullable
    public AnonymousClass0b1 getReporter() {
        return null;
    }

    public String getTag() {
        return "SecureBroadcastReceiver";
    }

    public abstract boolean isActionRemoved(String str);

    public final synchronized boolean shouldAllowIntent(Context context, Intent intent) {
        return this.mChecker.A8w(context, this, intent, getReporter());
    }

    public static <T> T checkNotNull(@Nullable T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException("Object is null!");
    }

    public void handleMissingReceiver(Context context, String str) {
        Log.e("SecureBroadcastReceiver", AnonymousClass006.A08("Rejected the intent for the receiver because it was not registered: ", str, ":", "SecureBroadcastReceiver"));
    }

    public void onBeforeReceive(Context context) {
        if (this.mEndpointName == null) {
            this.mEndpointName = String.format(GraphRequest.GRAPH_PATH_FORMAT, context.getPackageName(), getClass().getName());
        }
    }

    public boolean shouldProcessBroadcast(Context context, Intent intent) {
        AbstractC02610as r0 = this.mScope;
        if (r0 == null || r0.A2V(intent, context) != null) {
            return true;
        }
        return false;
    }

    public AbstractC04590iB getIntentLogger() {
        return C02650ay.A00;
    }

    @Nullable
    public AbstractC02610as getIntentScope() {
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
        AnonymousClass0b8 findReceiverForIntent = findReceiverForIntent(context, action);
        if (findReceiverForIntent != null) {
            if (!C02670bA.A01().A00(context, endpointObjectFor(findReceiverForIntent), intent) || !shouldAllowIntent(context, intent) || !shouldProcessBroadcast(context, intent)) {
                getIntentLogger().A00(str, null, "deny", intent);
                return;
            }
            getIntentLogger().A00(str, null, "allow", intent);
            findReceiverForIntent.onReceive(context, intent, this);
        } else if (!isActionRemoved(action)) {
            getIntentLogger().A00(str, null, "deny", intent);
            handleMissingReceiver(context, action);
        }
    }

    public void setIntentScope(AbstractC02610as r1) {
        this.mScope = r1;
    }
}
