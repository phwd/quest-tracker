package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.Service;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Beta
@GwtIncompatible
public abstract class AbstractExecutionThreadService implements Service {
    private static final Logger logger = Logger.getLogger(AbstractExecutionThreadService.class.getName());
    private final Service delegate = new AbstractService() {
        /* class com.google.common.util.concurrent.AbstractExecutionThreadService.AnonymousClass1 */

        /* access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.AbstractService
        public final void doStart() {
            MoreExecutors.renamingDecorator(AbstractExecutionThreadService.this.executor(), new Supplier<String>() {
                /* class com.google.common.util.concurrent.AbstractExecutionThreadService.AnonymousClass1.AnonymousClass1 */

                @Override // com.google.common.base.Supplier
                public String get() {
                    return AbstractExecutionThreadService.this.serviceName();
                }
            }).execute(new Runnable() {
                /* class com.google.common.util.concurrent.AbstractExecutionThreadService.AnonymousClass1.AnonymousClass2 */

                public void run() {
                    try {
                        AbstractExecutionThreadService.this.startUp();
                        AnonymousClass1.this.notifyStarted();
                        if (AnonymousClass1.this.isRunning()) {
                            try {
                                AbstractExecutionThreadService.this.run();
                            } catch (Throwable t) {
                                try {
                                    AbstractExecutionThreadService.this.shutDown();
                                } catch (Exception ignored) {
                                    AbstractExecutionThreadService.logger.log(Level.WARNING, "Error while attempting to shut down the service after failure.", (Throwable) ignored);
                                }
                                AnonymousClass1.this.notifyFailed(t);
                                return;
                            }
                        }
                        AbstractExecutionThreadService.this.shutDown();
                        AnonymousClass1.this.notifyStopped();
                    } catch (Throwable t2) {
                        AnonymousClass1.this.notifyFailed(t2);
                    }
                }
            });
        }

        /* access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.AbstractService
        public void doStop() {
            AbstractExecutionThreadService.this.triggerShutdown();
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public String toString() {
            return AbstractExecutionThreadService.this.toString();
        }
    };

    /* access modifiers changed from: protected */
    public abstract void run() throws Exception;

    protected AbstractExecutionThreadService() {
    }

    /* access modifiers changed from: protected */
    public void startUp() throws Exception {
    }

    /* access modifiers changed from: protected */
    public void shutDown() throws Exception {
    }

    /* access modifiers changed from: protected */
    public void triggerShutdown() {
    }

    /* access modifiers changed from: protected */
    public Executor executor() {
        return new Executor() {
            /* class com.google.common.util.concurrent.AbstractExecutionThreadService.AnonymousClass2 */

            public void execute(Runnable command) {
                MoreExecutors.newThread(AbstractExecutionThreadService.this.serviceName(), command).start();
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
    @CanIgnoreReturnValue
    public final Service startAsync() {
        this.delegate.startAsync();
        return this;
    }

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
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
