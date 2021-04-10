package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.ListenerCallQueue;
import com.google.common.util.concurrent.Monitor;
import com.google.common.util.concurrent.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.Immutable;

@Beta
public abstract class AbstractService implements Service {
    private static final ListenerCallQueue.Callback<Service.Listener> RUNNING_CALLBACK = new ListenerCallQueue.Callback<Service.Listener>("running()") {
        /* class com.google.common.util.concurrent.AbstractService.AnonymousClass2 */

        /* access modifiers changed from: package-private */
        public void call(Service.Listener listener) {
            listener.running();
        }
    };
    private static final ListenerCallQueue.Callback<Service.Listener> STARTING_CALLBACK = new ListenerCallQueue.Callback<Service.Listener>("starting()") {
        /* class com.google.common.util.concurrent.AbstractService.AnonymousClass1 */

        /* access modifiers changed from: package-private */
        public void call(Service.Listener listener) {
            listener.starting();
        }
    };
    private static final ListenerCallQueue.Callback<Service.Listener> STOPPING_FROM_RUNNING_CALLBACK = stoppingCallback(Service.State.RUNNING);
    private static final ListenerCallQueue.Callback<Service.Listener> STOPPING_FROM_STARTING_CALLBACK = stoppingCallback(Service.State.STARTING);
    private static final ListenerCallQueue.Callback<Service.Listener> TERMINATED_FROM_NEW_CALLBACK = terminatedCallback(Service.State.NEW);
    private static final ListenerCallQueue.Callback<Service.Listener> TERMINATED_FROM_RUNNING_CALLBACK = terminatedCallback(Service.State.RUNNING);
    private static final ListenerCallQueue.Callback<Service.Listener> TERMINATED_FROM_STOPPING_CALLBACK = terminatedCallback(Service.State.STOPPING);
    private final Monitor.Guard hasReachedRunning = new Monitor.Guard(this.monitor) {
        /* class com.google.common.util.concurrent.AbstractService.AnonymousClass7 */

        @Override // com.google.common.util.concurrent.Monitor.Guard
        public boolean isSatisfied() {
            return AbstractService.this.state().compareTo(Service.State.RUNNING) >= 0;
        }
    };
    private final Monitor.Guard isStartable = new Monitor.Guard(this.monitor) {
        /* class com.google.common.util.concurrent.AbstractService.AnonymousClass5 */

        @Override // com.google.common.util.concurrent.Monitor.Guard
        public boolean isSatisfied() {
            return AbstractService.this.state() == Service.State.NEW;
        }
    };
    private final Monitor.Guard isStoppable = new Monitor.Guard(this.monitor) {
        /* class com.google.common.util.concurrent.AbstractService.AnonymousClass6 */

        @Override // com.google.common.util.concurrent.Monitor.Guard
        public boolean isSatisfied() {
            return AbstractService.this.state().compareTo(Service.State.RUNNING) <= 0;
        }
    };
    private final Monitor.Guard isStopped = new Monitor.Guard(this.monitor) {
        /* class com.google.common.util.concurrent.AbstractService.AnonymousClass8 */

        @Override // com.google.common.util.concurrent.Monitor.Guard
        public boolean isSatisfied() {
            return AbstractService.this.state().isTerminal();
        }
    };
    @GuardedBy("monitor")
    private final List<ListenerCallQueue<Service.Listener>> listeners = Collections.synchronizedList(new ArrayList());
    private final Monitor monitor = new Monitor();
    @GuardedBy("monitor")
    private volatile StateSnapshot snapshot = new StateSnapshot(Service.State.NEW);

    /* access modifiers changed from: protected */
    public abstract void doStart();

    /* access modifiers changed from: protected */
    public abstract void doStop();

    private static ListenerCallQueue.Callback<Service.Listener> terminatedCallback(final Service.State from) {
        return new ListenerCallQueue.Callback<Service.Listener>("terminated({from = " + from + "})") {
            /* class com.google.common.util.concurrent.AbstractService.AnonymousClass3 */

            /* access modifiers changed from: package-private */
            public void call(Service.Listener listener) {
                listener.terminated(from);
            }
        };
    }

    private static ListenerCallQueue.Callback<Service.Listener> stoppingCallback(final Service.State from) {
        return new ListenerCallQueue.Callback<Service.Listener>("stopping({from = " + from + "})") {
            /* class com.google.common.util.concurrent.AbstractService.AnonymousClass4 */

            /* access modifiers changed from: package-private */
            public void call(Service.Listener listener) {
                listener.stopping(from);
            }
        };
    }

    protected AbstractService() {
    }

