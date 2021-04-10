package X;

import com.google.common.collect.ImmutableEntry;
import java.util.Iterator;
import java.util.Map;

public abstract class UZ implements Iterator {
    public final Iterator A00;

    public final boolean hasNext() {
        return this.A00.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object next = this.A00.next();
        if (this instanceof C1178uZ) {
            return ((AbstractC1179ua) next).A01();
        }
        if (this instanceof C1172uT) {
            return new ImmutableEntry(next, ((C1172uT) this).A00.apply(next));
        }
        if (!(this instanceof C1171uS)) {
            return ((Map.Entry) next).getKey();
        }
        return ((Map.Entry) next).getValue();
    }

    public final void remove() {
        this.A00.remove();
    }

    public UZ(Iterator it) {
        if (it != null) {
            this.A00 = it;
            return;
        }
        throw null;
    }
}
