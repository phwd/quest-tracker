package X;

import java.util.Iterator;

/* renamed from: X.0gY  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03980gY implements AbstractC04850jm, Iterable<AbstractC03980gY> {
    public abstract String A02();

    public abstract boolean equals(Object obj);

    public abstract String toString();

    public Iterator<AbstractC03980gY> A01() {
        return C06350mw.A00;
    }

    @Override // java.lang.Iterable
    public final Iterator<AbstractC03980gY> iterator() {
        return A01();
    }
}
