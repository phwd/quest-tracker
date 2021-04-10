package androidx.lifecycle;

import X.AbstractC01030Da;
import X.AbstractC03550cd;
import X.AnonymousClass0DW;
import androidx.annotation.NonNull;

public class FullLifecycleObserverAdapter implements AbstractC03550cd {
    public final AbstractC03550cd A00;

    @Override // X.AbstractC03550cd
    public final void A6c(@NonNull AbstractC01030Da r3, @NonNull AnonymousClass0DW r4) {
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
                AbstractC03550cd r0 = this.A00;
                if (r0 != null) {
                    r0.A6c(r3, r4);
                    return;
                }
                return;
        }
    }
}
