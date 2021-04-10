package defpackage;

/* renamed from: Cj1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Cj1 implements Runnable {
    public final Oj1 F;
    public final Integer G;

    public Cj1(Oj1 oj1, Integer num) {
        this.F = oj1;
        this.G = num;
    }

    public void run() {
        Oj1 oj1 = this.F;
        Integer num = this.G;
        AbstractC5717y9 y9Var = oj1.N;
        if (y9Var != null) {
            ((C5887z9) y9Var).i(num);
        }
    }
}
