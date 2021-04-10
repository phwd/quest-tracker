package X;

import com.google.common.collect.Multiset;
import java.util.Iterator;

/* renamed from: X.0CJ  reason: invalid class name */
public class AnonymousClass0CJ extends AnonymousClass0dJ<E> {
    public final /* synthetic */ AnonymousClass06q A00;

    public AnonymousClass0CJ(AnonymousClass06q r1) {
        this.A00 = r1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<Multiset.Entry<E>> iterator() {
        return this.A00.A04();
    }

    public final int size() {
        return this.A00.A03().entrySet().size();
    }

    @Override // X.AnonymousClass0dJ
    public final AnonymousClass0r9<E> A00() {
        return this.A00;
    }
}
