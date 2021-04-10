package com.oculus.alpenglow.config;

import X.AbstractC01940Ou;
import com.facebook.graphql.modelutil.GeneratedGraphQL;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@GeneratedGraphQL
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public interface DeviceConfigQueryResponse extends AbstractC01940Ou {

    @GeneratedGraphQL
    @ThreadSafe
    public interface Me extends AbstractC01940Ou {

        @GeneratedGraphQL
        @ThreadSafe
        public interface HwmDevice extends AbstractC01940Ou {
            @Nullable
            Device A16();
        }

        @Nullable
        HwmDevice A3i();
    }

    @Nullable
    Me A3z();
}
