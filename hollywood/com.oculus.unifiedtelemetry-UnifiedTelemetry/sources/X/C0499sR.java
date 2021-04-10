package X;

import java.util.Arrays;

/* renamed from: X.sR  reason: case insensitive filesystem */
public final class C0499sR extends UnsatisfiedLinkError {
    public C0499sR(Throwable th, String str) {
        super(AnonymousClass06.A06("APK was built for a different platform. Supported ABIs: ", Arrays.toString(s7.A01()), " error: ", str));
        initCause(th);
    }
}
