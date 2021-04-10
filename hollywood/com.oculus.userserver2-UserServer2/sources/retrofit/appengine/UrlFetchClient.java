package retrofit.appengine;

import X.AnonymousClass06;
import com.facebook.acra.util.HttpRequestMultipart;
import com.facebook.tigon.iface.TigonRequest;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedOutput;

public class UrlFetchClient implements Client {
    public final URLFetchService urlFetchService;

    public static HTTPRequest createRequest(Request request) throws IOException {
        HTTPRequest hTTPRequest = new HTTPRequest(new URL(request.url), getHttpMethod(request.method));
        for (Header header : request.headers) {
            hTTPRequest.addHeader(new HTTPHeader(header.name, header.value));
        }
        TypedOutput typedOutput = request.body;
        if (typedOutput != null) {
            String mimeType = typedOutput.mimeType();
            if (mimeType != null) {
                hTTPRequest.addHeader(new HTTPHeader(HttpRequestMultipart.CONTENT_TYPE, mimeType));
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            typedOutput.writeTo(byteArrayOutputStream);
            hTTPRequest.setPayload(byteArrayOutputStream.toByteArray());
        }
        return hTTPRequest;
    }

    public static HTTPMethod getHttpMethod(String str) {
        if (TigonRequest.GET.equals(str)) {
            return HTTPMethod.GET;
        }
        if (TigonRequest.POST.equals(str)) {
            return HTTPMethod.POST;
        }
        if ("PATCH".equals(str)) {
            return HTTPMethod.PATCH;
        }
        if ("PUT".equals(str)) {
            return HTTPMethod.PUT;
        }
        if ("DELETE".equals(str)) {
            return HTTPMethod.DELETE;
        }
        if (TigonRequest.HEAD.equals(str)) {
            return HTTPMethod.HEAD;
        }
        throw new IllegalStateException(AnonymousClass06.A03("Illegal HTTP method: ", str));
    }

    public static Response parseResponse(HTTPResponse hTTPResponse, HTTPRequest hTTPRequest) {
        URL finalUrl = hTTPResponse.getFinalUrl();
        if (finalUrl == null) {
            finalUrl = hTTPRequest.getURL();
        }
        String obj = finalUrl.toString();
        int responseCode = hTTPResponse.getResponseCode();
        List<HTTPHeader> headers = hTTPResponse.getHeaders();
        ArrayList arrayList = new ArrayList(headers.size());
        String str = "application/octet-stream";
        for (HTTPHeader hTTPHeader : headers) {
            String name = hTTPHeader.getName();
            String value = hTTPHeader.getValue();
            if (HttpRequestMultipart.CONTENT_TYPE.equalsIgnoreCase(name)) {
                str = value;
            }
            arrayList.add(new Header(name, value));
        }
        TypedByteArray typedByteArray = null;
        byte[] content = hTTPResponse.getContent();
        if (content != null) {
            typedByteArray = new TypedByteArray(str, content);
        }
        return new Response(obj, responseCode, "", arrayList, typedByteArray);
    }

    public UrlFetchClient() {
        this(URLFetchServiceFactory.getURLFetchService());
    }

    public UrlFetchClient(URLFetchService uRLFetchService) {
        this.urlFetchService = uRLFetchService;
    }

    public HTTPResponse execute(URLFetchService uRLFetchService, HTTPRequest hTTPRequest) throws IOException {
        return uRLFetchService.fetch(hTTPRequest);
    }

    @Override // retrofit.client.Client
    public Response execute(Request request) throws IOException {
        HTTPRequest createRequest = createRequest(request);
        return parseResponse(this.urlFetchService.fetch(createRequest), createRequest);
    }
}
