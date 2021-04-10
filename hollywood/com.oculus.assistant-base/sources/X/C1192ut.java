package X;

import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

/* renamed from: X.ut  reason: case insensitive filesystem */
public final class C1192ut {
    public static void A00(ListenableFuture listenableFuture, AbstractC0383Uv uv, Executor executor) {
        if (uv != null) {
            listenableFuture.addListener(new RunnableC0384Uw(listenableFuture, uv), executor);
            return;
        }
        throw null;
    }
}
