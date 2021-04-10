package retrofit.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import retrofit.mime.TypedOutput;

public final class Request {
    private final TypedOutput body;
    private final List<Header> headers;
    private final String method;
    private final String url;

    public Request(String method2, String url2, List<Header> headers2, TypedOutput body2) {
        if (method2 == null) {
            throw new NullPointerException("Method must not be null.");
        } else if (url2 == null) {
            throw new NullPointerException("URL must not be null.");
        } else {
            this.method = method2;
            this.url = url2;
            if (headers2 == null) {
                this.headers = Collections.emptyList();
            } else {
                this.headers = Collections.unmodifiableList(new ArrayList(headers2));
            }
            this.body = body2;
        }
    }

    public String getMethod() {
        return this.method;
    }

    public String getUrl() {
        return this.url;
    }

    public List<Header> getHeaders() {
        return this.headers;
    }

    public TypedOutput getBody() {
        return this.body;
    }
}
