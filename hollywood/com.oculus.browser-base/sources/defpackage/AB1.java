package defpackage;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.signin.internal.zak;
import java.util.Objects;
import java.util.Set;

/* renamed from: AB1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class AB1 implements Runnable {
    public final /* synthetic */ zak F;
    public final /* synthetic */ BinderC5725yB1 G;

    public AB1(BinderC5725yB1 yb1, zak zak) {
        this.G = yb1;
        this.F = zak;
    }

    public final void run() {
        BinderC5725yB1 yb1 = this.G;
        zak zak = this.F;
        Objects.requireNonNull(yb1);
        ConnectionResult connectionResult = zak.G;
        if (connectionResult.A()) {
            ResolveAccountResponse resolveAccountResponse = zak.H;
            ConnectionResult connectionResult2 = resolveAccountResponse.H;
            if (!connectionResult2.A()) {
                String valueOf = String.valueOf(connectionResult2);
                StringBuilder sb = new StringBuilder(valueOf.length() + 48);
                sb.append("Sign-in succeeded with resolve account failure: ");
                sb.append(valueOf);
                Log.wtf("SignInCoordinator", sb.toString(), new Exception());
                ((C1670aW) yb1.h).b(connectionResult2);
                yb1.g.disconnect();
                return;
            }
            AbstractC5895zB1 zb1 = yb1.h;
            AbstractC4757sY x = resolveAccountResponse.x();
            Set set = yb1.e;
            C1670aW aWVar = (C1670aW) zb1;
            Objects.requireNonNull(aWVar);
            if (x == null || set == null) {
                Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
                aWVar.b(new ConnectionResult(4));
            } else {
                aWVar.c = x;
                aWVar.d = set;
                if (aWVar.e) {
                    ((BaseGmsClient) aWVar.f9434a).j(x, set);
                }
            }
        } else {
            ((C1670aW) yb1.h).b(connectionResult);
        }
        yb1.g.disconnect();
    }
}
