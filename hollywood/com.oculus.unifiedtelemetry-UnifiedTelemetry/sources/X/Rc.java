package X;

import com.facebook.infer.annotation.Functional;
import com.facebook.infer.annotation.ThreadSafe;

@ThreadSafe
public interface Rc {
    @Functional
    boolean A2L(long j);

    @Functional
    boolean A2M(long j, Rd rd);

    @Functional
    boolean A2N(long j, boolean z, Rd rd);

    @Functional
    long A2b(long j, long j2, Rd rd);

    @Functional
    long A2c(long j, Rd rd);

    @Functional
    String A2u(long j, Rd rd);

    @Functional
    String A2v(long j, String str, Rd rd);
}
