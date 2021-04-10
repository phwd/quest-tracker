package defpackage;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.chromium.chrome.browser.device_dialog.BluetoothChooserDialog;

/* renamed from: ti  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4955ti extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ BluetoothChooserDialog f11362a;

    public C4955ti(BluetoothChooserDialog bluetoothChooserDialog) {
        this.f11362a = bluetoothChooserDialog;
    }

    public void onReceive(Context context, Intent intent) {
        if ("android.location.MODE_CHANGED".equals(intent.getAction()) && this.f11362a.c()) {
            this.f11362a.c.a();
            N.MvKl$XvB(this.f11362a.i);
        }
    }
}
