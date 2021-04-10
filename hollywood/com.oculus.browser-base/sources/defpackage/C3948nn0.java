package defpackage;

import java.util.regex.Pattern;

/* renamed from: nn0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3948nn0 extends AbstractC5856yz {

    /* renamed from: a  reason: collision with root package name */
    public final int f10513a;
    public final boolean b;
    public final int c;

    public C3948nn0(C4700sA sAVar, int i) {
        boolean z = true;
        int d = AbstractC5686xz.d(1);
        this.f10513a = d;
        int i2 = (int) (((float) i) * sAVar.c);
        this.c = i2;
        this.b = i2 >= d ? false : z;
    }

    @Override // defpackage.AbstractC5856yz
    public boolean a() {
        return this.b;
    }

    @Override // defpackage.AbstractC5856yz
    public void c() {
        if (this.f10513a > 0) {
            boolean z = this.b;
            Pattern pattern = AA.f7657a;
            AbstractC3100ip1.f10165a.a("Search.ContextualSearchScreenTopSuppressed", z);
        }
    }

    @Override // defpackage.AbstractC5856yz
    public void e(AbstractC0486Hz hz) {
        ((C4017oA) hz).b(6, Integer.valueOf(this.c));
    }

    @Override // defpackage.AbstractC5856yz
    public void g(boolean z, boolean z2) {
        if (z2) {
            int i = this.c;
            Pattern pattern = AA.f7657a;
            if (z2) {
                AbstractC3364kK0.e(z ? "Search.ContextualSearchTopLocationSeen" : "Search.ContextualSearchTopLocationNotSeen", i, 1, 250, 50);
            }
        }
    }
}
