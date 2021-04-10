package X;

import com.facebook.tigon.iface.TigonRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedOutput;

/* renamed from: X.1Su  reason: invalid class name */
public final class AnonymousClass1Su implements Client {
    public static final byte[] A01 = new byte[0];
    public final Call.Factory A00;

    @Override // retrofit.client.Client
    public final Response execute(Request request) throws IOException {
        RequestBody r1;
        AnonymousClass1Sv r11;
        Call.Factory factory = this.A00;
        String str = request.method;
        if ((TigonRequest.POST.equals(str) || "PUT".equals(str) || "PATCH".equals(str) || "PROPPATCH".equals(str) || "REPORT".equals(str)) && request.body == null) {
            r1 = RequestBody.create((MediaType) null, A01);
        } else {
            TypedOutput typedOutput = request.body;
            if (typedOutput == null) {
                r1 = null;
            } else {
                r1 = new AnonymousClass1Sw(MediaType.parse(typedOutput.mimeType()), typedOutput);
            }
        }
        Request.Builder builder = new Request.Builder();
        builder.url(request.url);
        builder.method(request.method, r1);
        List<Header> list = request.headers;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Header header = list.get(i);
            String str2 = header.value;
            if (str2 == null) {
                str2 = "";
            }
            builder.addHeader(header.name, str2);
        }
        okhttp3.Response execute = factory.newCall(builder.build()).execute();
        String obj = execute.request.url.toString();
        int i2 = execute.code;
        String str3 = execute.message;
        Headers headers = execute.headers;
        int length = headers.namesAndValues.length >> 1;
        ArrayList arrayList = new ArrayList(length);
        for (int i3 = 0; i3 < length; i3++) {
            arrayList.add(new Header(headers.namesAndValues[i3 << 1], headers.value(i3)));
        }
        ResponseBody responseBody = execute.body;
        if (responseBody.contentLength() == 0) {
            r11 = null;
        } else {
            r11 = new AnonymousClass1Sv(responseBody);
        }
        return new Response(obj, i2, str3, arrayList, r11);
    }

    public AnonymousClass1Su() {
        this(new OkHttpClient());
    }

    public AnonymousClass1Su(OkHttpClient okHttpClient) {
        if (okHttpClient != null) {
            this.A00 = okHttpClient;
            return;
        }
        throw new NullPointerException("client == null");
    }
}
