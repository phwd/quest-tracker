package X;

import java.util.Arrays;

/* renamed from: X.0cJ  reason: invalid class name and case insensitive filesystem */
public final class C03150cJ extends UnsatisfiedLinkError {
    public C03150cJ(Throwable th, String str) {
        super(AnonymousClass006.A08("APK was built for a different platform. Supported ABIs: ", Arrays.toString(C03190cO.A02()), " error: ", str));
        initCause(th);
    }
}
