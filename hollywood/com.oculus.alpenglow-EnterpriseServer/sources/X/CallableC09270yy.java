package X;

import java.net.Socket;
import java.security.cert.Certificate;
import java.util.concurrent.Callable;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

/* renamed from: X.0yy  reason: invalid class name and case insensitive filesystem */
public class CallableC09270yy implements Callable<Socket> {
    public final /* synthetic */ int A00;
    public final /* synthetic */ AnonymousClass0z6 A01;
    public final /* synthetic */ String A02;
    public final /* synthetic */ Socket A03;

    public CallableC09270yy(AnonymousClass0z6 r1, Socket socket, String str, int i) {
        this.A01 = r1;
        this.A03 = socket;
        this.A02 = str;
        this.A00 = i;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.concurrent.Callable
    public final Socket call() throws Exception {
        String str;
        String str2;
        Object[] objArr;
        String str3;
        int length;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        AnonymousClass0z6 r4 = this.A01;
        Socket socket = this.A03;
        String str9 = this.A02;
        int i = this.A00;
        C08780xo r42 = (C08780xo) r4;
        C08170wh.A00(socket.isConnected());
        SSLSocket sSLSocket = (SSLSocket) r42.A01.createSocket(socket, str9, i, true);
        C07580vX r43 = r42.A00;
        sSLSocket.getInputStream();
        SSLSession session = sSLSocket.getSession();
        if (session != null) {
            if ("SSL_NULL_WITH_NULL_NULL".equals(session.getCipherSuite())) {
                if (sSLSocket.isClosed()) {
                    str4 = "closed";
                } else {
                    str4 = "open";
                }
                if (sSLSocket.isConnected()) {
                    str5 = "connected";
                } else {
                    str5 = "disconnected";
                }
                if (sSLSocket.isBound()) {
                    str6 = "bound";
                } else {
                    str6 = "unbound";
                }
                if (sSLSocket.isInputShutdown()) {
                    str7 = "input_shutdown";
                } else {
                    str7 = "input_open";
                }
                if (sSLSocket.isOutputShutdown()) {
                    str8 = "output_shutdown";
                } else {
                    str8 = "output_open";
                }
                objArr = new Object[]{str4, str5, str6, str7, str8, str9, String.valueOf(sSLSocket.getInetAddress())};
                str3 = "SSL handshake returned an invalid session. Socket state (%s, %s, %s, %s, %s, %s, %s)";
            } else if (r43.A00.verify(str9, session)) {
                return sSLSocket;
            } else {
                String inetAddress = sSLSocket.getInetAddress().toString();
                try {
                    Certificate[] peerCertificates = session.getPeerCertificates();
                    if (peerCertificates == null || (length = peerCertificates.length) <= 0) {
                        str2 = "No certificates";
                    } else {
                        str2 = String.format(null, "num: %d, %s", Integer.valueOf(length), peerCertificates[0].toString());
                    }
                } catch (SSLException e) {
                    str2 = AnonymousClass006.A05("Exception getting certificates ", e.toString());
                }
                objArr = new Object[]{str9, inetAddress, str2};
                str3 = "could not verify hostname for (%s, %s). (%s)";
            }
            str = String.format(null, str3, objArr);
        } else {
            str = "SSL Session is null";
        }
        throw new SSLException(str);
    }
}
