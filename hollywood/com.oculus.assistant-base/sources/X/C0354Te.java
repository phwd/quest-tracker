package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableEntry;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.Te  reason: case insensitive filesystem */
public final class C0354Te implements Iterator {
    public Collection A00;
    public final Iterator A01;
    public final /* synthetic */ C1149tp A02;

    public C0354Te(C1149tp tpVar) {
        this.A02 = tpVar;
        this.A01 = tpVar.A00.entrySet().iterator();
    }

    public final boolean hasNext() {
        return this.A01.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Map.Entry entry = (Map.Entry) this.A01.next();
        this.A00 = (Collection) entry.getValue();
        C1149tp tpVar = this.A02;
        Object key = entry.getKey();
        return new ImmutableEntry(key, tpVar.A01.A04(key, (Collection) entry.getValue()));
    }

    public final void remove() {
        boolean z = false;
        if (this.A00 != null) {
            z = true;
        }
        Preconditions.checkState(z, "no calls to next() since the last call to remove()");
        this.A01.remove();
        this.A02.A01.A00 -= this.A00.size();
        this.A00.clear();
        this.A00 = null;
    }
}
