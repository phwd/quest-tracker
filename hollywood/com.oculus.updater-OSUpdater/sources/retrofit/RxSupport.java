package retrofit;

import java.util.concurrent.Executor;
import rx.Observable;

/* access modifiers changed from: package-private */
public final class RxSupport {
    private final ErrorHandler errorHandler;
    private final Executor executor;
    private final RequestInterceptor requestInterceptor;

    /* access modifiers changed from: package-private */
    public interface Invoker {
    }

    RxSupport(Executor executor2, ErrorHandler errorHandler2, RequestInterceptor requestInterceptor2) {
        this.executor = executor2;
        this.errorHandler = errorHandler2;
        this.requestInterceptor = requestInterceptor2;
    }

    /* access modifiers changed from: package-private */
    public Observable createRequestObservable(final Invoker invoker) {
        return Observable.create(new Observable.OnSubscribe<Object>() {
            /* class retrofit.RxSupport.AnonymousClass1 */
        });
    }
}
