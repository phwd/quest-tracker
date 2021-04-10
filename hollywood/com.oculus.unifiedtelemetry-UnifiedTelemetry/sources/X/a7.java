package X;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.util.HashSet;

public class a7 implements AbstractC00409v {
    public final /* synthetic */ AbstractC00279a A00;

    @Override // X.AbstractC00409v
    public final void A3e(@NonNull Fragment fragment, @NonNull AnonymousClass5d r5) {
        boolean z;
        int i;
        synchronized (r5) {
            z = r5.A00;
        }
        if (!z) {
            AbstractC00279a r2 = this.A00;
            HashSet<AnonymousClass5d> hashSet = r2.A0B.get(fragment);
            if (hashSet != null && hashSet.remove(r5) && hashSet.isEmpty()) {
                r2.A0B.remove(fragment);
                if (fragment.A05 < 3) {
                    AbstractC00279a.A06(r2, fragment);
                    AnonymousClass9D r0 = fragment.A0C;
                    if (r0 == null) {
                        i = 0;
                    } else {
                        i = r0.A01;
                    }
                    r2.A0X(fragment, i);
                }
            }
        }
    }

    public a7(AbstractC00279a r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC00409v
    public final void A41(@NonNull Fragment fragment, @NonNull AnonymousClass5d r5) {
        AbstractC00279a r2 = this.A00;
        if (r2.A0B.get(fragment) == null) {
            r2.A0B.put(fragment, new HashSet<>());
        }
        r2.A0B.get(fragment).add(r5);
    }
}
