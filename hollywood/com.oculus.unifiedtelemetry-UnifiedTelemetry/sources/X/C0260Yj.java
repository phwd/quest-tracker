package X;

import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.Yj  reason: case insensitive filesystem */
public class C0260Yj {
    public final String accountId;
    @Nullable
    public final String claim = null;
    public final String userId;

    public C0260Yj(String str, String str2) {
        this.userId = str2;
        this.accountId = str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x000d, code lost:
        if (r1 == null) goto L_0x000f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0028, code lost:
        if (r0.equals(r1) == false) goto L_0x002a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002a, code lost:
        X.YE.A00(r3, "account_id", r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void A01(X.YE r3, X.MG r4) {
        /*
            r2 = this;
            java.lang.String r1 = r2.userId
            java.lang.String r0 = "uid"
            X.YE.A00(r3, r0, r1)
            java.lang.String r0 = r2.userId
            if (r0 != 0) goto L_0x0022
            java.lang.String r1 = r2.accountId
            if (r1 != 0) goto L_0x002a
        L_0x000f:
            java.lang.String r0 = r2.claim
            if (r0 == 0) goto L_0x0021
            X.YF r1 = r4.A01()
            java.lang.String r0 = r2.claim
            X.YF.A01(r1, r0)
            java.lang.String r0 = "claims"
            r3.A0C(r0, r1)
        L_0x0021:
            return
        L_0x0022:
            java.lang.String r1 = r2.accountId
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L_0x000f
        L_0x002a:
            java.lang.String r0 = "account_id"
            X.YE.A00(r3, r0, r1)
            goto L_0x000f
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0260Yj.A01(X.YE, X.MG):void");
    }
}
