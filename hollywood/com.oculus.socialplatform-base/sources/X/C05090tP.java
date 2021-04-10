package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractBiMap;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0tP  reason: invalid class name and case insensitive filesystem */
public class C05090tP implements Iterator<Map.Entry<K, V>> {
    @NullableDecl
    public Map.Entry<K, V> A00;
    public final /* synthetic */ AbstractBiMap A01;
    public final /* synthetic */ Iterator A02;

    public C05090tP(AbstractBiMap abstractBiMap, Iterator it) {
        this.A01 = abstractBiMap;
        this.A02 = it;
    }

    public final boolean hasNext() {
        return this.A02.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Map.Entry<K, V> entry = (Map.Entry) this.A02.next();
        this.A00 = entry;
        return new AnonymousClass0Ni(this.A01, entry);
    }

    public final void remove() {
        boolean z = false;
        if (this.A00 != null) {
            z = true;
        }
        Preconditions.checkState(z, "no calls to next() since the last call to remove()");
        V value = this.A00.getValue();
        this.A02.remove();
        this.A01.A00.A01.remove(value);
        this.A00 = null;
    }
}
