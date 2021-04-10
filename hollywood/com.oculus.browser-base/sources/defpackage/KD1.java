package defpackage;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/* renamed from: KD1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class KD1 extends AE1 implements ListIterator {
    public final int F;
    public int G;

    public KD1(int i, int i2) {
        if (i2 < 0 || i2 > i) {
            throw new IndexOutOfBoundsException(DD1.b(i2, i, "index"));
        }
        this.F = i;
        this.G = i2;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean hasNext() {
        return this.G < this.F;
    }

    public final boolean hasPrevious() {
        return this.G > 0;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final Object next() {
        if (hasNext()) {
            int i = this.G;
            this.G = i + 1;
            return ((VD1) this).H.get(i);
        }
        throw new NoSuchElementException();
    }

    public final int nextIndex() {
        return this.G;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (hasPrevious()) {
            int i = this.G - 1;
            this.G = i;
            return ((VD1) this).H.get(i);
        }
        throw new NoSuchElementException();
    }

    public final int previousIndex() {
        return this.G - 1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
