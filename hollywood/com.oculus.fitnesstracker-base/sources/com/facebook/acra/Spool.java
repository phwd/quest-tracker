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

public final class Spool {
    private final File mDirectoryName;
    final HashSet<File> mHazardList = new HashSet<>();

    public Spool(File file) {
        this.mDirectoryName = file;
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
        com.facebook.debug.log.BLog.w("Spool", r4, "error using donor file %s; falling back to regular path", r10);
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
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.facebook.acra.Spool.FileBeingProduced produceWithDonorFile(java.lang.String r9, java.io.File r10) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 285
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.Spool.produceWithDonorFile(java.lang.String, java.io.File):com.facebook.acra.Spool$FileBeingProduced");
    }

    public final Snapshot snapshot(Comparator<Descriptor> comparator, FilenameFilter filenameFilter) {
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
        Arrays.sort(descriptorArr, comparator);
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
        public final void close() throws IOException {
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
        public final void close() {
            synchronized (Spool.this) {
                Spool.this.mHazardList.remove(this.fileName);
            }
            Spool.closeSilently(this.file);
        }
    }

    public final class Snapshot implements Closeable, Iterator<FileBeingConsumed> {
        private FileBeingConsumed mCurrent = null;
        final Descriptor[] mDescriptors;
        private int mPosition = 0;

        Snapshot(Descriptor[] descriptorArr) {
            this.mDescriptors = descriptorArr;
        }

        @Override // java.util.Iterator
        public final FileBeingConsumed next() {
            updateCurrent();
            FileBeingConsumed fileBeingConsumed = this.mCurrent;
            if (fileBeingConsumed != null) {
                this.mCurrent = null;
                return fileBeingConsumed;
            }
            throw new NoSuchElementException();
        }

        public final boolean hasNext() {
            updateCurrent();
            return this.mCurrent != null;
        }

        public final void remove() {
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
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x002a, code lost:
            if (com.facebook.acra.Spool.tryLock(r3) != false) goto L_0x003e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x002c, code lost:
            com.facebook.acra.Spool.closeSilently(r3);
            r0 = r9.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0031, code lost:
            monitor-enter(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            r9.this$0.mHazardList.remove(r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0039, code lost:
            monitor-exit(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x003a, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0042, code lost:
            if (r10.exists() != false) goto L_0x0056;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0044, code lost:
            com.facebook.acra.Spool.closeSilently(r3);
            r0 = r9.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0049, code lost:
            monitor-enter(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
            r9.this$0.mHazardList.remove(r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0051, code lost:
            monitor-exit(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0052, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x005e, code lost:
            if (r3.length() != 0) goto L_0x0075;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0060, code lost:
            r10.delete();
            com.facebook.acra.Spool.closeSilently(r3);
            r0 = r9.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x0068, code lost:
            monitor-enter(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
            r9.this$0.mHazardList.remove(r10);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x0070, code lost:
            monitor-exit(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x0071, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:0x0075, code lost:
            r4 = new com.facebook.acra.Spool.FileBeingConsumed(r9.this$0, r10, r3);
            com.facebook.acra.Spool.closeSilently(null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x007f, code lost:
            return r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x0080, code lost:
            r1 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x0082, code lost:
            r4 = e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x0084, code lost:
            r1 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x0085, code lost:
            r3 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x0087, code lost:
            r4 = e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:0x0088, code lost:
            r3 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x0089, code lost:
            r5 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:79:0x00b9, code lost:
            monitor-enter(r9.this$0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
            r9.this$0.mHazardList.remove(r10);
         */
        /* JADX WARNING: Removed duplicated region for block: B:78:0x00b7  */
        /* JADX WARNING: Removed duplicated region for block: B:91:0x00ce  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private com.facebook.acra.Spool.FileBeingConsumed tryOpenFileForConsumption(com.facebook.acra.Spool.Descriptor r10) {
            /*
            // Method dump skipped, instructions count: 222
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.Spool.Snapshot.tryOpenFileForConsumption(com.facebook.acra.Spool$Descriptor):com.facebook.acra.Spool$FileBeingConsumed");
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            Spool.closeSilently(this.mCurrent);
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

    static void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    static boolean tryLock(RandomAccessFile randomAccessFile) throws IOException {
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
