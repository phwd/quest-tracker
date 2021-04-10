package X;

import java.util.HashMap;
import java.util.Map;

/* renamed from: X.1kK  reason: invalid class name */
public final class AnonymousClass1kK {
    public AnonymousClass1kP A00;
    public AnonymousClass1ZY A01;
    public AnonymousClass1kW A02;
    public AnonymousClass1kS A03;
    public AnonymousClass1kR A04;
    public Map<String, String> A05 = new HashMap();
    public boolean A06;

    public AnonymousClass1kK() {
        AnonymousClass1kW r1 = new AnonymousClass1kW();
        this.A02 = r1;
        this.A04 = new AnonymousClass1kR(r1);
        AnonymousClass1kP r12 = new AnonymousClass1kP(AnonymousClass1kM.SHA256.name());
        this.A00 = r12;
        this.A03 = new AnonymousClass1kS(r12);
        this.A06 = true;
    }

    public AnonymousClass1kK(AnonymousClass1ZY r3) {
        AnonymousClass1kW r1 = new AnonymousClass1kW();
        this.A02 = r1;
        this.A04 = new AnonymousClass1kR(r1);
        AnonymousClass1kP r12 = new AnonymousClass1kP(AnonymousClass1kM.SHA256.name());
        this.A00 = r12;
        this.A03 = new AnonymousClass1kS(r12);
        this.A06 = true;
        this.A01 = r3;
    }
}
