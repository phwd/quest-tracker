package defpackage;

import J.N;
import android.hardware.camera2.CameraDevice;

/* renamed from: pt1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4309pt1 extends CameraDevice.StateCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5330vt1 f11096a;

    public C4309pt1(C5330vt1 vt1, AbstractC3283jt1 jt1) {
        this.f11096a = vt1;
    }

    public void onClosed(CameraDevice cameraDevice) {
        C5330vt1 vt1 = this.f11096a;
        if (vt1.j != null) {
            vt1.j = null;
        }
        vt1.o.open();
    }

    public void onDisconnected(CameraDevice cameraDevice) {
        AbstractC1220Ua0.a("VideoCapture", "cameraDevice was closed unexpectedly", new Object[0]);
        cameraDevice.close();
        C5330vt1 vt1 = this.f11096a;
        vt1.i = null;
        vt1.f(3);
    }

    public void onError(CameraDevice cameraDevice, int i) {
        AbstractC1220Ua0.a("VideoCapture", "cameraDevice encountered an error", new Object[0]);
        cameraDevice.close();
        C5330vt1 vt1 = this.f11096a;
        vt1.i = null;
        vt1.f(3);
        C5330vt1 vt12 = this.f11096a;
        long j = vt12.e;
        StringBuilder i2 = AbstractC2531fV.i("Camera device error ");
        i2.append(Integer.toString(i));
        N.MhmwjISE(j, vt12, 69, i2.toString());
    }

    public void onOpened(CameraDevice cameraDevice) {
        AbstractC1220Ua0.a("VideoCapture", "CameraDevice.StateCallback onOpened", new Object[0]);
        C5330vt1 vt1 = this.f11096a;
        vt1.i = cameraDevice;
        vt1.o.close();
        this.f11096a.f(1);
        C5330vt1.e(this.f11096a, 114);
    }
}
