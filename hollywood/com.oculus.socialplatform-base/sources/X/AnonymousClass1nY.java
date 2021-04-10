package X;

import android.util.Log;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.apache.commons.cli.HelpFormatter;

/* renamed from: X.1nY  reason: invalid class name */
public final class AnonymousClass1nY implements AbstractC08081dw {
    public C10521nZ A00;
    public final long A01;
    public final AnonymousClass1na A02 = new AnonymousClass1na();
    public final C08291eL A03;
    public final File A04;

    @Override // X.AbstractC08081dw
    public final synchronized void clear() {
        try {
            C10521nZ A002 = A00();
            A002.close();
            C06051On.A00(A002.A07);
        } catch (IOException e) {
            if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                Log.w("DiskLruCacheWrapper", "Unable to clear disk cache or disk cache cleared externally", e);
            }
        } catch (Throwable th) {
            this.A00 = null;
            throw th;
        }
        this.A00 = null;
    }

    private synchronized C10521nZ A00() throws IOException {
        C10521nZ r8;
        String A012;
        String str;
        r8 = this.A00;
        if (r8 == null) {
            File file = this.A04;
            long j = this.A01;
            if (j > 0) {
                File file2 = new File(file, "journal.bkp");
                if (file2.exists()) {
                    File file3 = new File(file, "journal");
                    if (file3.exists()) {
                        file2.delete();
                    } else if (!file2.renameTo(file3)) {
                        throw new IOException();
                    }
                }
                r8 = new C10521nZ(file, j);
                File file4 = r8.A08;
                if (file4.exists()) {
                    try {
                        FileInputStream fileInputStream = new FileInputStream(file4);
                        Charset charset = C06051On.A01;
                        AnonymousClass1nc r16 = new AnonymousClass1nc(fileInputStream, charset);
                        try {
                            String A013 = r16.A01();
                            String A014 = r16.A01();
                            String A015 = r16.A01();
                            String A016 = r16.A01();
                            String A017 = r16.A01();
                            if ("libcore.io.DiskLruCache".equals(A013) && "1".equals(A014)) {
                                String num = Integer.toString(1);
                                if (num.equals(A015) && num.equals(A016) && "".equals(A017)) {
                                    int i = 0;
                                    while (true) {
                                        try {
                                            A012 = r16.A01();
                                            int indexOf = A012.indexOf(32);
                                            if (indexOf != -1) {
                                                int i2 = indexOf + 1;
                                                int indexOf2 = A012.indexOf(32, i2);
                                                if (indexOf2 == -1) {
                                                    str = A012.substring(i2);
                                                    if (indexOf == 6 && A012.startsWith("REMOVE")) {
                                                        r8.A0A.remove(str);
                                                        i++;
                                                    }
                                                } else {
                                                    str = A012.substring(i2, indexOf2);
                                                }
                                                LinkedHashMap<String, AnonymousClass1nb> linkedHashMap = r8.A0A;
                                                AnonymousClass1nb r12 = linkedHashMap.get(str);
                                                if (r12 == null) {
                                                    r12 = new AnonymousClass1nb(r8, str);
                                                    linkedHashMap.put(str, r12);
                                                }
                                                if (indexOf2 != -1) {
                                                    if (indexOf == 5 && A012.startsWith("CLEAN")) {
                                                        String[] split = A012.substring(indexOf2 + 1).split(HelpFormatter.DEFAULT_LONG_OPT_SEPARATOR);
                                                        r12.A02 = true;
                                                        r12.A01 = null;
                                                        int length = split.length;
                                                        if (length == r12.A07.A06) {
                                                            for (int i3 = 0; i3 < length; i3++) {
                                                                try {
                                                                    r12.A06[i3] = Long.parseLong(split[i3]);
                                                                } catch (NumberFormatException unused) {
                                                                    throw new IOException(AnonymousClass006.A07("unexpected journal line: ", Arrays.toString(split)));
                                                                }
                                                            }
                                                            i++;
                                                        } else {
                                                            throw new IOException(AnonymousClass006.A07("unexpected journal line: ", Arrays.toString(split)));
                                                        }
                                                    } else if (indexOf2 != -1) {
                                                        if (indexOf2 != -1) {
                                                            break;
                                                        }
                                                        if (!(indexOf == 4 && A012.startsWith("READ"))) {
                                                            break;
                                                        }
                                                        i++;
                                                    }
                                                }
                                                if (indexOf == 5) {
                                                    if (!A012.startsWith("DIRTY")) {
                                                        break;
                                                    }
                                                    r12.A01 = new AnonymousClass1ne(r8, r12);
                                                    i++;
                                                }
                                                i++;
                                            } else {
                                                throw new IOException(AnonymousClass006.A07("unexpected journal line: ", A012));
                                            }
                                        } catch (EOFException unused2) {
                                            LinkedHashMap<String, AnonymousClass1nb> linkedHashMap2 = r8.A0A;
                                            r8.A00 = i - linkedHashMap2.size();
                                            if (r16.A00 == -1) {
                                                C10521nZ.A01(r8);
                                            } else {
                                                r8.A02 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file4, true), charset));
                                            }
                                            try {
                                            } catch (RuntimeException e) {
                                                throw e;
                                            } catch (Exception unused3) {
                                            }
                                            C10521nZ.A03(r8.A09);
                                            Iterator<AnonymousClass1nb> it = linkedHashMap2.values().iterator();
                                            while (it.hasNext()) {
                                                AnonymousClass1nb next = it.next();
                                                if (next.A01 == null) {
                                                    r8.A01 += next.A06[0];
                                                } else {
                                                    next.A01 = null;
                                                    C10521nZ.A03(next.A03[0]);
                                                    C10521nZ.A03(next.A04[0]);
                                                    it.remove();
                                                }
                                            }
                                        }
                                    }
                                    throw new IOException(AnonymousClass006.A07("unexpected journal line: ", A012));
                                }
                            }
                            StringBuilder sb = new StringBuilder();
                            sb.append("unexpected journal header: [");
                            sb.append(A013);
                            sb.append(", ");
                            sb.append(A014);
                            sb.append(", ");
                            sb.append(A016);
                            sb.append(", ");
                            sb.append(A017);
                            sb.append("]");
                            throw new IOException(sb.toString());
                        } finally {
                            try {
                                r16.close();
                            } catch (RuntimeException e2) {
                                throw e2;
                            } catch (Exception unused4) {
                            }
                        }
                    } catch (IOException e3) {
                        PrintStream printStream = System.out;
                        StringBuilder sb2 = new StringBuilder("DiskLruCache ");
                        sb2.append(file);
                        sb2.append(" is corrupt: ");
                        sb2.append(e3.getMessage());
                        sb2.append(", removing");
                        printStream.println(sb2.toString());
                        r8.close();
                        C06051On.A00(r8.A07);
                    }
                }
                file.mkdirs();
                r8 = new C10521nZ(file, j);
                C10521nZ.A01(r8);
                this.A00 = r8;
            } else {
                throw new IllegalArgumentException("maxSize <= 0");
            }
        }
        return r8;
    }

    @Override // X.AbstractC08081dw
    public final File A3L(AbstractC06491aL r5) {
        try {
            AnonymousClass1nj A07 = A00().A07(this.A03.A00(r5));
            if (A07 != null) {
                return A07.A00[0];
            }
        } catch (IOException e) {
            if (Log.isLoggable("DiskLruCacheWrapper", 5)) {
                Log.w("DiskLruCacheWrapper", "Unable to get from disk cache", e);
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        r7 = r6.A03;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0091, code lost:
        monitor-enter(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r5 = r6.A01;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0096, code lost:
        if (r5.A01 != r6) goto L_0x00cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x009a, code lost:
        if (r5.A02 != false) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x009c, code lost:
        r6.A02[0] = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00a1, code lost:
        r8 = r5.A04[0];
        r1 = r7.A07;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ab, code lost:
        if (r1.exists() != false) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ad, code lost:
        r1.mkdirs();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00b0, code lost:
        monitor-exit(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00bb, code lost:
        if (r11.A00.A2m(r11.A02, r8, r11.A01) == false) goto L_0x00c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00bd, code lost:
        X.C10521nZ.A02(r7, r6, true);
        r6.A00 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00c5, code lost:
        if (r6.A00 != false) goto L_0x0106;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        X.C10521nZ.A02(r7, r6, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00d1, code lost:
        throw new java.lang.IllegalStateException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x00d5, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00d8, code lost:
        if (r6.A00 == false) goto L_0x00da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        X.C10521nZ.A02(r6.A03, r6, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00e0, code lost:
        throw r5;
     */
    @Override // X.AbstractC08081dw
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A8n(X.AbstractC06491aL r10, X.AnonymousClass1dZ r11) {
        /*
        // Method dump skipped, instructions count: 274
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1nY.A8n(X.1aL, X.1dZ):void");
    }

    @Deprecated
    public AnonymousClass1nY(File file, long j) {
        this.A04 = file;
        this.A01 = j;
        this.A03 = new C08291eL();
    }
}
