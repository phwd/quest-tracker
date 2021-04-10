package defpackage;

import java.util.Objects;

/* renamed from: xq1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5661xq1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C6001zq1 f11641a;
    public final int b;
    public final int c;

    public C5661xq1(C6001zq1 zq1, int i, int i2) {
        this.f11641a = zq1;
        this.b = i;
        this.c = i2;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C6001zq1 zq1 = this.f11641a;
        int i = this.b;
        int i2 = this.c;
        C4300pq1 pq1 = (C4300pq1) obj;
        Objects.requireNonNull(zq1);
        int i3 = 1;
        AbstractC3100ip1.f10165a.a("GoogleUpdate.StartingUpdateState", pq1 != null);
        C4300pq1 pq12 = C4300pq1.e;
        C4300pq1 pq13 = C4300pq1.e;
        C4300pq1 pq14 = new C4300pq1();
        long currentTimeMillis = System.currentTimeMillis();
        int i4 = pq14.g | 1;
        pq14.g = i4;
        pq14.h = currentTimeMillis;
        int i5 = i4 | 2;
        pq14.g = i5;
        pq14.i = "89.0.4389.105";
        pq14.j = AbstractC5580xK0.c(i != 0 ? i != 1 ? 1 : 3 : 2);
        int i6 = i5 | 4;
        pq14.g = i6;
        if (i2 == 0) {
            i3 = 2;
        } else if (i2 == 1) {
            i3 = 3;
        } else if (i2 == 2) {
            i3 = 4;
        }
        pq14.k = AbstractC5580xK0.b(i3);
        int i7 = i6 | 8;
        pq14.g = i7;
        pq14.g = i7 | 16;
        pq14.l = false;
        C2163dI0.f9768a.b(pq14).c(pq14);
        if (pq14.i()) {
            zq1.f11773a.f9370a.b(new Xm1(pq14));
            return;
        }
        throw new C5488wp1();
    }
}
