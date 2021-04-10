package defpackage;

import android.os.SystemClock;
import java.util.Objects;
import org.chromium.chrome.browser.AppHooks;

/* renamed from: Z8  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Z8 implements AbstractC5058uG {

    /* renamed from: a  reason: collision with root package name */
    public final C1964c9 f9322a;
    public final AbstractC5228vG b;
    public final String c;

    public Z8(C1964c9 c9Var, AbstractC5228vG vGVar, String str) {
        this.f9322a = c9Var;
        this.b = vGVar;
        this.c = str;
    }

    @Override // defpackage.AbstractC1494Yl
    public void a(Object obj) {
        C1964c9 c9Var = this.f9322a;
        AbstractC5228vG vGVar = this.b;
        String str = this.c;
        Ex1 ex1 = (Ex1) obj;
        Objects.requireNonNull(c9Var);
        ((AbstractC2459f30) vGVar).close();
        boolean z = ex1 != null;
        C1793b9 b9Var = new C1793b9(null);
        b9Var.f9517a = SystemClock.elapsedRealtime();
        b9Var.b = z;
        c9Var.a().put(str, b9Var);
        if (ex1 != null) {
            if (Y8.f9254a == null) {
                Objects.requireNonNull(AppHooks.get());
                Y8.f9254a = new Y8();
            }
            Objects.requireNonNull(Y8.f9254a);
        }
    }
}
