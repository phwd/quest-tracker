package defpackage;

import android.text.TextUtils;
import java.util.Objects;
import java.util.concurrent.Executor;
import org.chromium.components.signin.AccountManagerFacadeProvider;

/* renamed from: n1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3819n1 implements Runnable {
    public final C4673s1 F;

    public RunnableC3819n1(C4673s1 s1Var) {
        this.F = s1Var;
    }

    public void run() {
        C4673s1 s1Var = this.F;
        UH0 uh0 = s1Var.H;
        SH0 sh0 = AbstractC5183v1.e;
        int f = uh0.f(sh0);
        if (f == 1 || f == 5) {
            s1Var.H.l(sh0, 3);
            AbstractC3364kK0.g("Signin.AccountConsistencyPromoAction.SignedIn.Count", C4072oW0.f10556a.b.f("Chrome.AccountPickerBottomSheet.ShownCount", 0), 100);
            if (TextUtils.equals(s1Var.L, s1Var.N)) {
                AbstractC3901nW0.a(8);
            } else if (TextUtils.equals(s1Var.L, s1Var.M)) {
                AbstractC3901nW0.a(4);
            } else {
                AbstractC3901nW0.a(5);
            }
            C4502r1 r1Var = new C4502r1(s1Var);
            Executor executor = AbstractC2032cb.f9616a;
            r1Var.f();
            ((ExecutorC1463Ya) executor).execute(r1Var.e);
        } else if (f == 0) {
            s1Var.h();
        } else if (f == 6) {
            AbstractC5863z1 z1Var = s1Var.F;
            String str = s1Var.L;
            C4161p1 p1Var = new C4161p1(s1Var);
            D1 d1 = (D1) z1Var;
            Objects.requireNonNull(d1);
            AccountManagerFacadeProvider.getInstance().b(V1.b(str), d1.b, p1Var);
        }
    }
}
