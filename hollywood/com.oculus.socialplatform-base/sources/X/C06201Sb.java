package X;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* renamed from: X.1Sb  reason: invalid class name and case insensitive filesystem */
public final class C06201Sb implements Interceptor {
    public final C06211Sc A00;

    @Override // okhttp3.Interceptor
    public final Response intercept(Interceptor.Chain chain) throws IOException {
        this.A00.A00.getAndIncrement();
        Request request = chain.request();
        synchronized (AnonymousClass1Kq.class) {
        }
        try {
            Response proceed = chain.proceed(request);
            synchronized (AnonymousClass1Kq.class) {
            }
            return proceed;
        } catch (IOException e) {
            synchronized (AnonymousClass1Kq.class) {
                throw e;
            }
        }
    }

    public C06201Sb() {
        C06211Sc r0;
        synchronized (C06211Sc.class) {
            r0 = C06211Sc.A01;
            if (r0 == null) {
                r0 = new C06211Sc();
                C06211Sc.A01 = r0;
            }
        }
        this.A00 = r0;
    }
}
