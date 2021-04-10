package androidx.lifecycle;

import X.AN;
import X.AR;
import X.Zx;
import androidx.annotation.NonNull;

public class FullLifecycleObserverAdapter implements Zx {
    public final Zx A00;

    @Override // X.Zx
    public final void A42(@NonNull AR ar, @NonNull AN an) {
        switch (an.ordinal()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                throw null;
            case 6:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
            default:
                Zx zx = this.A00;
                if (zx != null) {
                    zx.A42(ar, an);
                    return;
                }
                return;
        }
    }
}
