package defpackage;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/* renamed from: QP0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class QP0 extends MK0 {

    /* renamed from: a  reason: collision with root package name */
    public Ou1 f8759a;
    public final ViewPager2 b;
    public final RecyclerView c;
    public final LinearLayoutManager d;
    public int e;
    public int f;
    public PP0 g = new PP0();
    public int h;
    public int i;
    public boolean j;
    public boolean k;
    public boolean l;
    public boolean m;

    public QP0(ViewPager2 viewPager2) {
        this.b = viewPager2;
        RecyclerView recyclerView = viewPager2.P;
        this.c = recyclerView;
        this.d = (LinearLayoutManager) recyclerView.U;
        e();
    }

    @Override // defpackage.MK0
    public void a(RecyclerView recyclerView, int i2) {
        Ou1 ou1;
        int i3 = this.e;
        boolean z = true;
        if (!(i3 == 1 && this.f == 1) && i2 == 1) {
            this.m = false;
            this.e = 1;
            int i4 = this.i;
            if (i4 != -1) {
                this.h = i4;
                this.i = -1;
            } else if (this.h == -1) {
                this.h = this.d.k1();
            }
            d(1);
            return;
        }
        if (!(i3 == 1 || i3 == 4) || i2 != 2) {
            if ((i3 == 1 || i3 == 4) && i2 == 0) {
                f();
                if (!this.k) {
                    int i5 = this.g.f8689a;
                    if (!(i5 == -1 || (ou1 = this.f8759a) == null)) {
                        ou1.b(i5, 0.0f, 0);
                    }
                } else {
                    PP0 pp0 = this.g;
                    if (pp0.c == 0) {
                        int i6 = this.h;
                        int i7 = pp0.f8689a;
                        if (i6 != i7) {
                            c(i7);
                        }
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    d(0);
                    e();
                }
            }
            if (this.e == 2 && i2 == 0 && this.l) {
                f();
                PP0 pp02 = this.g;
                if (pp02.c == 0) {
                    int i8 = this.i;
                    int i9 = pp02.f8689a;
                    if (i8 != i9) {
                        if (i9 == -1) {
                            i9 = 0;
                        }
                        c(i9);
                    }
                    d(0);
                    e();
                }
            }
        } else if (this.k) {
            d(2);
            this.j = true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        if ((r6 < 0) == r4.b.a()) goto L_0x0022;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0025  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0039  */
    @Override // defpackage.MK0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(androidx.recyclerview.widget.RecyclerView r5, int r6, int r7) {
        /*
        // Method dump skipped, instructions count: 118
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.QP0.b(androidx.recyclerview.widget.RecyclerView, int, int):void");
    }

    public final void c(int i2) {
        Ou1 ou1 = this.f8759a;
        if (ou1 != null) {
            ou1.c(i2);
        }
    }

    public final void d(int i2) {
        if ((this.e != 3 || this.f != 0) && this.f != i2) {
            this.f = i2;
            Ou1 ou1 = this.f8759a;
            if (ou1 != null) {
                ou1.a(i2);
            }
        }
    }

    public final void e() {
        this.e = 0;
        this.f = 0;
        this.g.a();
        this.h = -1;
        this.i = -1;
        this.j = false;
        this.k = false;
        this.m = false;
        this.l = false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0151, code lost:
        if (r4[r2 - 1][1] >= r3) goto L_0x0154;
     */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x017e  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0183  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x018b  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x017b A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void f() {
        /*
        // Method dump skipped, instructions count: 429
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.QP0.f():void");
    }
}
