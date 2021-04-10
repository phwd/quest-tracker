package defpackage;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: gU  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2700gU implements AbstractC3042iU {
    public C2700gU(AbstractC2188dU dUVar) {
    }

    @Override // defpackage.AbstractC3042iU
    public String a(Object obj, String str) {
        JSONObject jSONObject = (JSONObject) obj;
        if (JSONObject.NULL.equals(jSONObject.opt(str))) {
            return null;
        }
        return jSONObject.optString(str, null);
    }

    @Override // defpackage.AbstractC3042iU
    public String[] b(Object obj, String str) {
        JSONArray optJSONArray = ((JSONObject) obj).optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(optJSONArray.length());
        for (int i = 0; i < optJSONArray.length(); i++) {
            arrayList.add(optJSONArray.optString(i));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @Override // defpackage.AbstractC3042iU
    public boolean c(Object obj, String str) {
        return ((JSONObject) obj).has(str);
    }
}
