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

    public Spool(File directoryName) {
        this.mDirectoryName = directoryName;
    }

    public FileBeingProduced produce(String uniqueName) throws IOException {
        return produceWithDonorFile(uniqueName, null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x00fd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.acra.Spool.FileBeingProduced produceWithDonorFile(java.lang.String r14, java.io.File r15) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 329
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.Spool.produceWithDonorFile(java.lang.String, java.io.File):com.facebook.acra.Spool$FileBeingProduced");
    }

    public Snapshot snapshot(Comparator<Descriptor> comparator, FilenameFilter filter) {
        String[] fileBaseNames = this.mDirectoryName.list(filter);
        if (fileBaseNames == null) {
            fileBaseNames = new String[0];
        }
        int nfiles = fileBaseNames.length;
        Descriptor[] descriptors = new Descriptor[nfiles];
        for (int i = 0; i < nfiles; i++) {
            String fileBaseName = fileBaseNames[i];
            File fileName = new File(this.mDirectoryName, fileBaseName);
            descriptors[i] = new Descriptor(fileBaseName, fileName.lastModified(), fileName);
        }
        if (comparator != null) {
            Arrays.sort(descriptors, comparator);
        }
        return new Snapshot(descriptors);
    }

    public final class FileBeingProduced implements Closeable {
        public final RandomAccessFile file;
        public final File fileName;

        FileBeingProduced(File fileName2, RandomAccessFile file2) {
            this.fileName = fileName2;
            this.file = file2;
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

        FileBeingConsumed(File fileName2, RandomAccessFile file2) {
            this.fileName = fileName2;
            this.file = file2;
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

        Snapshot(Descriptor[] descriptors) {
            this.mDescriptors = descriptors;
        }

        public int getEstimate() {
            return this.mDescriptors.length;
        }

        @Override // java.util.Iterator
        public FileBeingConsumed next() {
            updateCurrent();
            if (this.mCurrent == null) {
                throw new NoSuchElementException();
            }
            FileBeingConsumed fbc = this.mCurrent;
            this.mCurrent = null;
            return fbc;
        }

        public boolean hasNext() {
            updateCurrent();
            return this.mCurrent != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void updateCurrent() {
            while (this.mCurrent == null && this.mPosition < this.mDescriptors.length) {
                Descriptor[] descriptorArr = this.mDescriptors;
                int i = this.mPosition;
                this.mPosition = i + 1;
                this.mCurrent = tryOpenFileForConsumption(descriptorArr[i]);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:101:?, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:102:?, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:104:?, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:105:?, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:106:?, code lost:
            return r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:107:?, code lost:
            return r1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001a, code lost:
            r7 = r12.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
            monitor-enter(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            r12.this$0.mHazardList.remove(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0024, code lost:
            monitor-exit(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0033, code lost:
            r2 = new java.io.RandomAccessFile(r3, "rw");
         */
        /* JADX WARNING: Code restructure failed: missing block: B:22:0x0041, code lost:
            if (r12.this$0.tryLock(r2) != false) goto L_0x0086;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0043, code lost:
            r12.this$0.closeSilently(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0048, code lost:
            if (1 == 0) goto L_0x0055;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x004a, code lost:
            r7 = r12.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x004c, code lost:
            monitor-enter(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
            r12.this$0.mHazardList.remove(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0054, code lost:
            monitor-exit(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x008a, code lost:
            if (r3.exists() != false) goto L_0x00a3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x008c, code lost:
            r12.this$0.closeSilently(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x0091, code lost:
            if (1 == 0) goto L_0x009e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x0093, code lost:
            r7 = r12.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x0095, code lost:
            monitor-enter(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
            r12.this$0.mHazardList.remove(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:57:0x009d, code lost:
            monitor-exit(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:63:0x00ab, code lost:
            if (r2.length() != 0) goto L_0x00c8;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:64:0x00ad, code lost:
            r3.delete();
            r12.this$0.closeSilently(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:65:0x00b5, code lost:
            if (1 == 0) goto L_0x00c2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:66:0x00b7, code lost:
            r7 = r12.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:67:0x00b9, code lost:
            monitor-enter(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
            r12.this$0.mHazardList.remove(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:70:0x00c1, code lost:
            monitor-exit(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:75:0x00c8, code lost:
            r1 = new com.facebook.acra.Spool.FileBeingConsumed(r12.this$0, r3, r2);
            r12.this$0.closeSilently(null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:76:0x00d6, code lost:
            if (0 == 0) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:77:0x00d8, code lost:
            r7 = r12.this$0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:78:0x00da, code lost:
            monitor-enter(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:80:?, code lost:
            r12.this$0.mHazardList.remove(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:81:0x00e2, code lost:
            monitor-exit(r7);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
            r12.this$0.closeSilently(null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
            if (0 == 0) goto L_0x0025;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private com.facebook.acra.Spool.FileBeingConsumed tryOpenFileForConsumption(com.facebook.acra.Spool.Descriptor r13) {
            /*
            // Method dump skipped, instructions count: 258
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

        public Descriptor(String fileBaseName2, long lastModifiedTime2, File fileName2) {
            this.fileBaseName = fileBaseName2;
            this.lastModifiedTime = lastModifiedTime2;
            this.fileName = fileName2;
        }
    }

    /* access modifiers changed from: package-private */
    public void closeSilently(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean tryLock(RandomAccessFile raf) throws IOException {
        try {
            if (raf.getChannel().tryLock(0, Long.MAX_VALUE, false) != null) {
                return true;
            }
            return false;
        } catch (OverlappingFileLockException e) {
            return false;
        } catch (IOException ex) {
            String exm = ex.getMessage();
            if (exm != null && (exm.contains(": EAGAIN (") || exm.contains(": errno 11 ("))) {
                return false;
            }
            throw ex;
        }
    }
}
