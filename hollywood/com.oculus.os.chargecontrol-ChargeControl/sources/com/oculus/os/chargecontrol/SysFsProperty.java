package com.oculus.os.chargecontrol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

public class SysFsProperty {
    private final ConcurrentHashMap<String, File> keys_;
    private final File parentPath_;

    public static class Value<T> {
        private final IOException exception_;
        private final T value_;

        public T get() throws IOException {
            T t = this.value_;
            if (t != null) {
                return t;
            }
            throw this.exception_;
        }

        private Value(T t) {
            this.value_ = t;
            this.exception_ = null;
        }

        private Value(IOException iOException) {
            this.value_ = null;
            this.exception_ = iOException;
        }
    }

    public SysFsProperty(File file) {
        this.keys_ = new ConcurrentHashMap<>();
        this.parentPath_ = file;
    }

    public SysFsProperty(String str) {
        this(new File(str));
    }

    private static /* synthetic */ void $closeResource(Throwable th, AutoCloseable autoCloseable) {
        if (th != null) {
            try {
                autoCloseable.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            autoCloseable.close();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001f, code lost:
        if (r3 != null) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
        $closeResource(r4, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0024, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.oculus.os.chargecontrol.SysFsProperty.Value<java.lang.Long> getLong(java.lang.String r4) {
        /*
            r3 = this;
            r0 = 0
            java.io.BufferedReader r3 = r3.readerFor(r4)     // Catch:{ NumberFormatException -> 0x002c, IOException -> 0x0025 }
            com.oculus.os.chargecontrol.SysFsProperty$Value r4 = new com.oculus.os.chargecontrol.SysFsProperty$Value     // Catch:{ all -> 0x001c }
            java.lang.String r1 = r3.readLine()     // Catch:{ all -> 0x001c }
            long r1 = java.lang.Long.parseLong(r1)     // Catch:{ all -> 0x001c }
            java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x001c }
            r4.<init>(r1)     // Catch:{ all -> 0x001c }
            if (r3 == 0) goto L_0x001b
            $closeResource(r0, r3)
        L_0x001b:
            return r4
        L_0x001c:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x001e }
        L_0x001e:
            r1 = move-exception
            if (r3 == 0) goto L_0x0024
            $closeResource(r4, r3)
        L_0x0024:
            throw r1
        L_0x0025:
            r3 = move-exception
            com.oculus.os.chargecontrol.SysFsProperty$Value r4 = new com.oculus.os.chargecontrol.SysFsProperty$Value
            r4.<init>(r3)
            return r4
        L_0x002c:
            r3 = move-exception
            com.oculus.os.chargecontrol.SysFsProperty$Value r4 = new com.oculus.os.chargecontrol.SysFsProperty$Value
            java.io.IOException r1 = new java.io.IOException
            java.lang.String r2 = "parse error"
            r1.<init>(r2, r3)
            r4.<init>(r1)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.os.chargecontrol.SysFsProperty.getLong(java.lang.String):com.oculus.os.chargecontrol.SysFsProperty$Value");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
        $closeResource(r3, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0022, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001d, code lost:
        if (r2 != null) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void putLong(java.lang.String r3, long r4) throws java.io.IOException {
        /*
            r2 = this;
            java.io.PrintWriter r2 = r2.writerFor(r3)
            java.lang.String r3 = "%d"
            r0 = 1
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch:{ all -> 0x001a }
            r1 = 0
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x001a }
            r0[r1] = r4     // Catch:{ all -> 0x001a }
            r2.printf(r3, r0)     // Catch:{ all -> 0x001a }
            if (r2 == 0) goto L_0x0019
            r3 = 0
            $closeResource(r3, r2)
        L_0x0019:
            return
        L_0x001a:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x001c }
        L_0x001c:
            r4 = move-exception
            if (r2 == 0) goto L_0x0022
            $closeResource(r3, r2)
        L_0x0022:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.os.chargecontrol.SysFsProperty.putLong(java.lang.String, long):void");
    }

    private BufferedReader readerFor(String str) throws IOException {
        return new BufferedReader(new FileReader(cachedPath(str)));
    }

    private PrintWriter writerFor(String str) throws IOException {
        return new PrintWriter(cachedPath(str), "UTF-8");
    }

    private File cachedPath(String str) {
        if (str == null) {
            throw new NullPointerException();
        } else if (str.startsWith("/") || str.indexOf("..") != -1) {
            throw new IllegalArgumentException("paths can not reference parents");
        } else {
            File file = this.keys_.get(str);
            if (file != null) {
                return file;
            }
            File file2 = new File(this.parentPath_, str);
            File putIfAbsent = this.keys_.putIfAbsent(str, file2);
            return putIfAbsent == null ? file2 : putIfAbsent;
        }
    }
}
