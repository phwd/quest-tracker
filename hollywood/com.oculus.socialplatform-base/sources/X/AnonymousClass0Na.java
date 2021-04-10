package X;

import com.google.common.collect.Multiset;
import java.util.Iterator;

/* renamed from: X.0Na  reason: invalid class name */
public class AnonymousClass0Na extends AnonymousClass0f0<E> {
    public final /* synthetic */ AnonymousClass0fp A00;

    public AnonymousClass0Na(AnonymousClass0fp r1) {
        this.A00 = r1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<Multiset.Entry<E>> iterator() {
        return this.A00.A06();
    }

    public final int size() {
        return this.A00.A04();
    }

    @Override // X.AnonymousClass0f0
    public final AbstractC05490vp<E> A00() {
        return this.A00;
    }
}
