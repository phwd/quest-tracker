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
    public static final String TAG = "Spool";
    public final File mDirectoryName;
    public final HashSet<File> mHazardList = new HashSet<>();

    public final class FileBeingConsumed implements Closeable {
        public final RandomAccessFile file;
        public final File fileName;

        public FileBeingConsumed(File file2, RandomAccessFile randomAccessFile) {
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

    public final class FileBeingProduced implements Closeable {
        public final RandomAccessFile file;
        public final File fileName;

        public FileBeingProduced(File file2, RandomAccessFile randomAccessFile) {
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

    public final class Snapshot implements Iterator<FileBeingConsumed>, Closeable {
        public FileBeingConsumed mCurrent = null;
        public final Descriptor[] mDescriptors;
        public int mPosition = 0;

        public Snapshot(Descriptor[] descriptorArr) {
            this.mDescriptors = descriptorArr;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:100:0x00e1, code lost:
            throw r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:104:0x00e5, code lost:
            throw r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
            r7 = new java.io.RandomAccessFile(r4, "rw");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0029, code lost:
            if (r11.this$0.tryLock(r7) != false) goto L_0x003f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x002b, code lost:
            r11.this$0.closeSilently(r7);
            r1 = r11.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:0x0032, code lost:
            monitor-enter(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            r11.this$0.mHazardList.remove(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x003a, code lost:
            monitor-exit(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x003b, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0043, code lost:
            if (r4.exists() != false) goto L_0x0059;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0045, code lost:
            r11.this$0.closeSilently(r7);
            r1 = r11.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x004c, code lost:
            monitor-enter(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
            r11.this$0.mHazardList.remove(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:33:0x0054, code lost:
            monitor-exit(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:0x0055, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x0061, code lost:
            if (r7.length() != 0) goto L_0x007a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x0063, code lost:
            r4.delete();
            r11.this$0.closeSilently(r7);
            r1 = r11.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:41:0x006d, code lost:
            monitor-enter(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
            r11.this$0.mHazardList.remove(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:44:0x0075, code lost:
            monitor-exit(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x0076, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x0081, code lost:
            return new com.facebook.acra.Spool.FileBeingConsumed(r11.this$0, r4, r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x0082, code lost:
            r2 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x0084, code lost:
            r6 = e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x0086, code lost:
            r6 = e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x0087, code lost:
            r7 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x0088, code lost:
            r5 = true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:80:0x00bc, code lost:
            monitor-enter(r11.this$0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:82:?, code lost:
            r11.this$0.mHazardList.remove(r4);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:84:0x00c5, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:88:0x00c9, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:91:0x00cd, code lost:
            r2 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:92:0x00ce, code lost:
            r7 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:96:0x00d8, code lost:
            monitor-enter(r11.this$0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:98:?, code lost:
            r11.this$0.mHazardList.remove(r4);
         */
        /* JADX WARNING: Removed duplicated region for block: B:104:0x00e5  */
        /* JADX WARNING: Removed duplicated region for block: B:79:0x00ba  */
        /* JADX WARNING: Removed duplicated region for block: B:88:0x00c9 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:95:0x00d6  */
        @javax.annotation.Nullable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private com.facebook.acra.Spool.FileBeingConsumed tryOpenFileForConsumption(com.facebook.acra.Spool.Descriptor r12) {
            /*
            // Method dump skipped, instructions count: 230
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.Spool.Snapshot.tryOpenFileForConsumption(com.facebook.acra.Spool$Descriptor):com.facebook.acra.Spool$FileBeingConsumed");
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

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            Spool.this.closeSilently(this.mCurrent);
        }

        public int getEstimate() {
            return this.mDescriptors.length;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public boolean hasNext() {
            updateCurrent();
            if (this.mCurrent != null) {
                return true;
            }
            return false;
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
    }

    @Nullable
    public FileBeingProduced produce(String str) throws IOException {
        return produceWithDonorFile(str, null);
    }

    public boolean tryLock(RandomAccessFile randomAccessFile) throws IOException {
        try {
            return randomAccessFile.getChannel().tryLock(0, Long.MAX_VALUE, false) != null;
        } catch (OverlappingFileLockException unused) {
        } catch (IOException e) {
            String message = e.getMessage();
            if (message == null || (!message.contains(": EAGAIN (") && !message.contains(": errno 11 ("))) {
                throw e;
            }
        }
    }

    public static final class Descriptor {
        public final String fileBaseName;
        public final File fileName;
        public final long lastModifiedTime;

        public Descriptor(String str, long j, File file) {
            this.fileBaseName = str;
            this.lastModifiedTime = j;
            this.fileName = file;
        }
    }

    public void closeSilently(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x00d1, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x00d7, code lost:
        monitor-enter(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:?, code lost:
        r9.mHazardList.remove(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x00de, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x00df, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0035, code lost:
        if (r3.exists() == false) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0037, code lost:
        monitor-enter(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r9.mHazardList.remove(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003d, code lost:
        monitor-exit(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x003e, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0042, code lost:
        if (r11 == null) goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0048, code lost:
        if (r11.exists() == false) goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r5 = new java.io.RandomAccessFile(r11, "rw");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0057, code lost:
        if (tryLock(r5) == false) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x005d, code lost:
        if (r11.renameTo(r3) == false) goto L_0x0073;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x005f, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0061, code lost:
        r4 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0063, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0064, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0066, code lost:
        r4 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0067, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
        X.AnonymousClass0NO.A0L(com.facebook.acra.Spool.TAG, r4, "error using donor file %s; falling back to regular path", r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        closeSilently(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0077, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0078, code lost:
        closeSilently(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x007b, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:?, code lost:
        r5 = new java.io.RandomAccessFile(r3, "rw");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0087, code lost:
        if (tryLock(r5) != false) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0089, code lost:
        r3.delete();
        closeSilently(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x008f, code lost:
        monitor-enter(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:?, code lost:
        r9.mHazardList.remove(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0096, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x009a, code lost:
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x009f, code lost:
        if (r3.exists() == false) goto L_0x00a1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00a1, code lost:
        if (r2 != null) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00a3, code lost:
        r2.delete();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00a6, code lost:
        closeSilently(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00a9, code lost:
        monitor-enter(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:?, code lost:
        r9.mHazardList.remove(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x00b0, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x00b9, code lost:
        return new com.facebook.acra.Spool.FileBeingProduced(r9, r3, r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x00ba, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x00bb, code lost:
        r8 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x00bd, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x00be, code lost:
        r5 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x00c0, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x00c1, code lost:
        r8 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x00c2, code lost:
        if (r8 != null) goto L_0x00c4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x00c4, code lost:
        r8.delete();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x00ce, code lost:
        r1 = th;
     */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x009a  */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.acra.Spool.FileBeingProduced produceWithDonorFile(java.lang.String r10, @javax.annotation.Nullable java.io.File r11) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 238
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

    public Spool(File file) {
        this.mDirectoryName = file;
    }
}
