package defpackage;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient;

/* renamed from: pg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4268pg extends AbstractC3413kg {
    public final IBinder g;
    public final /* synthetic */ BaseGmsClient h;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4268pg(BaseGmsClient baseGmsClient, int i, IBinder iBinder, Bundle bundle) {
        super(baseGmsClient, i, bundle);
        this.h = baseGmsClient;
        this.g = iBinder;
    }

    @Override // defpackage.AbstractC3413kg
    public final void b(ConnectionResult connectionResult) {
        PB1 pb1 = this.h.u;
        if (pb1 != null) {
            pb1.f8675a.e0(connectionResult);
        }
        this.h.q(connectionResult);
    }

    @Override // defpackage.AbstractC3413kg
    public final boolean d() {
        try {
            String interfaceDescriptor = this.g.getInterfaceDescriptor();
            if (!this.h.m().equals(interfaceDescriptor)) {
                String m = this.h.m();
                StringBuilder sb = new StringBuilder(String.valueOf(interfaceDescriptor).length() + String.valueOf(m).length() + 34);
                sb.append("service descriptor mismatch: ");
                sb.append(m);
                sb.append(" vs. ");
                sb.append(interfaceDescriptor);
                Log.e("GmsClient", sb.toString());
                return false;
            }
            IInterface e = this.h.e(this.g);
            if (e == null || (!BaseGmsClient.v(this.h, 2, 4, e) && !BaseGmsClient.v(this.h, 3, 4, e))) {
                return false;
            }
            BaseGmsClient baseGmsClient = this.h;
            baseGmsClient.x = null;
            Bundle b = baseGmsClient.b();
            MB1 mb1 = this.h.t;
            if (mb1 == null) {
                return true;
            }
            mb1.f8464a.f(b);
            return true;
        } catch (RemoteException unused) {
            Log.w("GmsClient", "service probably died");
            return false;
        }
    }
}
