package X;

import java.net.Socket;
import java.security.cert.Certificate;
import java.util.concurrent.Callable;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/* renamed from: X.23Y  reason: invalid class name */
public class AnonymousClass23Y implements Callable<Socket> {
    public final /* synthetic */ int A00;
    public final /* synthetic */ AnonymousClass23V A01;
    public final /* synthetic */ String A02;
    public final /* synthetic */ Socket A03;

    public AnonymousClass23Y(AnonymousClass23V r1, Socket socket, String str, int i) {
        this.A01 = r1;
        this.A03 = socket;
        this.A02 = str;
        this.A00 = i;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final Socket call() throws Exception {
        String str;
        int length;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        AnonymousClass23V r4 = this.A01;
        Socket socket = this.A03;
        String str7 = this.A02;
        int i = this.A00;
        C142122j r42 = (C142122j) r4;
        AnonymousClass1ZK.A00(socket.isConnected());
        SSLSocket sSLSocket = (SSLSocket) r42.A01.createSocket(socket, str7, i, true);
        AnonymousClass246 r43 = r42.A00;
        sSLSocket.getInputStream();
        SSLSession session = sSLSocket.getSession();
        if (session == null) {
            throw new SSLException("SSL Session is null");
        } else if ("SSL_NULL_WITH_NULL_NULL".equals(session.getCipherSuite())) {
            if (sSLSocket.isClosed()) {
                str2 = "closed";
            } else {
                str2 = "open";
            }
            if (sSLSocket.isConnected()) {
                str3 = "connected";
            } else {
                str3 = "disconnected";
            }
            if (sSLSocket.isBound()) {
                str4 = "bound";
            } else {
                str4 = "unbound";
            }
            if (sSLSocket.isInputShutdown()) {
                str5 = "input_shutdown";
            } else {
                str5 = "input_open";
            }
            if (sSLSocket.isOutputShutdown()) {
                str6 = "output_shutdown";
            } else {
                str6 = "output_open";
            }
            throw new SSLException(String.format(null, "SSL handshake returned an invalid session. Socket state (%s, %s, %s, %s, %s, %s, %s)", str2, str3, str4, str5, str6, str7, String.valueOf(sSLSocket.getInetAddress())));
        } else if (r43.A00.verify(str7, session)) {
            return sSLSocket;
        } else {
            String obj = sSLSocket.getInetAddress().toString();
            try {
                Certificate[] peerCertificates = session.getPeerCertificates();
                if (peerCertificates == null || (length = peerCertificates.length) <= 0) {
                    str = "No certificates";
                } else {
                    str = String.format(null, "num: %d, %s", Integer.valueOf(length), peerCertificates[0].toString());
                }
            } catch (SSLException e) {
                str = AnonymousClass006.A07("Exception getting certificates ", e.toString());
            }
            throw new SSLException(String.format(null, "could not verify hostname for (%s, %s). (%s)", str7, obj, str));
        }
    }
}
