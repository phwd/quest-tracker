package X;

import com.google.common.collect.ImmutableList;

/* renamed from: X.0Bc  reason: invalid class name */
public class AnonymousClass0Bc<E> extends AnonymousClass0Nd<E> {
    public final ImmutableList<E> A00;

    @Override // X.AnonymousClass0Nd
    public final E A00(int i) {
        return this.A00.get(i);
    }

    public AnonymousClass0Bc(ImmutableList<E> immutableList, int i) {
        super(immutableList.size(), i);
        this.A00 = immutableList;
    }
}
