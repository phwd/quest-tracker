package defpackage;

import java.util.regex.Pattern;

/* renamed from: iK0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3023iK0 extends AbstractC5856yz {

    /* renamed from: a  reason: collision with root package name */
    public final int f10131a;
    public final boolean b;
    public final int c;

    public C3023iK0(C4700sA sAVar) {
        long j = sAVar.o;
        boolean z = false;
        if (j > 0) {
            this.f10131a = (int) ((System.nanoTime() - j) / 1000000);
        } else {
            this.f10131a = 0;
        }
        int d = AbstractC5686xz.d(5);
        d = d <= 0 ? 300 : d;
        this.c = d;
        int i = this.f10131a;
        if (i > 0 && i < d) {
            z = true;
        }
        this.b = z;
    }

    @Override // defpackage.AbstractC5856yz
    public boolean a() {
        return this.b && this.c > 0;
    }

    @Override // defpackage.AbstractC5856yz
    public void c() {
        boolean z = this.b;
        Pattern pattern = AA.f7657a;
        AbstractC3100ip1.f10165a.a("Search.ContextualSearchRecentScrollSuppression", z);
    }

    @Override // defpackage.AbstractC5856yz
    public void e(AbstractC0486Hz hz) {
        ((C4017oA) hz).b(5, Integer.valueOf(this.f10131a));
    }
}
