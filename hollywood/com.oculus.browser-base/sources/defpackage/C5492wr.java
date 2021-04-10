package defpackage;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Pair;
import java.util.Map;
import java.util.Objects;
import org.chromium.chrome.browser.contextmenu.ContextMenuNativeDelegateImpl;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.embedder_support.contextmenu.ContextMenuParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* renamed from: wr  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5492wr implements AbstractC3128iz {

    /* renamed from: a  reason: collision with root package name */
    public final Context f11573a;
    public final AbstractC2787gz b;
    public final int c;
    public final Q31 d;
    public final YM e;
    public final ContextMenuParams f;
    public final Z70 g;
    public boolean h;
    public boolean i;
    public C2759gp1 j;
    public ContextMenuNativeDelegateImpl k;
    public Boolean l;

    public C5492wr(AbstractC2787gz gzVar, Q31 q31, int i2, YM ym, Context context, ContextMenuParams contextMenuParams, ContextMenuNativeDelegateImpl contextMenuNativeDelegateImpl) {
        Z70 z70 = new Z70();
        z70.f9321a = true;
        z70.b = 0;
        this.g = z70;
        this.b = gzVar;
        this.d = q31;
        this.c = i2;
        this.e = ym;
        this.f11573a = context;
        this.f = contextMenuParams;
        this.k = contextMenuNativeDelegateImpl;
    }

    public static boolean k(GURL gurl) {
        return gurl == null || gurl.j() || gurl.h().equals("about:blank");
    }

    @Override // defpackage.AbstractC3128iz
    public boolean a() {
        return ((C4349q61) this.b).f11116a.H;
    }

    @Override // defpackage.AbstractC3128iz
    public void b() {
        Boolean bool = this.l;
        if (bool != null && bool.booleanValue()) {
            Tm1 a2 = Um1.a(Profile.b());
            if (a2.isInitialized()) {
                a2.dismissed("IPH_EphemeralTab");
            }
        }
        if (!this.i && !((C4349q61) this.b).f11116a.H) {
            Objects.requireNonNull(X70.a());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:128:0x0346  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0449  */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x0456  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0463  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x04ba  */
    @Override // defpackage.AbstractC3128iz
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List c() {
        /*
        // Method dump skipped, instructions count: 1497
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5492wr.c():java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:146:0x05a4, code lost:
        if (org.chromium.base.PackageManagerUtils.c(r8, 0).isEmpty() != false) goto L_0x0633;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x03ce, code lost:
        if (r1.equals("application/pdf") != false) goto L_0x03d2;
     */
    @Override // defpackage.AbstractC3128iz
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean d(int r21) {
        /*
        // Method dump skipped, instructions count: 1588
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5492wr.d(int):boolean");
    }

    @Override // defpackage.AbstractC3128iz
    public W70 e() {
        if (!AbstractC1611a80.a(a())) {
            return null;
        }
        String h2 = this.f.b.h();
        ContextMenuParams contextMenuParams = this.f;
        return new W70(h2, contextMenuParams.e, contextMenuParams.g.h(), f(), a(), ((C4349q61) this.b).f11116a.L, this.k, new RunnableC3959nr(this), new RunnableC4130or(this));
    }

    @Override // defpackage.AbstractC3128iz
    public String f() {
        return ((C4349q61) this.b).f11116a.getTitle();
    }

    public final C4765sb0 g(int i2) {
        return h(i2, false);
    }

    public final C4765sb0 h(int i2, boolean z) {
        Map c2 = UH0.c(YM0.c);
        SH0 sh0 = YM0.b;
        int i3 = AbstractC3104ir.f10167a[i2];
        JH0 jh0 = new JH0(null);
        jh0.f8282a = i3;
        c2.put(sh0, jh0);
        TH0 th0 = YM0.f9269a;
        CharSequence c3 = AbstractC3104ir.c(this.f11573a, i2, z);
        LH0 lh0 = new LH0(null);
        lh0.f8415a = c3;
        c2.put(th0, lh0);
        return new C4765sb0(2, new UH0(c2, null));
    }

    public final C4765sb0 i(int i2, int i3) {
        Intent intent;
        if (i2 == 10) {
            intent = IT0.b();
        } else {
            intent = LT0.f(null);
        }
        Pair g2 = LT0.g(intent);
        Map c2 = UH0.c(ZM0.h);
        SH0 sh0 = YM0.b;
        int[] iArr = AbstractC3104ir.f10167a;
        int i4 = iArr[i2];
        JH0 jh0 = new JH0(null);
        jh0.f8282a = i4;
        c2.put(sh0, jh0);
        TH0 th0 = YM0.f9269a;
        CharSequence c3 = AbstractC3104ir.c(this.f11573a, i2, false);
        LH0 lh0 = new LH0(null);
        lh0.f8415a = c3;
        c2.put(th0, lh0);
        TH0 th02 = ZM0.d;
        LH0 lh02 = new LH0(null);
        lh02.f8415a = (Drawable) g2.first;
        c2.put(th02, lh02);
        TH0 th03 = ZM0.e;
        LH0 lh03 = new LH0(null);
        lh03.f8415a = (CharSequence) g2.second;
        c2.put(th03, lh03);
        SH0 sh02 = ZM0.f;
        int i5 = iArr[i3];
        JH0 jh02 = new JH0(null);
        jh02.f8282a = i5;
        c2.put(sh02, jh02);
        return new C4765sb0(3, new UH0(c2, null));
    }

    public final WindowAndroid j() {
        return ((C4349q61) this.b).f11116a.L.I();
    }

    public final void l(String str, String str2) {
        if (AbstractC1611a80.f(((C4349q61) this.b).f11116a.H)) {
            if (this.j == null) {
                this.j = new C2759gp1();
            }
            WebContents webContents = ((C4349q61) this.b).f11116a.L;
            if (webContents != null) {
                Objects.requireNonNull(this.j);
                N.MX4lNgiF(webContents, str, str2);
            }
        }
    }

    public final void m(int i2) {
        String str;
        WebContents webContents = ((C4349q61) this.b).f11116a.L;
        ContextMenuParams contextMenuParams = this.f;
        if (contextMenuParams.k) {
            str = "ContextMenu.SelectedOptionAndroid.Video";
        } else if (contextMenuParams.j) {
            if (AbstractC1611a80.e(contextMenuParams.b)) {
                AbstractC3364kK0.g(contextMenuParams.i ? "ContextMenu.SelectedOptionAndroid.ImageLink.ShoppingDomain" : "ContextMenu.SelectedOptionAndroid.Image.ShoppingDomain", i2, 35);
            }
            str = contextMenuParams.i ? "ContextMenu.SelectedOptionAndroid.ImageLink" : "ContextMenu.SelectedOptionAndroid.Image";
        } else {
            str = "ContextMenu.SelectedOptionAndroid.Link";
        }
        AbstractC3364kK0.g(str, i2, 35);
        if (contextMenuParams.i && N.MO0TyD6h(webContents, contextMenuParams.c) == 2) {
            AbstractC3364kK0.g(str + ".PerformanceClassFast", i2, 35);
        }
        if (AbstractC1611a80.f(((C4349q61) this.b).f11116a.H)) {
            if (this.j == null) {
                this.j = new C2759gp1();
            }
            WebContents webContents2 = ((C4349q61) this.b).f11116a.L;
            if (webContents2 != null) {
                Objects.requireNonNull(this.j);
                N.M5aNQ$DO(webContents2, "ContextMenuAndroid.Selected", "Action", i2);
            }
        }
    }

    public void n(boolean z, Z70 z70) {
        this.i = true;
        this.k.a(1, new C3788mr(this, z70, z));
    }

    public boolean o() {
        Tm1 a2 = Um1.a(Profile.b());
        return a2.isInitialized() && a2.shouldTriggerHelpUI("IPH_EphemeralTab");
    }
}
