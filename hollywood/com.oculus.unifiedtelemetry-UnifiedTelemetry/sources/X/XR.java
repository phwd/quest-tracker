package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class XR extends BroadcastReceiver implements AbstractC0386gl {
    public AbstractC0421hW mChecker = FY.A01;
    @Nullable
    public String mEndpointName;
    @Nullable
    public final AbstractC0377gR mScope;

    @Nullable
    public AbstractC0382ge A02() {
        return null;
    }

    @Nullable
    public abstract AbstractC0385gk A03(Context context, String str);

    public Object A04(AbstractC0385gk gkVar) {
        return gkVar;
    }

    public abstract boolean A07(String str);

    /* JADX WARN: Incorrect return type in method signature: <T:Ljava/lang/Object;>(TT;)TT; */
    public static void A00(@Nullable Object obj) {
        if (obj == null) {
            throw new NullPointerException("Object is null!");
        }
    }

    public void A05(Context context) {
        if (this.mEndpointName == null) {
            this.mEndpointName = String.format("%s/%s", context.getPackageName(), getClass().getName());
        }
    }

    public final void onReceive(Context context, Intent intent) {
        boolean A5D;
        A05(context);
        String str = this.mEndpointName;
        A00(str);
        String action = intent.getAction();
        if (action == null) {
            Log.e("SecureBroadcastReceiver", "action is null for SecureBroadcastReceiver");
            return;
        }
        AbstractC0385gk A03 = A03(context, action);
        if (A03 != null) {
            if (C0392gu.A01().A00(context, A04(A03), intent)) {
                synchronized (this) {
                    A5D = this.mChecker.A5D(context, this, intent, A02());
                }
                if (A5D && A06(context, intent)) {
                    A01().A02(str, null, "allow", intent);
                    A03.A3q(context, intent, this);
                    return;
                }
            }
            A01().A02(str, null, "deny", intent);
        } else if (!A07(action)) {
            A01().A02(str, null, "deny", intent);
            Log.e("SecureBroadcastReceiver", AnonymousClass06.A06("Rejected the intent for the receiver because it was not registered: ", action, ":", "SecureBroadcastReceiver"));
        }
    }

    public XT A01() {
        return C0381gb.A00;
    }

    public boolean A06(Context context, Intent intent) {
        return true;
    }
}
