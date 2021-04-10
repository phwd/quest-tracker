package X;

import android.app.Application;
import android.content.Context;
import android.os.Looper;

/* renamed from: X.0p7  reason: invalid class name */
public class AnonymousClass0p7 implements AnonymousClass0JK<Context, AnonymousClass005> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:19:0x0012 */
    @Override // X.AnonymousClass0JK
    public final AnonymousClass005 A5D(Context context) {
        Context context2 = context;
        Context applicationContext = context2.getApplicationContext();
        boolean z = applicationContext instanceof Application;
        Object obj = applicationContext;
        if (z) {
            boolean z2 = applicationContext instanceof AnonymousClass0Q4;
            obj = applicationContext;
            if (!z2) {
                obj = applicationContext.getApplicationContext();
            }
        }
        while (obj instanceof AnonymousClass0Q4) {
            obj = ((AnonymousClass0Q4) obj).A3a();
        }
        if (obj instanceof AnonymousClass0Q3) {
            AnonymousClass0J2 A3X = ((AnonymousClass0Q3) obj).A3X();
            if (A3X != null) {
                return new AnonymousClass005(A3X, context2);
            }
            throw new IllegalStateException("Can NOT get FbInjector instance! Possible reasons: (1) This method was called in ContentProvider's onCreate. (2) This is a test, and you forgot to initialize the MockInjector. For example, using RobolectricTestUtil.initializeMockInjector().");
        }
        StringBuilder sb = new StringBuilder("Injector is not supported in process ");
        sb.append(C01160Kw.A00().A01);
        sb.append(". Current thread is: ");
        sb.append(Thread.currentThread());
        sb.append(" and current Looper is: ");
        sb.append(Looper.myLooper());
        throw new UnsupportedOperationException(sb.toString());
    }
}
