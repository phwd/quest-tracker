package X;

import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.rti.push.service.FbnsService;
import java.util.UUID;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0ie  reason: invalid class name and case insensitive filesystem */
public final class C04640ie {
    public C06510nV A00;
    public AnonymousClass0Y3 A01;
    public AnonymousClass0Y6 A02;
    public final FbnsService A03;

    public final synchronized String A01() {
        return (String) this.A01.first;
    }

    public final synchronized boolean A02(AnonymousClass0Y3 r6) {
        boolean z;
        if (!this.A01.equals(r6)) {
            C06520nY A2L = this.A00.A00(AnonymousClass0WE.IDS).A2L();
            A2L.A00.putString("/settings/mqtt/id/mqtt_device_id", (String) r6.first);
            A2L.A00.putString("/settings/mqtt/id/mqtt_device_secret", (String) r6.second);
            A2L.A00.putLong("/settings/mqtt/id/timestamp", r6.A00);
            A2L.A00();
            this.A01 = r6;
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    private void A00() {
        AnonymousClass0Y6 r3 = new AnonymousClass0Y6(AnonymousClass0Y9.ECDSAP256);
        this.A02 = r3;
        synchronized (this) {
            if (!this.A02.equals(r3)) {
                C06520nY A2L = this.A00.A00(AnonymousClass0WE.IDS).A2L();
                AnonymousClass0Y8 r32 = r3.A00;
                A2L.A00.putString("/settings/mqtt/id/token_binding_priv_key", r32.A00());
                A2L.A00.putString("/settings/mqtt/id/token_binding_pub_key", r32.A01());
                A2L.A00.putLong("/settings/mqtt/id/token_binding_timestamp", System.currentTimeMillis());
                A2L.A00();
            }
        }
    }

    public final boolean A03(boolean z) {
        AnonymousClass0WD A002 = this.A00.A00(AnonymousClass0WE.IDS);
        boolean z2 = true;
        if (A002.A1r("/settings/mqtt/id/is_using_secure_auth")) {
            z2 = false;
            if (z != A002.A38("/settings/mqtt/id/is_using_secure_auth", false)) {
                z2 = true;
            }
            return z2;
        }
        C06520nY A2L = A002.A2L();
        A2L.A00.putBoolean("/settings/mqtt/id/is_using_secure_auth", z);
        A2L.A00();
        return z2;
    }

    public C04640ie(FbnsService fbnsService, C06510nV r9) {
        this.A03 = fbnsService;
        this.A00 = r9;
        AnonymousClass0WD A002 = r9.A00(AnonymousClass0WE.IDS);
        AnonymousClass0Y3 r0 = new AnonymousClass0Y3(A002.A4R("/settings/mqtt/id/mqtt_device_id", ""), A002.A4R("/settings/mqtt/id/mqtt_device_secret", ""), A002.A3l("/settings/mqtt/id/timestamp", Long.MAX_VALUE));
        this.A01 = r0;
        String str = (String) r0.first;
        if ((str == null || str.equals("")) && C01880Xv.A00(this.A03.getPackageName())) {
            A02(new AnonymousClass0Y3(UUID.randomUUID().toString(), "", System.currentTimeMillis()));
        }
        AnonymousClass0WD A003 = this.A00.A00(AnonymousClass0WE.IDS);
        try {
            String A4R = A003.A4R("/settings/mqtt/id/token_binding_priv_key", "");
            String A4R2 = A003.A4R("/settings/mqtt/id/token_binding_pub_key", "");
            if (TextUtils.isEmpty(A4R) || TextUtils.isEmpty(A4R2)) {
                A00();
            } else {
                this.A02 = new AnonymousClass0Y6(A4R2, A4R, AnonymousClass0Y9.ECDSAP256);
            }
        } catch (Exception unused) {
            A00();
        }
    }
}
