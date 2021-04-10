package defpackage;

/* renamed from: IV  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class IV {

    /* renamed from: a  reason: collision with root package name */
    public static AbstractC3270jp0 f8228a;

    public static AbstractC3270jp0 a() {
        if (f8228a == null) {
            if (!AbstractC1575Zv.e().g("force-enable-night-mode")) {
                if (O51.f8599a == null) {
                    O51.f8599a = new O51();
                }
                O51 o51 = O51.f8599a;
                if (RE0.f8820a == null) {
                    RE0.f8820a = new RE0();
                }
                f8228a = new FV(o51, RE0.f8820a, NU0.f8549a);
            } else {
                f8228a = new HV(null);
            }
        }
        return f8228a;
    }
}
