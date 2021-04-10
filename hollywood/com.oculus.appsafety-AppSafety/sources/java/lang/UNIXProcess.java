package java.lang;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ProcessBuilder;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* access modifiers changed from: package-private */
public final class UNIXProcess extends Process {
    private static final Executor processReaperExecutor = ((Executor) AccessController.doPrivileged(new PrivilegedAction<Executor>() {
        /* class java.lang.UNIXProcess.AnonymousClass1 */

        @Override // java.security.PrivilegedAction
        public Executor run() {
            return Executors.newCachedThreadPool(new ProcessReaperThreadFactory());
        }
    }));
    private int exitcode;
    private boolean hasExited;
    private final int pid;
    private InputStream stderr;
    private OutputStream stdin;
    private InputStream stdout;

    private static native void destroyProcess(int i);

    private native int forkAndExec(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, int i2, byte[] bArr4, int[] iArr, boolean z) throws IOException;

    private static native void initIDs();

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private native int waitForProcessExit(int i);

    /* access modifiers changed from: private */
    public static class ProcessReaperThreadFactory implements ThreadFactory {
        private static final ThreadGroup group = getRootThreadGroup();

        private ProcessReaperThreadFactory() {
        }

        private static ThreadGroup getRootThreadGroup() {
            return (ThreadGroup) AccessController.doPrivileged(new PrivilegedAction<ThreadGroup>() {
                /* class java.lang.UNIXProcess.ProcessReaperThreadFactory.AnonymousClass1 */

                @Override // java.security.PrivilegedAction
                public ThreadGroup run() {
                    ThreadGroup root = Thread.currentThread().getThreadGroup();
                    while (root.getParent() != null) {
                        root = root.getParent();
                    }
                    return root;
                }
            });
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable grimReaper) {
            Thread t = new Thread(group, grimReaper, "process reaper", 32768);
            t.setDaemon(true);
            t.setPriority(10);
            return t;
        }
    }

    static {
        initIDs();
    }

