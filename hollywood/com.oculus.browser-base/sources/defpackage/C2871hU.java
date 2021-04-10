package defpackage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: hU  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2871hU implements AbstractC3212jU {
    public C2871hU(C3383kU kUVar, AbstractC2188dU dUVar) {
    }

    @Override // defpackage.AbstractC3212jU
    public Object a() {
        return new JSONObject();
    }

    @Override // defpackage.AbstractC3212jU
    public void b(Object obj, String str, String[] strArr) {
        JSONObject jSONObject = (JSONObject) obj;
        JSONArray jSONArray = new JSONArray();
        try {
            for (String str2 : strArr) {
                jSONArray.put(str2);
            }
            jSONObject.put(str, jSONArray);
        } catch (JSONException unused) {
            AbstractC1220Ua0.a("GCMMessage", "Error when serializing a GCMMessage into a JSONObject.", new Object[0]);
        }
    }

    @Override // defpackage.AbstractC3212jU
    public void c(Object obj, String str, String str2) {
        JSONObject jSONObject = (JSONObject) obj;
        if (str2 == null) {
            try {
                jSONObject.put(str, JSONObject.NULL);
            } catch (JSONException unused) {
                AbstractC1220Ua0.a("GCMMessage", "Error when serializing a GCMMessage into a JSONObject.", new Object[0]);
            }
        } else {
            jSONObject.put(str, str2);
        }
    }
}
