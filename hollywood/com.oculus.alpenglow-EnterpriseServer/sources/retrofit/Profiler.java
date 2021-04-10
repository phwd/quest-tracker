package retrofit;

public interface Profiler<T> {

    public static final class RequestInformation {
        public final String baseUrl;
        public final long contentLength;
        public final String contentType;
        public final String method;
        public final String relativePath;

        public String getBaseUrl() {
            return this.baseUrl;
        }

        public long getContentLength() {
            return this.contentLength;
        }

        public String getContentType() {
            return this.contentType;
        }

        public String getMethod() {
            return this.method;
        }

        public String getRelativePath() {
            return this.relativePath;
        }

        public RequestInformation(String str, String str2, String str3, long j, String str4) {
            this.method = str;
            this.baseUrl = str2;
            this.relativePath = str3;
            this.contentLength = j;
            this.contentType = str4;
        }
    }

    void afterCall(RequestInformation requestInformation, long j, int i, T t);

    T beforeCall();
}
