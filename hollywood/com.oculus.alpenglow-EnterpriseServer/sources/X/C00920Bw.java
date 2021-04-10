package X;

import com.google.common.collect.ImmutableList;

/* renamed from: X.0Bw  reason: invalid class name and case insensitive filesystem */
public class C00920Bw<E> extends AnonymousClass0JQ<E> {
    public final ImmutableList<E> A00;

    @Override // X.AnonymousClass0JQ
    public final E A00(int i) {
        return this.A00.get(i);
    }

    public C00920Bw(ImmutableList<E> immutableList, int i) {
        super(immutableList.size(), i);
        this.A00 = immutableList;
    }
}
