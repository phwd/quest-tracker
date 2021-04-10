package com.google.common.util.concurrent;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.errorprone.annotations.ForOverride;
import com.google.errorprone.annotations.OverridingMethodsMustInvokeSuper;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
@GwtCompatible
public abstract class AggregateFuture<InputT, OutputT> extends AbstractFuture.TrustedFuture<OutputT> {
    private static final Logger logger = Logger.getLogger(AggregateFuture.class.getName());
    @NullableDecl
    private AggregateFuture<InputT, OutputT>.RunningState runningState;

    AggregateFuture() {
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        boolean isCancelled;
        super.afterDone();
        AggregateFuture<InputT, OutputT>.RunningState localRunningState = this.runningState;
        if (localRunningState != null) {
            this.runningState = null;
            ImmutableCollection<? extends ListenableFuture<? extends InputT>> futures = ((RunningState) localRunningState).futures;
            boolean wasInterrupted = wasInterrupted();
            if (wasInterrupted) {
                localRunningState.interruptTask();
            }
            if ((futures != null) && isCancelled()) {
                UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it = futures.iterator();
                while (it.hasNext()) {
                    ((ListenableFuture) it.next()).cancel(wasInterrupted);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    public String pendingToString() {
        ImmutableCollection<? extends ListenableFuture<? extends InputT>> localFutures;
        AggregateFuture<InputT, OutputT>.RunningState localRunningState = this.runningState;
        if (localRunningState == null || (localFutures = ((RunningState) localRunningState).futures) == null) {
            return null;
        }
        return "futures=[" + localFutures + "]";
    }

    /* access modifiers changed from: package-private */
    public final void init(AggregateFuture<InputT, OutputT>.RunningState runningState2) {
        this.runningState = runningState2;
        runningState2.init();
    }

    /* access modifiers changed from: package-private */
    public abstract class RunningState extends AggregateFutureState implements Runnable {
        private final boolean allMustSucceed;
        private final boolean collectsValues;
        private ImmutableCollection<? extends ListenableFuture<? extends InputT>> futures;

        /* access modifiers changed from: package-private */
        public abstract void collectOneValue(boolean z, int i, @NullableDecl InputT inputt);

        /* access modifiers changed from: package-private */
        public abstract void handleAllCompleted();

        RunningState(ImmutableCollection<? extends ListenableFuture<? extends InputT>> futures2, boolean allMustSucceed2, boolean collectsValues2) {
            super(futures2.size());
            this.futures = (ImmutableCollection) Preconditions.checkNotNull(futures2);
            this.allMustSucceed = allMustSucceed2;
            this.collectsValues = collectsValues2;
        }

        public final void run() {
            decrementCountAndMaybeComplete();
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void init() {
            if (this.futures.isEmpty()) {
                handleAllCompleted();
            } else if (this.allMustSucceed) {
                final int i = 0;
                UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it = this.futures.iterator();
                while (it.hasNext()) {
                    final ListenableFuture<? extends InputT> listenable = (ListenableFuture) it.next();
                    listenable.addListener(new Runnable() {
                        /* class com.google.common.util.concurrent.AggregateFuture.RunningState.AnonymousClass1 */

                        public void run() {
                            try {
                                RunningState.this.handleOneInputDone(i, listenable);
                            } finally {
                                RunningState.this.decrementCountAndMaybeComplete();
                            }
                        }
                    }, MoreExecutors.directExecutor());
                    i++;
                }
            } else {
                UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it2 = this.futures.iterator();
                while (it2.hasNext()) {
                    ((ListenableFuture) it2.next()).addListener(this, MoreExecutors.directExecutor());
                }
            }
        }

        private void handleException(Throwable throwable) {
            boolean z;
            String message;
            Preconditions.checkNotNull(throwable);
            boolean completedWithFailure = false;
            boolean firstTimeSeeingThisException = true;
            if (this.allMustSucceed) {
                completedWithFailure = AggregateFuture.this.setException(throwable);
                if (completedWithFailure) {
                    releaseResourcesAfterFailure();
                } else {
                    firstTimeSeeingThisException = AggregateFuture.addCausalChain(getOrInitSeenExceptions(), throwable);
                }
            }
            if (((!completedWithFailure) & this.allMustSucceed & firstTimeSeeingThisException) || (throwable instanceof Error)) {
                if (throwable instanceof Error) {
                    message = "Input Future failed with Error";
                } else {
                    message = "Got more than one input Future failure. Logging failures after the first";
                }
                AggregateFuture.logger.log(Level.SEVERE, message, throwable);
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AggregateFutureState
        public final void addInitialException(Set<Throwable> seen) {
            if (!AggregateFuture.this.isCancelled()) {
                AggregateFuture.addCausalChain(seen, AggregateFuture.this.trustedGetException());
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: com.google.common.util.concurrent.AggregateFuture$RunningState */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void handleOneInputDone(int index, Future<? extends InputT> future) {
            boolean z = false;
            if (this.allMustSucceed || !AggregateFuture.this.isDone() || AggregateFuture.this.isCancelled()) {
                z = true;
            }
            Preconditions.checkState(z, "Future was done before all dependencies completed");
            try {
                Preconditions.checkState(future.isDone(), "Tried to set value from future which is not done");
                if (this.allMustSucceed) {
                    if (future.isCancelled()) {
                        AggregateFuture.this.runningState = null;
                        AggregateFuture.this.cancel(false);
                        return;
                    }
                    Object done = Futures.getDone(future);
                    if (this.collectsValues) {
                        collectOneValue(this.allMustSucceed, index, done);
                    }
                } else if (this.collectsValues && !future.isCancelled()) {
                    collectOneValue(this.allMustSucceed, index, Futures.getDone(future));
                }
            } catch (ExecutionException e) {
                handleException(e.getCause());
            } catch (Throwable t) {
                handleException(t);
            }
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void decrementCountAndMaybeComplete() {
            int newRemaining = decrementRemainingAndGet();
            Preconditions.checkState(newRemaining >= 0, "Less than 0 remaining futures");
            if (newRemaining == 0) {
                processCompleted();
            }
        }

        private void processCompleted() {
            boolean z;
            if ((!this.allMustSucceed) && this.collectsValues) {
                int i = 0;
                UnmodifiableIterator<? extends ListenableFuture<? extends InputT>> it = this.futures.iterator();
                while (it.hasNext()) {
                    handleOneInputDone(i, (ListenableFuture) it.next());
                    i++;
                }
            }
            handleAllCompleted();
        }

        /* access modifiers changed from: package-private */
        @ForOverride
        @OverridingMethodsMustInvokeSuper
        public void releaseResourcesAfterFailure() {
            this.futures = null;
        }

        /* access modifiers changed from: package-private */
        public void interruptTask() {
        }
    }

    /* access modifiers changed from: private */
    public static boolean addCausalChain(Set<Throwable> seen, Throwable t) {
        while (t != null) {
            if (!seen.add(t)) {
                return false;
            }
            t = t.getCause();
        }
        return true;
    }
}
