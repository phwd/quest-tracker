package com.facebook.soloader;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import com.facebook.imagepipeline.common.BytesRange;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public abstract class UnpackingSoSource extends DirectorySoSource {
    private static final String DEPS_FILE_NAME = "dso_deps";
    private static final String LOCK_FILE_NAME = "dso_lock";
    private static final String MANIFEST_FILE_NAME = "dso_manifest";
    private static final byte MANIFEST_VERSION = 1;
    private static final byte STATE_CLEAN = 1;
    private static final byte STATE_DIRTY = 0;
    private static final String STATE_FILE_NAME = "dso_state";
    private static final String TAG = "fb-UnpackingSoSource";
    @Nullable
    private String[] mAbis;
    protected final Context mContext;
    @Nullable
    protected String mCorruptedLib;
    private final Map<String, Object> mLibsBeingLoaded = new HashMap();

    /* access modifiers changed from: protected */
    public abstract Unpacker makeUnpacker() throws IOException;

    protected UnpackingSoSource(Context context, String str) {
        super(getSoStorePath(context, str), 1);
        this.mContext = context;
    }

    protected UnpackingSoSource(Context context, File file) {
        super(file, 1);
        this.mContext = context;
    }

    public static File getSoStorePath(Context context, String str) {
        return new File(context.getApplicationInfo().dataDir + "/" + str);
    }

    @Override // com.facebook.soloader.SoSource
    public String[] getSoSourceAbis() {
        String[] strArr = this.mAbis;
        return strArr == null ? super.getSoSourceAbis() : strArr;
    }

    public void setSoSourceAbis(String[] strArr) {
        this.mAbis = strArr;
    }

    public static class Dso {
        public final String hash;
        public final String name;

        public Dso(String str, String str2) {
            this.name = str;
            this.hash = str2;
        }
    }

    public static final class DsoManifest {
        public final Dso[] dsos;

        public DsoManifest(Dso[] dsoArr) {
            this.dsos = dsoArr;
        }

        static final DsoManifest read(DataInput dataInput) throws IOException {
            if (dataInput.readByte() == 1) {
                int readInt = dataInput.readInt();
                if (readInt >= 0) {
                    Dso[] dsoArr = new Dso[readInt];
                    for (int i = 0; i < readInt; i++) {
                        dsoArr[i] = new Dso(dataInput.readUTF(), dataInput.readUTF());
                    }
                    return new DsoManifest(dsoArr);
                }
                throw new RuntimeException("illegal number of shared libraries");
            }
            throw new RuntimeException("wrong dso manifest version");
        }

        public final void write(DataOutput dataOutput) throws IOException {
            dataOutput.writeByte(1);
            dataOutput.writeInt(this.dsos.length);
            int i = 0;
            while (true) {
                Dso[] dsoArr = this.dsos;
                if (i < dsoArr.length) {
                    dataOutput.writeUTF(dsoArr[i].name);
                    dataOutput.writeUTF(this.dsos[i].hash);
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public static final class InputDso implements Closeable {
        public final InputStream content;
        public final Dso dso;

        public InputDso(Dso dso2, InputStream inputStream) {
            this.dso = dso2;
            this.content = inputStream;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.content.close();
        }
    }

    /* access modifiers changed from: protected */
    public static abstract class InputDsoIterator implements Closeable {
        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        public abstract boolean hasNext();

        public abstract InputDso next() throws IOException;

        protected InputDsoIterator() {
        }
    }

    /* access modifiers changed from: protected */
    public static abstract class Unpacker implements Closeable {
        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }

        /* access modifiers changed from: protected */
        public abstract DsoManifest getDsoManifest() throws IOException;

        /* access modifiers changed from: protected */
        public abstract InputDsoIterator openDsoIterator() throws IOException;

        protected Unpacker() {
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r3.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0022, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0026, code lost:
        if (r3 != null) goto L_0x0028;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void writeState(java.io.File r3, byte r4) throws java.io.IOException {
        /*
            java.io.RandomAccessFile r0 = new java.io.RandomAccessFile
            java.lang.String r1 = "rw"
            r0.<init>(r3, r1)
            r1 = 0
            r3 = 0
            r0.seek(r1)     // Catch:{ Throwable -> 0x0024 }
            r0.write(r4)     // Catch:{ Throwable -> 0x0024 }
            long r1 = r0.getFilePointer()     // Catch:{ Throwable -> 0x0024 }
            r0.setLength(r1)     // Catch:{ Throwable -> 0x0024 }
            java.io.FileDescriptor r4 = r0.getFD()     // Catch:{ Throwable -> 0x0024 }
            r4.sync()     // Catch:{ Throwable -> 0x0024 }
            r0.close()
            return
        L_0x0022:
            r4 = move-exception
            goto L_0x0026
        L_0x0024:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0022 }
        L_0x0026:
            if (r3 == 0) goto L_0x0031
            r0.close()     // Catch:{ Throwable -> 0x002c }
            goto L_0x0034
        L_0x002c:
            r0 = move-exception
            r3.addSuppressed(r0)
            goto L_0x0034
        L_0x0031:
            r0.close()
        L_0x0034:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.UnpackingSoSource.writeState(java.io.File, byte):void");
    }

    private void deleteUnmentionedFiles(Dso[] dsoArr) throws IOException {
        String[] list = this.soDirectory.list();
        if (list != null) {
            for (String str : list) {
                if (!str.equals(STATE_FILE_NAME) && !str.equals(LOCK_FILE_NAME) && !str.equals(DEPS_FILE_NAME) && !str.equals(MANIFEST_FILE_NAME)) {
                    boolean z = false;
                    int i = 0;
                    while (!z && i < dsoArr.length) {
                        if (dsoArr[i].name.equals(str)) {
                            z = true;
                        }
                        i++;
                    }
                    if (!z) {
                        File file = new File(this.soDirectory, str);
                        Log.v(TAG, "deleting unaccounted-for file " + file);
                        SysUtil.dumbDeleteRecursive(file);
                    }
                }
            }
            return;
        }
        throw new IOException("unable to list directory " + this.soDirectory);
    }

    private void extractDso(InputDso inputDso, byte[] bArr) throws IOException {
        RandomAccessFile randomAccessFile;
        Log.i(TAG, "extracting DSO " + inputDso.dso.name);
        if (this.soDirectory.setWritable(true, true)) {
            File file = new File(this.soDirectory, inputDso.dso.name);
            try {
                randomAccessFile = new RandomAccessFile(file, "rw");
            } catch (IOException e) {
                Log.w(TAG, "error overwriting " + file + " trying to delete and start over", e);
                SysUtil.dumbDeleteRecursive(file);
                randomAccessFile = new RandomAccessFile(file, "rw");
            }
            try {
                int available = inputDso.content.available();
                if (available > 1) {
                    SysUtil.fallocateIfSupported(randomAccessFile.getFD(), (long) available);
                }
                SysUtil.copyBytes(randomAccessFile, inputDso.content, BytesRange.TO_END_OF_CONTENT, bArr);
                randomAccessFile.setLength(randomAccessFile.getFilePointer());
                if (file.setExecutable(true, false)) {
                    randomAccessFile.close();
                    return;
                }
                throw new IOException("cannot make file executable: " + file);
            } catch (IOException e2) {
                SysUtil.dumbDeleteRecursive(file);
                throw e2;
            } catch (Throwable th) {
                randomAccessFile.close();
                throw th;
            }
        } else {
            throw new IOException("cannot make directory writable for us: " + this.soDirectory);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0037, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00d8, code lost:
        if (r12 != null) goto L_0x00da;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00de, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00df, code lost:
        r12.addSuppressed(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00e3, code lost:
        r2.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0048 A[Catch:{ all -> 0x0037, Throwable -> 0x00de }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005f A[Catch:{ all -> 0x0037, Throwable -> 0x00de }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void regenerate(byte r12, com.facebook.soloader.UnpackingSoSource.DsoManifest r13, com.facebook.soloader.UnpackingSoSource.InputDsoIterator r14) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 231
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.UnpackingSoSource.regenerate(byte, com.facebook.soloader.UnpackingSoSource$DsoManifest, com.facebook.soloader.UnpackingSoSource$InputDsoIterator):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003d, code lost:
        if (r13 != null) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0043, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0044, code lost:
        r13.addSuppressed(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0048, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00e0, code lost:
        r13 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00e1, code lost:
        r14 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00e5, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00e6, code lost:
        r14 = r13;
        r13 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00fa, code lost:
        r13 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x00fb, code lost:
        r14 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00ff, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x0100, code lost:
        r14 = r13;
        r13 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0038, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0114, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0119, code lost:
        if (r13 != null) goto L_0x011b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x011f, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0120, code lost:
        r13.addSuppressed(r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0124, code lost:
        r1.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00ad A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00fa A[ExcHandler: all (th java.lang.Throwable)] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean refreshLocked(final com.facebook.soloader.FileLocker r13, int r14, final byte[] r15) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 296
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.UnpackingSoSource.refreshLocked(com.facebook.soloader.FileLocker, int, byte[]):boolean");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0039, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003e, code lost:
        if (r1 != null) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0040, code lost:
        if (r0 != null) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0046, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0047, code lost:
        r0.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004b, code lost:
        r1.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getDepsBlock() throws java.io.IOException {
        /*
            r6 = this;
            android.os.Parcel r0 = android.os.Parcel.obtain()
            com.facebook.soloader.UnpackingSoSource$Unpacker r1 = r6.makeUnpacker()
            r2 = 0
            com.facebook.soloader.UnpackingSoSource$DsoManifest r3 = r1.getDsoManifest()     // Catch:{ Throwable -> 0x003b }
            com.facebook.soloader.UnpackingSoSource$Dso[] r3 = r3.dsos     // Catch:{ Throwable -> 0x003b }
            r4 = 1
            r0.writeByte(r4)     // Catch:{ Throwable -> 0x003b }
            int r4 = r3.length     // Catch:{ Throwable -> 0x003b }
            r0.writeInt(r4)     // Catch:{ Throwable -> 0x003b }
            r4 = 0
        L_0x0018:
            int r5 = r3.length     // Catch:{ Throwable -> 0x003b }
            if (r4 >= r5) goto L_0x002c
            r5 = r3[r4]     // Catch:{ Throwable -> 0x003b }
            java.lang.String r5 = r5.name     // Catch:{ Throwable -> 0x003b }
            r0.writeString(r5)     // Catch:{ Throwable -> 0x003b }
            r5 = r3[r4]     // Catch:{ Throwable -> 0x003b }
            java.lang.String r5 = r5.hash     // Catch:{ Throwable -> 0x003b }
            r0.writeString(r5)     // Catch:{ Throwable -> 0x003b }
            int r4 = r4 + 1
            goto L_0x0018
        L_0x002c:
            if (r1 == 0) goto L_0x0031
            r1.close()
        L_0x0031:
            byte[] r1 = r0.marshall()
            r0.recycle()
            return r1
        L_0x0039:
            r0 = move-exception
            goto L_0x003e
        L_0x003b:
            r0 = move-exception
            r2 = r0
            throw r2     // Catch:{ all -> 0x0039 }
        L_0x003e:
            if (r1 == 0) goto L_0x004e
            if (r2 == 0) goto L_0x004b
            r1.close()     // Catch:{ Throwable -> 0x0046 }
            goto L_0x004e
        L_0x0046:
            r1 = move-exception
            r2.addSuppressed(r1)
            goto L_0x004e
        L_0x004b:
            r1.close()
        L_0x004e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.UnpackingSoSource.getDepsBlock():byte[]");
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.soloader.SoSource
    public void prepare(int i) throws IOException {
        SysUtil.mkdirOrThrow(this.soDirectory);
        FileLocker lock = FileLocker.lock(new File(this.soDirectory, LOCK_FILE_NAME));
        try {
            Log.v(TAG, "locked dso store " + this.soDirectory);
            if (refreshLocked(lock, i, getDepsBlock())) {
                lock = null;
            } else {
                Log.i(TAG, "dso store is up-to-date: " + this.soDirectory);
            }
            if (lock == null) {
                Log.v(TAG, "not releasing dso store lock for " + this.soDirectory + " (syncer thread started)");
            }
        } finally {
            if (lock != null) {
                Log.v(TAG, "releasing dso store lock for " + this.soDirectory);
                lock.close();
            } else {
                Log.v(TAG, "not releasing dso store lock for " + this.soDirectory + " (syncer thread started)");
            }
        }
    }

    private Object getLibraryLock(String str) {
        Object obj;
        synchronized (this.mLibsBeingLoaded) {
            obj = this.mLibsBeingLoaded.get(str);
            if (obj == null) {
                obj = new Object();
                this.mLibsBeingLoaded.put(str, obj);
            }
        }
        return obj;
    }

    /* access modifiers changed from: protected */
    public synchronized void prepare(String str) throws IOException {
        synchronized (getLibraryLock(str)) {
            this.mCorruptedLib = str;
            prepare(2);
        }
    }

    @Override // com.facebook.soloader.DirectorySoSource, com.facebook.soloader.SoSource
    public int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        int loadLibraryFrom;
        synchronized (getLibraryLock(str)) {
            loadLibraryFrom = loadLibraryFrom(str, i, this.soDirectory, threadPolicy);
        }
        return loadLibraryFrom;
    }
}
