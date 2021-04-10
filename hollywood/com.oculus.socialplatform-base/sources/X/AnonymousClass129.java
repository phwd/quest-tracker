package X;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

@GwtIncompatible
/* renamed from: X.129  reason: invalid class name */
public interface AnonymousClass129 extends ExecutorService {
    <T> ListenableFuture<T> AAX(Callable<T> callable);
}
