package sun.nio.ch;

import dalvik.system.VMDebug;
import java.io.IOException;
import java.nio.channels.spi.AsynchronousChannelProvider;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import sun.nio.ch.Invoker;
import sun.nio.ch.Port;

/* access modifiers changed from: package-private */
public final class EPollPort extends Port {
    private static final int ENOENT = 2;
    private static final int MAX_EPOLL_EVENTS = 512;
    private final Event EXECUTE_TASK_OR_SHUTDOWN = new Event(null, 0);
    private final Event NEED_TO_POLL = new Event(null, 0);
    private final long address;
    private boolean closed;
    private final int epfd = EPoll.epollCreate();
    private final ArrayBlockingQueue<Event> queue;
    private final int[] sp;
    private final AtomicInteger wakeupCount = new AtomicInteger();

    private static native void close0(int i);

    /* access modifiers changed from: private */
    public static native void drain1(int i) throws IOException;

    private static native void interrupt(int i) throws IOException;

    private static native void socketpair(int[] iArr) throws IOException;

    /* access modifiers changed from: package-private */
    public static class Event {
        final Port.PollableChannel channel;
        final int events;

        Event(Port.PollableChannel channel2, int events2) {
            this.channel = channel2;
            this.events = events2;
        }

        /* access modifiers changed from: package-private */
        public Port.PollableChannel channel() {
            return this.channel;
        }

        /* access modifiers changed from: package-private */
        public int events() {
            return this.events;
        }
    }

    EPollPort(AsynchronousChannelProvider provider, ThreadPool pool) throws IOException {
        super(provider, pool);
        int[] sv = new int[2];
        try {
            socketpair(sv);
            EPoll.epollCtl(this.epfd, 1, sv[0], Net.POLLIN);
            this.sp = sv;
            this.address = EPoll.allocatePollArray(512);
            this.queue = new ArrayBlockingQueue<>(512);
            this.queue.offer(this.NEED_TO_POLL);
        } catch (IOException x) {
            close0(this.epfd);
            throw x;
        }
    }

    /* access modifiers changed from: package-private */
    public EPollPort start() {
        startThreads(new EventHandlerTask());
        return this;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void implClose() {
        synchronized (this) {
            if (!this.closed) {
                this.closed = true;
                EPoll.freePollArray(this.address);
                close0(this.sp[0]);
                close0(this.sp[1]);
                close0(this.epfd);
            }
        }
    }

    private void wakeup() {
        if (this.wakeupCount.incrementAndGet() == 1) {
            try {
                interrupt(this.sp[1]);
            } catch (IOException x) {
                throw new AssertionError(x);
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.AsynchronousChannelGroupImpl
    public void executeOnHandlerTask(Runnable task) {
        synchronized (this) {
            if (!this.closed) {
                offerTask(task);
                wakeup();
            } else {
                throw new RejectedExecutionException();
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.AsynchronousChannelGroupImpl
    public void shutdownHandlerTasks() {
        int nThreads = threadCount();
        if (nThreads == 0) {
            implClose();
            return;
        }
        while (true) {
            int nThreads2 = nThreads - 1;
            if (nThreads > 0) {
                wakeup();
                nThreads = nThreads2;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    @Override // sun.nio.ch.Port
    public void startPoll(int fd, int events) {
        int err = EPoll.epollCtl(this.epfd, 3, fd, events | VMDebug.KIND_THREAD_EXT_FREED_OBJECTS);
        if (err == 2) {
            err = EPoll.epollCtl(this.epfd, 1, fd, 1073741824 | events);
        }
        if (err != 0) {
            throw new AssertionError();
        }
    }

    private class EventHandlerTask implements Runnable {
        private EventHandlerTask() {
        }

        /* JADX INFO: finally extract failed */
        private Event poll() throws IOException {
            while (true) {
                try {
                    int n = EPoll.epollWait(EPollPort.this.epfd, EPollPort.this.address, 512);
                    EPollPort.this.fdToChannelLock.readLock().lock();
                    while (true) {
                        int n2 = n - 1;
                        if (n <= 0) {
                            break;
                        }
                        try {
                            long eventAddress = EPoll.getEvent(EPollPort.this.address, n2);
                            int fd = EPoll.getDescriptor(eventAddress);
                            if (fd == EPollPort.this.sp[0]) {
                                if (EPollPort.this.wakeupCount.decrementAndGet() == 0) {
                                    EPollPort.drain1(EPollPort.this.sp[0]);
                                }
                                if (n2 > 0) {
                                    EPollPort.this.queue.offer(EPollPort.this.EXECUTE_TASK_OR_SHUTDOWN);
                                } else {
                                    Event event = EPollPort.this.EXECUTE_TASK_OR_SHUTDOWN;
                                    EPollPort.this.fdToChannelLock.readLock().unlock();
                                    return event;
                                }
                            } else {
                                Port.PollableChannel channel = (Port.PollableChannel) EPollPort.this.fdToChannel.get(Integer.valueOf(fd));
                                if (channel != null) {
                                    Event ev = new Event(channel, EPoll.getEvents(eventAddress));
                                    if (n2 > 0) {
                                        EPollPort.this.queue.offer(ev);
                                    } else {
                                        EPollPort.this.fdToChannelLock.readLock().unlock();
                                        EPollPort.this.queue.offer(EPollPort.this.NEED_TO_POLL);
                                        return ev;
                                    }
                                } else {
                                    continue;
                                }
                            }
                            n = n2;
                        } catch (Throwable th) {
                            EPollPort.this.fdToChannelLock.readLock().unlock();
                            throw th;
                        }
                    }
                    EPollPort.this.fdToChannelLock.readLock().unlock();
                } finally {
                    EPollPort.this.queue.offer(EPollPort.this.NEED_TO_POLL);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            Invoker.GroupAndInvokeCount myGroupAndInvokeCount = Invoker.getGroupAndInvokeCount();
            boolean isPooledThread = myGroupAndInvokeCount != null;
            boolean replaceMe = false;
            while (true) {
                if (isPooledThread) {
                    try {
                        myGroupAndInvokeCount.resetInvokeCount();
                    } catch (Throwable th) {
                        if (EPollPort.this.threadExit(this, replaceMe) == 0 && EPollPort.this.isShutdown()) {
                            EPollPort.this.implClose();
                        }
                        throw th;
                    }
                }
                replaceMe = false;
                try {
                    Event ev = (Event) EPollPort.this.queue.take();
                    if (ev == EPollPort.this.NEED_TO_POLL) {
                        try {
                            ev = poll();
                        } catch (IOException x) {
                            x.printStackTrace();
                            if (EPollPort.this.threadExit(this, false) == 0 && EPollPort.this.isShutdown()) {
                                EPollPort.this.implClose();
                                return;
                            }
                            return;
                        }
                    }
                    if (ev == EPollPort.this.EXECUTE_TASK_OR_SHUTDOWN) {
                        Runnable task = EPollPort.this.pollTask();
                        if (task == null) {
                            break;
                        }
                        replaceMe = true;
                        task.run();
                    } else {
                        try {
                            ev.channel().onEvent(ev.events(), isPooledThread);
                        } catch (Error x2) {
                            throw x2;
                        } catch (RuntimeException x3) {
                            throw x3;
                        }
                    }
                } catch (InterruptedException e) {
                }
            }
            if (EPollPort.this.threadExit(this, false) == 0 && EPollPort.this.isShutdown()) {
                EPollPort.this.implClose();
            }
        }
    }
}
