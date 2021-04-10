package defpackage;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import java.io.IOException;

/* renamed from: fn  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2580fn implements WV, XV {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C2922hn f9951a;

    public C2580fn(C2922hn hnVar, VE1 ve1) {
        this.f9951a = hnVar;
    }

    @Override // defpackage.AbstractC0482Hx
    public final void c(int i) {
        try {
            NH1 nh1 = (NH1) this.f9951a.g;
            Parcel c = nh1.c();
            c.writeInt(i);
            nh1.f(2, c);
        } catch (RemoteException unused) {
            NF1 nf1 = C2922hn.d;
            Object[] objArr = {"onConnectionSuspended", AbstractC5403wH1.class.getSimpleName()};
            if (nf1.d()) {
                nf1.c("Unable to call %s on %s.", objArr);
            }
        }
    }

    @Override // defpackage.AbstractC0777Ms0
    public final void e0(ConnectionResult connectionResult) {
        try {
            NH1 nh1 = (NH1) this.f9951a.g;
            Parcel c = nh1.c();
            AbstractC4376qF1.c(c, connectionResult);
            nh1.f(3, c);
        } catch (RemoteException unused) {
            NF1 nf1 = C2922hn.d;
            Object[] objArr = {"onConnectionFailed", AbstractC5403wH1.class.getSimpleName()};
            if (nf1.d()) {
                nf1.c("Unable to call %s on %s.", objArr);
            }
        }
    }

    @Override // defpackage.AbstractC0482Hx
    public final void f(Bundle bundle) {
        try {
            ML0 ml0 = this.f9951a.l;
            if (ml0 != null) {
                try {
                    ml0.v();
                    this.f9951a.l.p();
                } catch (IOException e) {
                    NF1 nf1 = C2922hn.d;
                    Log.e(nf1.f8536a, nf1.c("Exception when setting GoogleApiClient.", new Object[0]), e);
                    this.f9951a.l = null;
                }
            }
            NH1 nh1 = (NH1) this.f9951a.g;
            Parcel c = nh1.c();
            AbstractC4376qF1.c(c, bundle);
            nh1.f(1, c);
        } catch (RemoteException unused) {
            NF1 nf12 = C2922hn.d;
            Object[] objArr = {"onConnected", AbstractC5403wH1.class.getSimpleName()};
            if (nf12.d()) {
                nf12.c("Unable to call %s on %s.", objArr);
            }
        }
    }
}
