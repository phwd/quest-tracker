package com.facebook.graphservice.interfaces;

import X.AbstractC01980Pf;
import com.facebook.graphservice.interfaces.GraphQLService;
import com.facebook.proguard.annotations.DoNotStrip;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

@DoNotStrip
public interface GraphQLBaseConsistency {
    @DoNotStrip
    <T> ListenableFuture<T> lookup(T t);

    @DoNotStrip
    ListenableFuture<Void> publishBuilder(AbstractC01980Pf v);

    @DoNotStrip
    ListenableFuture<Void> publishBuilderWithFullConsistency(AbstractC01980Pf v);

    @DoNotStrip
    <T> GraphQLService.Token subscribe(T t, GraphQLService.DataCallbacks dataCallbacks, Executor executor);

    @DoNotStrip
    <T> GraphQLService.Token subscribeWithFullConsistency(T t, GraphQLService.DataCallbacks dataCallbacks, Executor executor);
}
