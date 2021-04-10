package X;

import com.google.common.collect.ImmutableList;

public final class W3 extends E1 {
    public final ImmutableList A00;

    @Override // X.E1
    public final Object A00(int i) {
        return this.A00.get(i);
    }

    public W3(ImmutableList immutableList, int i) {
        super(immutableList.size(), i);
        this.A00 = immutableList;
    }
}
