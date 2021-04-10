package retrofit.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import retrofit.mime.TypedOutput;

public final class Request {
    public final TypedOutput body;
    public final List<Header> headers;
    public final String method;
    public final String url;

    public Request(String str, String str2, List<Header> list, TypedOutput typedOutput) {
        List<Header> unmodifiableList;
        if (str == null) {
            throw new NullPointerException("Method must not be null.");
        } else if (str2 != null) {
            this.method = str;
            this.url = str2;
            if (list == null) {
                unmodifiableList = Collections.emptyList();
            } else {
                unmodifiableList = Collections.unmodifiableList(new ArrayList(list));
            }
            this.headers = unmodifiableList;
            this.body = typedOutput;
        } else {
            throw new NullPointerException("URL must not be null.");
        }
    }

    public TypedOutput getBody() {
        return this.body;
    }

    public List<Header> getHeaders() {
        return this.headers;
    }

    public String getMethod() {
        return this.method;
    }

    public String getUrl() {
        return this.url;
    }
}
