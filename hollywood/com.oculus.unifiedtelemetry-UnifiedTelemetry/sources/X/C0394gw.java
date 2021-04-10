package X;

import android.content.Intent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.gw  reason: case insensitive filesystem */
public final class C0394gw {
    public final Map<String, C0398h0> A00;
    @Nullable
    public final C0399h1 A01;
    @Nullable
    public final C0395gx[] A02;

    @Nullable
    public static C0394gw A00(@Nullable JSONObject jSONObject) {
        String string;
        if (jSONObject != null) {
            HashMap hashMap = new HashMap();
            C0395gx[] gxVarArr = new C0395gx[0];
            Iterator<String> keys = jSONObject.keys();
            C0399h1 h1Var = null;
            while (keys.hasNext()) {
                try {
                    String next = keys.next();
                    if (!next.equals("selector_config")) {
                        if (next.equals("clip_data")) {
                            h1Var = C0399h1.A00(jSONObject.getJSONObject("clip_data"));
                        } else if (jSONObject.has(next)) {
                            hashMap.put(next, C0398h0.A00(jSONObject.get(next)));
                        }
                    }
                } catch (IllegalArgumentException | JSONException unused) {
                    return null;
                }
            }
            if (jSONObject.has("selector_config") && (string = jSONObject.getString("selector_config")) != null) {
                gxVarArr = C0395gx.A00(string);
            }
            if (!hashMap.isEmpty()) {
                if (gxVarArr.length > 0) {
                    return new C0394gw(hashMap, gxVarArr, h1Var);
                }
                return new C0394gw(hashMap, null, h1Var);
            }
        }
        return null;
    }

    public final boolean A01(JSONObject jSONObject, @Nullable Intent intent) {
        int length;
        JSONArray jSONArray;
        String str;
        int i;
        JSONArray jSONArray2;
        String str2;
        String str3;
        boolean z;
        JSONArray jSONArray3;
        Map<String, C0398h0> map = this.A00;
        Iterator<Map.Entry<String, C0398h0>> it = map.entrySet().iterator();
        loop0:
        while (true) {
            if (it.hasNext()) {
                Map.Entry<String, C0398h0> next = it.next();
                String key = next.getKey();
                if ("categories".equals(key)) {
                    if (!jSONObject.has(key) || (jSONArray3 = jSONObject.getJSONArray(key)) == null) {
                        break;
                    }
                    C0398h0 value = next.getValue();
                    for (int i2 = 0; i2 < jSONArray3.length(); i2++) {
                        if (!value.A01(jSONArray3.getString(i2))) {
                        }
                    }
                    break loop0;
                } else if ("extra_names".equals(key)) {
                    if (!jSONObject.has(key) || (jSONArray2 = jSONObject.getJSONArray(key)) == null) {
                        break;
                    }
                    C0398h0 value2 = next.getValue();
                    C0398h0 h0Var = null;
                    if (map.containsKey("extra_value_types")) {
                        h0Var = map.get("extra_value_types");
                    }
                    for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                        JSONObject jSONObject2 = jSONArray2.getJSONObject(i3);
                        if (jSONObject2.has("name")) {
                            str2 = jSONObject2.getString("name");
                        } else {
                            str2 = "";
                        }
                        if (jSONObject2.has("value_type")) {
                            str3 = jSONObject2.getString("value_type");
                        } else {
                            str3 = "";
                        }
                        boolean A012 = value2.A01(str2);
                        if (h0Var == null) {
                            z = str3.equals("");
                        } else {
                            z = h0Var.A01(str3);
                        }
                        if (!A012 || !z) {
                        }
                    }
                    break loop0;
                } else if ("extra_value_types".equals(key)) {
                    continue;
                } else if ("flags".equals(key)) {
                    if (jSONObject.has(key)) {
                        i = jSONObject.getInt(key);
                    } else {
                        i = 0;
                    }
                    try {
                        int parseInt = Integer.parseInt(next.getValue().A00.toString());
                        if (i != parseInt) {
                            if ((i & parseInt) <= 0) {
                                break;
                            }
                        } else {
                            continue;
                        }
                    } catch (JSONException unused) {
                    }
                } else {
                    if (jSONObject.has(key)) {
                        str = jSONObject.getString(key);
                        if (str == null) {
                            break;
                        }
                    } else {
                        str = "";
                    }
                    if (!next.getValue().A01(str)) {
                        return false;
                    }
                }
            } else {
                C0399h1 h1Var = this.A01;
                if (h1Var != null) {
                    if (jSONObject.has("clip_data") && (jSONArray = jSONObject.getJSONArray("clip_data")) != null) {
                        int i4 = 0;
                        loop3:
                        while (true) {
                            if (i4 >= jSONArray.length()) {
                                break;
                            }
                            JSONObject jSONObject3 = jSONArray.getJSONObject(i4);
                            try {
                                for (Map.Entry<String, C0398h0> entry : h1Var.A00.entrySet()) {
                                    String key2 = entry.getKey();
                                    C0398h0 value3 = entry.getValue();
                                    if (jSONObject3.has(key2)) {
                                        if (!value3.A01(jSONObject3.getString(key2))) {
                                        }
                                    }
                                    i4++;
                                }
                                break loop3;
                            } catch (JSONException unused2) {
                            }
                        }
                    }
                }
                C0395gx[] gxVarArr = this.A02;
                if (gxVarArr == null || (length = gxVarArr.length) <= 0) {
                    return true;
                }
                if (intent != null) {
                    int i5 = 0;
                    while (gxVarArr[i5].A01(intent, null)) {
                        i5++;
                        if (i5 >= length) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public C0394gw(Map<String, C0398h0> map, @Nullable C0395gx[] gxVarArr, @Nullable C0399h1 h1Var) {
        this.A00 = map;
        this.A02 = gxVarArr;
        this.A01 = h1Var;
    }
}
