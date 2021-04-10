package X;

import java.util.Iterator;

public abstract class OA implements Iterable {
    @Override // java.lang.Iterable
    public final Iterator iterator() {
        if (this instanceof AnonymousClass0U) {
            return ((AnonymousClass0U) this).A00.values().iterator();
        }
        if (!(this instanceof AnonymousClass0Z)) {
            return Q7.A00;
        }
        return ((AnonymousClass0Z) this).A00.iterator();
    }

    public abstract String toString();
}
