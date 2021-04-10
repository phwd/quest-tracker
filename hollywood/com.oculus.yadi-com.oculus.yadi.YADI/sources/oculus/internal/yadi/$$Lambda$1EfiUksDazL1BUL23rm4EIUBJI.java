package oculus.internal.yadi;

import java.util.Optional;
import java.util.function.Function;
import oculus.internal.functional.Pair;

/* renamed from: oculus.internal.yadi.-$$Lambda$1EfiUksDazL1BUL23rm4-EIUBJI  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$1EfiUksDazL1BUL23rm4EIUBJI implements Function {
    public static final /* synthetic */ $$Lambda$1EfiUksDazL1BUL23rm4EIUBJI INSTANCE = new $$Lambda$1EfiUksDazL1BUL23rm4EIUBJI();

    private /* synthetic */ $$Lambda$1EfiUksDazL1BUL23rm4EIUBJI() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return (Pair) ((Optional) obj).get();
    }
}
