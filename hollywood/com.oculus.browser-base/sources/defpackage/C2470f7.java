package defpackage;

/* renamed from: f7  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2470f7 {

    /* renamed from: a  reason: collision with root package name */
    public final Y6 f9899a;
    public final C2299e7 b;
    public final String c;

    public C2470f7(String str, Y6 y6, C2299e7 e7Var) {
        SE0.i(y6, "Cannot construct an Api with a null ClientBuilder");
        SE0.i(e7Var, "Cannot construct an Api with a null ClientKey");
        this.c = str;
        this.f9899a = y6;
        this.b = e7Var;
    }

    public final AbstractC1607a7 a() {
        C2299e7 e7Var = this.b;
        if (e7Var != null) {
            return e7Var;
        }
        throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
    }
}
