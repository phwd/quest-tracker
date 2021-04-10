package org.chromium.content.browser.framehost;

import J.N;
import android.graphics.Bitmap;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.NavigationEntry;
import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NavigationControllerImpl implements NavigationController {

    /* renamed from: a  reason: collision with root package name */
    public long f10924a;

    public NavigationControllerImpl(long j) {
        this.f10924a = j;
    }

    public static void addToNavigationHistory(Object obj, Object obj2) {
        ((C0948Pm0) obj).f8712a.add((NavigationEntry) obj2);
    }

    public static NavigationControllerImpl create(long j) {
        return new NavigationControllerImpl(j);
    }

    public static NavigationEntry createNavigationEntry(int i, GURL gurl, GURL gurl2, GURL gurl3, GURL gurl4, String str, Bitmap bitmap, int i2, long j) {
        return new NavigationEntry(i, gurl, gurl2, gurl3, gurl4, str, bitmap, i2, j);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public boolean a(int i) {
        long j = this.f10924a;
        return j != 0 && N.M4jjW0jG(j, this, i);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void b(boolean z) {
        long j = this.f10924a;
        if (j != 0) {
            N.M81h$w2r(j, this, z);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void c(LoadUrlParams loadUrlParams) {
        long j = this.f10924a;
        if (j != 0) {
            String str = loadUrlParams.f10938a;
            int i = loadUrlParams.c;
            C2512fL0 fl0 = loadUrlParams.d;
            N.MAqmDh4t(j, this, str, 0, i, fl0 != null ? fl0.f9916a : null, fl0 != null ? fl0.b : 0, loadUrlParams.g, loadUrlParams.a("\n", false), loadUrlParams.h, null, null, null, false, loadUrlParams.i, loadUrlParams.j);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void d(boolean z, boolean z2) {
        long j = this.f10924a;
        if (j != 0) {
            N.MrilyCK6(j, this, z, z2);
        }
    }

    public final void destroy() {
        this.f10924a = 0;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void e() {
        long j = this.f10924a;
        if (j != 0) {
            N.Mdhd0AR3(j, this);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public boolean f() {
        long j = this.f10924a;
        return j != 0 && N.MsUTH_HQ(j, this);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void g() {
        N.MQgIP2q9(this.f10924a, this);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public boolean h() {
        long j = this.f10924a;
        return j != 0 && N.MgAw5sIR(j, this);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public boolean i(int i) {
        long j = this.f10924a;
        if (j != 0) {
            return N.MVbDeBRx(j, this, i);
        }
        return false;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void j() {
        long j = this.f10924a;
        if (j != 0) {
            N.MEEEhNfT(j, this);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public boolean k() {
        long j = this.f10924a;
        return j != 0 && N.MCUxt83x(j, this);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public NavigationEntry l(int i) {
        long j = this.f10924a;
        if (j != 0) {
            return (NavigationEntry) N.MLcS2$Fy(j, this, i);
        }
        return null;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public int m() {
        long j = this.f10924a;
        if (j != 0) {
            return N.Mil0WqAo(j, this);
        }
        return -1;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public boolean n(int i) {
        long j = this.f10924a;
        if (j == 0) {
            return false;
        }
        return N.MVjP87pN(j, this, i);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void o() {
        long j = this.f10924a;
        if (j != 0) {
            N.MWJb9aa$(j, this);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public boolean p() {
        long j = this.f10924a;
        if (j == 0) {
            return false;
        }
        return N.MCIN9$qH(j, this);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void q(boolean z) {
        long j = this.f10924a;
        if (j != 0) {
            N.My0bMgld(j, this, z);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public NavigationEntry r() {
        long j = this.f10924a;
        if (j != 0) {
            return (NavigationEntry) N.MHqMy8sY(j, this);
        }
        return null;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public NavigationEntry s() {
        long j = this.f10924a;
        if (j != 0) {
            return (NavigationEntry) N.Mgxh2WfC(j, this);
        }
        return null;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void t() {
        long j = this.f10924a;
        if (j != 0) {
            N.M2mQucXv(j, this);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void u() {
        long j = this.f10924a;
        if (j != 0) {
            N.MNF4lMMb(j, this);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void v(int i) {
        long j = this.f10924a;
        if (j != 0) {
            N.MuxwAbEo(j, this, i);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public void w() {
        long j = this.f10924a;
        if (j != 0) {
            N.Mp5SLq_N(j, this);
        }
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public boolean x() {
        long j = this.f10924a;
        return j != 0 && N.MEOFE6aD(j, this);
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public C0948Pm0 y() {
        if (this.f10924a == 0) {
            return null;
        }
        C0948Pm0 pm0 = new C0948Pm0();
        pm0.b = N.MUZRDUOx(this.f10924a, this, pm0);
        return pm0;
    }

    @Override // org.chromium.content_public.browser.NavigationController
    public C0948Pm0 z(boolean z, int i) {
        if (this.f10924a == 0) {
            return null;
        }
        C0948Pm0 pm0 = new C0948Pm0();
        N.Mx5VuK3_(this.f10924a, this, pm0, z, i);
        return pm0;
    }
}
