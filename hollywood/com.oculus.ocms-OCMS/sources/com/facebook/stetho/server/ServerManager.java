package com.facebook.stetho.server;

import com.facebook.stetho.common.LogUtil;
import java.io.IOException;

public class ServerManager {
    private static final String THREAD_PREFIX = "StethoListener";
    private final LocalSocketServer mServer;
    private volatile boolean mStarted;

    public ServerManager(LocalSocketServer localSocketServer) {
        this.mServer = localSocketServer;
    }

    public void start() {
        if (!this.mStarted) {
            this.mStarted = true;
            startServer(this.mServer);
            return;
        }
        throw new IllegalStateException("Already started");
    }

    private void startServer(final LocalSocketServer localSocketServer) {
        new Thread("StethoListener-" + localSocketServer.getName()) {
            /* class com.facebook.stetho.server.ServerManager.AnonymousClass1 */

            public void run() {
                try {
                    localSocketServer.run();
                } catch (IOException e) {
                    LogUtil.e(e, "Could not start Stetho server: %s", localSocketServer.getName());
                }
            }
        }.start();
    }
}
