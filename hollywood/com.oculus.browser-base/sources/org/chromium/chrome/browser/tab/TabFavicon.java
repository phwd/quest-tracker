package org.chromium.chrome.browser.tab;

import J.N;
import android.graphics.Bitmap;
import com.oculus.browser.R;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TabFavicon extends AbstractC0499Id1 {
    public final TabImpl G;
    public final long H = N.MMZhE4x7(this);
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public Bitmap f10772J;
    public int K;
    public int L;
    public String M;

    public TabFavicon(Tab tab) {
        super(tab);
        TabImpl tabImpl = (TabImpl) tab;
        this.G = tabImpl;
        this.I = tabImpl.I.getResources().getDimensionPixelSize(R.dimen.f18040_resource_name_obfuscated_RES_2131165423);
    }

    public static TabFavicon j(Tab tab) {
        if (tab == null || !tab.isInitialized()) {
            return null;
        }
        return (TabFavicon) tab.M().c(TabFavicon.class);
    }

    public static Bitmap k(Tab tab) {
        Bitmap bitmap;
        String str;
        TabFavicon j = j(tab);
        if (j == null || j.G.isNativePage()) {
            return null;
        }
        TabImpl tabImpl = j.G;
        if (tabImpl.L == null) {
            return null;
        }
        if (j.f10772J == null || (str = j.M) == null || !str.equals(tabImpl.s())) {
            bitmap = (Bitmap) N.MmmF426r(j.H, j);
        } else {
            bitmap = j.f10772J;
        }
        return bitmap;
    }

    @Override // defpackage.AbstractC0499Id1
    public void c(WebContents webContents) {
        N.Mmp1Icg1(this.H, this);
    }

    @Override // defpackage.AbstractC0499Id1
    public void e() {
        N.M221q0MR(this.H, this);
    }

    @Override // defpackage.AbstractC0499Id1
    public void h(WebContents webContents) {
        N.MP93Z_9Y(this.H, this, webContents);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0044, code lost:
        if (r5 != false) goto L_0x0046;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onFaviconAvailable(android.graphics.Bitmap r9) {
        /*
        // Method dump skipped, instructions count: 117
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.tab.TabFavicon.onFaviconAvailable(android.graphics.Bitmap):void");
    }
}
