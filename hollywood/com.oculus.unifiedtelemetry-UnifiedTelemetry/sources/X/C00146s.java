package X;

import com.google.common.collect.ImmutableList;

/* renamed from: X.6s  reason: invalid class name and case insensitive filesystem */
public class C00146s<E> extends AbstractC0062Ba<E> {
    public final ImmutableList<E> A00;

    @Override // X.AbstractC0062Ba
    public final E A00(int i) {
        return this.A00.get(i);
    }

    public C00146s(ImmutableList<E> immutableList, int i) {
        super(immutableList.size(), i);
        this.A00 = immutableList;
    }
}
