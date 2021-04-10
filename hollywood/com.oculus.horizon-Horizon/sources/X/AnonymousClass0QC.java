package X;

import java.util.concurrent.atomic.AtomicReferenceArray;
import javax.annotation.Nullable;

/* renamed from: X.0QC  reason: invalid class name */
public final class AnonymousClass0QC {
    public final byte A00 = AnonymousClass0Qe.A01.get().A00;
    public final AnonymousClass0Iy A01;
    @Nullable
    public final AtomicReferenceArray A02;

    public AnonymousClass0QC(int i, AbstractC06640p5 r3) {
        this.A01 = r3.getScopeAwareInjector();
        if (i > 0) {
            this.A02 = new AtomicReferenceArray(i);
        }
    }
}
