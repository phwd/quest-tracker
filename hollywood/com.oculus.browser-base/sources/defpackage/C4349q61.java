package defpackage;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.Clipboard;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.url.GURL;

/* renamed from: q61  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4349q61 implements AbstractC2787gz {

    /* renamed from: a  reason: collision with root package name */
    public final TabImpl f11116a;
    public final AbstractC0124Ca1 b;
    public WK c;
    public final Q31 d;
    public final Runnable e;
    public final Q31 f;

    public C4349q61(Tab tab, AbstractC0124Ca1 ca1, Q31 q31, Runnable runnable, Q31 q312) {
        TabImpl tabImpl = (TabImpl) tab;
        this.f11116a = tabImpl;
        this.b = ca1;
        this.d = q31;
        this.e = runnable;
        this.f = q312;
        C4178p61 p61 = new C4178p61(this);
        this.c = p61;
        tabImpl.P.b(p61);
    }

    @Override // defpackage.AbstractC2787gz
    public boolean a() {
        return true;
    }

    public void b(GURL gurl, String str) {
        Profile profile;
        Q31 q31 = this.d;
        if (q31 != null && q31.get() != null) {
            DL dl = (DL) this.d.get();
            boolean z = this.f11116a.H;
            dl.R = gurl;
            if (!z) {
                profile = Profile.b();
            } else {
                Z00.b(dl.G);
                profile = Profile.b().c();
            }
            if (dl.M == null) {
                dl.M = new IL(dl.K, new CL(dl.F), dl.L, (int) (((float) dl.F.getResources().getDimensionPixelSize(R.dimen.f26330_resource_name_obfuscated_RES_2131166252)) / dl.G.I.e));
            }
            if (dl.N == null) {
                WebContents a2 = AbstractC5342vx1.a(profile, true);
                dl.N = a2;
                Context context = dl.F;
                int i = AbstractViewGroup$OnHierarchyChangeListenerC1520Yy.F;
                C1459Xy xy = new C1459Xy(context, null, a2);
                dl.O = xy;
                dl.N.J("89.0.4389.105", new ViewAndroidDelegate(xy), dl.O, dl.G, new C3466kx1());
                N.Mt4iWzCb(dl.N);
                AL al = new AL(dl);
                dl.Q = al;
                ((C5638xj) dl.K).j(al);
                NL nl = new NL(dl.F, new RunnableC5581xL(dl), new RunnableC5751yL(dl), new RunnableC5921zL(dl), dl.a());
                dl.P = nl;
                IL il = dl.M;
                WebContents webContents = dl.N;
                AbstractViewGroup$OnHierarchyChangeListenerC1520Yy yy = dl.O;
                il.i = profile;
                il.e = webContents;
                il.f = nl;
                il.g = new FL(il, webContents);
                HL hl = new HL(il);
                il.h = hl;
                NL nl2 = il.f;
                nl2.h = il.e;
                nl2.i = yy;
                if (yy.getParent() != null) {
                    ((ViewGroup) nl2.i.getParent()).removeView(nl2.i);
                }
                ((C1544Zg1) nl2.j).a(nl2.h, nl2.i, hl);
                dl.H.addOnLayoutChangeListener(dl);
            }
            dl.T = false;
            dl.U = false;
            dl.V = false;
            IL il2 = dl.M;
            il2.e.f().c(new LoadUrlParams(gurl.h(), 0));
            ((TextView) il2.f.f.findViewById(R.id.title)).setText(str);
            ((C5638xj) il2.f8219a).u(il2.f, true);
            Tm1 a3 = Um1.a(profile);
            if (a3.isInitialized()) {
                a3.notifyEvent("ephemeral_tab_used");
            }
        }
    }

    public void c(GURL gurl, boolean z) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(gurl.h()));
        intent.setFlags(268435456);
        intent.setClass(ContextUtils.getApplicationContext(), Lr.class);
        if (z) {
            intent.putExtra("com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", true);
            intent.putExtra("com.android.browser.application_id", ContextUtils.getApplicationContext().getPackageName());
            S20.a(intent);
            intent.putExtra("org.chromium.chrome.browser.tab_launch_type", 1);
        }
        U20.q(this.f11116a.getContext(), intent);
    }

    public void d(String str, int i) {
        Clipboard.getInstance().setText(str);
        if (i == 0) {
            this.e.run();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean e(org.chromium.url.GURL r6, boolean r7) {
        /*
        // Method dump skipped, instructions count: 173
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4349q61.e(org.chromium.url.GURL, boolean):boolean");
    }
}
