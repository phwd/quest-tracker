package X;

import com.google.common.collect.Multiset;
import java.util.Iterator;

/* renamed from: X.06L  reason: invalid class name */
public class AnonymousClass06L extends AnonymousClass0C0<E> {
    public final /* synthetic */ AnonymousClass0C1 A00;

    public AnonymousClass06L(AnonymousClass0C1 r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0C0
    public final AnonymousClass0Ie<E> A03() {
        return this.A00;
    }

    @Override // X.AnonymousClass0C0
    public final Iterator<Multiset.Entry<E>> A04() {
        return this.A00.A07();
    }

    @Override // java.util.Collection, X.AnonymousClass0YC, X.AnonymousClass0C0, java.lang.Iterable
    public final Iterator<E> iterator() {
        AnonymousClass0Ie A26 = this.A00.A26();
        return new AnonymousClass0tE(A26, A26.entrySet().iterator());
    }
}
