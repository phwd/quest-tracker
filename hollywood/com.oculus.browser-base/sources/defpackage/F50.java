package defpackage;

import org.chromium.components.autofill.AutofillSuggestion;

/* renamed from: F50  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class F50 extends G50 {
    public final AutofillSuggestion c;
    public String d;

    public F50(AutofillSuggestion autofillSuggestion, C2636g50 g50) {
        super(1, g50);
        this.c = autofillSuggestion;
    }

    @Override // defpackage.G50
    public String toString() {
        StringBuilder i = AbstractC2531fV.i("Autofill");
        i.append(super.toString());
        return i.toString();
    }
}
