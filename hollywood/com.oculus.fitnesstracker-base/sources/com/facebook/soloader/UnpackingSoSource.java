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

public abstract class UnpackingSoSource extends DirectorySoSource {
    String[] mAbis;
    protected final Context mContext;
    protected String mCorruptedLib;
    private final Map<String, Object> mLibsBeingLoaded = new HashMap();

    /* access modifiers changed from: protected */
    public interface InputDso extends Closeable {
        int available() throws IOException;

        Dso getDso();

        String getFileName();

        void write(DataOutput dataOutput, byte[] bArr) throws IOException;
    }

    /* access modifiers changed from: protected */
    public abstract Unpacker makeUnpacker() throws IOException;

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

    public static class InputDsoStream implements InputDso {
        private final InputStream content;
        private final Dso dso;

        public InputDsoStream(Dso dso2, InputStream inputStream) {
            this.dso = dso2;
            this.content = inputStream;
        }

        @Override // com.facebook.soloader.UnpackingSoSource.InputDso
        public final void write(DataOutput dataOutput, byte[] bArr) throws IOException {
            int read;
            InputStream inputStream = this.content;
            int i = 0;
            while (i < Integer.MAX_VALUE && (read = inputStream.read(bArr, 0, Math.min(bArr.length, Integer.MAX_VALUE - i))) != -1) {
                dataOutput.write(bArr, 0, read);
                i += read;
            }
        }

        @Override // com.facebook.soloader.UnpackingSoSource.InputDso
        public final Dso getDso() {
            return this.dso;
        }

        @Override // com.facebook.soloader.UnpackingSoSource.InputDso
        public final String getFileName() {
            return this.dso.name;
        }

        @Override // com.facebook.soloader.UnpackingSoSource.InputDso
        public final int available() throws IOException {
            return this.content.available();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() throws IOException {
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

    static void writeState(File file, byte b) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        try {
            randomAccessFile.seek(0);
            randomAccessFile.write(b);
            randomAccessFile.setLength(randomAccessFile.getFilePointer());
            randomAccessFile.getFD().sync();
            randomAccessFile.close();
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }

    private void deleteUnmentionedFiles(Dso[] dsoArr) throws IOException {
        String[] list = this.soDirectory.list();
        if (list != null) {
            for (String str : list) {
                if (!str.equals("dso_state") && !str.equals("dso_lock") && !str.equals("dso_deps") && !str.equals("dso_manifest")) {
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
                        Log.v("fb-UnpackingSoSource", "deleting unaccounted-for file " + file);
                        SysUtil.dumbDeleteRecursive(file);
                    }
                }
            }
            return;
        }
        throw new IOException("unable to list directory " + this.soDirectory);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:105:0x01fb */
    /* JADX DEBUG: Multi-variable search result rejected for r0v17, resolved type: java.io.File */
    /* JADX DEBUG: Multi-variable search result rejected for r13v7, resolved type: java.io.File */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0197, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0198, code lost:
        r6 = false;
        r14 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x019b, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x019c, code lost:
        r7 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0049 A[Catch:{ all -> 0x003b, all -> 0x0227 }] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0063 A[Catch:{ all -> 0x003b, all -> 0x0227 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0197 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:37:0x00d6] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01aa  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x01be  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void regenerate(byte r17, com.facebook.soloader.UnpackingSoSource.DsoManifest r18, com.facebook.soloader.UnpackingSoSource.InputDsoIterator r19) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 554
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.UnpackingSoSource.regenerate(byte, com.facebook.soloader.UnpackingSoSource$DsoManifest, com.facebook.soloader.UnpackingSoSource$InputDsoIterator):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x0097 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0098  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean refreshLocked(final com.facebook.soloader.FileLocker r12, int r13, final byte[] r14) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 217
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.UnpackingSoSource.refreshLocked(com.facebook.soloader.FileLocker, int, byte[]):boolean");
    }

    /* access modifiers changed from: protected */
    public byte[] getDepsBlock() throws IOException {
        Parcel obtain = Parcel.obtain();
        Unpacker makeUnpacker = makeUnpacker();
        try {
            Dso[] dsoArr = makeUnpacker.getDsoManifest().dsos;
            obtain.writeByte((byte) 1);
            obtain.writeInt(dsoArr.length);
            for (int i = 0; i < dsoArr.length; i++) {
                obtain.writeString(dsoArr[i].name);
                obtain.writeString(dsoArr[i].hash);
            }
            makeUnpacker.close();
            byte[] marshall = obtain.marshall();
            obtain.recycle();
            return marshall;
        } catch (Throwable unused) {
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.soloader.SoSource
    public final void prepare(int i) throws IOException {
        File file = this.soDirectory;
        if (file.mkdirs() || file.isDirectory()) {
            FileLocker lock = FileLocker.lock(new File(this.soDirectory, "dso_lock"));
            try {
                Log.v("fb-UnpackingSoSource", "locked dso store " + this.soDirectory);
                if (refreshLocked(lock, i, getDepsBlock())) {
                    lock = null;
                } else {
                    Log.i("fb-UnpackingSoSource", "dso store is up-to-date: " + this.soDirectory);
                }
                if (lock == null) {
                    Log.v("fb-UnpackingSoSource", "not releasing dso store lock for " + this.soDirectory + " (syncer thread started)");
                }
            } finally {
                if (lock != null) {
                    Log.v("fb-UnpackingSoSource", "releasing dso store lock for " + this.soDirectory);
                    lock.close();
                } else {
                    Log.v("fb-UnpackingSoSource", "not releasing dso store lock for " + this.soDirectory + " (syncer thread started)");
                }
            }
        } else {
            throw new IOException("cannot mkdir: " + file);
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
    public final synchronized void prepare(String str) throws IOException {
        synchronized (getLibraryLock(str)) {
            this.mCorruptedLib = str;
            prepare(2);
        }
    }

    @Override // com.facebook.soloader.DirectorySoSource, com.facebook.soloader.SoSource
    public final int loadLibrary(String str, int i, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        int loadLibraryFrom;
        synchronized (getLibraryLock(str)) {
            loadLibraryFrom = loadLibraryFrom(str, i, this.soDirectory, threadPolicy);
        }
        return loadLibraryFrom;
    }

    protected UnpackingSoSource(Context context, String str) {
        super(new File(context.getApplicationInfo().dataDir + "/" + str), 1);
        this.mContext = context;
    }
}
