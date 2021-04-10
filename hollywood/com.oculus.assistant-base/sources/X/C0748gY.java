package X;

import com.facebook.assistant.oacr.OacrConstants;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.gY  reason: case insensitive filesystem */
public final class C0748gY implements AbstractC0105Aj {
    public final /* synthetic */ C00608k A00;

    public C0748gY(C00608k r1) {
        this.A00 = r1;
    }

    @Override // X.AbstractC0105Aj
    public final void A47(C0104Ai ai, AbstractC0106Ak ak) {
        C00608k r2 = this.A00;
        int i = ((C0772gy) ak.A2L()).A00;
        synchronized (r2) {
            C0139Dd.A09("KeyboardAssistant", r2.A02.toString());
            hG hGVar = C00879r.A00;
            hGVar.A06();
            hGVar.A03(EnumC00899t.SERVICE, r2.A02.A05);
            hGVar.A03(EnumC00899t.PROTOCOL, OacrConstants.OACR_PROTOCOL_NAME);
            C0782hF hFVar = C00839n.A00;
            C00568g r8 = r2.A08;
            C00849o r1 = new C00849o(r2.A00);
            hFVar.A00 = r1;
            r8.A04 = new C0781hE(r1, hGVar);
            FS fs = r2.A0F;
            C00618l r12 = r2.A02;
            fs.A0C = r12.A08;
            C00799i.A00.logLocalEvent("DataForKeyboard", r12.toString());
            C00618l r0 = r2.A02;
            String str = r0.A06;
            fs.A0A = str;
            C00598j r5 = r2.A09;
            r5.A02 = str;
            fs.A06 = r0.A03;
            if (r2.A00 > 0) {
                r8.A06.set(true);
                synchronized (r5) {
                    r5.A08.clear();
                    if (r5.A00 == null) {
                        RunnableC00578h r4 = new RunnableC00578h(r5);
                        r5.A00 = r4;
                        AtomicBoolean atomicBoolean = r4.A01;
                        if (atomicBoolean.get()) {
                            atomicBoolean.set(false);
                            r4.A00.add(new C0746gW(r4.A02));
                        }
                        atomicBoolean.set(true);
                        r4.A00.add(r4.A02.A04);
                        new Thread(r4).start();
                    } else {
                        C0139Dd.A0F("LastAudioLogger", "Already recording %s", r5.A07);
                    }
                }
                r8.A02 = new C0752gc(r2);
                r8.A01 = new C0753gd(r2);
                r2.A03.A01(new iF(r8, "transcription", r2.A00, str, r2.A02.A08, "not_required", r2.A07, 0, null, r2.A04, i));
            } else {
                throw new IllegalStateException("Microphone Sample Rate not set");
            }
        }
    }
}
