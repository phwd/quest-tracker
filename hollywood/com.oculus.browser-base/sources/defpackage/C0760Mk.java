package defpackage;

import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: Mk  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0760Mk extends AbstractC4822su0 {
    public final long b;
    public final String c;

    public C0760Mk(long j, String str) {
        super("BUYABLE_PRODUCT");
        this.b = j;
        this.c = str;
    }

    public static C0760Mk a(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("buyableProduct").getJSONObject("currentPrice");
            if (jSONObject2 != null && jSONObject2.has("amountMicros")) {
                if (!jSONObject2.isNull("amountMicros")) {
                    Long c2 = AbstractC5162uu0.c(jSONObject2.getString("amountMicros"));
                    if (c2 != null) {
                        return new C0760Mk(c2.longValue(), jSONObject2.getString("currencyCode"));
                    }
                    AbstractC1220Ua0.d("BPPA", String.format(Locale.US, "Invalid price micros.", new Object[0]), new Object[0]);
                    return null;
                }
            }
            AbstractC1220Ua0.d("BPPA", String.format(Locale.US, "Invalid price info.", new Object[0]), new Object[0]);
            return null;
        } catch (JSONException e) {
            AbstractC1220Ua0.d("BPPA", String.format(Locale.US, "There was a problem parsing BuyableProductPageAnnotation Details: %s", e.toString()), new Object[0]);
            return null;
        }
    }
}
