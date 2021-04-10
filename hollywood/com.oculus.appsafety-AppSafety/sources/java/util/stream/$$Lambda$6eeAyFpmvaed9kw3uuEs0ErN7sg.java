package java.util.stream;

import java.util.function.LongBinaryOperator;

/* renamed from: java.util.stream.-$$Lambda$6eeAyFpmvaed9kw3uuEs0ErN7sg  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$6eeAyFpmvaed9kw3uuEs0ErN7sg implements LongBinaryOperator {
    public static final /* synthetic */ $$Lambda$6eeAyFpmvaed9kw3uuEs0ErN7sg INSTANCE = new $$Lambda$6eeAyFpmvaed9kw3uuEs0ErN7sg();

    private /* synthetic */ $$Lambda$6eeAyFpmvaed9kw3uuEs0ErN7sg() {
    }

    @Override // java.util.function.LongBinaryOperator
    public final long applyAsLong(long j, long j2) {
        return Math.max(j, j2);
    }
}
