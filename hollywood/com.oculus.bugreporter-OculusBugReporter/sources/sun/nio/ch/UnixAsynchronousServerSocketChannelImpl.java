package sun.nio.ch;

import dalvik.system.CloseGuard;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AcceptPendingException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.CompletionHandler;
import java.nio.channels.NotYetBoundException;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import sun.nio.ch.Port;

class UnixAsynchronousServerSocketChannelImpl extends AsynchronousServerSocketChannelImpl implements Port.PollableChannel {
    private static final NativeDispatcher nd = new SocketDispatcher();
    private AccessControlContext acceptAcc;
    private Object acceptAttachment;
    private PendingFuture<AsynchronousSocketChannel, Object> acceptFuture;
    private CompletionHandler<AsynchronousSocketChannel, Object> acceptHandler;
    private boolean acceptPending;
    private final AtomicBoolean accepting = new AtomicBoolean();
    private final int fdVal;
    private final CloseGuard guard = CloseGuard.get();
    private final Port port;
    private final Object updateLock = new Object();

    private native int accept0(FileDescriptor fileDescriptor, FileDescriptor fileDescriptor2, InetSocketAddress[] inetSocketAddressArr) throws IOException;

    private static native void initIDs();

    static {
        initIDs();
    }

    private void enableAccept() {
        this.accepting.set(false);
    }

