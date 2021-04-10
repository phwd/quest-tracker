package oculus.internal.yadi;

import com.oculus.os.yadi.RemoteResource;
import java.util.function.Function;
import oculus.internal.functional.Pair;

/* renamed from: oculus.internal.yadi.-$$Lambda$VEK2dqyOOzCSBhroT1qKKyUBNx8  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$VEK2dqyOOzCSBhroT1qKKyUBNx8 implements Function {
    public static final /* synthetic */ $$Lambda$VEK2dqyOOzCSBhroT1qKKyUBNx8 INSTANCE = new $$Lambda$VEK2dqyOOzCSBhroT1qKKyUBNx8();

    private /* synthetic */ $$Lambda$VEK2dqyOOzCSBhroT1qKKyUBNx8() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return (RemoteResource) ((Pair) obj).left();
    }
}
