package X;

import android.annotation.TargetApi;
import android.net.http.X509TrustManagerExtensions;
import javax.annotation.Nullable;
import javax.net.ssl.X509TrustManager;

@TargetApi(17)
public final class XU extends SP implements X509TrustManager {
    public final X509TrustManagerExtensions A00 = new X509TrustManagerExtensions(this.A03);

    public XU(long j, @Nullable C0308cL cLVar) {
        super(j, cLVar);
    }
}
