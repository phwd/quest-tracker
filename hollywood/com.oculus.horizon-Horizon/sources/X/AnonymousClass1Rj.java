package X;

import java.util.concurrent.atomic.AtomicReference;
import org.apache.http.client.HttpResponseException;

/* renamed from: X.1Rj  reason: invalid class name */
public abstract class AnonymousClass1Rj {
    public final void A00(Exception exc, boolean z) {
        AtomicReference<AnonymousClass1kE> atomicReference;
        AnonymousClass1kE r4;
        long A00;
        AnonymousClass1Rk r10;
        String str;
        if (!(this instanceof AnonymousClass1kF)) {
            AnonymousClass1kG r0 = (AnonymousClass1kG) this;
            atomicReference = r0.A01;
            r4 = atomicReference.get();
            if (r4 != null) {
                AnonymousClass1kS r2 = r0.A00.A02;
                if ((exc instanceof HttpResponseException) && ((HttpResponseException) exc).getStatusCode() == 418) {
                    r2.A00++;
                }
                A00 = AnonymousClass1kE.A00(exc);
                r10 = AnonymousClass1Rk.GET;
                str = "Failed GET request for fetching offset";
            }
            atomicReference.set(null);
        }
        AnonymousClass1kF r02 = (AnonymousClass1kF) this;
        atomicReference = r02.A02;
        r4 = atomicReference.get();
        if (r4 != null) {
            AnonymousClass1kS r22 = r02.A00.A02;
            if ((exc instanceof HttpResponseException) && ((HttpResponseException) exc).getStatusCode() == 418) {
                r22.A00++;
            }
            A00 = AnonymousClass1kE.A00(exc);
            r10 = AnonymousClass1Rk.POST;
            str = "Failed to complete POST request";
        }
        atomicReference.set(null);
        AnonymousClass1kE.A02(r4, A00, str, exc, z, r10);
        atomicReference.set(null);
    }
}
