package X;

import android.app.Application;
import android.content.Context;
import android.os.Looper;

/* renamed from: X.0bK  reason: invalid class name and case insensitive filesystem */
public class C03000bK implements AnonymousClass0JF<Context, AnonymousClass00A> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // X.AnonymousClass0JF
    public final AnonymousClass00A A5e(Context context) {
        Context context2 = context;
        Context applicationContext = context2.getApplicationContext();
        if (applicationContext instanceof Application) {
            applicationContext = ((Application) applicationContext).getApplicationContext();
        }
        if (applicationContext instanceof AnonymousClass0Qz) {
            AnonymousClass0Lh A3k = ((AnonymousClass0Qz) applicationContext).A3k();
            if (A3k != null) {
                return new AnonymousClass00A(A3k, context2);
            }
            throw new IllegalStateException("Can NOT get FbInjector instance! Possible reasons: (1) This method was called in ContentProvider's onCreate. (2) This is a test, and you forgot to initialize the MockInjector. For example, using RobolectricTestUtil.initializeMockInjector().");
        }
        throw new UnsupportedOperationException("Injector is not supported in process " + AnonymousClass0L5.A00().A01 + ". Current thread is: " + Thread.currentThread() + " and current Looper is: " + Looper.myLooper());
    }
}
