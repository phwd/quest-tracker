package X;

import com.google.common.collect.Multiset;
import java.util.Iterator;

/* renamed from: X.031  reason: invalid class name */
public class AnonymousClass031 extends AnonymousClass0Bg<E> {
    public final /* synthetic */ AnonymousClass0Bh A00;

    public AnonymousClass031(AnonymousClass0Bh r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0Bg
    public final Iterator<Multiset.Entry<E>> A07() {
        return this.A00.A07();
    }

    @Override // X.AnonymousClass0Bg, X.AbstractC01640ff, java.util.Collection, java.lang.Iterable
    public final Iterator<E> iterator() {
        AnonymousClass0MC A2a = this.A00.A2a();
        return new C05500vq(A2a, A2a.entrySet().iterator());
    }

    @Override // X.AnonymousClass0Bg
    public final AnonymousClass0MC<E> A06() {
        return this.A00;
    }
}
