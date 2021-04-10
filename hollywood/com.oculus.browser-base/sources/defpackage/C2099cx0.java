package defpackage;

import android.view.KeyEvent;
import android.widget.TextView;
import org.chromium.chrome.browser.sync.ui.PassphraseCreationDialogFragment;

/* renamed from: cx0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2099cx0 implements TextView.OnEditorActionListener {
    public final /* synthetic */ PassphraseCreationDialogFragment F;

    public C2099cx0(PassphraseCreationDialogFragment passphraseCreationDialogFragment) {
        this.F = passphraseCreationDialogFragment;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        PassphraseCreationDialogFragment.l1(this.F);
        return false;
    }
}
