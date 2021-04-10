package defpackage;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* renamed from: Dp1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Dp1 extends AbstractList implements R70, RandomAccess {
    public final R70 F;

    public Dp1(R70 r70) {
        this.F = r70;
    }

    @Override // defpackage.R70
    public R70 g() {
        return this;
    }

    @Override // java.util.List, java.util.AbstractList
    public Object get(int i) {
        return (String) this.F.get(i);
    }

    @Override // defpackage.R70
    public Object i(int i) {
        return this.F.i(i);
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, java.util.AbstractList, java.lang.Iterable
    public Iterator iterator() {
        return new Cp1(this);
    }

    @Override // defpackage.R70
    public List j() {
        return this.F.j();
    }

    @Override // defpackage.R70
    public void k(AbstractC1248Uk uk) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, java.util.AbstractList
    public ListIterator listIterator(int i) {
        return new Bp1(this, i);
    }

    public int size() {
        return this.F.size();
    }
}
