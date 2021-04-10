package com.oculus.alpenglow.remotewipe;

import X.AbstractC01940Ou;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.alpenglow.graphql.enums.GraphQLRemoteWipeStatus;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@GeneratedGraphQL
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public interface CurrentDevice extends AbstractC01940Ou {

    @GeneratedGraphQL
    @ThreadSafe
    public interface PersistedConfig extends AbstractC01940Ou {
        @Nullable
        GraphQLRemoteWipeStatus A4O();
    }

    @Nullable
    PersistedConfig A4F();
}
