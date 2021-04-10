package X;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
/* renamed from: X.s6  reason: case insensitive filesystem */
public final class C0490s6 {
    public long A00;
    public List<C0510sh> A01;
    public List<String> A02;
    public final int A03;
    public final long A04;
    public final long A05;
    public final AbstractC0481qo A06;

    public C0490s6(C0498sQ sQVar) {
        this.A06 = sQVar.A04;
        this.A05 = sQVar.A02;
        this.A04 = sQVar.A01;
        this.A00 = sQVar.A03;
        this.A03 = sQVar.A00;
        this.A02 = sQVar.A06;
        this.A01 = sQVar.A05;
    }

    public C0490s6(AbstractC0481qo qoVar, long j, long j2) {
        this.A06 = qoVar;
        this.A05 = j;
        this.A04 = j2;
        this.A00 = j2;
        this.A03 = 1;
        this.A01 = new ArrayList();
    }
}
