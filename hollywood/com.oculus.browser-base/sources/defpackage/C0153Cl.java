package defpackage;

import J.N;
import android.os.Handler;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.CastDevice;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import org.chromium.components.media_router.BrowserMediaRouter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: Cl  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0153Cl {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f7836a = {"PLAY", "LOAD", "PAUSE", "SEEK", "STOP_MEDIA", "MEDIA_SET_VOLUME", "MEDIA_GET_STATUS", "EDIT_TRACKS_INFO", "QUEUE_LOAD", "QUEUE_INSERT", "QUEUE_UPDATE", "QUEUE_REMOVE", "QUEUE_REORDER"};
    public static final String[] b = {"pause", "seek", "stream_volume", "stream_mute"};
    public static final Object c = new Object();
    public static Map d;
    public SparseArray e = new SparseArray();
    public C4931ta f = new C4931ta();
    public Queue g;
    public final C3605ln h;
    public final C5814yl i;
    public Handler j;

    public C0153Cl(C5814yl ylVar, C3605ln lnVar) {
        this.i = ylVar;
        this.h = lnVar;
        this.g = new ArrayDeque();
        this.j = new Handler();
        synchronized (c) {
            if (d == null) {
                HashMap hashMap = new HashMap();
                d = hashMap;
                hashMap.put("STOP_MEDIA", "STOP");
                d.put("MEDIA_SET_VOLUME", "SET_VOLUME");
                d.put("MEDIA_GET_STATUS", "GET_STATUS");
            }
        }
    }

    public static void f(Object obj) {
        JSONObject jSONObject;
        JSONArray names;
        int i2 = 0;
        if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            while (i2 < jSONArray.length()) {
                f(jSONArray.get(i2));
                i2++;
            }
        } else if ((obj instanceof JSONObject) && (names = (jSONObject = (JSONObject) obj).names()) != null) {
            while (i2 < names.length()) {
                String string = names.getString(i2);
                if (jSONObject.isNull(string)) {
                    jSONObject.remove(string);
                } else {
                    f(jSONObject.get(string));
                }
                i2++;
            }
        }
    }

    public void a(String str, String str2) {
        for (String str3 : this.i.i.keySet()) {
            h(str3, str, str2, -1);
        }
    }

    public String b() {
        String str;
        int i2;
        if (!this.h.h()) {
            return "{}";
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("level", this.h.f7686a.g());
            jSONObject.put("muted", this.h.f7686a.h());
            JSONObject jSONObject2 = new JSONObject();
            CastDevice e2 = this.h.f7686a.e();
            if (e2.F.startsWith("__cast_nearby__")) {
                str = e2.F.substring(16);
            } else {
                str = e2.F;
            }
            jSONObject2.put("label", str);
            jSONObject2.put("friendlyName", this.h.f7686a.e().I);
            jSONObject2.put("capabilities", l(this.h.d()));
            jSONObject2.put("volume", jSONObject);
            C2922hn hnVar = this.h.f7686a;
            Objects.requireNonNull(hnVar);
            SE0.e("Must be called from the main thread.");
            YV yv = hnVar.k;
            if (yv != null) {
                Objects.requireNonNull(hnVar.i);
                C3350kF1 kf1 = (C3350kF1) yv.g(KF1.f8354a);
                kf1.c();
                i2 = kf1.U;
            } else {
                i2 = -1;
            }
            jSONObject2.put("isActiveInput", i2);
            String str2 = null;
            jSONObject2.put("displayStatus", (Object) null);
            jSONObject2.put("receiverType", "cast");
            JSONArray jSONArray = new JSONArray();
            for (String str3 : this.h.f) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("name", str3);
                jSONArray.put(jSONObject3);
            }
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("sessionId", this.h.f());
            C2922hn hnVar2 = this.h.f7686a;
            Objects.requireNonNull(hnVar2);
            SE0.e("Must be called from the main thread.");
            YV yv2 = hnVar2.k;
            if (yv2 != null) {
                Objects.requireNonNull(hnVar2.i);
                C3350kF1 kf12 = (C3350kF1) yv2.g(KF1.f8354a);
                kf12.c();
                str2 = kf12.N;
            }
            jSONObject4.put("statusText", str2);
            jSONObject4.put("receiver", jSONObject2);
            jSONObject4.put("namespaces", jSONArray);
            jSONObject4.put("media", l(new ArrayList()));
            jSONObject4.put("status", "connected");
            jSONObject4.put("transportId", "web-4");
            ApplicationMetadata d2 = this.h.f7686a.d();
            if (d2 != null) {
                jSONObject4.put("appId", d2.F);
            } else {
                jSONObject4.put("appId", this.h.c.f9983a.a());
            }
            jSONObject4.put("displayName", this.h.f7686a.e().I);
            return jSONObject4.toString();
        } catch (JSONException e3) {
            AbstractC1220Ua0.f("CafMR", "Building session message failed", e3);
            return "{}";
        }
    }

    public final boolean c(JSONObject jSONObject) {
        C3458kv kvVar;
        String string = jSONObject.getString("clientId");
        if (string == null || (kvVar = (C3458kv) this.i.i.get(string)) == null) {
            return false;
        }
        kvVar.f = true;
        if (this.h.h()) {
            h(kvVar.b, "new_session", b(), -1);
        }
        C5814yl ylVar = this.i;
        Objects.requireNonNull(ylVar);
        for (String str : kvVar.g) {
            AbstractC5800yg0 yg0 = ylVar.c;
            String str2 = kvVar.f10313a;
            BrowserMediaRouter browserMediaRouter = (BrowserMediaRouter) yg0;
            long j2 = browserMediaRouter.b;
            if (j2 != 0) {
                N.MM5f2cm0(j2, browserMediaRouter, str2, str);
            }
        }
        kvVar.g.clear();
        return true;
    }

    public final boolean d(JSONObject jSONObject) {
        C3458kv kvVar;
        String string = jSONObject.getString("clientId");
        if (string == null || !this.h.h()) {
            return false;
        }
        if (!this.h.f().equals(jSONObject.getString("message")) || (kvVar = (C3458kv) this.i.i.get(string)) == null) {
            return false;
        }
        int optInt = jSONObject.optInt("sequenceNumber", -1);
        C5814yl ylVar = this.i;
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("type", "leave_session");
        jSONObject2.put("sequenceNumber", optInt);
        jSONObject2.put("timeoutMillis", 0);
        jSONObject2.put("clientId", string);
        ylVar.x(string, jSONObject2.toString());
        ArrayList arrayList = new ArrayList();
        Iterator it = this.i.i.values().iterator();
        while (true) {
            boolean z = true;
            if (!it.hasNext()) {
                break;
            }
            C3458kv kvVar2 = (C3458kv) it.next();
            if ((!"tab_and_origin_scoped".equals(kvVar.c) || !AbstractC3776mn.b(kvVar2.d, kvVar.d) || kvVar2.e != kvVar.e) && (!"origin_scoped".equals(kvVar.c) || !AbstractC3776mn.b(kvVar2.d, kvVar.d))) {
                z = false;
            }
            if (z) {
                arrayList.add(kvVar2);
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            this.i.t(((C3458kv) it2.next()).f10313a, null);
        }
        return true;
    }

    public final boolean e(JSONObject jSONObject) {
        String string;
        boolean z;
        boolean z2;
        String string2 = jSONObject.getString("type");
        if ("v2_message".equals(string2)) {
            String string3 = jSONObject.getString("clientId");
            if (string3 != null && this.i.i.containsKey(string3)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("message");
                String string4 = jSONObject2.getString("type");
                int optInt = jSONObject.optInt("sequenceNumber", -1);
                if ("STOP".equals(string4)) {
                    Queue queue = (Queue) this.f.getOrDefault(string3, null);
                    if (queue == null) {
                        queue = new ArrayDeque();
                        this.f.put(string3, queue);
                    }
                    queue.add(Integer.valueOf(optInt));
                    this.h.c();
                    return true;
                } else if ("SET_VOLUME".equals(string4)) {
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("volume");
                    if (jSONObject3 != null && this.h.h()) {
                        try {
                            if (jSONObject3.isNull("muted") || this.h.f7686a.h() == (z2 = jSONObject3.getBoolean("muted"))) {
                                z = false;
                            } else {
                                this.h.f7686a.j(z2);
                                z = true;
                            }
                            if (!jSONObject3.isNull("level")) {
                                double d2 = jSONObject3.getDouble("level");
                                double g2 = this.h.f7686a.g();
                                if (!Double.isNaN(g2) && Math.abs(g2 - d2) > 1.0E-7d) {
                                    this.h.f7686a.k(d2);
                                    z = true;
                                }
                            }
                            if (z) {
                                this.g.add(new C0092Bl(string3, optInt));
                                return true;
                            }
                            this.j.post(new RunnableC0031Al(this, string3, optInt));
                            return true;
                        } catch (IOException | IllegalStateException e2) {
                            AbstractC1220Ua0.a("CafMR", "Failed to send volume command: " + e2, new Object[0]);
                        }
                    }
                } else if (!Arrays.asList(f7836a).contains(string4)) {
                    return true;
                } else {
                    if (d.containsKey(string4)) {
                        jSONObject2.put("type", (String) d.get(string4));
                    }
                    return i(jSONObject2, "urn:x-cast:com.google.cast.media", string3, optInt);
                }
            }
            return false;
        } else if ("app_message".equals(string2)) {
            String string5 = jSONObject.getString("clientId");
            if (string5 == null || !this.i.i.containsKey(string5)) {
                return false;
            }
            JSONObject jSONObject4 = jSONObject.getJSONObject("message");
            if (!this.h.f().equals(jSONObject4.getString("sessionId")) || (string = jSONObject4.getString("namespaceName")) == null || string.isEmpty() || !this.h.f.contains(string)) {
                return false;
            }
            int optInt2 = jSONObject.optInt("sequenceNumber", -1);
            Object obj = jSONObject4.get("message");
            if (obj == null) {
                return false;
            }
            if (obj instanceof String) {
                return k(jSONObject4.getString("message"), string, string5, optInt2);
            }
            return i(jSONObject4.getJSONObject("message"), string, string5, optInt2);
        } else {
            AbstractC1220Ua0.a("CafMR", "Unsupported message: %s", jSONObject);
            return false;
        }
    }

    public final void g(JSONObject jSONObject) {
        jSONObject.put("sessionId", this.h.f());
        JSONArray jSONArray = jSONObject.getJSONArray("status");
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
            jSONObject2.put("sessionId", this.h.f());
            if (jSONObject2.has("supportedMediaCommands")) {
                JSONArray jSONArray2 = new JSONArray();
                int i3 = jSONObject2.getInt("supportedMediaCommands");
                for (int i4 = 0; i4 < 4; i4++) {
                    if (((1 << i4) & i3) != 0) {
                        jSONArray2.put(b[i4]);
                    }
                }
                jSONObject2.put("supportedMediaCommands", jSONArray2);
            }
        }
    }

    public void h(String str, String str2, String str3, int i2) {
        C5814yl ylVar = this.i;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", str2);
            jSONObject.put("sequenceNumber", i2);
            jSONObject.put("timeoutMillis", 0);
            jSONObject.put("clientId", str);
            if (str3 == null || "remove_session".equals(str2) || "disconnect_session".equals(str2)) {
                jSONObject.put("message", str3);
                ylVar.x(str, jSONObject.toString());
            }
            JSONObject jSONObject2 = new JSONObject(str3);
            if ("v2_message".equals(str2) && "MEDIA_STATUS".equals(jSONObject2.getString("type"))) {
                g(jSONObject2);
            }
            jSONObject.put("message", jSONObject2);
            ylVar.x(str, jSONObject.toString());
        } catch (JSONException e2) {
            AbstractC1220Ua0.a("CafMR", "Failed to build the reply: " + e2, new Object[0]);
        }
    }

    public boolean i(JSONObject jSONObject, String str, String str2, int i2) {
        if (!this.h.h()) {
            return false;
        }
        f(jSONObject);
        if (i2 != -1) {
            int optInt = jSONObject.optInt("requestId", 0);
            if (optInt == 0) {
                synchronized (C2068cn.f9632a) {
                    if (C2068cn.b == null) {
                        C2068cn.b = new C2068cn();
                    }
                }
                C2068cn cnVar = C2068cn.b;
                int i3 = cnVar.c;
                if (i3 == 0) {
                    cnVar.c = i3 + 1;
                }
                int i4 = cnVar.c;
                cnVar.c = i4 + 1;
                jSONObject.put("requestId", i4);
                optInt = i4;
            }
            this.e.append(optInt, new C0092Bl(str2, i2));
        }
        return k(jSONObject.toString(), str, str2, i2);
    }

    public void j(C1363Wh0 wh0, String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("label", wh0.f9167a);
            jSONObject.put("friendlyName", wh0.b);
            jSONObject.put("capabilities", l(this.h.d()));
            jSONObject.put("volume", (Object) null);
            jSONObject.put("isActiveInput", (Object) null);
            jSONObject.put("displayStatus", (Object) null);
            jSONObject.put("receiverType", "cast");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("receiver", jSONObject);
            jSONObject2.put("action", str2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("type", "receiver_action");
            jSONObject3.put("sequenceNumber", -1);
            jSONObject3.put("timeoutMillis", 0);
            jSONObject3.put("clientId", str);
            jSONObject3.put("message", jSONObject2);
            this.i.x(str, jSONObject3.toString());
        } catch (JSONException e2) {
            AbstractC1220Ua0.a("CafMR", "Failed to send receiver action message", e2);
        }
    }

    public boolean k(String str, String str2, String str3, int i2) {
        AbstractC4439qg qgVar;
        if (!this.h.h()) {
            return false;
        }
        C2922hn hnVar = this.h.f7686a;
        Objects.requireNonNull(hnVar);
        SE0.e("Must be called from the main thread.");
        YV yv = hnVar.k;
        if (yv != null) {
            Objects.requireNonNull(hnVar.i);
            qgVar = yv.f(new C3353kG1(yv, str2, str));
        } else {
            qgVar = null;
        }
        if (TextUtils.equals(str2, "urn:x-cast:com.google.cast.media")) {
            return true;
        }
        qgVar.b(new C5984zl(this, str3, i2));
        return true;
    }

    public final JSONArray l(List list) {
        JSONArray jSONArray = new JSONArray();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put((String) it.next());
        }
        return jSONArray;
    }
}
