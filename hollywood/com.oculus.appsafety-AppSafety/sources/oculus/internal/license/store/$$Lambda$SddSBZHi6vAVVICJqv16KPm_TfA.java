package oculus.internal.license.store;

import com.oculus.license.License;
import java.util.Objects;
import java.util.function.Predicate;

/* renamed from: oculus.internal.license.store.-$$Lambda$SddSBZHi6vAVVICJqv16KPm_TfA  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$SddSBZHi6vAVVICJqv16KPm_TfA implements Predicate {
    public static final /* synthetic */ $$Lambda$SddSBZHi6vAVVICJqv16KPm_TfA INSTANCE = new $$Lambda$SddSBZHi6vAVVICJqv16KPm_TfA();

    private /* synthetic */ $$Lambda$SddSBZHi6vAVVICJqv16KPm_TfA() {
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return Objects.nonNull((License) obj);
    }
}
