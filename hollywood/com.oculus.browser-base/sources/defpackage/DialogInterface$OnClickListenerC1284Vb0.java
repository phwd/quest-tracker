package defpackage;

import android.content.DialogInterface;
import org.chromium.chrome.browser.sync.settings.ManageSyncSettings;

/* renamed from: Vb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class DialogInterface$OnClickListenerC1284Vb0 implements DialogInterface.OnClickListener {
    public final ManageSyncSettings.CancelSyncDialog F;

    public DialogInterface$OnClickListenerC1284Vb0(ManageSyncSettings.CancelSyncDialog cancelSyncDialog) {
        this.F = cancelSyncDialog;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.F.m1();
    }
}
