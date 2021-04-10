package defpackage;

import J.N;
import android.bluetooth.BluetoothAdapter;
import android.view.View;
import com.oculus.browser.R;
import org.chromium.chrome.browser.device_dialog.BluetoothChooserDialog;

/* renamed from: si  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4785si extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final BluetoothChooserDialog f11292a;
    public final int b;

    public C4785si(BluetoothChooserDialog bluetoothChooserDialog, int i) {
        this.f11292a = bluetoothChooserDialog;
        this.b = i;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        BluetoothChooserDialog bluetoothChooserDialog = this.f11292a;
        int i = this.b;
        View view = (View) obj;
        long j = bluetoothChooserDialog.i;
        if (j != 0) {
            switch (i) {
                case 0:
                    N.M$uhYebq(j);
                    break;
                case 1:
                    BluetoothAdapter bluetoothAdapter = bluetoothChooserDialog.k;
                    if (bluetoothAdapter != null && bluetoothAdapter.enable()) {
                        bluetoothChooserDialog.c.c(0);
                        break;
                    } else {
                        bluetoothChooserDialog.c.b(bluetoothChooserDialog.b.getString(R.string.f47960_resource_name_obfuscated_RES_2131952113), bluetoothChooserDialog.l);
                        break;
                    }
                case 2:
                    N.MZvQXN$v(j);
                    break;
                case 3:
                    bluetoothChooserDialog.c.l = true;
                    bluetoothChooserDialog.f10651a.i(new String[]{"android.permission.ACCESS_FINE_LOCATION"}, bluetoothChooserDialog);
                    break;
                case 4:
                    bluetoothChooserDialog.c.l = true;
                    bluetoothChooserDialog.b.startActivity(C1159Ta0.a().b());
                    break;
                case 5:
                    N.MkOkhfCA(j);
                    break;
                case 6:
                    bluetoothChooserDialog.c.a();
                    N.MvKl$XvB(bluetoothChooserDialog.i);
                    break;
            }
            view.invalidate();
        }
    }
}
