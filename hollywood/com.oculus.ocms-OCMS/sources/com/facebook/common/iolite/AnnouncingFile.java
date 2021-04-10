package com.facebook.common.iolite;

import android.os.FileObserver;
import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public class AnnouncingFile extends File {
    private final CountDownLatch mFileCreatedCondition = new CountDownLatch(1);
    @Nullable
    private volatile FileObserver mFileObserver;
    private volatile boolean mIsClosed = false;
    private volatile boolean mIsTailing = false;
    @Nullable
    private volatile Listener mListener = null;
    @Nullable
    private volatile FileObserver mParentFolderObserver;

    public interface Listener {
        void onClose();

        void onFlush();
    }

    public AnnouncingFile(String str, boolean z) {
        super(str);
        this.mIsTailing = z;
    }

    public void startObserving() {
        if (!this.mIsClosed) {
            startObservingFile();
            startObservingParentFolder();
        }
    }

    public void stopObserving() {
        this.mFileCreatedCondition.countDown();
        cleanupObservers();
    }

    public void setListener(Listener listener) {
        this.mListener = listener;
    }

    public void flush() {
        if (this.mListener != null) {
            this.mListener.onFlush();
        }
    }

    public void close() {
        this.mIsClosed = true;
        cleanupObservers();
        if (this.mListener != null) {
            this.mListener.onClose();
        }
    }

    public boolean isClosed() {
        return this.mIsClosed;
    }

    public boolean isTailing() {
        return this.mIsTailing;
    }

    public boolean ensureExists() {
        if (exists() || this.mIsClosed) {
            return exists();
        }
        try {
            this.mFileCreatedCondition.await(5, TimeUnit.SECONDS);
        } catch (InterruptedException unused) {
        }
        return exists();
    }

    private synchronized void startObservingFile() {
        cleanupFileObserver();
        this.mFileObserver = createFileObserver();
        this.mFileObserver.startWatching();
    }

    private synchronized void startObservingParentFolder() {
        cleanupParentFolderObserver();
        this.mParentFolderObserver = createParentFolderObserver();
        this.mParentFolderObserver.startWatching();
    }

    /* access modifiers changed from: package-private */
    public FileObserver createParentFolderObserver() {
        return new FileObserver(getParentFile().getPath(), 256) {
            /* class com.facebook.common.iolite.AnnouncingFile.AnonymousClass1 */

            public void onEvent(int i, @Nullable String str) {
                AnnouncingFile.this.onParentFolderObserverOnEvent(str);
            }
        };
    }

    /* access modifiers changed from: package-private */
    public void onParentFolderObserverOnEvent(@Nullable String str) {
        if (getName().equals(str)) {
            this.mFileCreatedCondition.countDown();
            startObservingFile();
        }
    }

    /* access modifiers changed from: package-private */
    public FileObserver createFileObserver() {
        return new FileObserver(getPath(), 2) {
            /* class com.facebook.common.iolite.AnnouncingFile.AnonymousClass2 */

            public void onEvent(int i, @Nullable String str) {
                AnnouncingFile.this.onFileObserverOnEvent();
            }
        };
    }

    /* access modifiers changed from: package-private */
    public void onFileObserverOnEvent() {
        cleanupParentFolderObserver();
        this.mFileCreatedCondition.countDown();
        flush();
    }

    private void cleanupObservers() {
        cleanupFileObserver();
        cleanupParentFolderObserver();
    }

    private synchronized void cleanupFileObserver() {
        if (this.mFileObserver != null) {
            this.mFileObserver.stopWatching();
            this.mFileObserver = null;
        }
    }

    private synchronized void cleanupParentFolderObserver() {
        if (this.mParentFolderObserver != null) {
            this.mParentFolderObserver.stopWatching();
            this.mParentFolderObserver = null;
        }
    }
}
