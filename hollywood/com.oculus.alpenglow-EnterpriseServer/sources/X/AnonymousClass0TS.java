package X;

import android.content.pm.PackageManager;
import android.content.pm.Signature;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSetMultimap;

/* renamed from: X.0TS  reason: invalid class name */
public final class AnonymousClass0TS {
    public final PackageManager A00;
    public final ImmutableSet<Signature> A01;
    public final ImmutableSetMultimap<Signature, String> A02;

    public AnonymousClass0TS(AbstractC07440t7<Signature, String> r6, PackageManager packageManager) {
        AnonymousClass0J8 r4 = new AnonymousClass0J8();
        AnonymousClass0Y5 r3 = new AnonymousClass0Y5();
        for (Signature signature : r6.keySet()) {
            if (r6.A1q(signature, "*|all_packages|*")) {
                r4.A00(signature);
            } else {
                r3.A05(signature, r6.A2s(signature));
            }
        }
        this.A01 = r4.build();
        this.A02 = r3.A02();
        this.A00 = packageManager;
    }
}
