package X;

import com.facebook.assistant.oacr.OacrConstants;
import java.util.List;

/* renamed from: X.99  reason: invalid class name */
public final class AnonymousClass99 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.keyboard.typosuggestionproviders.EDAutoCorrectionSuggestionPredictor$1";
    public final /* synthetic */ C0779hA A00;
    public final /* synthetic */ List A01;

    public AnonymousClass99(C0779hA hAVar, List list) {
        this.A00 = hAVar;
        this.A01 = list;
    }

    public final void run() {
        C0779hA hAVar = this.A00;
        hAVar.A00.A00(this.A01, OacrConstants.AUTO_SPEECH_DOMAIN);
        hAVar.A03 = true;
        hAVar.A04 = false;
    }
}
