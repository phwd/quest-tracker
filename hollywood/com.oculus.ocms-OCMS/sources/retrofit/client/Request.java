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

    public Request(String str, String str2, List<Header> list, TypedOutput typedOutput) {
        if (str == null) {
            throw new NullPointerException("Method must not be null.");
        } else if (str2 != null) {
            this.method = str;
            this.url = str2;
            if (list == null) {
                this.headers = Collections.emptyList();
            } else {
                this.headers = Collections.unmodifiableList(new ArrayList(list));
            }
            this.body = typedOutput;
        } else {
            throw new NullPointerException("URL must not be null.");
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
