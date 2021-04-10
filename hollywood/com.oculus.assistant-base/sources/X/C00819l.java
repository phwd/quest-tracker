package X;

import android.os.Bundle;
import com.facebook.acra.util.HttpRequest;
import com.oculus.assistant.R;
import com.oculus.assistant.assistantutils.SystemUXUtil;

/* renamed from: X.9l  reason: invalid class name and case insensitive filesystem */
public final class C00819l extends C1300wu {
    public static final X5 A00 = new X5();

    @Override // X.C1300wu
    public final void A0E(String str) {
        C0514bB.A02(str, "dialogType");
        super.A0E(str);
    }

    public C00819l() {
        A0B(R.string.dialog_button_dont_submit);
        A0A(R.string.submit_button_review_text);
        A0C(R.string.submit_transcription_title);
        A0G("APD_SUBMIT_TRANSCRIPTION_AUDIO");
        Bundle bundle = this.A01;
        bundle.putInt("width", 512);
        bundle.putInt("height", HttpRequest.CHAR_ARRAY_BUFFER_SIZE);
        if (SystemUXUtil.A00() >= 3) {
            A0D(new C1308x2(R.string.submit_transcription_description));
        } else {
            A09(R.string.submit_transcription_description);
        }
    }

    @Override // X.C1300wu
    public final String A03() {
        if (SystemUXUtil.A00() >= 3) {
            return "dialog";
        }
        return super.A03();
    }
}
