package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Supplier;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.Service;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Beta
public abstract class AbstractIdleService implements Service {
    private final Service delegate = new AbstractService() {
        /* class com.google.common.util.concurrent.AbstractIdleService.AnonymousClass2 */

        /* access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.AbstractService
        public final void doStart() {
            MoreExecutors.renamingDecorator(AbstractIdleService.this.executor(), AbstractIdleService.this.threadNameSupplier).execute(new Runnable() {
                /* class com.google.common.util.concurrent.AbstractIdleService.AnonymousClass2.AnonymousClass1 */

                public void run() {
                    try {
                        AbstractIdleService.this.startUp();
                        AnonymousClass2.this.notifyStarted();
                    } catch (Throwable t) {
                        AnonymousClass2.this.notifyFailed(t);
                        throw Throwables.propagate(t);
                    }
                }
            });
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.AbstractService
        public final void doStop() {
            MoreExecutors.renamingDecorator(AbstractIdleService.this.executor(), AbstractIdleService.this.threadNameSupplier).execute(new Runnable() {
                /* class com.google.common.util.concurrent.AbstractIdleService.AnonymousClass2.AnonymousClass2 */

                public void run() {
                    try {
                        AbstractIdleService.this.shutDown();
                        AnonymousClass2.this.notifyStopped();
                    } catch (Throwable t) {
                        AnonymousClass2.this.notifyFailed(t);
                        throw Throwables.propagate(t);
                    }
                }
            });
        }
    };
    private final Supplier<String> threadNameSupplier = new Supplier<String>() {
        /* class com.google.common.util.concurrent.AbstractIdleService.AnonymousClass1 */

        @Override // com.google.common.base.Supplier
        public String get() {
            return AbstractIdleService.this.serviceName() + " " + AbstractIdleService.this.state();
        }
    };

    /* access modifiers changed from: protected */
    public abstract void shutDown() throws Exception;

    /* access modifiers changed from: protected */
    public abstract void startUp() throws Exception;

    protected AbstractIdleService() {
    }

    /* access modifiers changed from: protected */
    public Executor executor() {
        return new Executor() {
            /* class com.google.common.util.concurrent.AbstractIdleService.AnonymousClass3 */

            public void execute(Runnable command) {
                MoreExecutors.newThread((String) AbstractIdleService.this.threadNameSupplier.get(), command).start();
            }
        };
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

    /* access modifiers changed from: protected */
    public String serviceName() {
        return getClass().getSimpleName();
    }
}
