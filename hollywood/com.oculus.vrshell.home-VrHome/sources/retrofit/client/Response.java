package retrofit.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import retrofit.mime.TypedInput;

public final class Response {
    private final TypedInput body;
    private final List<Header> headers;
    private final String reason;
    private final int status;
    private final String url;

    public Response(String url2, int status2, String reason2, List<Header> headers2, TypedInput body2) {
        if (url2 == null) {
            throw new IllegalArgumentException("url == null");
        } else if (status2 < 200) {
            throw new IllegalArgumentException("Invalid status code: " + status2);
        } else if (reason2 == null) {
            throw new IllegalArgumentException("reason == null");
        } else if (headers2 == null) {
            throw new IllegalArgumentException("headers == null");
        } else {
            this.url = url2;
            this.status = status2;
            this.reason = reason2;
            this.headers = Collections.unmodifiableList(new ArrayList(headers2));
            this.body = body2;
        }
    }

    public String getUrl() {
        return this.url;
    }

    public int getStatus() {
        return this.status;
    }

    public String getReason() {
        return this.reason;
    }

    public List<Header> getHeaders() {
        return this.headers;
    }

    public TypedInput getBody() {
        return this.body;
    }
}
