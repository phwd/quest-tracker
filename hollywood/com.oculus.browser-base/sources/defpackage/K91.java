package defpackage;

import org.chromium.chrome.browser.tab.Tab;

/* renamed from: K91  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class K91 extends C4935tb0 {
    public int A(int i) {
        for (int size = size() - 1; size >= 0; size--) {
            UH0 uh0 = ((C4765sb0) get(size)).b;
            if (uh0.f(J91.f8274a) == 1 && uh0.f(AbstractC0516Ij0.f8246a) == i) {
                return size;
            }
        }
        return -1;
    }

    public void B(int i, boolean z) {
        if (i >= 0 && i < size()) {
            int i2 = z ? 4 : 3;
            UH0 uh0 = ((C4765sb0) this.G.get(i)).b;
            SH0 sh0 = AbstractC5106ub1.k;
            if (uh0.f(sh0) != i2) {
                ((C4765sb0) this.G.get(i)).b.l(sh0, i2);
            }
        }
    }

    public void C(int i, boolean z) {
        if (i >= 0 && i < size()) {
            int i2 = z ? 2 : 1;
            UH0 uh0 = ((C4765sb0) this.G.get(i)).b;
            SH0 sh0 = AbstractC5106ub1.k;
            if (uh0.f(sh0) != i2) {
                ((C4765sb0) this.G.get(i)).b.l(sh0, i2);
                ((C4765sb0) this.G.get(i)).b.k(J91.b, z ? 0.8f : 1.0f);
            }
        }
    }

    public void D(Tab tab, int i) {
        if (((C4765sb0) this.G.get(i)).b.f(J91.f8274a) == 0) {
            ((C4765sb0) this.G.get(i)).b.l(AbstractC5106ub1.f11420a, tab.getId());
        }
    }

    @Override // defpackage.AbstractC1965c90
    public void add(int i, Object obj) {
        this.G.add(i, (C4765sb0) obj);
        o(i, 1);
    }

    public int v(int i) {
        if (i < 0) {
            return -1;
        }
        if (i > size()) {
            i = size();
        }
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            if (((C4765sb0) get(i3)).b.f(J91.f8274a) == 0) {
                i2++;
            }
        }
        return i2;
    }

    public int w(int i) {
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (((C4765sb0) get(i2)).b.f(J91.f8274a) == 0) {
                return i2;
            }
        }
        return -1;
    }

    public int x(int i) {
        for (int i2 = 0; i2 < size(); i2++) {
            UH0 uh0 = ((C4765sb0) get(i2)).b;
            if (uh0.f(J91.f8274a) == 0 && uh0.f(AbstractC5106ub1.f11420a) == i) {
                return i2;
            }
        }
        return -1;
    }

    public int y(int i) {
        int i2 = -1;
        if (i < 0) {
            return -1;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < size(); i4++) {
            if (((C4765sb0) get(i4)).b.f(J91.f8274a) == 0) {
                int i5 = i3 + 1;
                if (i3 == i) {
                    return i4;
                }
                i3 = i5;
                i2 = i4;
            }
        }
        return i2 + 1;
    }

    public int z() {
        for (int size = size() - 1; size >= 0; size--) {
            if (((C4765sb0) get(size)).b.f(J91.f8274a) == 1) {
                return size;
            }
        }
        return -1;
    }
}
