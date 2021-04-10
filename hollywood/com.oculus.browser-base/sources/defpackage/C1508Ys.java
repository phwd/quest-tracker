package defpackage;

import java.util.Arrays;
import org.chromium.base.Callback;

/* renamed from: Ys  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1508Ys {

    /* renamed from: a  reason: collision with root package name */
    public int f9302a;
    public int b;
    public String c;
    public Callback d;
    public Integer[] e = new Integer[0];
    public final Integer[] f;
    public final /* synthetic */ C1569Zs g;

    public C1508Ys(C1569Zs zs, Integer... numArr) {
        this.g = zs;
        this.f = numArr;
    }

    public C1386Ws a() {
        return new C1386Ws(CU0.a(AbstractC5544x8.a(this.g.f9379a, this.f9302a), this.g.f9379a.getResources().getString(this.b), new View$OnClickListenerC1447Xs(this), false), Arrays.asList(this.f), Arrays.asList(this.e), false);
    }
}
