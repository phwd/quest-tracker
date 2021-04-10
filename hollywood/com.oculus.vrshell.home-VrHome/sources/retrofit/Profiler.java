package retrofit;

public interface Profiler<T> {
    void afterCall(RequestInformation requestInformation, long j, int i, T t);

    T beforeCall();

    public static final class RequestInformation {
        private final String baseUrl;
        private final long contentLength;
        private final String contentType;
        private final String method;
        private final String relativePath;

        public RequestInformation(String method2, String baseUrl2, String relativePath2, long contentLength2, String contentType2) {
            this.method = method2;
            this.baseUrl = baseUrl2;
            this.relativePath = relativePath2;
            this.contentLength = contentLength2;
            this.contentType = contentType2;
        }

        public String getMethod() {
            return this.method;
        }

        public String getBaseUrl() {
            return this.baseUrl;
        }

        public String getRelativePath() {
            return this.relativePath;
        }

        public long getContentLength() {
            return this.contentLength;
        }

        public String getContentType() {
            return this.contentType;
        }
    }
}
