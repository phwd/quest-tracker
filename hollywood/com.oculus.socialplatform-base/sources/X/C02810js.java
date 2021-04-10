package X;

import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.0js  reason: invalid class name and case insensitive filesystem */
public final class C02810js {
    @Nullable
    public final C02800jr A00;
    @Nullable
    public final C02800jr A01;
    @Nullable
    public final C02850jw A02;
    @Nullable
    public final Pattern A03;

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0018 A[ADDED_TO_REGION, Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0098 A[Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x009b A[Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x009c A[Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00a7 A[Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:? A[Catch:{ JSONException -> 0x00b2 }, ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A01(@javax.annotation.Nullable android.content.Intent r10, @javax.annotation.Nullable X.C02610jP r11) {
        /*
        // Method dump skipped, instructions count: 198
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C02810js.A01(android.content.Intent, X.0jP):boolean");
    }

    public static C02810js[] A00(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() > 0) {
                C02810js[] r11 = new C02810js[jSONArray.length()];
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    C02800jr r12 = null;
                    String string = jSONObject.has("endpoint_name") ? jSONObject.getString("endpoint_name") : null;
                    C02800jr A002 = jSONObject.has("caller_info") ? C02800jr.A00(jSONObject.getJSONObject("caller_info")) : null;
                    C02850jw A003 = jSONObject.has("uri_component") ? C02850jw.A00(jSONObject.getJSONObject("uri_component")) : null;
                    if (jSONObject.has("intent_field")) {
                        r12 = C02800jr.A00(jSONObject.getJSONObject("intent_field"));
                    }
                    r11[i] = new C02810js(string, A002, A003, r12);
                }
                return r11;
            }
        } catch (JSONException unused) {
        }
        return new C02810js[0];
    }

    public C02810js(@Nullable String str, @Nullable C02800jr r3, @Nullable C02850jw r4, @Nullable C02800jr r5) {
        Pattern compile;
        if (str == null) {
            compile = null;
        } else {
            compile = Pattern.compile(str, 32);
        }
        this.A03 = compile;
        this.A01 = r5;
        this.A00 = r3;
        this.A02 = r4;
    }
}
