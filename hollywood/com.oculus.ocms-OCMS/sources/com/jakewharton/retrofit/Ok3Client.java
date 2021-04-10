package com.jakewharton.retrofit;

import com.facebook.tigon.iface.TigonRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public final class Ok3Client implements Client {
    private static final byte[] NO_BODY = new byte[0];
    private final Call.Factory client;

    public Ok3Client() {
        this(new OkHttpClient());
    }

    public Ok3Client(OkHttpClient okHttpClient) {
        this((Call.Factory) okHttpClient);
    }

    public Ok3Client(Call.Factory factory) {
        if (factory != null) {
            this.client = factory;
            return;
        }
        throw new NullPointerException("client == null");
    }

    @Override // retrofit.client.Client
    public Response execute(Request request) throws IOException {
        return parseResponse(this.client.newCall(createRequest(request)).execute());
    }

    static okhttp3.Request createRequest(Request request) {
        RequestBody requestBody;
        if (!requiresRequestBody(request.getMethod()) || request.getBody() != null) {
            requestBody = createRequestBody(request.getBody());
        } else {
            requestBody = RequestBody.create((MediaType) null, NO_BODY);
        }
        Request.Builder method = new Request.Builder().url(request.getUrl()).method(request.getMethod(), requestBody);
        List<Header> headers = request.getHeaders();
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            Header header = headers.get(i);
            String value = header.getValue();
            if (value == null) {
                value = "";
            }
            method.addHeader(header.getName(), value);
        }
        return method.build();
    }

    static Response parseResponse(okhttp3.Response response) {
        return new Response(response.request().url().toString(), response.code(), response.message(), createHeaders(response.headers()), createResponseBody(response.body()));
    }

    private static RequestBody createRequestBody(final TypedOutput typedOutput) {
        if (typedOutput == null) {
            return null;
        }
        final MediaType parse = MediaType.parse(typedOutput.mimeType());
        return new RequestBody() {
            /* class com.jakewharton.retrofit.Ok3Client.AnonymousClass1 */

            @Override // okhttp3.RequestBody
            public MediaType contentType() {
                return MediaType.this;
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                typedOutput.writeTo(bufferedSink.outputStream());
            }

            @Override // okhttp3.RequestBody
            public long contentLength() {
                return typedOutput.length();
            }
        };
    }

    private static TypedInput createResponseBody(final ResponseBody responseBody) {
        if (responseBody.contentLength() == 0) {
            return null;
        }
        return new TypedInput() {
            /* class com.jakewharton.retrofit.Ok3Client.AnonymousClass2 */

            @Override // retrofit.mime.TypedInput
            public String mimeType() {
                MediaType contentType = ResponseBody.this.contentType();
                if (contentType == null) {
                    return null;
                }
                return contentType.toString();
            }

            @Override // retrofit.mime.TypedInput
            public long length() {
                return ResponseBody.this.contentLength();
            }

            @Override // retrofit.mime.TypedInput
            public InputStream in() throws IOException {
                return ResponseBody.this.byteStream();
            }
        };
    }

    private static List<Header> createHeaders(Headers headers) {
        int size = headers.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new Header(headers.name(i), headers.value(i)));
        }
        return arrayList;
    }

    private static boolean requiresRequestBody(String str) {
        return TigonRequest.POST.equals(str) || "PUT".equals(str) || "PATCH".equals(str) || "PROPPATCH".equals(str) || "REPORT".equals(str);
    }
}
