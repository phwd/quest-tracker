package X;

import java.util.concurrent.atomic.AtomicReferenceArray;
import javax.annotation.Nullable;

public final class QC {
    public final byte A00 = Qe.A01.get().A00;
    public final AbstractC0094Hs A01;
    @Nullable
    public final AtomicReferenceArray A02;

    public QC(int i, AbstractC0247Xu xu) {
        this.A01 = xu.getScopeAwareInjector();
        if (i > 0) {
            this.A02 = new AtomicReferenceArray(i);
        }
    }
}
