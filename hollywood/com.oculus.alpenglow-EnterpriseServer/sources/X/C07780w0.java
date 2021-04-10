package X;

import javax.annotation.Nullable;

/* renamed from: X.0w0  reason: invalid class name and case insensitive filesystem */
public class C07780w0 implements AbstractC04970iB {
    public final /* synthetic */ C07770vz A00;

    public C07780w0(C07770vz r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC04970iB
    public final void A7P(String str) {
        AnonymousClass0NK.A03("SecurePendingIntent", str);
    }

    @Override // X.AbstractC04970iB
    public final void A7Q(String str, String str2, @Nullable Throwable th) {
        AnonymousClass0NK.A05(String.format("tag: %s, file: %s, category: %s", "SecurePendingIntent", "FbnsRegistrarRetry", str), str2, th);
    }
}
