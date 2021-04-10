package X;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.util.HashSet;

/* renamed from: X.0s7  reason: invalid class name */
public class AnonymousClass0s7 implements AbstractC004709v {
    public final /* synthetic */ AbstractC003209a A00;

    @Override // X.AbstractC004709v
    public final void A5r(@NonNull Fragment fragment, @NonNull AnonymousClass05d r5) {
        boolean z;
        synchronized (r5) {
            z = r5.A02;
        }
        if (!z) {
            AbstractC003209a r2 = this.A00;
            HashSet<AnonymousClass05d> hashSet = r2.A0C.get(fragment);
            if (hashSet != null && hashSet.remove(r5) && hashSet.isEmpty()) {
                r2.A0C.remove(fragment);
                if (fragment.mState < 3) {
                    AbstractC003209a.A08(r2, fragment);
                    r2.A0e(fragment, fragment.getStateAfterAnimating());
                }
            }
        }
    }

    public AnonymousClass0s7(AbstractC003209a r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC004709v
    public final void A6w(@NonNull Fragment fragment, @NonNull AnonymousClass05d r5) {
        AbstractC003209a r2 = this.A00;
        if (r2.A0C.get(fragment) == null) {
            r2.A0C.put(fragment, new HashSet<>());
        }
        r2.A0C.get(fragment).add(r5);
    }
}
