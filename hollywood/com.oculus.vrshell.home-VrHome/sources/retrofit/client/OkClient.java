package retrofit.client;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okio.BufferedSink;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class OkClient implements Client {
    private final OkHttpClient client;

    private static OkHttpClient generateDefaultOkHttp() {
        OkHttpClient client2 = new OkHttpClient();
        client2.setConnectTimeout(15000, TimeUnit.MILLISECONDS);
        client2.setReadTimeout(20000, TimeUnit.MILLISECONDS);
        return client2;
    }

    public OkClient() {
        this(generateDefaultOkHttp());
    }

    public OkClient(OkHttpClient client2) {
        if (client2 == null) {
            throw new NullPointerException("client == null");
        }
        this.client = client2;
    }

    @Override // retrofit.client.Client
    public Response execute(Request request) throws IOException {
        return parseResponse(this.client.newCall(createRequest(request)).execute());
    }

    static Request createRequest(Request request) {
        Request.Builder builder = new Request.Builder().url(request.getUrl()).method(request.getMethod(), createRequestBody(request.getBody()));
        List<Header> headers = request.getHeaders();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            Header header = headers.get(i);
            String value = header.getValue();
            if (value == null) {
                value = "";
            }
            builder.addHeader(header.getName(), value);
        }
        return builder.build();
    }

    static Response parseResponse(Response response) {
        return new Response(response.request().urlString(), response.code(), response.message(), createHeaders(response.headers()), createResponseBody(response.body()));
    }

    private static RequestBody createRequestBody(final TypedOutput body) {
        if (body == null) {
            return null;
        }
        final MediaType mediaType = MediaType.parse(body.mimeType());
        return new RequestBody() {
            /* class retrofit.client.OkClient.AnonymousClass1 */

            @Override // com.squareup.okhttp.RequestBody
            public MediaType contentType() {
                return mediaType;
            }

            @Override // com.squareup.okhttp.RequestBody
            public void writeTo(BufferedSink sink) throws IOException {
                body.writeTo(sink.outputStream());
            }

            @Override // com.squareup.okhttp.RequestBody
            public long contentLength() {
                return body.length();
            }
        };
    }

    private static TypedInput createResponseBody(final ResponseBody body) {
        if (body.contentLength() == 0) {
            return null;
        }
        return new TypedInput() {
            /* class retrofit.client.OkClient.AnonymousClass2 */

            @Override // retrofit.mime.TypedInput
            public String mimeType() {
                MediaType mediaType = body.contentType();
                if (mediaType == null) {
                    return null;
                }
                return mediaType.toString();
            }

            @Override // retrofit.mime.TypedInput
            public long length() {
                return body.contentLength();
            }

            @Override // retrofit.mime.TypedInput
            public InputStream in() throws IOException {
                return body.byteStream();
            }
        };
    }

    private static List<Header> createHeaders(Headers headers) {
        int size = headers.size();
        List<Header> headerList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            headerList.add(new Header(headers.name(i), headers.value(i)));
        }
        return headerList;
    }
}
