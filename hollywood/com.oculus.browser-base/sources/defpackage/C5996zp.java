package defpackage;

import java.util.Locale;
import org.chromium.components.browser_ui.site_settings.ChosenObjectSettings;

/* renamed from: zp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5996zp implements AbstractC2012cR0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ChosenObjectSettings f11771a;

    public C5996zp(ChosenObjectSettings chosenObjectSettings) {
        this.f11771a = chosenObjectSettings;
    }

    @Override // defpackage.AbstractC2012cR0
    public boolean onQueryTextChange(String str) {
        String lowerCase = str.toLowerCase(Locale.getDefault());
        if (lowerCase.equals(this.f11771a.L0)) {
            return true;
        }
        ChosenObjectSettings chosenObjectSettings = this.f11771a;
        chosenObjectSettings.L0 = lowerCase;
        chosenObjectSettings.k1();
        return true;
    }

    @Override // defpackage.AbstractC2012cR0
    public boolean onQueryTextSubmit(String str) {
        return true;
    }
}
