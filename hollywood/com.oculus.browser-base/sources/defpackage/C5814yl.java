package defpackage;

import J.N;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.chromium.components.media_router.BrowserMediaRouter;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: yl  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5814yl extends AbstractC5474wl {
    public C3458kv h;
    public final Map i = new HashMap();
    public C0153Cl j;
    public final C3605ln k;

    public C5814yl(C3246jh0 jh0, AbstractC5800yg0 yg0) {
        super(jh0, yg0);
        C3605ln lnVar = new C3605ln(this);
        this.k = lnVar;
        this.j = new C0153Cl(this, lnVar);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006c, code lost:
        if (r12 == r8.h.e) goto L_0x00ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00af, code lost:
        if (r12 == r3.e) goto L_0x00ef;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00cc, code lost:
        if (r0.equals(r8.k.f()) != false) goto L_0x00ef;
     */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0101  */
    @Override // defpackage.AbstractC5970zg0
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d(java.lang.String r9, java.lang.String r10, java.lang.String r11, int r12, int r13) {
        /*
        // Method dump skipped, instructions count: 295
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5814yl.d(java.lang.String, java.lang.String, java.lang.String, int, int):void");
    }

    @Override // defpackage.AbstractC5970zg0
    public void f(String str, String str2) {
        if (this.e.containsKey(str)) {
            C0153Cl cl = this.j;
            Objects.requireNonNull(cl);
            try {
                JSONObject jSONObject = new JSONObject(str2);
                String optString = jSONObject.optString("type");
                char c = 65535;
                int hashCode = optString.hashCode();
                if (hashCode != -1409221232) {
                    if (hashCode != -906487690) {
                        if (hashCode == 784257294) {
                            if (optString.equals("leave_session")) {
                                c = 2;
                            }
                        }
                    } else if (optString.equals("client_connect")) {
                        c = 0;
                    }
                } else if (optString.equals("client_disconnect")) {
                    c = 1;
                }
                if (c == 0) {
                    cl.c(jSONObject);
                } else if (c == 1) {
                    String string = jSONObject.getString("clientId");
                    if (string != null) {
                        C3458kv kvVar = (C3458kv) cl.i.i.get(string);
                        if (kvVar != null) {
                            cl.i.t(kvVar.f10313a, null);
                        }
                    }
                } else if (c != 2) {
                    cl.e(jSONObject);
                } else {
                    cl.d(jSONObject);
                }
            } catch (JSONException e) {
                AbstractC1220Ua0.a("CafMR", "JSONException while handling internal message: " + e, new Object[0]);
            }
        }
    }

    @Override // defpackage.AbstractC5970zg0, defpackage.AbstractC5474wl
    public void m(String str) {
        C3458kv w;
        C1363Wh0 g;
        boolean containsKey = this.e.containsKey(str);
        super.m(str);
        if (containsKey && (w = w(str)) != null && (g = this.k.g()) != null) {
            this.j.j(g, w.b, "stop");
        }
    }

    @Override // defpackage.AbstractC5474wl
    public void n(C1694af0 af0, String str, int i2, int i3, boolean z) {
        super.n(af0, str, i2, i3, z);
        C1897bn e = C1897bn.e(af0.c);
        String str2 = e.e;
        if (str2 != null && !this.i.containsKey(str2)) {
            this.i.put(str2, new C3458kv(af0.f9441a, str2, e.d, e.f, str, i2));
        }
    }

    @Override // defpackage.AbstractC5474wl
    public AbstractC1424Xh0 p(String str) {
        return C1897bn.e(str);
    }

    @Override // defpackage.AbstractC5474wl
    public void r(C2922hn hnVar, String str) {
        super.r(hnVar, str);
        for (C3458kv kvVar : this.i.values()) {
            C0153Cl cl = this.j;
            String str2 = kvVar.f10313a;
            cl.j(this.k.g(), kvVar.b, "cast");
        }
        C0153Cl cl2 = this.j;
        for (C3458kv kvVar2 : cl2.i.i.values()) {
            if (kvVar2.f) {
                cl2.h(kvVar2.b, "new_session", cl2.b(), -1);
            }
        }
        this.k.f7686a.f().p();
    }

    @Override // defpackage.AbstractC5474wl
    public void u(String str) {
        C3458kv w = w(str);
        if (w != null) {
            this.h = (C3458kv) this.i.remove(w.b);
        }
        this.e.remove(str);
    }

    @Override // defpackage.AbstractC5474wl
    public AbstractC0018Ag v() {
        return this.k;
    }

    public final C3458kv w(String str) {
        for (C3458kv kvVar : this.i.values()) {
            if (kvVar.f10313a.equals(str)) {
                return kvVar;
            }
        }
        return null;
    }

    public void x(String str, String str2) {
        C3458kv kvVar = (C3458kv) this.i.get(str);
        if (kvVar != null) {
            if (!kvVar.f) {
                kvVar.g.add(str2);
                return;
            }
            AbstractC5800yg0 yg0 = this.c;
            String str3 = kvVar.f10313a;
            BrowserMediaRouter browserMediaRouter = (BrowserMediaRouter) yg0;
            long j2 = browserMediaRouter.b;
            if (j2 != 0) {
                N.MM5f2cm0(j2, browserMediaRouter, str3, str2);
            }
        }
    }
}
