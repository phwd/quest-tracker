package defpackage;

import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.cast.ApplicationMetadata;
import java.io.IOException;
import java.util.Objects;

/* renamed from: en  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2409en implements BM0 {

    /* renamed from: a  reason: collision with root package name */
    public String f9878a;
    public final /* synthetic */ C2922hn b;

    public C2409en(C2922hn hnVar, String str) {
        this.b = hnVar;
        this.f9878a = str;
    }

    @Override // defpackage.BM0
    public final void a(AM0 am0) {
        AbstractC1008Qm qm = (AbstractC1008Qm) am0;
        C2922hn hnVar = this.b;
        NF1 nf1 = C2922hn.d;
        Objects.requireNonNull(hnVar);
        try {
            if (qm.b().x()) {
                NF1 nf12 = C2922hn.d;
                Object[] objArr = {this.f9878a};
                if (nf12.d()) {
                    nf12.c("%s() -> success result", objArr);
                }
                this.b.l = new ML0(new MF1(), this.b.i);
                try {
                    C2922hn hnVar2 = this.b;
                    hnVar2.l.u(hnVar2.k);
                    this.b.l.v();
                    this.b.l.p();
                    C2922hn hnVar3 = this.b;
                    C2490fD1 fd1 = hnVar3.j;
                    ML0 ml0 = hnVar3.l;
                    hnVar3.e();
                    Objects.requireNonNull(fd1);
                } catch (IOException e) {
                    NF1 nf13 = C2922hn.d;
                    Log.e(nf13.f8536a, nf13.c("Exception when setting GoogleApiClient.", new Object[0]), e);
                    this.b.l = null;
                }
                AbstractC5403wH1 wh1 = this.b.g;
                ApplicationMetadata t = qm.t();
                String h = qm.h();
                String i = qm.i();
                boolean c = qm.c();
                NH1 nh1 = (NH1) wh1;
                Parcel c2 = nh1.c();
                AbstractC4376qF1.c(c2, t);
                c2.writeString(h);
                c2.writeString(i);
                c2.writeInt(c ? 1 : 0);
                nh1.f(4, c2);
                return;
            }
            NF1 nf14 = C2922hn.d;
            Object[] objArr2 = {this.f9878a};
            if (nf14.d()) {
                nf14.c("%s() -> failure result", objArr2);
            }
            AbstractC5403wH1 wh12 = this.b.g;
            int i2 = qm.b().K;
            NH1 nh12 = (NH1) wh12;
            Parcel c3 = nh12.c();
            c3.writeInt(i2);
            nh12.f(5, c3);
        } catch (RemoteException unused) {
            NF1 nf15 = C2922hn.d;
            Object[] objArr3 = {"methods", AbstractC5403wH1.class.getSimpleName()};
            if (nf15.d()) {
                nf15.c("Unable to call %s on %s.", objArr3);
            }
        }
    }
}
