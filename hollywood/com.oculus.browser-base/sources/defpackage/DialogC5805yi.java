package defpackage;

import android.app.Dialog;
import android.content.Context;
import org.chromium.chrome.browser.device_dialog.BluetoothScanningPermissionDialog;

/* renamed from: yi  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogC5805yi extends Dialog {
    public final /* synthetic */ BluetoothScanningPermissionDialog F;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DialogC5805yi(BluetoothScanningPermissionDialog bluetoothScanningPermissionDialog, Context context) {
        super(context);
        this.F = bluetoothScanningPermissionDialog;
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!this.F.e && !z) {
            dismiss();
        }
        this.F.e = false;
    }
}
