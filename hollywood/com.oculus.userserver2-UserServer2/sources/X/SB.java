package X;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.security.basecomponent.OculusSecureBroadcastReceiverBase;

@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class SB extends BroadcastReceiver implements f6 {
    public AbstractC0224fr mChecker = BO.A01;
    @Nullable
    public String mEndpointName;
    @Nullable
    public final AbstractC0196ei mScope;

    public void A00(Context context) {
        if (this.mEndpointName == null) {
            this.mEndpointName = String.format("%s/%s", context.getPackageName(), getClass().getName());
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: X.f5 */
    /* JADX WARN: Multi-variable type inference failed */
    public final void onReceive(Context context, Intent intent) {
        SD sd;
        SB sb;
        SD sd2;
        AbstractC0201ew ewVar;
        boolean A3f;
        SD sd3;
        A00(context);
        String str = this.mEndpointName;
        if (str != null) {
            String action = intent.getAction();
            if (action == null) {
                Log.e("SecureBroadcastReceiver", "action is null for SecureBroadcastReceiver");
                return;
            }
            OculusSecureBroadcastReceiverBase oculusSecureBroadcastReceiverBase = (OculusSecureBroadcastReceiverBase) this;
            f5 f5Var = oculusSecureBroadcastReceiverBase.mActionReceiver;
            boolean z = this instanceof OculusSecureBroadcastReceiverBase;
            if (f5Var != 0) {
                if (!z) {
                    sb = f5Var;
                } else {
                    sb = this;
                }
                if (fF.A01().A00(context, sb, intent)) {
                    synchronized (this) {
                        AbstractC0224fr frVar = this.mChecker;
                        if (!z) {
                            ewVar = null;
                        } else {
                            ewVar = oculusSecureBroadcastReceiverBase.mReporter;
                        }
                        A3f = frVar.A3f(context, this, intent, ewVar);
                    }
                    if (A3f && A01(context, intent)) {
                        if (!z) {
                            sd3 = C0200et.A00;
                        } else {
                            sd3 = oculusSecureBroadcastReceiverBase.mOculusIntentLogger;
                        }
                        sd3.A00(str, null, "allow", intent);
                        f5Var.A2Y(context, intent, this);
                        return;
                    }
                }
                if (!z) {
                    sd2 = C0200et.A00;
                } else {
                    sd2 = oculusSecureBroadcastReceiverBase.mOculusIntentLogger;
                }
                sd2.A00(str, null, "deny", intent);
                return;
            }
            if (!z) {
                sd = C0200et.A00;
            } else {
                sd = oculusSecureBroadcastReceiverBase.mOculusIntentLogger;
            }
            sd.A00(str, null, "deny", intent);
            Log.e("SecureBroadcastReceiver", AnonymousClass06.A05("Rejected the intent for the receiver because it was not registered: ", action, ":", "SecureBroadcastReceiver"));
            return;
        }
        throw new NullPointerException("Object is null!");
    }

    public boolean A01(Context context, Intent intent) {
        return true;
    }
}
