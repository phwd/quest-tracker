package X;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class M5 extends AbstractC0241hm implements Iterable<AbstractC0241hm> {
    public final List<AbstractC0241hm> A00 = new ArrayList();

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof M5) || !((M5) obj).A00.equals(this.A00)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // java.lang.Iterable
    public final Iterator<AbstractC0241hm> iterator() {
        return this.A00.iterator();
    }
}
