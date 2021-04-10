package com.facebook.stetho.server;

import android.net.LocalServerSocket;
import android.net.LocalSocket;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.BindException;
import java.net.SocketException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import org.apache.commons.cli.HelpFormatter;

public class LocalSocketServer {
    private static final int MAX_BIND_RETRIES = 2;
    private static final int TIME_BETWEEN_BIND_RETRIES_MS = 1000;
    private static final String WORKER_THREAD_NAME_PREFIX = "StethoWorker";
    private final String mAddress;
    private final String mFriendlyName;
    private Thread mListenerThread;
    private LocalServerSocket mServerSocket;
    private final SocketHandler mSocketHandler;
    private boolean mStopped;
    private final AtomicInteger mThreadId = new AtomicInteger();

    public LocalSocketServer(String str, String str2, SocketHandler socketHandler) {
        this.mFriendlyName = (String) Util.throwIfNull(str);
        this.mAddress = (String) Util.throwIfNull(str2);
        this.mSocketHandler = socketHandler;
    }

    public String getName() {
        return this.mFriendlyName;
    }

    public void run() throws IOException {
        synchronized (this) {
            if (!this.mStopped) {
                this.mListenerThread = Thread.currentThread();
                listenOnAddress(this.mAddress);
            }
        }
    }

    private void listenOnAddress(String str) throws IOException {
        this.mServerSocket = bindToSocket(str);
        LogUtil.i("Listening on @" + str);
        while (!Thread.interrupted()) {
            try {
                WorkerThread workerThread = new WorkerThread(this.mServerSocket.accept(), this.mSocketHandler);
                workerThread.setName("StethoWorker-" + this.mFriendlyName + HelpFormatter.DEFAULT_OPT_PREFIX + this.mThreadId.incrementAndGet());
                workerThread.setDaemon(true);
                workerThread.start();
            } catch (SocketException e) {
                if (Thread.interrupted()) {
                    break;
                }
                LogUtil.w(e, "I/O error");
            } catch (InterruptedIOException unused) {
            } catch (IOException e2) {
                LogUtil.w(e2, "I/O error initialising connection thread");
            }
        }
        LogUtil.i("Server shutdown on @" + str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0012, code lost:
        if (r1.mServerSocket == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0014, code lost:
        r1.mServerSocket.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x000b, code lost:
        r1.mListenerThread.interrupt();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void stop() {
        /*
            r1 = this;
            monitor-enter(r1)
            r0 = 1
            r1.mStopped = r0     // Catch:{ all -> 0x001a }
            java.lang.Thread r0 = r1.mListenerThread     // Catch:{ all -> 0x001a }
            if (r0 != 0) goto L_0x000a
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            return
        L_0x000a:
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            java.lang.Thread r0 = r1.mListenerThread
            r0.interrupt()
            android.net.LocalServerSocket r0 = r1.mServerSocket     // Catch:{ IOException -> 0x0019 }
            if (r0 == 0) goto L_0x0019
            android.net.LocalServerSocket r0 = r1.mServerSocket     // Catch:{ IOException -> 0x0019 }
            r0.close()     // Catch:{ IOException -> 0x0019 }
        L_0x0019:
            return
        L_0x001a:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.stetho.server.LocalSocketServer.stop():void");
    }

    @Nonnull
    private static LocalServerSocket bindToSocket(String str) throws IOException {
        int i = 2;
        BindException bindException = null;
        while (true) {
            try {
                if (LogUtil.isLoggable(3)) {
                    LogUtil.d("Trying to bind to @" + str);
                }
                return new LocalServerSocket(str);
            } catch (BindException e) {
                LogUtil.w(e, "Binding error, sleep 1000 ms...");
                if (bindException == null) {
                    bindException = e;
                }
                Util.sleepUninterruptibly(1000);
                int i2 = i - 1;
                if (i > 0) {
                    i = i2;
                } else {
                    throw bindException;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static class WorkerThread extends Thread {
        private final LocalSocket mSocket;
        private final SocketHandler mSocketHandler;

        public WorkerThread(LocalSocket localSocket, SocketHandler socketHandler) {
            this.mSocket = localSocket;
            this.mSocketHandler = socketHandler;
        }

        public void run() {
            try {
                this.mSocketHandler.onAccepted(this.mSocket);
            } catch (IOException e) {
                LogUtil.w("I/O error: %s", e);
            } catch (Throwable th) {
                try {
                    this.mSocket.close();
                } catch (IOException unused) {
                }
                throw th;
            }
            try {
                this.mSocket.close();
            } catch (IOException unused2) {
            }
        }
    }
}
