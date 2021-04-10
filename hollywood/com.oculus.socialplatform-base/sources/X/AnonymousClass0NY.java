package X;

import com.google.common.collect.Multiset;
import java.util.Iterator;

/* renamed from: X.0NY  reason: invalid class name */
public class AnonymousClass0NY extends AnonymousClass0f0<E> {
    public final /* synthetic */ AnonymousClass0Bg A00;

    public AnonymousClass0NY(AnonymousClass0Bg r1) {
        this.A00 = r1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set, java.lang.Iterable
    public final Iterator<Multiset.Entry<E>> iterator() {
        return this.A00.A07();
    }

    public final int size() {
        return this.A00.A06().entrySet().size();
    }

    @Override // X.AnonymousClass0f0
    public final AbstractC05490vp<E> A00() {
        return this.A00;
    }
}
