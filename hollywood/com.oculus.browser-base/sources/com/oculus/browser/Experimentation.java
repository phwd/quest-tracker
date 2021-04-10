package com.oculus.browser;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Experimentation extends RN {

    /* renamed from: a  reason: collision with root package name */
    public static String f9698a = "1562231310457908|f43f647cc211742b5a66f49910b24bb0";
    public static Experimentation b;
    public String[] c;
    public String d;
    public Context e;
    public Handler f = new Handler();
    public HashMap g;
    public HashSet h = new HashSet();
    public QN i;
    public UnifiedTelemetryLogger j;

    public Experimentation(QN qn) {
        this.i = qn;
        qn.b = this;
    }

    public static Experimentation e() {
        if (b == null) {
            b = new Experimentation(new QN());
        }
        return b;
    }

    public static boolean getBooleanValue(String str, String str2, boolean z) {
        Log.i("Experimentation", "getBooleanValue: " + str + " - " + str2);
        Experimentation e2 = e();
        e2.f();
        HashMap hashMap = e2.g;
        if (hashMap == null) {
            Log.e("Experimentation", "Experiment Data not loaded");
            return z;
        } else if (hashMap.containsKey(str)) {
            C4903tM tMVar = (C4903tM) e2.g.get(str);
            if (Boolean.valueOf(tMVar.d.containsKey(str2)).booleanValue()) {
                try {
                    String a2 = tMVar.a(str2);
                    e2.g(tMVar.f11338a, tMVar.c);
                    return a2.equals("1");
                } catch (NumberFormatException unused) {
                    Log.e("Experimentation", "Value for key not a valid boolean" + str2);
                    return z;
                }
            } else {
                Log.e("Experimentation", "Universe " + str + " has no param " + str2);
                return z;
            }
        } else {
            Log.e("Experimentation", "Has no setting for universe " + str);
            return z;
        }
    }

    public static int getIntegerValue(String str, String str2, int i2) {
        Log.i("Experimentation", "getIntegerValue: " + str + " - " + str2);
        Experimentation e2 = e();
        e2.f();
        HashMap hashMap = e2.g;
        if (hashMap == null) {
            Log.e("Experimentation", "Experiment Data not loaded");
            return i2;
        } else if (hashMap.containsKey(str)) {
            C4903tM tMVar = (C4903tM) e2.g.get(str);
            if (Boolean.valueOf(tMVar.d.containsKey(str2)).booleanValue()) {
                try {
                    String a2 = tMVar.a(str2);
                    e2.g(tMVar.f11338a, tMVar.c);
                    return Integer.parseInt(a2);
                } catch (NumberFormatException unused) {
                    Log.e("Experimentation", "Value for key not a valid integer" + str2);
                    return i2;
                }
            } else {
                Log.e("Experimentation", "Universe " + str + " has no param " + str2);
                return i2;
            }
        } else {
            Log.e("Experimentation", "Has no setting for universe " + str);
            return i2;
        }
    }

    public static void initialize(String str) {
        Log.i("Experimentation", "initialize: " + str);
        Experimentation e2 = e();
        String[] split = str.split(",");
        Objects.requireNonNull(e2);
        Object obj = ThreadUtils.f10596a;
        e2.c = split;
        e2.i();
    }

    public static void logExposure(String str, String str2) {
        Log.i("Experimentation", "logExposure: " + str + " - " + str2);
        e().g(str, str2);
    }

    public static native void nativeSetExperimentData(String str, String str2);

    @Override // defpackage.RN
    public void b(String str) {
        Object obj = ThreadUtils.f10596a;
        StringBuilder sb = new StringBuilder("[");
        for (int i2 = 0; i2 < this.c.length; i2++) {
            if (i2 != 0) {
                sb.append(",");
            }
            sb.append("\"");
            sb.append(this.c[i2]);
            sb.append("\"");
        }
        sb.append("]");
        String sb2 = sb.toString();
        Log.i("Experimentation", "fetchExperimentData result: " + sb2 + ", " + str);
        nativeSetExperimentData(sb2, str);
        Iterator it = this.h.iterator();
        while (it.hasNext()) {
            NewTabMessageHandler newTabMessageHandler = (NewTabMessageHandler) it.next();
            long j2 = newTabMessageHandler.F;
            if (j2 != 0) {
                newTabMessageHandler.nativeSetExperimentData(j2, sb2, str);
            }
        }
        Preferences.getInstance().setString("QE_CACHE", str);
        Preferences.getInstance().setString("UNIVERSE_CACHE", sb2);
        h(sb2, str);
    }

    public final void f() {
        if (this.g == null && Preferences.getInstance().a()) {
            String string = Preferences.getInstance().getString("QE_CACHE", null);
            String string2 = Preferences.getInstance().getString("UNIVERSE_CACHE", null);
            if (string != null && string2 != null) {
                h(string2, string);
            }
        }
    }

    public final void g(String str, String str2) {
        if (this.d == null || this.e == null || this.g == null || this.j == null) {
            Log.e("Experimentation", "missed logExposureCore: " + str + " " + str2 + " " + this.d);
            return;
        }
        Log.i("Experimentation", "logExposureCore: " + str + " " + str2 + " " + this.d);
        if (this.g.containsKey(str)) {
            String str3 = ((C4903tM) this.g.get(str)).b;
            AnalyticsEvent analyticsEvent = new AnalyticsEvent("quick_experiment_event");
            analyticsEvent.setInternalUseParam("exprID", "__qe__" + str3).setInternalUseParam("unit_id", this.d).setInternalUseParam("oculus_sessionless", "1").setExtra("group", str2).setExtra("event", "EXPOSURE");
            this.j.logExposure(analyticsEvent, false);
            return;
        }
        Log.e("Experimentation", "no experiment data for " + str);
    }

    public final void h(String str, String str2) {
        C4903tM tMVar;
        try {
            JSONArray jSONArray = new JSONArray(str2);
            JSONArray jSONArray2 = new JSONArray(str);
            HashMap hashMap = new HashMap();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONArray jSONArray3 = jSONArray.getJSONObject(i2).getJSONArray("data");
                String string = jSONArray2.getString(i2);
                for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                    JSONObject jSONObject = jSONArray3.getJSONObject(i3);
                    if (hashMap.containsKey(string)) {
                        tMVar = (C4903tM) hashMap.get(string);
                    } else {
                        C4903tM tMVar2 = new C4903tM(string, jSONObject.getString("name"), jSONObject.getString("group"));
                        hashMap.put(string, tMVar2);
                        tMVar = tMVar2;
                    }
                    JSONObject jSONObject2 = jSONObject.getJSONObject("params");
                    Iterator<String> keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        tMVar.d.put(next, jSONObject2.getJSONObject(next).getString("value"));
                    }
                }
            }
            this.g = hashMap;
        } catch (JSONException e2) {
            StringBuilder i4 = AbstractC2531fV.i("JSON failed to parse: ");
            i4.append(e2.toString());
            Log.e("Experimentation", i4.toString());
        }
    }

    public final void i() {
        String str;
        if (!(this.c == null || this.d == null)) {
            try {
                StringBuilder sb = new StringBuilder("[");
                StringBuilder sb2 = new StringBuilder("[");
                for (int i2 = 0; i2 < this.c.length; i2++) {
                    if (i2 != 0) {
                        sb.append(",");
                        sb2.append(",");
                    }
                    sb.append("\"");
                    sb.append("testexpt:qe:");
                    sb.append(this.c[i2]);
                    sb.append("\"");
                    sb2.append("\"\"");
                }
                sb.append("]");
                sb2.append("]");
                str = "https://graph.facebook.com/sessionless_test_experiment_members" + "?access_token=" + URLEncoder.encode(f9698a, "UTF-8") + "&member=" + URLEncoder.encode(this.d, "UTF-8") + "&quick_experiment_ids=" + URLEncoder.encode(sb.toString(), "UTF-8") + "&quick_experiment_hashes=" + URLEncoder.encode(sb2.toString(), "UTF-8");
            } catch (UnsupportedEncodingException e2) {
                StringBuilder i3 = AbstractC2531fV.i("Filed to encode url: ");
                i3.append(e2.toString());
                Log.e("Experimentation", i3.toString());
                str = null;
            }
            if (str != null) {
                Log.i("Experimentation", "fetching experiment data: " + str);
                this.i.d(str, null, 2, 2);
            }
            this.f.postDelayed(new RunnableC4733sM(this), 7200000);
        }
    }
}
