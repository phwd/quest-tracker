package org.chromium.content.browser.input;

import J.N;
import android.content.Context;
import org.chromium.content.browser.webcontents.WebContentsImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.ViewAndroidDelegate;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TextSuggestionHost extends Vy1 implements BE0, Qr1 {
    public long F;
    public final WebContentsImpl G;
    public final Context H;
    public final ViewAndroidDelegate I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f10929J;
    public WindowAndroid K;
    public OY0 L;
    public C0569Jg1 M;

    public TextSuggestionHost(WebContents webContents) {
        WebContentsImpl webContentsImpl = (WebContentsImpl) webContents;
        this.G = webContentsImpl;
        this.H = webContentsImpl.u0();
        this.K = webContentsImpl.I();
        this.I = webContentsImpl.F();
        if (webContentsImpl != null) {
            EE0.c(webContentsImpl).F.add(this);
        }
        Zy1.t0(webContentsImpl).r0(this);
    }

    public static TextSuggestionHost create(WebContents webContents, long j) {
        TextSuggestionHost textSuggestionHost = (TextSuggestionHost) ((WebContentsImpl) webContents).v0(TextSuggestionHost.class, AbstractC0508Ig1.f8241a);
        textSuggestionHost.F = j;
        return textSuggestionHost;
    }

    @Override // defpackage.BE0
    public void b() {
        hidePopups();
    }

    @Override // defpackage.Qr1
    public void destroy() {
    }

    @Override // defpackage.XF, defpackage.WF
    public void h0(int i) {
        hidePopups();
    }

    public void hidePopups() {
        C0569Jg1 jg1 = this.M;
        if (jg1 != null && jg1.L.isShowing()) {
            this.M.L.dismiss();
            this.M = null;
        }
        OY0 oy0 = this.L;
        if (oy0 != null && oy0.L.isShowing()) {
            this.L.L.dismiss();
            this.L = null;
        }
    }

    @Override // defpackage.Wy1, defpackage.Vy1
    public void onAttachedToWindow() {
        this.f10929J = true;
    }

    @Override // defpackage.Wy1, defpackage.Vy1
    public void onDetachedFromWindow() {
        this.f10929J = false;
    }

    public final void onNativeDestroyed() {
        hidePopups();
        this.F = 0;
    }

    public void r0(boolean z) {
        if (!z) {
            N.MnvYa0QF(this.F, this);
        }
        this.L = null;
        this.M = null;
    }

    public final void showSpellCheckSuggestionMenu(double d, double d2, String str, String[] strArr) {
        if (!this.f10929J) {
            r0(false);
            return;
        }
        hidePopups();
        OY0 oy0 = new OY0(this.H, this, this.K, this.I.getContainerView());
        this.L = oy0;
        double d3 = d2 + ((double) this.G.M.k);
        oy0.W = (String[]) strArr.clone();
        oy0.P.setVisibility(0);
        oy0.e(d, d3, str);
    }

    public final void showTextSuggestionMenu(double d, double d2, String str, SuggestionInfo[] suggestionInfoArr) {
        if (!this.f10929J) {
            r0(false);
            return;
        }
        hidePopups();
        C0569Jg1 jg1 = new C0569Jg1(this.H, this, this.K, this.I.getContainerView());
        this.M = jg1;
        double d3 = d2 + ((double) this.G.M.k);
        jg1.W = (SuggestionInfo[]) suggestionInfoArr.clone();
        jg1.P.setVisibility(8);
        jg1.e(d, d3, str);
    }

    @Override // defpackage.Wy1, defpackage.Vy1
    public void x(WindowAndroid windowAndroid) {
        this.K = windowAndroid;
        OY0 oy0 = this.L;
        if (oy0 != null) {
            oy0.I = windowAndroid;
        }
        C0569Jg1 jg1 = this.M;
        if (jg1 != null) {
            jg1.I = windowAndroid;
        }
    }
}
