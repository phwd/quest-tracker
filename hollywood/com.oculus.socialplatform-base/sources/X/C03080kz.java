package X;

import java.util.Arrays;

/* renamed from: X.0kz  reason: invalid class name and case insensitive filesystem */
public final class C03080kz extends UnsatisfiedLinkError {
    public C03080kz(Throwable th, String str) {
        super(AnonymousClass006.A0B("APK was built for a different platform. Supported ABIs: ", Arrays.toString(AnonymousClass0l3.A01()), " error: ", str));
        initCause(th);
    }
}
