package retrofit.client;

import com.facebook.acra.util.HttpRequestMultipart;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

public class UrlConnectionClient implements Client {
    public static final int CHUNK_SIZE = 4096;

    public HttpURLConnection openConnection(Request request) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(request.url).openConnection();
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(Defaults.READ_TIMEOUT_MILLIS);
        return httpURLConnection;
    }

    public void prepareRequest(HttpURLConnection httpURLConnection, Request request) throws IOException {
        httpURLConnection.setRequestMethod(request.method);
        httpURLConnection.setDoInput(true);
        for (Header header : request.headers) {
            httpURLConnection.addRequestProperty(header.name, header.value);
        }
        TypedOutput typedOutput = request.body;
        if (typedOutput != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty(HttpRequestMultipart.CONTENT_TYPE, typedOutput.mimeType());
            long length = typedOutput.length();
            if (length != -1) {
                httpURLConnection.setFixedLengthStreamingMode((int) length);
                httpURLConnection.addRequestProperty("Content-Length", String.valueOf(length));
            } else {
                httpURLConnection.setChunkedStreamingMode(4096);
            }
            typedOutput.writeTo(httpURLConnection.getOutputStream());
        }
    }

    public static class TypedInputStream implements TypedInput {
        public final long length;
        public final String mimeType;
        public final InputStream stream;

        @Override // retrofit.mime.TypedInput
        public InputStream in() throws IOException {
            return this.stream;
        }

        @Override // retrofit.mime.TypedInput
        public long length() {
            return this.length;
        }

        @Override // retrofit.mime.TypedInput
        public String mimeType() {
            return this.mimeType;
        }

        public TypedInputStream(String str, long j, InputStream inputStream) {
            this.mimeType = str;
            this.length = j;
            this.stream = inputStream;
        }
    }

    @Override // retrofit.client.Client
    public Response execute(Request request) throws IOException {
        HttpURLConnection openConnection = openConnection(request);
        prepareRequest(openConnection, request);
        return readResponse(openConnection);
    }

    public Response readResponse(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream;
        int responseCode = httpURLConnection.getResponseCode();
        String responseMessage = httpURLConnection.getResponseMessage();
        if (responseMessage == null) {
            responseMessage = "";
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, List<String>> entry : httpURLConnection.getHeaderFields().entrySet()) {
            String key = entry.getKey();
            for (String str : entry.getValue()) {
                arrayList.add(new Header(key, str));
            }
        }
        String contentType = httpURLConnection.getContentType();
        int contentLength = httpURLConnection.getContentLength();
        if (responseCode >= 400) {
            inputStream = httpURLConnection.getErrorStream();
        } else {
            inputStream = httpURLConnection.getInputStream();
        }
        return new Response(httpURLConnection.getURL().toString(), responseCode, responseMessage, arrayList, new TypedInputStream(contentType, (long) contentLength, inputStream));
    }
}
