package com.facebook.graphservice.interfaces;

import X.AnonymousClass0OK;
import X.C00950Nx;
import com.facebook.proguard.annotations.DoNotStrip;
import com.google.common.util.concurrent.ListenableFuture;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@ThreadSafe
public interface GraphQLConsistency extends GraphQLBaseConsistency {
    @DoNotStrip
    ListenableFuture<MutationHandle> applyOptimistic(Tree tree, C00950Nx v);

    @DoNotStrip
    ListenableFuture<MutationHandle> applyOptimisticBuilder(AnonymousClass0OK v, C00950Nx v2);

    @DoNotStrip
    ListenableFuture<Void> publish(Tree tree);

    @Override // com.facebook.graphservice.interfaces.GraphQLBaseConsistency
    @DoNotStrip
    ListenableFuture<Void> publishBuilder(AnonymousClass0OK v);

    @Override // com.facebook.graphservice.interfaces.GraphQLBaseConsistency
    @DoNotStrip
    ListenableFuture<Void> publishBuilderWithFullConsistency(AnonymousClass0OK v);

    @DoNotStrip
    ListenableFuture<Void> publishWithFullConsistency(Tree tree);
}
