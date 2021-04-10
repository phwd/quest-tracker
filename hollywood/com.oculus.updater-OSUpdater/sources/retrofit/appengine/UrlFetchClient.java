package retrofit.appengine;

import com.facebook.tigon.iface.TigonRequest;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.oculus.common.build.BuildConfig;
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
    private final URLFetchService urlFetchService;

    private static HTTPMethod getHttpMethod(String str) {
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
        throw new IllegalStateException("Illegal HTTP method: " + str);
    }

    public UrlFetchClient() {
        this(URLFetchServiceFactory.getURLFetchService());
    }

    public UrlFetchClient(URLFetchService uRLFetchService) {
        this.urlFetchService = uRLFetchService;
    }

    @Override // retrofit.client.Client
    public Response execute(Request request) throws IOException {
        HTTPRequest createRequest = createRequest(request);
        return parseResponse(execute(this.urlFetchService, createRequest), createRequest);
    }

    /* access modifiers changed from: protected */
    public HTTPResponse execute(URLFetchService uRLFetchService, HTTPRequest hTTPRequest) throws IOException {
        return uRLFetchService.fetch(hTTPRequest);
    }

    static HTTPRequest createRequest(Request request) throws IOException {
        HTTPRequest hTTPRequest = new HTTPRequest(new URL(request.getUrl()), getHttpMethod(request.getMethod()));
        for (Header header : request.getHeaders()) {
            hTTPRequest.addHeader(new HTTPHeader(header.getName(), header.getValue()));
        }
        TypedOutput body = request.getBody();
        if (body != null) {
            String mimeType = body.mimeType();
            if (mimeType != null) {
                hTTPRequest.addHeader(new HTTPHeader("Content-Type", mimeType));
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            body.writeTo(byteArrayOutputStream);
            hTTPRequest.setPayload(byteArrayOutputStream.toByteArray());
        }
        return hTTPRequest;
    }

    static Response parseResponse(HTTPResponse hTTPResponse, HTTPRequest hTTPRequest) {
        URL finalUrl = hTTPResponse.getFinalUrl();
        if (finalUrl == null) {
            finalUrl = hTTPRequest.getURL();
        }
        String url = finalUrl.toString();
        int responseCode = hTTPResponse.getResponseCode();
        List<HTTPHeader> headers = hTTPResponse.getHeaders();
        ArrayList arrayList = new ArrayList(headers.size());
        String str = "application/octet-stream";
        for (HTTPHeader hTTPHeader : headers) {
            String name = hTTPHeader.getName();
            String value = hTTPHeader.getValue();
            if ("Content-Type".equalsIgnoreCase(name)) {
                str = value;
            }
            arrayList.add(new Header(name, value));
        }
        TypedByteArray typedByteArray = null;
        byte[] content = hTTPResponse.getContent();
        if (content != null) {
            typedByteArray = new TypedByteArray(str, content);
        }
        return new Response(url, responseCode, BuildConfig.PROVIDER_SUFFIX, arrayList, typedByteArray);
    }
}
