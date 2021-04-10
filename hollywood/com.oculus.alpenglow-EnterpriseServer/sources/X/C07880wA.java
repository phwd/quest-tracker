package X;

import android.text.TextUtils;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.rti.push.service.FbnsService;
import java.security.KeyPair;
import java.util.UUID;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0wA  reason: invalid class name and case insensitive filesystem */
public final class C07880wA {
    public C07710vp A00;
    public C07920wE A01;
    public AnonymousClass0wH A02;
    public final FbnsService A03;

    public final synchronized String A01() {
        return (String) this.A01.first;
    }

    public final synchronized boolean A02(C07920wE r6) {
        boolean z;
        if (!this.A01.equals(r6)) {
            C07720vq A2E = this.A00.A00(EnumC07690vn.IDS).A2E();
            A2E.A00.putString("/settings/mqtt/id/mqtt_device_id", (String) r6.first);
            A2E.A00.putString("/settings/mqtt/id/mqtt_device_secret", (String) r6.second);
            A2E.A00.putLong("/settings/mqtt/id/timestamp", r6.A00);
            A2E.A00();
            this.A01 = r6;
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    private void A00() {
        AnonymousClass0wH r3 = new AnonymousClass0wH(AnonymousClass0z2.ECDSAP256);
        this.A02 = r3;
        synchronized (this) {
            if (!this.A02.equals(r3)) {
                C07720vq A2E = this.A00.A00(EnumC07690vn.IDS).A2E();
                KeyPair keyPair = ((AnonymousClass15W) r3.A00).A00;
                A2E.A00.putString("/settings/mqtt/id/token_binding_priv_key", AnonymousClass0yX.A01(keyPair.getPrivate().getEncoded()));
                A2E.A00.putString("/settings/mqtt/id/token_binding_pub_key", AnonymousClass0yX.A01(keyPair.getPublic().getEncoded()));
                A2E.A00.putLong("/settings/mqtt/id/token_binding_timestamp", System.currentTimeMillis());
                A2E.A00();
            }
        }
    }

    public final boolean A03(boolean z) {
        AnonymousClass0ux A002 = this.A00.A00(EnumC07690vn.IDS);
        boolean z2 = true;
        if (A002.A1p("/settings/mqtt/id/is_using_secure_auth")) {
            z2 = false;
            if (z != A002.A37("/settings/mqtt/id/is_using_secure_auth", false)) {
                z2 = true;
            }
            return z2;
        }
        C07720vq A2E = A002.A2E();
        A2E.A00.putBoolean("/settings/mqtt/id/is_using_secure_auth", z);
        A2E.A00();
        return z2;
    }

    public C07880wA(FbnsService fbnsService, C07710vp r9) {
        this.A03 = fbnsService;
        this.A00 = r9;
        AnonymousClass0ux A002 = r9.A00(EnumC07690vn.IDS);
        C07920wE r0 = new C07920wE(A002.A4Z("/settings/mqtt/id/mqtt_device_id", ""), A002.A4Z("/settings/mqtt/id/mqtt_device_secret", ""), A002.A3x("/settings/mqtt/id/timestamp", Long.MAX_VALUE));
        this.A01 = r0;
        String str = (String) r0.first;
        if ((str == null || str.equals("")) && AnonymousClass0vB.A00(this.A03.getPackageName())) {
            A02(new C07920wE(UUID.randomUUID().toString(), "", System.currentTimeMillis()));
        }
        AnonymousClass0ux A003 = this.A00.A00(EnumC07690vn.IDS);
        try {
            String A4Z = A003.A4Z("/settings/mqtt/id/token_binding_priv_key", "");
            String A4Z2 = A003.A4Z("/settings/mqtt/id/token_binding_pub_key", "");
            if (TextUtils.isEmpty(A4Z) || TextUtils.isEmpty(A4Z2)) {
                A00();
            } else {
                this.A02 = new AnonymousClass0wH(A4Z2, A4Z, AnonymousClass0z2.ECDSAP256);
            }
        } catch (Exception unused) {
            A00();
        }
    }
}
