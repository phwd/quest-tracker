package defpackage;

import java.util.Iterator;

/* renamed from: Cp1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Cp1 implements Iterator {
    public Iterator F;
    public final /* synthetic */ Dp1 G;

    public Cp1(Dp1 dp1) {
        this.G = dp1;
        this.F = dp1.F.iterator();
    }

    public boolean hasNext() {
        return this.F.hasNext();
    }

    @Override // java.util.Iterator
    public Object next() {
        return (String) this.F.next();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }
}
