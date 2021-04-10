package defpackage;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

/* renamed from: bC1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1804bC1 implements AbstractC4705sB1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ YB1 f9520a;

    public C1804bC1(YB1 yb1, AbstractRunnableC1975cC1 cc1) {
        this.f9520a = yb1;
    }

    @Override // defpackage.AbstractC4705sB1
    public final void a(Bundle bundle) {
        this.f9520a.l.lock();
        try {
            YB1 yb1 = this.f9520a;
            Bundle bundle2 = yb1.h;
            if (bundle2 == null) {
                yb1.h = bundle;
            } else if (bundle != null) {
                bundle2.putAll(bundle);
            }
            YB1 yb12 = this.f9520a;
            yb12.i = ConnectionResult.F;
            YB1.d(yb12);
        } finally {
            this.f9520a.l.unlock();
        }
    }

    @Override // defpackage.AbstractC4705sB1
    public final void b(ConnectionResult connectionResult) {
        this.f9520a.l.lock();
        try {
            YB1 yb1 = this.f9520a;
            yb1.i = connectionResult;
            YB1.d(yb1);
        } finally {
            this.f9520a.l.unlock();
        }
    }

    @Override // defpackage.AbstractC4705sB1
    public final void c(int i, boolean z) {
        ConnectionResult connectionResult;
        this.f9520a.l.lock();
        try {
            YB1 yb1 = this.f9520a;
            if (!yb1.k && (connectionResult = yb1.j) != null) {
                if (connectionResult.A()) {
                    YB1 yb12 = this.f9520a;
                    yb12.k = true;
                    yb12.d.c(i);
                    this.f9520a.l.unlock();
                    return;
                }
            }
            YB1 yb13 = this.f9520a;
            yb13.k = false;
            yb13.b.c(i, z);
            yb13.j = null;
            yb13.i = null;
        } finally {
            this.f9520a.l.unlock();
        }
    }
}
