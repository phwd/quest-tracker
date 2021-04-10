package X;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.0XV  reason: invalid class name */
public final class AnonymousClass0XV extends AnonymousClass0AU implements Iterable<AnonymousClass0AU> {
    public final List<AnonymousClass0AU> A00 = new ArrayList();

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AnonymousClass0XV) || !((AnonymousClass0XV) obj).A00.equals(this.A00)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // java.lang.Iterable
    public final Iterator<AnonymousClass0AU> iterator() {
        return this.A00.iterator();
    }
}
