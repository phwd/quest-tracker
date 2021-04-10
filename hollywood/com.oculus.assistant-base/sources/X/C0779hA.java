package X;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

/* renamed from: X.hA  reason: case insensitive filesystem */
public final class C0779hA {
    public AnonymousClass9A A00;
    public AnonymousClass9B A01;
    public List A02 = new ArrayList();
    public boolean A03;
    public boolean A04;
    public final ExecutorService A05;

    public C0779hA(ExecutorService executorService) {
        double d = AnonymousClass9C.A02;
        double d2 = AnonymousClass9C.A03;
        this.A00 = new AnonymousClass9A(d, d2);
        this.A05 = executorService;
        this.A01 = new C0780hB(d, d2, AnonymousClass9C.A04, AnonymousClass9C.A05, AnonymousClass9C.A01);
        this.A03 = false;
        this.A04 = false;
    }
}
