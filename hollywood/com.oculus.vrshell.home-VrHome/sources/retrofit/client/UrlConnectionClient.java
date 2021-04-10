package retrofit.client;

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
    private static final int CHUNK_SIZE = 4096;

    @Override // retrofit.client.Client
    public Response execute(Request request) throws IOException {
        HttpURLConnection connection = openConnection(request);
        prepareRequest(connection, request);
        return readResponse(connection);
    }

    /* access modifiers changed from: protected */
    public HttpURLConnection openConnection(Request request) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(request.getUrl()).openConnection();
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(20000);
        return connection;
    }

    /* access modifiers changed from: package-private */
    public void prepareRequest(HttpURLConnection connection, Request request) throws IOException {
        connection.setRequestMethod(request.getMethod());
        connection.setDoInput(true);
        for (Header header : request.getHeaders()) {
            connection.addRequestProperty(header.getName(), header.getValue());
        }
        TypedOutput body = request.getBody();
        if (body != null) {
            connection.setDoOutput(true);
            connection.addRequestProperty("Content-Type", body.mimeType());
            long length = body.length();
            if (length != -1) {
                connection.setFixedLengthStreamingMode((int) length);
                connection.addRequestProperty("Content-Length", String.valueOf(length));
            } else {
                connection.setChunkedStreamingMode(4096);
            }
            body.writeTo(connection.getOutputStream());
        }
    }

    /* access modifiers changed from: package-private */
    public Response readResponse(HttpURLConnection connection) throws IOException {
        InputStream stream;
        int status = connection.getResponseCode();
        String reason = connection.getResponseMessage();
        if (reason == null) {
            reason = "";
        }
        List<Header> headers = new ArrayList<>();
        for (Map.Entry<String, List<String>> field : connection.getHeaderFields().entrySet()) {
            String name = field.getKey();
            for (String value : field.getValue()) {
                headers.add(new Header(name, value));
            }
        }
        String mimeType = connection.getContentType();
        int length = connection.getContentLength();
        if (status >= 400) {
            stream = connection.getErrorStream();
        } else {
            stream = connection.getInputStream();
        }
        return new Response(connection.getURL().toString(), status, reason, headers, new TypedInputStream(mimeType, (long) length, stream));
    }

    /* access modifiers changed from: private */
    public static class TypedInputStream implements TypedInput {
        private final long length;
        private final String mimeType;
        private final InputStream stream;

        private TypedInputStream(String mimeType2, long length2, InputStream stream2) {
            this.mimeType = mimeType2;
            this.length = length2;
            this.stream = stream2;
        }

        @Override // retrofit.mime.TypedInput
        public String mimeType() {
            return this.mimeType;
        }

        @Override // retrofit.mime.TypedInput
        public long length() {
            return this.length;
        }

        @Override // retrofit.mime.TypedInput
        public InputStream in() throws IOException {
            return this.stream;
        }
    }
}
