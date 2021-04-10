package X;

import java.util.Iterator;

/* renamed from: X.0iC  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC02170iC implements AbstractC03680oI, Iterable<AbstractC02170iC> {
    public abstract String A02();

    public abstract boolean equals(Object obj);

    public abstract String toString();

    public Iterator<AbstractC02170iC> A01() {
        return AnonymousClass0rK.A00;
    }

    @Override // java.lang.Iterable
    public final Iterator<AbstractC02170iC> iterator() {
        return A01();
    }
}
