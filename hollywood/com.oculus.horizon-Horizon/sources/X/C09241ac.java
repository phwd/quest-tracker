package X;

import android.content.Context;
import android.content.Intent;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.1ac  reason: invalid class name and case insensitive filesystem */
public final class C09241ac {
    @Nullable
    public final C09251ad A00;
    @Nullable
    public final C09251ad A01;
    @Nullable
    public final C09261ae A02;
    @Nullable
    public final Pattern A03;

    public static boolean A00(Object obj, Intent intent, Context context, @Nullable C09281ag r12, @Nullable C09241ac[] r13) {
        int length;
        if (r13 == null || (length = r13.length) <= 0) {
            return false;
        }
        int i = 0;
        do {
            C09241ac r5 = r13[i];
            Pattern pattern = r5.A03;
            if (pattern == null || pattern.matcher(obj.getClass().getName()).matches()) {
                try {
                    C02790bO A002 = C02800bY.A00(context, intent);
                    C09251ad r6 = r5.A00;
                    if (r6 != null) {
                        if (A002 != null) {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("caller_uid", A002.A00);
                            String A012 = A002.A01();
                            if (A012 != null) {
                                jSONObject.put("caller_package_name", A012);
                            }
                            String str = A002.A03;
                            if (str != null) {
                                jSONObject.put("caller_version_name", str);
                            }
                            String str2 = A002.A02;
                            if (str2 != null) {
                                jSONObject.put("caller_domain", str2);
                            }
                            if (!r6.A01(jSONObject, null)) {
                            }
                        }
                    }
                    if (r5.A02(intent, r12)) {
                        return true;
                    }
                } catch (JSONException unused) {
                }
            }
            i++;
        } while (i < length);
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0018 A[ADDED_TO_REGION, Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0098 A[Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x009b A[Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x009c A[Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00a7 A[Catch:{ JSONException -> 0x00b2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:? A[Catch:{ JSONException -> 0x00b2 }, ORIG_RETURN, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean A02(@javax.annotation.Nullable android.content.Intent r10, @javax.annotation.Nullable X.C09281ag r11) {
        /*
        // Method dump skipped, instructions count: 198
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09241ac.A02(android.content.Intent, X.1ag):boolean");
    }

    public static C09241ac[] A01(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() > 0) {
                C09241ac[] r11 = new C09241ac[jSONArray.length()];
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    C09251ad r12 = null;
                    String string = jSONObject.has("endpoint_name") ? jSONObject.getString("endpoint_name") : null;
                    C09251ad A002 = jSONObject.has("caller_info") ? C09251ad.A00(jSONObject.getJSONObject("caller_info")) : null;
                    C09261ae A003 = jSONObject.has("uri_component") ? C09261ae.A00(jSONObject.getJSONObject("uri_component")) : null;
                    if (jSONObject.has("intent_field")) {
                        r12 = C09251ad.A00(jSONObject.getJSONObject("intent_field"));
                    }
                    r11[i] = new C09241ac(string, A002, A003, r12);
                }
                return r11;
            }
        } catch (JSONException unused) {
        }
        return new C09241ac[0];
    }

    public C09241ac(@Nullable String str, @Nullable C09251ad r3, @Nullable C09261ae r4, @Nullable C09251ad r5) {
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
