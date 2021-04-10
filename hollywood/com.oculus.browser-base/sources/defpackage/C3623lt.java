package defpackage;

import android.content.Context;
import android.content.Intent;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* renamed from: lt  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3623lt extends A61 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f10381a;
    public AbstractC3226ja1 b;
    public final C4675s11 c;
    public WindowAndroid d;
    public final boolean e;
    public TabModel f;
    public AbstractC5953za1 g;
    public Q31 h;
    public final C1918bu i;
    public final C1280Va j;

    public C3623lt(Context context, AbstractC3226ja1 ja1, WindowAndroid windowAndroid, C4675s11 s11, Q31 q31, boolean z, C1918bu buVar, C1280Va va) {
        this.f10381a = context;
        this.b = ja1;
        this.c = s11;
        this.d = windowAndroid;
        this.h = q31;
        this.e = z;
        this.i = buVar;
        this.j = va;
    }

    @Override // defpackage.A61
    public Tab a(C0797Nb1 nb1, byte[] bArr, int i2, boolean z, int i3) {
        int i4;
        Tab tab;
        C3452kt ktVar = new C3452kt(this.b.P());
        boolean b2 = ((C0002Aa1) this.g).b(3, z);
        C1280Va va = this.j;
        AbstractC1097Sa sa = (AbstractC1097Sa) va.f9093a.get(i2);
        va.f9093a.remove(i2);
        if (sa == null || sa.m() == null) {
            tab = null;
            i4 = 2;
        } else {
            tab = ((C5446wb1) sa).f11553a;
            if (tab.a() == z) {
                if (tab.getUrl().g().equals("file")) {
                    tab.B();
                } else if (tab.f()) {
                    tab.q();
                }
                i4 = 1;
            } else {
                throw new IllegalStateException("Incognito state mismatch. TabState: " + z + ". Tab: " + tab.a());
            }
        }
        if (tab == null) {
            C2128d61 d61 = new C2128d61();
            d61.d(3);
            d61.c(2);
            d61.h = true;
            d61.f9748a = i2;
            d61.c = ktVar;
            d61.d = z;
            d61.e = this.d;
            d61.l = g();
            d61.m = !b2;
            d61.n = nb1;
            d61.o = bArr;
            d61.j = this.b;
            tab = d61.a();
        }
        if (z == this.e) {
            this.f.f(tab, i3, 3, i4);
            return tab;
        }
        StringBuilder i5 = AbstractC2531fV.i("Incognito state mismatch. TabState: ");
        i5.append(nb1.h);
        i5.append(". Creator: ");
        i5.append(this.e);
        throw new IllegalStateException(i5.toString());
    }

    @Override // defpackage.A61
    public Tab b(LoadUrlParams loadUrlParams, int i2, Tab tab) {
        return i(loadUrlParams, i2, tab, null);
    }

    @Override // defpackage.A61
    public boolean c(Tab tab, WebContents webContents, int i2, GURL gurl) {
        int i3 = -1;
        int id = tab != null ? tab.getId() : -1;
        if (this.f.t(id)) {
            return false;
        }
        int e2 = AbstractC1160Ta1.e(this.f, id);
        if (e2 != -1) {
            i3 = e2 + 1;
        }
        boolean b2 = ((C0002Aa1) this.g).b(i2, this.e);
        C61 g2 = tab == null ? g() : null;
        C2128d61 b3 = C2128d61.b(!b2);
        b3.b = tab;
        b3.d = this.e;
        b3.e = this.d;
        b3.f = Integer.valueOf(i2);
        b3.k = webContents;
        b3.l = g2;
        b3.m = !b2;
        b3.j = this.b;
        this.f.f(b3.a(), i3, i2, !b2);
        return true;
    }

    @Override // defpackage.A61
    public boolean d() {
        return false;
    }

    @Override // defpackage.A61
    public Tab f(String str, int i2) {
        return j(str, i2, null, 0);
    }

    public C61 g() {
        Q31 q31 = this.h;
        if (q31 != null) {
            return (C61) q31.get();
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0054, code lost:
        if ((r14.i1 != null) != false) goto L_0x0056;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0060  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0069 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final org.chromium.chrome.browser.tab.Tab h(org.chromium.content_public.browser.LoadUrlParams r19, int r20, org.chromium.chrome.browser.tab.Tab r21, int r22, android.content.Intent r23) {
        /*
        // Method dump skipped, instructions count: 540
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C3623lt.h(org.chromium.content_public.browser.LoadUrlParams, int, org.chromium.chrome.browser.tab.Tab, int, android.content.Intent):org.chromium.chrome.browser.tab.Tab");
    }

    public Tab i(LoadUrlParams loadUrlParams, int i2, Tab tab, Intent intent) {
        int i3 = this.f.i(tab);
        int i4 = -1;
        if (i3 != -1) {
            i4 = i3 + 1;
        }
        return h(loadUrlParams, i2, tab, i4, intent);
    }

    public Tab j(String str, int i2, Intent intent, long j2) {
        LoadUrlParams loadUrlParams = new LoadUrlParams(str, 0);
        loadUrlParams.k = j2;
        return i(loadUrlParams, i2, null, intent);
    }
}
