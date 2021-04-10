package org.chromium.chrome.browser.accessibility;

import J.N;
import android.content.SharedPreferences;
import java.util.Iterator;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.accessibility.settings.TextScalePreference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FontSizePrefs {

    /* renamed from: a  reason: collision with root package name */
    public static FontSizePrefs f10604a;
    public final long b = N.MtOl9Oto(this);
    public final C1322Vq0 c = new C1322Vq0();

    public static FontSizePrefs b() {
        Object obj = ThreadUtils.f10596a;
        if (f10604a == null) {
            f10604a = new FontSizePrefs();
        }
        return f10604a;
    }

    public float a() {
        return N.MHphDsyg(this.b, this);
    }

    public final float c() {
        return ContextUtils.getApplicationContext().getResources().getConfiguration().fontScale;
    }

    public float d() {
        PU0 pu0 = NU0.f8549a;
        pu0.f8694a.a("user_font_scale_factor");
        P21 f0 = P21.f0();
        try {
            float f = AbstractC3983nz.f10523a.getFloat("user_font_scale_factor", 0.0f);
            f0.close();
            if (f == 0.0f) {
                float a2 = a();
                f = 1.0f;
                if (Math.abs(a2 - 1.0f) > 0.001f) {
                    f = AbstractC4089od0.b(a2 / c(), 0.5f, 2.0f);
                }
                pu0.f8694a.a("user_font_scale_factor");
                SharedPreferences.Editor edit = AbstractC3983nz.f10523a.edit();
                edit.putFloat("user_font_scale_factor", f);
                edit.apply();
            }
            return f;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public final void e(float f) {
        float a2 = a();
        N.Mr3oVxR_(this.b, this, f);
        if (a2 < 1.3f && f >= 1.3f && !N.MOnmBKet(this.b, this)) {
            f(true, false);
        } else if (a2 >= 1.3f && f < 1.3f && !NU0.f8549a.d("user_set_force_enable_zoom", false)) {
            f(false, false);
        }
    }

    public final void f(boolean z, boolean z2) {
        NU0.f8549a.m("user_set_force_enable_zoom", z2);
        N.MFeACHCG(this.b, this, z);
    }

    public final void onFontScaleFactorChanged(float f) {
        float d = d();
        Iterator it = this.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                TextScalePreference textScalePreference = ((J) uq0.next()).f8263a.G0;
                textScalePreference.u0 = f;
                textScalePreference.t0 = d;
                textScalePreference.a0();
            } else {
                return;
            }
        }
    }

    public final void onForceEnableZoomChanged(boolean z) {
        Iterator it = this.c.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((J) uq0.next()).f8263a.H0.a0(z);
            } else {
                return;
            }
        }
    }
}
