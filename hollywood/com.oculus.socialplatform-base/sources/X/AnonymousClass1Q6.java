package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import org.json.JSONException;
import org.json.JSONObject;

@Nullsafe(Nullsafe.Mode.LOCAL)
@Immutable
/* renamed from: X.1Q6  reason: invalid class name */
public final class AnonymousClass1Q6 {
    public final boolean A00;
    @Nullable
    public final AnonymousClass1Q7 A01;
    @Nullable
    public final AnonymousClass1QB A02;
    @Nullable
    public final C06101Pi A03;
    public final AnonymousClass1Q8 A04;
    @Nullable
    public final AnonymousClass1QA A05;
    @Nullable
    public final AnonymousClass1Q5 A06;
    @Nullable
    public final C06091Ph A07;
    @Nullable
    public final C06081Pg A08;
    public final boolean A09;

    public final String toString() {
        try {
            return A00(this, false).toString();
        } catch (JSONException unused) {
            return "";
        }
    }

    public static JSONObject A00(AnonymousClass1Q6 r5, boolean z) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        AnonymousClass1Q8 r2 = r5.A04;
        if (r2 != null) {
            jSONObject.putOpt(r2.A00, r2.A01(z, r5.A09));
        }
        AnonymousClass1Q5 r22 = r5.A06;
        if (r22 != null) {
            jSONObject.putOpt(r22.A00, r22.A01(z, r5.A09));
        }
        AnonymousClass1QB r23 = r5.A02;
        if (r23 != null) {
            jSONObject.putOpt(r23.A00, r23.A01(z, r5.A09));
        }
        AnonymousClass1QA r24 = r5.A05;
        if (r24 != null) {
            jSONObject.putOpt(r24.A00, r24.A01(z, r5.A09));
        }
        AnonymousClass1Q7 r4 = r5.A01;
        if (r4 != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt("ssr", r4.A03);
            jSONObject2.putOpt("mcd", Long.valueOf(r4.A00));
            jSONObject2.putOpt("mfcl", Long.valueOf(r4.A02));
            jSONObject2.putOpt("mcg", Long.valueOf(r4.A01));
            jSONObject.putOpt("ss", jSONObject2);
        }
        C06101Pi r25 = r5.A03;
        if (r25 != null) {
            jSONObject.putOpt(r25.A03, r25.A02(r5.A09));
        }
        C06081Pg r26 = r5.A08;
        if (r26 != null) {
            jSONObject.putOpt(r26.A03, r26.A02(r5.A09));
        }
        C06091Ph r27 = r5.A07;
        if (r27 != null) {
            jSONObject.putOpt(r27.A03, r27.A02(r5.A09));
        }
        return jSONObject;
    }

    public AnonymousClass1Q6(AnonymousClass1Q8 r1, @Nullable AnonymousClass1Q5 r2, @Nullable AnonymousClass1QB r3, @Nullable AnonymousClass1QA r4, @Nullable AnonymousClass1Q7 r5, @Nullable C06101Pi r6, @Nullable C06081Pg r7, @Nullable C06091Ph r8, boolean z, boolean z2) {
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
