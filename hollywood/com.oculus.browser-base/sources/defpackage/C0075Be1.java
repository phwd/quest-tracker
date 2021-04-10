package defpackage;

import java.util.regex.Pattern;

/* renamed from: Be1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0075Be1 extends AbstractC5856yz {

    /* renamed from: a  reason: collision with root package name */
    public final int f7747a;
    public final int b;
    public final boolean c;

    public C0075Be1(int i) {
        this.f7747a = i;
        int d = AbstractC5686xz.d(4);
        this.b = d;
        this.c = i < (d == 0 ? 70 : d);
    }

    @Override // defpackage.AbstractC5856yz
    public boolean a() {
        return this.c && this.b != 0;
    }

    @Override // defpackage.AbstractC5856yz
    public boolean b() {
        return this.c;
    }

    @Override // defpackage.AbstractC5856yz
    public void e(AbstractC0486Hz hz) {
        ((C4017oA) hz).b(17, Integer.valueOf(this.f7747a));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.AbstractC5856yz
    public void g(boolean z, boolean z2) {
        if (z2) {
            boolean z3 = this.c;
            Pattern pattern = AA.f7657a;
            if (z3) {
                AbstractC3364kK0.g("Search.ContextualSearchTapShortDurationSeen", !z, 2);
            } else {
                AbstractC3364kK0.g("Search.ContextualSearchTapLongDurationSeen", !z ? 1 : 0, 2);
            }
            int i = this.f7747a;
            if (z != 0) {
                AbstractC3364kK0.e("Search.ContextualSearchTapDurationSeen", i, 1, 1000, 100);
            } else {
                AbstractC3364kK0.e("Search.ContextualSearchTapDurationNotSeen", i, 1, 1000, 100);
            }
        }
    }

    @Override // defpackage.AbstractC5856yz
    public boolean h() {
        return true;
    }
}
