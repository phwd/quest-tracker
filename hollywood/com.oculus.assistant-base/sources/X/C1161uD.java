package X;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableEntry;
import com.google.common.collect.ImmutableMultimap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.uD  reason: case insensitive filesystem */
public final class C1161uD extends AbstractC0370Ug {
    public Object A00 = null;
    public Iterator A01 = SQ.A01;
    public final Iterator A02;
    public final /* synthetic */ ImmutableMultimap A03;

    public C1161uD(ImmutableMultimap immutableMultimap) {
        this.A03 = immutableMultimap;
        this.A02 = immutableMultimap.A01.entrySet().iterator();
    }

    public final boolean hasNext() {
        if (this.A01.hasNext() || this.A02.hasNext()) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!this.A01.hasNext()) {
            Map.Entry entry = (Map.Entry) this.A02.next();
            this.A00 = entry.getKey();
            this.A01 = ((ImmutableCollection) entry.getValue()).iterator();
        }
        return new ImmutableEntry(this.A00, this.A01.next());
    }
}
