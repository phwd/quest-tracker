package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0aq  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02800aq extends BroadcastReceiver implements AbstractC05000iI {
    public AnonymousClass0j1 mChecker = AnonymousClass0LS.A01;
    @Nullable
    public String mEndpointName;
    @Nullable
    public AbstractC04930hy mScope;

    @Nullable
    public AbstractC04970iB A02() {
        return null;
    }

    @Nullable
    public abstract AbstractC04990iH A03(Context context, String str);

    public Object A04(AbstractC04990iH r1) {
        return r1;
    }

    public abstract boolean A07(String str);

    public AbstractC02820as A01() {
        return C04950i8.A00;
    }

    public void A05(Context context) {
        if (this.mEndpointName == null) {
            this.mEndpointName = String.format("%s/%s", context.getPackageName(), getClass().getName());
        }
    }

    public boolean A06(Context context, Intent intent) {
        AbstractC04930hy r0 = this.mScope;
        if (r0 == null || r0.A2M(intent, context) != null) {
            return true;
        }
        return false;
    }

    public final void onReceive(Context context, Intent intent) {
        boolean A8K;
        A05(context);
        String str = this.mEndpointName;
        if (str != null) {
            String action = intent.getAction();
            if (action == null) {
                Log.e("SecureBroadcastReceiver", "action is null for SecureBroadcastReceiver");
                return;
            }
            AbstractC04990iH A03 = A03(context, action);
            if (A03 != null) {
                if (C05040iP.A01().A00(context, A04(A03), intent)) {
                    synchronized (this) {
                        A8K = this.mChecker.A8K(context, this, intent, A02());
                    }
                    if (A8K && A06(context, intent)) {
                        A01().A00(str, null, "allow", intent);
                        A03.onReceive(context, intent, this);
                        return;
                    }
                }
                A01().A00(str, null, "deny", intent);
            } else if (!A07(action)) {
                A01().A00(str, null, "deny", intent);
                Log.e("SecureBroadcastReceiver", AnonymousClass006.A08("Rejected the intent for the receiver because it was not registered: ", action, ":", "SecureBroadcastReceiver"));
            }
        } else {
            throw new NullPointerException("Object is null!");
        }
    }
}
