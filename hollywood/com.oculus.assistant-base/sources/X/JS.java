package X;

import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class JS {
    public final JR A00;
    public final JR A01;
    public final JW A02;
    public final Pattern A03;

    /* JADX WARNING: Removed duplicated region for block: B:16:0x001b A[ADDED_TO_REGION, Catch:{ JSONException -> 0x00b5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x009b A[Catch:{ JSONException -> 0x00b5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x009e A[Catch:{ JSONException -> 0x00b5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x009f A[Catch:{ JSONException -> 0x00b5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00aa A[Catch:{ JSONException -> 0x00b5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:70:? A[Catch:{ JSONException -> 0x00b5 }, ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A01(android.content.Intent r10, X.J6 r11) {
        /*
        // Method dump skipped, instructions count: 200
        */
        throw new UnsupportedOperationException("Method not decompiled: X.JS.A01(android.content.Intent, X.J6):boolean");
    }

    public static JS[] A00(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() > 0) {
                JS[] jsArr = new JS[jSONArray.length()];
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    JR jr = null;
                    String string = jSONObject.has("endpoint_name") ? jSONObject.getString("endpoint_name") : null;
                    JR A002 = jSONObject.has("caller_info") ? JR.A00(jSONObject.getJSONObject("caller_info")) : null;
                    JW A003 = jSONObject.has("uri_component") ? JW.A00(jSONObject.getJSONObject("uri_component")) : null;
                    if (jSONObject.has("intent_field")) {
                        jr = JR.A00(jSONObject.getJSONObject("intent_field"));
                    }
                    jsArr[i] = new JS(string, A002, A003, jr);
                }
                return jsArr;
            }
        } catch (JSONException unused) {
        }
        return new JS[0];
    }

    public JS(String str, JR jr, JW jw, JR jr2) {
        Pattern compile;
        if (str == null) {
            compile = null;
        } else {
            compile = Pattern.compile(str, 32);
        }
        this.A03 = compile;
        this.A01 = jr2;
        this.A00 = jr;
        this.A02 = jw;
    }
}
