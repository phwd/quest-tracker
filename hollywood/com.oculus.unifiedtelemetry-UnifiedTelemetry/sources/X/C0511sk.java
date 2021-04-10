package X;

import com.facebook.analytics2.uploader.okhttp3.OkHttp3AnalyticsUploader;
import com.squareup.okhttp.internal.DiskLruCache;
import java.io.IOException;

/* renamed from: X.sk  reason: case insensitive filesystem */
public class C0511sk extends AbstractC0361di {
    public final /* synthetic */ ST A00;
    public final /* synthetic */ String A01;

    public C0511sk(String str, ST st) {
        this.A01 = str;
        this.A00 = st;
    }

    @Override // X.AbstractC0361di
    public final void A02(KJ kj) throws IOException {
        C0512sl slVar = new C0512sl(kj);
        String str = this.A01;
        if (str != null) {
            slVar.A00("access_token", str);
        }
        slVar.A00("format", "json");
        slVar.A00("compressed", "0");
        ST st = this.A00;
        if (st.A3G()) {
            slVar.A00("multi_batch", DiskLruCache.VERSION_1);
        }
        slVar.A00("message", null);
        st.A5v(new Mt(new MY(kj.A48())));
    }

    @Override // X.AbstractC0361di
    public final C0366dn A01() {
        return OkHttp3AnalyticsUploader.WWW_FORM_URLENCODED_CONTENT_TYPE;
    }
}
