package defpackage;

import android.content.DialogInterface;
import org.chromium.chrome.browser.sync.ui.PassphraseDialogFragment;

/* renamed from: jx0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogInterface$OnShowListenerC3294jx0 implements DialogInterface.OnShowListener {
    public final /* synthetic */ DialogC2461f4 F;
    public final /* synthetic */ PassphraseDialogFragment G;

    public DialogInterface$OnShowListenerC3294jx0(PassphraseDialogFragment passphraseDialogFragment, DialogC2461f4 f4Var) {
        this.G = passphraseDialogFragment;
        this.F = f4Var;
    }

    public void onShow(DialogInterface dialogInterface) {
        this.F.c(-1).setOnClickListener(new View$OnClickListenerC3123ix0(this));
    }
}
