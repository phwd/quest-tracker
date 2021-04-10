package X;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class U5 extends M4 implements Iterable<M4> {
    public final List<M4> A00 = new ArrayList();

    @Override // X.M4
    public final String A03() {
        List<M4> list = this.A00;
        if (list.size() == 1) {
            return list.get(0).A03();
        }
        throw new IllegalStateException();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof U5) || !((U5) obj).A00.equals(this.A00)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // java.lang.Iterable
    public final Iterator<M4> iterator() {
        return this.A00.iterator();
    }
}
