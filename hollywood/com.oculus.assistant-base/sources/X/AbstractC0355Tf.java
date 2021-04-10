package X;

import com.google.common.collect.AbstractMapBasedMultimap;
import com.google.common.collect.ImmutableEntry;
import com.google.common.collect.Iterators$EmptyModifiableIterator;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.Tf  reason: case insensitive filesystem */
public abstract class AbstractC0355Tf implements Iterator {
    public Object A00 = null;
    public Collection A01 = null;
    public Iterator A02 = Iterators$EmptyModifiableIterator.INSTANCE;
    public final Iterator A03;
    public final /* synthetic */ AbstractMapBasedMultimap A04;

    public AbstractC0355Tf(AbstractMapBasedMultimap abstractMapBasedMultimap) {
        this.A04 = abstractMapBasedMultimap;
        this.A03 = abstractMapBasedMultimap.A01.entrySet().iterator();
    }

    public final boolean hasNext() {
        if (this.A03.hasNext() || this.A02.hasNext()) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!this.A02.hasNext()) {
            Map.Entry entry = (Map.Entry) this.A03.next();
            this.A00 = entry.getKey();
            Collection collection = (Collection) entry.getValue();
            this.A01 = collection;
            this.A02 = collection.iterator();
        }
        return new ImmutableEntry(this.A00, this.A02.next());
    }

    public final void remove() {
        this.A02.remove();
        if (this.A01.isEmpty()) {
            this.A03.remove();
        }
        AbstractMapBasedMultimap abstractMapBasedMultimap = this.A04;
        abstractMapBasedMultimap.A00--;
    }
}
