package defpackage;

import java.util.ListIterator;

/* renamed from: Bp1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Bp1 implements ListIterator {
    public ListIterator F;
    public final /* synthetic */ int G;
    public final /* synthetic */ Dp1 H;

    public Bp1(Dp1 dp1, int i) {
        this.H = dp1;
        this.G = i;
        this.F = dp1.F.listIterator(i);
    }

    @Override // java.util.ListIterator
    public void add(Object obj) {
        String str = (String) obj;
        throw new UnsupportedOperationException();
    }

    public boolean hasNext() {
        return this.F.hasNext();
    }

    public boolean hasPrevious() {
        return this.F.hasPrevious();
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public Object next() {
        return (String) this.F.next();
    }

    public int nextIndex() {
        return this.F.nextIndex();
    }

    @Override // java.util.ListIterator
    public Object previous() {
        return (String) this.F.previous();
    }

    public int previousIndex() {
        return this.F.previousIndex();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.ListIterator
    public void set(Object obj) {
        String str = (String) obj;
        throw new UnsupportedOperationException();
    }
}
