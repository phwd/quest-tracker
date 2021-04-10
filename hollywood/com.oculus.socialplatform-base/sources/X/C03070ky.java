package X;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* renamed from: X.0ky  reason: invalid class name and case insensitive filesystem */
public final class C03070ky implements Interceptor {
    public final Map<String, AnonymousClass0Ug> A00 = new HashMap();

    public C03070ky(List<AnonymousClass0Ug> list) {
        for (AnonymousClass0Ug r2 : list) {
            this.A00.put(r2.A00, r2);
        }
    }

    @Override // okhttp3.Interceptor
    public final Response intercept(Interceptor.Chain chain) throws IOException {
        AnonymousClass0Ug r1;
        Request request = chain.request();
        HttpUrl httpUrl = request.url;
        if (!httpUrl.scheme.equals("https")) {
            String str = httpUrl.host;
            String str2 = str;
            if (str != null) {
                while (true) {
                    r1 = this.A00.get(str2);
                    if (r1 == null || (!str2.equals(str) && !r1.A02)) {
                        String substring = str2.substring(str2.indexOf(46) + 1);
                        int indexOf = substring.indexOf(46);
                        str2 = substring;
                        if (indexOf == -1) {
                            break;
                        }
                    }
                }
                if (r1.A01) {
                    HttpUrl httpUrl2 = request.url;
                    int i = httpUrl2.port;
                    if (i == 80) {
                        i = 443;
                    }
                    HttpUrl.Builder newBuilder = httpUrl2.newBuilder();
                    newBuilder.scheme("https");
                    newBuilder.port(i);
                    HttpUrl build = newBuilder.build();
                    Request.Builder newBuilder2 = request.newBuilder();
                    newBuilder2.url(build);
                    request = newBuilder2.build();
                }
            }
        }
        return chain.proceed(request);
    }
}
