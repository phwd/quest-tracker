package com.google.common.util.concurrent;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD)
abstract class CollectionFuture<V, C> extends AggregateFuture<V, C> {
    CollectionFuture() {
    }

    abstract class CollectionFutureRunningState extends AggregateFuture<V, C>.RunningState {
        private List<Optional<V>> values;

        /* access modifiers changed from: package-private */
        public abstract C combine(List<Optional<V>> list);

        CollectionFutureRunningState(ImmutableCollection<? extends ListenableFuture<? extends V>> futures, boolean allMustSucceed) {
            super(futures, allMustSucceed, true);
            List<Optional<V>> newArrayListWithCapacity;
            if (futures.isEmpty()) {
                newArrayListWithCapacity = ImmutableList.of();
            } else {
                newArrayListWithCapacity = Lists.newArrayListWithCapacity(futures.size());
            }
            this.values = newArrayListWithCapacity;
            for (int i = 0; i < futures.size(); i++) {
                this.values.add(null);
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AggregateFuture.RunningState
        public final void collectOneValue(boolean allMustSucceed, int index, @NullableDecl V returnValue) {
            List<Optional<V>> localValues = this.values;
            if (localValues != null) {
                localValues.set(index, Optional.fromNullable(returnValue));
            } else {
                Preconditions.checkState(allMustSucceed || CollectionFuture.this.isCancelled(), "Future was done before all dependencies completed");
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: com.google.common.util.concurrent.CollectionFuture */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AggregateFuture.RunningState
        public final void handleAllCompleted() {
            List<Optional<V>> localValues = this.values;
            if (localValues != null) {
                CollectionFuture.this.set(combine(localValues));
            } else {
                Preconditions.checkState(CollectionFuture.this.isDone());
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AggregateFuture.RunningState
        public void releaseResourcesAfterFailure() {
            super.releaseResourcesAfterFailure();
            this.values = null;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class ListFuture<V> extends CollectionFuture<V, List<V>> {
        ListFuture(ImmutableCollection<? extends ListenableFuture<? extends V>> futures, boolean allMustSucceed) {
            init(new ListFutureRunningState(futures, allMustSucceed));
        }

        private final class ListFutureRunningState extends CollectionFuture<V, List<V>>.CollectionFutureRunningState {
            ListFutureRunningState(ImmutableCollection<? extends ListenableFuture<? extends V>> futures, boolean allMustSucceed) {
                super(futures, allMustSucceed);
            }

            @Override // com.google.common.util.concurrent.CollectionFuture.CollectionFutureRunningState
            public List<V> combine(List<Optional<V>> values) {
                List<V> result = Lists.newArrayListWithCapacity(values.size());
                Iterator<Optional<V>> it = values.iterator();
                while (it.hasNext()) {
                    Optional<V> element = it.next();
                    result.add(element != null ? element.orNull() : null);
                }
                return Collections.unmodifiableList(result);
            }
        }
    }
}
