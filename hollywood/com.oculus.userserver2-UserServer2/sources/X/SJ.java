package X;

import android.annotation.TargetApi;
import android.net.http.X509TrustManagerExtensions;
import javax.annotation.Nullable;
import javax.net.ssl.X509TrustManager;

@TargetApi(17)
public final class SJ extends C0117Qk implements X509TrustManager {
    public final X509TrustManagerExtensions A00 = new X509TrustManagerExtensions(this.A03);

    public SJ(long j, @Nullable W1 w1) {
        super(j, w1);
    }
}
