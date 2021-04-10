package defpackage;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import org.chromium.device.usb.ChromeUsbService;

/* renamed from: ju  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3284ju extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ChromeUsbService f10245a;

    public C3284ju(ChromeUsbService chromeUsbService) {
        this.f10245a = chromeUsbService;
    }

    public void onReceive(Context context, Intent intent) {
        UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
        if ("android.hardware.usb.action.USB_DEVICE_ATTACHED".equals(intent.getAction())) {
            ChromeUsbService chromeUsbService = this.f10245a;
            N.MNmyS$Xi(chromeUsbService.f10966a, chromeUsbService, usbDevice);
        } else if ("android.hardware.usb.action.USB_DEVICE_DETACHED".equals(intent.getAction())) {
            ChromeUsbService chromeUsbService2 = this.f10245a;
            N.MrBuy2sN(chromeUsbService2.f10966a, chromeUsbService2, usbDevice.getDeviceId());
        } else if ("org.chromium.device.ACTION_USB_PERMISSION".equals(intent.getAction())) {
            ChromeUsbService chromeUsbService3 = this.f10245a;
            N.MDvFAfgT(chromeUsbService3.f10966a, chromeUsbService3, usbDevice.getDeviceId(), intent.getBooleanExtra("permission", false));
        }
    }
}
