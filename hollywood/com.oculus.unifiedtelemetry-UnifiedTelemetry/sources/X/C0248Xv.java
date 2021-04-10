package X;

import android.app.Application;
import android.content.Context;
import android.os.Looper;

/* renamed from: X.Xv  reason: case insensitive filesystem */
public class C0248Xv implements IO<Context, AnonymousClass05> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.IO
    public final AnonymousClass05 A3N(Context context) {
        Context context2 = context;
        Context applicationContext = context2.getApplicationContext();
        if (applicationContext instanceof Application) {
            applicationContext = applicationContext.getApplicationContext();
        }
        if (applicationContext instanceof Q4) {
            AbstractC0096Hu A2U = ((Q4) applicationContext).A2U();
            if (A2U != null) {
                return new AnonymousClass05(A2U, context2);
            }
            throw new IllegalStateException("Can NOT get FbInjector instance! Possible reasons: (1) This method was called in ContentProvider's onCreate. (2) This is a test, and you forgot to initialize the MockInjector. For example, using RobolectricTestUtil.initializeMockInjector().");
        }
        StringBuilder sb = new StringBuilder("Injector is not supported in process ");
        sb.append(KO.A00().A01);
        sb.append(". Current thread is: ");
        sb.append(Thread.currentThread());
        sb.append(" and current Looper is: ");
        sb.append(Looper.myLooper());
        throw new UnsupportedOperationException(sb.toString());
    }
}
