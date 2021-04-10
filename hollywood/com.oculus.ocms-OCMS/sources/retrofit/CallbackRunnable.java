package retrofit;

import java.util.concurrent.Executor;

/* access modifiers changed from: package-private */
public abstract class CallbackRunnable<T> implements Runnable {
    private final Callback<T> callback;
    private final Executor callbackExecutor;
    private final ErrorHandler errorHandler;

    public abstract ResponseWrapper obtainResponse();

    CallbackRunnable(Callback<T> callback2, Executor executor, ErrorHandler errorHandler2) {
        this.callback = callback2;
        this.callbackExecutor = executor;
        this.errorHandler = errorHandler2;
    }

    public final void run() {
        try {
            final ResponseWrapper obtainResponse = obtainResponse();
            this.callbackExecutor.execute(new Runnable() {
                /* class retrofit.CallbackRunnable.AnonymousClass1 */

                /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: retrofit.Callback */
                /* JADX WARN: Multi-variable type inference failed */
                public void run() {
                    CallbackRunnable.this.callback.success(obtainResponse.responseBody, obtainResponse.response);
                }
            });
        } catch (RetrofitError e) {
            e = e;
            Throwable handleError = this.errorHandler.handleError(e);
            if (handleError != e) {
                e = RetrofitError.unexpectedError(e.getUrl(), handleError);
            }
            this.callbackExecutor.execute(new Runnable() {
                /* class retrofit.CallbackRunnable.AnonymousClass2 */

                public void run() {
                    CallbackRunnable.this.callback.failure(e);
                }
            });
        }
    }
}
