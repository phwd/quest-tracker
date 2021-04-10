package X;

import com.oculus.horizon.cast.CastHTTPServerBase;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.net.ssl.SSLSocketFactory;

/* renamed from: X.1ea  reason: invalid class name */
public abstract class AnonymousClass1ea {
    public static final Pattern CONTENT_DISPOSITION_ATTRIBUTE_PATTERN = Pattern.compile(CONTENT_DISPOSITION_ATTRIBUTE_REGEX);
    public static final String CONTENT_DISPOSITION_ATTRIBUTE_REGEX = "[ |\t]*([a-zA-Z]*)[ |\t]*=[ |\t]*['|\"]([^\"^']*)['|\"]";
    public static final Pattern CONTENT_DISPOSITION_PATTERN = Pattern.compile(CONTENT_DISPOSITION_REGEX, 2);
    public static final String CONTENT_DISPOSITION_REGEX = "([ |\t]*Content-Disposition[ |\t]*:)(.*)";
    public static final Pattern CONTENT_TYPE_PATTERN = Pattern.compile(CONTENT_TYPE_REGEX, 2);
    public static final String CONTENT_TYPE_REGEX = "([ |\t]*content-type[ |\t]*:)(.*)";
    public static final Logger LOG = Logger.getLogger(AnonymousClass1ea.class.getName());
    public static final String MIME_HTML = "text/html";
    public static final String MIME_PLAINTEXT = "text/plain";
    public static Map<String, String> MIME_TYPES = null;
    public static final String QUERY_STRING_PARAMETER = "NanoHttpd.QUERY_STRING";
    public static final int SOCKET_READ_TIMEOUT = 5000;
    public AnonymousClass1hR asyncRunner = new C09421ee();
    public final String hostname = null;
    public final int myPort = CastHTTPServerBase.PORT;
    public volatile ServerSocket myServerSocket;
    public Thread myThread;
    public boolean proxyEnabled = false;
    public AnonymousClass1hQ serverSocketFactory = new AnonymousClass1gs();
    public final SSLSocketFactory sslSocketFactory;
    public AnonymousClass1i0 tempFileManagerFactory = new AnonymousClass1hD(this);

    public final void A04(int i) throws IOException {
        this.myServerSocket = this.serverSocketFactory.A1z();
        this.myServerSocket.setReuseAddress(true);
        AnonymousClass1en r2 = new AnonymousClass1en(this, i);
        Thread thread = new Thread(r2);
        this.myThread = thread;
        thread.setDaemon(false);
        this.myThread.setName("NanoHttpd Main Listener");
        this.myThread.start();
        while (!r2.A01 && r2.A00 == null) {
            try {
                Thread.sleep(10);
            } catch (Throwable unused) {
            }
        }
        IOException iOException = r2.A00;
        if (iOException != null) {
            throw iOException;
        }
    }

    public static AnonymousClass1eX A00(AnonymousClass1iB r9, String str) {
        byte[] bArr;
        ByteArrayInputStream byteArrayInputStream;
        long length;
        String str2 = MIME_PLAINTEXT;
        AnonymousClass1fQ r4 = new AnonymousClass1fQ(str2);
        if (str == null) {
            byteArrayInputStream = new ByteArrayInputStream(new byte[0]);
            length = 0;
        } else {
            try {
                String str3 = r4.A03;
                if (str3 == null) {
                    str3 = "US-ASCII";
                }
                if (!Charset.forName(str3).newEncoder().canEncode(str) && str3 == null) {
                    r4 = new AnonymousClass1fQ(AnonymousClass006.A05(str2, "; charset=UTF-8"));
                }
                String str4 = r4.A03;
                if (str4 == null) {
                    str4 = "US-ASCII";
                }
                bArr = str.getBytes(str4);
            } catch (UnsupportedEncodingException e) {
                LOG.log(Level.SEVERE, "encoding problem, responding nothing", (Throwable) e);
                bArr = new byte[0];
            }
            str2 = r4.A02;
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            length = (long) bArr.length;
        }
        return new AnonymousClass1eX(r9, str2, byteArrayInputStream, length);
    }

    public static String A01(String str) {
        try {
            return URLDecoder.decode(str, "UTF8");
        } catch (UnsupportedEncodingException e) {
            LOG.log(Level.WARNING, "Encoding not supported, ignored", (Throwable) e);
            return null;
        }
    }

    public static final void A02(Object obj) {
        if (obj != null) {
            try {
                if (obj instanceof Closeable) {
                    ((Closeable) obj).close();
                } else if (obj instanceof Socket) {
                    ((Socket) obj).close();
                } else if (obj instanceof ServerSocket) {
                    ((ServerSocket) obj).close();
                } else {
                    throw new IllegalArgumentException("Unknown object to close");
                }
            } catch (IOException e) {
                LOG.log(Level.SEVERE, "Could not close", (Throwable) e);
            }
        }
    }

    public AnonymousClass1eX A03(AnonymousClass1em r4) {
        HashMap hashMap = new HashMap();
        AnonymousClass1fH A3s = r4.A3s();
        if (AnonymousClass1fH.PUT.equals(A3s) || AnonymousClass1fH.POST.equals(A3s)) {
            try {
                r4.A7G(hashMap);
            } catch (IOException e) {
                return A00(AnonymousClass1Xz.INTERNAL_ERROR, AnonymousClass006.A05("SERVER INTERNAL ERROR: IOException: ", e.getMessage()));
            } catch (AnonymousClass1gT e2) {
                return A00(e2.status, e2.getMessage());
            }
        }
        r4.A45().put(QUERY_STRING_PARAMETER, r4.A4C());
        return A00(AnonymousClass1Xz.NOT_FOUND, "Not Found");
    }
}
