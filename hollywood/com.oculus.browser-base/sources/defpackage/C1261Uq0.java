package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: Uq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1261Uq0 implements Iterator {
    public int F;
    public int G;
    public boolean H;
    public final /* synthetic */ C1322Vq0 I;

    public C1261Uq0(C1322Vq0 vq0, AbstractC1200Tq0 tq0) {
        this.I = vq0;
        vq0.G++;
        this.F = vq0.F.size();
    }

    public final void a() {
        if (!this.H) {
            this.H = true;
            C1322Vq0 vq0 = this.I;
            int i = vq0.G - 1;
            vq0.G = i;
            if (i <= 0 && vq0.I) {
                vq0.I = false;
                int size = vq0.F.size();
                while (true) {
                    size--;
                    if (size < 0) {
                        return;
                    }
                    if (vq0.F.get(size) == null) {
                        vq0.F.remove(size);
                    }
                }
            }
        }
    }

    public void b() {
        a();
        C1322Vq0 vq0 = this.I;
        vq0.G++;
        this.F = vq0.F.size();
        this.H = false;
        this.G = 0;
    }

    public boolean hasNext() {
        int i = this.G;
        while (i < this.F && C1322Vq0.a(this.I, i) == null) {
            i++;
        }
        if (i < this.F) {
            return true;
        }
        a();
        return false;
    }

    @Override // java.util.Iterator
    public Object next() {
        while (true) {
            int i = this.G;
            if (i >= this.F || C1322Vq0.a(this.I, i) != null) {
                int i2 = this.G;
            } else {
                this.G++;
            }
        }
        int i22 = this.G;
        if (i22 < this.F) {
            C1322Vq0 vq0 = this.I;
            this.G = i22 + 1;
            return C1322Vq0.a(vq0, i22);
        }
        a();
        throw new NoSuchElementException();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
