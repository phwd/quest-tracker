package X;

import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.0iS  reason: invalid class name and case insensitive filesystem */
public final class C05050iS {
    @Nullable
    public final AnonymousClass0iR A00;
    @Nullable
    public final AnonymousClass0iR A01;
    @Nullable
    public final AnonymousClass0iW A02;
    @Nullable
    public final Pattern A03;

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0018 A[ADDED_TO_REGION, Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0098 A[Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x009b A[Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x009c A[Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00a7 A[Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:? A[Catch:{ JSONException -> 0x00b2 }, ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A01(@javax.annotation.Nullable android.content.Intent r10, @javax.annotation.Nullable X.AnonymousClass0i5 r11) {
        /*
        // Method dump skipped, instructions count: 198
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C05050iS.A01(android.content.Intent, X.0i5):boolean");
    }

    public static C05050iS[] A00(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() > 0) {
                C05050iS[] r11 = new C05050iS[jSONArray.length()];
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    AnonymousClass0iR r12 = null;
                    String string = jSONObject.has("endpoint_name") ? jSONObject.getString("endpoint_name") : null;
                    AnonymousClass0iR A002 = jSONObject.has("caller_info") ? AnonymousClass0iR.A00(jSONObject.getJSONObject("caller_info")) : null;
                    AnonymousClass0iW A003 = jSONObject.has("uri_component") ? AnonymousClass0iW.A00(jSONObject.getJSONObject("uri_component")) : null;
                    if (jSONObject.has("intent_field")) {
                        r12 = AnonymousClass0iR.A00(jSONObject.getJSONObject("intent_field"));
                    }
                    r11[i] = new C05050iS(string, A002, A003, r12);
                }
                return r11;
            }
        } catch (JSONException unused) {
        }
        return new C05050iS[0];
    }

    public C05050iS(@Nullable String str, @Nullable AnonymousClass0iR r3, @Nullable AnonymousClass0iW r4, @Nullable AnonymousClass0iR r5) {
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