    UnixAsynchronousServerSocketChannelImpl(Port port2) throws IOException {
        super(port2);
        try {
            IOUtil.configureBlocking(this.fd, false);
            this.port = port2;
            this.fdVal = IOUtil.fdVal(this.fd);
            port2.register(this.fdVal, this);
            this.guard.open("close");
        } catch (IOException x) {
            nd.close(this.fd);
            throw x;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0030, code lost:
        if (r2 != null) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0032, code lost:
        r4.setFailure(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0036, code lost:
        sun.nio.ch.Invoker.invokeIndirectly(r5, r2, r3, (java.lang.Object) null, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0026, code lost:
        r0 = new java.nio.channels.AsynchronousCloseException();
        r0.setStackTrace(new java.lang.StackTraceElement[0]);
     */
    @Override // sun.nio.ch.AsynchronousServerSocketChannelImpl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void implClose() throws java.io.IOException {
        /*
            r5 = this;
            dalvik.system.CloseGuard r0 = r5.guard
            r0.close()
            sun.nio.ch.Port r0 = r5.port
            int r1 = r5.fdVal
            r0.unregister(r1)
            sun.nio.ch.NativeDispatcher r0 = sun.nio.ch.UnixAsynchronousServerSocketChannelImpl.nd
            java.io.FileDescriptor r1 = r5.fd
            r0.close(r1)
            java.lang.Object r0 = r5.updateLock
            monitor-enter(r0)
            boolean r1 = r5.acceptPending     // Catch:{ all -> 0x003b }
            if (r1 != 0) goto L_0x001c
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            return
        L_0x001c:
            r1 = 0
            r5.acceptPending = r1     // Catch:{ all -> 0x003b }
            java.nio.channels.CompletionHandler<java.nio.channels.AsynchronousSocketChannel, java.lang.Object> r2 = r5.acceptHandler     // Catch:{ all -> 0x003b }
            java.lang.Object r3 = r5.acceptAttachment     // Catch:{ all -> 0x003b }
            sun.nio.ch.PendingFuture<java.nio.channels.AsynchronousSocketChannel, java.lang.Object> r4 = r5.acceptFuture     // Catch:{ all -> 0x003b }
            monitor-exit(r0)     // Catch:{ all -> 0x003b }
            java.nio.channels.AsynchronousCloseException r0 = new java.nio.channels.AsynchronousCloseException
            r0.<init>()
            java.lang.StackTraceElement[] r1 = new java.lang.StackTraceElement[r1]
            r0.setStackTrace(r1)
            if (r2 != 0) goto L_0x0036
            r4.setFailure(r0)
            goto L_0x003a
        L_0x0036:
            r1 = 0
            sun.nio.ch.Invoker.invokeIndirectly(r5, r2, r3, r1, r0)
        L_0x003a:
            return
        L_0x003b:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.UnixAsynchronousServerSocketChannelImpl.implClose():void");
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            if (this.guard != null) {
                this.guard.warnIfOpen();
            }
            close();
        } finally {
            super.finalize();
        }
    }

    @Override // sun.nio.ch.Groupable
    public AsynchronousChannelGroupImpl group() {
        return this.port;
    }

    /* JADX INFO: Multiple debug info for r1v3 java.nio.channels.CompletionHandler<java.nio.channels.AsynchronousSocketChannel, java.lang.Object>: [D('x' java.lang.Throwable), D('handler' java.nio.channels.CompletionHandler<java.nio.channels.AsynchronousSocketChannel, java.lang.Object>)] */
    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        begin();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0020, code lost:
        if (accept(r8.fd, r0, r3) != -2) goto L_0x0045;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0022, code lost:
        r6 = r8.updateLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0024, code lost:
        monitor-enter(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r8.acceptPending = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0027, code lost:
        monitor-exit(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0028, code lost:
        r8.port.startPoll(r8.fdVal, sun.nio.ch.Net.POLLIN);
        end();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0034, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0039, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x003c, code lost:
        if ((r2 instanceof java.nio.channels.ClosedChannelException) != false) goto L_0x003e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0043, code lost:
        r2 = new java.nio.channels.AsynchronousCloseException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0044, code lost:
        r4 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0086, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0087, code lost:
        end();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x008a, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000d, code lost:
        r0 = new java.io.FileDescriptor();
        r3 = new java.net.InetSocketAddress[1];
        r4 = null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0082  */
    @Override // sun.nio.ch.Port.PollableChannel
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onEvent(int r9, boolean r10) {
        /*
        // Method dump skipped, instructions count: 142
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.UnixAsynchronousServerSocketChannelImpl.onEvent(int, boolean):void");
    }

    private AsynchronousSocketChannel finishAccept(FileDescriptor newfd, final InetSocketAddress remote, AccessControlContext acc) throws IOException, SecurityException {
        try {
            AsynchronousSocketChannel ch = new UnixAsynchronousSocketChannelImpl(this.port, newfd, remote);
            if (acc != null) {
                try {
                    AccessController.doPrivileged(new PrivilegedAction<Void>() {
                        /* class sun.nio.ch.UnixAsynchronousServerSocketChannelImpl.AnonymousClass1 */

                        @Override // java.security.PrivilegedAction
                        public Void run() {
                            SecurityManager sm = System.getSecurityManager();
                            if (sm == null) {
                                return null;
                            }
                            sm.checkAccept(remote.getAddress().getHostAddress(), remote.getPort());
                            return null;
                        }
                    }, acc);
                } catch (SecurityException x) {
                    ch.close();
                } catch (Throwable suppressed) {
                    x.addSuppressed(suppressed);
                }
            } else {
                SecurityManager sm = System.getSecurityManager();
                if (sm != null) {
                    sm.checkAccept(remote.getAddress().getHostAddress(), remote.getPort());
                }
            }
            return ch;
            throw x;
        } catch (IOException x2) {
            nd.close(newfd);
            throw x2;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.AsynchronousServerSocketChannelImpl
    public Future<AsynchronousSocketChannel> implAccept(Object att, CompletionHandler<AsynchronousSocketChannel, Object> handler) {
        if (!isOpen()) {
            Throwable e = new ClosedChannelException();
            if (handler == null) {
                return CompletedFuture.withFailure(e);
            }
            Invoker.invoke(this, handler, att, null, e);
            return null;
        } else if (this.localAddress == null) {
            throw new NotYetBoundException();
        } else if (isAcceptKilled()) {
            throw new RuntimeException("Accept not allowed due cancellation");
        } else if (this.accepting.compareAndSet(false, true)) {
            FileDescriptor newfd = new FileDescriptor();
            InetSocketAddress[] isaa = new InetSocketAddress[1];
            Throwable exc = null;
            try {
                begin();
                if (accept(this.fd, newfd, isaa) == -2) {
                    PendingFuture<AsynchronousSocketChannel, Object> result = null;
                    synchronized (this.updateLock) {
                        if (handler == null) {
                            this.acceptHandler = null;
                            result = new PendingFuture<>(this);
                            this.acceptFuture = result;
                        } else {
                            this.acceptHandler = handler;
                            this.acceptAttachment = att;
                        }
                        this.acceptAcc = System.getSecurityManager() == null ? null : AccessController.getContext();
                        this.acceptPending = true;
                    }
                    this.port.startPoll(this.fdVal, Net.POLLIN);
                    end();
                    return result;
                }
            } catch (Throwable th) {
                end();
                throw th;
            }
            end();
            AsynchronousSocketChannel child = null;
            if (exc == null) {
                try {
                    child = finishAccept(newfd, isaa[0], null);
                } catch (Throwable x) {
                    exc = x;
                }
            }
            enableAccept();
            if (handler == null) {
                return CompletedFuture.withResult(child, exc);
            }
            Invoker.invokeIndirectly(this, handler, att, child, exc);
            return null;
        } else {
            throw new AcceptPendingException();
        }
    }

    private int accept(FileDescriptor ssfd, FileDescriptor newfd, InetSocketAddress[] isaa) throws IOException {
        return accept0(ssfd, newfd, isaa);
    }
}
