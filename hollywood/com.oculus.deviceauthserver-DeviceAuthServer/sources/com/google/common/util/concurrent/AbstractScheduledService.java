package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.Service;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;
import javax.annotation.concurrent.GuardedBy;

@Beta
public abstract class AbstractScheduledService implements Service {
    private static final Logger logger = Logger.getLogger(AbstractScheduledService.class.getName());
    private final AbstractService delegate = new AbstractService() {
        /* class com.google.common.util.concurrent.AbstractScheduledService.AnonymousClass1 */
        private volatile ScheduledExecutorService executorService;
        private final ReentrantLock lock = new ReentrantLock();
        private volatile Future<?> runningTask;
        private final Runnable task = new Runnable() {
            /* class com.google.common.util.concurrent.AbstractScheduledService.AnonymousClass1.AnonymousClass1 */

            public void run() {
                AnonymousClass1.this.lock.lock();
                try {
                    AbstractScheduledService.this.runOneIteration();
                    AnonymousClass1.this.lock.unlock();
                } catch (Throwable t) {
                    AnonymousClass1.this.lock.unlock();
                    throw t;
                }
            }
        };

        /* access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.AbstractService
        public final void doStart() {
            this.executorService = MoreExecutors.renamingDecorator(AbstractScheduledService.this.executor(), (Supplier<String>) new Supplier<String>() {
                /* class com.google.common.util.concurrent.AbstractScheduledService.AnonymousClass1.AnonymousClass2 */

                @Override // com.google.common.base.Supplier
                public String get() {
                    return AbstractScheduledService.this.serviceName() + " " + AnonymousClass1.this.state();
                }
            });
            this.executorService.execute(new Runnable() {
                /* class com.google.common.util.concurrent.AbstractScheduledService.AnonymousClass1.AnonymousClass3 */

                public void run() {
                    AnonymousClass1.this.lock.lock();
                    try {
                        AbstractScheduledService.this.startUp();
                        AnonymousClass1.this.runningTask = AbstractScheduledService.this.scheduler().schedule(AbstractScheduledService.this.delegate, AnonymousClass1.this.executorService, AnonymousClass1.this.task);
                        AnonymousClass1.this.notifyStarted();
                        AnonymousClass1.this.lock.unlock();
                    } catch (Throwable t) {
                        AnonymousClass1.this.lock.unlock();
                        throw t;
                    }
                }
            });
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.AbstractService
        public final void doStop() {
            this.runningTask.cancel(false);
            this.executorService.execute(new Runnable() {
                /* class com.google.common.util.concurrent.AbstractScheduledService.AnonymousClass1.AnonymousClass4 */

                public void run() {
                    try {
                        AnonymousClass1.this.lock.lock();
                        try {
                            if (AnonymousClass1.this.state() == Service.State.STOPPING) {
                                AbstractScheduledService.this.shutDown();
                                AnonymousClass1.this.lock.unlock();
                                AnonymousClass1.this.notifyStopped();
                            }
                        } finally {
                            AnonymousClass1.this.lock.unlock();
                        }
                    } catch (Throwable t) {
                        AnonymousClass1.this.notifyFailed(t);
                        throw Throwables.propagate(t);
                    }
                }
            });
        }
    };

    /* access modifiers changed from: protected */
    public abstract void runOneIteration() throws Exception;

    /* access modifiers changed from: protected */
    public abstract Scheduler scheduler();

    public static abstract class Scheduler {
        /* access modifiers changed from: package-private */
        public abstract Future<?> schedule(AbstractService abstractService, ScheduledExecutorService scheduledExecutorService, Runnable runnable);

        public static Scheduler newFixedDelaySchedule(final long initialDelay, final long delay, final TimeUnit unit) {
            return new Scheduler() {
                /* class com.google.common.util.concurrent.AbstractScheduledService.Scheduler.AnonymousClass1 */

                @Override // com.google.common.util.concurrent.AbstractScheduledService.Scheduler
                public Future<?> schedule(AbstractService service, ScheduledExecutorService executor, Runnable task) {
                    return executor.scheduleWithFixedDelay(task, initialDelay, delay, unit);
                }
            };
        }

        public static Scheduler newFixedRateSchedule(final long initialDelay, final long period, final TimeUnit unit) {
            return new Scheduler() {
                /* class com.google.common.util.concurrent.AbstractScheduledService.Scheduler.AnonymousClass2 */

                @Override // com.google.common.util.concurrent.AbstractScheduledService.Scheduler
                public Future<?> schedule(AbstractService service, ScheduledExecutorService executor, Runnable task) {
                    return executor.scheduleAtFixedRate(task, initialDelay, period, unit);
                }
            };
        }

        private Scheduler() {
        }
    }

    protected AbstractScheduledService() {
    }

    /* access modifiers changed from: protected */
    public void startUp() throws Exception {
    }

    /* access modifiers changed from: protected */
    public void shutDown() throws Exception {
    }

    /* access modifiers changed from: protected */
    public ScheduledExecutorService executor() {
        final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            /* class com.google.common.util.concurrent.AbstractScheduledService.AnonymousClass2 */

            public Thread newThread(Runnable runnable) {
                return MoreExecutors.newThread(AbstractScheduledService.this.serviceName(), runnable);
            }
        });
        addListener(new Service.Listener() {
            /* class com.google.common.util.concurrent.AbstractScheduledService.AnonymousClass3 */

            @Override // com.google.common.util.concurrent.Service.Listener
            public void terminated(Service.State from) {
                executor.shutdown();
            }

            @Override // com.google.common.util.concurrent.Service.Listener
            public void failed(Service.State from, Throwable failure) {
                executor.shutdown();
            }
        }, MoreExecutors.directExecutor());
        return executor;
    }

    /* access modifiers changed from: protected */
    public String serviceName() {
        return getClass().getSimpleName();
    }

    public String toString() {
        return serviceName() + " [" + state() + "]";
    }

    @Override // com.google.common.util.concurrent.Service
    public final boolean isRunning() {
        return this.delegate.isRunning();
    }

    @Override // com.google.common.util.concurrent.Service
    public final Service.State state() {
        return this.delegate.state();
    }

    @Override // com.google.common.util.concurrent.Service
    public final void addListener(Service.Listener listener, Executor executor) {
        this.delegate.addListener(listener, executor);
    }

    @Override // com.google.common.util.concurrent.Service
    public final Throwable failureCause() {
        return this.delegate.failureCause();
    }

    @Override // com.google.common.util.concurrent.Service
    public final Service startAsync() {
        this.delegate.startAsync();
        return this;
    }

    @Override // com.google.common.util.concurrent.Service
    public final Service stopAsync() {
        this.delegate.stopAsync();
        return this;
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning() {
        this.delegate.awaitRunning();
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning(long timeout, TimeUnit unit) throws TimeoutException {
        this.delegate.awaitRunning(timeout, unit);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated() {
        this.delegate.awaitTerminated();
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated(long timeout, TimeUnit unit) throws TimeoutException {
        this.delegate.awaitTerminated(timeout, unit);
    }

    @Beta
    public static abstract class CustomScheduler extends Scheduler {
        /* access modifiers changed from: protected */
        public abstract Schedule getNextSchedule() throws Exception;

        public CustomScheduler() {
            super();
        }

        private class ReschedulableCallable extends ForwardingFuture<Void> implements Callable<Void> {
            @GuardedBy("lock")
            private Future<Void> currentFuture;
            private final ScheduledExecutorService executor;
            private final ReentrantLock lock = new ReentrantLock();
            private final AbstractService service;
            private final Runnable wrappedRunnable;

            ReschedulableCallable(AbstractService service2, ScheduledExecutorService executor2, Runnable runnable) {
                this.wrappedRunnable = runnable;
                this.executor = executor2;
                this.service = service2;
            }

            @Override // java.util.concurrent.Callable
            public Void call() throws Exception {
                this.wrappedRunnable.run();
                reschedule();
                return null;
            }

            public void reschedule() {
                this.lock.lock();
                try {
                    if (this.currentFuture == null || !this.currentFuture.isCancelled()) {
                        Schedule schedule = CustomScheduler.this.getNextSchedule();
                        this.currentFuture = this.executor.schedule(this, schedule.delay, schedule.unit);
                    }
                } catch (Throwable th) {
                    this.lock.unlock();
                    throw th;
                }
                this.lock.unlock();
            }

            @Override // com.google.common.util.concurrent.ForwardingFuture
            public boolean cancel(boolean mayInterruptIfRunning) {
                this.lock.lock();
                try {
                    return this.currentFuture.cancel(mayInterruptIfRunning);
                } finally {
                    this.lock.unlock();
                }
            }

            /* access modifiers changed from: protected */
            @Override // com.google.common.collect.ForwardingObject, com.google.common.util.concurrent.ForwardingFuture, com.google.common.util.concurrent.ForwardingFuture
            public Future<Void> delegate() {
                throw new UnsupportedOperationException("Only cancel is supported by this future");
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractScheduledService.Scheduler
        public final Future<?> schedule(AbstractService service, ScheduledExecutorService executor, Runnable runnable) {
            ReschedulableCallable task = new ReschedulableCallable(service, executor, runnable);
            task.reschedule();
            return task;
        }

        /* access modifiers changed from: protected */
        @Beta
        public static final class Schedule {
            private final long delay;
            private final TimeUnit unit;

            public Schedule(long delay2, TimeUnit unit2) {
                this.delay = delay2;
                this.unit = (TimeUnit) Preconditions.checkNotNull(unit2);
            }
        }
    }
}
