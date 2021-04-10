package X;

import com.facebook.infer.annotation.NullsafeStrict;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@NullsafeStrict
public abstract class H0 {
    @GuardedBy("this")
    @Nullable
    public C0260Yj mPigeonIdentity;
    @GuardedBy("this")
    public boolean mPigeonIdentityKnown;
    public final Gz mSessionManagerCallbackObservable = new Gz();

    @Nullable
    public C0260Yj A00() {
        throw new IllegalStateException("Should not be called");
    }
}
