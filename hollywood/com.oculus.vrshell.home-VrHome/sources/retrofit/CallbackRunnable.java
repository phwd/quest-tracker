package retrofit;

import java.util.concurrent.Executor;

abstract class CallbackRunnable<T> implements Runnable {
    private final Callback<T> callback;
    private final Executor callbackExecutor;
    private final ErrorHandler errorHandler;

    public abstract ResponseWrapper obtainResponse();

    CallbackRunnable(Callback<T> callback2, Executor callbackExecutor2, ErrorHandler errorHandler2) {
        this.callback = callback2;
        this.callbackExecutor = callbackExecutor2;
        this.errorHandler = errorHandler2;
    }

    public final void run() {
        try {
            final ResponseWrapper wrapper = obtainResponse();
            this.callbackExecutor.execute(new Runnable() {
                /* class retrofit.CallbackRunnable.AnonymousClass1 */

                /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: retrofit.Callback */
                /* JADX WARN: Multi-variable type inference failed */
                public void run() {
                    CallbackRunnable.this.callback.success(wrapper.responseBody, wrapper.response);
                }
            });
        } catch (RetrofitError e) {
            Throwable cause = this.errorHandler.handleError(e);
            final RetrofitError handled = cause == e ? e : RetrofitError.unexpectedError(e.getUrl(), cause);
            this.callbackExecutor.execute(new Runnable() {
                /* class retrofit.CallbackRunnable.AnonymousClass2 */

                public void run() {
                    CallbackRunnable.this.callback.failure(handled);
                }
            });
        }
    }
}
