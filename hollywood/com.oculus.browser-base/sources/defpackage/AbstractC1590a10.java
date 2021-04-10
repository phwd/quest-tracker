package defpackage;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: a10  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1590a10 implements Iterator {
    public int F;
    public int G;
    public boolean H;

    public AbstractC1590a10(int i) {
        this.F = i;
    }

    public abstract Object a(int i);

    public abstract void b(int i);

    public final boolean hasNext() {
        return this.G < this.F;
    }

    @Override // java.util.Iterator
    public Object next() {
        if (hasNext()) {
            Object a2 = a(this.G);
            this.G++;
            this.H = true;
            return a2;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        if (this.H) {
            int i = this.G - 1;
            this.G = i;
            b(i);
            this.F--;
            this.H = false;
            return;
        }
        throw new IllegalStateException();
    }
}
