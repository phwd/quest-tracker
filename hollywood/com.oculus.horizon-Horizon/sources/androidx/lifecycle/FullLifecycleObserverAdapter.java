package androidx.lifecycle;

import X.AbstractC07290ro;
import X.AnonymousClass0AN;
import X.AnonymousClass0AR;
import androidx.annotation.NonNull;

public class FullLifecycleObserverAdapter implements AbstractC07290ro {
    public final AbstractC07290ro A00;

    @Override // X.AbstractC07290ro
    public final void A70(@NonNull AnonymousClass0AR r3, @NonNull AnonymousClass0AN r4) {
        switch (r4.ordinal()) {
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
                AbstractC07290ro r0 = this.A00;
                if (r0 != null) {
                    r0.A70(r3, r4);
                    return;
                }
                return;
        }
    }
}
