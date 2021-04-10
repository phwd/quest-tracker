package X;

import androidx.annotation.AnyThread;
import java.util.concurrent.Future;

/* renamed from: X.1ZX  reason: invalid class name */
public interface AnonymousClass1ZX<T> extends Future<T> {
    @AnyThread
    AnonymousClass1ZX<T> AA9(AnonymousClass1YZ<T> v);

    @AnyThread
    void cancel();
}
