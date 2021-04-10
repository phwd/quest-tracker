package X;

import javax.annotation.Nullable;

/* renamed from: X.0jd  reason: invalid class name */
public class AnonymousClass0jd implements AnonymousClass0b1 {
    public final /* synthetic */ C02490ac A00;

    public AnonymousClass0jd(C02490ac r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0b1
    public final void report(String str) {
        AnonymousClass0NO.A0A("SecurePendingIntent", str);
    }

    @Override // X.AnonymousClass0b1
    public final void report(String str, String str2, @Nullable Throwable th) {
        AnonymousClass0NO.A0D(String.format("tag: %s, file: %s, category: %s", "SecurePendingIntent", "FbnsRegistrarRetry", str), str2, th);
    }
}
