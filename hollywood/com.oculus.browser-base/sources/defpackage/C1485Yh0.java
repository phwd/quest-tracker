package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;

/* renamed from: Yh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1485Yh0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final String f9289a;

    public C1485Yh0(String str) {
        this.f9289a = str;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        String str = this.f9289a;
        Iterator it = ((ArrayList) obj).iterator();
        while (it.hasNext()) {
            LF lf = (LF) it.next();
            if (lf.e == 1 && str.contains(lf.b)) {
                C1546Zh0 zh0 = new C1546Zh0(str);
                Executor executor = AbstractC2032cb.f9616a;
                zh0.f();
                ((ExecutorC1463Ya) executor).execute(zh0.e);
                return;
            }
        }
    }
}
