package defpackage;

import org.chromium.base.ContextUtils;
import org.chromium.base.SysUtils;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;
import org.chromium.ui.base.DeviceFormFactor;

/* renamed from: uE  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5052uE {

    /* renamed from: a  reason: collision with root package name */
    public static C5052uE f11399a;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean e;
    public boolean f;
    public final boolean g;

    public C5052uE() {
        if (SysUtils.isLowEndDevice()) {
            this.b = true;
            this.c = true;
            this.d = false;
            this.e = false;
            this.f = false;
        } else {
            this.b = true;
            this.c = false;
            this.d = true;
            this.e = true;
            this.f = true;
        }
        if (DeviceFormFactor.isTablet()) {
            this.c = false;
        }
        AbstractC1575Zv e2 = AbstractC1575Zv.e();
        this.c |= e2.g("enable-accessibility-tab-switcher");
        this.g = !e2.g("disable-fullscreen");
        if (this.c) {
            this.d = false;
        }
    }

    public static boolean a() {
        if ((!DeviceFormFactor.a(ContextUtils.getApplicationContext())) && CachedFeatureFlags.isEnabled("TabGroupsContinuationAndroid") && CachedFeatureFlags.isEnabled("TabGroupsAndroid")) {
            return false;
        }
        if (c().c) {
            return true;
        }
        if (!C0283Ep.h().d()) {
            return false;
        }
        return NU0.f8549a.d("accessibility_tab_switcher", true);
    }

    public static boolean b() {
        if (!c().d) {
            return false;
        }
        if (!C0283Ep.h().d()) {
            return true;
        }
        return !NU0.f8549a.d("accessibility_tab_switcher", true);
    }

    public static C5052uE c() {
        if (f11399a == null) {
            f11399a = new C5052uE();
        }
        return f11399a;
    }
}
