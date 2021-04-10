package com.oculus.alpenglow.config;

import X.AbstractC01940Ou;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.infer.annotation.Nullsafe;
import com.google.common.collect.ImmutableList;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@GeneratedGraphQL
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public interface RemoteResourceAsset extends AbstractC01940Ou {

    @GeneratedGraphQL
    @ThreadSafe
    public interface Headers extends AbstractC01940Ou {
        @Nullable
        String getKey();

        @Nullable
        String getValue();
    }

    @Nullable
    String A3E();

    @Nullable
    String A3T();

    @Nullable
    String A3Z();

    ImmutableList<? extends Headers> A3d();

    @Nullable
    String getId();
}
