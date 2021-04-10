package X;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.oculus.common.build.BuildConfig;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Beta
@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD)
public final class gM {
    @CanIgnoreReturnValue
    public static <V> V A00(Future<V> future) throws ExecutionException {
        V v;
        boolean z = false;
        while (true) {
            try {
                v = future.get();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return v;
    }
}
