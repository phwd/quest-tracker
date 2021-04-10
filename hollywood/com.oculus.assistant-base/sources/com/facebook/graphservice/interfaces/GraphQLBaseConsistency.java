package com.facebook.graphservice.interfaces;

import X.EU;
import com.facebook.graphservice.interfaces.GraphQLService;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

public interface GraphQLBaseConsistency {
    ListenableFuture lookup(Object obj);

    ListenableFuture publishBuilder(EU eu);

    ListenableFuture publishBuilderWithFullConsistency(EU eu);

    GraphQLService.Token subscribe(Object obj, GraphQLService.DataCallbacks dataCallbacks, Executor executor);

    GraphQLService.Token subscribeWithFullConsistency(Object obj, GraphQLService.DataCallbacks dataCallbacks, Executor executor);
}
