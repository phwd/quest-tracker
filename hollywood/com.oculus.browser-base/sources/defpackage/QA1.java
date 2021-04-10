package defpackage;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

/* renamed from: QA1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class QA1 implements WV, XV {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ JA1 f8740a;

    public QA1(JA1 ja1, IA1 ia1) {
        this.f8740a = ja1;
    }

    @Override // defpackage.AbstractC0482Hx
    public final void c(int i) {
    }

    @Override // defpackage.AbstractC0777Ms0
    public final void e0(ConnectionResult connectionResult) {
        this.f8740a.b.lock();
        try {
            if (this.f8740a.l && !connectionResult.x()) {
                this.f8740a.j();
                this.f8740a.h();
            } else {
                this.f8740a.o(connectionResult);
            }
        } finally {
            this.f8740a.b.unlock();
        }
    }

    @Override // defpackage.AbstractC0482Hx
    public final void f(Bundle bundle) {
        JA1 ja1 = this.f8740a;
        if (ja1.r.h) {
            ja1.b.lock();
            try {
                JA1 ja12 = this.f8740a;
                AbstractC5045uB1 ub1 = ja12.k;
                if (ub1 == null) {
                    ja12.b.unlock();
                    return;
                }
                ((C4752sV0) ub1).z(new OA1(this.f8740a));
            } finally {
                this.f8740a.b.unlock();
            }
        } else {
            ((C4752sV0) ja1.k).z(new OA1(this.f8740a));
        }
    }
}
