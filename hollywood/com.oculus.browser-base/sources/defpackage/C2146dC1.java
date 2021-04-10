package defpackage;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

/* renamed from: dC1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2146dC1 implements AbstractC4705sB1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ YB1 f9757a;

    public C2146dC1(YB1 yb1, AbstractRunnableC1975cC1 cc1) {
        this.f9757a = yb1;
    }

    @Override // defpackage.AbstractC4705sB1
    public final void a(Bundle bundle) {
        this.f9757a.l.lock();
        try {
            YB1 yb1 = this.f9757a;
            yb1.j = ConnectionResult.F;
            YB1.d(yb1);
        } finally {
            this.f9757a.l.unlock();
        }
    }

    @Override // defpackage.AbstractC4705sB1
    public final void b(ConnectionResult connectionResult) {
        this.f9757a.l.lock();
        try {
            YB1 yb1 = this.f9757a;
            yb1.j = connectionResult;
            YB1.d(yb1);
        } finally {
            this.f9757a.l.unlock();
        }
    }

    @Override // defpackage.AbstractC4705sB1
    public final void c(int i, boolean z) {
        this.f9757a.l.lock();
        try {
            YB1 yb1 = this.f9757a;
            if (yb1.k) {
                yb1.k = false;
                yb1.b.c(i, z);
                yb1.j = null;
                yb1.i = null;
                return;
            }
            yb1.k = true;
            yb1.c.c(i);
            this.f9757a.l.unlock();
        } finally {
            this.f9757a.l.unlock();
        }
    }
}
