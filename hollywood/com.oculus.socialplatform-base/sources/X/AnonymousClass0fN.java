package X;

import com.google.common.collect.ImmutableMultiset;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;

/* renamed from: X.0fN  reason: invalid class name */
public class AnonymousClass0fN extends AbstractC05710wh<E> {
    public int A00;
    @MonotonicNonNullDecl
    public E A01;
    public final /* synthetic */ ImmutableMultiset A02;
    public final /* synthetic */ Iterator A03;

    public AnonymousClass0fN(ImmutableMultiset immutableMultiset, Iterator it) {
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
            AnonymousClass0f2 r1 = (AnonymousClass0f2) this.A03.next();
            this.A01 = (E) r1.A01();
            i = r1.A00();
            this.A00 = i;
        }
        this.A00 = i - 1;
        return this.A01;
    }
}
