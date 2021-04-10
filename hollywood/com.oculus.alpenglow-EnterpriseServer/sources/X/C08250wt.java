package X;

import javax.annotation.Nullable;

/* renamed from: X.0wt  reason: invalid class name and case insensitive filesystem */
public class C08250wt implements AbstractC04970iB {
    public final /* synthetic */ C08200wo A00;

    public C08250wt(C08200wo r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC04970iB
    public final void A7P(String str) {
        AnonymousClass0NK.A03("KeepaliveManager-SecurePendingIntent", str);
    }

    @Override // X.AbstractC04970iB
    public final void A7Q(String str, String str2, @Nullable Throwable th) {
        Object[] objArr;
        if (th == null) {
            objArr = new Object[]{str, str2};
        } else {
            objArr = new Object[]{str, str2, th};
        }
        AnonymousClass0NK.A08("%s-%s", "KeepaliveManager-SecurePendingIntent", objArr);
    }
}
