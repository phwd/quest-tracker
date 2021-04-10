package com.facebook.soloader;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;

public final class FileLocker implements Closeable {
    private final FileLock mLock;
    private final FileOutputStream mLockFileOutputStream;

    public static FileLocker lock(File lockFile) throws IOException {
        return new FileLocker(lockFile);
    }

    private FileLocker(File lockFile) throws IOException {
        this.mLockFileOutputStream = new FileOutputStream(lockFile);
        FileLock lock = null;
        try {
            lock = this.mLockFileOutputStream.getChannel().lock();
            this.mLock = lock;
        } finally {
            if (lock == null) {
                this.mLockFileOutputStream.close();
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            if (this.mLock != null) {
                this.mLock.release();
            }
        } finally {
            this.mLockFileOutputStream.close();
        }
    }
}
