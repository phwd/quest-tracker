package retrofit.client;

import com.squareup.okhttp.Call;
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
import libraries.marauder.analytics.DefaultAnalyticsLogger;
import okio.BufferedSink;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class OkClient implements Client {
    public final OkHttpClient client;

    public static List<Header> createHeaders(Headers headers) {
        int length = headers.namesAndValues.length >> 1;
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            arrayList.add(new Header(headers.name(i), headers.value(i)));
        }
        return arrayList;
    }

    public static Request createRequest(Request request) {
        Request.Builder builder = new Request.Builder();
        builder.url(request.url);
        builder.method(request.method, createRequestBody(request.body));
        List<Header> list = request.headers;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Header header = list.get(i);
            String str = header.value;
            if (str == null) {
                str = "";
            }
            builder.headers.add(header.name, str);
        }
        return builder.build();
    }

    public static RequestBody createRequestBody(final TypedOutput typedOutput) {
        if (typedOutput == null) {
            return null;
        }
        final MediaType parse = MediaType.parse(typedOutput.mimeType());
        return new RequestBody() {
            /* class retrofit.client.OkClient.AnonymousClass1 */

            @Override // com.squareup.okhttp.RequestBody
            public long contentLength() {
                return typedOutput.length();
            }

            @Override // com.squareup.okhttp.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                typedOutput.writeTo(bufferedSink.outputStream());
            }

            @Override // com.squareup.okhttp.RequestBody
            public MediaType contentType() {
                return parse;
            }
        };
    }

    public static OkHttpClient generateDefaultOkHttp() {
        OkHttpClient okHttpClient = new OkHttpClient();
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        okHttpClient.setConnectTimeout(DefaultAnalyticsLogger.STORE_BATCH_DELAY_MS, timeUnit);
        okHttpClient.setReadTimeout(20000, timeUnit);
        return okHttpClient;
    }

    public static Response parseResponse(Response response) {
        return new Response(response.request.url.toString(), response.code, response.message, createHeaders(response.headers), createResponseBody(response.body));
    }

    @Override // retrofit.client.Client
    public Response execute(Request request) throws IOException {
        return parseResponse(new Call(this.client, createRequest(request)).execute());
    }

    public static TypedInput createResponseBody(final ResponseBody responseBody) {
        if (responseBody.contentLength() == 0) {
            return null;
        }
        return new TypedInput() {
            /* class retrofit.client.OkClient.AnonymousClass2 */

            @Override // retrofit.mime.TypedInput
            public InputStream in() throws IOException {
                return responseBody.byteStream();
            }

            @Override // retrofit.mime.TypedInput
            public long length() {
                return responseBody.contentLength();
            }

            @Override // retrofit.mime.TypedInput
            public String mimeType() {
                MediaType contentType = responseBody.contentType();
                if (contentType == null) {
                    return null;
                }
                return contentType.toString();
            }
        };
    }

    public OkClient() {
        this(generateDefaultOkHttp());
    }

    public OkClient(OkHttpClient okHttpClient) {
        if (okHttpClient != null) {
            this.client = okHttpClient;
            return;
        }
        throw new NullPointerException("client == null");
    }
}
