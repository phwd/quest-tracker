package com.facebook.soloader;

import android.content.Context;
import android.os.Parcel;
import android.os.StrictMode;
import android.util.Log;
import java.io.Closeable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public abstract class UnpackingSoSource extends DirectorySoSource {
    @Nullable
    private String[] mAbis;
    protected final Context mContext;
    @Nullable
    protected String mCorruptedLib;
    private final Map<String, Object> mLibsBeingLoaded = new HashMap();

    /* access modifiers changed from: protected */
    public abstract Unpacker makeUnpacker() throws IOException;

    protected UnpackingSoSource(Context context, String name) {
        super(getSoStorePath(context, name), 1);
        this.mContext = context;
    }

    public static File getSoStorePath(Context context, String name) {
        return new File(context.getApplicationInfo().dataDir + "/" + name);
    }

    public void setSoSourceAbis(String[] abis) {
        this.mAbis = abis;
    }

    public static class Dso {
        public final String hash;
        public final String name;

        public Dso(String name2, String hash2) {
            this.name = name2;
            this.hash = hash2;
        }
    }

    public static final class DsoManifest {
        public final Dso[] dsos;

        public DsoManifest(Dso[] dsos2) {
            this.dsos = dsos2;
        }

        static final DsoManifest read(DataInput xdi) throws IOException {
            if (xdi.readByte() != 1) {
                throw new RuntimeException("wrong dso manifest version");
            }
            int nrDso = xdi.readInt();
            if (nrDso < 0) {
                throw new RuntimeException("illegal number of shared libraries");
            }
            Dso[] dsos2 = new Dso[nrDso];
            for (int i = 0; i < nrDso; i++) {
                dsos2[i] = new Dso(xdi.readUTF(), xdi.readUTF());
            }
            return new DsoManifest(dsos2);
        }

        public final void write(DataOutput xdo) throws IOException {
            xdo.writeByte(1);
            xdo.writeInt(this.dsos.length);
            for (int i = 0; i < this.dsos.length; i++) {
                xdo.writeUTF(this.dsos[i].name);
                xdo.writeUTF(this.dsos[i].hash);
            }
        }
    }

    /* access modifiers changed from: protected */
    public static final class InputDso implements Closeable {
        public final InputStream content;
        public final Dso dso;

        public InputDso(Dso dso2, InputStream content2) {
            this.dso = dso2;
            this.content = content2;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.content.close();
        }
    }

    /* access modifiers changed from: protected */
    public static abstract class InputDsoIterator implements Closeable {
        public abstract boolean hasNext();

        public abstract InputDso next() throws IOException;

        protected InputDsoIterator() {
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }
    }

    /* access modifiers changed from: protected */
    public static abstract class Unpacker implements Closeable {
        /* access modifiers changed from: protected */
        public abstract DsoManifest getDsoManifest() throws IOException;

        /* access modifiers changed from: protected */
        public abstract InputDsoIterator openDsoIterator() throws IOException;

        protected Unpacker() {
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
        }
    }

    /* access modifiers changed from: private */
    public static void writeState(File stateFileName, byte state) throws IOException {
        RandomAccessFile stateFile = new RandomAccessFile(stateFileName, "rw");
        try {
            stateFile.seek(0);
            stateFile.write(state);
            stateFile.setLength(stateFile.getFilePointer());
            stateFile.getFD().sync();
            stateFile.close();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    private void deleteUnmentionedFiles(Dso[] dsos) throws IOException {
        String[] existingFiles = this.soDirectory.list();
        if (existingFiles == null) {
            throw new IOException("unable to list directory " + this.soDirectory);
        }
        for (String fileName : existingFiles) {
            if (!fileName.equals("dso_state") && !fileName.equals("dso_lock") && !fileName.equals("dso_deps") && !fileName.equals("dso_manifest")) {
                boolean found = false;
                int j = 0;
                while (!found && j < dsos.length) {
                    if (dsos[j].name.equals(fileName)) {
                        found = true;
                    }
                    j++;
                }
                if (!found) {
                    File fileNameToDelete = new File(this.soDirectory, fileName);
                    Log.v("fb-UnpackingSoSource", "deleting unaccounted-for file " + fileNameToDelete);
                    SysUtil.dumbDeleteRecursive(fileNameToDelete);
                }
            }
        }
    }

    private void extractDso(InputDso iDso, byte[] ioBuffer) throws IOException {
        Log.i("fb-UnpackingSoSource", "extracting DSO " + iDso.dso.name);
        try {
            if (!this.soDirectory.setWritable(true)) {
                throw new IOException("cannot make directory writable for us: " + this.soDirectory);
            }
            extractDsoImpl(iDso, ioBuffer);
        } finally {
            if (!this.soDirectory.setWritable(false)) {
                Log.w("fb-UnpackingSoSource", "error removing " + this.soDirectory.getCanonicalPath() + " write permission");
            }
        }
    }

    private void extractDsoImpl(InputDso iDso, byte[] ioBuffer) throws IOException {
        RandomAccessFile dsoFile;
        File dsoFileName = new File(this.soDirectory, iDso.dso.name);
        RandomAccessFile dsoFile2 = null;
        try {
            if (dsoFileName.exists() && !dsoFileName.setWritable(true)) {
                Log.w("fb-UnpackingSoSource", "error adding write permission to: " + dsoFileName);
            }
            try {
                dsoFile = new RandomAccessFile(dsoFileName, "rw");
            } catch (IOException ex) {
                Log.w("fb-UnpackingSoSource", "error overwriting " + dsoFileName + " trying to delete and start over", ex);
                SysUtil.dumbDeleteRecursive(dsoFileName);
                dsoFile = new RandomAccessFile(dsoFileName, "rw");
            }
            int sizeHint = iDso.content.available();
            if (sizeHint > 1) {
                SysUtil.fallocateIfSupported(dsoFile.getFD(), (long) sizeHint);
            }
            SysUtil.copyBytes(dsoFile, iDso.content, Integer.MAX_VALUE, ioBuffer);
            dsoFile.setLength(dsoFile.getFilePointer());
            if (!dsoFileName.setExecutable(true, false)) {
                throw new IOException("cannot make file executable: " + dsoFileName);
            }
            if (!dsoFileName.setWritable(false)) {
                Log.w("fb-UnpackingSoSource", "error removing " + dsoFileName + " write permission");
            }
            if (dsoFile != null) {
                dsoFile.close();
            }
        } catch (IOException e) {
            SysUtil.dumbDeleteRecursive(dsoFileName);
            throw e;
        } catch (Throwable th) {
            if (!dsoFileName.setWritable(false)) {
                Log.w("fb-UnpackingSoSource", "error removing " + dsoFileName + " write permission");
            }
            if (0 != 0) {
                dsoFile2.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0053 A[Catch:{ Throwable -> 0x0099 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x003b A[SYNTHETIC, Splitter:B:6:0x003b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void regenerate(byte r13, com.facebook.soloader.UnpackingSoSource.DsoManifest r14, com.facebook.soloader.UnpackingSoSource.InputDsoIterator r15) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 217
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.soloader.UnpackingSoSource.regenerate(byte, com.facebook.soloader.UnpackingSoSource$DsoManifest, com.facebook.soloader.UnpackingSoSource$InputDsoIterator):void");
    }

    private boolean refreshLocked(final FileLocker lock, int flags, final byte[] deps) throws IOException {
        byte state;
        final File stateFileName = new File(this.soDirectory, "dso_state");
        RandomAccessFile stateFile = new RandomAccessFile(stateFileName, "rw");
        try {
            state = stateFile.readByte();
            if (state != 1) {
                Log.v("fb-UnpackingSoSource", "dso store " + this.soDirectory + " regeneration interrupted: wiping clean");
                state = 0;
            }
        } catch (EOFException e) {
            state = 0;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        stateFile.close();
        final File depsFileName = new File(this.soDirectory, "dso_deps");
        final DsoManifest desiredManifest = null;
        RandomAccessFile depsFile = new RandomAccessFile(depsFileName, "rw");
        try {
            byte[] existingDeps = new byte[((int) depsFile.length())];
            if (depsFile.read(existingDeps) != existingDeps.length) {
                Log.v("fb-UnpackingSoSource", "short read of so store deps file: marking unclean");
                state = 0;
            }
            if (!Arrays.equals(existingDeps, deps)) {
                Log.v("fb-UnpackingSoSource", "deps mismatch on deps store: regenerating");
                state = 0;
            }
            if (state == 0 || (flags & 2) != 0) {
                Log.v("fb-UnpackingSoSource", "so store dirty: regenerating");
                writeState(stateFileName, (byte) 0);
                Unpacker u = makeUnpacker();
                try {
                    desiredManifest = u.getDsoManifest();
                    InputDsoIterator idi = u.openDsoIterator();
                    try {
                        regenerate(state, desiredManifest, idi);
                        if (idi != null) {
                            idi.close();
                        }
                        if (u != null) {
                            u.close();
                        }
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
            }
            depsFile.close();
            if (desiredManifest == null) {
                return false;
            }
            Runnable syncer = new Runnable() {
                /* class com.facebook.soloader.UnpackingSoSource.AnonymousClass1 */

                public void run() {
                    try {
                        Log.v("fb-UnpackingSoSource", "starting syncer worker");
                        RandomAccessFile depsFile = new RandomAccessFile(depsFileName, "rw");
                        try {
                            depsFile.write(deps);
                            depsFile.setLength(depsFile.getFilePointer());
                            depsFile.close();
                            RandomAccessFile manifestFile = new RandomAccessFile(new File(UnpackingSoSource.this.soDirectory, "dso_manifest"), "rw");
                            try {
                                desiredManifest.write(manifestFile);
                                manifestFile.close();
                                SysUtil.fsyncRecursive(UnpackingSoSource.this.soDirectory);
                                UnpackingSoSource.writeState(stateFileName, (byte) 1);
                                try {
                                    Log.v("fb-UnpackingSoSource", "releasing dso store lock for " + UnpackingSoSource.this.soDirectory + " (from syncer thread)");
                                    lock.close();
                                    return;
                                } catch (IOException ex) {
                                    throw new RuntimeException(ex);
                                }
                            } catch (Throwable th) {
                                th.addSuppressed(th);
                            }
                            throw th;
                            throw th;
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    } catch (Throwable th3) {
                        Log.v("fb-UnpackingSoSource", "releasing dso store lock for " + UnpackingSoSource.this.soDirectory + " (from syncer thread)");
                        lock.close();
                        throw th3;
                    }
                }
            };
            if ((flags & 1) != 0) {
                new Thread(syncer, "SoSync:" + this.soDirectory.getName()).start();
            } else {
                syncer.run();
            }
            return true;
        } catch (Throwable th4) {
            th.addSuppressed(th4);
        }
        throw th;
        throw th;
        throw th;
        throw th;
    }

    /* access modifiers changed from: protected */
    public byte[] getDepsBlock() throws IOException {
        Parcel parcel = Parcel.obtain();
        Unpacker u = makeUnpacker();
        try {
            Dso[] dsos = u.getDsoManifest().dsos;
            parcel.writeByte((byte) 1);
            parcel.writeInt(dsos.length);
            for (int i = 0; i < dsos.length; i++) {
                parcel.writeString(dsos[i].name);
                parcel.writeString(dsos[i].hash);
            }
            if (u != null) {
                u.close();
            }
            byte[] depsBlock = parcel.marshall();
            parcel.recycle();
            return depsBlock;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* access modifiers changed from: protected */
    @Override // com.facebook.soloader.SoSource
    public void prepare(int flags) throws IOException {
        SysUtil.mkdirOrThrow(this.soDirectory);
        FileLocker lock = FileLocker.lock(new File(this.soDirectory, "dso_lock"));
        try {
            Log.v("fb-UnpackingSoSource", "locked dso store " + this.soDirectory);
            if (refreshLocked(lock, flags, getDepsBlock())) {
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
    }

    private Object getLibraryLock(String soName) {
        Object lock;
        synchronized (this.mLibsBeingLoaded) {
            lock = this.mLibsBeingLoaded.get(soName);
            if (lock == null) {
                lock = new Object();
                this.mLibsBeingLoaded.put(soName, lock);
            }
        }
        return lock;
    }

    /* access modifiers changed from: protected */
    public synchronized void prepare(String soName) throws IOException {
        synchronized (getLibraryLock(soName)) {
            this.mCorruptedLib = soName;
            prepare(2);
        }
    }

    @Override // com.facebook.soloader.DirectorySoSource, com.facebook.soloader.SoSource
    public int loadLibrary(String soName, int loadFlags, StrictMode.ThreadPolicy threadPolicy) throws IOException {
        int loadLibraryFrom;
        synchronized (getLibraryLock(soName)) {
            loadLibraryFrom = loadLibraryFrom(soName, loadFlags, this.soDirectory, threadPolicy);
        }
        return loadLibraryFrom;
    }
}
