package X;

import android.app.Application;
import android.content.Context;
import android.os.Looper;

/* renamed from: X.0lh  reason: invalid class name and case insensitive filesystem */
public class C03190lh implements AnonymousClass0HN<Context, AnonymousClass00A> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass0HN
    public final AnonymousClass00A A6G(Context context) {
        Context context2 = context;
        Context applicationContext = context2.getApplicationContext();
        if (applicationContext instanceof Application) {
            applicationContext = applicationContext.getApplicationContext();
        }
        if (applicationContext instanceof AnonymousClass0R6) {
            AnonymousClass0VF injector = ((AnonymousClass0R6) applicationContext).getInjector();
            if (injector != null) {
                return new AnonymousClass00A(injector, context2);
            }
            throw new IllegalStateException("Can NOT get FbInjector instance! Possible reasons: (1) This method was called in ContentProvider's onCreate. (2) This is a test, and you forgot to initialize the MockInjector. For example, using RobolectricTestUtil.initializeMockInjector().");
        }
        StringBuilder sb = new StringBuilder("Injector is not supported in process ");
        sb.append(AnonymousClass0Jl.A00().A01);
        sb.append(". Current thread is: ");
        sb.append(Thread.currentThread());
        sb.append(" and current Looper is: ");
        sb.append(Looper.myLooper());
        throw new UnsupportedOperationException(sb.toString());
    }
}
