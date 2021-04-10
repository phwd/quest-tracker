package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Process;
import android.util.SparseArray;
import android.webkit.URLUtil;
import com.google.android.gms.vision.barcode.Barcode;
import com.oculus.browser.R;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.concurrent.Executor;

/* renamed from: gJ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2678gJ0 implements Camera.PreviewCallback {

    /* renamed from: a  reason: collision with root package name */
    public final Context f9988a;
    public final UH0 b;
    public final XI0 c;
    public final AbstractC2809h6 d;
    public C1173Tf e = null;
    public C1184Ti1 f;

    public C2678gJ0(Context context, UH0 uh0, XI0 xi0) {
        this.f9988a = context;
        this.b = uh0;
        this.d = new I2(new WeakReference((Activity) context));
        a();
        this.c = xi0;
        C2507fJ0 fj0 = new C2507fJ0(this);
        Executor executor = AbstractC2032cb.f9616a;
        fj0.f();
        ((ExecutorC1463Ya) executor).execute(fj0.e);
    }

    public final void a() {
        this.b.j(AbstractC3703mJ0.b, Boolean.valueOf(this.d.canRequestPermission("android.permission.CAMERA")).booleanValue());
        this.b.j(AbstractC3703mJ0.f10414a, Boolean.valueOf(this.f9988a.checkPermission("android.permission.CAMERA", Process.myPid(), Process.myUid()) == 0).booleanValue());
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
        if (this.e != null) {
            ByteBuffer allocate = ByteBuffer.allocate(bArr.length);
            allocate.put(bArr);
            GT gt = new GT(null);
            int i = camera.getParameters().getPreviewSize().width;
            int i2 = camera.getParameters().getPreviewSize().height;
            if (allocate.capacity() >= i * i2) {
                gt.b = allocate;
                FT ft = gt.f8091a;
                ft.f8017a = i;
                ft.b = i2;
                ft.c = 17;
                if (allocate == null && gt.c == null) {
                    throw new IllegalStateException("Missing image data.  Call either setBitmap or setImageData to specify the image");
                }
                SparseArray b2 = this.e.b(gt);
                if (this.b.h(AbstractC3703mJ0.c)) {
                    if (b2.size() == 0) {
                        camera.setOneShotPreviewCallback(this);
                        return;
                    }
                    Barcode barcode = (Barcode) b2.valueAt(0);
                    if (!URLUtil.isValidUrl(barcode.G)) {
                        String string = this.f9988a.getString(R.string.f59740_resource_name_obfuscated_RES_2131953291, barcode.G);
                        C1184Ti1 ti1 = this.f;
                        if (ti1 != null) {
                            ti1.b.cancel();
                        }
                        C1184Ti1 b3 = C1184Ti1.b(this.f9988a, string, 1);
                        this.f = b3;
                        b3.b.show();
                        AbstractC3535lK0.a("SharingQRCode.ScannedNonURL");
                        camera.setOneShotPreviewCallback(this);
                        return;
                    }
                    Intent putExtra = new Intent().setAction("android.intent.action.VIEW").setData(Uri.parse(barcode.G)).setClass(this.f9988a, Lr.class).addFlags(268959744).putExtra("com.android.browser.application_id", this.f9988a.getPackageName()).putExtra("REUSE_URL_MATCHING_TAB_ELSE_NEW_TAB", true);
                    S20.a(putExtra);
                    this.f9988a.startActivity(putExtra);
                    this.c.f9201a.dismiss();
                    AbstractC3535lK0.a("SharingQRCode.ScannedURL");
                    return;
                }
                return;
            }
            throw new IllegalArgumentException("Invalid image data size.");
        }
    }
}
