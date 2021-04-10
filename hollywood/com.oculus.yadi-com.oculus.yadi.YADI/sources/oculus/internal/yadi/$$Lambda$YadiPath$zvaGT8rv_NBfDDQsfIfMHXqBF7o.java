package oculus.internal.yadi;

import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

/* renamed from: oculus.internal.yadi.-$$Lambda$YadiPath$zvaGT8rv_NBfDDQsfIfMHXqBF7o  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$YadiPath$zvaGT8rv_NBfDDQsfIfMHXqBF7o implements BiFunction {
    public static final /* synthetic */ $$Lambda$YadiPath$zvaGT8rv_NBfDDQsfIfMHXqBF7o INSTANCE = new $$Lambda$YadiPath$zvaGT8rv_NBfDDQsfIfMHXqBF7o();

    private /* synthetic */ $$Lambda$YadiPath$zvaGT8rv_NBfDDQsfIfMHXqBF7o() {
    }

    @Override // java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        Long l = (Long) obj;
        return Collections.unmodifiableList((List) obj2);
    }
}
