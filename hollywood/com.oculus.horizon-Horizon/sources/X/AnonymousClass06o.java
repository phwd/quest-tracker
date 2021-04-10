package X;

import com.google.common.collect.ImmutableList;

/* renamed from: X.06o  reason: invalid class name */
public class AnonymousClass06o<E> extends AnonymousClass0CW<E> {
    public final ImmutableList<E> A00;

    @Override // X.AnonymousClass0CW
    public final E A00(int i) {
        return this.A00.get(i);
    }

    public AnonymousClass06o(ImmutableList<E> immutableList, int i) {
        super(immutableList.size(), i);
        this.A00 = immutableList;
    }
}
