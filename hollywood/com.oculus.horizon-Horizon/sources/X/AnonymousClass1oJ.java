package X;

import java.io.IOException;
import javax.annotation.Nullable;

/* renamed from: X.1oJ  reason: invalid class name */
public class AnonymousClass1oJ extends IOException {
    public AnonymousClass1oJ(String str, @Nullable Throwable th) {
        super(str);
        initCause(th);
    }
}
