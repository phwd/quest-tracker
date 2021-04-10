package sun.nio.ch;

import dalvik.system.CloseGuard;
import java.io.FileDescriptor;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AlreadyConnectedException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.CompletionHandler;
import java.nio.channels.ConnectionPendingException;
import java.nio.channels.ShutdownChannelGroupException;
import java.security.AccessController;
import java.util.concurrent.Future;
import sun.net.NetHooks;
import sun.nio.ch.Port;
import sun.security.action.GetPropertyAction;

/* access modifiers changed from: package-private */
public class UnixAsynchronousSocketChannelImpl extends AsynchronousSocketChannelImpl implements Port.PollableChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean disableSynchronousRead;
    private static final NativeDispatcher nd = new SocketDispatcher();
    private Object connectAttachment;
    private PendingFuture<Void, Object> connectFuture;
    private CompletionHandler<Void, Object> connectHandler;
    private boolean connectPending;
    private final int fdVal;
    private final CloseGuard guard = CloseGuard.get();
    private boolean isGatheringWrite;
    private boolean isScatteringRead;
    private SocketAddress pendingRemote;
    private final Port port;
    private Object readAttachment;
    private ByteBuffer readBuffer;
    private ByteBuffer[] readBuffers;
    private PendingFuture<Number, Object> readFuture;
    private CompletionHandler<Number, Object> readHandler;
    private boolean readPending;
    private Runnable readTimeoutTask = new Runnable() {
        /* class sun.nio.ch.UnixAsynchronousSocketChannelImpl.AnonymousClass1 */

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x003b, code lost:
            if (r4 != null) goto L_0x0041;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x003d, code lost:
            r4.setFailure(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0041, code lost:
            sun.nio.ch.Invoker.invokeIndirectly(r6.this$0, r4, r4, (java.lang.Object) null, r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0030, code lost:
            r6.this$0.enableReading(true);
            r3 = new java.nio.channels.InterruptedByTimeoutException();
         */
        @Override // java.lang.Runnable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r6 = this;
                r0 = 0
                r1 = 0
                r2 = 0
                sun.nio.ch.UnixAsynchronousSocketChannelImpl r3 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.this
                java.lang.Object r3 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.access$000(r3)
                monitor-enter(r3)
                sun.nio.ch.UnixAsynchronousSocketChannelImpl r4 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.this     // Catch:{ all -> 0x0048 }
                boolean r4 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.access$100(r4)     // Catch:{ all -> 0x0048 }
                if (r4 != 0) goto L_0x0014
                monitor-exit(r3)     // Catch:{ all -> 0x0048 }
                return
            L_0x0014:
                sun.nio.ch.UnixAsynchronousSocketChannelImpl r4 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.this     // Catch:{ all -> 0x0048 }
                r5 = 0
                sun.nio.ch.UnixAsynchronousSocketChannelImpl.access$102(r4, r5)     // Catch:{ all -> 0x0048 }
                sun.nio.ch.UnixAsynchronousSocketChannelImpl r4 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.this     // Catch:{ all -> 0x0048 }
                java.nio.channels.CompletionHandler r4 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.access$200(r4)     // Catch:{ all -> 0x0048 }
                r0 = r4
                sun.nio.ch.UnixAsynchronousSocketChannelImpl r4 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.this     // Catch:{ all -> 0x0048 }
                java.lang.Object r4 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.access$300(r4)     // Catch:{ all -> 0x0048 }
                r1 = r4
                sun.nio.ch.UnixAsynchronousSocketChannelImpl r4 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.this     // Catch:{ all -> 0x0048 }
                sun.nio.ch.PendingFuture r4 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.access$400(r4)     // Catch:{ all -> 0x0048 }
                r2 = r4
                monitor-exit(r3)     // Catch:{ all -> 0x0048 }
                sun.nio.ch.UnixAsynchronousSocketChannelImpl r3 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.this
                r4 = 1
                r3.enableReading(r4)
                java.nio.channels.InterruptedByTimeoutException r3 = new java.nio.channels.InterruptedByTimeoutException
                r3.<init>()
                if (r0 != 0) goto L_0x0041
                r2.setFailure(r3)
                goto L_0x0047
            L_0x0041:
                sun.nio.ch.UnixAsynchronousSocketChannelImpl r4 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.this
                r5 = 0
                sun.nio.ch.Invoker.invokeIndirectly(r4, r0, r1, r5, r3)
            L_0x0047:
                return
            L_0x0048:
                r4 = move-exception
                monitor-exit(r3)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.UnixAsynchronousSocketChannelImpl.AnonymousClass1.run():void");
        }
    };
    private Future<?> readTimer;
    private final Object updateLock = new Object();
    private Object writeAttachment;
    private ByteBuffer writeBuffer;
    private ByteBuffer[] writeBuffers;
    private PendingFuture<Number, Object> writeFuture;
    private CompletionHandler<Number, Object> writeHandler;
    private boolean writePending;
    private Runnable writeTimeoutTask = new Runnable() {
        /* class sun.nio.ch.UnixAsynchronousSocketChannelImpl.AnonymousClass2 */

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x003b, code lost:
            if (r4 == null) goto L_0x0044;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x003d, code lost:
            sun.nio.ch.Invoker.invokeIndirectly(r6.this$0, r4, r4, (java.lang.Object) null, r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0044, code lost:
            r4.setFailure(r3);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x0030, code lost:
            r6.this$0.enableWriting(true);
            r3 = new java.nio.channels.InterruptedByTimeoutException();
         */
        @Override // java.lang.Runnable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r6 = this;
                r0 = 0
                r1 = 0
                r2 = 0
                sun.nio.ch.UnixAsynchronousSocketChannelImpl r3 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.this
                java.lang.Object r3 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.access$000(r3)
                monitor-enter(r3)
                sun.nio.ch.UnixAsynchronousSocketChannelImpl r4 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.this     // Catch:{ all -> 0x0048 }
                boolean r4 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.access$500(r4)     // Catch:{ all -> 0x0048 }
                if (r4 != 0) goto L_0x0014
                monitor-exit(r3)     // Catch:{ all -> 0x0048 }
                return
            L_0x0014:
                sun.nio.ch.UnixAsynchronousSocketChannelImpl r4 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.this     // Catch:{ all -> 0x0048 }
                r5 = 0
                sun.nio.ch.UnixAsynchronousSocketChannelImpl.access$502(r4, r5)     // Catch:{ all -> 0x0048 }
                sun.nio.ch.UnixAsynchronousSocketChannelImpl r4 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.this     // Catch:{ all -> 0x0048 }
                java.nio.channels.CompletionHandler r4 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.access$600(r4)     // Catch:{ all -> 0x0048 }
                r0 = r4
                sun.nio.ch.UnixAsynchronousSocketChannelImpl r4 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.this     // Catch:{ all -> 0x0048 }
                java.lang.Object r4 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.access$700(r4)     // Catch:{ all -> 0x0048 }
                r1 = r4
                sun.nio.ch.UnixAsynchronousSocketChannelImpl r4 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.this     // Catch:{ all -> 0x0048 }
                sun.nio.ch.PendingFuture r4 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.access$800(r4)     // Catch:{ all -> 0x0048 }
                r2 = r4
                monitor-exit(r3)     // Catch:{ all -> 0x0048 }
                sun.nio.ch.UnixAsynchronousSocketChannelImpl r3 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.this
                r4 = 1
                r3.enableWriting(r4)
                java.nio.channels.InterruptedByTimeoutException r3 = new java.nio.channels.InterruptedByTimeoutException
                r3.<init>()
                if (r0 == 0) goto L_0x0044
                sun.nio.ch.UnixAsynchronousSocketChannelImpl r4 = sun.nio.ch.UnixAsynchronousSocketChannelImpl.this
                r5 = 0
                sun.nio.ch.Invoker.invokeIndirectly(r4, r0, r1, r5, r3)
                goto L_0x0047
            L_0x0044:
                r2.setFailure(r3)
            L_0x0047:
                return
            L_0x0048:
                r4 = move-exception
                monitor-exit(r3)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.UnixAsynchronousSocketChannelImpl.AnonymousClass2.run():void");
        }
    };
    private Future<?> writeTimer;

    private enum OpType {
        CONNECT,
        READ,
        WRITE
    }

    private static native void checkConnect(int i) throws IOException;

    static {
        String propValue = (String) AccessController.doPrivileged(new GetPropertyAction("sun.nio.ch.disableSynchronousRead", "false"));
        disableSynchronousRead = propValue.length() == 0 ? true : Boolean.valueOf(propValue).booleanValue();
    }

    UnixAsynchronousSocketChannelImpl(Port port2) throws IOException {
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

    UnixAsynchronousSocketChannelImpl(Port port2, FileDescriptor fd, InetSocketAddress remote) throws IOException {
        super(port2, fd, remote);
        this.fdVal = IOUtil.fdVal(fd);
        IOUtil.configureBlocking(fd, false);
        try {
            port2.register(this.fdVal, this);
            this.port = port2;
            this.guard.open("close");
        } catch (ShutdownChannelGroupException x) {
            throw new IOException(x);
        }
    }

    @Override // sun.nio.ch.Groupable
    public AsynchronousChannelGroupImpl group() {
        return this.port;
    }

    private void updateEvents() {
        int events = 0;
        if (this.readPending) {
            events = 0 | Net.POLLIN;
        }
        if (this.connectPending || this.writePending) {
            events |= Net.POLLOUT;
        }
        if (events != 0) {
            this.port.startPoll(this.fdVal, events);
        }
    }

    private void lockAndUpdateEvents() {
        synchronized (this.updateLock) {
            updateEvents();
        }
    }

    private void finish(boolean mayInvokeDirect, boolean readable, boolean writable) {
        boolean finishRead = false;
        boolean finishWrite = false;
        boolean finishConnect = false;
        synchronized (this.updateLock) {
            if (readable) {
                try {
                    if (this.readPending) {
                        this.readPending = false;
                        finishRead = true;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            if (writable) {
                if (this.writePending) {
                    this.writePending = false;
                    finishWrite = true;
                } else if (this.connectPending) {
                    this.connectPending = false;
                    finishConnect = true;
                }
            }
        }
        if (finishRead) {
            if (finishWrite) {
                finishWrite(false);
            }
            finishRead(mayInvokeDirect);
            return;
        }
        if (finishWrite) {
            finishWrite(mayInvokeDirect);
        }
        if (finishConnect) {
            finishConnect(mayInvokeDirect);
        }
    }

    @Override // sun.nio.ch.Port.PollableChannel
    public void onEvent(int events, boolean mayInvokeDirect) {
        boolean writable = true;
        boolean readable = (Net.POLLIN & events) > 0;
        if ((Net.POLLOUT & events) <= 0) {
            writable = false;
        }
        if (((Net.POLLERR | Net.POLLHUP) & events) > 0) {
            readable = true;
            writable = true;
        }
        finish(mayInvokeDirect, readable, writable);
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.AsynchronousSocketChannelImpl
    public void implClose() throws IOException {
        this.guard.close();
        this.port.unregister(this.fdVal);
        nd.close(this.fd);
        finish(false, true, true);
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

    @Override // sun.nio.ch.Cancellable
    public void onCancel(PendingFuture<?, ?> task) {
        if (task.getContext() == OpType.CONNECT) {
            killConnect();
        }
        if (task.getContext() == OpType.READ) {
            killReading();
        }
        if (task.getContext() == OpType.WRITE) {
            killWriting();
        }
    }

    private void setConnected() throws IOException {
        synchronized (this.stateLock) {
            this.state = 2;
            this.localAddress = Net.localAddress(this.fd);
            this.remoteAddress = (InetSocketAddress) this.pendingRemote;
        }
    }

    /* JADX INFO: Multiple debug info for r1v0 java.nio.channels.CompletionHandler<java.lang.Void, java.lang.Object>: [D('suppressed' java.lang.Throwable), D('handler' java.nio.channels.CompletionHandler<java.lang.Void, java.lang.Object>)] */
    private void finishConnect(boolean mayInvokeDirect) {
        Throwable e = null;
        try {
            begin();
            checkConnect(this.fdVal);
            setConnected();
        } catch (Throwable th) {
            end();
            throw th;
        }
        end();
        if (e != null) {
            try {
                close();
            } catch (Throwable suppressed) {
                e.addSuppressed(suppressed);
            }
        }
        CompletionHandler<Void, Object> handler = this.connectHandler;
        Object att = this.connectAttachment;
        PendingFuture<Void, Object> future = this.connectFuture;
        if (handler == null) {
            future.setResult(null, e);
        } else if (mayInvokeDirect) {
            Invoker.invokeUnchecked(handler, att, null, e);
        } else {
            Invoker.invokeIndirectly(this, handler, att, (Object) null, e);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.AsynchronousSocketChannelImpl
    public <A> Future<Void> implConnect(SocketAddress remote, A attachment, CompletionHandler<Void, ? super A> handler) {
        boolean notifyBeforeTcpConnect;
        if (!isOpen()) {
            Throwable e = new ClosedChannelException();
            if (handler == null) {
                return CompletedFuture.withFailure(e);
            }
            Invoker.invoke(this, handler, attachment, null, e);
            return null;
        }
        InetSocketAddress isa = Net.checkAddress(remote);
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkConnect(isa.getAddress().getHostAddress(), isa.getPort());
        }
        synchronized (this.stateLock) {
            if (this.state == 2) {
                throw new AlreadyConnectedException();
            } else if (this.state != 1) {
                this.state = 1;
                this.pendingRemote = remote;
                notifyBeforeTcpConnect = this.localAddress == null;
            } else {
                throw new ConnectionPendingException();
            }
        }
        Throwable e2 = null;
        try {
            begin();
            if (notifyBeforeTcpConnect) {
                NetHooks.beforeTcpConnect(this.fd, isa.getAddress(), isa.getPort());
            }
            if (Net.connect(this.fd, isa.getAddress(), isa.getPort()) == -2) {
                PendingFuture<Void, A> result = null;
                synchronized (this.updateLock) {
                    if (handler == null) {
                        result = new PendingFuture<>(this, OpType.CONNECT);
                        this.connectFuture = result;
                    } else {
                        this.connectHandler = handler;
                        this.connectAttachment = attachment;
                    }
                    this.connectPending = true;
                    updateEvents();
                }
                end();
                return result;
            }
            setConnected();
            end();
            if (e2 != null) {
                try {
                    close();
                } catch (Throwable suppressed) {
                    e2.addSuppressed(suppressed);
                }
            }
            if (handler == null) {
                return CompletedFuture.withResult(null, e2);
            }
            Invoker.invoke(this, handler, attachment, null, e2);
            return null;
        } catch (Throwable th) {
            end();
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004e, code lost:
        if ((r1 instanceof java.nio.channels.AsynchronousCloseException) == false) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0062, code lost:
        if ((r1 instanceof java.nio.channels.AsynchronousCloseException) != false) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0064, code lost:
        lockAndUpdateEvents();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0067, code lost:
        end();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x006b, code lost:
        if (r6 == null) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x006d, code lost:
        r6.cancel(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0071, code lost:
        if (r1 == null) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0074, code lost:
        if (r2 == false) goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0076, code lost:
        r7 = java.lang.Long.valueOf((long) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x007c, code lost:
        r7 = java.lang.Integer.valueOf(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0081, code lost:
        if (r3 != null) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0083, code lost:
        r5.setResult(r7, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0087, code lost:
        if (r14 == false) goto L_0x008d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0089, code lost:
        sun.nio.ch.Invoker.invokeUnchecked(r3, r4, r7, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x008d, code lost:
        sun.nio.ch.Invoker.invokeIndirectly(r13, r3, r4, r7, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void finishRead(boolean r14) {
        /*
        // Method dump skipped, instructions count: 157
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.UnixAsynchronousSocketChannelImpl.finishRead(boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00cc, code lost:
        if (1 != 0) goto L_0x00d1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00ce, code lost:
        enableReading();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00d1, code lost:
        end();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00d4, code lost:
        return r12;
     */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x00fa A[Catch:{ all -> 0x012d }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0103 A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0128  */
    @Override // sun.nio.ch.AsynchronousSocketChannelImpl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <V extends java.lang.Number, A> java.util.concurrent.Future<V> implRead(boolean r20, java.nio.ByteBuffer r21, java.nio.ByteBuffer[] r22, long r23, java.util.concurrent.TimeUnit r25, A r26, java.nio.channels.CompletionHandler<V, ? super A> r27) {
        /*
        // Method dump skipped, instructions count: 311
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.UnixAsynchronousSocketChannelImpl.implRead(boolean, java.nio.ByteBuffer, java.nio.ByteBuffer[], long, java.util.concurrent.TimeUnit, java.lang.Object, java.nio.channels.CompletionHandler):java.util.concurrent.Future");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x004e, code lost:
        if ((r1 instanceof java.nio.channels.AsynchronousCloseException) == false) goto L_0x0064;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0062, code lost:
        if ((r1 instanceof java.nio.channels.AsynchronousCloseException) != false) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0064, code lost:
        lockAndUpdateEvents();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0067, code lost:
        end();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x006b, code lost:
        if (r6 == null) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x006d, code lost:
        r6.cancel(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0071, code lost:
        if (r1 == null) goto L_0x0074;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0074, code lost:
        if (r2 == false) goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0076, code lost:
        r7 = java.lang.Long.valueOf((long) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x007c, code lost:
        r7 = java.lang.Integer.valueOf(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0081, code lost:
        if (r3 != null) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0083, code lost:
        r5.setResult(r7, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0087, code lost:
        if (r14 == false) goto L_0x008d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0089, code lost:
        sun.nio.ch.Invoker.invokeUnchecked(r3, r4, r7, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x008d, code lost:
        sun.nio.ch.Invoker.invokeIndirectly(r13, r3, r4, r7, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void finishWrite(boolean r14) {
        /*
        // Method dump skipped, instructions count: 157
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.UnixAsynchronousSocketChannelImpl.finishWrite(boolean):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00a9, code lost:
        if (1 != 0) goto L_0x00ae;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00ab, code lost:
        enableWriting();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00ae, code lost:
        end();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00b1, code lost:
        return r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00c1, code lost:
        if (0 == 0) goto L_0x00c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00c3, code lost:
        enableWriting();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00c6, code lost:
        end();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00dc, code lost:
        if (0 == 0) goto L_0x00c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00df, code lost:
        if (r13 == null) goto L_0x00e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00e1, code lost:
        r15 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00e3, code lost:
        if (r20 == false) goto L_0x00eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x00e5, code lost:
        r15 = java.lang.Long.valueOf((long) r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00eb, code lost:
        r15 = java.lang.Integer.valueOf(r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x00f0, code lost:
        if (r27 == null) goto L_0x00fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x00f2, code lost:
        if (r10 == false) goto L_0x00f8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x00f4, code lost:
        sun.nio.ch.Invoker.invokeDirect(r9, r27, r26, r15, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x00f8, code lost:
        sun.nio.ch.Invoker.invokeIndirectly(r19, r27, r26, r15, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0101, code lost:
        return sun.nio.ch.CompletedFuture.withResult(r15, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:?, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00d5 A[Catch:{ all -> 0x0102 }] */
    @Override // sun.nio.ch.AsynchronousSocketChannelImpl
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <V extends java.lang.Number, A> java.util.concurrent.Future<V> implWrite(boolean r20, java.nio.ByteBuffer r21, java.nio.ByteBuffer[] r22, long r23, java.util.concurrent.TimeUnit r25, A r26, java.nio.channels.CompletionHandler<V, ? super A> r27) {
        /*
        // Method dump skipped, instructions count: 268
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.nio.ch.UnixAsynchronousSocketChannelImpl.implWrite(boolean, java.nio.ByteBuffer, java.nio.ByteBuffer[], long, java.util.concurrent.TimeUnit, java.lang.Object, java.nio.channels.CompletionHandler):java.util.concurrent.Future");
    }
}
