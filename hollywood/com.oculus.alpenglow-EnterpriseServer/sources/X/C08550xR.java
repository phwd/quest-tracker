package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
/* renamed from: X.0xR  reason: invalid class name and case insensitive filesystem */
public final class C08550xR {
    public final boolean A00;
    @Nullable
    public final C09460zV A01;
    @Nullable
    public final C09660zz A02;
    @Nullable
    public final C09510za A03;
    public final AnonymousClass102 A04;
    @Nullable
    public final C09210yr A05;
    @Nullable
    public final C08560xS A06;
    @Nullable
    public final C09500zZ A07;
    @Nullable
    public final C09490zY A08;
    public final boolean A09;

    public final String toString() {
        try {
            return A00(this, false).toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public static JSONObject A00(C08550xR r5, boolean z) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        AnonymousClass102 r2 = r5.A04;
        if (r2 != null) {
            jSONObject.putOpt(r2.A00, r2.A01(z, r5.A09));
        }
        C08560xS r22 = r5.A06;
        if (r22 != null) {
            jSONObject.putOpt(r22.A00, r22.A01(z, r5.A09));
        }
        C09660zz r23 = r5.A02;
        if (r23 != null) {
            jSONObject.putOpt(r23.A00, r23.A01(z, r5.A09));
        }
        C09210yr r24 = r5.A05;
        if (r24 != null) {
            jSONObject.putOpt(r24.A00, r24.A01(z, r5.A09));
        }
        C09460zV r4 = r5.A01;
        if (r4 != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("ssr", r4.A03);
            jSONObject2.putOpt("mcd", Long.valueOf(r4.A00));
            jSONObject2.putOpt("mfcl", Long.valueOf(r4.A02));
            jSONObject2.putOpt("mcg", Long.valueOf(r4.A01));
            jSONObject.putOpt("ss", jSONObject2);
        }
        C09510za r25 = r5.A03;
        if (r25 != null) {
            jSONObject.putOpt(r25.A03, r25.A02(r5.A09));
        }
        C09490zY r26 = r5.A08;
        if (r26 != null) {
            jSONObject.putOpt(r26.A03, r26.A02(r5.A09));
        }
        C09500zZ r27 = r5.A07;
        if (r27 != null) {
            jSONObject.putOpt(r27.A03, r27.A02(r5.A09));
        }
        return jSONObject;
    }

    public C08550xR(AnonymousClass102 r1, @Nullable C08560xS r2, @Nullable C09660zz r3, @Nullable C09210yr r4, @Nullable C09460zV r5, @Nullable C09510za r6, @Nullable C09490zY r7, @Nullable C09500zZ r8, boolean z, boolean z2) {
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
