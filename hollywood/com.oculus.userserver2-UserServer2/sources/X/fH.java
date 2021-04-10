package X;

import android.content.Intent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class fH {
    public final Map<String, C0206fL> A00;
    @Nullable
    public final C0207fM A01;
    @Nullable
    public final fI[] A02;

    @Nullable
    public static fH A00(@Nullable JSONObject jSONObject) {
        String string;
        if (jSONObject != null) {
            HashMap hashMap = new HashMap();
            fI[] fIVarArr = new fI[0];
            Iterator<String> keys = jSONObject.keys();
            C0207fM fMVar = null;
            while (keys.hasNext()) {
                try {
                    String next = keys.next();
                    if (!next.equals("selector_config")) {
                        if (next.equals("clip_data")) {
                            fMVar = C0207fM.A00(jSONObject.getJSONObject("clip_data"));
                        } else if (jSONObject.has(next)) {
                            hashMap.put(next, C0206fL.A00(jSONObject.get(next)));
                        }
                    }
                } catch (IllegalArgumentException | JSONException unused) {
                    return null;
                }
            }
            if (jSONObject.has("selector_config") && (string = jSONObject.getString("selector_config")) != null) {
                fIVarArr = fI.A00(string);
            }
            if (!hashMap.isEmpty()) {
                if (fIVarArr.length > 0) {
                    return new fH(hashMap, fIVarArr, fMVar);
                }
                return new fH(hashMap, null, fMVar);
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
        Map<String, C0206fL> map = this.A00;
        Iterator<Map.Entry<String, C0206fL>> it = map.entrySet().iterator();
        loop0:
        while (true) {
            if (it.hasNext()) {
                Map.Entry<String, C0206fL> next = it.next();
                String key = next.getKey();
                if ("categories".equals(key)) {
                    if (!jSONObject.has(key) || (jSONArray3 = jSONObject.getJSONArray(key)) == null) {
                        break;
                    }
                    C0206fL value = next.getValue();
                    for (int i2 = 0; i2 < jSONArray3.length(); i2++) {
                        if (!value.A01(jSONArray3.getString(i2))) {
                        }
                    }
                    break loop0;
                } else if ("extra_names".equals(key)) {
                    if (!jSONObject.has(key) || (jSONArray2 = jSONObject.getJSONArray(key)) == null) {
                        break;
                    }
                    C0206fL value2 = next.getValue();
                    C0206fL fLVar = null;
                    if (map.containsKey("extra_value_types")) {
                        fLVar = map.get("extra_value_types");
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
                        if (fLVar == null) {
                            z = str3.equals("");
                        } else {
                            z = fLVar.A01(str3);
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
                C0207fM fMVar = this.A01;
                if (fMVar != null) {
                    if (jSONObject.has("clip_data") && (jSONArray = jSONObject.getJSONArray("clip_data")) != null) {
                        int i4 = 0;
                        loop3:
                        while (true) {
                            if (i4 >= jSONArray.length()) {
                                break;
                            }
                            JSONObject jSONObject3 = jSONArray.getJSONObject(i4);
                            try {
                                for (Map.Entry<String, C0206fL> entry : fMVar.A00.entrySet()) {
                                    String key2 = entry.getKey();
                                    C0206fL value3 = entry.getValue();
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
                fI[] fIVarArr = this.A02;
                if (fIVarArr == null || (length = fIVarArr.length) <= 0) {
                    return true;
                }
                if (intent != null) {
                    int i5 = 0;
                    while (fIVarArr[i5].A01(intent, null)) {
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

    public fH(Map<String, C0206fL> map, @Nullable fI[] fIVarArr, @Nullable C0207fM fMVar) {
        this.A00 = map;
        this.A02 = fIVarArr;
        this.A01 = fMVar;
    }
}
