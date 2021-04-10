package X;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.0cI  reason: invalid class name and case insensitive filesystem */
public final class C03140cI extends AbstractC08820ye implements Iterable<AbstractC08820ye> {
    public final List<AbstractC08820ye> A00 = new ArrayList();

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C03140cI) || !((C03140cI) obj).A00.equals(this.A00)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // java.lang.Iterable
    public final Iterator<AbstractC08820ye> iterator() {
        return this.A00.iterator();
    }
}
