package X;

import com.facebook.msys.mci.Execution;

/* renamed from: X.1uK  reason: invalid class name */
public class AnonymousClass1uK extends ThreadLocal<Integer> {
    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.lang.ThreadLocal
    public final Integer initialValue() {
        Execution.assertInitialized();
        return Integer.valueOf(Execution.nativeGetExecutionContext());
    }
}
