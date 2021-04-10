package com.oculus.browser;

import J.N;
import android.os.Handler;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import org.chromium.base.ThreadUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Gatekeeper extends RN implements AbstractC0530Iq0 {
    public static final Object F = new Object();
    public static Gatekeeper G;
    public String[] H;
    public Handler I = new Handler();

    /* renamed from: J  reason: collision with root package name */
    public HashMap f9701J;
    public HashMap K;
    public HashMap L;
    public boolean M;
    public String N;
    public Preferences O;
    public QN P;
    public C5562xE Q;

    public Gatekeeper(Preferences preferences, QN qn, C5562xE xEVar) {
        C0591Jq0.a(this);
        this.O = preferences;
        this.Q = xEVar;
        this.P = qn;
        qn.b = this;
    }

    public static void clearGkOverride(String str) {
        Gatekeeper g = g();
        g.L.remove(str);
        g.l();
        g.j();
    }

    public static Gatekeeper g() {
        Gatekeeper gatekeeper;
        synchronized (F) {
            if (G == null) {
                G = new Gatekeeper(Preferences.getInstance(), new QN(), new C5562xE());
            }
            gatekeeper = G;
        }
        return gatekeeper;
    }

    public static void overrideGk(String str, String str2) {
        Gatekeeper g = g();
        g.L.put(str, Boolean.valueOf("1".equals(str2)));
        g.l();
        g.j();
    }

    @Override // defpackage.AbstractC0530Iq0
    public void a() {
        j();
    }

    @Override // defpackage.RN
    public void b(String str) {
        Object obj = ThreadUtils.f10596a;
        String[] e = e();
        Arrays.toString(e);
        try {
            HashMap hashMap = new HashMap();
            JSONArray jSONArray = new JSONObject(str).getJSONArray("data");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONArray jSONArray2 = jSONArray.getJSONObject(i).getJSONArray("results");
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    hashMap.put(e[i2], Boolean.valueOf(jSONArray2.getBoolean(i2)));
                }
            }
            this.K = hashMap;
            j();
            String replaceAll = str.replaceAll("\\s+", "");
            i(e);
            this.O.setString("PROJECT_CACHE", i(e));
            this.O.setString("GK_CACHE", replaceAll);
        } catch (JSONException e2) {
            StringBuilder i3 = AbstractC2531fV.i("JSON failed to parse: ");
            i3.append(e2.toString());
            Log.e("Gatekeeper", i3.toString());
        }
    }

    @Override // defpackage.AbstractC0530Iq0
    public void c(String str) {
        this.N = str;
        k();
    }

    public final String[] e() {
        TU[] values = TU.values();
        String[] strArr = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            strArr[i] = values[i].A0;
        }
        return strArr;
    }

    public final HashMap f() {
        HashMap hashMap = new HashMap();
        TU[] values = TU.values();
        for (TU tu : values) {
            hashMap.put(tu.A0, Boolean.valueOf(tu.B0));
        }
        return hashMap;
    }

    public boolean h(AbstractC1712al0 al0) {
        String str = ((TU) al0).A0;
        return ((Boolean) this.L.getOrDefault(str, (Boolean) this.f9701J.get(str))).booleanValue();
    }

    public final String i(String[] strArr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < strArr.length; i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append("\"");
            sb.append(strArr[i]);
            sb.append("\"");
        }
        sb.append("]");
        return sb.toString();
    }

    public final void j() {
        boolean z;
        String str;
        Set keySet = this.f9701J.keySet();
        String[] strArr = (String[]) keySet.toArray(new String[keySet.size()]);
        Arrays.sort(strArr);
        StringBuilder sb = new StringBuilder("{\"data\":[");
        HashMap f = f();
        int i = 0;
        while (i < strArr.length) {
            String str2 = strArr[i];
            boolean booleanValue = ((Boolean) this.f9701J.get(str2)).booleanValue();
            HashMap hashMap = this.K;
            if (!(hashMap == null || hashMap.get(str2) == null)) {
                booleanValue = ((Boolean) this.K.get(str2)).booleanValue();
            }
            if (this.L.get(str2) != null) {
                booleanValue = ((Boolean) this.L.get(str2)).booleanValue();
                z = true;
            } else {
                z = false;
            }
            StringBuilder i2 = AbstractC2531fV.i("{\"value\":\"");
            String str3 = "1";
            i2.append(booleanValue ? str3 : "0");
            i2.append("\",\"override\":\"");
            String h = AbstractC2531fV.h(i2, z ? str3 : "0", "\"}");
            if (((Boolean) this.f9701J.get(str2)).booleanValue()) {
                str = str3;
            } else {
                str = "0";
            }
            if (!Boolean.TRUE.equals(f.get(str2))) {
                str3 = "0";
            }
            sb.append("{\"gatekeeper_name\":\"" + str2 + "\",");
            sb.append("\"current_value\":\"" + str + "\",");
            sb.append("\"public_value\":\"" + str3 + "\",");
            sb.append("\"next_value\":" + h + "}");
            i++;
            if (i < strArr.length) {
                sb.append(",");
            }
        }
        sb.append("]}");
        N.M1YM8c6t(sb.toString());
    }

    public final void k() {
        String str;
        try {
            if (this.N == null) {
                str = null;
                if (!(str == null || this.M)) {
                    this.M = true;
                    this.P.d(str, null, 2, 2);
                    this.I.postDelayed(new SU(this), 3600000);
                    return;
                }
            }
            StringBuilder sb = new StringBuilder("https://graph.oculus.com/features");
            sb.append("?access_token=");
            sb.append(this.N);
            String[] e = e();
            for (int i = 0; i < e.length; i++) {
                sb.append("&projects[" + i + "]=");
                sb.append(URLEncoder.encode(e[i], "UTF-8"));
            }
            str = sb.toString();
            if (str == null) {
            }
        } catch (UnsupportedEncodingException e2) {
            StringBuilder i2 = AbstractC2531fV.i("Failed to encode url: ");
            i2.append(e2.toString());
            Log.e("Gatekeeper", i2.toString());
        }
    }

    public final void l() {
        Set keySet = this.L.keySet();
        String[] strArr = (String[]) keySet.toArray(new String[keySet.size()]);
        Arrays.sort(strArr);
        String[] strArr2 = new String[keySet.size()];
        for (int i = 0; i < keySet.size(); i++) {
            strArr2[i] = ((Boolean) this.L.get(strArr[i])).booleanValue() ? "1" : "0";
        }
        this.O.setString("OVERRIDE_PROJECT_NAMES", i(strArr));
        this.O.setString("OVERRIDE_VALUES", i(strArr2));
    }
}
