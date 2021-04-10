package com.facebook.acra;

import java.io.Closeable;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.OverlappingFileLockException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;

public final class Spool {
    private static final String TAG = "Spool";
    private final File mDirectoryName;
    final HashSet<File> mHazardList = new HashSet<>();

    public Spool(File file) {
        this.mDirectoryName = file;
    }

    @Nullable
    public FileBeingProduced produce(String str) throws IOException {
        return produceWithDonorFile(str, null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:101:0x00dd, code lost:
        r3 = new com.facebook.acra.Spool.FileBeingProduced(r8, r1, r10);
        closeSilently(null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x00e5, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x00e6, code lost:
        r10 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x00e7, code lost:
        r0 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x00e8, code lost:
        r9 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x00ea, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x00eb, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0038, code lost:
        if (r1.exists() == false) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003a, code lost:
        closeSilently(null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003d, code lost:
        monitor-enter(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r8.mHazardList.remove(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0043, code lost:
        monitor-exit(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0044, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0048, code lost:
        if (r10 == null) goto L_0x0095;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x004e, code lost:
        if (r10.exists() == false) goto L_0x0095;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0050, code lost:
        r9 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r3 = new java.io.RandomAccessFile(r10, "rw");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x005d, code lost:
        if (tryLock(r3) == false) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0063, code lost:
        if (r10.renameTo(r1) == false) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0065, code lost:
        r9 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0066, code lost:
        if (r9 != false) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        closeSilently(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x006c, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x006d, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0070, code lost:
        r9 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0072, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0074, code lost:
        r4 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0076, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0077, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x007a, code lost:
        r4 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x007b, code lost:
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        com.facebook.debug.log.BLog.w(com.facebook.acra.Spool.TAG, r4, "error using donor file %s; falling back to regular path", r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        closeSilently(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x008c, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x008d, code lost:
        closeSilently(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0090, code lost:
        throw r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0091, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0092, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0095, code lost:
        r9 = null;
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0097, code lost:
        if (r3 != null) goto L_0x00c1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        r10 = new java.io.RandomAccessFile(r1, "rw");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00a4, code lost:
        if (tryLock(r10) == false) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00a6, code lost:
        r1.delete();
        closeSilently(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00ac, code lost:
        monitor-enter(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:?, code lost:
        r8.mHazardList.remove(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x00b3, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x00b7, code lost:
        r2 = r10;
        r9 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x00ba, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x00bb, code lost:
        r2 = r10;
        r0 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x00be, code lost:
        r10 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x00bf, code lost:
        r2 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x00c1, code lost:
        r2 = r9;
        r9 = null;
        r10 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x00c8, code lost:
        if (r1.exists() != false) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x00ca, code lost:
        if (r9 != null) goto L_0x00cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x00cc, code lost:
        r9.delete();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x00cf, code lost:
        closeSilently(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x00d2, code lost:
        monitor-enter(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:?, code lost:
        r8.mHazardList.remove(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x00d9, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0099 A[SYNTHETIC, Splitter:B:67:0x0099] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x00ca  */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.acra.Spool.FileBeingProduced produceWithDonorFile(java.lang.String r9, @javax.annotation.Nullable java.io.File r10) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 288
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.Spool.produceWithDonorFile(java.lang.String, java.io.File):com.facebook.acra.Spool$FileBeingProduced");
    }

    public Snapshot snapshot(@Nullable Comparator<Descriptor> comparator, @Nullable FilenameFilter filenameFilter) {
        String[] list = this.mDirectoryName.list(filenameFilter);
        if (list == null) {
            list = new String[0];
        }
        int length = list.length;
        Descriptor[] descriptorArr = new Descriptor[length];
        for (int i = 0; i < length; i++) {
            String str = list[i];
            File file = new File(this.mDirectoryName, str);
            descriptorArr[i] = new Descriptor(str, file.lastModified(), file);
        }
        if (comparator != null) {
            Arrays.sort(descriptorArr, comparator);
        }
        return new Snapshot(descriptorArr);
    }

    public final class FileBeingProduced implements Closeable {
        public final RandomAccessFile file;
        public final File fileName;

        FileBeingProduced(File file2, RandomAccessFile randomAccessFile) {
            this.fileName = file2;
            this.file = randomAccessFile;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (Spool.this) {
                Spool.this.mHazardList.remove(this.fileName);
            }
            this.file.close();
        }
    }

    public final class FileBeingConsumed implements Closeable {
        public final RandomAccessFile file;
        public final File fileName;

        FileBeingConsumed(File file2, RandomAccessFile randomAccessFile) {
            this.fileName = file2;
            this.file = randomAccessFile;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            synchronized (Spool.this) {
                Spool.this.mHazardList.remove(this.fileName);
            }
            Spool.this.closeSilently(this.file);
        }
    }

    public final class Snapshot implements Closeable, Iterator<FileBeingConsumed> {
        private FileBeingConsumed mCurrent = null;
        private final Descriptor[] mDescriptors;
        private int mPosition = 0;

        Snapshot(Descriptor[] descriptorArr) {
            this.mDescriptors = descriptorArr;
        }

        public int getEstimate() {
            return this.mDescriptors.length;
        }

        @Override // java.util.Iterator
        public FileBeingConsumed next() {
            updateCurrent();
            FileBeingConsumed fileBeingConsumed = this.mCurrent;
            if (fileBeingConsumed != null) {
                this.mCurrent = null;
                return fileBeingConsumed;
            }
            throw new NoSuchElementException();
        }

        public boolean hasNext() {
            updateCurrent();
            return this.mCurrent != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void updateCurrent() {
            while (this.mCurrent == null) {
                int i = this.mPosition;
                Descriptor[] descriptorArr = this.mDescriptors;
                if (i < descriptorArr.length) {
                    this.mPosition = i + 1;
                    this.mCurrent = tryOpenFileForConsumption(descriptorArr[i]);
                } else {
                    return;
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
            r3 = new java.io.RandomAccessFile(r10, "rw");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x002e, code lost:
            if (r9.this$0.tryLock(r3) != false) goto L_0x0044;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0030, code lost:
            r9.this$0.closeSilently(r3);
            r4 = r9.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0037, code lost:
            monitor-enter(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            r9.this$0.mHazardList.remove(r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x003f, code lost:
            monitor-exit(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0040, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0048, code lost:
            if (r10.exists() != false) goto L_0x005e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x004a, code lost:
            r9.this$0.closeSilently(r3);
            r4 = r9.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0051, code lost:
            monitor-enter(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
            r9.this$0.mHazardList.remove(r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0059, code lost:
            monitor-exit(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x005a, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0066, code lost:
            if (r3.length() != 0) goto L_0x007f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0068, code lost:
            r10.delete();
            r9.this$0.closeSilently(r3);
            r1 = r9.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x0072, code lost:
            monitor-enter(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
            r9.this$0.mHazardList.remove(r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x007a, code lost:
            monitor-exit(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x007b, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x007f, code lost:
            r4 = new com.facebook.acra.Spool.FileBeingConsumed(r9.this$0, r10, r3);
            r9.this$0.closeSilently(null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x008b, code lost:
            return r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x008c, code lost:
            r1 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x008e, code lost:
            r4 = e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x0090, code lost:
            r1 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x0091, code lost:
            r3 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x0093, code lost:
            r4 = e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:0x0094, code lost:
            r3 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x0095, code lost:
            r5 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:66:0x009e, code lost:
            r1 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:67:0x009f, code lost:
            r3 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:68:0x00a1, code lost:
            r4 = e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:69:0x00a2, code lost:
            r3 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:81:0x00c9, code lost:
            monitor-enter(r9.this$0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:83:?, code lost:
            r9.this$0.mHazardList.remove(r10);
         */
        /* JADX WARNING: Removed duplicated region for block: B:80:0x00c7  */
        /* JADX WARNING: Removed duplicated region for block: B:94:0x00e0  */
        @javax.annotation.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private com.facebook.acra.Spool.FileBeingConsumed tryOpenFileForConsumption(com.facebook.acra.Spool.Descriptor r10) {
            /*
            // Method dump skipped, instructions count: 242
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.Spool.Snapshot.tryOpenFileForConsumption(com.facebook.acra.Spool$Descriptor):com.facebook.acra.Spool$FileBeingConsumed");
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            Spool.this.closeSilently(this.mCurrent);
        }
    }

    public static final class Descriptor {
        public final String fileBaseName;
        final File fileName;
        public final long lastModifiedTime;

        public Descriptor(String str, long j, File file) {
            this.fileBaseName = str;
            this.lastModifiedTime = j;
            this.fileName = file;
        }
    }

    /* access modifiers changed from: package-private */
    public void closeSilently(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean tryLock(RandomAccessFile randomAccessFile) throws IOException {
        try {
            return randomAccessFile.getChannel().tryLock(0, Long.MAX_VALUE, false) != null;
        } catch (OverlappingFileLockException unused) {
            return false;
        } catch (IOException e) {
            String message = e.getMessage();
            if (message != null && (message.contains(": EAGAIN (") || message.contains(": errno 11 ("))) {
                return false;
            }
            throw e;
        }
    }
}
