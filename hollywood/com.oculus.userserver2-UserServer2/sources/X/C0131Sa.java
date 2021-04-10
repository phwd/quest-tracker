package X;

import android.app.Application;
import android.content.Context;
import android.os.Looper;

/* renamed from: X.Sa  reason: case insensitive filesystem */
public class C0131Sa implements Ik<Context, AnonymousClass05> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.Ik
    public final AnonymousClass05 A2H(Context context) {
        Context context2 = context;
        Context applicationContext = context2.getApplicationContext();
        if (applicationContext instanceof Application) {
            applicationContext = applicationContext.getApplicationContext();
        }
        if (applicationContext instanceof Oe) {
            BZ A1h = ((Oe) applicationContext).A1h();
            if (A1h != null) {
                return new AnonymousClass05(A1h, context2);
            }
            throw new IllegalStateException("Can NOT get FbInjector instance! Possible reasons: (1) This method was called in ContentProvider's onCreate. (2) This is a test, and you forgot to initialize the MockInjector. For example, using RobolectricTestUtil.initializeMockInjector().");
        }
        StringBuilder sb = new StringBuilder("Injector is not supported in process ");
        sb.append(C0066Kf.A00().A01);
        sb.append(". Current thread is: ");
        sb.append(Thread.currentThread());
        sb.append(" and current Looper is: ");
        sb.append(Looper.myLooper());
        throw new UnsupportedOperationException(sb.toString());
    }
}
