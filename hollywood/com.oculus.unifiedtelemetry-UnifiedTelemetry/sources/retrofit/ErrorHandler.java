package retrofit;

public interface ErrorHandler {
    public static final ErrorHandler DEFAULT = new ErrorHandler() {
        /* class retrofit.ErrorHandler.AnonymousClass1 */

        @Override // retrofit.ErrorHandler
        public Throwable handleError(RetrofitError retrofitError) {
            return retrofitError;
        }
    };

    Throwable handleError(RetrofitError retrofitError);
}
