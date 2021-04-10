package X;

import java.util.Arrays;

public final class KI extends UnsatisfiedLinkError {
    public KI(Throwable th, String str) {
        super(AnonymousClass08.A06("APK was built for a different platform. Supported ABIs: ", Arrays.toString(KM.A01()), " error: ", str));
        initCause(th);
    }
}
