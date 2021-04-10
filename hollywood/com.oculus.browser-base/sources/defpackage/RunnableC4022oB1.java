package defpackage;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.util.Collections;

/* renamed from: oB1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RunnableC4022oB1 implements Runnable {
    public final /* synthetic */ ConnectionResult F;
    public final /* synthetic */ C1670aW G;

    public RunnableC4022oB1(C1670aW aWVar, ConnectionResult connectionResult) {
        this.G = aWVar;
        this.F = connectionResult;
    }

    public final void run() {
        AbstractC4757sY sYVar;
        C1670aW aWVar = this.G;
        ZV zv = (ZV) aWVar.f.P.get(aWVar.b);
        if (zv != null) {
            if (this.F.A()) {
                C1670aW aWVar2 = this.G;
                aWVar2.e = true;
                if (aWVar2.f9434a.requiresSignIn()) {
                    C1670aW aWVar3 = this.G;
                    if (aWVar3.e && (sYVar = aWVar3.c) != null) {
                        ((BaseGmsClient) aWVar3.f9434a).j(sYVar, aWVar3.d);
                        return;
                    }
                    return;
                }
                try {
                    AbstractC2129d7 d7Var = this.G.f9434a;
                    KV kv = (KV) d7Var;
                    ((BaseGmsClient) d7Var).j(null, kv.requiresSignIn() ? kv.B : Collections.emptySet());
                } catch (SecurityException e) {
                    Log.e("GoogleApiManager", "Failed to get service from broker. ", e);
                    zv.e0(new ConnectionResult(10));
                }
            } else {
                zv.e0(this.F);
            }
        }
    }
}
