package defpackage;

import android.view.View;
import android.widget.LinearLayout;
import org.chromium.chrome.browser.device_dialog.BluetoothScanningPermissionDialog;

/* renamed from: wi  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnLayoutChangeListenerC5465wi implements View.OnLayoutChangeListener {
    public final BluetoothScanningPermissionDialog F;
    public final LinearLayout G;

    public View$OnLayoutChangeListenerC5465wi(BluetoothScanningPermissionDialog bluetoothScanningPermissionDialog, LinearLayout linearLayout) {
        this.F = bluetoothScanningPermissionDialog;
        this.G = linearLayout;
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.F.d(this.G, i, i2, i3, i4, i5, i6, i7, i8);
    }
}
