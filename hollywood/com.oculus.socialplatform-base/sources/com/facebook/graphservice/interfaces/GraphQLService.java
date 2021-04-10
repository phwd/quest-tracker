package com.facebook.graphservice.interfaces;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.tigon.TigonErrorException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@DoNotStrip
@ThreadSafe
@Nullsafe(Nullsafe.Mode.LOCAL)
public interface GraphQLService {

    @DoNotStrip
    public interface DataCallbacks {
        @DoNotStrip
        void onError(TigonErrorException tigonErrorException, @Nullable Summary summary);

        @DoNotStrip
        void onUpdate(Tree tree, @Nullable Summary summary);
    }

    public @interface GraphQLRequestPurpose {
    }

    @DoNotStrip
    public interface OperationCallbacks {
        @DoNotStrip
        void onError(TigonErrorException tigonErrorException);

        @DoNotStrip
        void onSuccess();
    }

    @DoNotStrip
    @ThreadSafe
    public interface Token {
        @DoNotStrip
        void cancel();
    }
}
