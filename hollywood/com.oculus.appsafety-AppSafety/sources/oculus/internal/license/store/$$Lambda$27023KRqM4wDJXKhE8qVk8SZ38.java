package oculus.internal.license.store;

import com.oculus.license.License;
import java.util.function.Function;
import oculus.internal.functional.Try;

/* renamed from: oculus.internal.license.store.-$$Lambda$27023-KRqM4wDJXKhE8qVk8SZ38  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$27023KRqM4wDJXKhE8qVk8SZ38 implements Function {
    public static final /* synthetic */ $$Lambda$27023KRqM4wDJXKhE8qVk8SZ38 INSTANCE = new $$Lambda$27023KRqM4wDJXKhE8qVk8SZ38();

    private /* synthetic */ $$Lambda$27023KRqM4wDJXKhE8qVk8SZ38() {
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return Try.success((License) obj);
    }
}
