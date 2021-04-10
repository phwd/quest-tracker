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
public final class XV extends X509ExtendedTrustManager implements X509TrustManager {
    public final XU A00;

    public XV(long j) {
        C0308cL cLVar;
        synchronized (C0308cL.class) {
            cLVar = C0308cL.A02;
            if (cLVar == null) {
                cLVar = new C0308cL();
                C0308cL.A02 = cLVar;
            }
        }
        this.A00 = new XU(j, cLVar);
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
        XU xu = this.A00;
        ((X509ExtendedTrustManager) xu.A03).checkServerTrusted(x509CertificateArr, str, socket);
        xu.A00(x509CertificateArr);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        XU xu = this.A00;
        ((X509ExtendedTrustManager) xu.A03).checkServerTrusted(x509CertificateArr, str, sSLEngine);
        xu.A00(x509CertificateArr);
    }
}
