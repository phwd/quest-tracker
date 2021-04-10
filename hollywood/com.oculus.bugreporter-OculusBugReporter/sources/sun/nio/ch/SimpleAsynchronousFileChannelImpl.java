package sun.nio.ch;

import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.CompletionHandler;
import java.nio.channels.FileLock;
import java.nio.channels.NonReadableChannelException;
import java.nio.channels.NonWritableChannelException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class SimpleAsynchronousFileChannelImpl extends AsynchronousFileChannelImpl {
    private static final FileDispatcher nd = new FileDispatcherImpl();
    private final NativeThreadSet threads = new NativeThreadSet(2);

    private static class DefaultExecutorHolder {
        static final ExecutorService defaultExecutor = ThreadPool.createDefault().executor();

        private DefaultExecutorHolder() {
        }
    }

    SimpleAsynchronousFileChannelImpl(FileDescriptor fdObj, boolean reading, boolean writing, ExecutorService executor) {
        super(fdObj, reading, writing, executor);
    }

    public static AsynchronousFileChannel open(FileDescriptor fdo, boolean reading, boolean writing, ThreadPool pool) {
        return new SimpleAsynchronousFileChannelImpl(fdo, reading, writing, pool == null ? DefaultExecutorHolder.defaultExecutor : pool.executor());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.AsynchronousChannel, java.nio.channels.Channel
    public void close() throws IOException {
        synchronized (this.fdObj) {
            if (!this.closed) {
                this.closed = true;
                invalidateAllLocks();
                this.threads.signalAndWait();
                this.closeLock.writeLock().lock();
                this.closeLock.writeLock().unlock();
                nd.close(this.fdObj);
            }
        }
    }

    @Override // java.nio.channels.AsynchronousFileChannel
    public long size() throws IOException {
        long n;
        int ti = this.threads.add();
        boolean z = true;
        try {
            begin();
            do {
                n = nd.size(this.fdObj);
                if (n != -3) {
                    break;
                }
            } while (isOpen());
            if (n < 0) {
                z = false;
            }
            try {
                end(z);
                return n;
            } finally {
                this.threads.remove(ti);
            }
        } catch (Throwable th) {
            if (0 < 0) {
                z = false;
            }
            end(z);
            throw th;
        }
    }

    @Override // java.nio.channels.AsynchronousFileChannel
    public AsynchronousFileChannel truncate(long size) throws IOException {
        long n;
        if (size < 0) {
            throw new IllegalArgumentException("Negative size");
        } else if (this.writing) {
            int ti = this.threads.add();
            boolean z = true;
            try {
                begin();
                do {
                    n = nd.size(this.fdObj);
                    if (n != -3) {
                        break;
                    }
                } while (isOpen());
                if (size < n && isOpen()) {
                    do {
                        n = (long) nd.truncate(this.fdObj, size);
                        if (n != -3) {
                            break;
                        }
                    } while (isOpen());
                }
                if (n <= 0) {
                    z = false;
                }
                try {
                    end(z);
                    return this;
                } finally {
                    this.threads.remove(ti);
                }
            } catch (Throwable th) {
                if (0 <= 0) {
                    z = false;
                }
                end(z);
                throw th;
            }
        } else {
            throw new NonWritableChannelException();
        }
    }

    @Override // java.nio.channels.AsynchronousFileChannel
    public void force(boolean metaData) throws IOException {
        int n;
        int ti = this.threads.add();
        boolean z = true;
        try {
            begin();
            do {
                n = nd.force(this.fdObj, metaData);
                if (n != -3) {
                    break;
                }
            } while (isOpen());
            if (n < 0) {
                z = false;
            }
            try {
                end(z);
            } finally {
                this.threads.remove(ti);
            }
        } catch (Throwable th) {
            if (0 < 0) {
                z = false;
            }
            end(z);
            throw th;
        }
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.AsynchronousFileChannelImpl
    public <A> Future<FileLock> implLock(final long position, final long size, final boolean shared, final A attachment, final CompletionHandler<FileLock, ? super A> handler) {
        if (shared && !this.reading) {
            throw new NonReadableChannelException();
        } else if (shared || this.writing) {
            final FileLockImpl fli = addToFileLockTable(position, size, shared);
            final PendingFuture<FileLock, A> result = null;
            if (fli == null) {
                Throwable exc = new ClosedChannelException();
                if (handler == null) {
                    return CompletedFuture.withFailure(exc);
                }
                Invoker.invokeIndirectly(handler, attachment, (Object) null, exc, this.executor);
                return null;
            }
            if (handler == null) {
                result = new PendingFuture<>(this);
            }
            boolean executed = false;
            try {
                this.executor.execute(new Runnable() {
                    /* class sun.nio.ch.SimpleAsynchronousFileChannelImpl.AnonymousClass1 */

                    @Override // java.lang.Runnable
                    public void run() {
                        int n;
                        Throwable exc = null;
                        int ti = SimpleAsynchronousFileChannelImpl.this.threads.add();
                        try {
                            SimpleAsynchronousFileChannelImpl.this.begin();
                            do {
                                n = SimpleAsynchronousFileChannelImpl.nd.lock(SimpleAsynchronousFileChannelImpl.this.fdObj, true, position, size, shared);
                                if (n != 2) {
                                    break;
                                }
                            } while (SimpleAsynchronousFileChannelImpl.this.isOpen());
                            if (n != 0 || !SimpleAsynchronousFileChannelImpl.this.isOpen()) {
                                throw new AsynchronousCloseException();
                            }
                            try {
                                SimpleAsynchronousFileChannelImpl.this.threads.remove(ti);
                                CompletionHandler completionHandler = handler;
                                if (completionHandler == null) {
                                    result.setResult(fli, exc);
                                } else {
                                    Invoker.invokeUnchecked(completionHandler, attachment, fli, exc);
                                }
                            } catch (Throwable th) {
                                SimpleAsynchronousFileChannelImpl.this.threads.remove(ti);
                                throw th;
                            }
                        } catch (IOException e) {
                            x = e;
                            SimpleAsynchronousFileChannelImpl.this.removeFromFileLockTable(fli);
                            if (!SimpleAsynchronousFileChannelImpl.this.isOpen()) {
                                x = new AsynchronousCloseException();
                            }
                            exc = x;
                        } finally {
                            SimpleAsynchronousFileChannelImpl.this.end();
                        }
                    }
                });
                executed = true;
                return result;
            } finally {
                if (!executed) {
                    removeFromFileLockTable(fli);
                }
            }
        } else {
            throw new NonWritableChannelException();
        }
    }

    @Override // java.nio.channels.AsynchronousFileChannel
    public FileLock tryLock(long position, long size, boolean shared) throws IOException {
        int n;
        if (shared && !this.reading) {
            throw new NonReadableChannelException();
        } else if (shared || this.writing) {
            FileLockImpl fli = addToFileLockTable(position, size, shared);
            if (fli != null) {
                int ti = this.threads.add();
                boolean gotLock = false;
                try {
                    begin();
                    do {
                        n = nd.lock(this.fdObj, false, position, size, shared);
                        if (n != 2) {
                            break;
                        }
                    } while (isOpen());
                    if (n == 0 && isOpen()) {
                        gotLock = true;
                        return fli;
                    } else if (n == -1) {
                        if (!gotLock) {
                            removeFromFileLockTable(fli);
                        }
                        end();
                        this.threads.remove(ti);
                        return null;
                    } else if (n == 2) {
                        throw new AsynchronousCloseException();
                    } else {
                        throw new AssertionError();
                    }
                } finally {
                    if (!gotLock) {
                        removeFromFileLockTable(fli);
                    }
                    end();
                    this.threads.remove(ti);
                }
            } else {
                throw new ClosedChannelException();
            }
        } else {
            throw new NonWritableChannelException();
        }
    }

    /* access modifiers changed from: protected */
    @Override // sun.nio.ch.AsynchronousFileChannelImpl
    public void implRelease(FileLockImpl fli) throws IOException {
        nd.release(this.fdObj, fli.position(), fli.size());
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.AsynchronousFileChannelImpl
    public <A> Future<Integer> implRead(final ByteBuffer dst, final long position, final A attachment, final CompletionHandler<Integer, ? super A> handler) {
        if (position < 0) {
            throw new IllegalArgumentException("Negative position");
        } else if (!this.reading) {
            throw new NonReadableChannelException();
        } else if (!dst.isReadOnly()) {
            final PendingFuture<Integer, A> result = null;
            if (!isOpen() || dst.remaining() == 0) {
                Throwable exc = isOpen() ? null : new ClosedChannelException();
                if (handler == null) {
                    return CompletedFuture.withResult(0, exc);
                }
                Invoker.invokeIndirectly((CompletionHandler) handler, (Object) attachment, (Object) 0, exc, (Executor) this.executor);
                return null;
            }
            if (handler == null) {
                result = new PendingFuture<>(this);
            }
            this.executor.execute(new Runnable() {
                /* class sun.nio.ch.SimpleAsynchronousFileChannelImpl.AnonymousClass2 */

                @Override // java.lang.Runnable
                public void run() {
                    int n = 0;
                    Throwable exc = null;
                    int ti = SimpleAsynchronousFileChannelImpl.this.threads.add();
                    try {
                        SimpleAsynchronousFileChannelImpl.this.begin();
                        do {
                            n = IOUtil.read(SimpleAsynchronousFileChannelImpl.this.fdObj, dst, position, SimpleAsynchronousFileChannelImpl.nd);
                            if (n != -3) {
                                break;
                            }
                        } while (SimpleAsynchronousFileChannelImpl.this.isOpen());
                        if (n < 0) {
                            if (!SimpleAsynchronousFileChannelImpl.this.isOpen()) {
                                throw new AsynchronousCloseException();
                            }
                        }
                    } catch (IOException e) {
                        x = e;
                        if (!SimpleAsynchronousFileChannelImpl.this.isOpen()) {
                            x = new AsynchronousCloseException();
                        }
                        exc = x;
                    } catch (Throwable th) {
                        SimpleAsynchronousFileChannelImpl.this.end();
                        SimpleAsynchronousFileChannelImpl.this.threads.remove(ti);
                        throw th;
                    }
                    SimpleAsynchronousFileChannelImpl.this.end();
                    SimpleAsynchronousFileChannelImpl.this.threads.remove(ti);
                    CompletionHandler completionHandler = handler;
                    if (completionHandler == null) {
                        result.setResult(Integer.valueOf(n), exc);
                    } else {
                        Invoker.invokeUnchecked(completionHandler, attachment, Integer.valueOf(n), exc);
                    }
                }
            });
            return result;
        } else {
            throw new IllegalArgumentException("Read-only buffer");
        }
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.AsynchronousFileChannelImpl
    public <A> Future<Integer> implWrite(final ByteBuffer src, final long position, final A attachment, final CompletionHandler<Integer, ? super A> handler) {
        if (position < 0) {
            throw new IllegalArgumentException("Negative position");
        } else if (this.writing) {
            final PendingFuture<Integer, A> result = null;
            if (!isOpen() || src.remaining() == 0) {
                Throwable exc = isOpen() ? null : new ClosedChannelException();
                if (handler == null) {
                    return CompletedFuture.withResult(0, exc);
                }
                Invoker.invokeIndirectly((CompletionHandler) handler, (Object) attachment, (Object) 0, exc, (Executor) this.executor);
                return null;
            }
            if (handler == null) {
                result = new PendingFuture<>(this);
            }
            this.executor.execute(new Runnable() {
                /* class sun.nio.ch.SimpleAsynchronousFileChannelImpl.AnonymousClass3 */

                @Override // java.lang.Runnable
                public void run() {
                    int n = 0;
                    Throwable exc = null;
                    int ti = SimpleAsynchronousFileChannelImpl.this.threads.add();
                    try {
                        SimpleAsynchronousFileChannelImpl.this.begin();
                        do {
                            n = IOUtil.write(SimpleAsynchronousFileChannelImpl.this.fdObj, src, position, SimpleAsynchronousFileChannelImpl.nd);
                            if (n != -3) {
                                break;
                            }
                        } while (SimpleAsynchronousFileChannelImpl.this.isOpen());
                        if (n < 0) {
                            if (!SimpleAsynchronousFileChannelImpl.this.isOpen()) {
                                throw new AsynchronousCloseException();
                            }
                        }
                    } catch (IOException e) {
                        x = e;
                        if (!SimpleAsynchronousFileChannelImpl.this.isOpen()) {
                            x = new AsynchronousCloseException();
                        }
                        exc = x;
                    } catch (Throwable th) {
                        SimpleAsynchronousFileChannelImpl.this.end();
                        SimpleAsynchronousFileChannelImpl.this.threads.remove(ti);
                        throw th;
                    }
                    SimpleAsynchronousFileChannelImpl.this.end();
                    SimpleAsynchronousFileChannelImpl.this.threads.remove(ti);
                    CompletionHandler completionHandler = handler;
                    if (completionHandler == null) {
                        result.setResult(Integer.valueOf(n), exc);
                    } else {
                        Invoker.invokeUnchecked(completionHandler, attachment, Integer.valueOf(n), exc);
                    }
                }
            });
            return result;
        } else {
            throw new NonWritableChannelException();
        }
    }
}
