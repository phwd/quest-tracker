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

    public Response(String str, int i, String str2, List<Header> list, TypedInput typedInput) {
        if (str == null) {
            throw new IllegalArgumentException("url == null");
        } else if (i < 200) {
            throw new IllegalArgumentException("Invalid status code: " + i);
        } else if (str2 == null) {
            throw new IllegalArgumentException("reason == null");
        } else if (list != null) {
            this.url = str;
            this.status = i;
            this.reason = str2;
            this.headers = Collections.unmodifiableList(new ArrayList(list));
            this.body = typedInput;
        } else {
            throw new IllegalArgumentException("headers == null");
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
