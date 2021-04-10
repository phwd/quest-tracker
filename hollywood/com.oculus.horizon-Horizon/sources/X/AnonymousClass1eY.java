package X;

import fi.iki.elonen.NanoHTTPD;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/* renamed from: X.1eY  reason: invalid class name */
public class AnonymousClass1eY implements AnonymousClass1em {
    public int A00;
    public int A01;
    public AnonymousClass1eh A02;
    public AnonymousClass1fH A03;
    public BufferedInputStream A04;
    public OutputStream A05;
    public String A06;
    public String A07;
    public String A08;
    public String A09;
    public Socket A0A;
    public Map<String, String> A0B;
    public Map<String, List<String>> A0C;
    public String A0D;
    public final AnonymousClass1ef A0E;
    public final /* synthetic */ AnonymousClass1ea A0F;

    /* JADX WARNING: Code restructure failed: missing block: B:185:?, code lost:
        throw new X.AnonymousClass1gT(X.AnonymousClass1Xz.BAD_REQUEST, "BAD REQUEST: Content type is multipart/form-data but chunk does not start with boundary.");
     */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x02ef  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x0062 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0069 A[Catch:{ all -> 0x030c }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0080 A[Catch:{ all -> 0x030c }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0086 A[Catch:{ all -> 0x030c }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0087 A[Catch:{ all -> 0x030c }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b3 A[Catch:{ all -> 0x030c }] */
    @Override // X.AnonymousClass1em
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A7G(java.util.Map<java.lang.String, java.lang.String> r26) throws java.io.IOException, X.AnonymousClass1gT {
        /*
        // Method dump skipped, instructions count: 785
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1eY.A7G(java.util.Map):void");
    }

    public AnonymousClass1eY(AnonymousClass1ea r4, AnonymousClass1ef r5, Socket socket) throws IOException {
        String str;
        this.A0F = r4;
        this.A0E = r5;
        this.A04 = new BufferedInputStream(socket.getInputStream(), 8192);
        this.A05 = socket.getOutputStream();
        this.A0A = socket;
        InetAddress inetAddress = socket.getInetAddress();
        if (inetAddress.isLoopbackAddress() || inetAddress.isAnyLocalAddress()) {
            str = "127.0.0.1";
        } else {
            str = inetAddress.getHostAddress();
        }
        this.A08 = str;
        if (!inetAddress.isLoopbackAddress() && !inetAddress.isAnyLocalAddress()) {
            inetAddress.getHostName();
        }
        this.A0B = new LinkedHashMap();
    }

    private String A00(ByteBuffer byteBuffer, int i, int i2) {
        Throwable th;
        Exception e;
        if (i2 <= 0) {
            return "";
        }
        FileOutputStream fileOutputStream = null;
        try {
            AnonymousClass1ef r2 = this.A0E;
            NanoHTTPD.TempFile r1 = new AnonymousClass1fz(r2.A00);
            r2.A01.add(r1);
            ByteBuffer duplicate = byteBuffer.duplicate();
            File file = r1.A00;
            FileOutputStream fileOutputStream2 = new FileOutputStream(file.getAbsolutePath());
            try {
                FileChannel channel = fileOutputStream2.getChannel();
                duplicate.position(i).limit(i + i2);
                channel.write(duplicate.slice());
                String absolutePath = file.getAbsolutePath();
                AnonymousClass1ea.A02(fileOutputStream2);
                return absolutePath;
            } catch (Exception e2) {
                e = e2;
                fileOutputStream = fileOutputStream2;
                try {
                    throw new Error(e);
                } catch (Throwable th2) {
                    th = th2;
                    AnonymousClass1ea.A02(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = fileOutputStream2;
                AnonymousClass1ea.A02(fileOutputStream);
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            throw new Error(e);
        }
    }

    /* JADX WARN: Incorrect args count in method signature: (Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;Ljava/util/List<Ljava/lang/String;>;>;)V */
    public static void A01(AnonymousClass1eY r5, String str, Map map) {
        String trim;
        String str2;
        if (str == null) {
            r5.A0D = "";
            return;
        }
        r5.A0D = str;
        StringTokenizer stringTokenizer = new StringTokenizer(str, "&");
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            int indexOf = nextToken.indexOf(61);
            if (indexOf >= 0) {
                trim = AnonymousClass1ea.A01(nextToken.substring(0, indexOf)).trim();
                str2 = AnonymousClass1ea.A01(nextToken.substring(indexOf + 1));
            } else {
                trim = AnonymousClass1ea.A01(nextToken).trim();
                str2 = "";
            }
            List list = (List) map.get(trim);
            if (list == null) {
                list = new ArrayList();
                map.put(trim, list);
            }
            list.add(str2);
        }
    }

    @Override // X.AnonymousClass1em
    @Deprecated
    public final Map<String, String> A45() {
        HashMap hashMap = new HashMap();
        for (String str : this.A0C.keySet()) {
            hashMap.put(str, this.A0C.get(str).get(0));
        }
        return hashMap;
    }

    @Override // X.AnonymousClass1em
    public final String A4C() {
        String str = this.A0D;
        if (str == null) {
            return "";
        }
        return str;
    }

    @Override // X.AnonymousClass1em
    public final Map<String, String> A3W() {
        return this.A0B;
    }

    @Override // X.AnonymousClass1em
    public final InputStream A3b() {
        return this.A04;
    }

    @Override // X.AnonymousClass1em
    public final AnonymousClass1fH A3s() {
        return this.A03;
    }

    @Override // X.AnonymousClass1em
    public final Map<String, List<String>> A44() {
        return this.A0C;
    }

    @Override // X.AnonymousClass1em
    public final String getUri() {
        return this.A09;
    }
}
