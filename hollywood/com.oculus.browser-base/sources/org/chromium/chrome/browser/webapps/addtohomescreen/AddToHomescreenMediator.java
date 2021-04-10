package org.chromium.chrome.browser.webapps.addtohomescreen;

import J.N;
import android.graphics.Bitmap;
import android.util.Pair;
import java.util.Objects;
import org.chromium.chrome.browser.banners.AppData;
import org.chromium.components.webapps.WebappsIconUtils;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AddToHomescreenMediator implements A3 {
    public long F = N.MnlHINDO(this);
    public UH0 G;
    public WindowAndroid H;
    public AppData I;

    public AddToHomescreenMediator(UH0 uh0, WindowAndroid windowAndroid) {
        this.G = uh0;
        this.H = windowAndroid;
    }

    @Override // defpackage.A3
    public boolean f() {
        if (this.G.f(AbstractC5869z3.f) != 0) {
            return false;
        }
        WindowAndroid windowAndroid = this.H;
        Objects.requireNonNull(this.I);
        windowAndroid.E0(null, null, null);
        long j = this.F;
        if (j == 0) {
            return true;
        }
        N.MekzKFPG(j);
        return true;
    }

    @Override // defpackage.A3
    public void g(String str) {
        long j = this.F;
        if (j != 0) {
            N.MUktpePd(j, str);
            long j2 = this.F;
            if (j2 != 0) {
                N.MUkrhyF_(j2);
                this.F = 0;
            }
        }
    }

    @Override // defpackage.A3
    public void i() {
        long j = this.F;
        if (j != 0) {
            N.MUUypVwe(j);
            long j2 = this.F;
            if (j2 != 0) {
                N.MUkrhyF_(j2);
                this.F = 0;
            }
        }
    }

    public void setIcon(Bitmap bitmap, boolean z, boolean z2) {
        if (z2) {
            bitmap = WebappsIconUtils.createHomeScreenIconFromWebIcon(bitmap, true);
        }
        this.G.m(AbstractC5869z3.e, new Pair(bitmap, Boolean.valueOf(z)));
        this.G.j(AbstractC5869z3.g, true);
    }

    public void setNativeAppInfo(AppData appData) {
        this.I = appData;
        UH0 uh0 = this.G;
        TH0 th0 = AbstractC5869z3.f11718a;
        Objects.requireNonNull(appData);
        uh0.m(th0, null);
        this.G.l(AbstractC5869z3.f, 0);
        this.G.k(AbstractC5869z3.j, 0.0f);
        this.G.j(AbstractC5869z3.g, true);
        this.G.m(AbstractC5869z3.i, null);
    }

    public void setWebAppInfo(String str, String str2, boolean z) {
        this.G.m(AbstractC5869z3.f11718a, str);
        this.G.m(AbstractC5869z3.b, str2);
        this.G.l(AbstractC5869z3.f, z ? 1 : 2);
    }
}
