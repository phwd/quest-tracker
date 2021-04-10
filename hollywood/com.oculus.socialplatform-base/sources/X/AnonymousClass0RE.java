package X;

import java.util.concurrent.atomic.AtomicReferenceArray;
import javax.annotation.Nullable;

/* renamed from: X.0RE  reason: invalid class name */
public final class AnonymousClass0RE {
    public final byte A00 = C01130Rh.A01.get().A00;
    public final AnonymousClass0VD A01;
    @Nullable
    public final AtomicReferenceArray A02;

    public AnonymousClass0RE(int i, AnonymousClass0lg r3) {
        this.A01 = r3.getScopeAwareInjector();
        if (i > 0) {
            this.A02 = new AtomicReferenceArray(i);
        }
    }
}
