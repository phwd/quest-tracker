package X;

import android.content.Intent;
import com.facebook.acra.AppComponentStats;
import com.facebook.assistant.oacr.OacrConstants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class JR {
    public final Map A00;
    public final JW A01;
    public final JS[] A02;

    public static JR A00(JSONObject jSONObject) {
        String string;
        if (jSONObject != null) {
            HashMap hashMap = new HashMap();
            JS[] jsArr = new JS[0];
            Iterator<String> keys = jSONObject.keys();
            JW jw = null;
            while (keys.hasNext()) {
                try {
                    String next = keys.next();
                    if (!next.equals("selector_config")) {
                        if (next.equals("clip_data")) {
                            jw = JW.A00(jSONObject.getJSONObject("clip_data"));
                        } else if (jSONObject.has(next)) {
                            hashMap.put(next, JV.A00(jSONObject.get(next)));
                        }
                    }
                } catch (IllegalArgumentException | JSONException unused) {
                    return null;
                }
            }
            if (jSONObject.has("selector_config") && (string = jSONObject.getString("selector_config")) != null) {
                jsArr = JS.A00(string);
            }
            if (!hashMap.isEmpty()) {
                if (jsArr.length > 0) {
                    return new JR(hashMap, jsArr, jw);
                }
                return new JR(hashMap, null, jw);
            }
        }
        return null;
    }

    public final boolean A01(JSONObject jSONObject, Intent intent) {
        int length;
        JSONArray jSONArray;
        String str;
        int i;
        JSONArray jSONArray2;
        boolean z;
        JSONArray jSONArray3;
        Map map = this.A00;
        Iterator it = map.entrySet().iterator();
        loop0:
        while (true) {
            if (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String str2 = (String) entry.getKey();
                if ("categories".equals(str2)) {
                    if (!jSONObject.has(str2) || (jSONArray3 = jSONObject.getJSONArray(str2)) == null) {
                        break;
                    }
                    JV jv = (JV) entry.getValue();
                    for (int i2 = 0; i2 < jSONArray3.length(); i2++) {
                        if (!jv.A01(jSONArray3.getString(i2))) {
                        }
                    }
                    break loop0;
                } else if ("extra_names".equals(str2)) {
                    if (!jSONObject.has(str2) || (jSONArray2 = jSONObject.getJSONArray(str2)) == null) {
                        break;
                    }
                    JV jv2 = (JV) entry.getValue();
                    JV jv3 = null;
                    if (map.containsKey("extra_value_types")) {
                        jv3 = (JV) map.get("extra_value_types");
                    }
                    for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                        JSONObject jSONObject2 = jSONArray2.getJSONObject(i3);
                        String string = jSONObject2.has(AppComponentStats.ATTRIBUTE_NAME) ? jSONObject2.getString(AppComponentStats.ATTRIBUTE_NAME) : OacrConstants.AUTO_SPEECH_DOMAIN;
                        String string2 = jSONObject2.has("value_type") ? jSONObject2.getString("value_type") : OacrConstants.AUTO_SPEECH_DOMAIN;
                        boolean A012 = jv2.A01(string);
                        if (jv3 == null) {
                            z = string2.equals(OacrConstants.AUTO_SPEECH_DOMAIN);
                        } else {
                            z = jv3.A01(string2);
                        }
                        if (!A012 || !z) {
                        }
                    }
                    break loop0;
                } else if ("extra_value_types".equals(str2)) {
                    continue;
                } else if ("flags".equals(str2)) {
                    if (jSONObject.has(str2)) {
                        i = jSONObject.getInt(str2);
                    } else {
                        i = 0;
                    }
                    try {
                        int parseInt = Integer.parseInt(((JV) entry.getValue()).A00.toString());
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
                    if (jSONObject.has(str2)) {
                        str = jSONObject.getString(str2);
                        if (str == null) {
                            break;
                        }
                    } else {
                        str = OacrConstants.AUTO_SPEECH_DOMAIN;
                    }
                    if (!((JV) entry.getValue()).A01(str)) {
                        return false;
                    }
                }
            } else {
                JW jw = this.A01;
                if (jw != null) {
                    if (jSONObject.has("clip_data") && (jSONArray = jSONObject.getJSONArray("clip_data")) != null) {
                        int i4 = 0;
                        loop3:
                        while (true) {
                            if (i4 >= jSONArray.length()) {
                                break;
                            }
                            JSONObject jSONObject3 = jSONArray.getJSONObject(i4);
                            try {
                                for (Map.Entry entry2 : jw.A00.entrySet()) {
                                    String str3 = (String) entry2.getKey();
                                    JV jv4 = (JV) entry2.getValue();
                                    if (jSONObject3.has(str3)) {
                                        if (!jv4.A01(jSONObject3.getString(str3))) {
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
                JS[] jsArr = this.A02;
                if (jsArr == null || (length = jsArr.length) <= 0) {
                    return true;
                }
                if (intent != null) {
                    int i5 = 0;
                    while (jsArr[i5].A01(intent, null)) {
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

    public JR(Map map, JS[] jsArr, JW jw) {
        this.A00 = map;
        this.A02 = jsArr;
        this.A01 = jw;
    }
}
