package defpackage;

import J.N;
import android.content.Context;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.net.NetworkChangeNotifier;
import org.chromium.url.GURL;

/* renamed from: Or0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0897Or0 extends WK implements AbstractC5481wn0 {
    public static Map F;
    public final View$OnClickListenerC5098uY0 G;
    public final AbstractC4758sY0 H;
    public final AbstractC0124Ca1 I;

    /* renamed from: J  reason: collision with root package name */
    public final AbstractC0855Oa1 f8653J;
    public final Map K = new HashMap();
    public boolean L;
    public Tab M;

    public C0897Or0(AbstractC0124Ca1 ca1, View$OnClickListenerC5098uY0 uy0, AbstractC4758sY0 sy0) {
        this.G = uy0;
        this.H = sy0;
        this.I = ca1;
        this.f8653J = new C0714Lr0(this, ca1);
        this.L = false;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void A(Tab tab, int i) {
        this.M = null;
        this.G.j(this.H);
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void J(Tab tab, GURL gurl) {
        if (V(tab)) {
            ((C0836Nr0) this.K.get(Integer.valueOf(tab.getId()))).f8581a = true;
            W(tab, false);
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void P(Tab tab, int i) {
        W(tab, false);
        this.M = tab;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void S(Tab tab) {
        if (!AbstractC2254ds0.f(tab)) {
            X(tab);
        } else if (V(tab)) {
            ((C0836Nr0) this.K.get(Integer.valueOf(tab.getId()))).f8581a = false;
            ((C0836Nr0) this.K.get(Integer.valueOf(tab.getId()))).b = false;
        }
        this.G.j(this.H);
    }

    public boolean V(Tab tab) {
        return this.K.containsKey(Integer.valueOf(tab.getId()));
    }

    public void W(Tab tab, boolean z) {
        boolean z2;
        if (tab != null && !tab.G() && !tab.isHidden() && AbstractC2254ds0.f(tab)) {
            Objects.requireNonNull(AbstractC2254ds0.b());
            OfflinePageBridge a2 = OfflinePageBridge.a(Profile.a(tab.l()));
            if (a2 == null) {
                z2 = false;
            } else {
                z2 = N.MYT2RMuB(a2.f10718a, a2, tab.l());
            }
            if (!z2 && AbstractC2254ds0.e()) {
                if (V(tab) && ((C0836Nr0) this.K.get(Integer.valueOf(tab.getId()))).f8581a) {
                    if (!(V(tab) && ((C0836Nr0) this.K.get(Integer.valueOf(tab.getId()))).b) || z) {
                        Context context = tab.getContext();
                        View$OnClickListenerC5098uY0 uy0 = this.G;
                        AbstractC4758sY0 sy0 = this.H;
                        int id = tab.getId();
                        Objects.requireNonNull(AbstractC2254ds0.b());
                        if (id != -1) {
                            String str = "showReloadSnackbar called with controller " + sy0;
                            C4076oY0 c = C4076oY0.c(context.getString(R.string.f56540_resource_name_obfuscated_RES_2131952971), sy0, 0, 3);
                            c.i = false;
                            String string = context.getString(R.string.f60100_resource_name_obfuscated_RES_2131953327);
                            Integer valueOf = Integer.valueOf(id);
                            c.d = string;
                            c.e = valueOf;
                            c.j = 6000;
                            uy0.l(c);
                        }
                        ((C0836Nr0) this.K.get(Integer.valueOf(tab.getId()))).b = true;
                    }
                }
            }
        }
    }

    public void X(Tab tab) {
        if (V(tab)) {
            this.K.remove(Integer.valueOf(tab.getId()));
            tab.I(this);
        }
        if (this.K.isEmpty() && this.L) {
            NetworkChangeNotifier.j(this);
            this.L = false;
        }
    }

    @Override // defpackage.AbstractC5481wn0
    public void a(int i) {
        String str = "Got connectivity event, connectionType: " + i + ", is connected: " + AbstractC2254ds0.e() + ", controller: " + this.H;
        W(this.M, true);
        if (!AbstractC2254ds0.e()) {
            for (C0836Nr0 nr0 : this.K.values()) {
                nr0.b = false;
            }
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void r(Tab tab) {
        X(tab);
        this.G.j(this.H);
    }
}
