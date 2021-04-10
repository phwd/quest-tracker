package java.lang;

import android.system.OsConstants;
import dalvik.system.BlockGuard;
import dalvik.system.VMRuntime;
import java.util.ArrayList;
import java.util.List;
import libcore.io.Libcore;

public class Runtime {
    private static Runtime currentRuntime = new Runtime();
    private static boolean finalizeOnExit;
    private volatile String[] mLibPaths = null;
    private List shutdownHooks = new ArrayList();
    private boolean shuttingDown;

    private static native void nativeExit(int i);

    private native void nativeGc();

    public static Runtime getRuntime() {
        return currentRuntime;
    }

    private Runtime() {
    }

    public void exit(int i) {
        Thread[] threadArr;
        synchronized (this) {
            if (!this.shuttingDown) {
                this.shuttingDown = true;
                synchronized (this.shutdownHooks) {
                    threadArr = new Thread[this.shutdownHooks.size()];
                    this.shutdownHooks.toArray(threadArr);
                }
                for (Thread thread : threadArr) {
                    thread.start();
                }
                for (Thread thread2 : threadArr) {
                    try {
                        thread2.join();
                    } catch (InterruptedException unused) {
                    }
                }
                if (finalizeOnExit) {
                    runFinalization();
                }
                nativeExit(i);
            }
        }
    }

    public void addShutdownHook(Thread thread) {
        if (thread == null) {
            throw new NullPointerException("hook == null");
        } else if (this.shuttingDown) {
            throw new IllegalStateException("VM already shutting down");
        } else if (!thread.started) {
            synchronized (this.shutdownHooks) {
                if (!this.shutdownHooks.contains(thread)) {
                    this.shutdownHooks.add(thread);
                } else {
                    throw new IllegalArgumentException("Hook already registered.");
                }
            }
        } else {
            throw new IllegalArgumentException("Hook has already been started");
        }
    }

    public int availableProcessors() {
        return (int) Libcore.os.sysconf(OsConstants._SC_NPROCESSORS_CONF);
    }

    public void gc() {
        BlockGuard.getThreadPolicy().onExplicitGc();
        nativeGc();
    }

    public void runFinalization() {
        VMRuntime.runFinalization(0);
    }
}
