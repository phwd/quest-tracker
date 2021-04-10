package X;

import java.io.IOException;
import java.net.Socket;
import java.security.cert.Certificate;
import java.util.concurrent.ExecutorService;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/* renamed from: X.0my  reason: invalid class name and case insensitive filesystem */
public final class C06370my extends AbstractC01750Xb {
    public final C01860Xt A00;
    public final SSLSocketFactory A01;

    public C06370my(ExecutorService executorService, SSLSocketFactory sSLSocketFactory, C01860Xt r3, boolean z) {
        super(executorService, z);
        this.A01 = sSLSocketFactory;
        this.A00 = r3;
    }

    @Override // X.AbstractC01750Xb
    public final Socket A01(Socket socket, String str, int i) throws IOException {
        String str2;
        Object[] objArr;
        String str3;
        String str4;
        int length;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        AnonymousClass0W9.A00(socket.isConnected());
        SSLSocket sSLSocket = (SSLSocket) this.A01.createSocket(socket, str, i, true);
        C01860Xt r3 = this.A00;
        sSLSocket.getInputStream();
        SSLSession session = sSLSocket.getSession();
        if (session != null) {
            if ("SSL_NULL_WITH_NULL_NULL".equals(session.getCipherSuite())) {
                objArr = new Object[7];
                if (sSLSocket.isClosed()) {
                    str5 = "closed";
                } else {
                    str5 = "open";
                }
                objArr[0] = str5;
                if (sSLSocket.isConnected()) {
                    str6 = "connected";
                } else {
                    str6 = "disconnected";
                }
                objArr[1] = str6;
                if (sSLSocket.isBound()) {
                    str7 = "bound";
                } else {
                    str7 = "unbound";
                }
                objArr[2] = str7;
                if (sSLSocket.isInputShutdown()) {
                    str8 = "input_shutdown";
                } else {
                    str8 = "input_open";
                }
                objArr[3] = str8;
                if (sSLSocket.isOutputShutdown()) {
                    str9 = "output_shutdown";
                } else {
                    str9 = "output_open";
                }
                objArr[4] = str9;
                objArr[5] = str;
                objArr[6] = String.valueOf(sSLSocket.getInetAddress());
                str4 = "SSL handshake returned an invalid session. Socket state (%s, %s, %s, %s, %s, %s, %s)";
            } else if (r3.A00.verify(str, session)) {
                return sSLSocket;
            } else {
                objArr = new Object[3];
                objArr[0] = str;
                objArr[1] = sSLSocket.getInetAddress().toString();
                try {
                    Certificate[] peerCertificates = session.getPeerCertificates();
                    if (peerCertificates == null || (length = peerCertificates.length) <= 0) {
                        str3 = "No certificates";
                    } else {
                        str3 = String.format(null, "num: %d, %s", Integer.valueOf(length), peerCertificates[0].toString());
                    }
                } catch (SSLException e) {
                    str3 = AnonymousClass006.A05("Exception getting certificates ", e.toString());
                }
                objArr[2] = str3;
                str4 = "could not verify hostname for (%s, %s). (%s)";
            }
            str2 = String.format(null, str4, objArr);
        } else {
            str2 = "SSL Session is null";
        }
        throw new SSLException(str2);
    }
}
