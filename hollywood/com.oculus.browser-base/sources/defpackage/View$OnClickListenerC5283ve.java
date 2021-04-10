package defpackage;

import J.N;
import android.view.View;
import org.chromium.chrome.browser.autofill.PersonalDataManager;
import org.chromium.chrome.browser.autofill.settings.AutofillServerCardEditor;

/* renamed from: ve  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC5283ve implements View.OnClickListener {
    public final /* synthetic */ AutofillServerCardEditor F;

    public View$OnClickListenerC5283ve(AutofillServerCardEditor autofillServerCardEditor) {
        this.F = autofillServerCardEditor;
    }

    public void onClick(View view) {
        PersonalDataManager c = PersonalDataManager.c();
        N.MSOj73VL(c.b, c, this.F.y0);
        AutofillServerCardEditor autofillServerCardEditor = this.F;
        int i = AutofillServerCardEditor.E0;
        autofillServerCardEditor.j1();
    }
}
