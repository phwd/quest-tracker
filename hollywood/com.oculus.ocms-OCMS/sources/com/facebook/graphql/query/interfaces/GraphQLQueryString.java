package com.facebook.graphql.query.interfaces;

import java.util.Set;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public interface GraphQLQueryString<T> {
    String getCallName();

    String getQueryName();

    @Nullable
    String getQueryText();

    @Nullable
    Set<Integer> getRuntimeDefaultParams();

    @Nullable
    Class<?> getTreeModelType();

    long getTreeShapeHash();

    int getTypeTag();

    boolean hasVirtualRootType();

    boolean isMutation();

    boolean isRootedOnOperation();

    boolean isVarArgsCall();

    GraphQLQueryString<T> setEnableFullConsistency(boolean z);

    GraphQLQueryString<T> setParam_DO_NOT_USE(String str, Object obj);
}
