package X;

import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.gx  reason: case insensitive filesystem */
public final class C0395gx {
    @Nullable
    public final C0394gw A00;
    @Nullable
    public final C0394gw A01;
    @Nullable
    public final C0399h1 A02;
    @Nullable
    public final Pattern A03;

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0018 A[ADDED_TO_REGION, Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0098 A[Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x009b A[Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x009c A[Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00a7 A[Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:? A[Catch:{ JSONException -> 0x00b2 }, ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A01(@javax.annotation.Nullable android.content.Intent r10, @javax.annotation.Nullable X.C0379gY r11) {
        /*
        // Method dump skipped, instructions count: 198
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0395gx.A01(android.content.Intent, X.gY):boolean");
    }

    public static C0395gx[] A00(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() > 0) {
                C0395gx[] gxVarArr = new C0395gx[jSONArray.length()];
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    C0394gw gwVar = null;
                    String string = jSONObject.has("endpoint_name") ? jSONObject.getString("endpoint_name") : null;
                    C0394gw A002 = jSONObject.has("caller_info") ? C0394gw.A00(jSONObject.getJSONObject("caller_info")) : null;
                    C0399h1 A003 = jSONObject.has("uri_component") ? C0399h1.A00(jSONObject.getJSONObject("uri_component")) : null;
                    if (jSONObject.has("intent_field")) {
                        gwVar = C0394gw.A00(jSONObject.getJSONObject("intent_field"));
                    }
                    gxVarArr[i] = new C0395gx(string, A002, A003, gwVar);
                }
                return gxVarArr;
            }
        } catch (JSONException unused) {
        }
        return new C0395gx[0];
    }

    public C0395gx(@Nullable String str, @Nullable C0394gw gwVar, @Nullable C0399h1 h1Var, @Nullable C0394gw gwVar2) {
        Pattern compile;
        if (str == null) {
            compile = null;
        } else {
            compile = Pattern.compile(str, 32);
        }
        this.A03 = compile;
        this.A01 = gwVar2;
        this.A00 = gwVar;
        this.A02 = h1Var;
    }
}
