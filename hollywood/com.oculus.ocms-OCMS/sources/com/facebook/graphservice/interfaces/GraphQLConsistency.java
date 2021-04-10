package com.facebook.graphservice.interfaces;

import com.facebook.proguard.annotations.DoNotStrip;
import com.google.common.util.concurrent.ListenableFuture;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@ThreadSafe
public interface GraphQLConsistency extends GraphQLBaseConsistency {

    public interface MutationHandle {
        void commit();

        void rollback();
    }

    public static class MutationPublisherRequest {
        @DoNotStrip
        public String mutationName;
    }

    @DoNotStrip
    ListenableFuture<MutationHandle> applyOptimistic(Tree tree, MutationPublisherRequest mutationPublisherRequest);

    @DoNotStrip
    ListenableFuture<MutationHandle> applyOptimisticBuilder(TreeBuilder treeBuilder, MutationPublisherRequest mutationPublisherRequest);

    @DoNotStrip
    ListenableFuture<Void> publish(Tree tree);

    @Override // com.facebook.graphservice.interfaces.GraphQLBaseConsistency
    @DoNotStrip
    ListenableFuture<Void> publishBuilder(TreeBuilder treeBuilder);

    @Override // com.facebook.graphservice.interfaces.GraphQLBaseConsistency
    @DoNotStrip
    ListenableFuture<Void> publishBuilderWithFullConsistency(TreeBuilder treeBuilder);

    @DoNotStrip
    ListenableFuture<Void> publishWithFullConsistency(Tree tree);
}
