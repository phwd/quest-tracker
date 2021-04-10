package X;

import javax.annotation.Nullable;

/* renamed from: X.0ma  reason: invalid class name and case insensitive filesystem */
public class C06150ma implements AnonymousClass0b1 {
    public final /* synthetic */ AnonymousClass0YF A00;

    public C06150ma(AnonymousClass0YF r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass0b1
    public final void report(String str) {
        AnonymousClass0NO.A0A("KeepaliveManager-SecurePendingIntent", str);
    }

    @Override // X.AnonymousClass0b1
    public final void report(String str, String str2, @Nullable Throwable th) {
        AnonymousClass0NO.A0G("%s-%s", "KeepaliveManager-SecurePendingIntent", th == null ? new Object[]{str, str2} : new Object[]{str, str2, th});
    }
}
