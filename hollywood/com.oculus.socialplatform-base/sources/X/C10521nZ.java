package X;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: X.1nZ  reason: invalid class name and case insensitive filesystem */
public final class C10521nZ implements Closeable {
    public int A00;
    public long A01 = 0;
    public Writer A02;
    public long A03;
    public long A04 = 0;
    public final int A05;
    public final int A06;
    public final File A07;
    public final File A08;
    public final File A09;
    public final LinkedHashMap<String, AnonymousClass1nb> A0A = new LinkedHashMap<>(0, 0.75f, true);
    public final Callable<Void> A0B = new AnonymousClass1nf(this);
    public final ThreadPoolExecutor A0C = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactoryC10531nh());
    public final File A0D;

    public C10521nZ(File file, long j) {
        this.A07 = file;
        this.A05 = 1;
        this.A08 = new File(file, "journal");
        this.A09 = new File(file, "journal.tmp");
        this.A0D = new File(file, "journal.bkp");
        this.A06 = 1;
        this.A03 = j;
    }

    /* JADX INFO: finally extract failed */
    public static synchronized void A01(C10521nZ r13) throws IOException {
        synchronized (r13) {
            Writer writer = r13.A02;
            if (writer != null) {
                A05(writer);
            }
            File file = r13.A09;
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            Charset charset = C06051On.A01;
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream, charset));
            try {
                bufferedWriter.write("libcore.io.DiskLruCache");
                bufferedWriter.write("\n");
                bufferedWriter.write("1");
                bufferedWriter.write("\n");
                bufferedWriter.write(Integer.toString(r13.A05));
                bufferedWriter.write("\n");
                bufferedWriter.write(Integer.toString(r13.A06));
                bufferedWriter.write("\n");
                bufferedWriter.write("\n");
                for (AnonymousClass1nb r2 : r13.A0A.values()) {
                    if (r2.A01 != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("DIRTY ");
                        sb.append(r2.A05);
                        sb.append('\n');
                        bufferedWriter.write(sb.toString());
                    } else {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("CLEAN ");
                        sb2.append(r2.A05);
                        StringBuilder sb3 = new StringBuilder();
                        long[] jArr = r2.A06;
                        for (long j : jArr) {
                            sb3.append(' ');
                            sb3.append(j);
                        }
                        sb2.append(sb3.toString());
                        sb2.append('\n');
                        bufferedWriter.write(sb2.toString());
                    }
                }
                A05(bufferedWriter);
                File file2 = r13.A08;
                if (file2.exists()) {
                    File file3 = r13.A0D;
                    A03(file3);
                    if (!file2.renameTo(file3)) {
                        throw new IOException();
                    }
                }
                if (file.renameTo(file2)) {
                    r13.A0D.delete();
                    r13.A02 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file2, true), charset));
                } else {
                    throw new IOException();
                }
            } catch (Throwable th) {
                A05(bufferedWriter);
                throw th;
            }
        }
    }

    public static synchronized void A02(C10521nZ r10, AnonymousClass1ne r11, boolean z) throws IOException {
        synchronized (r10) {
            AnonymousClass1nb r5 = r11.A01;
            if (r5.A01 == r11) {
                if (z && !r5.A02) {
                    int i = 0;
                    while (true) {
                        if (i >= r10.A06) {
                            break;
                        } else if (!r11.A02[i]) {
                            A02(r11.A03, r11, false);
                            throw new IllegalStateException(AnonymousClass006.A03("Newly created entry didn't create value for index ", i));
                        } else if (!r5.A04[i].exists()) {
                            A02(r11.A03, r11, false);
                            break;
                        } else {
                            i++;
                        }
                    }
                }
                for (int i2 = 0; i2 < r10.A06; i2++) {
                    File file = r5.A04[i2];
                    if (!z) {
                        A03(file);
                    } else if (file.exists()) {
                        File file2 = r5.A03[i2];
                        file.renameTo(file2);
                        long[] jArr = r5.A06;
                        long j = jArr[i2];
                        long length = file2.length();
                        jArr[i2] = length;
                        r10.A01 = (r10.A01 - j) + length;
                    }
                }
                r10.A00++;
                r5.A01 = null;
                if (r5.A02 || z) {
                    r5.A02 = true;
                    r10.A02.append((CharSequence) "CLEAN");
                    r10.A02.append(' ');
                    r10.A02.append((CharSequence) r5.A05);
                    Writer writer = r10.A02;
                    StringBuilder sb = new StringBuilder();
                    long[] jArr2 = r5.A06;
                    for (long j2 : jArr2) {
                        sb.append(' ');
                        sb.append(j2);
                    }
                    writer.append((CharSequence) sb.toString());
                    r10.A02.append('\n');
                    if (z) {
                        long j3 = r10.A04;
                        r10.A04 = 1 + j3;
                        r5.A00 = j3;
                    }
                } else {
                    LinkedHashMap<String, AnonymousClass1nb> linkedHashMap = r10.A0A;
                    String str = r5.A05;
                    linkedHashMap.remove(str);
                    r10.A02.append((CharSequence) "REMOVE");
                    r10.A02.append(' ');
                    r10.A02.append((CharSequence) str);
                    r10.A02.append('\n');
                }
                A04(r10.A02);
                if (r10.A01 > r10.A03 || A06(r10)) {
                    r10.A0C.submit(r10.A0B);
                }
            } else {
                throw new IllegalStateException();
            }
        }
    }

    public final synchronized AnonymousClass1nj A07(String str) throws IOException {
        if (this.A02 != null) {
            AnonymousClass1nb r3 = this.A0A.get(str);
            if (r3 != null && r3.A02) {
                for (File file : r3.A03) {
                    if (file.exists()) {
                    }
                }
                this.A00++;
                this.A02.append((CharSequence) "READ");
                this.A02.append(' ');
                this.A02.append((CharSequence) str);
                this.A02.append('\n');
                if (A06(this)) {
                    this.A0C.submit(this.A0B);
                }
                return new AnonymousClass1nj(this, r3.A03, r3.A06);
            }
            return null;
        }
        throw new IllegalStateException("cache is closed");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final synchronized void close() throws IOException {
        if (this.A02 != null) {
            Iterator it = new ArrayList(this.A0A.values()).iterator();
            while (it.hasNext()) {
                AnonymousClass1ne r2 = ((AnonymousClass1nb) it.next()).A01;
                if (r2 != null) {
                    A02(r2.A03, r2, false);
                }
            }
            A00(this);
            A05(this.A02);
            this.A02 = null;
        }
    }

    public static void A00(C10521nZ r9) throws IOException {
        while (r9.A01 > r9.A03) {
            LinkedHashMap<String, AnonymousClass1nb> linkedHashMap = r9.A0A;
            String key = linkedHashMap.entrySet().iterator().next().getKey();
            synchronized (r9) {
                if (r9.A02 != null) {
                    AnonymousClass1nb r8 = linkedHashMap.get(key);
                    if (r8 != null) {
                        if (r8.A01 == null) {
                            for (int i = 0; i < r9.A06; i++) {
                                File file = r8.A03[i];
                                if (!file.exists() || file.delete()) {
                                    long j = r9.A01;
                                    long[] jArr = r8.A06;
                                    r9.A01 = j - jArr[i];
                                    jArr[i] = 0;
                                } else {
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("failed to delete ");
                                    sb.append(file);
                                    throw new IOException(sb.toString());
                                }
                            }
                            r9.A00++;
                            r9.A02.append((CharSequence) "REMOVE");
                            r9.A02.append(' ');
                            r9.A02.append((CharSequence) key);
                            r9.A02.append('\n');
                            linkedHashMap.remove(key);
                            if (A06(r9)) {
                                r9.A0C.submit(r9.A0B);
                            }
                        }
                    }
                } else {
                    throw new IllegalStateException("cache is closed");
                }
            }
        }
    }

    @TargetApi(26)
    public static void A04(Writer writer) throws IOException {
        if (Build.VERSION.SDK_INT < 26) {
            writer.flush();
            return;
        }
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitUnbufferedIo().build());
        try {
            writer.flush();
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    @TargetApi(26)
    public static void A05(Writer writer) throws IOException {
        if (Build.VERSION.SDK_INT < 26) {
            writer.close();
            return;
        }
        StrictMode.ThreadPolicy threadPolicy = StrictMode.getThreadPolicy();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(threadPolicy).permitUnbufferedIo().build());
        try {
            writer.close();
        } finally {
            StrictMode.setThreadPolicy(threadPolicy);
        }
    }

    public static boolean A06(C10521nZ r3) {
        int i = r3.A00;
        if (i < 2000 || i < r3.A0A.size()) {
            return false;
        }
        return true;
    }

    public static void A03(File file) throws IOException {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }
}
