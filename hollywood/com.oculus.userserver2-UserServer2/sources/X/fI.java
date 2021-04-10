package X;

import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class fI {
    @Nullable
    public final fH A00;
    @Nullable
    public final fH A01;
    @Nullable
    public final C0207fM A02;
    @Nullable
    public final Pattern A03;

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0018 A[ADDED_TO_REGION, Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0098 A[Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x009b A[Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x009c A[Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00a7 A[Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:? A[Catch:{ JSONException -> 0x00b2 }, ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A01(@javax.annotation.Nullable android.content.Intent r10, @javax.annotation.Nullable X.ep r11) {
        /*
        // Method dump skipped, instructions count: 198
        */
        throw new UnsupportedOperationException("Method not decompiled: X.fI.A01(android.content.Intent, X.ep):boolean");
    }

    public static fI[] A00(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() > 0) {
                fI[] fIVarArr = new fI[jSONArray.length()];
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    fH fHVar = null;
                    String string = jSONObject.has("endpoint_name") ? jSONObject.getString("endpoint_name") : null;
                    fH A002 = jSONObject.has("caller_info") ? fH.A00(jSONObject.getJSONObject("caller_info")) : null;
                    C0207fM A003 = jSONObject.has("uri_component") ? C0207fM.A00(jSONObject.getJSONObject("uri_component")) : null;
                    if (jSONObject.has("intent_field")) {
                        fHVar = fH.A00(jSONObject.getJSONObject("intent_field"));
                    }
                    fIVarArr[i] = new fI(string, A002, A003, fHVar);
                }
                return fIVarArr;
            }
        } catch (JSONException unused) {
        }
        return new fI[0];
    }

    public fI(@Nullable String str, @Nullable fH fHVar, @Nullable C0207fM fMVar, @Nullable fH fHVar2) {
        Pattern compile;
        if (str == null) {
            compile = null;
        } else {
            compile = Pattern.compile(str, 32);
        }
        this.A03 = compile;
        this.A01 = fHVar2;
        this.A00 = fHVar;
        this.A02 = fMVar;
    }
}
