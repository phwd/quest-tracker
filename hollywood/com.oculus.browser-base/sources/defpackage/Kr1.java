package defpackage;

import J.N;
import android.view.View;
import org.chromium.chrome.browser.device_dialog.UsbChooserDialog;

/* renamed from: Kr1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Kr1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final UsbChooserDialog f8392a;

    public Kr1(UsbChooserDialog usbChooserDialog) {
        this.f8392a = usbChooserDialog;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        View view = (View) obj;
        long j = this.f8392a.b;
        if (j != 0) {
            N.M0RyTBvz(j);
            view.invalidate();
        }
    }
}
