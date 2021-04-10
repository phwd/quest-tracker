package androidx.lifecycle;

import X.AbstractC05230uw;
import X.AnonymousClass0AO;
import X.AnonymousClass0AS;
import androidx.annotation.NonNull;

public class FullLifecycleObserverAdapter implements AbstractC05230uw {
    public final AbstractC05230uw A00;

    @Override // X.AbstractC05230uw
    public final void A87(@NonNull AnonymousClass0AS r3, @NonNull AnonymousClass0AO r4) {
        switch (r4.ordinal()) {
            case 0:
                throw new NullPointerException("onCreate");
            case 1:
                throw new NullPointerException("onStart");
            case 2:
                throw new NullPointerException("onResume");
            case 3:
                throw new NullPointerException("onPause");
            case 4:
                throw new NullPointerException("onStop");
            case 5:
                throw new NullPointerException("onDestroy");
            case 6:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
            default:
                AbstractC05230uw r0 = this.A00;
                if (r0 != null) {
                    r0.A87(r3, r4);
                    return;
                }
                return;
        }
    }
}
