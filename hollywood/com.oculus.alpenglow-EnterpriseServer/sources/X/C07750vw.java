package X;

import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0vw  reason: invalid class name and case insensitive filesystem */
public final class C07750vw {
    public C07760vx A00;
    public final C07710vp A01;

    public final synchronized void A00() {
        A01(C07760vx.A00);
    }

    public final synchronized boolean A01(C07760vx r5) {
        boolean z;
        if (!this.A00.equals(r5)) {
            C07720vq A2E = this.A01.A00(EnumC07690vn.IDS).A2E();
            A2E.A00.putString("/settings/mqtt/id/connection_key", (String) r5.first);
            A2E.A00.putString("/settings/mqtt/id/connection_secret", (String) r5.second);
            A2E.A00();
            this.A00 = r5;
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public C07750vw(C07710vp r5) {
        C07760vx r0;
        this.A01 = r5;
        AnonymousClass0ux A002 = r5.A00(EnumC07690vn.IDS);
        String A4Z = A002.A4Z("/settings/mqtt/id/connection_key", "");
        String A4Z2 = A002.A4Z("/settings/mqtt/id/connection_secret", "");
        if (TextUtils.isEmpty(A4Z) || TextUtils.isEmpty(A4Z2)) {
            r0 = C07760vx.A00;
        } else {
            r0 = new C07760vx(A4Z, A4Z2);
        }
        this.A00 = r0;
    }
}
