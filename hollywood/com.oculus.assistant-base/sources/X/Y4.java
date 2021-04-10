package X;

import java.util.ArrayList;

public final class Y4 {
    public static final Y3 A0H = new Y3();
    public boolean A00;
    public final C00608k A01;
    public final C00618l A02 = new C00618l();
    public final AbstractC0105Aj A03 = new yC(this);
    public final AbstractC0105Aj A04 = new yD(this);
    public final AbstractC0105Aj A05 = new C1377yE(this);
    public final AbstractC0105Aj A06 = new C1378yF(this);
    public final AbstractC0105Aj A07 = new C1379yG(this);
    public final AbstractC0105Aj A08 = new C1380yH(this);
    public final AbstractC0105Aj A09 = new C1381yI(this);
    public final C0112Aq A0A = C0112Aq.A00();
    public final YP A0B;
    public final ArrayList A0C = new ArrayList();
    public final ArrayList A0D = new ArrayList();
    public final ArrayList A0E = new ArrayList();
    public final ArrayList A0F = new ArrayList();
    public final ArrayList A0G = new ArrayList();

    public Y4(C00608k r2, YP yp) {
        C0514bB.A02(r2, "keyboardAssistant");
        C0514bB.A02(yp, "logger");
        this.A01 = r2;
        this.A0B = yp;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0064, code lost:
        if (X.AnonymousClass0o.A02(r1, "com.oculus.vrshell.desktop.PanelService") != false) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0027, code lost:
        if (X.AnonymousClass0o.A02(r1, "com.oculus.explore") != false) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void A00(X.Y4 r6) {
        /*
        // Method dump skipped, instructions count: 104
        */
        throw new UnsupportedOperationException("Method not decompiled: X.Y4.A00(X.Y4):void");
    }

    public final void A01() {
        if (this.A00) {
            YP yp = this.A0B;
            C0139Dd.A09("OculusAssistantInteractionLatencyLogger", "logStopDictation");
            C0139Dd.A09("OculusAssistantInteractionLatencyLogger", "endInteraction");
            YO yo = yp.A01;
            if (yo != null) {
                yo.A03(2);
            }
            yp.A01 = null;
            C0139Dd.A09("DictationServiceManager", "Stopping transcription.");
            this.A0A.A01(new C0774h0());
            C00608k r3 = this.A01;
            C00648r r6 = r3.A0A;
            C0112Aq A002 = C0112Aq.A00();
            A002.A04(C0772gy.class, r6.A02);
            A002.A04(C0774h0.class, r6.A03);
            A002.A04(C0811hz.class, r6.A01);
            A002.A04(C0806hu.class, r6.A00);
            A002.A04(h4.class, r6.A04);
            A002.A04(C0776h5.class, r6.A05);
            r3.A03.A04(C0772gy.class, r3.A0D);
            r3.A03.A04(C0774h0.class, r3.A0E);
            r3.A03.A04(C0801hp.class, r3.A0B);
        }
        this.A00 = false;
    }
}
