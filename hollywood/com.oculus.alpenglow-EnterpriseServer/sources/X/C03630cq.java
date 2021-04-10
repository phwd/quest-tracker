package X;

import androidx.annotation.NonNull;
import java.util.HashSet;

/* renamed from: X.0cq  reason: invalid class name and case insensitive filesystem */
public class C03630cq implements AnonymousClass0D4 {
    public final /* synthetic */ AnonymousClass0Cj A00;

    @Override // X.AnonymousClass0D4
    public final void A5y(@NonNull AnonymousClass0MN r4, @NonNull AnonymousClass091 r5) {
        boolean z;
        int i;
        synchronized (r5) {
            z = r5.A00;
        }
        if (!z) {
            AnonymousClass0Cj r2 = this.A00;
            HashSet<AnonymousClass091> hashSet = r2.A0B.get(r4);
            if (hashSet != null && hashSet.remove(r5) && hashSet.isEmpty()) {
                r2.A0B.remove(r4);
                if (r4.A05 < 3) {
                    AnonymousClass0Cj.A06(r2, r4);
                    AnonymousClass0CM r0 = r4.A0C;
                    if (r0 == null) {
                        i = 0;
                    } else {
                        i = r0.A01;
                    }
                    r2.A0X(r4, i);
                }
            }
        }
    }

    public C03630cq(AnonymousClass0Cj r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0D4
    public final void A6Z(@NonNull AnonymousClass0MN r4, @NonNull AnonymousClass091 r5) {
        AnonymousClass0Cj r2 = this.A00;
        if (r2.A0B.get(r4) == null) {
            r2.A0B.put(r4, new HashSet<>());
        }
        r2.A0B.get(r4).add(r5);
    }
}
