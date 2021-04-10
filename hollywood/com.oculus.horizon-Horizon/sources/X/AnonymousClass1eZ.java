package X;

import java.io.InputStream;
import java.net.Socket;

/* renamed from: X.1eZ  reason: invalid class name */
public class AnonymousClass1eZ implements Runnable {
    public static final String __redex_internal_original_name = "fi.iki.elonen.NanoHTTPD$ClientHandler";
    public final InputStream A00;
    public final Socket A01;
    public final /* synthetic */ AnonymousClass1ea A02;

    public AnonymousClass1eZ(AnonymousClass1ea r1, InputStream inputStream, Socket socket) {
        this.A02 = r1;
        this.A00 = inputStream;
        this.A01 = socket;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:150:?, code lost:
        X.AnonymousClass1ea.A00(X.AnonymousClass1Xz.INTERNAL_ERROR, "SERVER INTERNAL ERROR: RuntimeException").A03(r2.A05);
        X.AnonymousClass1ea.A02(r2.A05);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x0364, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0365, code lost:
        X.AnonymousClass1ea.A00(r0.status, r0.getMessage()).A03(r2.A05);
        X.AnonymousClass1ea.A02(r2.A05);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x037a, code lost:
        X.AnonymousClass1ea.A00(X.AnonymousClass1Xz.INTERNAL_ERROR, "SERVER INTERNAL ERROR: IOException").A03(r2.A05);
        X.AnonymousClass1ea.A02(r2.A05);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x038d, code lost:
        X.AnonymousClass1ea.A00(X.AnonymousClass1Xz.INTERNAL_ERROR, "SSL PROTOCOL FAILURE").A03(r2.A05);
        X.AnonymousClass1ea.A02(r2.A05);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x03a0, code lost:
        X.AnonymousClass1ea.A00(X.AnonymousClass1Xz.BAD_REQUEST, "BAD REQUEST: Invalid URI Syntax").A03(r2.A05);
        X.AnonymousClass1ea.A02(r2.A05);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x03b3, code lost:
        X.AnonymousClass1ea.A00(X.AnonymousClass1Xz.BAD_REQUEST, "BAD REQUEST: Malformed URL").A03(r2.A05);
        X.AnonymousClass1ea.A02(r2.A05);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x03cf, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x03d0, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x03d1, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x03d2, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x03d3, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x03d4, code lost:
        X.AnonymousClass1ea.A02(null);
        r2.A0E.A00();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x03dc, code lost:
        throw r1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:149:0x0351 */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x0351 A[SYNTHETIC, Splitter:B:149:0x0351] */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x0364 A[ExcHandler: 1gT (r0v31 'e' X.1gT A[CUSTOM_DECLARE]), Splitter:B:6:0x0024] */
    /* JADX WARNING: Removed duplicated region for block: B:158:? A[ExcHandler: URISyntaxException (unused java.net.URISyntaxException), SYNTHETIC, Splitter:B:6:0x0024] */
    /* JADX WARNING: Removed duplicated region for block: B:160:? A[ExcHandler: MalformedURLException (unused java.net.MalformedURLException), SYNTHETIC, Splitter:B:6:0x0024] */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x03d1 A[ExcHandler: SocketException (r0v16 'e' java.net.SocketException A[CUSTOM_DECLARE]), Splitter:B:6:0x0024] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        // Method dump skipped, instructions count: 1062
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1eZ.run():void");
    }
}
