package X;

import javax.annotation.Nullable;

/* renamed from: X.1R3  reason: invalid class name */
public class AnonymousClass1R3 implements AbstractC02660jW {
    public final /* synthetic */ AnonymousClass1WV A00;

    public AnonymousClass1R3(AnonymousClass1WV r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC02660jW
    public final void report(String str) {
        AnonymousClass0MD.A06("KeepaliveManager-SecurePendingIntent", str);
    }

    @Override // X.AbstractC02660jW
    public final void report(String str, String str2, @Nullable Throwable th) {
        Object[] objArr;
        if (th == null) {
            objArr = new Object[]{str, str2};
        } else {
            objArr = new Object[]{str, str2, th};
        }
        AnonymousClass0MD.A0B("%s-%s", "KeepaliveManager-SecurePendingIntent", objArr);
    }
}
