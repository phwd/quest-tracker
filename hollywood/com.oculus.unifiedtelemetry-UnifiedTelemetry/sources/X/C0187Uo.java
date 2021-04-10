package X;

import com.google.common.collect.ImmutableMultiset;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;

/* renamed from: X.Uo  reason: case insensitive filesystem */
public class C0187Uo extends AnonymousClass8f<E> {
    public int A00;
    @MonotonicNonNullDecl
    public E A01;
    public final /* synthetic */ ImmutableMultiset A02;
    public final /* synthetic */ Iterator A03;

    public C0187Uo(ImmutableMultiset immutableMultiset, Iterator it) {
        this.A02 = immutableMultiset;
        this.A03 = it;
    }

    public final boolean hasNext() {
        if (this.A00 > 0 || this.A03.hasNext()) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final E next() {
        int i = this.A00;
        if (i <= 0) {
            AbstractC0181Ug ug = (AbstractC0181Ug) this.A03.next();
            this.A01 = (E) ug.A01();
            i = ug.A00();
            this.A00 = i;
        }
        this.A00 = i - 1;
        return this.A01;
    }
}
