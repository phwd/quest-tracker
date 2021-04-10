package defpackage;

import android.view.KeyEvent;
import android.widget.TextView;
import org.chromium.chrome.browser.sync.ui.PassphraseDialogFragment;

/* renamed from: gx0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2782gx0 implements TextView.OnEditorActionListener {
    public final /* synthetic */ PassphraseDialogFragment F;

    public C2782gx0(PassphraseDialogFragment passphraseDialogFragment) {
        this.F = passphraseDialogFragment;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 5) {
            return false;
        }
        PassphraseDialogFragment.l1(this.F);
        return false;
    }
}
