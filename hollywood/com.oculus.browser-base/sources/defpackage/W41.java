package defpackage;

import android.content.DialogInterface;
import org.chromium.chrome.browser.sync.settings.SyncAndServicesSettings;

/* renamed from: W41  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class W41 implements DialogInterface.OnClickListener {
    public final SyncAndServicesSettings.CancelSyncDialog F;

    public W41(SyncAndServicesSettings.CancelSyncDialog cancelSyncDialog) {
        this.F = cancelSyncDialog;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.F.m1();
    }
}
