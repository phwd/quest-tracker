package X;

import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.Tg  reason: case insensitive filesystem */
public final class C0356Tg implements Iterator {
    public Map.Entry A00;
    public final /* synthetic */ C0152Du A01;
    public final /* synthetic */ Iterator A02;

    public C0356Tg(C0152Du du, Iterator it) {
        this.A01 = du;
        this.A02 = it;
    }

    public final boolean hasNext() {
        return this.A02.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Map.Entry entry = (Map.Entry) this.A02.next();
        this.A00 = entry;
        return entry.getKey();
    }

    public final void remove() {
        boolean z = false;
        if (this.A00 != null) {
            z = true;
        }
        Preconditions.checkState(z, "no calls to next() since the last call to remove()");
        Collection collection = (Collection) this.A00.getValue();
        this.A02.remove();
        this.A01.A00.A00 -= collection.size();
        collection.clear();
        this.A00 = null;
    }
}
