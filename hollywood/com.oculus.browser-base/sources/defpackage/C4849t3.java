package defpackage;

import android.content.Context;
import org.chromium.chrome.browser.language.settings.AddLanguageFragment;

/* renamed from: t3  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4849t3 extends H60 {
    public final /* synthetic */ AddLanguageFragment S;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4849t3(AddLanguageFragment addLanguageFragment, Context context) {
        super(context);
        this.S = addLanguageFragment;
    }

    @Override // defpackage.H60, defpackage.AbstractC5750yK0
    public void j(XK0 xk0, int i) {
        super.j(xk0, i);
        ((G60) xk0).G.setOnClickListener(new F60(this.S.E0, (B60) this.L.get(i)));
    }
}
