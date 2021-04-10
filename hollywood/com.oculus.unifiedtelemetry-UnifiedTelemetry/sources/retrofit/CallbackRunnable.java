package retrofit;

import java.util.concurrent.Executor;

public abstract class CallbackRunnable<T> implements Runnable {
    public final Callback<T> callback;
    public final Executor callbackExecutor;
    public final ErrorHandler errorHandler;

    public abstract ResponseWrapper obtainResponse();

    public CallbackRunnable(Callback<T> callback2, Executor executor, ErrorHandler errorHandler2) {
        this.callback = callback2;
        this.callbackExecutor = executor;
        this.errorHandler = errorHandler2;
    }

    public final void run() {
        try {
            final ResponseWrapper obtainResponse = obtainResponse();
            this.callbackExecutor.execute(new Runnable() {
                /* class retrofit.CallbackRunnable.AnonymousClass1 */

                /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: retrofit.Callback<T> */
                /* JADX WARN: Multi-variable type inference failed */
                public void run() {
                    ResponseWrapper responseWrapper = obtainResponse;
                    CallbackRunnable.this.callback.success(responseWrapper.responseBody, responseWrapper.response);
                }
            });
        } catch (RetrofitError e) {
            e = e;
            Throwable handleError = this.errorHandler.handleError(e);
            if (handleError != e) {
                e = RetrofitError.unexpectedError(e.url, handleError);
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
