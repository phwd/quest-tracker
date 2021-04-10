package X;

import android.content.Intent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.1ad  reason: invalid class name and case insensitive filesystem */
public final class C09251ad {
    public final Map<String, C09271af> A00;
    @Nullable
    public final C09261ae A01;
    @Nullable
    public final C09241ac[] A02;

    @Nullable
    public static C09251ad A00(@Nullable JSONObject jSONObject) {
        String string;
        if (jSONObject != null) {
            HashMap hashMap = new HashMap();
            C09241ac[] r2 = new C09241ac[0];
            Iterator<String> keys = jSONObject.keys();
            C09261ae r5 = null;
            while (keys.hasNext()) {
                try {
                    String next = keys.next();
                    if (!next.equals("selector_config")) {
                        if (next.equals("clip_data")) {
                            r5 = C09261ae.A00(jSONObject.getJSONObject("clip_data"));
                        } else if (jSONObject.has(next)) {
                            hashMap.put(next, C09271af.A00(jSONObject.get(next)));
                        }
                    }
                } catch (IllegalArgumentException | JSONException unused) {
                    return null;
                }
            }
            if (jSONObject.has("selector_config") && (string = jSONObject.getString("selector_config")) != null) {
                r2 = C09241ac.A01(string);
            }
            if (!hashMap.isEmpty()) {
                if (r2.length > 0) {
                    return new C09251ad(hashMap, r2, r5);
                }
                return new C09251ad(hashMap, null, r5);
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
        Map<String, C09271af> map = this.A00;
        Iterator<Map.Entry<String, C09271af>> it = map.entrySet().iterator();
        loop0:
        while (true) {
            if (it.hasNext()) {
                Map.Entry<String, C09271af> next = it.next();
                String key = next.getKey();
                if ("categories".equals(key)) {
                    if (!jSONObject.has(key) || (jSONArray3 = jSONObject.getJSONArray(key)) == null) {
                        break;
                    }
                    C09271af value = next.getValue();
                    for (int i2 = 0; i2 < jSONArray3.length(); i2++) {
                        if (!value.A01(jSONArray3.getString(i2))) {
                        }
                    }
                    break loop0;
                } else if ("extra_names".equals(key)) {
                    if (!jSONObject.has(key) || (jSONArray2 = jSONObject.getJSONArray(key)) == null) {
                        break;
                    }
                    C09271af value2 = next.getValue();
                    C09271af r7 = null;
                    if (map.containsKey("extra_value_types")) {
                        r7 = map.get("extra_value_types");
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
                        if (r7 == null) {
                            z = str3.equals("");
                        } else {
                            z = r7.A01(str3);
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
                C09261ae r72 = this.A01;
                if (r72 != null) {
                    if (jSONObject.has("clip_data") && (jSONArray = jSONObject.getJSONArray("clip_data")) != null) {
                        int i4 = 0;
                        loop3:
                        while (true) {
                            if (i4 >= jSONArray.length()) {
                                break;
                            }
                            JSONObject jSONObject3 = jSONArray.getJSONObject(i4);
                            try {
                                for (Map.Entry<String, C09271af> entry : r72.A00.entrySet()) {
                                    String key2 = entry.getKey();
                                    C09271af value3 = entry.getValue();
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
                C09241ac[] r3 = this.A02;
                if (r3 == null || (length = r3.length) <= 0) {
                    return true;
                }
                if (intent != null) {
                    int i5 = 0;
                    while (r3[i5].A02(intent, null)) {
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

    public C09251ad(Map<String, C09271af> map, @Nullable C09241ac[] r2, @Nullable C09261ae r3) {
        this.A00 = map;
        this.A02 = r2;
        this.A01 = r3;
    }
}
