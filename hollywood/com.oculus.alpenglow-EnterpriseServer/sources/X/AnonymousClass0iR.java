package X;

import android.content.Intent;
import androidx.core.content.FileProvider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: X.0iR  reason: invalid class name */
public final class AnonymousClass0iR {
    public final Map<String, C05060iV> A00;
    @Nullable
    public final AnonymousClass0iW A01;
    @Nullable
    public final C05050iS[] A02;

    @Nullable
    public static AnonymousClass0iR A00(@Nullable JSONObject jSONObject) {
        String string;
        if (jSONObject != null) {
            HashMap hashMap = new HashMap();
            C05050iS[] r2 = new C05050iS[0];
            Iterator<String> keys = jSONObject.keys();
            AnonymousClass0iW r5 = null;
            while (keys.hasNext()) {
                try {
                    String next = keys.next();
                    if (!next.equals("selector_config")) {
                        if (next.equals("clip_data")) {
                            r5 = AnonymousClass0iW.A00(jSONObject.getJSONObject("clip_data"));
                        } else if (jSONObject.has(next)) {
                            hashMap.put(next, C05060iV.A00(jSONObject.get(next)));
                        }
                    }
                } catch (IllegalArgumentException | JSONException unused) {
                    return null;
                }
            }
            if (jSONObject.has("selector_config") && (string = jSONObject.getString("selector_config")) != null) {
                r2 = C05050iS.A00(string);
            }
            if (!hashMap.isEmpty()) {
                if (r2.length > 0) {
                    return new AnonymousClass0iR(hashMap, r2, r5);
                }
                return new AnonymousClass0iR(hashMap, null, r5);
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
        Map<String, C05060iV> map = this.A00;
        Iterator<Map.Entry<String, C05060iV>> it = map.entrySet().iterator();
        loop0:
        while (true) {
            if (it.hasNext()) {
                Map.Entry<String, C05060iV> next = it.next();
                String key = next.getKey();
                if ("categories".equals(key)) {
                    if (!jSONObject.has(key) || (jSONArray3 = jSONObject.getJSONArray(key)) == null) {
                        break;
                    }
                    C05060iV value = next.getValue();
                    for (int i2 = 0; i2 < jSONArray3.length(); i2++) {
                        if (!value.A01(jSONArray3.getString(i2))) {
                        }
                    }
                    break loop0;
                } else if ("extra_names".equals(key)) {
                    if (!jSONObject.has(key) || (jSONArray2 = jSONObject.getJSONArray(key)) == null) {
                        break;
                    }
                    C05060iV value2 = next.getValue();
                    C05060iV r7 = null;
                    if (map.containsKey("extra_value_types")) {
                        r7 = map.get("extra_value_types");
                    }
                    for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                        JSONObject jSONObject2 = jSONArray2.getJSONObject(i3);
                        if (jSONObject2.has(FileProvider.ATTR_NAME)) {
                            str2 = jSONObject2.getString(FileProvider.ATTR_NAME);
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
                AnonymousClass0iW r72 = this.A01;
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
                                for (Map.Entry<String, C05060iV> entry : r72.A00.entrySet()) {
                                    String key2 = entry.getKey();
                                    C05060iV value3 = entry.getValue();
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
                C05050iS[] r3 = this.A02;
                if (r3 == null || (length = r3.length) <= 0) {
                    return true;
                }
                if (intent != null) {
                    int i5 = 0;
                    while (r3[i5].A01(intent, null)) {
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

    public AnonymousClass0iR(Map<String, C05060iV> map, @Nullable C05050iS[] r2, @Nullable AnonymousClass0iW r3) {
        this.A00 = map;
        this.A02 = r2;
        this.A01 = r3;
    }
}
