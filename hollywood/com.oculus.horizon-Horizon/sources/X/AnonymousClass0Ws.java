package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
/* renamed from: X.0Ws  reason: invalid class name */
public final class AnonymousClass0Ws {
    public final boolean A00;
    @Nullable
    public final AnonymousClass0Wv A01;
    @Nullable
    public final AnonymousClass0Ie A02;
    @Nullable
    public final AnonymousClass0Ic A03;
    public final AnonymousClass0Ib A04;
    @Nullable
    public final AnonymousClass0Ia A05;
    @Nullable
    public final AnonymousClass0IW A06;
    @Nullable
    public final AnonymousClass0IV A07;
    @Nullable
    public final AnonymousClass0IU A08;
    public final boolean A09;

    public final String toString() {
        try {
            return A00(this, false).toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public static JSONObject A00(AnonymousClass0Ws r5, boolean z) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        AnonymousClass0Ib r2 = r5.A04;
        if (r2 != null) {
            jSONObject.putOpt(r2.A00, r2.A01(z, r5.A09));
        }
        AnonymousClass0IW r22 = r5.A06;
        if (r22 != null) {
            jSONObject.putOpt(r22.A00, r22.A01(z, r5.A09));
        }
        AnonymousClass0Ie r23 = r5.A02;
        if (r23 != null) {
            jSONObject.putOpt(r23.A00, r23.A01(z, r5.A09));
        }
        AnonymousClass0Ia r24 = r5.A05;
        if (r24 != null) {
            jSONObject.putOpt(r24.A00, r24.A01(z, r5.A09));
        }
        AnonymousClass0Wv r4 = r5.A01;
        if (r4 != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("ssr", r4.A03);
            jSONObject2.putOpt("mcd", Long.valueOf(r4.A00));
            jSONObject2.putOpt("mfcl", Long.valueOf(r4.A02));
            jSONObject2.putOpt("mcg", Long.valueOf(r4.A01));
            jSONObject.putOpt("ss", jSONObject2);
        }
        AnonymousClass0Ic r25 = r5.A03;
        if (r25 != null) {
            jSONObject.putOpt(r25.A03, r25.A02(r5.A09));
        }
        AnonymousClass0IU r26 = r5.A08;
        if (r26 != null) {
            jSONObject.putOpt(r26.A03, r26.A02(r5.A09));
        }
        AnonymousClass0IV r27 = r5.A07;
        if (r27 != null) {
            jSONObject.putOpt(r27.A03, r27.A02(r5.A09));
        }
        return jSONObject;
    }

    public AnonymousClass0Ws(AnonymousClass0Ib r1, @Nullable AnonymousClass0IW r2, @Nullable AnonymousClass0Ie r3, @Nullable AnonymousClass0Ia r4, @Nullable AnonymousClass0Wv r5, @Nullable AnonymousClass0Ic r6, @Nullable AnonymousClass0IU r7, @Nullable AnonymousClass0IV r8, boolean z, boolean z2) {
        this.A04 = r1;
        this.A06 = r2;
        this.A02 = r3;
        this.A05 = r4;
        this.A01 = r5;
        this.A03 = r6;
        this.A08 = r7;
        this.A07 = r8;
        this.A00 = z;
        this.A09 = z2;
    }
}
