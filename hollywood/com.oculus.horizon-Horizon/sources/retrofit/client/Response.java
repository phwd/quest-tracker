package retrofit.client;

import X.AnonymousClass006;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import retrofit.mime.TypedInput;

public final class Response {
    public final TypedInput body;
    public final List<Header> headers;
    public final String reason;
    public final int status;
    public final String url;

    public Response(String str, int i, String str2, List<Header> list, TypedInput typedInput) {
        String str3;
        if (str == null) {
            str3 = "url == null";
        } else if (i < 200) {
            str3 = AnonymousClass006.A01("Invalid status code: ", i);
        } else if (str2 == null) {
            str3 = "reason == null";
        } else if (list != null) {
            this.url = str;
            this.status = i;
            this.reason = str2;
            this.headers = Collections.unmodifiableList(new ArrayList(list));
            this.body = typedInput;
            return;
        } else {
            str3 = "headers == null";
        }
        throw new IllegalArgumentException(str3);
    }

    public TypedInput getBody() {
        return this.body;
    }

    public List<Header> getHeaders() {
        return this.headers;
    }

    public String getReason() {
        return this.reason;
    }

    public int getStatus() {
        return this.status;
    }

    public String getUrl() {
        return this.url;
    }
}
