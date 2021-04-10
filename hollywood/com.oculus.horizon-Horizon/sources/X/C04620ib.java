package X;

import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0ib  reason: invalid class name and case insensitive filesystem */
public final class C04620ib {
    public AnonymousClass0Y2 A00;
    public final C06510nV A01;

    public final synchronized void A00() {
        A01(AnonymousClass0Y2.A00);
    }

    public final synchronized boolean A01(AnonymousClass0Y2 r5) {
        boolean z;
        if (!this.A00.equals(r5)) {
            C06520nY A2L = this.A01.A00(AnonymousClass0WE.IDS).A2L();
            A2L.A00.putString("/settings/mqtt/id/connection_key", (String) r5.first);
            A2L.A00.putString("/settings/mqtt/id/connection_secret", (String) r5.second);
            A2L.A00();
            this.A00 = r5;
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public C04620ib(C06510nV r5) {
        AnonymousClass0Y2 r0;
        this.A01 = r5;
        AnonymousClass0WD A002 = r5.A00(AnonymousClass0WE.IDS);
        String A4R = A002.A4R("/settings/mqtt/id/connection_key", "");
        String A4R2 = A002.A4R("/settings/mqtt/id/connection_secret", "");
        if (TextUtils.isEmpty(A4R) || TextUtils.isEmpty(A4R2)) {
            r0 = AnonymousClass0Y2.A00;
        } else {
            r0 = new AnonymousClass0Y2(A4R, A4R2);
        }
        this.A00 = r0;
    }
}
