package X;

import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0ou  reason: invalid class name */
public class AnonymousClass0ou implements Iterator<K> {
    @NullableDecl
    public Map.Entry<K, Collection<V>> A00;
    public final /* synthetic */ AnonymousClass0CP A01;
    public final /* synthetic */ Iterator A02;

    public AnonymousClass0ou(AnonymousClass0CP r1, Iterator it) {
        this.A01 = r1;
        this.A02 = it;
    }

    public final boolean hasNext() {
        return this.A02.hasNext();
    }

    @Override // java.util.Iterator
    public final K next() {
        Map.Entry<K, Collection<V>> entry = (Map.Entry) this.A02.next();
        this.A00 = entry;
        return entry.getKey();
    }

    public final void remove() {
        boolean z = false;
        if (this.A00 != null) {
            z = true;
        }
        Preconditions.checkState(z, "no calls to next() since the last call to remove()");
        Collection<V> value = this.A00.getValue();
        this.A02.remove();
        this.A01.A00.A00 -= value.size();
        value.clear();
        this.A00 = null;
    }
}
