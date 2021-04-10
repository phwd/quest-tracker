package com.oculus.alpenglow.remotewipe;

import X.AbstractC01940Ou;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@GeneratedGraphQL
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public interface RemoteWipeStatusQueryResponse extends AbstractC01940Ou {

    @GeneratedGraphQL
    @ThreadSafe
    public interface Me extends AbstractC01940Ou {

        @GeneratedGraphQL
        @ThreadSafe
        public interface HwmDevice extends AbstractC01940Ou {
            @Nullable
            CurrentDevice A15();
        }

        @Nullable
        HwmDevice A3j();
    }

    @Nullable
    Me A40();
}
