package com.facebook.graphservice.interfaces;

import X.EK;
import X.EU;
import com.google.common.util.concurrent.ListenableFuture;

public interface GraphQLConsistency extends GraphQLBaseConsistency {
    ListenableFuture applyOptimistic(Tree tree, EK ek);

    ListenableFuture applyOptimisticBuilder(EU eu, EK ek);

    ListenableFuture publish(Tree tree);

    @Override // com.facebook.graphservice.interfaces.GraphQLBaseConsistency
    ListenableFuture publishBuilder(EU eu);

    @Override // com.facebook.graphservice.interfaces.GraphQLBaseConsistency
    ListenableFuture publishBuilderWithFullConsistency(EU eu);

    ListenableFuture publishWithFullConsistency(Tree tree);
}
