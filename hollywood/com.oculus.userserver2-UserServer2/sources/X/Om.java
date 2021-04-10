package X;

import java.util.concurrent.atomic.AtomicReferenceArray;
import javax.annotation.Nullable;

public final class Om {
    public final byte A00 = PE.A01.get().A00;
    public final BX A01;
    @Nullable
    public final AtomicReferenceArray A02;

    public Om(int i, SZ sz) {
        this.A01 = sz.getScopeAwareInjector();
        if (i > 0) {
            this.A02 = new AtomicReferenceArray(i);
        }
    }
}
