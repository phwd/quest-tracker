package X;

import com.oculus.assistant.R;
import com.oculus.os.SettingsManager;

/* renamed from: X.wc  reason: case insensitive filesystem */
public final class C1282wc extends AbstractC0400Wb {
    public static final C0406Wi A01 = new C0406Wi();
    public final int A00 = R.raw.tts_welcome_instruction;

    public C1282wc() {
        Integer num = AnonymousClass09.A0C;
        C0514bB.A02(num, "<set-?>");
        this.A01 = num;
    }

    @Override // X.AbstractC0400Wb
    public final void A04() {
        new SettingsManager(BX.A00()).setInt("oculus_button_doublepress_behavior", 2);
        C0112Aq.A00().A01(new C1290wk());
    }
}
