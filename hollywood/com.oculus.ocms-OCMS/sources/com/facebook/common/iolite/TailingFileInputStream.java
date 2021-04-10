package com.facebook.common.iolite;

import com.facebook.common.iolite.AnnouncingFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TailingFileInputStream extends InputStream implements AnnouncingFile.Listener {
    private final byte[] mBuffer;
    private int mBufferLimit;
    private int mBufferPos;
    private boolean mClosed;
    private final RandomAccessFile mFile;
    private final int mFileReadBufferSize;
    private final AtomicBoolean mIsEOF;
    private final Lock mLock;
    private final Condition mReadFile;
    private final SoftErrorDelegate mSoftErrorDelegate;

    public interface SoftErrorDelegate {
        void logSoftError(String str, String str2, int i);
    }

    public TailingFileInputStream(AnnouncingFile announcingFile, int i, SoftErrorDelegate softErrorDelegate) throws FileNotFoundException {
        this((File) announcingFile, i, softErrorDelegate);
        announcingFile.setListener(this);
        if (announcingFile.isClosed()) {
            this.mIsEOF.set(true);
        }
    }

    public TailingFileInputStream(File file, int i, SoftErrorDelegate softErrorDelegate) throws FileNotFoundException {
        this.mLock = new ReentrantLock();
        this.mReadFile = this.mLock.newCondition();
        this.mIsEOF = new AtomicBoolean(false);
        this.mClosed = false;
        this.mFileReadBufferSize = i;
        this.mBuffer = new byte[this.mFileReadBufferSize];
        this.mFile = new RandomAccessFile(file, "r");
        this.mSoftErrorDelegate = softErrorDelegate;
    }

    private boolean finishedReadingCurrentBuffer() {
        return this.mBufferPos == this.mBufferLimit;
    }

    /* JADX INFO: finally extract failed */
    @Override // java.io.InputStream
    public synchronized int read() throws IOException {
        if (this.mClosed) {
            return -1;
        }
        if (finishedReadingCurrentBuffer()) {
            this.mLock.lock();
            try {
                if (!this.mIsEOF.get()) {
                    this.mReadFile.awaitUninterruptibly();
                }
                this.mLock.unlock();
                if (!fillBuffer() && this.mIsEOF.get()) {
                    this.mFile.close();
                    this.mClosed = true;
                    return -1;
                }
            } catch (Throwable th) {
                this.mLock.unlock();
                throw th;
            }
        }
        if (finishedReadingCurrentBuffer()) {
            SoftErrorDelegate softErrorDelegate = this.mSoftErrorDelegate;
            softErrorDelegate.logSoftError("TailingFileInputStream:BufferError", "mBufferPos = " + this.mBufferPos + "; mBufferLimit = " + this.mBufferLimit, 10000);
            if (this.mBufferPos == this.mFileReadBufferSize) {
                throw new IOException("Stopping invalid buffer during TailingFileInputStream upload operation");
            }
        }
        byte[] bArr = this.mBuffer;
        int i = this.mBufferPos;
        this.mBufferPos = i + 1;
        return bArr[i] & 255;
    }

    /* access modifiers changed from: protected */
    public boolean fillBuffer() throws IOException {
        int read = this.mFile.read(this.mBuffer, 0, this.mFileReadBufferSize);
        if (read == -1) {
            return false;
        }
        this.mBufferLimit = read;
        this.mBufferPos = 0;
        return true;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.io.InputStream
    public void close() {
        this.mLock.lock();
        try {
            this.mIsEOF.set(true);
            this.mReadFile.signalAll();
        } finally {
            this.mLock.unlock();
        }
    }

    public void flush() {
        this.mLock.lock();
        try {
            this.mReadFile.signalAll();
        } finally {
            this.mLock.unlock();
        }
    }

    @Override // com.facebook.common.iolite.AnnouncingFile.Listener
    public void onClose() {
        close();
    }

    @Override // com.facebook.common.iolite.AnnouncingFile.Listener
    public void onFlush() {
        flush();
    }
}
