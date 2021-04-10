package defpackage;

import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.oculus.browser.R;
import org.chromium.ui.widget.ButtonCompat;

/* renamed from: kJ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3361kJ0 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f10272a;
    public final FrameLayout b;
    public final Camera.PreviewCallback c;
    public boolean d;
    public boolean e;
    public boolean f;
    public SurfaceHolder$CallbackC2065cm g;
    public View h;
    public View i;
    public View j;
    public final Camera.ErrorCallback k = new C3020iJ0(this);

    public C3361kJ0(Context context, Camera.PreviewCallback previewCallback, C1995cJ0 cj0) {
        this.f10272a = context;
        this.c = previewCallback;
        FrameLayout frameLayout = new FrameLayout(context);
        this.b = frameLayout;
        View inflate = LayoutInflater.from(context).inflate(R.layout.f41010_resource_name_obfuscated_RES_2131624410, (ViewGroup) null, false);
        ((ButtonCompat) inflate.findViewById(R.id.open_settings_button)).setOnClickListener(new View$OnClickListenerC3190jJ0(this, context));
        this.j = inflate;
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        View inflate2 = LayoutInflater.from(context).inflate(R.layout.f41020_resource_name_obfuscated_RES_2131624411, (ViewGroup) null, false);
        ((ButtonCompat) inflate2.findViewById(R.id.ask_for_permission)).setOnClickListener(new View$OnClickListenerC2849hJ0(this, cj0));
        this.h = inflate2;
        this.i = LayoutInflater.from(context).inflate(R.layout.f40990_resource_name_obfuscated_RES_2131624408, (ViewGroup) null, false);
    }

    public final void a() {
        SurfaceHolder$CallbackC2065cm cmVar = this.g;
        if (cmVar != null) {
            if (!this.f || !this.d) {
                cmVar.b();
                return;
            }
            if (cmVar.K == null) {
                HandlerThread handlerThread = new HandlerThread("CameraHandlerThread");
                cmVar.K = handlerThread;
                handlerThread.start();
            }
            int numberOfCameras = Camera.getNumberOfCameras();
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            int i2 = -1;
            int i3 = 0;
            while (true) {
                if (i3 >= numberOfCameras) {
                    i3 = i2;
                    break;
                }
                Camera.getCameraInfo(i3, cameraInfo);
                if (cameraInfo.facing == 0) {
                    break;
                }
                i2 = i3;
                i3++;
            }
            cmVar.I = i3;
            new Handler(cmVar.K.getLooper()).post(new RunnableC1714am(cmVar));
        }
    }

    public final void b() {
        if (this.f) {
            boolean z = this.d;
            if (z && this.g == null) {
                this.b.removeAllViews();
                SurfaceHolder$CallbackC2065cm cmVar = this.g;
                if (cmVar != null) {
                    cmVar.b();
                    this.g = null;
                }
                if (this.d) {
                    SurfaceHolder$CallbackC2065cm cmVar2 = new SurfaceHolder$CallbackC2065cm(this.f10272a, this.c, this.k);
                    this.g = cmVar2;
                    this.b.addView(cmVar2);
                    this.b.addView(new C2236dm(this.f10272a));
                    a();
                }
            } else if (z && this.g != null) {
                a();
            } else if (this.e) {
                this.b.removeAllViews();
                this.b.addView(this.h);
            } else {
                this.b.removeAllViews();
                this.b.addView(this.j);
            }
        }
    }
}
