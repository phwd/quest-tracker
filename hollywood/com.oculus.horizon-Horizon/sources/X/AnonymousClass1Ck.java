package X;

import com.facebook.internal.Validate;
import java.util.Collections;
import java.util.Set;

/* renamed from: X.1Ck  reason: invalid class name */
public final class AnonymousClass1Ck {
    public static final Set<String> A00 = Collections.unmodifiableSet(new AnonymousClass1Cj());
    public static volatile AnonymousClass1Ck A01;

    public static AnonymousClass1Ck A00() {
        if (A01 == null) {
            synchronized (AnonymousClass1Ck.class) {
                if (A01 == null) {
                    A01 = new AnonymousClass1Ck();
                }
            }
        }
        return A01;
    }

    public AnonymousClass1Ck() {
        Validate.sdkInitialized();
    }
}
