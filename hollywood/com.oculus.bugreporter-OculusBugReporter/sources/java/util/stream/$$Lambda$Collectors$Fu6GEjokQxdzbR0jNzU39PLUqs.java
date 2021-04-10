package java.util.stream;

import java.util.function.BinaryOperator;

/* renamed from: java.util.stream.-$$Lambda$Collectors$Fu6GEjokQxdzbR0jNzU39-PLUqs  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$Collectors$Fu6GEjokQxdzbR0jNzU39PLUqs implements BinaryOperator {
    public static final /* synthetic */ $$Lambda$Collectors$Fu6GEjokQxdzbR0jNzU39PLUqs INSTANCE = new $$Lambda$Collectors$Fu6GEjokQxdzbR0jNzU39PLUqs();

    private /* synthetic */ $$Lambda$Collectors$Fu6GEjokQxdzbR0jNzU39PLUqs() {
    }

    @Override // java.util.function.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return ((StringBuilder) obj).append((StringBuilder) ((CharSequence) ((StringBuilder) obj2)));
    }
}