    @Override // com.google.common.util.concurrent.Service
    public final Service startAsync() {
        if (this.monitor.enterIf(this.isStartable)) {
            try {
                this.snapshot = new StateSnapshot(Service.State.STARTING);
                starting();
                doStart();
            } catch (Throwable th) {
                this.monitor.leave();
                executeListeners();
                throw th;
            }
            this.monitor.leave();
            executeListeners();
            return this;
        }
        throw new IllegalStateException("Service " + this + " has already been started");
    }

    @Override // com.google.common.util.concurrent.Service
    public final Service stopAsync() {
        if (this.monitor.enterIf(this.isStoppable)) {
            try {
                Service.State previous = state();
                switch (previous) {
                    case NEW:
                        this.snapshot = new StateSnapshot(Service.State.TERMINATED);
                        terminated(Service.State.NEW);
                        break;
                    case STARTING:
                        this.snapshot = new StateSnapshot(Service.State.STARTING, true, null);
                        stopping(Service.State.STARTING);
                        break;
                    case RUNNING:
                        this.snapshot = new StateSnapshot(Service.State.STOPPING);
                        stopping(Service.State.RUNNING);
                        doStop();
                        break;
                    case STOPPING:
                    case TERMINATED:
                    case FAILED:
                        throw new AssertionError("isStoppable is incorrectly implemented, saw: " + previous);
                    default:
                        throw new AssertionError("Unexpected state: " + previous);
                }
            } catch (Throwable th) {
                this.monitor.leave();
                executeListeners();
                throw th;
            }
            this.monitor.leave();
            executeListeners();
        }
        return this;
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning() {
        this.monitor.enterWhenUninterruptibly(this.hasReachedRunning);
        try {
            checkCurrentState(Service.State.RUNNING);
        } finally {
            this.monitor.leave();
        }
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning(long timeout, TimeUnit unit) throws TimeoutException {
        if (this.monitor.enterWhenUninterruptibly(this.hasReachedRunning, timeout, unit)) {
            try {
                checkCurrentState(Service.State.RUNNING);
            } finally {
                this.monitor.leave();
            }
        } else {
            throw new TimeoutException("Timed out waiting for " + this + " to reach the RUNNING state. Current state: " + state());
        }
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated() {
        this.monitor.enterWhenUninterruptibly(this.isStopped);
        try {
            checkCurrentState(Service.State.TERMINATED);
        } finally {
            this.monitor.leave();
        }
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated(long timeout, TimeUnit unit) throws TimeoutException {
        if (this.monitor.enterWhenUninterruptibly(this.isStopped, timeout, unit)) {
            try {
                checkCurrentState(Service.State.TERMINATED);
            } finally {
                this.monitor.leave();
            }
        } else {
            throw new TimeoutException("Timed out waiting for " + this + " to reach a terminal state. Current state: " + state());
        }
    }

    @GuardedBy("monitor")
    private void checkCurrentState(Service.State expected) {
        Service.State actual = state();
        if (actual == expected) {
            return;
        }
        if (actual == Service.State.FAILED) {
            throw new IllegalStateException("Expected the service to be " + expected + ", but the service has FAILED", failureCause());
        }
        throw new IllegalStateException("Expected the service to be " + expected + ", but was " + actual);
    }

    /* access modifiers changed from: protected */
    public final void notifyStarted() {
        this.monitor.enter();
        try {
            if (this.snapshot.state == Service.State.STARTING) {
                if (this.snapshot.shutdownWhenStartupFinishes) {
                    this.snapshot = new StateSnapshot(Service.State.STOPPING);
                    doStop();
                } else {
                    this.snapshot = new StateSnapshot(Service.State.RUNNING);
                    running();
                }
                return;
            }
            IllegalStateException failure = new IllegalStateException("Cannot notifyStarted() when the service is " + this.snapshot.state);
            notifyFailed(failure);
            throw failure;
        } finally {
            this.monitor.leave();
            executeListeners();
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyStopped() {
        this.monitor.enter();
        try {
            Service.State previous = this.snapshot.state;
            if (previous != Service.State.STOPPING) {
                if (previous != Service.State.RUNNING) {
                    IllegalStateException failure = new IllegalStateException("Cannot notifyStopped() when the service is " + previous);
                    notifyFailed(failure);
                    throw failure;
                }
            }
            this.snapshot = new StateSnapshot(Service.State.TERMINATED);
            terminated(previous);
        } finally {
            this.monitor.leave();
            executeListeners();
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyFailed(Throwable cause) {
        Preconditions.checkNotNull(cause);
        this.monitor.enter();
        try {
            Service.State previous = state();
            switch (previous) {
                case NEW:
                case TERMINATED:
                    throw new IllegalStateException("Failed while in state:" + previous, cause);
                case STARTING:
                case RUNNING:
                case STOPPING:
                    this.snapshot = new StateSnapshot(Service.State.FAILED, false, cause);
                    failed(previous, cause);
                    break;
                case FAILED:
                    break;
                default:
                    throw new AssertionError("Unexpected state: " + previous);
            }
        } finally {
            this.monitor.leave();
            executeListeners();
        }
    }

    @Override // com.google.common.util.concurrent.Service
    public final boolean isRunning() {
        return state() == Service.State.RUNNING;
    }

    @Override // com.google.common.util.concurrent.Service
    public final Service.State state() {
        return this.snapshot.externalState();
    }

    @Override // com.google.common.util.concurrent.Service
    public final Throwable failureCause() {
        return this.snapshot.failureCause();
    }

    @Override // com.google.common.util.concurrent.Service
    public final void addListener(Service.Listener listener, Executor executor) {
        Preconditions.checkNotNull(listener, "listener");
        Preconditions.checkNotNull(executor, "executor");
        this.monitor.enter();
        try {
            if (!state().isTerminal()) {
                this.listeners.add(new ListenerCallQueue<>(listener, executor));
            }
        } finally {
            this.monitor.leave();
        }
    }

    public String toString() {
        return getClass().getSimpleName() + " [" + state() + "]";
    }

    private void executeListeners() {
        if (!this.monitor.isOccupiedByCurrentThread()) {
            for (int i = 0; i < this.listeners.size(); i++) {
                this.listeners.get(i).execute();
            }
        }
    }

    @GuardedBy("monitor")
    private void starting() {
        STARTING_CALLBACK.enqueueOn(this.listeners);
    }

    @GuardedBy("monitor")
    private void running() {
        RUNNING_CALLBACK.enqueueOn(this.listeners);
    }

    @GuardedBy("monitor")
    private void stopping(Service.State from) {
        if (from == Service.State.STARTING) {
            STOPPING_FROM_STARTING_CALLBACK.enqueueOn(this.listeners);
        } else if (from == Service.State.RUNNING) {
            STOPPING_FROM_RUNNING_CALLBACK.enqueueOn(this.listeners);
        } else {
            throw new AssertionError();
        }
    }

    @GuardedBy("monitor")
    private void terminated(Service.State from) {
        int i = AnonymousClass10.$SwitchMap$com$google$common$util$concurrent$Service$State[from.ordinal()];
        if (i == 1) {
            TERMINATED_FROM_NEW_CALLBACK.enqueueOn(this.listeners);
        } else if (i == 3) {
            TERMINATED_FROM_RUNNING_CALLBACK.enqueueOn(this.listeners);
        } else if (i == 4) {
            TERMINATED_FROM_STOPPING_CALLBACK.enqueueOn(this.listeners);
        } else {
            throw new AssertionError();
        }
    }

    @GuardedBy("monitor")
    private void failed(final Service.State from, final Throwable cause) {
        new ListenerCallQueue.Callback<Service.Listener>("failed({from = " + from + ", cause = " + cause + "})") {
            /* class com.google.common.util.concurrent.AbstractService.AnonymousClass9 */

            /* access modifiers changed from: package-private */
            public void call(Service.Listener listener) {
                listener.failed(from, cause);
            }
        }.enqueueOn(this.listeners);
    }

    /* access modifiers changed from: private */
    @Immutable
    public static final class StateSnapshot {
        @Nullable
        final Throwable failure;
        final boolean shutdownWhenStartupFinishes;
        final Service.State state;

        StateSnapshot(Service.State internalState) {
            this(internalState, false, null);
        }

        StateSnapshot(Service.State internalState, boolean shutdownWhenStartupFinishes2, @Nullable Throwable failure2) {
            Preconditions.checkArgument(!shutdownWhenStartupFinishes2 || internalState == Service.State.STARTING, "shudownWhenStartupFinishes can only be set if state is STARTING. Got %s instead.", internalState);
            Preconditions.checkArgument(!((failure2 != null) ^ (internalState == Service.State.FAILED)), "A failure cause should be set if and only if the state is failed.  Got %s and %s instead.", internalState, failure2);
            this.state = internalState;
            this.shutdownWhenStartupFinishes = shutdownWhenStartupFinishes2;
            this.failure = failure2;
        }

        /* access modifiers changed from: package-private */
        public Service.State externalState() {
            if (!this.shutdownWhenStartupFinishes || this.state != Service.State.STARTING) {
                return this.state;
            }
            return Service.State.STOPPING;
        }

        /* access modifiers changed from: package-private */
        public Throwable failureCause() {
            Preconditions.checkState(this.state == Service.State.FAILED, "failureCause() is only valid if the service has failed, service is %s", this.state);
            return this.failure;
        }
    }
}
