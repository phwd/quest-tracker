package defpackage;

import org.chromium.base.Callback;

/* renamed from: fl0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2575fl0 {
    public Callback F;
    public UH0 G;

    public static void a(AbstractC2575fl0 fl0, UH0 uh0, Callback callback) {
        if (uh0 == null) {
            fl0.e(fl0.G);
            fl0.G = null;
            fl0.F = null;
            return;
        }
        fl0.G = uh0;
        fl0.F = callback;
        fl0.b(uh0);
    }

    public static String d(UH0 uh0) {
        String str = (String) uh0.g(AbstractC3258jl0.b);
        return str == null ? (String) uh0.g(AbstractC3258jl0.c) : str;
    }

    public abstract void b(UH0 uh0);

    public final void c(int i) {
        Callback callback = this.F;
        if (callback != null) {
            this.F = null;
            callback.onResult(Integer.valueOf(i));
        }
    }

    public abstract void e(UH0 uh0);
}
