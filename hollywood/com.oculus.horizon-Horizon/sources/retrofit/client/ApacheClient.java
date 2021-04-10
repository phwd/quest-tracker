package retrofit.client;

import com.oculus.appmanager.downloader.OculusFileDownloader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedOutput;

public class ApacheClient implements Client {
    public final HttpClient client;

    public static class TypedOutputEntity extends AbstractHttpEntity {
        public final TypedOutput typedOutput;

        public boolean isRepeatable() {
            return true;
        }

        public boolean isStreaming() {
            return false;
        }

        public InputStream getContent() throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            this.typedOutput.writeTo(byteArrayOutputStream);
            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        }

        public long getContentLength() {
            return this.typedOutput.length();
        }

        public void writeTo(OutputStream outputStream) throws IOException {
            this.typedOutput.writeTo(outputStream);
        }

        public TypedOutputEntity(TypedOutput typedOutput2) {
            this.typedOutput = typedOutput2;
            setContentType(typedOutput2.mimeType());
        }
    }

    public static class GenericEntityHttpRequest extends HttpEntityEnclosingRequestBase {
        public final String method;

        public GenericEntityHttpRequest(Request request) {
            this.method = request.method;
            setURI(URI.create(request.url));
            for (Header header : request.headers) {
                addHeader(new BasicHeader(header.name, header.value));
            }
            setEntity(new TypedOutputEntity(request.body));
        }

        public String getMethod() {
            return this.method;
        }
    }

    public static class GenericHttpRequest extends HttpRequestBase {
        public final String method;

        public GenericHttpRequest(Request request) {
            this.method = request.method;
            setURI(URI.create(request.url));
            for (Header header : request.headers) {
                addHeader(new BasicHeader(header.name, header.value));
            }
        }

        public String getMethod() {
            return this.method;
        }
    }

    public static HttpClient createDefaultClient() {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 15000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, Defaults.READ_TIMEOUT_MILLIS);
        return new DefaultHttpClient(basicHttpParams);
    }

    public static HttpUriRequest createRequest(Request request) {
        if (request.body != null) {
            return new GenericEntityHttpRequest(request);
        }
        return new GenericHttpRequest(request);
    }

    public static Response parseResponse(String str, HttpResponse httpResponse) throws IOException {
        StatusLine statusLine = httpResponse.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        String reasonPhrase = statusLine.getReasonPhrase();
        ArrayList arrayList = new ArrayList();
        Header[] allHeaders = httpResponse.getAllHeaders();
        String str2 = OculusFileDownloader.ACCEPT_BINARY_STREAM;
        for (Header header : allHeaders) {
            String name = header.getName();
            String value = header.getValue();
            if ("Content-Type".equalsIgnoreCase(name)) {
                str2 = value;
            }
            arrayList.add(new Header(name, value));
        }
        TypedByteArray typedByteArray = null;
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null) {
            typedByteArray = new TypedByteArray(str2, EntityUtils.toByteArray(entity));
        }
        return new Response(str, statusCode, reasonPhrase, arrayList, typedByteArray);
    }

    public ApacheClient() {
        this(createDefaultClient());
    }

    public ApacheClient(HttpClient httpClient) {
        this.client = httpClient;
    }

    public HttpResponse execute(HttpClient httpClient, HttpUriRequest httpUriRequest) throws IOException {
        return httpClient.execute(httpUriRequest);
    }

    @Override // retrofit.client.Client
    public Response execute(Request request) throws IOException {
        return parseResponse(request.url, this.client.execute(createRequest(request)));
    }
}
