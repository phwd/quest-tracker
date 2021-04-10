package defpackage;

import J.N;
import java.util.Collections;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.profiles.Profile;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: tc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4939tc1 implements AbstractC3403kc1 {
    @Override // defpackage.AbstractC3403kc1
    public void a(C3836n61 n61, Callback callback) {
        try {
            for (C3665m61 m61 : n61.f10472a) {
                if (m61.L) {
                    ((C4087oc1) callback).onResult(new C3574lc1(Collections.emptyList(), n61));
                    return;
                }
            }
            N.MiPC31k4(Profile.b(), "https://memex-pa.googleapis.com/v1/suggestions", "POST", "application/json; charset=UTF-8", b(n61), 10000, new String[0], new C4769sc1(this, callback, n61));
        } catch (JSONException e) {
            StringBuilder i = AbstractC2531fV.i("There was a problem parsing the JSON");
            i.append(e.getMessage());
            AbstractC1220Ua0.a("TSSF", i.toString(), new Object[0]);
            ((C4087oc1) callback).onResult(new C3574lc1(Collections.emptyList(), n61));
        }
    }

    public String b(C3836n61 n61) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        for (C3665m61 m61 : n61.f10472a) {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("id", m61.I);
            jSONObject3.put("url", m61.F);
            jSONObject3.put("title", m61.f10398J);
            jSONObject3.put("timestamp", m61.H);
            String str = m61.G;
            if (str != null) {
                jSONObject3.put("referrer", str);
            }
            jSONArray.put(jSONObject3);
        }
        return jSONObject.put("tabContext", jSONObject2.put("tabs", jSONArray)).toString();
    }

    @Override // defpackage.AbstractC3403kc1
    public boolean isEnabled() {
        if (!C5949zZ.a().c(Profile.b()).c() || !N.M6bsIDpc("CloseTabSuggestions", "server_fetcher_enabled", false) || !AbstractC4772sd1.g()) {
            return false;
        }
        return true;
    }
}
