package defpackage;

import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.signin.internal.zak;

/* renamed from: RA1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RA1 extends AbstractC2143dB1 {
    public final /* synthetic */ JA1 b;
    public final /* synthetic */ zak c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RA1(AbstractC1801bB1 bb1, JA1 ja1, zak zak) {
        super(bb1);
        this.b = ja1;
        this.c = zak;
    }

    @Override // defpackage.AbstractC2143dB1
    public final void a() {
        JA1 ja1 = this.b;
        zak zak = this.c;
        boolean z = false;
        if (ja1.n(0)) {
            ConnectionResult connectionResult = zak.G;
            if (connectionResult.A()) {
                ResolveAccountResponse resolveAccountResponse = zak.H;
                ConnectionResult connectionResult2 = resolveAccountResponse.H;
                if (!connectionResult2.A()) {
                    String valueOf = String.valueOf(connectionResult2);
                    StringBuilder sb = new StringBuilder(valueOf.length() + 48);
                    sb.append("Sign-in succeeded with resolve account failure: ");
                    sb.append(valueOf);
                    Log.wtf("GACConnecting", sb.toString(), new Exception());
                    ja1.o(connectionResult2);
                    return;
                }
                ja1.n = true;
                ja1.o = resolveAccountResponse.x();
                ja1.p = resolveAccountResponse.I;
                ja1.q = resolveAccountResponse.f9659J;
                ja1.h();
                return;
            }
            if (ja1.l && !connectionResult.x()) {
                z = true;
            }
            if (z) {
                ja1.j();
                ja1.h();
                return;
            }
            ja1.o(connectionResult);
        }
    }
}
