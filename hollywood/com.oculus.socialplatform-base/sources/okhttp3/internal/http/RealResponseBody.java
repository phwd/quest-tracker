package okhttp3.internal.http;

import com.facebook.acra.util.HttpRequestMultipart;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public final class RealResponseBody extends ResponseBody {
    public final Headers headers;
    public final BufferedSource source;

    @Override // okhttp3.ResponseBody
    public long contentLength() {
        return HttpHeaders.contentLength(this.headers);
    }

    @Override // okhttp3.ResponseBody
    public MediaType contentType() {
        String str = Headers.get(this.headers.namesAndValues, HttpRequestMultipart.CONTENT_TYPE);
        if (str != null) {
            return MediaType.parse(str);
        }
        return null;
    }

    public RealResponseBody(Headers headers2, BufferedSource bufferedSource) {
        this.headers = headers2;
        this.source = bufferedSource;
    }

    @Override // okhttp3.ResponseBody
    public BufferedSource source() {
        return this.source;
    }
}
