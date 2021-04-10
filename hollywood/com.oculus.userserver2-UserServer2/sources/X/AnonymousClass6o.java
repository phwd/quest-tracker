package X;

import com.google.common.collect.ImmutableList;

/* renamed from: X.6o  reason: invalid class name */
public class AnonymousClass6o<E> extends AnonymousClass9n<E> {
    public final ImmutableList<E> A00;

    @Override // X.AnonymousClass9n
    public final E A00(int i) {
        return this.A00.get(i);
    }

    public AnonymousClass6o(ImmutableList<E> immutableList, int i) {
        super(immutableList.size(), i);
        this.A00 = immutableList;
    }
}
