package X;

import android.annotation.TargetApi;
import com.facebook.acra.util.minidump.MinidumpReader;
import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;

@TargetApi(MinidumpReader.MODULE_LIST_OFFSET)
public final class SK extends X509ExtendedTrustManager implements X509TrustManager {
    public final SJ A00;

    public SK(long j) {
        W1 w1;
        synchronized (W1.class) {
            w1 = W1.A02;
            if (w1 == null) {
                w1 = new W1();
                W1.A02 = w1;
            }
        }
        this.A00 = new SJ(j, w1);
    }

    public final X509Certificate[] getAcceptedIssuers() {
        return this.A00.getAcceptedIssuers();
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        this.A00.checkServerTrusted(x509CertificateArr, str);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
        SJ sj = this.A00;
        ((X509ExtendedTrustManager) sj.A03).checkServerTrusted(x509CertificateArr, str, socket);
        sj.A00(x509CertificateArr);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        SJ sj = this.A00;
        ((X509ExtendedTrustManager) sj.A03).checkServerTrusted(x509CertificateArr, str, sSLEngine);
        sj.A00(x509CertificateArr);
    }
}
