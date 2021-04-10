package X;

import com.google.common.collect.AbstractBiMap;
import java.util.Iterator;
import java.util.Set;

/* renamed from: X.0Bi  reason: invalid class name */
public class AnonymousClass0Bi extends AnonymousClass0NM<V> {
    public final Set<V> A00;
    public final /* synthetic */ AbstractBiMap A01;

    public AnonymousClass0Bi(AbstractBiMap abstractBiMap) {
        this.A01 = abstractBiMap;
        this.A00 = abstractBiMap.A00.keySet();
    }

    @Override // X.AbstractC01640ff, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<V> iterator() {
        return new AnonymousClass0fB(this.A01.entrySet().iterator());
    }

    @Override // X.AnonymousClass0NM
    /* renamed from: A05 */
    public final Set<V> A02() {
        return this.A00;
    }

    @Override // X.AbstractC05120uD
    public final String toString() {
        return A01();
    }

    @Override // X.AbstractC01640ff
    public final Object[] toArray() {
        return toArray(new Object[size()]);
    }

    @Override // X.AbstractC01640ff, java.util.Collection, java.util.Set
    public final <T> T[] toArray(T[] tArr) {
        return (T[]) A04(tArr);
    }
}
