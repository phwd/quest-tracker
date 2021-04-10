package defpackage;

import android.content.DialogInterface;
import org.chromium.chrome.browser.device_dialog.BluetoothScanningPermissionDialog;

/* renamed from: xi  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class DialogInterface$OnCancelListenerC5635xi implements DialogInterface.OnCancelListener {
    public final BluetoothScanningPermissionDialog F;

    public DialogInterface$OnCancelListenerC5635xi(BluetoothScanningPermissionDialog bluetoothScanningPermissionDialog) {
        this.F = bluetoothScanningPermissionDialog;
    }

    public void onCancel(DialogInterface dialogInterface) {
        this.F.e();
    }
}
