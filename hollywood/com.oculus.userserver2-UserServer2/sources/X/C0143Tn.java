package X;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.util.HashSet;

/* renamed from: X.Tn  reason: case insensitive filesystem */
public class C0143Tn implements BC {
    public final /* synthetic */ Au A00;

    @Override // X.BC
    public final void A2Q(@NonNull Fragment fragment, @NonNull AnonymousClass6N r5) {
        boolean z;
        int i;
        synchronized (r5) {
            z = r5.A00;
        }
        if (!z) {
            Au au = this.A00;
            HashSet<AnonymousClass6N> hashSet = au.A0B.get(fragment);
            if (hashSet != null && hashSet.remove(r5) && hashSet.isEmpty()) {
                au.A0B.remove(fragment);
                if (fragment.A05 < 3) {
                    Au.A06(au, fragment);
                    AX ax = fragment.A0C;
                    if (ax == null) {
                        i = 0;
                    } else {
                        i = ax.A01;
                    }
                    au.A0X(fragment, i);
                }
            }
        }
    }

    public C0143Tn(Au au) {
        this.A00 = au;
    }

    @Override // X.BC
    public final void A2h(@NonNull Fragment fragment, @NonNull AnonymousClass6N r5) {
        Au au = this.A00;
        if (au.A0B.get(fragment) == null) {
            au.A0B.put(fragment, new HashSet<>());
        }
        au.A0B.get(fragment).add(r5);
    }
}
