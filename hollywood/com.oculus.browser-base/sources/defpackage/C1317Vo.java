package defpackage;

import android.os.Handler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: Vo  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1317Vo implements Iterable {
    public static final C1195To F = new C1195To(null);
    public final Handler G;
    public final int H;
    public final List I;

    /* renamed from: J  reason: collision with root package name */
    public final Runnable f9106J;
    public boolean K;
    public boolean L;

    public C1317Vo() {
        this.G = new Handler();
        this.I = new ArrayList();
        this.f9106J = new RunnableC0951Po(this);
        this.H = -1;
    }

    public final void a() {
        this.L = false;
        int size = this.I.size();
        while (true) {
            size--;
            if (size >= 0) {
                C1134So so = (C1134So) this.I.get(size);
                if (!so.a()) {
                    C5653xo xoVar = so.f8918a;
                    if (xoVar.g()) {
                        ((ServiceConnectionC2244dp) xoVar.y).a();
                    }
                }
            } else {
                return;
            }
        }
    }

    public final int b(C5653xo xoVar) {
        for (int i = 0; i < this.I.size(); i++) {
            if (((C1134So) this.I.get(i)).f8918a == xoVar) {
                return i;
            }
        }
        return -1;
    }

    public final void c(int i) {
        int i2;
        C1134So so = (C1134So) this.I.remove(i);
        int i3 = 0;
        int i4 = 0;
        while (i4 < this.I.size() && F.compare((C1134So) this.I.get(i4), so) < 0) {
            i4++;
        }
        this.I.add(i4, so);
        if (this.K) {
            if (!so.a()) {
                C5653xo xoVar = so.f8918a;
                if (xoVar.B != 0) {
                    xoVar.p(0, 0);
                    return;
                }
                return;
            }
            boolean z = i4 == 0;
            boolean z2 = i4 == this.I.size() - 1;
            if (!z) {
                i3 = ((C1134So) this.I.get(i4 - 1)).f8918a.C;
            }
            if (z2) {
                i2 = Integer.MAX_VALUE;
            } else {
                i2 = ((C1134So) this.I.get(i4 + 1)).f8918a.C;
            }
            C5653xo xoVar2 = so.f8918a;
            int i5 = xoVar2.C;
            if (i5 <= i3 || i5 >= i2) {
                int i6 = i2 - i3;
                if (i6 > 65536) {
                    xoVar2.p(1, i2 - 32768);
                } else if (i6 > 2) {
                    xoVar2.p(1, (i6 / 2) + i3);
                } else {
                    e();
                }
                if (!this.L) {
                    this.G.postDelayed(this.f9106J, 1000);
                    this.L = true;
                }
            }
        }
    }

    public final void e() {
        int i = 2147450879;
        for (int size = this.I.size() - 1; size >= 0; size--) {
            C1134So so = (C1134So) this.I.get(size);
            if (so.a()) {
                so.f8918a.p(1, i);
                i -= 32768;
            } else {
                return;
            }
        }
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return new C1256Uo(this);
    }

    public C1317Vo(int i) {
        this.G = new Handler();
        this.I = new ArrayList();
        this.f9106J = new RunnableC1012Qo(this);
        this.H = i;
    }
}
