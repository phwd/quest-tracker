package sun.nio.ch;

import java.io.Closeable;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.channels.Channel;
import java.nio.channels.ShutdownChannelGroupException;
import java.nio.channels.spi.AsynchronousChannelProvider;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

abstract class Port extends AsynchronousChannelGroupImpl {
    protected final Map<Integer, PollableChannel> fdToChannel = new HashMap();
    protected final ReadWriteLock fdToChannelLock = new ReentrantReadWriteLock();

    /* access modifiers changed from: package-private */
    public interface PollableChannel extends Closeable {
        void onEvent(int i, boolean z);
    }

    /* access modifiers changed from: package-private */
    public abstract void startPoll(int i, int i2);

    Port(AsynchronousChannelProvider provider, ThreadPool pool) {
        super(provider, pool);
    }

    /* access modifiers changed from: package-private */
    public final void register(int fd, PollableChannel ch) {
        this.fdToChannelLock.writeLock().lock();
        try {
            if (!isShutdown()) {
                this.fdToChannel.put(Integer.valueOf(fd), ch);
                return;
            }
            throw new ShutdownChannelGroupException();
        } finally {
            this.fdToChannelLock.writeLock().unlock();
        }
    }

    /* access modifiers changed from: protected */
    public void preUnregister(int fd) {
    }

    /* access modifiers changed from: package-private */
    public final void unregister(int fd) {
        boolean checkForShutdown = false;
        preUnregister(fd);
        this.fdToChannelLock.writeLock().lock();
        try {
            this.fdToChannel.remove(Integer.valueOf(fd));
            if (this.fdToChannel.isEmpty()) {
                checkForShutdown = true;
            }
            if (checkForShutdown && isShutdown()) {
                try {
                    shutdownNow();
                } catch (IOException e) {
                }
            }
        } finally {
            this.fdToChannelLock.writeLock().unlock();
        }
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.AsynchronousChannelGroupImpl
    public final boolean isEmpty() {
        this.fdToChannelLock.writeLock().lock();
        try {
            return this.fdToChannel.isEmpty();
        } finally {
            this.fdToChannelLock.writeLock().unlock();
        }
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.AsynchronousChannelGroupImpl
    public final Object attachForeignChannel(final Channel channel, FileDescriptor fd) {
        int fdVal = IOUtil.fdVal(fd);
        register(fdVal, new PollableChannel() {
            /* class sun.nio.ch.Port.AnonymousClass1 */

            @Override // sun.nio.ch.Port.PollableChannel
            public void onEvent(int events, boolean mayInvokeDirect) {
            }

            @Override // java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                channel.close();
            }
        });
        return Integer.valueOf(fdVal);
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.AsynchronousChannelGroupImpl
    public final void detachForeignChannel(Object key) {
        unregister(((Integer) key).intValue());
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.AsynchronousChannelGroupImpl
    public final void closeAllChannels() {
        int count;
        Throwable th;
        PollableChannel[] channels = new PollableChannel[128];
        do {
            this.fdToChannelLock.writeLock().lock();
            count = 0;
            try {
                Iterator<Integer> it = this.fdToChannel.keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    int count2 = count + 1;
                    try {
                        channels[count] = this.fdToChannel.get(it.next());
                        if (count2 >= 128) {
                            count = count2;
                            break;
                        }
                        count = count2;
                    } catch (Throwable th2) {
                        th = th2;
                        this.fdToChannelLock.writeLock().unlock();
                        throw th;
                    }
                }
                this.fdToChannelLock.writeLock().unlock();
                for (int i = 0; i < count; i++) {
                    try {
                        channels[i].close();
                    } catch (IOException e) {
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                this.fdToChannelLock.writeLock().unlock();
                throw th;
            }
        } while (count > 0);
    }
}
