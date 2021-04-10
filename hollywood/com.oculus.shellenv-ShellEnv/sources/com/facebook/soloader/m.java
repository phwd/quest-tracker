package com.facebook.soloader;

import android.content.Context;
import android.os.Parcel;
import android.os.StrictMode;
import android.util.Log;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

public abstract class m extends c {
    private final Map b = new HashMap();
    protected final Context d;
    protected String e;

    public static class a {
        public final String c;
        public final String d;

        public a(String str, String str2) {
            this.c = str;
            this.d = str2;
        }
    }

    public static final class b {
        public final a[] a;

        public b(a[] aVarArr) {
            this.a = aVarArr;
        }

        static final b a(DataInput dataInput) {
            if (dataInput.readByte() == 1) {
                int readInt = dataInput.readInt();
                if (readInt >= 0) {
                    a[] aVarArr = new a[readInt];
                    for (int i = 0; i < readInt; i++) {
                        aVarArr[i] = new a(dataInput.readUTF(), dataInput.readUTF());
                    }
                    return new b(aVarArr);
                }
                throw new RuntimeException("illegal number of shared libraries");
            }
            throw new RuntimeException("wrong dso manifest version");
        }

        public final void a(DataOutput dataOutput) {
            dataOutput.writeByte(1);
            dataOutput.writeInt(this.a.length);
            int i = 0;
            while (true) {
                a[] aVarArr = this.a;
                if (i < aVarArr.length) {
                    dataOutput.writeUTF(aVarArr[i].c);
                    dataOutput.writeUTF(this.a[i].d);
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public static final class c implements Closeable {
        public final a a;
        public final InputStream b;

        public c(a aVar, InputStream inputStream) {
            this.a = aVar;
            this.b = inputStream;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            this.b.close();
        }
    }

    public static abstract class d implements Closeable {
        protected d() {
        }

        public abstract boolean a();

        public abstract c b();

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }
    }

    public static abstract class e implements Closeable {
        protected e() {
        }

        /* access modifiers changed from: protected */
        public abstract b a();

        /* access modifiers changed from: protected */
        public abstract d b();

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }
    }

    protected m(Context context, String str) {
        super(new File(context.getApplicationInfo().dataDir + "/" + str), 1);
        this.d = context;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v14, resolved type: java.io.File */
    /* JADX DEBUG: Multi-variable search result rejected for r13v5, resolved type: java.io.File */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00de, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00df, code lost:
        android.util.Log.w("fb-UnpackingSoSource", "error overwriting " + r13 + " trying to delete and start over", r0);
        com.facebook.soloader.SysUtil.a(r13);
        r0 = new java.io.RandomAccessFile(r13, "rw");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0182, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0183, code lost:
        r5 = false;
        r14 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0195, code lost:
        android.util.Log.w("fb-UnpackingSoSource", "error removing " + r13 + " write permission");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01a9, code lost:
        r14.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0042 A[Catch:{ all -> 0x0034, all -> 0x020c }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x005c A[Catch:{ all -> 0x0034, all -> 0x020c }] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0182 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:36:0x00ba] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0195  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01a9  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(byte r17, com.facebook.soloader.m.b r18, com.facebook.soloader.m.d r19) {
        /*
        // Method dump skipped, instructions count: 527
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.m.a(byte, com.facebook.soloader.m$b, com.facebook.soloader.m$d):void");
    }

    private void a(a[] aVarArr) {
        String[] list = this.a.list();
        if (list != null) {
            for (String str : list) {
                if (!str.equals("dso_state") && !str.equals("dso_lock") && !str.equals("dso_deps") && !str.equals("dso_manifest")) {
                    boolean z = false;
                    int i = 0;
                    while (!z && i < aVarArr.length) {
                        if (aVarArr[i].c.equals(str)) {
                            z = true;
                        }
                        i++;
                    }
                    if (!z) {
                        File file = new File(this.a, str);
                        new StringBuilder("deleting unaccounted-for file ").append(file);
                        SysUtil.a(file);
                    }
                }
            }
            return;
        }
        throw new IOException("unable to list directory " + this.a);
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x007f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0080  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(com.facebook.soloader.f r11, int r12, byte[] r13) {
        /*
        // Method dump skipped, instructions count: 191
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.m.a(com.facebook.soloader.f, int, byte[]):boolean");
    }

    private Object b(String str) {
        Object obj;
        synchronized (this.b) {
            obj = this.b.get(str);
            if (obj == null) {
                obj = new Object();
                this.b.put(str, obj);
            }
        }
        return obj;
    }

    /* access modifiers changed from: private */
    public static void b(File file, byte b2) {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        try {
            randomAccessFile.seek(0);
            randomAccessFile.write(b2);
            randomAccessFile.setLength(randomAccessFile.getFilePointer());
            randomAccessFile.getFD().sync();
            randomAccessFile.close();
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }

    @Override // com.facebook.soloader.c, com.facebook.soloader.k
    public final int a(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        int a2;
        synchronized (b(str)) {
            a2 = a(str, i, this.a, threadPolicy);
        }
        return a2;
    }

    /* access modifiers changed from: protected */
    public abstract e a();

    /* access modifiers changed from: protected */
    @Override // com.facebook.soloader.k
    public final void a(int i) {
        StringBuilder sb;
        File file = this.a;
        if (file.mkdirs() || file.isDirectory()) {
            f a2 = f.a(new File(this.a, "dso_lock"));
            try {
                new StringBuilder("locked dso store ").append(this.a);
                if (a(a2, i, b())) {
                    a2 = null;
                } else {
                    Log.i("fb-UnpackingSoSource", "dso store is up-to-date: " + this.a);
                }
                if (a2 != null) {
                    sb.append(this.a);
                    a2.close();
                    return;
                }
                sb = new StringBuilder("not releasing dso store lock for ");
                sb.append(this.a);
                sb.append(" (syncer thread started)");
            } catch (Throwable th) {
                if (a2 != null) {
                    new StringBuilder("releasing dso store lock for ").append(this.a);
                    a2.close();
                } else {
                    StringBuilder sb2 = new StringBuilder("not releasing dso store lock for ");
                    sb2.append(this.a);
                    sb2.append(" (syncer thread started)");
                }
                throw th;
            }
        } else {
            throw new IOException("cannot mkdir: " + file);
        }
    }

    /* access modifiers changed from: protected */
    public final synchronized void a(String str) {
        synchronized (b(str)) {
            this.e = str;
            a(2);
        }
    }

    /* access modifiers changed from: protected */
    public byte[] b() {
        Parcel obtain = Parcel.obtain();
        e a2 = a();
        try {
            a[] aVarArr = a2.a().a;
            obtain.writeByte((byte) 1);
            obtain.writeInt(aVarArr.length);
            for (int i = 0; i < aVarArr.length; i++) {
                obtain.writeString(aVarArr[i].c);
                obtain.writeString(aVarArr[i].d);
            }
            a2.close();
            byte[] marshall = obtain.marshall();
            obtain.recycle();
            return marshall;
        } catch (Throwable unused) {
        }
        throw th;
    }
}
