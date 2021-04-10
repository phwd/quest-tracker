package com.facebook.graphservice.interfaces;

import X.AbstractC01980Pf;
import X.AnonymousClass0PL;
import com.facebook.proguard.annotations.DoNotStrip;
import com.google.common.util.concurrent.ListenableFuture;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@ThreadSafe
public interface GraphQLConsistency extends GraphQLBaseConsistency {
    @DoNotStrip
    ListenableFuture<MutationHandle> applyOptimistic(Tree tree, AnonymousClass0PL v);

    @DoNotStrip
    ListenableFuture<MutationHandle> applyOptimisticBuilder(AbstractC01980Pf v, AnonymousClass0PL v2);

    @DoNotStrip
    ListenableFuture<Void> publish(Tree tree);

    @Override // com.facebook.graphservice.interfaces.GraphQLBaseConsistency
    @DoNotStrip
    ListenableFuture<Void> publishBuilder(AbstractC01980Pf v);

    @Override // com.facebook.graphservice.interfaces.GraphQLBaseConsistency
    @DoNotStrip
    ListenableFuture<Void> publishBuilderWithFullConsistency(AbstractC01980Pf v);

    @DoNotStrip
    ListenableFuture<Void> publishWithFullConsistency(Tree tree);
}
