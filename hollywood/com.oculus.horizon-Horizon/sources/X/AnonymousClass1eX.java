package X;

import com.facebook.acra.util.HttpRequestMultipart;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.zip.GZIPOutputStream;

/* renamed from: X.1eX  reason: invalid class name */
public class AnonymousClass1eX implements Closeable {
    public AnonymousClass1fH A00;
    public String A01;
    public boolean A02;
    public boolean A03;
    public boolean A04;
    public long A05;
    public AnonymousClass1iB A06;
    public InputStream A07;
    public final AnonymousClass1gd A08 = new AnonymousClass1gd(this);

    public static final String A00(AnonymousClass1eX r2, String str) {
        List<String> list = r2.A08.A00;
        int size = list.size();
        do {
            size -= 2;
            if (size < 0) {
                return null;
            }
        } while (!str.equalsIgnoreCase(list.get(size)));
        return list.get(size + 1);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0030 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void A01(java.io.OutputStream r10, long r11) throws java.io.IOException {
        /*
            r9 = this;
            r2 = 16384(0x4000, double:8.0948E-320)
            int r0 = (int) r2
            byte[] r7 = new byte[r0]
            r6 = 0
            r4 = -1
            int r0 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            r8 = 0
            if (r0 != 0) goto L_0x000e
            r8 = 1
        L_0x000e:
            r4 = 0
            int r0 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r0 > 0) goto L_0x0029
            if (r8 == 0) goto L_0x0030
        L_0x0016:
            r4 = 16384(0x4000, double:8.0948E-320)
        L_0x0018:
            java.io.InputStream r1 = r9.A07
            int r0 = (int) r4
            int r0 = r1.read(r7, r6, r0)
            if (r0 <= 0) goto L_0x0030
            r10.write(r7, r6, r0)
            if (r8 != 0) goto L_0x000e
            long r0 = (long) r0
            long r11 = r11 - r0
            goto L_0x000e
        L_0x0029:
            if (r8 != 0) goto L_0x0016
            long r4 = java.lang.Math.min(r11, r2)
            goto L_0x0018
        L_0x0030:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1eX.A01(java.io.OutputStream, long):void");
    }

    public void A03(OutputStream outputStream) {
        long j;
        String str;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            if (this.A06 != null) {
                String str2 = new AnonymousClass1fQ(this.A01).A03;
                if (str2 == null) {
                    str2 = "US-ASCII";
                }
                PrintWriter printWriter = new PrintWriter((Writer) new BufferedWriter(new OutputStreamWriter(outputStream, str2)), false);
                printWriter.append((CharSequence) "HTTP/1.1").append((CharSequence) " ").append((CharSequence) this.A06.getDescription()).append((CharSequence) " \r\n");
                String str3 = this.A01;
                if (str3 != null) {
                    A02(printWriter, "Content-Type", str3);
                }
                if (A00(this, "date") == null) {
                    A02(printWriter, "Date", simpleDateFormat.format(new Date()));
                }
                int i = 0;
                while (true) {
                    List<String> list = this.A08.A00;
                    if (i >= (list.size() >> 1)) {
                        break;
                    }
                    A02(printWriter, list.get(i << 1), list.get((i << 1) + 1));
                    i++;
                }
                if (A00(this, AnonymousClass1eW.HEADER_CONNECTION) == null) {
                    if (this.A04) {
                        str = "keep-alive";
                    } else {
                        str = "close";
                    }
                    A02(printWriter, "Connection", str);
                }
                if (A00(this, "content-length") != null) {
                    this.A03 = false;
                }
                if (this.A03) {
                    A02(printWriter, "Content-Encoding", "gzip");
                    this.A02 = true;
                }
                if (this.A07 != null) {
                    j = this.A05;
                } else {
                    j = 0;
                }
                if (this.A00 != AnonymousClass1fH.HEAD && this.A02) {
                    A02(printWriter, "Transfer-Encoding", "chunked");
                } else if (!this.A03) {
                    String A002 = A00(this, "content-length");
                    if (A002 != null) {
                        try {
                            j = Long.parseLong(A002);
                        } catch (NumberFormatException unused) {
                            AnonymousClass1ea.LOG.severe(AnonymousClass006.A05("content-length was no number ", A002));
                        }
                    }
                    StringBuilder sb = new StringBuilder("Content-Length: ");
                    sb.append(j);
                    sb.append(HttpRequestMultipart.LINE_FEED);
                    printWriter.print(sb.toString());
                }
                printWriter.append((CharSequence) HttpRequestMultipart.LINE_FEED);
                printWriter.flush();
                if (this.A00 != AnonymousClass1fH.HEAD && this.A02) {
                    AnonymousClass1fM r3 = new AnonymousClass1fM(outputStream);
                    if (this.A03) {
                        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(r3);
                        A01(gZIPOutputStream, -1);
                        gZIPOutputStream.finish();
                    } else {
                        A01(r3, -1);
                    }
                    r3.A00();
                } else if (this.A03) {
                    GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(outputStream);
                    A01(gZIPOutputStream2, -1);
                    gZIPOutputStream2.finish();
                } else {
                    A01(outputStream, j);
                }
                outputStream.flush();
                AnonymousClass1ea.A02(this.A07);
                return;
            }
            throw new Error("sendResponse(): Status can't be null.");
        } catch (IOException e) {
            AnonymousClass1ea.LOG.log(Level.SEVERE, "Could not send response to the client", (Throwable) e);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        InputStream inputStream = this.A07;
        if (inputStream != null) {
            inputStream.close();
        }
    }

    public AnonymousClass1eX(AnonymousClass1iB r8, String str, InputStream inputStream, long j) {
        this.A06 = r8;
        this.A01 = str;
        boolean z = false;
        if (inputStream == null) {
            this.A07 = new ByteArrayInputStream(new byte[0]);
        } else {
            this.A07 = inputStream;
            this.A05 = j;
        }
        this.A02 = this.A05 < 0 ? true : z;
        this.A04 = true;
    }

    public static final void A02(PrintWriter printWriter, String str, String str2) {
        printWriter.append((CharSequence) str).append(": ").append((CharSequence) str2).append(HttpRequestMultipart.LINE_FEED);
    }
}
