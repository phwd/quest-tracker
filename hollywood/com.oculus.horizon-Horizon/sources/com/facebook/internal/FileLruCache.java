package com.facebook.internal;

import X.AnonymousClass006;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidParameterException;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public final class FileLruCache {
    public static final String HEADER_CACHEKEY_KEY = "key";
    public static final String HEADER_CACHE_CONTENT_TAG_KEY = "tag";
    public static final String TAG = "FileLruCache";
    public static final AtomicLong bufferIndex = new AtomicLong();
    public final File directory;
    public boolean isTrimInProgress;
    public boolean isTrimPending;
    public AtomicLong lastClearCacheTime = new AtomicLong(0);
    public final Limits limits;
    public final Object lock;
    public final String tag;

    public static class BufferFile {
        public static final String FILE_NAME_PREFIX = "buffer";
        public static final FilenameFilter filterExcludeBufferFiles = new FilenameFilter() {
            /* class com.facebook.internal.FileLruCache.BufferFile.AnonymousClass1 */

            public boolean accept(File file, String str) {
                return !str.startsWith(BufferFile.FILE_NAME_PREFIX);
            }
        };
        public static final FilenameFilter filterExcludeNonBufferFiles = new FilenameFilter() {
            /* class com.facebook.internal.FileLruCache.BufferFile.AnonymousClass2 */

            public boolean accept(File file, String str) {
                return str.startsWith(BufferFile.FILE_NAME_PREFIX);
            }
        };

        public static void deleteAll(File file) {
            File[] listFiles = file.listFiles(filterExcludeNonBufferFiles);
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    file2.delete();
                }
            }
        }

        public static File newFile(File file) {
            return new File(file, AnonymousClass006.A05(FILE_NAME_PREFIX, Long.valueOf(FileLruCache.bufferIndex.incrementAndGet()).toString()));
        }

        public static FilenameFilter excludeBufferFiles() {
            return filterExcludeBufferFiles;
        }

        public static FilenameFilter excludeNonBufferFiles() {
            return filterExcludeNonBufferFiles;
        }
    }

    public static class CloseCallbackOutputStream extends OutputStream {
        public final StreamCloseCallback callback;
        public final OutputStream innerStream;

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            try {
                this.innerStream.close();
            } finally {
                this.callback.onClose();
            }
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() throws IOException {
            this.innerStream.flush();
        }

        public CloseCallbackOutputStream(OutputStream outputStream, StreamCloseCallback streamCloseCallback) {
            this.innerStream = outputStream;
            this.callback = streamCloseCallback;
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            this.innerStream.write(i);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            this.innerStream.write(bArr);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            this.innerStream.write(bArr, i, i2);
        }
    }

    public static final class CopyingInputStream extends InputStream {
        public final InputStream input;
        public final OutputStream output;

        public boolean markSupported() {
            return false;
        }

        @Override // java.io.InputStream
        public synchronized void reset() {
            throw new UnsupportedOperationException();
        }

        @Override // java.io.InputStream
        public int available() throws IOException {
            return this.input.available();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
        public void close() throws IOException {
            try {
                this.input.close();
            } finally {
                this.output.close();
            }
        }

        public void mark(int i) {
            throw new UnsupportedOperationException();
        }

        @Override // java.io.InputStream
        public long skip(long j) throws IOException {
            int read;
            byte[] bArr = new byte[1024];
            long j2 = 0;
            while (j2 < j && (read = read(bArr, 0, (int) Math.min(j - j2, (long) 1024))) >= 0) {
                j2 += (long) read;
            }
            return j2;
        }

        public CopyingInputStream(InputStream inputStream, OutputStream outputStream) {
            this.input = inputStream;
            this.output = outputStream;
        }

        @Override // java.io.InputStream
        public int read() throws IOException {
            int read = this.input.read();
            if (read >= 0) {
                this.output.write(read);
            }
            return read;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr) throws IOException {
            int read = this.input.read(bArr);
            if (read > 0) {
                this.output.write(bArr, 0, read);
            }
            return read;
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = this.input.read(bArr, i, i2);
            if (read > 0) {
                this.output.write(bArr, i, read);
            }
            return read;
        }
    }

    public static final class Limits {
        public int byteCount = ApkUpdateInfoContract.UPDATE_TYPE_FULL;
        public int fileCount = 1024;

        public void setByteCount(int i) {
            if (i >= 0) {
                this.byteCount = i;
                return;
            }
            throw new InvalidParameterException("Cache byte-count limit must be >= 0");
        }

        public void setFileCount(int i) {
            if (i >= 0) {
                this.fileCount = i;
                return;
            }
            throw new InvalidParameterException("Cache file count limit must be >= 0");
        }

        public int getByteCount() {
            return this.byteCount;
        }

        public int getFileCount() {
            return this.fileCount;
        }
    }

    public static final class ModifiedFile implements Comparable<ModifiedFile> {
        public static final int HASH_MULTIPLIER = 37;
        public static final int HASH_SEED = 29;
        public final File file;
        public final long modified;

        public boolean equals(Object obj) {
            if (!(obj instanceof ModifiedFile) || compareTo((ModifiedFile) obj) != 0) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return ((1073 + this.file.hashCode()) * 37) + ((int) (this.modified % 2147483647L));
        }

        public ModifiedFile(File file2) {
            this.file = file2;
            this.modified = file2.lastModified();
        }

        public File getFile() {
            return this.file;
        }

        public long getModified() {
            return this.modified;
        }

        public int compareTo(ModifiedFile modifiedFile) {
            long j = this.modified;
            long j2 = modifiedFile.modified;
            if (j < j2) {
                return -1;
            }
            if (j > j2) {
                return 1;
            }
            return this.file.compareTo(modifiedFile.file);
        }
    }

    public interface StreamCloseCallback {
        void onClose();
    }

    public InputStream interceptAndPut(String str, InputStream inputStream) throws IOException {
        return new CopyingInputStream(inputStream, openPutStream(str, null));
    }

    public static final class StreamHeader {
        public static final int HEADER_VERSION = 0;

        public static JSONObject readHeader(InputStream inputStream) throws IOException {
            if (inputStream.read() == 0) {
                int i = 0;
                int i2 = 0;
                for (int i3 = 0; i3 < 3; i3++) {
                    int read = inputStream.read();
                    if (read == -1) {
                        Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: stream.read returned -1 while reading header size");
                    } else {
                        i2 = (i2 << 8) + (read & 255);
                    }
                }
                byte[] bArr = new byte[i2];
                while (i < i2) {
                    int read2 = inputStream.read(bArr, i, i2 - i);
                    if (read2 < 1) {
                        LoggingBehavior loggingBehavior = LoggingBehavior.CACHE;
                        StringBuilder sb = new StringBuilder("readHeader: stream.read stopped at ");
                        sb.append(Integer.valueOf(i));
                        sb.append(" when expected ");
                        sb.append(i2);
                        Logger.log(loggingBehavior, FileLruCache.TAG, sb.toString());
                        return null;
                    }
                    i += read2;
                }
                try {
                    Object nextValue = new JSONTokener(new String(bArr)).nextValue();
                    if (nextValue instanceof JSONObject) {
                        return (JSONObject) nextValue;
                    }
                    Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, AnonymousClass006.A05("readHeader: expected JSONObject, got ", nextValue.getClass().getCanonicalName()));
                    return null;
                } catch (JSONException e) {
                    throw new IOException(e.getMessage());
                }
            }
            return null;
        }

        public static void writeHeader(OutputStream outputStream, JSONObject jSONObject) throws IOException {
            byte[] bytes = jSONObject.toString().getBytes();
            outputStream.write(0);
            int length = bytes.length;
            outputStream.write((length >> 16) & 255);
            outputStream.write((length >> 8) & 255);
            outputStream.write((length >> 0) & 255);
            outputStream.write(bytes);
        }
    }

    private void postTrim() {
        synchronized (this.lock) {
            if (!this.isTrimPending) {
                this.isTrimPending = true;
                FacebookSdk.getExecutor().execute(new Runnable() {
                    /* class com.facebook.internal.FileLruCache.AnonymousClass3 */

                    public void run() {
                        FileLruCache.this.trim();
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void renameToTargetAndTrim(String str, File file) {
        if (!file.renameTo(new File(this.directory, Utility.hashWithAlgorithm(Utility.HASH_ALGORITHM_MD5, str)))) {
            file.delete();
        }
        postTrim();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void trim() {
        long j;
        synchronized (this.lock) {
            this.isTrimPending = false;
            this.isTrimInProgress = true;
        }
        try {
            Logger.log(LoggingBehavior.CACHE, TAG, "trim started");
            PriorityQueue priorityQueue = new PriorityQueue();
            File[] listFiles = this.directory.listFiles(BufferFile.filterExcludeBufferFiles);
            long j2 = 0;
            if (listFiles != null) {
                j = 0;
                for (File file : listFiles) {
                    ModifiedFile modifiedFile = new ModifiedFile(file);
                    priorityQueue.add(modifiedFile);
                    LoggingBehavior loggingBehavior = LoggingBehavior.CACHE;
                    StringBuilder sb = new StringBuilder();
                    sb.append("  trim considering time=");
                    sb.append(Long.valueOf(modifiedFile.modified));
                    sb.append(" name=");
                    sb.append(modifiedFile.file.getName());
                    Logger.log(loggingBehavior, TAG, sb.toString());
                    j2 += file.length();
                    j++;
                }
            } else {
                j = 0;
            }
            while (true) {
                Limits limits2 = this.limits;
                if (j2 > ((long) limits2.byteCount) || j > ((long) limits2.fileCount)) {
                    File file2 = ((ModifiedFile) priorityQueue.remove()).file;
                    Logger.log(LoggingBehavior.CACHE, TAG, AnonymousClass006.A05("  trim removing ", file2.getName()));
                    j2 -= file2.length();
                    j--;
                    file2.delete();
                } else {
                    synchronized (this.lock) {
                        this.isTrimInProgress = false;
                        this.lock.notifyAll();
                    }
                    return;
                }
            }
        } catch (Throwable th) {
            th = th;
        }
        throw th;
    }

    public void clearCache() {
        final File[] listFiles = this.directory.listFiles(BufferFile.filterExcludeBufferFiles);
        this.lastClearCacheTime.set(System.currentTimeMillis());
        if (listFiles != null) {
            FacebookSdk.getExecutor().execute(new Runnable() {
                /* class com.facebook.internal.FileLruCache.AnonymousClass2 */

                public void run() {
                    for (File file : listFiles) {
                        file.delete();
                    }
                }
            });
        }
    }

    public String getLocation() {
        return this.directory.getPath();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:2:0x0003 */
    /* JADX WARNING: Removed duplicated region for block: B:2:0x0003 A[LOOP:0: B:2:0x0003->B:20:0x0003, LOOP_START, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long sizeInBytesForTest() {
        /*
            r7 = this;
            java.lang.Object r1 = r7.lock
            monitor-enter(r1)
        L_0x0003:
            boolean r0 = r7.isTrimPending     // Catch:{ all -> 0x002c }
            if (r0 != 0) goto L_0x000d
            boolean r0 = r7.isTrimInProgress     // Catch:{ all -> 0x002c }
            if (r0 != 0) goto L_0x000d
            monitor-exit(r1)     // Catch:{ all -> 0x002c }
            goto L_0x0013
        L_0x000d:
            java.lang.Object r0 = r7.lock     // Catch:{ InterruptedException -> 0x0003 }
            r0.wait()     // Catch:{ InterruptedException -> 0x0003 }
            goto L_0x0003
        L_0x0013:
            java.io.File r0 = r7.directory
            java.io.File[] r6 = r0.listFiles()
            r4 = 0
            if (r6 == 0) goto L_0x002b
            int r3 = r6.length
            r2 = 0
        L_0x001f:
            if (r2 >= r3) goto L_0x002b
            r0 = r6[r2]
            long r0 = r0.length()
            long r4 = r4 + r0
            int r2 = r2 + 1
            goto L_0x001f
        L_0x002b:
            return r4
        L_0x002c:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.FileLruCache.sizeInBytesForTest():long");
    }

    public String toString() {
        return AnonymousClass006.A09("{FileLruCache: tag:", this.tag, " file:", this.directory.getName(), "}");
    }

    public FileLruCache(String str, Limits limits2) {
        this.tag = str;
        this.limits = limits2;
        File file = new File(FacebookSdk.getCacheDir(), str);
        this.directory = file;
        this.lock = new Object();
        if (file.mkdirs() || this.directory.isDirectory()) {
            BufferFile.deleteAll(this.directory);
        }
    }

    public InputStream get(String str) throws IOException {
        return get(str, null);
    }

    public InputStream get(String str, String str2) throws IOException {
        String optString;
        File file = new File(this.directory, Utility.hashWithAlgorithm(Utility.HASH_ALGORITHM_MD5, str));
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file), 8192);
            try {
                JSONObject readHeader = StreamHeader.readHeader(bufferedInputStream);
                if (!(readHeader == null || (optString = readHeader.optString("key")) == null || !optString.equals(str))) {
                    String optString2 = readHeader.optString("tag", null);
                    if (str2 != null || optString2 == null) {
                        if (str2 == null || str2.equals(optString2)) {
                            long time = new Date().getTime();
                            LoggingBehavior loggingBehavior = LoggingBehavior.CACHE;
                            StringBuilder sb = new StringBuilder();
                            sb.append("Setting lastModified to ");
                            sb.append(Long.valueOf(time));
                            sb.append(" for ");
                            sb.append(file.getName());
                            Logger.log(loggingBehavior, TAG, sb.toString());
                            file.setLastModified(time);
                            return bufferedInputStream;
                        }
                    }
                }
                return null;
            } finally {
                bufferedInputStream.close();
            }
        } catch (IOException unused) {
            return null;
        }
    }

    public OutputStream openPutStream(String str) throws IOException {
        return openPutStream(str, null);
    }

    public OutputStream openPutStream(final String str, String str2) throws IOException {
        final File newFile = BufferFile.newFile(this.directory);
        newFile.delete();
        if (newFile.createNewFile()) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(newFile);
                final long currentTimeMillis = System.currentTimeMillis();
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new CloseCallbackOutputStream(fileOutputStream, new StreamCloseCallback() {
                    /* class com.facebook.internal.FileLruCache.AnonymousClass1 */

                    @Override // com.facebook.internal.FileLruCache.StreamCloseCallback
                    public void onClose() {
                        if (currentTimeMillis < FileLruCache.this.lastClearCacheTime.get()) {
                            newFile.delete();
                        } else {
                            FileLruCache.this.renameToTargetAndTrim(str, newFile);
                        }
                    }
                }), 8192);
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("key", str);
                    if (!Utility.isNullOrEmpty(str2)) {
                        jSONObject.put("tag", str2);
                    }
                    StreamHeader.writeHeader(bufferedOutputStream, jSONObject);
                    return bufferedOutputStream;
                } catch (JSONException e) {
                    LoggingBehavior loggingBehavior = LoggingBehavior.CACHE;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Error creating JSON header for cache file: ");
                    sb.append(e);
                    Logger.log(loggingBehavior, 5, TAG, sb.toString());
                    throw new IOException(e.getMessage());
                } catch (Throwable th) {
                    bufferedOutputStream.close();
                    throw th;
                }
            } catch (FileNotFoundException e2) {
                LoggingBehavior loggingBehavior2 = LoggingBehavior.CACHE;
                StringBuilder sb2 = new StringBuilder("Error creating buffer output stream: ");
                sb2.append(e2);
                Logger.log(loggingBehavior2, 5, TAG, sb2.toString());
                throw new IOException(e2.getMessage());
            }
        } else {
            throw new IOException(AnonymousClass006.A05("Could not create file at ", newFile.getAbsolutePath()));
        }
    }
}
