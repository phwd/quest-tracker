package com.facebook.graphservice.interfaces;

import X.AnonymousClass0OK;
import com.facebook.graphservice.interfaces.GraphQLService;
import com.facebook.proguard.annotations.DoNotStrip;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

@DoNotStrip
public interface GraphQLBaseConsistency {
    @DoNotStrip
    <T> ListenableFuture<T> lookup(T t);

    @DoNotStrip
    ListenableFuture<Void> publishBuilder(AnonymousClass0OK v);

    @DoNotStrip
    ListenableFuture<Void> publishBuilderWithFullConsistency(AnonymousClass0OK v);

    @DoNotStrip
    <T> GraphQLService.Token subscribe(T t, GraphQLService.DataCallbacks dataCallbacks, Executor executor);

    @DoNotStrip
    <T> GraphQLService.Token subscribeWithFullConsistency(T t, GraphQLService.DataCallbacks dataCallbacks, Executor executor);
}
