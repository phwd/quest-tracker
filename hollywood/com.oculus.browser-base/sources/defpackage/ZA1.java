package defpackage;

import com.google.android.gms.common.api.Scope;

/* renamed from: ZA1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class ZA1 {

    /* renamed from: a  reason: collision with root package name */
    public static final C2299e7 f9325a;
    public static final C2299e7 b;
    public static final Y6 c;
    public static final Y6 d;
    public static final C2470f7 e;

    static {
        C2299e7 e7Var = new C2299e7();
        f9325a = e7Var;
        C2299e7 e7Var2 = new C2299e7();
        b = e7Var2;
        BA1 ba1 = new BA1();
        c = ba1;
        IB1 ib1 = new IB1();
        d = ib1;
        new Scope("profile");
        new Scope("email");
        e = new C2470f7("SignIn.API", ba1, e7Var);
        SE0.i(ib1, "Cannot construct an Api with a null ClientBuilder");
        SE0.i(e7Var2, "Cannot construct an Api with a null ClientKey");
    }
}
