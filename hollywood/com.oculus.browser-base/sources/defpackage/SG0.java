package defpackage;

import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: SG0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SG0 extends AbstractC4822su0 {
    public final long b;
    public final long c;
    public final String d;

    public SG0(long j, long j2, String str) {
        super("PRODUCT_PRICE_UPDATE");
        this.b = j;
        this.c = j2;
        this.d = str;
    }

    public static SG0 a(JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("priceUpdate");
            JSONObject jSONObject3 = jSONObject2.getJSONObject("oldPrice");
            JSONObject jSONObject4 = jSONObject2.getJSONObject("newPrice");
            if (b(jSONObject3)) {
                if (b(jSONObject4)) {
                    String string = jSONObject3.getString("currencyCode");
                    String string2 = jSONObject4.getString("currencyCode");
                    if (string != null) {
                        if (string.equals(string2)) {
                            Long c2 = AbstractC5162uu0.c(jSONObject3.getString("amountMicros"));
                            Long c3 = AbstractC5162uu0.c(jSONObject4.getString("amountMicros"));
                            if (c2 != null) {
                                if (c3 != null) {
                                    return new SG0(c2.longValue(), c3.longValue(), string);
                                }
                            }
                            AbstractC1220Ua0.d("PPUPA", String.format(Locale.US, "Invalid amount micros.", new Object[0]), new Object[0]);
                            return null;
                        }
                    }
                    AbstractC1220Ua0.d("PPUPA", String.format(Locale.US, "There was currency code mismatch in price update.", new Object[0]), new Object[0]);
                    return null;
                }
            }
            AbstractC1220Ua0.d("PPUPA", String.format(Locale.US, "Invalid price update.", new Object[0]), new Object[0]);
            return null;
        } catch (JSONException e) {
            AbstractC1220Ua0.d("PPUPA", String.format(Locale.US, "There was a problem parsing ProductPriceUpdatePageAnnotation Details: %s", e.toString()), new Object[0]);
            return null;
        }
    }

    public static boolean b(JSONObject jSONObject) {
        return jSONObject != null && jSONObject.has("currencyCode") && !jSONObject.isNull("currencyCode") && jSONObject.has("amountMicros") && !jSONObject.isNull("amountMicros");
    }
}
