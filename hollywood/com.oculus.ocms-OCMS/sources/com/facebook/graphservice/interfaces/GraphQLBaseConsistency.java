package com.facebook.graphservice.interfaces;

import com.facebook.graphservice.interfaces.GraphQLService;
import com.facebook.proguard.annotations.DoNotStrip;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

@DoNotStrip
public interface GraphQLBaseConsistency {
    @DoNotStrip
    <T> ListenableFuture<T> lookup(T t);

    @DoNotStrip
    ListenableFuture<Void> publishBuilder(TreeBuilder treeBuilder);

    @DoNotStrip
    ListenableFuture<Void> publishBuilderWithFullConsistency(TreeBuilder treeBuilder);

    @DoNotStrip
    <T> GraphQLService.Token subscribe(T t, GraphQLService.DataCallbacks dataCallbacks, Executor executor);

    @DoNotStrip
    <T> GraphQLService.Token subscribeWithFullConsistency(T t, GraphQLService.DataCallbacks dataCallbacks, Executor executor);
}
