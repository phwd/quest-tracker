package X;

import com.facebook.assistant.oacr.OacrConstants;

/* renamed from: X.8k  reason: invalid class name and case insensitive filesystem */
public final class C00608k {
    public int A00;
    public long A01;
    public C00618l A02 = new C00618l();
    public C0112Aq A03;
    public BN A04 = BN.RAW;
    public String A05 = OacrConstants.AUTO_SPEECH_DOMAIN;
    public String A06 = OacrConstants.AUTO_SPEECH_DOMAIN;
    public final int A07;
    public final C00568g A08;
    public final C00598j A09;
    public final C00648r A0A;
    public final AbstractC0105Aj A0B = new C0750ga(this);
    public final AbstractC0105Aj A0C = new C0751gb(this);
    public final AbstractC0105Aj A0D = new C0748gY(this);
    public final AbstractC0105Aj A0E = new C0749gZ(this);
    public final FS A0F;

    public C00608k(C00568g r4, FS fs, int i, int i2) {
        C0139Dd.A0F("KeyboardAssistant", "sampling rate %d", Integer.valueOf(i));
        C00598j r0 = new C00598j();
        this.A09 = r0;
        this.A08 = r4;
        r4.A03 = r0;
        this.A0F = fs;
        this.A00 = i;
        this.A07 = i2;
        this.A0A = new C00648r();
        this.A03 = C0112Aq.A00();
    }
}
