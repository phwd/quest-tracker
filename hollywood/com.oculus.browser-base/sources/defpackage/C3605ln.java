package defpackage;

import android.os.RemoteException;
import com.google.android.gms.cast.CastDevice;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: ln  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3605ln extends AbstractC0018Ag {
    public List f = new ArrayList();
    public C3434kn g = new C3434kn(this, null);

    public C3605ln(AbstractC5474wl wlVar) {
        super(wlVar);
        new C0214Dl(this);
    }

    public static void m(C3605ln lnVar) {
        lnVar.o();
        lnVar.n().a("update_session", lnVar.n().b());
    }

    @Override // defpackage.AbstractC0018Ag
    public void a(C2922hn hnVar) {
        super.a(hnVar);
        C2922hn hnVar2 = this.f7686a;
        C3434kn knVar = this.g;
        Objects.requireNonNull(hnVar2);
        SE0.e("Must be called from the main thread.");
        if (knVar != null) {
            hnVar2.f.add(knVar);
        }
        o();
    }

    @Override // defpackage.AbstractC0018Ag
    public void b() {
        if (this.f7686a != null) {
            this.f.clear();
            C2922hn hnVar = this.f7686a;
            C3434kn knVar = this.g;
            Objects.requireNonNull(hnVar);
            SE0.e("Must be called from the main thread.");
            if (knVar != null) {
                hnVar.f.remove(knVar);
            }
            super.b();
        }
    }

    @Override // defpackage.AbstractC0018Ag
    public void i(CastDevice castDevice, String str, String str2) {
        super.i(castDevice, str, str2);
        C0153Cl n = n();
        Objects.requireNonNull(n);
        C0092Bl bl = null;
        try {
            int i = new JSONObject(str2).getInt("requestId");
            if (n.e.indexOfKey(i) >= 0) {
                C0092Bl bl2 = (C0092Bl) n.e.get(i);
                try {
                    n.e.delete(i);
                } catch (JSONException unused) {
                }
                bl = bl2;
            }
        } catch (JSONException unused2) {
        }
        boolean z = false;
        if ("urn:x-cast:com.google.cast.media".equals(str)) {
            try {
                z = "MEDIA_STATUS".equals(new JSONObject(str2).getString("type"));
            } catch (JSONException unused3) {
            }
            if (z) {
                for (String str3 : n.i.i.keySet()) {
                    if (bl == null || !str3.equals(bl.f7755a)) {
                        n.h(str3, "v2_message", str2, -1);
                    }
                }
            }
            if (bl != null) {
                n.h(bl.f7755a, "v2_message", str2, bl.b);
                return;
            }
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("sessionId", n.h.f());
            jSONObject.put("namespaceName", str);
            jSONObject.put("message", str2);
            if (bl != null) {
                n.h(bl.f7755a, "app_message", jSONObject.toString(), bl.b);
            } else {
                n.a("app_message", jSONObject.toString());
            }
        } catch (JSONException e) {
            AbstractC1220Ua0.a("CafMR", "Failed to create the message wrapper", e);
        }
    }

    @Override // defpackage.AbstractC0018Ag
    public void j() {
        C0153Cl n = n();
        for (String str : n.i.i.keySet()) {
            Queue<Integer> queue = (Queue) n.f.get(str);
            if (queue == null) {
                n.h(str, "remove_session", n.h.f(), -1);
            } else {
                for (Integer num : queue) {
                    n.h(str, "remove_session", n.h.f(), num.intValue());
                }
                n.f.remove(str);
            }
        }
        super.j();
    }

    public final C0153Cl n() {
        return ((C5814yl) this.b).j;
    }

    public void o() {
        if (h() && this.f7686a.d() != null && this.f7686a.d().x() != null) {
            HashSet hashSet = new HashSet(this.f7686a.d().x());
            HashSet hashSet2 = new HashSet(this.f);
            hashSet2.removeAll(hashSet);
            hashSet.removeAll(this.f);
            Iterator it = hashSet2.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (h()) {
                    try {
                        C2922hn hnVar = this.f7686a;
                        Objects.requireNonNull(hnVar);
                        SE0.e("Must be called from the main thread.");
                        YV yv = hnVar.k;
                        if (yv != null) {
                            Objects.requireNonNull(hnVar.i);
                            try {
                                ((C3350kF1) yv.g(KF1.f8354a)).z(str);
                            } catch (RemoteException unused) {
                                throw new IOException("service error");
                            }
                        }
                        this.f.remove(str);
                    } catch (Exception e) {
                        AbstractC1220Ua0.a("CafSessionCtrl", "Failed to remove the namespace listener for %s", str, e);
                    }
                }
            }
            Iterator it2 = hashSet.iterator();
            while (it2.hasNext()) {
                String str2 = (String) it2.next();
                if (h()) {
                    try {
                        this.f7686a.i(str2, new C3092in(this));
                        this.f.add(str2);
                    } catch (Exception e2) {
                        AbstractC1220Ua0.a("CafSessionCtrl", "Failed to register namespace listener for %s", str2, e2);
                    }
                }
            }
        }
    }
}
