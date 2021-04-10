package defpackage;

import android.text.style.ClickableSpan;
import android.view.View;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.ui.PassphraseDialogFragment;

/* renamed from: kx0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3465kx0 extends ClickableSpan {
    public final /* synthetic */ String F;
    public final /* synthetic */ PassphraseDialogFragment G;

    public C3465kx0(PassphraseDialogFragment passphraseDialogFragment, String str) {
        this.G = passphraseDialogFragment;
        this.F = str;
    }

    public void onClick(View view) {
        C2535fX.a().b(this.G.u(), this.F, Profile.b(), null);
    }
}
