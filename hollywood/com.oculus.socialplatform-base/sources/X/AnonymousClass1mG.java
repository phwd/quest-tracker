package X;

import java.io.IOException;
import javax.annotation.Nullable;

/* renamed from: X.1mG  reason: invalid class name */
public class AnonymousClass1mG extends IOException {
    public AnonymousClass1mG(String str, @Nullable Throwable th) {
        super(str);
        initCause(th);
    }
}