    UNIXProcess(byte[] prog, byte[] argBlock, int argc, byte[] envBlock, int envc, byte[] dir, final int[] fds, boolean redirectErrorStream) throws IOException {
        this.pid = forkAndExec(prog, argBlock, argc, envBlock, envc, dir, fds, redirectErrorStream);
        try {
            AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                /* class java.lang.UNIXProcess.AnonymousClass2 */

                @Override // java.security.PrivilegedExceptionAction
                public Void run() throws IOException {
                    UNIXProcess.this.initStreams(fds);
                    return null;
                }
            });
        } catch (PrivilegedActionException ex) {
            throw ((IOException) ex.getException());
        }
    }

    static FileDescriptor newFileDescriptor(int fd) {
        FileDescriptor fileDescriptor = new FileDescriptor();
        fileDescriptor.setInt$(fd);
        return fileDescriptor;
    }

    /* access modifiers changed from: package-private */
    public void initStreams(int[] fds) throws IOException {
        OutputStream outputStream;
        InputStream inputStream;
        InputStream inputStream2;
        if (fds[0] == -1) {
            outputStream = ProcessBuilder.NullOutputStream.INSTANCE;
        } else {
            outputStream = new ProcessPipeOutputStream(fds[0]);
        }
        this.stdin = outputStream;
        if (fds[1] == -1) {
            inputStream = ProcessBuilder.NullInputStream.INSTANCE;
        } else {
            inputStream = new ProcessPipeInputStream(fds[1]);
        }
        this.stdout = inputStream;
        if (fds[2] == -1) {
            inputStream2 = ProcessBuilder.NullInputStream.INSTANCE;
        } else {
            inputStream2 = new ProcessPipeInputStream(fds[2]);
        }
        this.stderr = inputStream2;
        processReaperExecutor.execute(new Runnable() {
            /* class java.lang.UNIXProcess.AnonymousClass3 */

            @Override // java.lang.Runnable
            public void run() {
                UNIXProcess uNIXProcess = UNIXProcess.this;
                UNIXProcess.this.processExited(uNIXProcess.waitForProcessExit(uNIXProcess.pid));
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void processExited(int exitcode2) {
        synchronized (this) {
            this.exitcode = exitcode2;
            this.hasExited = true;
            notifyAll();
        }
        InputStream inputStream = this.stdout;
        if (inputStream instanceof ProcessPipeInputStream) {
            ((ProcessPipeInputStream) inputStream).processExited();
        }
        InputStream inputStream2 = this.stderr;
        if (inputStream2 instanceof ProcessPipeInputStream) {
            ((ProcessPipeInputStream) inputStream2).processExited();
        }
        OutputStream outputStream = this.stdin;
        if (outputStream instanceof ProcessPipeOutputStream) {
            ((ProcessPipeOutputStream) outputStream).processExited();
        }
    }

    @Override // java.lang.Process
    public OutputStream getOutputStream() {
        return this.stdin;
    }

    @Override // java.lang.Process
    public InputStream getInputStream() {
        return this.stdout;
    }

    @Override // java.lang.Process
    public InputStream getErrorStream() {
        return this.stderr;
    }

    @Override // java.lang.Process
    public synchronized int waitFor() throws InterruptedException {
        while (!this.hasExited) {
            wait();
        }
        return this.exitcode;
    }

    @Override // java.lang.Process
    public synchronized int exitValue() {
        if (this.hasExited) {
        } else {
            throw new IllegalThreadStateException("process hasn't exited");
        }
        return this.exitcode;
    }

    @Override // java.lang.Process
    public void destroy() {
        synchronized (this) {
            if (!this.hasExited) {
                destroyProcess(this.pid);
            }
        }
        try {
            this.stdin.close();
        } catch (IOException e) {
        }
        try {
            this.stdout.close();
        } catch (IOException e2) {
        }
        try {
            this.stderr.close();
        } catch (IOException e3) {
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Process[pid=");
        sb.append(this.pid);
        if (this.hasExited) {
            sb.append(" ,hasExited=true, exitcode=");
            sb.append(this.exitcode);
            sb.append("]");
        } else {
            sb.append(", hasExited=false]");
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public static class ProcessPipeInputStream extends BufferedInputStream {
        ProcessPipeInputStream(int fd) {
            super(new FileInputStream(UNIXProcess.newFileDescriptor(fd), true));
        }

        private static byte[] drainInputStream(InputStream in) throws IOException {
            if (in == null) {
                return null;
            }
            int n = 0;
            byte[] a = null;
            while (true) {
                int j = in.available();
                if (j <= 0) {
                    break;
                }
                a = a == null ? new byte[j] : Arrays.copyOf(a, n + j);
                n += in.read(a, n, j);
            }
            return (a == null || n == a.length) ? a : Arrays.copyOf(a, n);
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized void processExited() {
            /*
                r3 = this;
                monitor-enter(r3)
                java.io.InputStream r0 = r3.in     // Catch:{ IOException -> 0x0026, all -> 0x0023 }
                if (r0 == 0) goto L_0x0022
                byte[] r1 = drainInputStream(r0)     // Catch:{ IOException -> 0x0020, all -> 0x0023 }
                r0.close()     // Catch:{ IOException -> 0x0020, all -> 0x0023 }
                if (r1 != 0) goto L_0x0011
                java.lang.ProcessBuilder$NullInputStream r2 = java.lang.ProcessBuilder.NullInputStream.INSTANCE     // Catch:{ IOException -> 0x0020, all -> 0x0023 }
                goto L_0x0016
            L_0x0011:
                java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x0020, all -> 0x0023 }
                r2.<init>(r1)     // Catch:{ IOException -> 0x0020, all -> 0x0023 }
            L_0x0016:
                r3.in = r2     // Catch:{ IOException -> 0x0020, all -> 0x0023 }
                byte[] r2 = r3.buf     // Catch:{ IOException -> 0x0020, all -> 0x0023 }
                if (r2 != 0) goto L_0x0022
                r2 = 0
                r3.in = r2     // Catch:{ IOException -> 0x0020, all -> 0x0023 }
                goto L_0x0022
            L_0x0020:
                r0 = move-exception
                goto L_0x0027
            L_0x0022:
                goto L_0x0027
            L_0x0023:
                r0 = move-exception
                monitor-exit(r3)
                throw r0
            L_0x0026:
                r0 = move-exception
            L_0x0027:
                monitor-exit(r3)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: java.lang.UNIXProcess.ProcessPipeInputStream.processExited():void");
        }
    }

    /* access modifiers changed from: package-private */
    public static class ProcessPipeOutputStream extends BufferedOutputStream {
        ProcessPipeOutputStream(int fd) {
            super(new FileOutputStream(UNIXProcess.newFileDescriptor(fd), true));
        }

        /* access modifiers changed from: package-private */
        public synchronized void processExited() {
            OutputStream out = this.out;
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
                this.out = ProcessBuilder.NullOutputStream.INSTANCE;
            }
        }
    }
}
