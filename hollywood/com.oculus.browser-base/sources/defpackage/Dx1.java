package defpackage;

import J.N;
import android.os.Handler;
import android.util.Log;
import com.oculus.browser.Preferences;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.chromium.base.ThreadUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: Dx1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Dx1 extends RN implements AbstractC0530Iq0 {
    public static Dx1 F;
    public Handler G = new Handler();
    public Map H;
    public Map I;

    /* renamed from: J  reason: collision with root package name */
    public String f7926J;
    public Preferences K;
    public QN L;

    public Dx1(Preferences preferences, QN qn) {
        C0591Jq0.a(this);
        this.K = preferences;
        this.L = qn;
        qn.b = this;
    }

    @Override // defpackage.AbstractC0530Iq0
    public void a() {
    }

    @Override // defpackage.RN
    public void b(String str) {
        Object obj = ThreadUtils.f10596a;
        g(str);
    }

    @Override // defpackage.AbstractC0530Iq0
    public void c(String str) {
        this.f7926J = str;
        h();
    }

    public final String[] e(Map map) {
        Set keySet = map.keySet();
        String[] strArr = (String[]) keySet.toArray(new String[keySet.size()]);
        Arrays.sort(strArr);
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (String str : strArr) {
            sb.append(str);
            sb.append("#");
            sb2.append((String) map.get(str));
            sb2.append("#");
        }
        return new String[]{sb.toString(), sb2.toString()};
    }

    public final Map f(JSONArray jSONArray) {
        HashMap hashMap = new HashMap(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            hashMap.put(jSONObject.getString("host"), jSONObject.getString("user_agent"));
        }
        return hashMap;
    }

    public final void g(String str) {
        try {
            JSONObject jSONObject = new JSONObject(new JSONObject(str).getJSONObject("data").getJSONObject("viewer").getString("oculus_browser_web_of_lies"));
            Map f = f(jSONObject.getJSONArray("web_of_lies_hosts"));
            Map f2 = f(jSONObject.getJSONArray("mobile_overrides"));
            this.H = f;
            this.I = f2;
            String[] e = e(f);
            N.MxJqdpsV(e[0], e[1]);
            String[] e2 = e(this.I);
            N.M7Q_$nP4(e2[0], e2[1]);
            this.K.setString("WOL_JSON_STRING_CACHE", str);
        } catch (JSONException e3) {
            StringBuilder i = AbstractC2531fV.i("JSON failed to parse: ");
            i.append(e3.toString());
            Log.e("WebOfLies", i.toString());
        }
    }

    public final void h() {
        String str;
        if (this.f7926J == null) {
            str = null;
        } else {
            StringBuilder sb = new StringBuilder("https://graph.oculus.com/graphql");
            StringBuilder i = AbstractC2531fV.i("?access_token=");
            i.append(this.f7926J);
            sb.append(i.toString());
            sb.append("&doc_id=2767087846731516");
            str = sb.toString();
        }
        if (str != null) {
            this.L.d(str, null, 2, 2);
            this.G.postDelayed(new Cx1(this), 3600000);
        }
    }
}
