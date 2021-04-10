package defpackage;

import com.oculus.browser.R;

/* renamed from: OM  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class OM implements Runnable {
    public final SM F;
    public final int G;
    public final String H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final int f8621J;

    public OM(SM sm, int i, String str, int i2, int i3) {
        this.F = sm;
        this.G = i;
        this.H = str;
        this.I = i2;
        this.f8621J = i3;
    }

    public void run() {
        SM sm = this.F;
        int i = this.G;
        String str = this.H;
        int i2 = this.I;
        KM km = new KM();
        sm.e = km;
        km.f8361a = i2;
        km.b = sm.g.a().getResources().getString(i);
        if (str != null) {
            sm.e.c = sm.g.a().getResources().getString(R.string.f57990_resource_name_obfuscated_RES_2131953116, str);
        }
        if (sm.f == null) {
            sm.c();
        }
    }
}
