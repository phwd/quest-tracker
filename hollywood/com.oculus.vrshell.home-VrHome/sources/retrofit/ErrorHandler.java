package retrofit;

public interface ErrorHandler {
    public static final ErrorHandler DEFAULT = new ErrorHandler() {
        /* class retrofit.ErrorHandler.AnonymousClass1 */

        @Override // retrofit.ErrorHandler
        public Throwable handleError(RetrofitError cause) {
            return cause;
        }
    };

    Throwable handleError(RetrofitError retrofitError);
}
