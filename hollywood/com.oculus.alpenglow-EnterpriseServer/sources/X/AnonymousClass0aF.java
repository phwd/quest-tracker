package X;

import java.util.Iterator;

/* renamed from: X.0aF  reason: invalid class name */
public abstract class AnonymousClass0aF implements AbstractC05970lj, Iterable<AnonymousClass0aF> {
    public abstract String A02();

    public abstract boolean equals(Object obj);

    public abstract String toString();

    public Iterator<AnonymousClass0aF> A01() {
        return C07150oo.A00;
    }

    @Override // java.lang.Iterable
    public final Iterator<AnonymousClass0aF> iterator() {
        return A01();
    }
}
