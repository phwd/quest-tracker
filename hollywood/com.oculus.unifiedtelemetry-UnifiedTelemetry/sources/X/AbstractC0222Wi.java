package X;

import java.util.Iterator;

/* renamed from: X.Wi  reason: case insensitive filesystem */
public abstract class AbstractC0222Wi implements q6, Iterable<AbstractC0222Wi> {
    public Number A01() {
        return null;
    }

    public boolean A03() {
        return false;
    }

    public abstract Integer A04();

    public String A05() {
        return null;
    }

    public abstract String A06();

    public abstract boolean equals(Object obj);

    public abstract String toString();

    @Override // java.lang.Iterable
    public final Iterator<AbstractC0222Wi> iterator() {
        return A02();
    }

    public Iterator<AbstractC0222Wi> A02() {
        return MC.A00;
    }
}
