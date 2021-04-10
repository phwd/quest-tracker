package X;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.0eW  reason: invalid class name */
public final class AnonymousClass0eW extends AnonymousClass13R implements Iterable<AnonymousClass13R> {
    public final List<AnonymousClass13R> A00 = new ArrayList();

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AnonymousClass0eW) || !((AnonymousClass0eW) obj).A00.equals(this.A00)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // java.lang.Iterable
    public final Iterator<AnonymousClass13R> iterator() {
        return this.A00.iterator();
    }
}
