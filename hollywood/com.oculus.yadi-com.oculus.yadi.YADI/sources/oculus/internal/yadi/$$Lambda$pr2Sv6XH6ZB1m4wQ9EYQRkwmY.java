package oculus.internal.yadi;

import java.util.Optional;
import java.util.function.Function;
import oculus.internal.functional.Pair;

/* renamed from: oculus.internal.yadi.-$$Lambda$p-r2Sv6XH6ZB1m4wQ9-EYQRkwmY  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$pr2Sv6XH6ZB1m4wQ9EYQRkwmY implements Function {
    public static final /* synthetic */ $$Lambda$pr2Sv6XH6ZB1m4wQ9EYQRkwmY INSTANCE = new $$Lambda$pr2Sv6XH6ZB1m4wQ9EYQRkwmY();

    private /* synthetic */ $$Lambda$pr2Sv6XH6ZB1m4wQ9EYQRkwmY() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return (Optional) ((Pair) obj).right();
    }
}
