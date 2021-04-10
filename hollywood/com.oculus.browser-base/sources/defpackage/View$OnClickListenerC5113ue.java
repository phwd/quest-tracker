package defpackage;

import android.view.View;
import org.chromium.chrome.browser.autofill.settings.AutofillServerCardEditor;

/* renamed from: ue  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC5113ue implements View.OnClickListener {
    public final /* synthetic */ AutofillServerCardEditor F;

    public View$OnClickListenerC5113ue(AutofillServerCardEditor autofillServerCardEditor) {
        this.F = autofillServerCardEditor;
    }

    public void onClick(View view) {
        AbstractActivityC5822yn1.r1(this.F.u(), "https://payments.google.com/#paymentMethods");
    }
}
