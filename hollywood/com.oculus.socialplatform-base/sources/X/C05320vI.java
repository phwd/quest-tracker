package X;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.util.HashSet;

/* renamed from: X.0vI  reason: invalid class name and case insensitive filesystem */
public class C05320vI implements AbstractC004309w {
    public final /* synthetic */ AnonymousClass09b A00;

    @Override // X.AbstractC004309w
    public final void A6s(@NonNull Fragment fragment, @NonNull AnonymousClass05j r5) {
        boolean z;
        int i;
        synchronized (r5) {
            z = r5.A00;
        }
        if (!z) {
            AnonymousClass09b r2 = this.A00;
            HashSet<AnonymousClass05j> hashSet = r2.A0B.get(fragment);
            if (hashSet != null && hashSet.remove(r5) && hashSet.isEmpty()) {
                r2.A0B.remove(fragment);
                if (fragment.A05 < 3) {
                    AnonymousClass09b.A06(r2, fragment);
                    AnonymousClass09E r0 = fragment.A0C;
                    if (r0 == null) {
                        i = 0;
                    } else {
                        i = r0.A01;
                    }
                    r2.A0Y(fragment, i);
                }
            }
        }
    }

    public C05320vI(AnonymousClass09b r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC004309w
    public final void A85(@NonNull Fragment fragment, @NonNull AnonymousClass05j r5) {
        AnonymousClass09b r2 = this.A00;
        if (r2.A0B.get(fragment) == null) {
            r2.A0B.put(fragment, new HashSet<>());
        }
        r2.A0B.get(fragment).add(r5);
    }
}
