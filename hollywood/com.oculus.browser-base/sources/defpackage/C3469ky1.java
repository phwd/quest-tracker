package defpackage;

import J.N;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;

/* renamed from: ky1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3469ky1 implements Serializable {
    public final C3640ly1 F;
    public final C3640ly1 G;
    public Map H = new HashMap();
    public Map I = new HashMap();

    /* renamed from: J  reason: collision with root package name */
    public V90 f10318J;
    public final List K = new ArrayList();
    public final List L = new ArrayList();

    public C3469ky1(C3640ly1 ly1, C3640ly1 ly12) {
        this.F = ly1;
        this.G = ly12;
    }

    public void a(BrowserContextHandle browserContextHandle, AbstractC3298jy1 jy1) {
        int[] iArr = {this.K.size() + 1};
        C3127iy1 iy1 = new C3127iy1(iArr, jy1);
        V90 v90 = this.f10318J;
        if (v90 != null) {
            N.Mks53EZS(browserContextHandle, v90.F);
            N.M101q5hN(browserContextHandle, v90.F, iy1);
            this.f10318J = null;
        } else {
            int i = iArr[0] - 1;
            iArr[0] = i;
            if (i == 0) {
                jy1.a();
            }
        }
        for (I21 i21 : this.K) {
            N.MykycHKg(browserContextHandle, i21.F, i21.G, iy1);
        }
        this.K.clear();
    }

    public int b(C3469ky1 ky1) {
        if (this == ky1) {
            return 0;
        }
        int a2 = g().compareTo(ky1.g());
        if (a2 != 0) {
            return a2;
        }
        if (c() == null) {
            if (ky1.c() == null) {
                return 0;
            }
            return -1;
        } else if (ky1.c() == null) {
            return 1;
        } else {
            return c().compareTo(ky1.c());
        }
    }

    public final C3640ly1 c() {
        if (k()) {
            return null;
        }
        return this.G;
    }

    public List d() {
        return new ArrayList(this.L);
    }

    public Integer e(BrowserContextHandle browserContextHandle, int i) {
        if (((OB0) this.I.get(Integer.valueOf(i))) != null) {
            return ((OB0) this.I.get(Integer.valueOf(i))).a(browserContextHandle);
        }
        if (((C1032Qy) this.H.get(Integer.valueOf(i))) != null) {
            return ((C1032Qy) this.H.get(Integer.valueOf(i))).I;
        }
        return null;
    }

    public C1032Qy f(int i) {
        return (C1032Qy) this.H.get(Integer.valueOf(i));
    }

    public final C3640ly1 g() {
        if (k()) {
            return this.G;
        }
        return this.F;
    }

    public OB0 h(int i) {
        return (OB0) this.I.get(Integer.valueOf(i));
    }

    public String i() {
        return g().f();
    }

    public long j() {
        V90 v90 = this.f10318J;
        long j = 0;
        if (v90 != null) {
            j = 0 + v90.G;
        }
        for (I21 i21 : this.K) {
            j += i21.H;
        }
        return j;
    }

    public boolean k() {
        C3640ly1 ly1;
        return this.F.f().equals("*") && (ly1 = this.G) != null && !ly1.f().equals("*");
    }

    public void l(BrowserContextHandle browserContextHandle, int i, int i2) {
        if (((OB0) this.I.get(Integer.valueOf(i))) != null) {
            OB0 ob0 = (OB0) this.I.get(Integer.valueOf(i));
            N.MxocgGQD(browserContextHandle, ob0.I, ob0.H, ob0.b(), i2);
            return;
        }
        C1032Qy qy = (C1032Qy) this.H.get(Integer.valueOf(i));
        if (i == 26) {
            if (qy == null) {
                qy = new C1032Qy(26, this.F.d(), 2, "");
                this.H.put(Integer.valueOf(i), qy);
            }
        } else if (i == 2) {
            if (qy == null) {
                qy = new C1032Qy(2, this.F.H, Integer.valueOf(i2), "");
                this.H.put(Integer.valueOf(i), qy);
            }
            if (i2 == 2) {
                AbstractC3535lK0.a("JavascriptContentSetting.EnableBy.SiteSettings");
            } else {
                AbstractC3535lK0.a("JavascriptContentSetting.DisableBy.SiteSettings");
            }
        } else if (i == 31) {
            if (qy == null) {
                qy = new C1032Qy(31, this.F.H, Integer.valueOf(i2), "");
                this.H.put(Integer.valueOf(i), qy);
            }
            if (i2 == 2) {
                AbstractC3535lK0.a("SoundContentSetting.MuteBy.SiteSettings");
            } else {
                AbstractC3535lK0.a("SoundContentSetting.UnmuteBy.SiteSettings");
            }
        }
        if (qy != null) {
            int i3 = qy.F;
            String str = qy.G;
            String str2 = qy.H;
            if (i3 == 0 && !str2.equals("*")) {
                str2.isEmpty();
            }
            N.Mxr_KJ0u(browserContextHandle, i3, str, str2, i2);
        }
    }

    public void m(int i, C1032Qy qy) {
        this.H.put(Integer.valueOf(i), qy);
    }
}
